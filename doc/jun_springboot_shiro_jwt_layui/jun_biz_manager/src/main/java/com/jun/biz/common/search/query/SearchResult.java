package com.jun.biz.common.search.query;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2018/5/16 13:23
 * <p>
 * Description: []
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class SearchResult<T> {
    private List<T> list;
    private Long total;
    /**
     * key:属性名
     * value:hash
     * key:属性值
     * value:个数
     */
    private Map<String, Map<Number, Long>> facets;

    private Map<String,Map<String, List<String>>> highlightFields = new HashMap<>();

}
