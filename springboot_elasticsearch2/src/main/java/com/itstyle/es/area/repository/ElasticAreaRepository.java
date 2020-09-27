package com.itstyle.es.area.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.itstyle.es.area.entity.Area;
public interface  ElasticAreaRepository extends ElasticsearchRepository<Area,Integer	> {

}
