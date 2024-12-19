package com.ruoyi.project.system.dict.utils;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StrUtils;
import com.ruoyi.project.system.dict.domain.DictData;

/**
 * 字典工具类
 * @author ruoyi
 */
public class DictUtils {
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * 设置字典缓存
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    public static void setDictCache(String key, List<DictData> dictDatas) {
        CacheUtils.put(getCacheName(), getCacheKey(key), dictDatas);
    }
    /**
     * 移除字典缓存
     * @param key       参数键
     */
    public static void removeDictCache(String key) {
        CacheUtils.remove(getCacheName(), getCacheKey(key));
    }

    /**
     * 获取字典缓存
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<DictData> getDictCache(String key) {
        Object cacheObj = CacheUtils.get(getCacheName(), getCacheKey(key));
        if (ObjectUtil.isNotNull(cacheObj)) {
            List<DictData> DictDatas = StrUtils.cast(cacheObj);
            return DictDatas;
        }
        return null;
    }

    /**
     * 根据字典类型和字典值获取字典标签
     * @param dictType 字典类型
     * @param dictValue 字典值
     * @return 字典标签
     */
    public static String getDictLabel(String dictType, String dictValue) {
        return getDictLabel(dictType, dictValue, SEPARATOR);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @return 字典值
     */
    public static String getDictValue(String dictType, String dictLabel) {
        return getDictValue(dictType, dictLabel, SEPARATOR);
    }

    /**
     * 根据字典类型和字典值获取字典标签
     * @param dictType  字典类型
     * @param dictValue 字典值
     * @param separator 分隔符
     * @return 字典标签
     */
    public static String getDictLabel(String dictType, String dictValue, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<DictData> datas = getDictCache(dictType);

        if (StrUtil.containsAny(separator, dictValue) && CollUtil.isNotEmpty(datas)) {
            for (DictData dict : datas) {
                for (String value : dictValue.split(separator)) {
                    if (value.equals(dict.getDictValue())) {
                        propertyString.append(dict.getDictLabel() + separator);
                        break;
                    }
                }
            }
        } else {
            for (DictData dict : datas) {
                if (dictValue.equals(dict.getDictValue())) {
                    return dict.getDictLabel();
                }
            }
        }
        return StrUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 根据字典类型和字典标签获取字典值
     * @param dictType  字典类型
     * @param dictLabel 字典标签
     * @param separator 分隔符
     * @return 字典值
     */
    public static String getDictValue(String dictType, String dictLabel, String separator) {
        StringBuilder propertyString = new StringBuilder();
        List<DictData> datas = getDictCache(dictType);

        if (StrUtil.containsAny(separator, dictLabel) && CollUtil.isNotEmpty(datas)) {
            for (DictData dict : datas) {
                for (String label : dictLabel.split(separator)) {
                    if (label.equals(dict.getDictLabel())) {
                        propertyString.append(dict.getDictValue() + separator);
                        break;
                    }
                }
            }
        } else {
            for (DictData dict : datas) {
                if (dictLabel.equals(dict.getDictLabel())) {
                    return dict.getDictValue();
                }
            }
        }
        return StrUtils.stripEnd(propertyString.toString(), separator);
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        CacheUtils.removeAll(getCacheName());
    }

    /**
     * 获取cache name
     * @return 缓存名
     */
    public static String getCacheName() {
        return Constants.SYS_DICT_CACHE;
    }

    /**
     * 设置cache key
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey) {
        return Constants.SYS_DICT_KEY + configKey;
    }
}
