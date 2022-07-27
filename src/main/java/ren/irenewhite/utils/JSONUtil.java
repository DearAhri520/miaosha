package ren.irenewhite.utils;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author DearAhri520
 * @date 2022/7/20
 */
public class JSONUtil {
    public static <T> String toJSON(T t) {
        return JSONObject.toJSONString(t);
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        return JSONObject.parseObject(json,clazz);
    }
}
