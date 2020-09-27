# dynamicds-spring-boot

## 简介
基于Spring Boot实现的多数据源动态切换，适用于2个及以上的数据源的项目。

1. 使用注解 `DSSelector` 标记在方法上定义在执行此方法时选择的数据源
2. 可标记在 `Service` 或者 `Batis Mapper Interface`上
3. 默认使用 `master` 数据源
4. 支持多种数据源（只要能与Spring Boot集成都可以）

## POM依赖
```xml
<dependency>
  <groupId>com.billcoding.dynamicds</groupId>
  <artifactId>dynamicds-spring-boot-starter</artifactId>
  <version>${version}</version>
</dependency>
```

## Yml配置
```yml
dynamicds:
  enable: true #开启动态数据源
  master: read #指定主数据源
  datasource: #定义数据源
    write: #数据源名称
      username: root
      password: 123
      url: jdbc:mysql://localhost:3306/test
      driver-class-name: com.mysql.cj.jdbc.Driver
    read: #数据源名称
      username: root
      password: 123
      url: jdbc:mysql://localhost:3306/test
      driver-class-name: com.mysql.cj.jdbc.Driver
```

