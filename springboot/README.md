#Spring Boot + MyBatis + MySql + view(jsp)

#spring boot + mybatis + mysql + view(jsp) 的基础例子，用于初学者学习spring boot

官方文档：http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-jsp-limitations

要完成简单的入门，只需要5步：

##1.maven pom.xml 

    释：spring-boot-starter-web（web mvc）、spring-boot-starter-test（开发测试包）、tomcat-embed-jasper（jsp解析引擎）、mybatis-spring-boot-starter（spring boot+mybatis结合版）、mysql-connector-java（mysql驱动）

##2.entity、dao、service、web

    释：实体、持久、业务、控制器就不做详细的描述了。

##3.mappings.xml

    释：mybatis对应的xml文件

##4.application.properties的关键属性配置，如下图

![输入图片说明](http://git.oschina.net/uploads/images/2016/0929/180549_0ef13002_629055.png "application.properties")

##5.junitTest

    释：@RunWith(SpringJUnit4ClassRunner.class)  （让测试运行在spring环境）
            @ContextConfiguration(classes = App.class,initializers = ConfigFileApplicationContextInitializer.class)（初始化一个spring的应用程序）


其余任何东西都不需要配置，只需要下载下来导入IDE就可以运行；

# 注：maven仓库需要另外配置