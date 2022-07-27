package ren.irenewhite.redis.key;

import ren.irenewhite.redis.prefix.BasePrefix;

/**
 * @author DearAhri520
 * @date 2022/7/26
 */
public class SnipingKey extends BasePrefix {
    /**
     * 五分钟有效期
     */
    private static final int EXPIRE = 60;

    private SnipingKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static SnipingKey SnipingPath = new SnipingKey(EXPIRE, "sp");
    public static SnipingKey SnipingVerifyCode = new SnipingKey(5 * 60, "svc");
}
