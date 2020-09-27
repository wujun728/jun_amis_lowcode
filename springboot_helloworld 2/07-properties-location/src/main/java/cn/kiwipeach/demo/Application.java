package cn.kiwipeach.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    /**
     * 1.使用命令行参数形式:java -jar *.jar --spring.config.location=G:\GiteeVSGitHub\2018year\SpringBoot-HelloWorld\files\application.properties
     * 2.执行顺序；
     * G:\GITEEVSGITHUB\2018YEAR\SPRINGBOOT-HELLOWORLD\07-PROPERTIES-LOCATION\TARGET\JARS
     * │  07-properties-location-0.0.1-SNAPSHOT.jar
     * │  application.properties
     * │
     * └─config
     *         application.properties
     * 先加载外部:config/application.properties
     * 后加载外部：application.properties
     * 然后加载jar内部:config/application.properties
     * 最后加载jar内部:application.properties
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
