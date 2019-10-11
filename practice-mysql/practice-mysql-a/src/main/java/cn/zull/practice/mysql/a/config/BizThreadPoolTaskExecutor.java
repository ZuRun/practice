package cn.zull.practice.mysql.a.config;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * @author zurun
 * @date 2019/7/23 21:06:38
 */
@Slf4j
public class BizThreadPoolTaskExecutor implements BizExecutor {
    private final Executor executor;
    private final Lock lock = BizThreadType.ThreadPoolLock.LOCK;

    public BizThreadPoolTaskExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }

    @Override
    public void execute(Runnable command, BizThreadType type) {
        AtomicInteger atomicInteger = type.getCounter();
        lock.lock();
        try {
            // 当type业务类型获取的线程数量大于配置时,阻塞获取新的线程
            while (atomicInteger.get() >= type.getMaxSize()) {
                type.getCondition().await();
            }
            atomicInteger.incrementAndGet();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return;
        } finally {
            lock.unlock();
        }
        executor.execute(() -> {
            final String threadName = Thread.currentThread().getName();
            try {
                Thread.currentThread().setName(type.getThreadName());
                command.run();

            } finally {
                lock.lock();
                try {
                    atomicInteger.getAndDecrement();
                    // 释放线程阻塞,注意需要每种都通知一次
                    // 否则会出现一下类似死锁的场景:
                    // 当login线程全部都在阻塞状态时(登录队列满了所以阻塞),此时获取到了signal通知,结果还是阻塞,导致其他线程拿不到
                    BizThreadType.signalBothType();
                } finally {
                    lock.unlock();
                }
                Thread.currentThread().setName(threadName);
            }
        });
    }
}