package cn.zull.practice.performance.redis.service;

import cn.zull.practice.common.basis.func.NoArgsFunction;
import cn.zull.practice.common.basis.utils.UUIDUtils;
import cn.zull.practice.common.redisson.RedisUtils;
import cn.zull.practice.performance.redis.constants.RedisConstants;
import cn.zull.practice.performance.redis.dto.RedisTpsRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import sun.jvm.hotspot.utilities.BitMap;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author zurun
 * @date 2019/10/16 10:13:07
 */
@Slf4j
@Service
public class RedisTestService {
    @Autowired
    RedisUtils<String, String, String> redisUtils;

    final String prefix = RedisConstants.PREFIX;

    private RedisTpsRespDTO batch(int threadSize, int cycleNum, Consumer<Integer> consumer) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        final AtomicInteger num = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        final Runnable runnable = () -> {
            for (int i = 0; i < cycleNum; i++) {
                consumer.accept(num.getAndIncrement());
            }
            countDownLatch.countDown();
        };
        List<Thread> threads = new ArrayList<>(threadSize);
        for (int i = 0; i < threadSize; i++) {
            threads.add(new Thread(runnable));
        }
        threads.forEach(thread -> thread.start());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopWatch.stop();
        double totalTimeSeconds = stopWatch.getTotalTimeSeconds();

        RedisTpsRespDTO respDTO = new RedisTpsRespDTO();
        respDTO.setCycleNum(cycleNum);
        respDTO.setThreadSize(threadSize);
        respDTO.setTotalTime(totalTimeSeconds);
        respDTO.setTps(threadSize * cycleNum / totalTimeSeconds);
        log.warn("[redis操作] threadSize:{} cycleNum:{} timeSecond:{} tps:{}", threadSize, cycleNum, totalTimeSeconds, respDTO.getTps());
        return respDTO;

    }

    public RedisTpsRespDTO batchRead(int threadSize, int cycleNum) {
        return batch(threadSize, cycleNum, (num) -> {
            String key = prefix + UUIDUtils.simpleUUID() + ":" + num;
            long startTime = System.nanoTime();
            redisUtils.get(key);
            long endTime = System.nanoTime();
            System.out.println(endTime - startTime);
        });
    }


    public RedisTpsRespDTO batchWrite(int threadSize, int cycleNum) {
        return batch(threadSize, cycleNum, (num) -> {
            String key = prefix + UUIDUtils.simpleUUID() + ":" + num;
            redisUtils.set(key, RedisConstants.DEF_VAL, 1, TimeUnit.MINUTES);
        });
    }


    public static void main(String[] args) {
        BitMap bitMap = new BitMap(121);
        bitMap.atPut(1, true);
        bitMap.atPut(1, false);
        bitMap.atPut(100, true);
        bitMap.atPut(101, true);
        System.out.println(bitMap.at(100));
        System.out.println(bitMap.at(1));

        System.out.println("----------");
        BitSet bitSet = new BitSet(111);
        bitSet.set(1);
        bitSet.set(100);
        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(100));
        System.out.println(bitSet);
        System.out.println(bitSet.size());
        System.out.println("----------");

        System.out.println(1 << 6);
        System.out.println(128 >> 6);
    }
}
