package cn.zull.practice.limiting.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 平滑限流
 *
 * @author zurun
 * @date 2019/6/10 10:19:33
 */
public class SmoothBurstyTest {
    private final RateLimiter limiter = RateLimiter.create(20);
    private final int poolSize = 11;
    private final AtomicInteger success = new AtomicInteger(0);
    private final AtomicInteger total = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        SmoothBurstyTest test = new SmoothBurstyTest();
        test.rateLimiter();
    }


    private Runnable runnable = () -> {
        try {
            while (true) {
//            System.out.println(getDateTime() + " " + limiter.acquire());
//            System.out.println(getDateTime() + "  " + limiter.tryAcquire());
                boolean isAcquire = limiter.tryAcquire();
                if (isAcquire) {
                    success.addAndGet(1);
                }
                total.addAndGet(1);

//            System.out.println(getDateTime()  );
//                System.out.println(limiter.);
                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public void rateLimiter() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            System.out.println(i);
            executorService.execute(runnable);
        }
        System.out.println("线程启动完成");
        while (true) {
            Thread.sleep(1000);
            System.out.println("total:" + total.get() + "  -  success:" + success.get());
        }
    }

    private String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}
