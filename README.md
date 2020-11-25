### `jun_springboot` 项目

#### 项目说明
基于SpringBoot家族的360度整合，基本覆盖开发用九成以上的场景，即开即用，主要是详尽的了解项目里面的每个stater跟lib组件及功能，为个人及项目实际开发提供助力，帮组项目快速开发，本项目是maven项目，每个组件均可以独立运行且不依赖第三方框架，具体见项目明细。

该项目已成功集成 actuator(`监控`)、admin(`可视化监控`)、logback(`日志`)、

aopLog(`通过AOP记录web请求日志`)、统一异常处理(`json级别和页面级别`)、
freemarker(`模板引擎`)、thymeleaf(`模板引擎`)、Beetl(`模板引擎`)、
JdbcTemplate(`通用JDBC操作数据库`)、JPA(`强大的ORM框架`)、mybatis(`强大的ORM框架`)、通用Mapper(`快速操作Mybatis`)、PageHelper(`通用的Mybatis分页插件`)、mybatis-plus(`快速操作Mybatis`)、BeetlSQL(`强大的ORM框架`)、upload(`本地文件上传和七牛云文件上传`)、redis(`缓存`)、ehcache(`缓存`)、
email(`发送各种类型邮件`)、task(`基础定时任务`)、quartz(`动态管理定时任务`)、xxl-job(`分布式定时任务`)、swagger(`API接口管理测试`)、security(`基于RBAC的动态权限认证`)、
SpringSession(`Session共享`)、Zookeeper(`结合AOP实现分布式锁`)、RabbitMQ(`消息队列`)、
Kafka(`消息队列`)、websocket(`服务端推送监控服务器运行信息`)、socket.io(`聊天室`)、
ureport2(`中国式报表`)、打包成`war`文件、集成 ElasticSearch(`基本操作和高级查询`)、
Async(`异步任务`)、集成Dubbo(`采用官方的starter`)、MongoDB(`文档数据库`)、neo4j(`图数据库`)、
docker(`容器化`)、`JPA多数据源`、`Mybatis多数据源`、`代码生成器`、GrayLog(`日志收集`)、
JustAuth(`第三方登录`)、LDAP(`增删改查`)、`动态添加/切换数据源`、单机限流(`AOP + Guava RateLimiter`)、
分布式限流(`AOP + Redis + Lua`)、ElasticSearch(`使用官方 Rest High Level Client`)、
HTTPS、Flyway(`数据库初始化`)。



