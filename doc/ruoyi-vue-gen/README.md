# ruoyi-vue-gen

此项目为ruoyi-vue代码生成器独立版；我将ruoyi-vue线上代码生成器的代码抽离出来封装成一个基于配置文件的、可执行的jar包，这样可以更加方便的生成模板代码。其生成结果和
前端“两步走（导入表和下载源码，无编辑）”生成的代码等效。

ruoyi-vue项目地址：[https://gitee.com/y_project/RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue)

## 使用手册

### 1. 配置application.properties配置文件
该配置文件的内容如下：
```properties
mysql.username=${dbUserName}
mysql.password=${dbPassword}
mysql.connectionUrl=${dbUrl}
mysql.driverClass=${dbDriverClassName}

gen.author=kdyzm
gen.packageName=com.kdyzm.business
gen.autoRemovePre=false
gen.tableName=news
gen.tablePrefix=sys_
```
上半部分是数据库配置，连接的是ruoyi-vue数据库，正常配置即可；下半部分是生成配置，除了`gen.tableName`，其它配置和原ruoyi-vue代码生成器的配置相同。

### 2.运行Main
在项目根目录运行命令`mvn clean package`，打包完成之后切换到target目录，使用命令`java -jar ruoyi-vue-gen-1.0-SNAPSHOT.jar`运行jar包得到ruoyi.zip压缩文件

解压之后生成目录结构如下所示
```text
├── goodsMenu.sql
├── main
│ ├── java
│ │ └── com
│ │     └── kdyzm
│ │         └── business
│ │             ├── controller
│ │             │ └── GoodsController.java
│ │             ├── domain
│ │             │ └── Goods.java
│ │             ├── mapper
│ │             │ └── GoodsMapper.java
│ │             └── service
│ │                 ├── IGoodsService.java
│ │                 └── impl
│ │                     └── GoodsServiceImpl.java
│ └── resources
│     └── mapper
│         └── business
│             └── GoodsMapper.xml
└── vue
    ├── api
    │ └── business
    │     └── goods.js
    └── views
        └── business
            └── goods
                └── index.vue
```

生成的代码分为三部分：后端代码、前端代码、sql语句，和在前端生成的代码完全一致。

## 注意事项

1. 生成的sql中有创建菜单的sql，该菜单如果不指定父级，会默认挂载到系统工具菜单下，这可能是个bug，已经在ruoyi-vue项目中提了issue，链接：[https://gitee.com/y_project/RuoYi-Vue/issues/I3915P](https://gitee.com/y_project/RuoYi-Vue/issues/I3915P)

2. 若依代码生成器支持三种数据格式模板的代码生成：单表、树表、主子表，这里默认使用的是单表模板，也是最常使用的模板。

3. 该生成工具更加注重后端CRUD接口的生成，前端页面组件的样式无法修改

4. 代码生成结果仅使用用ruoyi-vue项目，如需自定义模板，请自行修改源代码

5. 如果自定义包名配置，请注意修改原ruoyi-vue项目中的mybatis别名配置和MapperScan注解配置

## 了解更多

可以访问文章：[若依管理系统RuoYi-Vue（三）：代码生成器原理和实战](https://blog.kdyzm.cn/post/48) 了解更多






