package com.jun.biz.common.search.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.Serializable;
import java.util.*;

/**
 * Created on 2018/5/16 10:45
 * <p>
 * Description: []
 * <p>
 * Company: []
 *
 * 
 */
@Data
public class SearchQuery {

    private Map<String, Float> defaultSearchFields = new HashMap<>();
    private String keywords;
    private Set<String> highlightProps = new HashSet<>();
    private String[] facetFields;
    private Integer pageNum;
    private Integer pageSize;
    private Set<Condition> conds = new HashSet<>();

    private List<Sort> sorts = new ArrayList<>();

    private List<Between> betweens = new ArrayList<>();

    public void addConditon(String name, Object value) {
        addConditon(name, value, false);
    }

    public void addConditon(Condition condition) {
        conds.add(condition);
    }

    public void addConditon(String name, Object value, boolean or) {
        Condition cond = new Condition();
        cond.setName(name);
        if (value instanceof Collection) {
            cond.setValues((Collection<?>) value);
        } else {
            cond.setValues(Collections.singleton(value));
        }
        cond.setOr(or);
        conds.add(cond);
    }

    public void addSort(String field) {
        addSort(field, false);
    }

    public void addSort(String field, boolean desc) {
        sorts.add(new Sort(field, desc));
    }


    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void addBetween(String name, Object min, Object max) {
        this.betweens.add(new Between(name, min, max));
    }

    public void addHighlightProp(String... highlightProp) {
        this.highlightProps.addAll(Arrays.asList(highlightProp));
    }

    public void setFacetFields(String... facetFields) {
        this.facetFields = facetFields;
    }

    public List<SortBuilder<?>> genSortBuilder() {
        if (sorts.size() > 0) {
            List<SortBuilder<?>> sortBuilders = new ArrayList<>(sorts.size());
            for (Sort s : sorts) {
                sortBuilders.add(SortBuilders.fieldSort(s.getField()).order(s.desc ? SortOrder.DESC : SortOrder.ASC));
            }
            return sortBuilders;
        }
        return null;
    }

    public HighlightBuilder genHighlignter() {
        if (highlightProps.size() > 0) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            for (String field : highlightProps) {
                highlightBuilder.field(field);
            }
            return highlightBuilder;
        }
        return null;
    }


    public QueryBuilder genQueryBuilder() {
        if (conds.size() == 0 && betweens.size() == 0 && StringUtils.isBlank(keywords)) {
            return QueryBuilders.matchAllQuery();
        }
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(keywords)) {
            QueryStringQueryBuilder queryStringBuilder = QueryBuilders.queryStringQuery(keywords);
            queryStringBuilder.defaultOperator(Operator.AND);
            queryStringBuilder.fields(defaultSearchFields);
            boolQueryBuilder.must(queryStringBuilder);
        }
        for (Condition cond : conds) {
            if (cond.getValues().size() == 0) {
                continue;
            }
            QueryBuilder queryBuilder;
            if (cond.getValues().size() == 1) {
                if (cond.match) {
                    queryBuilder = QueryBuilders.matchQuery(cond.getName(), cond.getValues().iterator().next()).operator(Operator.AND);
                } else {
                    queryBuilder = QueryBuilders.termQuery(cond.getName(), cond.getValues().iterator().next());
                }
            } else {
                queryBuilder = QueryBuilders.termsQuery(cond.getName(), cond.getValues());
            }
            if (cond.isOr()) {
                boolQueryBuilder.should(queryBuilder);
            } else if (cond.isNot()) {
                boolQueryBuilder.mustNot(queryBuilder);
            } else {
                boolQueryBuilder.must(queryBuilder);
            }
        }
        for (Between between : betweens) {
            if (between.getMin() == null && between.max == null) {
                continue;
            }
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(between.getName());
            if (between.getMax() != null) {
                rangeQueryBuilder.lt(between.getMax());
            }
            if (between.getMin() != null) {
                rangeQueryBuilder.gte(between.getMin());
            }
            boolQueryBuilder.must(rangeQueryBuilder);
        }

        return boolQueryBuilder;
    }


    @Data
    static
    class Sort implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private String field;
        private boolean desc;

        /**
         * @param field
         * @param desc
         */
        public Sort(String field, boolean desc) {
            super();
            this.field = field;
            this.desc = desc;
        }


    }

    @Data
    private static class Between implements Serializable {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
        private String name;
        private Object min;
        private Object max;

        /**
         * @param name
         * @param min
         * @param max
         */
        public Between(String name, Object min, Object max) {
            super();
            this.name = name;
            this.min = min;
            this.max = max;
        }


    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Condition {
        private String name;
        private Collection<?> values;
        private boolean or;
        private boolean not;
        private boolean match;

    }


}
