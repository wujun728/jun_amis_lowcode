package com.jun.biz.common.search;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.Assert;

import com.jun.biz.common.search.query.SearchQuery;
import com.jun.biz.common.search.query.SearchResult;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 
 */
@Slf4j
public abstract class BaseSearcher<T, ID> {

    protected Class<T> entityClass;


    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @SuppressWarnings("unchecked")
    public BaseSearcher() {
        this.entityClass = (Class<T>) ResolvableType.forClass(getClass()).getSuperType().resolveGeneric(0);
        Assert.notNull(entityClass, "自动获取父类泛型失败！");
    }

    public T save(T p) {
        return elasticsearchRestTemplate.save(p);
    }

    public String delete(ID id) {
        return elasticsearchRestTemplate.delete(String.valueOf(id), entityClass);
    }

    public Iterable<T> save(Iterable<T> entities) {
        return elasticsearchRestTemplate.save(entities);
    }

    public T findById(ID id) {
        return elasticsearchRestTemplate.get(String.valueOf(id), entityClass);
    }

    /**
     * 设置默认搜索关键字的字段范围及评分比例
     *
     * @return
     */
    public abstract Map<String, Float> getDefaultSearchFields();


    public SearchResult<T> search(SearchQuery searchQuery) {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (searchQuery.getPageNum() != null && searchQuery.getPageSize() != null) {
            //分页，页码从0开始
            nativeSearchQueryBuilder.withPageable(PageRequest.of(searchQuery.getPageNum() - 1, searchQuery.getPageSize()));
        }
        searchQuery.setDefaultSearchFields(getDefaultSearchFields());
        QueryBuilder queryBuilder = searchQuery.genQueryBuilder();

        nativeSearchQueryBuilder.withQuery(queryBuilder);

        if (searchQuery.getFacetFields() != null) {
            for (String facetField : searchQuery.getFacetFields()) {
                nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(facetField).field(facetField));
            }
        }

        List<SortBuilder<?>> sortBuilders = searchQuery.genSortBuilder();
        if (sortBuilders != null) {
            sortBuilders.forEach(nativeSearchQueryBuilder::withSort);
        } else {
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        HighlightBuilder highlightBuilder = searchQuery.genHighlignter();
        if (highlightBuilder != null) {
            nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);
        }

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        log.info("search by query :{}", nativeSearchQuery.getQuery());

        SearchHits<T> searchHits = elasticsearchRestTemplate.search(nativeSearchQuery, entityClass);

        long totalHits = searchHits.getTotalHits();

        SearchResult<T> searchResult = new SearchResult<>();
        List<T> list = new ArrayList<>();
        for (SearchHit<T> hit : searchHits.getSearchHits()) {
            if (hit != null) {
                T result = hit.getContent();
                list.add(result);
                if (hit.getHighlightFields().size() > 0) {
                    searchResult.getHighlightFields().put(hit.getId(), hit.getHighlightFields());
                }
            }
        }
        if (searchHits.getAggregations() != null) {
            Map<String, Aggregation> aggregationMap = searchHits.getAggregations().asMap();
            Map<String, Map<Number, Long>> map = new HashMap<>(aggregationMap.size());
            for (Map.Entry<String, Aggregation> entry : aggregationMap.entrySet()) {
                Map<Number, Long> hash = ((Terms) entry.getValue()).getBuckets().stream().collect(Collectors.toMap(Terms.Bucket::getKeyAsNumber, Terms.Bucket::getDocCount));
                map.put(entry.getKey(), hash);
            }
            searchResult.setFacets(map);
        }
        searchResult.setList(list);
        searchResult.setTotal(totalHits);
        return searchResult;

    }
}
