package com.itstyle.es.log.controller;
import java.sql.Timestamp;
import java.util.Date;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itstyle.es.common.utils.JsonMapper;
import com.itstyle.es.log.entity.Pages;
import com.itstyle.es.log.entity.SysLogs;
import com.itstyle.es.log.service.LogService;
@Controller
@RequestMapping(value = "")
public class LogController {
   
   @Autowired
   private  ElasticsearchTemplate elasticSearchTemplate;
   @Reference
   private LogService logService;
   @Autowired
   private RedisTemplate<String, String> redisTemplate;
   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;
   
   
   @GetMapping(value="index")
   public String  index() {
		 return "log/index";
   }
   @PostMapping(value="list")
   public @ResponseBody Pages<SysLogs>  list(Integer pageNumber,Integer pageSize,
		 Integer platFrom,String searchContent) {
	  return logService.searchLogPage(pageNumber, pageSize, platFrom, searchContent);
   }
   /**
    * redis 日志队列测试接口
    */
   @GetMapping(value="redisLog")
   public @ResponseBody String redisLog() {
	    SysLogs log = new SysLogs();
		log.setUsername("小马云");
		log.setOperation("开源中国社区");
		log.setMethod("com.itstyle.es.log.controller.index()");
		log.setIp("192.168.1.70");
		log.setGmtCreate(new Timestamp(new Date().getTime()));
		log.setExceptionDetail("开源中国社区");
		log.setParams("{'name':'码云','type':'开源'}");
		log.setDeviceType((short)1);
		log.setPlatFrom((short)1);
		log.setLogType((short)1);
		log.setDeviceType((short)1);
		log.setId((long)0);
		log.setUserId((long)1);
		log.setTime((long)1);
		//模拟日志队列实现
		redisTemplate.convertAndSend("itstyle_log",log);
		return "success";
   }
   /**
    * kafka 日志队列测试接口
    */
   @GetMapping(value="kafkaLog")
   public @ResponseBody String kafkaLog() {
	    SysLogs log = new SysLogs();
		log.setUsername("红薯");
		log.setOperation("开源中国社区");
		log.setMethod("com.itstyle.es.log.controller.kafkaLog()");
		log.setIp("192.168.1.80");
		log.setGmtCreate(new Timestamp(new Date().getTime()));
		log.setExceptionDetail("开源中国社区");
		log.setParams("{'name':'码云','type':'开源'}");
		log.setDeviceType((short)1);
		log.setPlatFrom((short)1);
		log.setLogType((short)1);
		log.setDeviceType((short)1);
		log.setId((long)200000);
		log.setUserId((long)1);
		log.setTime((long)1);
		//模拟日志队列实现
		String json = JsonMapper.toJsonString(log);
        kafkaTemplate.send("itstyle", "itstyle_log",json);
		return "success";
   }
}
