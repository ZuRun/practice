package cn.zull.practice;

import cn.zull.practice.tracing.provider.BaseAppender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zurun
 * @date 2019/3/3 21:56:25
 */
@SpringBootApplication
public class TraceProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TraceProviderApplication.class, args);
        new TraceProviderApplication().provider();
    }

    public void provider() {
        int poolSize = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(providerRunnable);
        }
    }

    private Runnable providerRunnable = () -> {
        while (true) {
            try {
                Thread.sleep(1);
                BaseAppender.blockingQueue.put(UUID.randomUUID().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
