package cn.zull.practice.design.decorator.cake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zurun
 * @date 2018/11/5 00:44:28
 */
public class HandPancakeTest {
    private static final Logger logger = LoggerFactory.getLogger(HandPancakeTest.class);

    public static void main(String[] args) {
        HandPancake handPancake = new Egg(new Bacon(new OriginalHandPancake()));
        logger.info("【offer】:{} 【price】:{}", handPancake.offer(), handPancake.calcCost());
    }
}
