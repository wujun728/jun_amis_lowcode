package com.hope;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hope.mapper")
@SpringBootApplication
public class AdminApplication {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        LOGGER.info("[填写项目名称]admin服务启动成功！温馨提示：代码千万行，注释第一行，命名不规范，同事泪两行");
    }
}
