package ren.irenewhite.redis.key;

import ren.irenewhite.redis.prefix.BasePrefix;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */
public class UserKey extends BasePrefix {
    private UserKey(String prefix) {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
