package cn.zull.practice.common.basis.utils;

import java.util.Random;

/**
 * @author zurun
 * @date 2018/11/26 21:14:33
 */
public class RandomUtils {
    private static Random RANDOM = new Random();

    /**
     * 0-max 之间的随机证书
     *
     * @param max
     * @return
     */
    public static int randomNumber(int max) {
        return RANDOM.nextInt(max);
    }


    public static int getRangeNum(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getRangeNum(1,20));
        }
    }
}
