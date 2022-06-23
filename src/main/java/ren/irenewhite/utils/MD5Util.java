package ren.irenewhite.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author DearAhri520
 * @date 2022/6/19
 */
public class MD5Util {
    private static final String salt = "1a2b3c4d";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassToFormPass(String inputPass) {
        return md5(inputPass + salt);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        return md5(formPass+salt);
    }

    public static String inputPassToDBPass(String inputPass,String salt){
        return formPassToDBPass(inputPassToFormPass(inputPass),salt);
    }
}
