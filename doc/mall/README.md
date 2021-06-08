
### mall 商城系统，包含小程序端和后台CRM管理控制台
### 项目说明
-------------
1. mall商城是以SpringBoot+Shiro+Mybatis plus为核心开发的精简商城后台基础系统。
2. 包含用户管理,角色管理,部门管理,权限管理,菜单管理等系统业务模块，及商城管理系统模块。
3. 使用AdminLTE作为前端UI框架。
4. 第三方Mybatis-plus作为ORM框架。
5. redis、Encache权限缓存。
6. FreeMarker模板,页面拆分,封装公共部分。
7. Druid数据源,数据库监控。
8. 集成简单的oss文件管理模块

### 技术选型
-------------
AdminLTE、Spring MVC、Shiro、Mybatis、Mybatis-Plus、Mysql、Maven

### 快速开始
-------------
1. 创建数据库mall,导入resource/db/adminlte.sql
2. 导入mall后台源码到eclispe
3. 修改相关配置参数，mysql、redis等相关配置,修改workDir文件存储位置
4. 运行 MallApplication 启动类
5. http://localhost:8080,账号/密码:admin/admin123

### 参考项目
-------------
1. https://github.com/almasaeed2010/AdminLTE
2. http://mp.baomidou.com/#/

### 实例截图
-------------
![](https://images.gitee.com/uploads/images/2021/0218/180347_32ebba86_947463.png "2021-02-18_180257.png")

