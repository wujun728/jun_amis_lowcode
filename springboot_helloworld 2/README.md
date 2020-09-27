# SpringBoot-HelloWorld

#### 项目介绍
SpringBoot系统入门项目

#### 软件架构
软件架构说明


#### 安装教程

1. git clone https://gitee.com/KiWiPeach/SpringBoot-HelloWorld.git
2. import to your IDE

#### 项目说明

| 项目模块                | 案例设计内容                                                 |
| ----------------------- | ------------------------------------------------------------ |
| 01-springio-quickstart  | springboot官方网站上提供的标准helloword案例                  |
| 02-kiwipeach-quickstart | 自己手工创建的helloword案例                                  |
| 03-idea-initializar     | 使用IDE工具快速生成的项目骨架，本例使用IDEA开发工具          |
| 04-properties-config    | 1.使用application.properties属性配置文件                     |
|                         | 2.@PropertySource&@ImportResource&@Bean方式注入bean到ioc容器 |
|                         | 3.@Value注解不能注入封装对象类型；不能数据校验；可以使用SpEL |
|                         | 4.配置文件占位符（随机数，uuid，引用其他属性，为空判断）     |
| 05-yaml-config          | 1.使用application.yaml/yml属性配置文件                       |
|                         | 2.@ConfigurationProperties注解能数据校验；不能使用SpEL       |
|                         | 3.配置文件占位符（随机数，uuid，引用其他属性，为空判断）     |
| 06-properties-loadorder | 1.多个profile文件，文件激活                                  |
|                         | 2.使用VM参数进行文件激活                                     |
|                         | 3.使用系统环境变量进行文件激活                               |
|                         | 4.命令行进行文件激活java -jar *.jar --spring.profiles.active=prod |
|                         | 5.更多属性配置文件加载顺序见Application.java文件注释.        |
| 07-properties-location  | 属性配置文件加载的位置，(资源路径/项目路径/磁盘路径)         |



#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [http://git.mydoc.io/](http://git.mydoc.io/)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)