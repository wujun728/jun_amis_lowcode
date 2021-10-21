package com.jun.biz.common.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @date 2018年7月13日
 * <p>
 * Description: [枚举帮助类，提供防止code重复，根据code获取枚举]
 * <p>
 */
public class EnumUtil {
    private static final Map<Object, Object> CACHE = new ConcurrentHashMap<>();


    public static <T extends Enum<T>> void putEnum(Object code, T t) {
        String key = t.getClass().toString().concat("_").concat(code.toString());
        if (CACHE.containsKey(key)) {
            throw new IllegalStateException("code 已存在！" + t);
        }
        CACHE.put(key, t);
    }

    /**
     * 通过枚举code，获取枚举对象。<br/>
     * 注意：如果直接调用此方法，枚举类没有被加载，会返回空！
     * 要使用枚举内静态方法来调用
     *
     * @param clazz
     * @param code
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T getEnum(Class<T> clazz, Object code) {
        if (code == null) {
            return null;
        }
        return (T) CACHE.get(clazz.toString().concat("_").concat(code.toString()));
    }
}