#### jun_springboot 项目module组件功能列表
- 【springboot_helloworld】springboot 的一个 helloworld  
- 【springboot_properties】springboot 读取配置文件中的内容  
- 【springboot_actuator】springboot 集成 springboot_starter_actuator 用于监控 springboot 的启动和运行状态  
- 【springboot_admin_client】springboot 集成 springboot_admin 来可视化的监控 springboot 程序的运行状态，可以与 actuator 互相搭配使用，客户端示例  
- 【springboot_admin_server】springboot 集成 springboot_admin 来可视化的监控 springboot 程序的运行状态，可以与 actuator 互相搭配使用，服务端示例  
- 【springboot_logback】springboot 集成 logback 日志  
- 【springboot_log_aop】springboot 使用 AOP 切面的方式记录 web 请求日志  
- 【springboot_exception_handler】springboot 统一异常处理，包括2种，第一种返回统一的 json 格式，第二种统一跳转到异常页面  
- 【springboot_template_freemarker】springboot 集成 Freemarker 模板引擎  
- 【springboot_template_thymeleaf】springboot 集成 Thymeleaf 模板引擎  
- 【springboot_template_beetl】springboot 集成 Beetl 模板引擎  
- 【springboot_template_enjoy】springboot 集成 Enjoy 模板引擎  
- 【springboot_orm_jdbctemplate】springboot 集成 Jdbc Template 操作数据库，并简易封装通用 Dao 层  
- 【springboot_orm_jpa】springboot 集成 springboot_starter_data_jpa 操作数据库  
- 【springboot_orm_mybatis】springboot 集成原生mybatis，使用 - mybatis_springboot_starter集成  
- 【springboot_orm_mybatis_mapper_page】springboot 集成- 通用Mapper和PageHelper，使用mapper_springboot_starter 和pagehelper_springboot_starter集成  
- 【springboot_orm_mybatis_plus】springboot 集成mybatis_plus，使用mybatis_plus_boot_starter集成，集成 BaseMapper、BaseService、ActiveRecord 操作数据库  
- 【springboot_orm_beetlsql】springboot 集成beetl_sql，使用beetl_framework_starter集成  
- 【springboot_upload】springboot 文件上传示例，包含本地文件上传以及七牛云文件上传  
- 【springboot_cache_redis】springboot 整合 redis，操作redis中的数据，并使用redis缓存数据  
- 【springboot_cache_ehcache】springboot 整合 ehcache，使用 ehcache 缓存数据  
- 【springboot_email】springboot 整合 email，包括发送简单文本邮件、HTML邮件（包括模板HTML邮件）、附件邮件、静态资源邮件  
- 【springboot_task】springboot 快速实现定时任务  
- 【springboot_task_quartz】springboot 整合 quartz，并实现对定时任务的管理，包括新增定时任务，删除定时任务，暂停定时任务，恢复定时任务，修改定时任务启动时间，以及定时任务列表查询，`提供前端页面`  
- 【springboot_task_xxl_job】springboot 整合xxl_job，并提供绕过 `xxl_job_admin` 对定时任务的管理的方法，包括定时任务列表，触发器列表，新增定时任务，删除定时任务，停止定时任务，启动定时任务，修改定时任务，手动触发定时任务  
- 【springboot_swagger】springboot 集成原生的 `swagger` 用于统一管理、测试 API 接口  
- 【springboot_swagger_beauty】springboot 集成第三方 `swagger` swagger_bootstrap_ui美化API文档样式，用于统一管理、测试 API 接口  
- 【springboot_rbac_security】springboot 集成 spring security 完成基于RBAC权限模型的权限管理，支持自定义过滤请求，动态权限认证，使用 JWT 安全认证，支持在线人数统计，手动踢出用户等操作  
- 【springboot_rbac_shiro】springboot 集成 shiro 实现权限管理   
- 【springboot_session】springboot 集成 Spring Session 实现Session共享、重启程序Session不失效  
- 【springboot_oauth】springboot 实现 oauth 服务器功能，实现授权码机制   
- 【springboot_social】springboot 集成第三方登录，集成 `justauth_springboot_starter` 实现QQ登录、GitHub登录、微信登录、谷歌登录、微软登录、小米登录、企业微信登录。  
- 【springboot_zookeeper】springboot 集成 Zookeeper 结合AOP实现分布式锁  
- 【springboot_mq_rabbitmq】springboot 集成 RabbitMQ 实现基于直接队列模式、分列模式、主题模式、延迟队列的消息发送和接收  
- 【springboot_mq_rocketmq】springboot 集成 RocketMQ，实现消息的发送和接收   
- 【springboot_mq_kafka】springboot 集成 kafka，实现消息的发送和接收  
- 【springboot_websocket】springboot 集成 websocket，后端主动推送前端服务器运行信息  
- 【springboot_websocket_socketio】springboot 使用 netty_socketio 集成 websocket，实现一个简单的聊天室  
- 【springboot_ureport2】springboot 集成 ureport2 实现复杂的自定义的中国式报表   
- 【springboot_uflo】springboot 集成 uflo 快速实现轻量级流程引擎   
- 【springboot_urule】springboot 集成 urule 快速实现规则引擎   
- 【springboot_activiti】springboot 集成 activiti 7  流程引擎   
- 【springboot_async】springboot 使用原生提供的异步任务支持，实现异步执行任务  
- 【springboot_war】springboot 打成 war 包的配置  
- 【springboot_elasticsearch】springboot 集成 ElasticSearch，集成 `springboot_starter_data_elasticsearch` 完成对 ElasticSearch 的高级使用技巧，包括创建索引、配置映射、删除索引、增删改查基本操作、复杂查询、高级查询、聚合查询等  
- 【springboot_dubbo】springboot 集成 Dubbo，分别为公共模块 `springboot_dubbo_common`、服务提供方`springboot_dubbo_provider`、服务调用方`springboot_dubbo_consumer`  
- 【springboot_mongodb】springboot 集成 MongoDB，使用官方的 starter 实现增删改查  
- 【springboot_neo4j】springboot 集成 Neo4j 图数据库，实现一个校园人物关系网的demo  
- 【springboot_docker】springboot 容器化  
- 【springboot_multi_datasource_jpa】springboot 使用JPA集成多数据源  
- 【springboot_multi_datasource_mybatis】springboot 使用Mybatis集成多数据源，使用 Mybatis_Plus 提供的开源解决方案实现  
- 【springboot_sharding_jdbc】springboot 使用 `sharding_jdbc` 实现分库分表，同时ORM采用 Mybatis_Plus  
- 【springboot_tio】springboot 集成 tio 网络编程框架   
- 【springboot_grpc】springboot 集成grpc，配置tls/ssl
- 【springboot_codegen】springboot 集成 velocity 模板技术实现的代码生成器，简化开发  
- 【springboot_graylog】springboot 集成 graylog 实现日志统一收集  springboot_sso  springboot 集成 SSO 单点登录
- 【springboot_ldap】springboot 集成 LADP，集成 `springboot_starter_data_ldap` 完成对 Ldap 的基本 CURD操作, 并给出以登录为实战的 API 示例
- 【springboot_dynamic_datasource】springboot 动态添加数据源、动态切换数据源  
- 【springboot_ratelimit_guava】springboot 使用 Guava RateLimiter 实现单机版限流，保护 API  
- 【springboot_ratelimit_redis】springboot 使用 Redis + Lua 脚本实现分布式限流，保护 API  
- 【springboot_https】springboot 集成 HTTPS  
- 【springboot_elasticsearch_rest_high_level_client】spring boot 集成 ElasticSearch 版本，使用官方 Rest High Level Client 操作 ES 数据  
- 【springboot_flyway】spring boot 集成 Flyway，项目启动时初始化数据库表结构，同时支持数据库脚本版本控制

