package cn.zull.practice.mysql.a.config;

import java.util.concurrent.Executor;

/**
 * 业务相关,用于业务线程池使用
 * 进行了扩展,用于线程池复用于不同业务,但是又能限制不同业务使用的线程数
 *
 * @author zurun
 * @date 2019/7/23 20:43:07
 */
public interface BizExecutor extends Executor {
    /**
     * 同{@link Executor#execute(Runnable)}
     * type参数指定线程池类型
     *
     * @param runnable
     * @param type
     */
    void execute(Runnable runnable, BizThreadType type);
}
