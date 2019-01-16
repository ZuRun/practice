package cn.zull.practice.performance.dubbo.api.service;

/**
 * @author zurun
 * @date 2019/1/16 00:46:08
 */
public interface SimpleService {
    int plus(int num1, int num2);

    int plusWithSleep20(int num1, int num2);

    int plusWithSleep50(int num1, int num2);

    int plusWithSleep100(int num1, int num2);
}
