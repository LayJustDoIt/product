package org.lay.product.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Json格式化Util
 * Create by Lay
 * 2018-03-25 17:20
 */
public class JsonUtil {

    /**
     * json美化
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        // 创建GsonBuilder对象
        GsonBuilder gsonBuilder = new GsonBuilder();
        // 调用setPrettyPrinting()
        gsonBuilder.setPrettyPrinting();
        // 创建gson对象
        Gson gson = gsonBuilder.create();
        return gson.toJson(obj);
    }
}
