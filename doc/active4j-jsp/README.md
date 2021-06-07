


![active4j](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/logo.png "active4j")


Active4j-jsp是基于SpingBoot2.0轻量级的java快速开发框架。以Spring Framework为核心容器，Spring MVC为模型视图控制器，Mybatis Plus为数据访问层， Apache Shiro为权限授权层, Redis为分布式缓存，Quartz为分布式集群调度，JSP作为前端页面引擎，采用JSTL标签库封装组件的开源框架。



------------

# 项目介绍
 - Active4j-jsp是基于SpingBoot2.0轻量级的java快速开发框架。以Spring Framework为核心容器，Spring MVC为模型视图控制器，Mybatis Plus为数据访问层， Apache Shiro为权限授权层, Redis为分布式缓存，Quartz为分布式集群调度，JSP作为前端页面引擎，采用JSTL标签库封装组件的开源框架。
 - Active4j-jsp目前内置了部门管理、用户管理、角色管理、菜单管理、数据数据字典等基础功能，并内置了图片上传等扩展功能。
 - Active4j-jsp定位于企业快速开发平台建设，代码全部开源，持续更新，共同维护。Active4j可以应用在任何J2EE的项目开发中，尤其适合企业信息管理系统（MIS），企业办公系统（OA），客户关系管理系统（CRM），内容管理系统（CMS）等。

