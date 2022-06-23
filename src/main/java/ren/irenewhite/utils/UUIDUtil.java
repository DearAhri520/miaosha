package ren.irenewhite.utils;

import java.util.UUID;

/**
 * @author DearAhri520
 * @date 2022/6/23
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
