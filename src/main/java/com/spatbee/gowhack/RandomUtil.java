package com.spatbee.gowhack;

import java.util.Random;

public class RandomUtil {
    public static Random random;

    private static Random getRandom() {
        if(random == null) {
            random = new Random(System.currentTimeMillis());
        }
        return random;
    }

    public static int randomInt(int bound) {
        return getRandom().nextInt(bound);
    }
}
