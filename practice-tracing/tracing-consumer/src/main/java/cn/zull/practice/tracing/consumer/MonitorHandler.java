package cn.zull.practice.tracing.consumer;

import cn.zull.practice.common.redisson.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zurun
 * @date 2019/3/10 18:27:11
 */
@Slf4j
@Component
public class MonitorHandler implements CommandLineRunner {
    private final String key = "p:tracing:log";
    @Autowired
    RedisUtils<String, String, String> redisUtils;
    private static long[] mark = new long[2];


    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            log.info("[监控线程]");
            while (true) {
                try {
                    long now = System.currentTimeMillis();
                    long lastTime = mark[0];
                    int length = redisUtils.llen(key);
                    if (lastTime != 0) {
                        long cost = (now - lastTime) / 1000;
                        long lastLength = mark[1];
                        long difference = length - lastLength;
                        log.info("[redis队列长度] key:{} length:{} rate:{}/s", key, length, difference / cost);

                    }
                    mark[0] = now;
                    mark[1] = length;
                    Thread.sleep(1000L);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "queue-monitor-thread").start();

    }

    public static void main(String[] args) {
        int a = 1;
        System.out.println(mark[1]);
        System.out.println("" + mark.length);

        mark[0] = 11;
        mark[1] = a;
        System.out.println("" + mark.length);
    }
}
