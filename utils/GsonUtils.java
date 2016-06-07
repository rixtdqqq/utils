package com.zhuyx.mytraining.util;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.zhuyx.mytraining.entity.ResponseEntity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * gson解析工具类
 * Created by zhuyingxin on 2016/6/7.
 * email : rixtdqqq_2015@163.com
 */
public class GsonUtils {
    public static <T> ResponseEntity<T> parseObject(String json, Type type) throws JsonSyntaxException {
        return parse2Object(json, null, type);
    }

    public static <T> ResponseEntity<T> parseObject(String json, FieldNamingStrategy strategy, Type type) throws JsonSyntaxException {
        return parse2Object(json, strategy, type);
    }

    public static <T> ResponseEntity<List<T>> parseList(String json, Type type) throws JsonSyntaxException {
        return parse2Object(json, null, type);
    }

    public static <T> ResponseEntity<List<T>> parseList(String json, FieldNamingStrategy strategy, Type type) throws JsonSyntaxException {
        return parse2Object(json, strategy, type);
    }

    public static <T> T parse2Object(String json, FieldNamingStrategy strategy, Type type) {
        GsonBuilder builder = new GsonBuilder();
        if (null != strategy) {
            builder.setFieldNamingStrategy(strategy);
        }
        return builder.create().fromJson(json, type);
    }

    public static String parseObject2String(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static String parseObject2Json(Object object){
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(object);

    }

}
