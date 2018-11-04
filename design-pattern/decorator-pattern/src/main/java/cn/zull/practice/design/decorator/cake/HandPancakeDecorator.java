package cn.zull.practice.design.decorator.cake;

/**
 * @author zurun
 * @date 2018/11/5 00:39:18
 */
public abstract class HandPancakeDecorator implements HandPancake {
    private HandPancake handPancake;

    public HandPancakeDecorator(HandPancake handPancake) {
        this.handPancake = handPancake;
    }

    @Override
    public String offer() {
        return handPancake.offer();
    }

    @Override
    public Integer calcCost() {
        return handPancake.calcCost();
    }
}
