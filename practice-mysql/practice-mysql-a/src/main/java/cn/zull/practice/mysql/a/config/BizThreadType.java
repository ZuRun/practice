package cn.zull.practice.mysql.a.config;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定义使用线程的业务类型
 *
 * @author zurun
 * @date 2019/7/23 12:28:52
 */
public enum BizThreadType {
    /**/
    GOODS_INITIALIZE(5, "biz-goods-init"),
    BUYER_INITIALIZE(50, "biz-goods-init"),
    ORDER_BATCH_INSERT(50, "biz-order-insert"),
//    CONNECT(5, "mock-biz-connect"),
//    BIZ(1000)
    ;
    /**
     * 默认可以使用的最大线程数
     */
    private int maxSize;
    private String threadName;
    /**
     * 正在使用的线程数
     */
    private final AtomicInteger counter = new AtomicInteger(0);
    private final Condition condition;

    BizThreadType(int maxSize, String threadName) {
        this.maxSize = maxSize;
        this.threadName = threadName;
        this.condition = ThreadPoolLock.LOCK.newCondition();
    }

    public Condition getCondition() {
        return condition;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public String getThreadName() {
        return threadName;
    }

    /**
     * 每种类型都signal一次
     */
    public static void signalBothType() {
        for (BizThreadType type : BizThreadType.values()) {
            type.getCondition().signal();
        }
    }


    public BizThreadType setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        return this;
    }

    public interface ThreadPoolLock {
        Lock LOCK = new ReentrantLock();
    }

}
