package ren.irenewhite.redis.key;

import ren.irenewhite.redis.prefix.BasePrefix;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */
public class GoodListKey extends BasePrefix {
    /**
     * 一分钟有效期
     */
    private static final int EXPIRE = 60;

    private GoodListKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }

    public static GoodListKey GoodList = new GoodListKey(EXPIRE,"gl");
}
