package cn.zull.practice.design.decorator.cake;

/**
 * 原味手抓饼
 *
 * @author zurun
 * @date 2018/11/5 00:37:18
 */
public class OriginalHandPancake implements HandPancake {
    @Override
    public String offer() {
        return "原味手抓饼";
    }

    @Override
    public Integer calcCost() {
        return 5;
    }
}
