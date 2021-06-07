


![active4j](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jboot/logo.png "active4j")


Active4j-flow是基于SpingBoot2.0与activiti7.x开发的一套工作流程管理系统。是以[active4j-jsp](https://github.com/yunchaoyun/active4j-jsp "active4j-jsp")为项目基础架构构建而成。以Spring Framework为核心容器，Spring MVC为模型视图控制器，Mybatis Plus为数据访问层， Apache Shiro为权限授权层, Redis为分布式缓存，JSP作为前端页面引擎，采用JSTL标签库封装组件。



------------

# 项目介绍
 - Active4j-flow是基于SpingBoot2.0与activiti7.x开发的一套工作流程管理系统。是以[active4j-jsp](https://github.com/yunchaoyun/active4j-jsp "active4j-jsp")为项目基础架构构建而成。以Spring Framework为核心容器，Spring MVC为模型视图控制器，Mybatis Plus为数据访问层， Apache Shiro为权限授权层, Redis为分布式缓存，JSP作为前端页面引擎，采用JSTL标签库封装组件。
 - Active4j-flow目前内置了部门管理、用户管理、角色管理、菜单管理、数据数据字典等基础功能。
 - Active4j-flow内置了在线流程设计器，流程部署，流程定义，表单管理，流程权限管理，流程中心，我的流程，待我审批等基础流程管理功能。
 - Active4j-flow支持流程驳回，会签，组任务，权限管理，委托，审批意见等常用功能


# 技术文档
- 讨论加群：qq群①：203802692   qq群②：773872959
- 演示地址：[http://www.active4j.com:9006/flow](http://www.active4j.com:9006/jsp "http://www.active4j.com:9006/flow")
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



# 技术选型
### 服务端
| 技术 | 说明 | 官网  |
| ------------ | ------------ | ------------ |
|Spring Boot2|核心框架|[https://spring.io/projects/spring-boot/](https://spring.io/projects/spring-boot/ "https://spring.io/projects/spring-boot/")|
|activiti7|流程引擎|[https://www.activiti.org/](https://www.activiti.org/ "https://www.activiti.org/")|
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
- 流程管理
    ○ 流程类别：用于管理业务流程类别
    ○ 流程模型：集成activiti在线流程设计器
    ○ 流程部署：管理activiti流程部署，支持在线设计器流程部署与文件上传部署
    ○ 流程定义：管理流程定义，显示流程版本
    ○ 表单类别：用于管理业务表单类别
    ○ 表单构建：在线表单设计器
    ○ 表单列表：管理系统中的表单数据，分为在线设计的表单与系统预设表单
    ○ 流程管理：集中管理系统中流程，涉及流程类别，权限，版本控制等
- 流程中心
    ○ 流程中心：显示系统在用最新版本流程
    ○ 我的流程
        ○ 我的草稿：显示我的流程草稿
        ○ 我的申请：显示我的申请流程，可以查看审批进度
        ○ 我的完结：显示我的完结流程
    ○ 待我审批：显示系统中需要本人审批的流程
    ○ 已办审批：显示系统中本人已经完成的审批流程
    ○ 代办组任务：显示系统中有本人参与的组任务审批流程

```

# 更新日志

# 版权声明
Active4j-flow使用 MIT License 协议.

# 演示截图
![active4j-flow01](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow01.png "active4j-flow01")
![active4j-flow02](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow02.png "active4j-flow02")
![active4j-flow03](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow03.png "active4j-flow03")
![active4j-flow04](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow04.png "active4j-flow04")
![active4j-flow05](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow05.png "active4j-flow05")
![active4j-flow06](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow06.png "active4j-flow06")
![active4j-flow07](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow07.png "active4j-flow07")
![active4j-flow08](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow08.png "active4j-flow08")
![active4j-flow09](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow09.png "active4j-flow09")
![active4j-flow10](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow10.png "active4j-flow10")
![active4j-flow11](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow10.png "active4j-flow11")
![active4j-flow12](https://zh-active4j-1251505225.cos.ap-shanghai.myqcloud.com/active4jflow/active4j-flow/flow10.png "active4j-flow12")

