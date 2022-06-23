package ren.irenewhite.redis.key;

import ren.irenewhite.redis.prefix.BasePrefix;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */
public class UserKey extends BasePrefix {
    /**
     * 两天有效期
     */
    private static final int TOKEN_EXPIRE = 3600*24*2;

    private UserKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static UserKey token = new UserKey(TOKEN_EXPIRE,"token");
}
