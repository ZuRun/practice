package cn.zull.tracing.concurrency.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作
 *
 * @author zurun
 * @date 2018/11/24 00:10:20
 */
public class Counter {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 10000; i++) {
                    cas.count();
                    cas.safeCount();
                }
            });
            ts.add(thread);
        }

        for (Thread thread : ts) {
            thread.start();
        }
        for (Thread thread : ts) {
            thread.join();
        }

        System.out.println("i:" + cas.i);
        System.out.println("atomicI:" + cas.atomicInteger.get());
        System.out.println(System.currentTimeMillis() - start + "ms");
    }


    private void safeCount() {
        for (; ; ) {
            int i = atomicInteger.get();
//            atomicInteger.
            boolean isSuccess = atomicInteger.compareAndSet(i, ++i);
            if (isSuccess) {
                break;
            }
        }
    }

    private void count() {
        i++;
    }
}
