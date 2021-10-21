package com.jun.biz.common.base.dto;

import lombok.Data;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created on 2019/11/18 18:08
 * <p>
 * <p>
 *
 * 
 */
@Data
public class PageDTO {
    private Integer page;
    private Integer limit;

    private String orderBy;


    public Map<String, Object> toMap() {
        try {
            Map<String, Object> cond = PropertyUtils.describe(this);
            if (page != null && limit != null) {
                cond.put("beginIndex", (page - 1) * limit);
                cond.put("resultSize", limit);
            }
            cond.remove("page");
            cond.remove("limit");
            return cond;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
