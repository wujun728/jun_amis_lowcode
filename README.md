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




mybatisplus-spring-boot    干掉
redis-project    干掉，合并到springboot_redis
springboot     *****
springboot_2.0_learning   干掉
springboot_2.0_oauth2   挪走到 springboot_oauth2
springboot_223.0_learning  干掉
springboot_activiti   调整
springboot_activiti2   干掉
springboot_actuator 
springboot_admin 
springboot_admin 2   挪走，到springcloud
springboot_admin 3   挪走到jedp_boot
springboot_adminui   干掉
springboot_all   合并到springboot
springboot_api_boot2      合并到springboot
springboot_api_project   干掉
springboot_api_project_seed   干掉
springboot_api_project23   干掉
springboot_async   调整
springboot_batch  调整
springboot_bboot    干掉
springboot_blogtemplatewithbootstrap   挪走到jun_frontend
springboot_bootshiro    调整
springboot_bootstrapcomment      挪走到jun_frontend
springboot_bootutil   调整  springboot_util 
springboot_bucket  调整
springboot_bucket23   干掉
springboot_cache    调整，新增
springboot_cache_ehcache   调整
springboot_cache_redis    调整
springboot_camel   干掉
springboot_cloud   放到springcloud里面
springboot_cloud 2   干掉
springboot_codegen   调整
springboot_codegenerator   调整
springboot_codegenerator555   干掉
springboot_common_boot_email   调整
springboot_cronboot   干掉，挪走到springboot_quartz
springboot_crud_demo   调整，去掉
springboot_data_jpa   调整
springboot_data_mybatis   调整
springboot_demo   不动
springboot_demo 4   挪走到demo里面
springboot_demo_starter   干掉
springboot_demo2  合并到demo里面
springboot_demo2 2   合并到demo里面
springboot_demo11   挪走到demo里面
springboot_demo21  干掉
springboot_demo22  干掉
springboot_demo23  干掉
springboot_demo223  干掉
springboot_diboot_v2   干掉
springboot_distributed_seckill       调整
springboot_docker   调整
springboot_drools_springboot     调整到springboot_drools
springboot_drools7_demo  合并到springboot_drools
springboot_dubbo  调整
springboot_dubbo_consumer   干掉
springboot_dubbo_consumer23  干掉
springboot_dubbo_provider  干掉
springboot_dubbo_provider23  干掉
springboot_dubbox  调整
springboot_dubbox_web   干掉
springboot_dynamic_datasource  调整
springboot_dynamic_datasource_spring_boot_starter   干掉、合并
springboot_dynamic_datasource_spring_boot_starter2  干掉、删掉
springboot_dynamicds_spring_boot   干掉、合并
springboot_elastic_job   调整下
springboot_elasticsearch  调整
springboot_elasticsearch 2  干掉、合并
springboot_elasticsearch 3  干掉、合并
springboot_elasticsearch_rest_high_level_client   干掉、合并
springboot_elasticsearch2   干掉、合并
springboot_elasticsearch3  干掉
springboot_email  调整
springboot_eshelloword_booter   干掉
springboot_eshelloword_spring_boot_starter  干掉
springboot_example_ui     合并到springboot_demo
springboot_examples  合并到springboot_demo
springboot_examples22    合并到springboot_demo
springboot_exception_handler  调整
springboot_fastdfs       调整
springboot_fastdfs 2   干掉
springboot_fastdfs21  干掉
springboot_filemanager   调整
springboot_filemanager2   干掉
springboot_filemanager13  干掉
springboot_filemanager23  干掉
springboot_flyway   调整
springboot_freemarker   调整
springboot_graylog       调整
springboot_helloworld  调整
springboot_helloworld 2   干掉
springboot_helloworld 3  干掉
springboot_https   调整
springboot_integration   迁移到springboot_demo里面
springboot_jackson_datetime_example   调整 jackson
springboot_java_springboot_sell  调整
springboot_jboot   干掉
springboot_jbpm6   调整
springboot_jediscluster   调整
springboot_jfinal_bootstrap_table     调整
springboot_jms   调整

