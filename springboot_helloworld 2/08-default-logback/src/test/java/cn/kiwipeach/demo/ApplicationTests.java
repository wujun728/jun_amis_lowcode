package cn.kiwipeach.demo;

import cn.kiwipeach.demo.service.LogbackService;
import cn.kiwipeach.demo.utils.LogLevelUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

    /**
     * 测试1：springboot内置logback实现框架
     */
    @Test
    public void contextLoads() {
        //springboot默认输出是info级别
        logger.trace("Junit trace 日志");
        logger.debug("Junit debug 日志");
        logger.info("Junit info 日志");
        logger.warn("Junit warn 日志");
        logger.error("Junit error 日志");
    }

    @Autowired
    private LogbackService logbackService;
    @Autowired
    private LogLevelUtil logLevelUtil;

    /**
     * 测试2：测试多环境中，指定包体中的日志输出级别
     */
    @Test
    public void testPackageLogLevel() {
        //1）容器默认所有输出warn级别,这里以cn.kiwipeach.demo.utils.LogLevelUtil为例
        logLevelUtil.showLogLevel();
        //2）指定的特定包下输出trace级别的日志,指定位置请见logback-spring.xml配置
        logbackService.showLogLevel();
    }

}
