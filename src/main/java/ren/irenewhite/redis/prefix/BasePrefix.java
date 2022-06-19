package ren.irenewhite.redis.prefix;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author DearAhri520
 * @date 2022/6/1
 */
@AllArgsConstructor
public abstract class BasePrefix implements Prefix {
    @Getter
    private String prefix;
}