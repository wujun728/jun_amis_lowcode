package com.jun.plugin.code.generator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 * @author cy
 */
@MapperScan(basePackages = "com.jun.plugin.code.generator.mapper")
@SpringBootApplication
public class CodeGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(CodeGenApplication.class, args);
    }
}