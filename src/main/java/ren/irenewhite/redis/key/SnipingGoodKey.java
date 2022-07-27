package ren.irenewhite.redis.key;

import ren.irenewhite.redis.prefix.BasePrefix;

/**
 * @author DearAhri520
 * @date 2022/7/15
 */
public class SnipingGoodKey extends BasePrefix {
    /**
     * 一小时有效期
     */
    private static final int EXPIRE = 60 * 60;

    private SnipingGoodKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SnipingGoodKey SnipingGood = new SnipingGoodKey(EXPIRE, "sg");

    public static SnipingGoodKey SnipingGoodStock = new SnipingGoodKey(EXPIRE, "sgt");
}