package cn.zull.practice.design.decorator.cake;

/**
 * 手抓饼接口 描述抽象的手抓饼
 *
 * @author zurun
 * @date 2018/11/5 00:18:05
 */
public interface HandPancake {
    /**
     * 提供
     */
    String offer();

    /**
     * 计算价格
     *
     * @return
     */
    Integer calcCost();
}
