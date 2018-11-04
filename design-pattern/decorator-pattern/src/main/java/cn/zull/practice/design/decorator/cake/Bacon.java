package cn.zull.practice.design.decorator.cake;

/**
 * 培根 3元
 *
 * @author zurun
 * @date 2018/11/5 00:42:20
 */
public class Bacon extends HandPancakeDecorator {
    public Bacon(HandPancake handPancake) {
        super(handPancake);
    }

    @Override
    public String offer() {
        return super.offer() + "加培根";
    }

    @Override
    public Integer calcCost() {
        return super.calcCost() + 3;
    }
}
