package cn.zull.practice.tracing.provider;

import cn.zull.practice.common.redisson.RedisUtils;
import cn.zull.practice.tracing.appender.TraceAppend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author zurun
 * @date 2019/3/3 21:47:13
 */
@Slf4j
@Component
public class BaseAppender implements TraceAppend, CommandLineRunner {
    public static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(1000);
    private int consumerPoolSize = 10;
    private final String key = "p:tracing:log";
    @Autowired
    RedisUtils<String, String, String> redisUtils;

    private Runnable consumerRunnable = () -> {
        List<String> list = new ArrayList(20);
        while (true) {
            try {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                for (int i = 0; i < 20; i++) {
                    String data = blockingQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (data == null) {
                        continue;
                    }
//                    log.info(uuid + ":" + blockingQueue.size());
                    list.add(data);
                }
                redisUtils.rPush(key, list);
                log.info("[写redis] size:{} 耗时:{}", list.size(), stopWatch.getTotalTimeMillis());

                list.clear();
                Thread.sleep(10L);
                stopWatch.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(consumerPoolSize);
        for (int i = 0; i < consumerPoolSize; i++) {
            executorService.execute(consumerRunnable);
        }
    }

}
