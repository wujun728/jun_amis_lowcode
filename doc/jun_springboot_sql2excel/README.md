# SqlGeneratorExcel

#### 介绍
数据库文档生成工具，根据数据库存在的表 反解析成Excel数据库文档

#### 前言
能够根据配置的数据库信息,反向根据数据库生成指定的数据库的数据库文档Excel格式的文件。

datasource.properties 的配置项有：
````
#数据库的IP
host=127.0.0.1
#数据库的端口号
port=3306
#数据库的名称
databaseName=test
#数据库账号
username=root
#数据库密码
password=root
#导出Excel的绝对路径
exportPath=D://Soft
````

#### 使用说明

##### 1. 自行构建方式
1. 拉代码
2. maven安装依赖
3. 编译下或者运行下为了生成``target``文件
4. 复制根目录下的``datasource.properties``文件复制到``target``文件里
5. 配置``datasource.properties``里的参数为你自己的
6. 运行Main方法
7. 查看自动生成的 Excel 数据库文档文件


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
