
### jun_boot 通用信息管理系统-权限管理脚手架

### 项目说明
-------------
1. jun_boot是以Springboot+Shiro+Mybatis为核心，以shiro、redis、redis为辅开发的精简后台基础系统。
2. 包含用户管理,角色管理,菜单管理,定时任务,文件管理,图标工具等常用业务模块。
3. 使用AdminLTE作为前端UI框架，添加菜单Tab页签完成内嵌iframe多页面功能。
4. 使用Mybatis集成通用Mapper作为ORM框架。
5. 使用redis、Ehcache实现权限缓存。
6. 使用thymeleaf模板,实现页面拆分,封装公共部分。
7. 使用Druid数据源,数据库监控。
8. 添加文件管理，整合阿里云、腾讯云、七牛云实现文件oss存储。

### 技术选型
-------------
AdminLTE、Springboot、Shiro、Mybatis、通用Mapper、shiro-redis、Ehcache、redis、Mysql、Maven

### 快速开始
-------------
1. 根据application-dev.yml内配置,设置mysql相关配置（sql文件在resource/sql目录下）
2. 根据application-dev.yml内配置,设置redis相关配置 (安装好redis并启动redis服务)
3. 项目导入eclipse或idea
4. 运行Application.java启动类
5. 浏览器输入http://localhost:8081,账号/密码:admin/1

### 参考项目
-------------
1. https://github.com/almasaeed2010/AdminLTE
2. https://github.com/abel533/Mapper
3. https://github.com/alexxiyang/shiro-redis
4. https://gitee.com/y_project/RuoYi
5. https://gitee.com/renrenio/renren-security

### 实例截图
-------------
