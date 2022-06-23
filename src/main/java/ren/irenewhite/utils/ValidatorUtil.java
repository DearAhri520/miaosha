package ren.irenewhite.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DearAhri520
 * @date 2022/6/20
 */
public class ValidatorUtil {
    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobileNumber(String s) {
        if (StringUtils.isEmpty(s)) {
            return false;
        }
        Matcher m = MOBILE_PATTERN.matcher(s);
        return m.matches();
    }
}
