package cn.zull.practice.tracing.consumer;

import cn.zull.practice.common.redisson.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.client.RedisConnectionClosedException;
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
 *
 * @author zurun
 * @date 2019/3/3 23:56:35
 */
@Slf4j
@Component
public class TraceConsumerService
        implements CommandLineRunner
{
    private final int consumerPoolSize = 3;
    //    private final int consumerPoolSize = 50;
    private final String key = "p:tracing:log";
    @Autowired
    RedisUtils<String, String, String> redisUtils;

    //    @Override
    public void run(String... args) {


        ExecutorService executorService = Executors.newFixedThreadPool(consumerPoolSize);
        for (int i = 0; i < consumerPoolSize; i++) {
            executorService.execute(consuemrRunnable);
        }
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            log.warn("[线程关闭]");
//            executorService.shutdown();
//            try {
//                if (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
//                    log.warn("[线程关闭awaitTermination]");
//                    executorService.shutdownNow();
//
//                }
//            } catch (InterruptedException e) {
//                log.warn("[线程关闭InterruptedException]");
//                e.printStackTrace();
//                executorService.shutdownNow();
//            }
//        }));
    }

    private Runnable consuemrRunnable = () -> {
        boolean bl = true;
        int size = 0;
        long startTime = System.currentTimeMillis();
        while (bl) {
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                String uuid = redisUtils.bLPop(key, 50);
                stopWatch.stop();
                size++;
                long now = System.currentTimeMillis();
                if (now - startTime > 10000) {
//                    bl = false;
                }
//                log.info("[读redis] uuid:{} 耗时:{}", uuid, stopWatch.getTotalTimeMillis());
            } catch (InterruptedException e) {
                log.warn("[消费线程InterruptedException]");
                Thread.currentThread().interrupt();
            } catch (RedisConnectionClosedException e) {
                log.warn("[redis连接断开]");
                Thread.currentThread().interrupt();
            }
        }

        log.info("消费数量:{}", size);
    };
}
