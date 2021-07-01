### 从若依分离出来的 Code Generator   
1. 新建数据库, 把 document/gen.sql 导入   
2. 在 resources 文件夹中的 application-druid.yml 中配置数据库连接信息   
3. 在 generator.yml 中配置作者, 接口调用密钥, 默认生成package名称等   
4. Build 之后直接部署运行, 或者把jar包改名为code.jar, 与Dockerfile放在同一目录下执行 ```docker build .```   , 然后 ```docker run -d -p 19999:19999 --name=code-gen 镜像ID```   
5. 直接操作数据库, 导入建表语句, 表创建完成之后调用 http://localhost:19999/api/gen/download/{secret-key}/{table-name}/{package-name}  (GET请求) 下载代码
如 http://localhost:19999/api/gen/download/password/sys_user/com.github.cccy0.code.generator