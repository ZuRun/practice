package cn.zull.practice.design.decorator.cake;

/**
 * 鸡蛋1元
 *
 * @author zurun
 * @date 2018/11/5 00:43:24
 */
public class Egg extends HandPancakeDecorator {
    public Egg(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String offer() {
        return super.offer() + "加鸡蛋";
    }

    @Override
    public Integer calcCost() {
        return super.calcCost() + 1;
    }
}
