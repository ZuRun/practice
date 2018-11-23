package cn.zull.tracing.concurrency.chapter1;

/**
 * @author zurun
 * @date 2018/11/23 22:49:15
 */
public class ContextSwitch {
    private static final int count = 1;

    /**
     * 上下文切换,有可能导致多线程操作更慢
     * <p>
     * 此测试,理论上,count越大,多线程执行越快
     * <p>
     * 线程的创建和上下文切换开销
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        serial();
        concurrency();

    }

    /**
     * 并发执行
     */
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
        });

        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        thread.join();

        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency:" + time + "ms,b=" + b);
    }

    /**
     * 同步执行
     */
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        System.out.println("serial:" + time + "ms,b=" + b);
    }
}
