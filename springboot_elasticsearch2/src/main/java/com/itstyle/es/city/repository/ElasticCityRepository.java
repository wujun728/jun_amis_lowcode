package com.itstyle.es.city.repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.itstyle.es.city.entity.City;
public interface  ElasticCityRepository extends ElasticsearchRepository<City,Long> {

}
