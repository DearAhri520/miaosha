package ren.irenewhite.redis.prefix;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */
public abstract class BasePrefix implements Prefix {

    /**
     * 过期时间
     */
    private int expireSeconds;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 0代表永不过期
     */
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {//默认0代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}