a56bcd7ae5fbc0d5a26007f5334725798a3d7da3bd0c566e970c49066a216a841792da1657d135adea91dbe8f71fbd1b398f23c57b95423ccf9391d47f6f13c73fded8f84dfd0b77ce0f619927dc17999f93929ec5a8d93af8d566e2dd3787f050e64f693725aaf286c096683f9c66ffed7d2f827960766a1d151896d8a016498cadcdca7724d053780dd94c6ea4fa80b13dd1f65e193bad79101eacff164dd6396ee02b37b0f68a38f7384a0842913935caf493c60a0f73bacb611a492c795d4f5aabc36044d6033c4f297919fef65a80329c71e8575c9d68a13c4f376957fb8ddef41653a2a745835522fb2dbbe7ed2d9b7875167d187907440adca23185b14c174ab04fff06055c306622018afa58783ac066aacd647df32d7472f6a1d013a7c02e7fb0f0f4affdbc8de73428cd8b62e53b3983efb7dda5f4fbe8656133ae21afc904255d97875b25ae50e8581c55a5350160783c87b41c7472401a5ea0d6be28a924dac1523020b2ff1929bd25b9735542e6c7d6260a2258567138e00975aeb32a00c0c2f2c51cbc4ffd0d8a778ff718ba898f7ea883309f71d9611673f2f90110a739b7346b7fa7725104ecbf2de3c08c5df5e31054ab3ef60ac7b39f6f9061f22373b85879aaca7265a2e582b2cdda561d1bce5db10bb09321828df315b45d95b9818d19169195580b7df768ab63d3f2428fd73d5e1038d01e0de5ca762ed0792807ea874021b57514b9f8f4a14b8e2986e7b9a1f2423bb1b4e2591e63760cd11225842679539a1ff47c5431163c1ee3428af54135932f0e0c1fb314ebbafa271b18a667d1dcca8bb7a1ee35a1c193e86ad97bfd66633447eb32d64df0b890df55da903284c44890a0d4547b8cbd613caaadf64b756bbcdc5b6efdfc0e4ea25e2e8d16e8e899ac0d1605b7e0aedb03b7243d09dd276fa8297a323d91343340e9b2a8c5948fe1da41b0f4c26d9edc2fae250665bfc646d299e9763f574d24b85265e506735a97c312c2baa55373c0b1aadf64b36d36d4050f36e33664c1e55c965ed22a52df8b0f876f665f751f6f4895926ee67e48bdf35e7fede5246afd60d9a5306aafa7535e12e878c2b781bf7fa96d5705bcec09878a8a2a77c638985c4e5fe29ea47940ddb9f72c7d350d6370f59733f1c70c3103fec787eb01b06d14b34452dc98255cf4bc894e1bc628ebc5f238e33c0ce34e78cefcaa8cd5fe9e3bf691d1210a7d940b725e7a1de1bc7ba99ce29dd84994641fb1020e705f6ff16a2b29be28d70f53b0769bff5677710da0d43aaf87d86caa14822171407ffef0adb8431087af2ffe2a7e86c6379f0c78d82b7edd253f7013a136ccff8097888a3e1e1d535e19bf4f236ed7f6285192ecadab999ab82ebfe67ca184fb5ce2648e58a8ef65ae10b59f93bae5aa25a1eed4e4b46b6b250818f5670704a2c4925b5156f21bebfbd7a4f776fcf89d6b8d1238a0f424508c2e22bc96f2181e9a871effe6f612436b1606e3bd02e30c1ba9b029f8651cf1fc203b238a17c81b40cbb678cdfac751950452dea97d29d46863dae14f66761063820b415d7d1e534f4898c508bbd899b7639dc1af377532f1c96504cf26d8781a8a3247c795abeae039413e72b6cecb247d9cc6731c949d8e10f61a82d50f4ec240bbd4d6f45d16ddf40cc5b5fab7d501dc143d3a8e38255185f911e173d8a323dd2af33e06e1c3cfa07a71ce00011f9bc213300e2b52d23f82b69bbd4dd4feb231271f2f29f072d22f628fb7453c8587ee525b109fc9423ab04d1e6bcb53db4063a258456a7ac4146ed7b615c2a7845283227323b114f85378dbe991fc5cede2aab7d0d975180b6ac9c64dee79a6430685a44cfefee78467a596d1a41e962c6a06b62302ccdf7477a578439c8d4c4c66d22ed8656f18a94bcbfce9fec848fa62261cbcf808957f1acac6df042ec686a69df6916715d5b1484abaa1901fe5a90af9f47c0c5d50f28c151fa272b82fc0baf9ce90505267b3043fa5d1115fb3d800a1acf148b4dd72ab9f5bb369eba24cc75596029e8ebfe3a737dcb129a35675edaf70dbb5bb15e4fd644a9c4ab6be238a416c9b05c5e898dd270b14776cf6d7b2db725a2c5a07a26e91c5cb0b5456c5b4711ae84b56e96b02bd33cf97948ec4491c9b7b31e6c8c297a7903ac618a5a5114de394290e9bd24789f247fc8a2e0ea79182a9fe9bdc515278864c2c8b105e6790dbd05c3dbfd59231a5c2d26533a87e7fe86f04564d857384ad880781910a07f7c4311ce29e8335ed602e13e6cacf269f8277ef6081d1924252082cfb506f703cf7a794175dd9625d75fd4fa3eea10440fc077f443016f6156f235e8767350b4453dd2ac6114cdb37f84f9e428da043adb4d0fdfcaa7230347ade13bca6f6a5ef01671f681f54eebc6676398ce21f8665f603fe4273808e988ea61475fe97f8a0fcbe35c48c27cfda743f59699f0d8145fc3ade5b4b2fda2c8a