# 技术文档
- 讨论加群：qq群①：203802692   qq群②：773872959
- 演示地址：[http://www.active4j.com:9002/jsp](http://www.active4j.com:9002/jsp "http://www.active4j.com:9002/jsp")
- 官方网站：[www.active4j.com](http://www.active4j.com "www.active4j.com")
- 文档地址：[http://www.active4j.com/doc.html](http://www.active4j.com/doc.html "http://www.active4j.com/doc.html")

# 生态系统
|  版本 |  地址 |
| ------------ | ------------ |
|  前后端分离版本github |  [https://github.com/yunchaoyun/active4j](https://github.com/yunchaoyun/active4j "https://github.com/yunchaoyun/active4j") |
| 前后端分离版本gitee  |  [https://gitee.com/active4j/active4j](https://gitee.com/active4j/active4j "https://gitee.com/active4j/active4j") |
|  boot单体版本github | [https://github.com/yunchaoyun/active4j-boot](https://github.com/yunchaoyun/active4j-boot "https://github.com/yunchaoyun/active4j-boot")  |
| boot单体版本gitee  | [https://gitee.com/active4j/active4j-boot](https://gitee.com/active4j/active4j-boot "https://gitee.com/active4j/active4j-boot") |
|jsp版本github|[https://github.com/yunchaoyun/active4j-jsp](https://github.com/yunchaoyun/active4j-jsp "https://github.com/yunchaoyun/active4j-jsp")|
|jsp版本gitee|[https://gitee.com/active4j/active4j-jsp](https://gitee.com/active4j/active4j-jsp "https://gitee.com/active4j/active4j-jsp")|
| 工作流版本github | [https://github.com/yunchaoyun/active4j-flow](https://github.com/yunchaoyun/active4j-flow "https://github.com/yunchaoyun/active4j-flow") |
| 工作流版本gitee | [https://gitee.com/active4j/active4j-flow](https://gitee.com/active4j/active4j-flow "https://gitee.com/active4j/active4j-flow")|
| OA办公系统github|[https://github.com/yunchaoyun/active4j-oa](https://github.com/yunchaoyun/active4j-oa "https://github.com/yunchaoyun/active4j-oa")|
|OA办公系统gitee|[https://gitee.com/active4j/active4j-oa](https://gitee.com/active4j/active4j-oa "https://gitee.com/active4j/active4j-oa")|

# 项目特点
- 开箱即用，节省开发时间，提高开发效率
- 代码全部开源，持续更新，共同维护
- 基于SpringBoot，简化了大量项目配置和maven依赖，让您更专注于业务开发
- 友好的代码结构及注释，便于阅读及二次开发
- 使用分层设计，分为dao，service，Controller，view层，层次清楚，低耦合，高内聚。
- 支持分布式部署，session集成了redis
- 灵活的权限控制, 整合shiro，可控制到页面或按钮，满足绝大部分的权限需求,优化权限注解方便权限配置
- 日志记录采用aop(LogAop类)方式，可对用户所有操作进行记录
- 集成jsp页面，采用标准JSTL标签库对常用组件进行封装，便于将传统项目过度到springboot
- 组件库丰富，对常用页面组件进行了代码封装，提高开发效率
- 前端页面简洁优美，支持移动端
- 支持多种浏览器: Google, 火狐, IE,360等


# 组织结构

```html
active4j-jsp
   • com.active4j.hr.base  基础代码
   • com.active4j.hr.common  通用代码
   • com.active4j.hr.core （redis、线程池、缓存、shiro、自定义注解等）
   • com.active4j.hr.func  系统常用组件功能
		- com.active4j.hr.func.upload 上传图片功能组件
   • com.active4j.hr.system  系统管理
   • JSP + JSTL  前端页面引擎 + 标签库




```


# 技术选型
### 服务端
| 技术 | 说明 | 官网  |
| ------------ | ------------ | ------------ |
|Spring Boot2|核心框架|[https://spring.io/projects/spring-boot/](https://spring.io/projects/spring-boot/ "https://spring.io/projects/spring-boot/")|
|Spring MVC|视图框架|[http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc](http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc "http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#mvc")|
|Apache Shiro|权限框架|[http://shiro.apache.org/](http://shiro.apache.org/ "http://shiro.apache.org/")|
|MyBatis|持久层框架|[http://www.mybatis.org/mybatis-3/zh/index.html](http://www.mybatis.org/mybatis-3/zh/index.html "http://www.mybatis.org/mybatis-3/zh/index.html")|
|MyBatis-Plus|MyBatis增强工具|[https://mp.baomidou.com/](https://mp.baomidou.com/ "https://mp.baomidou.com/")|
|Alibaba Druid|数据库连接池|[https://github.com/alibaba/druid](https://github.com/alibaba/druid "https://github.com/alibaba/druid")|
|Redis|分布式缓存数据库|[https://redis.io/](https://redis.io/ "https://redis.io/")|
|Quartz|作业调度框架|[http://www.quartz-scheduler.org/](http://www.quartz-scheduler.org/ "http://www.quartz-scheduler.org/")|
|ActiveMQ|消息队列|[http://activemq.apache.org/](http://activemq.apache.org/ "http://activemq.apache.org/")|
|SLF4J|日志组件|[http://www.slf4j.org/](http://www.slf4j.org/ "http://www.slf4j.org/")|
|QcloudCOS|腾讯云存储|[https://www.qcloud.com/product/cos](https://www.qcloud.com/product/cos "https://www.qcloud.com/product/cos")|
|Maven|项目构建管理|[http://maven.apache.org/](http://maven.apache.org/ "http://maven.apache.org/")|
|swagger2|文档生成工具|[https://swagger.io/](https://swagger.io/ "https://swagger.io/")|
|Apache Commons|工具类|[http://commons.apache.org/](http://commons.apache.org/ "http://commons.apache.org/")|
|fastjson|JSON解析库|[https://github.com/alibaba/fastjson](https://github.com/alibaba/fastjson "https://github.com/alibaba/fastjson")|
### 前端
|技术|名称|官网|
| ------------ | ------------ | ------------ |
|jQuery|js库|[http://jquery.com/](http://jquery.com/ "http://jquery.com/")|
|JSP|前端页面引擎|[https://www.runoob.com/jsp/jsp-tutorial.html](https://www.runoob.com/jsp/jsp-tutorial.html "https://www.runoob.com/jsp/jsp-tutorial.html")|
|JSTL|标准标签库|[https://www.runoob.com/jsp/jsp-jstl.html](https://www.runoob.com/jsp/jsp-jstl.html "https://www.runoob.com/jsp/jsp-jstl.html")|


# 功能列表
```html
- 项目主页：介绍项目简介、技术介绍、更新日志等信息
- 系统管理
    ○ 用户管理：用于管理后台系统的用户，可进行增删改查等操作
    ○ 部门管理：通过不同的部门来管理和区分用户
    ○ 菜单管理：维护系统菜单，操作权限，按钮权限
    ○ 角色管理：维护系统角色信息，以角色为单位分配系统权限
    ○ 数据字段管理：对系统中经常使用的一些较为固定的数据进行维护，如：是否、男女、类别、级别等
    ○ 日志管理：系统正常操作日志记录和查询；系统异常信息日志记录和查询
- 常用功能
    ○ 上传图片：集成图片上传本地或数据库
```

# 更新日志
## 2020-3-24 active4-jsp版本发布
# 版权声明
Active4j-jsp使用 MIT License 协议.

# 演示截图
![active4j-boot-01](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-01.png "active4j-jsp-01")
![active4j-boot-02](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-02.png "active4j-jsp-02")
![active4j-boot-03](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-03.png "active4j-jsp-03")
![active4j-boot-04](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-04.png "active4j-jsp-04")
![active4j-boot-05](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-05.png "active4j-jsp-05")
![active4j-boot-06](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-06.png "active4j-jsp-06")
![active4j-boot-07](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-07.png "active4j-jsp-07")
![active4j-boot-08](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-08.png "active4j-jsp-08")
![active4j-boot-09](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-09.png "active4j-jsp-09")
![active4j-boot-10](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/active4j-jsp-10.png "active4j-jsp-10")