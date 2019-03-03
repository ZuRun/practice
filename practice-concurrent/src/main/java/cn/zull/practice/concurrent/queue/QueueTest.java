package cn.zull.practice.concurrent.queue;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author zurun
 * @date 2019/3/3 20:50:00
 */
public class QueueTest {
    private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        QueueTest queueTest = new QueueTest();
        queueTest.provider();
        queueTest.consuemr();
    }

    public void consuemr() throws InterruptedException {
        int poolSize = 2;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(consuemrRunnable);
        }

    }

    public void provider() {
        int poolSize = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.execute(providerRunnable);
        }
    }

    private Runnable consuemrRunnable = () -> {
        while (true) {
            String uuid = null;
            try {
                uuid = blockingQueue.poll(10, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(uuid + ":" + blockingQueue.size());

        }
    };
    private Runnable providerRunnable = () -> {
        while (true) {
            try {
                Thread.sleep(10);
                blockingQueue.put(UUID.randomUUID().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
