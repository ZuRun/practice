package cn.zull.practice.tracing.consumer;

import cn.zull.practice.common.redisson.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 总结:
 * 10s钟
 * 20线程:5850*20/10=11700tps
 * 30线程:4650*30/10=13950tps
 * 40线程:3650*40/10=14600tps
 * 50线程:3200*50/10=16000tps
 * @author zurun
 * @date 2019/3/3 23:56:35
 */
@Slf4j
@Component
public class TraceConsumerService implements CommandLineRunner {
    private int consumerPoolSize = 50;
    private final String key = "p:tracing:log";
    @Autowired
    RedisUtils<String, String, String> redisUtils;

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(consumerPoolSize);
        for (int i = 0; i < consumerPoolSize; i++) {
            executorService.execute(consuemrRunnable);
        }
    }

    private Runnable consuemrRunnable = () -> {
        boolean bl = true;
        int size = 0;
        long startTime = System.currentTimeMillis();
        while (bl) {
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                String uuid = redisUtils.lPop(key, 10);
                stopWatch.stop();
                size++;
                long now = System.currentTimeMillis();
                if (now - startTime > 10000) {
                    bl = false;
                }
//                log.info("[读redis] uuid:{} 耗时:{}", uuid, stopWatch.getTotalTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }

        log.info("消费数量:{}", size);
    };
}
