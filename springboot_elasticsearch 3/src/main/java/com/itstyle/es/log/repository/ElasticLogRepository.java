package com.itstyle.es.log.repository;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import com.itstyle.es.log.entity.SysLogs;
public interface  ElasticLogRepository extends ElasticsearchRepository<SysLogs,Long> {
	 //查询
	 public List<SysLogs> findByUsername(String username);
	 public List<SysLogs> findByDeviceType(Short deviceType);
	 //计数
	 public Long countByUsername(String username);
	 //删除
	 Long deleteByUsername(String username);
	 //异步查询
	 @Async
	 Future<SysLogs> findOneByUsername(String Username);        
	 @Async
	 ListenableFuture<SysLogs> findOneByDeviceType(Short deviceType);
}
