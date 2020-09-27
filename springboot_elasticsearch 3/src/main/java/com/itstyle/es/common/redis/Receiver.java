package com.itstyle.es.common.redis;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itstyle.es.log.entity.SysLogs;
import com.itstyle.es.log.repository.ElasticLogRepository;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
    @Autowired
	private  ElasticLogRepository elasticLogRepository;
    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("接收log消息 <{}>",message);
        if(message == null){
            LOGGER.info("接收log消息 <" + null + ">");
        }else {
        	ObjectMapper mapper = new ObjectMapper();  
			try {
				SysLogs log = mapper.readValue(message, SysLogs.class);
				elasticLogRepository.save(log);
				LOGGER.info("接收log消息内容 <{}>",log.getOperation());
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        latch.countDown();
    }
}