#### 附录-官方提供的 starter 介绍

| 名称                                   | 描述                                                        |
| :------------------------------------- | :---------------------------------------------------------- |
| spring-boot-starter                    | Spring Boot 核心包，包括自动装配，日志，以及YAML文件解析    |
| spring-boot-starter-actuator           | 帮助在生产环境下监控和管理 Spring Boot 应用                 |
| spring-boot-starter-amqp               | Spring Boot 快速集成 RabbitMQ                               |
| spring-boot-starter-aop                | 提供切面编程特性，包含 spring-aop 和 AspectJ 依赖           |
| spring-boot-starter-batch              | 快速集成 Spring Batch 批处理框架，包括操作  HSQLDB 数据库   |
| spring-boot-starter-cache              | Support for Spring’s Cache abstraction.                     |
| spring-boot-starter-data-elasticsearch | Spring Boot 快速集成 ElasticSearch 查询分析引擎             |
| spring-boot-starter-data-jpa           | Spring Boot 快速集成 JPA 操作数据库                         |
| spring-boot-starter-data-mongodb       | Spring Boot 快速集成 MongoDB 非关系型数据库                 |
| spring-boot-starter-data-rest          | Spring Boot 暴露数据库查询端点为 REST 服务                  |
| spring-boot-starter-data-solr          | Spring Boot 快速集成 Solr 实现全文索引                      |
| spring-boot-starter-freemarker         | 提供 FreeMarker 模板引擎                                    |
| spring-boot-starter-groovy-templates   | 提供 Groovy 模板引擎                                        |
| spring-boot-starter-integration        | 提供通用的集成 spring-integration 模块                      |
| spring-boot-starter-jdbc               | 快速集成 JDBC 操作数据库                                    |
| spring-boot-starter-jersey             | 提供 Jersey 提供  RESTful 服务                              |
| spring-boot-starter-jta-atomikos       | 集成 JTA Atomikos 实现分布式事务                            |
| spring-boot-starter-jta-bitronix       | 集成 JTA Bitronix 实现分布式事务                            |
| spring-boot-starter-mail               | 快速邮件集成                                                |
| spring-boot-starter-mustache           | 提供 Mustache 模板引擎                                      |
| spring-boot-starter-redis              | Spring Boot 快速集成 Redis                                  |
| spring-boot-starter-security           | Support for spring-security.                                |
| spring-boot-starter-social-facebook    | Support for spring-social-facebook.                         |
| spring-boot-starter-social-linkedin    | Support for spring-social-linkedin.                         |
| spring-boot-starter-social-twitter     | Support for spring-social-twitter.                          |
| spring-boot-starter-test               | 提供通用单元测试依赖，包括 JUnit, Hamcrest , Mockito        |
| spring-boot-starter-thymeleaf          | 提供 Thymeleaf 模板引擎，包括 Thymeleaf 的自动装配等        |
| spring-boot-starter-velocity           | 提供 Velocity 模板引擎                                      |
| spring-boot-starter-web                | 提供全栈的 web 开发特性，包括 Spring MVC 依赖和 Tomcat 容器 |
| spring-boot-starter-websocket          | Spring Boot 集成 WebSocket 功能                             |
| spring-boot-starter-ws                 | Spring Boot 集成 WebService 功能                            |

#### 开发环境

- **JDK 1.8 +**
- **Maven 3.5 +**
- **IDEA 2018.2 + or  STS 4.5 +** (*注意：务必使用 IDEA 开发，同时保证安装 `lombok` 插件*)
- **Mysql 5.7 +** (*尽量保证使用 5.7 版本以上，因为 5.7 版本加了一些新特性，同时不向下兼容。本 demo 里会尽量避免这种不兼容的地方，但还是建议尽量保证 5.7 版本以上*)

#### 运行方式

> 提示：如果是 fork 的朋友，同步代码的请参考：
1. `git clone https://github.com/wujun728/jun_xxx.git`
2. 使用 IDEA 打开 clone 下来的项目
3. 在 IDEA 中 Maven Projects 的面板导入项目根目录下 的 `pom.xml` 文件
4. Maven Projects 找不到的童鞋，可以勾上 IDEA 顶部工具栏的 View -> Tool Buttons ，然后 Maven Projects 的面板就会出现在 IDEA 的右侧
5. 找到各个 Module 的 Application 类就可以运行各个 demo 了
6. 注意：每个 demo 均有详细的 README 配套，用 demo 前记得先看看
7. 注意：运行各个 demo 之前，有些是需要事先初始化数据库数据的

#### 已办&待办列表
- [ ] spring_boot_demo_urule（集成  urule 实现规则引擎）
- [ ] spring_boot_demo_activiti（集成 Activiti 实现流程控制引擎）
- [x] ~~spring_boot_demo_async（Spring boot 实现异步调用）~~

