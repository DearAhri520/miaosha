package ren.irenewhite.redis.prefix;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */
public interface Prefix {
    /**
     * 获取前缀
     *
     * @return 前缀
     */
    String getPrefix();

    /**
     * 获取过期时间
     *
     * @return 过期时间
     */
    int expireSeconds();
}