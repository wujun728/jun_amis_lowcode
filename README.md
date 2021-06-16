### `jun_spring` 项目

#### 项目说明
基于Spring家族的360度整合，基本覆盖开发用九成以上的场景，即开即用，主要是详尽的了解项目里面的每个stater跟lib组件及功能，为个人及项目实际开发提供助力，帮组项目快速开发，本项目是maven项目，每个组件均可以独立运行且不依赖第三方框架，具体见项目明细。



#### jun_spring  项目module组件功能列表
- 【spring_helloworld】springboot 的一个 helloworld  
- 【spring_properties】springboot 读取配置文件中的内容  

TODO：
1、整cs的login跟index的page，适配Nginx跟ssm_jwt；
2、整ssm_jwt的代码生成器
3、整ssm_jwt的common工具包
4、整ssm_jwt的api跟rbac跟表结构


spring_activemq\    干掉，放到mq里面
spring_atomikos\    调整
spring_autowired\    干掉，合并到spring_demos
spring_camel\    调整
spring_cas\    调整
spring_cors\ 调整
spring_demo\      调整
spring_disruptor\    干掉
spring_distributed_config\ 调整
spring_distributed_fastdfs\ 调整
spring_distributed_lock\ 调整
spring_distributed_multidatasource\ 调整
spring_distributed_netty\ 调整
spring_distributed_oss_qiniu\ 调整
spring_distributed_rpc\ 调整
spring_distributed_session\ 调整
spring_distributed_transaction_tcc\   新增seata
spring_drools\ 调整
spring_dubbo\    调整
spring_dynamic_job\   干掉，合并到spring_quartz
spring_elasticsearch\ 调整
spring_email\    合并到jun_excel
spring_excel\   合并到jun_email
spring_generator\ 干掉
spring_hibernate\ 调整一下
spring_jasperreport\ 干掉，新增jimureport
spring_jpa\ 调整一下
spring_jqgrid\   干掉，迁移到spring_demo里面
spring_jsonp\   干掉，迁移到cors里面
spring_jsoup\   干掉，迁移到spring_webmagic里面
spring_jwt\    调整
spring_kafka\   干掉，新增spring_mq
spring_lucene\ 调整
spring_mina\   干掉，迁移到netty里面
spring_mongodb\ 调整
spring_mybatis\ 调整，并新增mybatisplus
spring_netty\ 调整
spring_nutch\ 干掉
spring_oauth\   调整
spring_plupload\ 调整
spring_quartz\ 调整
spring_rabbitmq\   干掉，迁移到mq里面
spring_redis\   调整
spring_s2sh\    干掉，迁移到ssh
spring_session\   调整
spring_shiro_redis\     调整
spring_simplessh14\   干掉，迁移到ssh里面
spring_solr\   调整
spring_spider\    干掉，迁移到spring_webmagic
spring_springbatch\   干掉，迁移到quartz里面
spring_springjdbc\ 调整
spring_springmvc\ 调整
spring_ssh\   调整
spring_ssm_dubbo\ 干掉，迁移到spring_ssml里面
spring_ssm_freemarker\ 干掉，迁移到spring_ssml里面
spring_ssm_layui\ 干掉，迁移到spring_ssml里面
spring_ssm2\ 干掉，迁移到spring_ssml里面
spring_sso\   干掉，迁移到oauth里面
spring_struts2\ 干掉，可以直接干掉，迁移到ssh里面
spring_swagger\    调整
spring_task\   干掉，迁移到quartz里面
spring_thymeleaf\    调整
spring_validator\ 干掉，新增spring_hibernate_validator
spring_velocity\   调整
spring_websocket\



doc\
SpringRainV3.1.0\ 合并到下面的ssm里面
SpringWind\   合并到下面的ssm里面
sshe\    合并到下面的ssm里面
jun_ssh_easyui\ 将前端的easyui调整为html+js放到nginx里面，保留
jun_ssm\
jun_ssm_admin\
jun_ssm_biz\
jun_ssm_captcha\
jun_ssm_common\
jun_ssm_email\
jun_ssm_mis\
jun_ssm_web\
 
jun_ssm_jwt\ 新增
jun_ssm_wechatservice,
jun_ssm_bizservice,
jun_ssm_apiservice,
jun_ssm_easyui,
jun_ssm_bootstrap,
jun_ssm_layadmin
 


WordPress4J  --迁移到jun_website
Springside3   迁移到jun_spring
Springside4   迁移到jun_boot

Jun_ssm
	jun_bootstrap2\   干掉，代码则取重要的合并到ssh_common
	jun_ssm\   干掉，代码迁移main核心111
	jun_ssm_bootstrap\   干掉， 迁移到ssm
	jun_ssm_dubbo\   保留，迁移，代码迁移到ssm
	jun_ssm_easyui\    主代码，ssm，拆分API+WEB
		jun_ssm_api\    主代码，ssm，拆分API+WEB
		jun_ssm_web\    主代码，ssm，拆分API+WEB
	jun_ssm_jsp\   干掉，代码迁移参考111
	jun_ssm_layui\    干掉，代码迁移ssm
	jun_ssm2\
		laycms\     干掉，代码迁移ssm
		spring_admin\   干掉，代码迁移到boot
		spring_springwind2\  干掉，代码ssm
		spring_ssm_cluster\  干掉，代码ssm
		spring_ssm_easyui\  外部跑起来，代码ssm
		spring_wind\    合并到ssm framework
		
		
Code-generator
	doc
		adminlte2-itheima-doc\  迁移到frontend
		codeutil\   合并到maventemplate，跑起来
		dp-generator\     干掉，代码挪走gen
		dp-security\   干掉，代码迁移ssm
		itheima-cli\     迁移到fontend
		roncoo-adminlte-springmvc\   干掉，代码合入ssm
		ssm\  代码合入ssm
		vue-element-admin-api-java-itheima\  干掉，合并到boot
		vue-element-admin-doc-itheima\  迁移到frontend
		vue-element-admin-itheima\    迁移到frontend
Jun_test111
	SSM-Shiro-JWT    合并到jwt
	auto-code\   干掉，代码合入到helper，拼字符串没模板
	auto-code-admin\  干掉，代码迁移到boot
	auto-code-springboot-demo\  干掉，合并boot
	auto-code-ui\  重要，保留，迁移到gen里面
	auto-code-ui-spring-boot-starter\   只是个模板，合并到boot
	auto-code-web-demo\    只是个模板，合并到ssm
	black\   迁移到product，新增产品图片网
	codeGenerator\   重要，保留，合并到gen，新增freemarker的模板
	code-generatorcpp\   干掉，代码合入到gen
	code-template\  合并到跟
	easy-boot\   迁移到boot
	generator\  重要，保持刘，合并到gen，新freemarker的模板
	HbackmanageDemo\   迁移到frontend
	hplus_requirejs_singlePage\  迁移到frontend
	hplus-shiro\   重要，main
0000000000000000000
	ifast\
	ittree.club-mybatis-generator\
	Light-Year-Admin-Template\
	mk-teamwork-server\
	mk-teamwork-ui\
	multi-module-web\
	mybatis-dsc-generator\
	one-deploy\
	redisson-spring-boot-starter\
	renren-generator\
	RuoYi\
	RuoYi-Vue\
	SpringBoot_v2\
	springboot2-integration\
	SpringBootCodeGenerator\
	Springboot-Mybatis-Thymeleaf\
	spring-boot-quartz\
	X-admin\
	xxl-deep\
	xxl-deep2\
	

jun_2021
jun_framework
jun_temp1
jun_temp2
jun_test\




jun_product
	123\
		BlogHtTemplate\  迁移到jun_frontend/jun_layui
		blogv20180113\  干掉 ，代码
		inspinia_admin_java_ssm\    干掉，代码
		layuiAdmin\   迁移到jun_frontend/jun_layui
		LuGenerate\   干掉，代码搞spring_plugin
		manager-system\  迁移到jun_boot rename jun_boot_layadmin
		noteblogv5\   干掉，源码迁移，没有脚本
		simple-spring-jdbc\  迁移到spring_jdbc
		snaker-springmvc\  干掉，代码
		spring-Boot_templates_layui-Admin\   直接干掉
		spring-shiro-training\  迁移到easyui
		sypro\  迁移到ssh里面
		zb-shiro\   合并到ruoyi
		
	jun_administrative\
	jun_ask_discuss\
	jun_blog\
	jun_bos\
	jun_crm\
	jun_edu\  外网调试
	jun_erp\
	jun_finance\
	jun_flybbs\
	jun_hr\
	jun_itselfservice\
	jun_mis\
	jun_music\
	jun_oa\
	jun_op\
	jun_portal\
	jun_prj\
	jun_resume_java\
	jun_resume_pm\
	jun_spring\
	jun_wms\
	项目1111111\
		Jar包下载网视频教程\
		百度云爬虫视频教程\
		百度云搜索视频教程\
		博客采集系统视频教程\
		博客系统视频教程\
		客户关系视频教程\
		请假系统视频教程\
		设备系统视频教程\
		实用cms系统视频教程\
		支付系统视频教程\
		mindskip-uexam-master.zip
		project.zip
		project(1).zip
		project(1)(1).zip
		
	pom.xml
	
	
	1、父pom.xml jun_system --> jun_product
	https://github.com/xuxueli/xxl-deep
	https://github.com/oneboat/ssm-lay
	https://github.com/search?q=lay++ssm&type=Repositories
	https://github.com/lcw2004/one
	https://github.com/thinkgem/jeesite
	https://github.com/thinkgem/jeesite4
	https://github.com/thinkgem/jeesite4-cloud
	
	
	https://github.com/febsteam/FEBS-Cloud
	https://github.com/febsteam/FEBS-Shiro
	
	ssm
	https://github.com/Mandelo/ssm_shiro_blog
	
	
	boot
	https://github.com/yzcheng90/X-SpringBoot
	https://github.com/Heeexy/SpringBoot-Shiro-Vue
	
	cloud
	https://github.com/yzcheng90/ms
	https://github.com/yzcheng90/ms-ui
	
	
	CMS
	https://github.com/xujeff/tianti
	
	*****
	https://github.com/hope-for/hope-boot  

https://github.com/moshowgame/SpringBootCMS



补充TODO待办清单

0、jun_ssh_easyui\ 将前端的easyui调整为html+js放到nginx里面，保留
1、补充API后台管理功能
2、补充代码生成器及模板
3、补充EasyUI模板
4、补充LayUI模板
6、补充Bootstrap模板
 
7、
https://github.com/huang303513/NodejsVuePractice
https://github.com/yuzhiping/jeeplus
https://www.jianshu.com/p/0bfe2318814f
https://github.com/u014427391/jeeplatform
https://github.com/white-cat/jeeweb

        
spring_data_jpa 调整，跑起来，适配合入到spring_jpa
spring_data_jpa (2)   删除
spring_data_jpa 2  删除
spring_dbutil   调整 spring_dynamicdatasource
spring_dbutil (2)   干掉
spring_dbutil 2 干掉
spring_demo 调整
spring_demo 2  干掉
spring_design_pattern  干掉，合入 jun_designpattern
spring_distributed_session
spring_doc  合并到maven_template里面
spring_doubbo  删掉
spring_drools_demo  调整 spring_drools
spring_dubbo_pay 挪走
spring_dubbo_pay 2  干掉
spring_dynamic_job 调整
spring_dynamic_job 2 删掉
spring_dynamic_job2 删掉
spring_easypoi  调整
spring_email  待添加
spring_example  调整，合入到spring_demo 
spring_fastdfs 调整
spring_fastdfs 2 合并到 spring_fastdfs
spring_fastdfs_client_java  干掉
spring_fastdfs2 干掉
spring_framework  不动
spring_framework_4_reference  干掉
spring_generator 调整
spring_generator2 干掉
spring_guides_translation 不动
spring_hibernate 调整
spring_hibernate 2  干掉
spring_hibernate 3  合并到spring_hibernate 
spring_hibernate_plus 调整
spring_hibernate_plus 2 干掉，新增springboot_hibernate_plus
spring_html_css_js  干掉，挪走，jun_fontend里面
spring_html5 干掉，挪走，jun_fontend里面
spring_ibatis_freemarker_sitemesh  干掉，挪走到jun_spring\spring_springmvc_test\spring_springmvc_multiviewresolver
spring_ibatis_plus   干掉，代码合入到mybatis代码生成器里面
spring_itext  占位
spring_jasperreport  调整
spring_javase  挪走，到jun_javase
spring_jbpm  调整
spring_jdbc (2)  干掉，合并到spring_springjdbc里面
spring_jpa  调整，跑起来
spring_jpa (2)  合入到spring_jpa
spring_jpa_hibernate_annotationtx  干掉，合入到spring_hibernate
spring_jqgrid_tutorial  跑起来
spring_jquery  干掉
spring_jquery_easyui  干掉
spring_json_view  干掉，合并到spring_demo
spring_jsonp 调整
新增spring_cors
spring_jsoup  干掉，合并到jun_jsoup
spring_ky26  干掉，合并到jun_javase
spring_lucene  占位，合并到jun_lucene
spring_mina  占位，调整
spring_mongodb  调整
spring_mongodb 2  调整，合并到spring_mongodb
spring_mongodb_jdbc_bae   合并到spring_springjdbc
spring_mvc_mybatis 挪走，到jedp_framework
spring_mybaits  挪走到spring_mybatis
spring_mybatis  挪部分到spring_mybatisplus
spring_mybatis.generator  干掉，合入到spring_mybatis_generator
spring_mybatis_generator 干掉，合入core到当前
spring_mybatis_generator_core 干掉，合入到spring_mybatis_generator
spring_mybatis_generator_gui 干掉，合入到spring_mybatis_generator
spring_mybatisplus_springmvc   重命名rename, spring_mybatisplus_generator 
spring_neo4j  调整
spring_neo4j 2  干掉
spring_oauth_client  合入到spring_oauth
spring_oauth_server  合入到spring_oauth
spring_oauth_server2  合入到spring_oauth
spring_oschina   合入到spring_demo
spring_perfect_ssm  合入到jdep_framework，整齐备 ssm系列，见readme
spring_perfect_ssm2 干掉
spring_plupload  调整
spring_plupload 2  干掉
spring_plupload2 干掉
spring_pom   干掉，合并到mvn_template
spring_production_ssm  干掉，合并到jdep_framework
spring_quartz  调整
spring_quartz_demo 干掉，代码合入到spring_quartz 
spring_rabbitmq
spring_rabbitmq 2   干掉
spring_rabbitmq2  干掉 
spring_redis
spring_redis 2  干掉
spring_redis_ssm_mysql  代码合入到jedp_framework
spring_redis2  干掉
spring_rest  重命名，srping_restful
spring_rest 2  干掉
spring_rest_helloworld  干掉
spring_rest2  干掉 
spring_s2sh     调整  ssh2
spring_security
spring_security_demo  干掉
spring_security_demo 2  干掉
spring_security2  干掉
spring_servers  干掉
spring_servlet  干掉，合入到springmvc
spring_servlet3  干掉，合入到springmvc



Login.html
 
login.css
login-1.css
bg-1.gif
SignOnServlet.htm
SignOnServlet-1.htm
bg1.jpg
bg1-1.jpg
logo.jpg
jquery-1.8.0.min.js
jquery-1.8.0.min-1.js
bnt.png
bnt_en-1.png
logo.png
 
 
index.html
 
button.css
commons.css
index.css
jquery-ui-1.8.20.custom.css
kefu.css
mybootstrap.css
pdf.css
style.css
index.htm
New.html
bootstrap.min.js
jquery.color.js
jquery.jslides.js
jquery.md5.js
jquery-1.7.1.min.js
jquery-1.9.1.min.js
jquery-ui-1.8.16.custom.min.js
kefu.js
oaActionLog.js
PDFObject.min.js
personal_information.js
portal.js
1.png
2.png
6.png
9.png
fuwurx.png
 
 
 
https://github.com/shenkunlin/code-template
 
 
 
 
1、整理cs的login页面
2、整理cs的index页面
3、整理layui的静态前端页面并归档
4、整理adminlte及hplus
5、整理easyui的前端页面并归档
6、整理代码生成器
单表生成
关联表生成
7、整理ssm项目
8、整理boot项目
先根据邮件去掉非必须的项目
综合配置login及index的页面的归档
---Boot+jwt
https://github.com/cailichao/easyweb-jwt
https://github.com/whvcse/JwtPermission
 
公司平台
企业官网
权限配置
电子流待办
公告消息
 
财经服务
支付报销
财务管理
发薪账户
内控风险
HR服务
员工手册
个人信息
考勤系统
HRBP专区
EM系统
商保政策
招聘专区
员工行为规范
 
行政服务
差旅服务
资产管理
保修服务
更多服务
有问必答
常见问题解答
员工有问必答
IT问题自助处理
公司论坛
IT服务
企业邮箱
密码取回
权限申请
设备进出
知识学习
学习平台
解决方案库
公司知识库
运营管理
运营平台
项目预算管理
销售专区
项目管理平台
项目成果归档
BCG签署
办公审批
OA办公
OA工作台
 
 
财务管理
https://github.com/1649865412/finance
https://github.com/jesonwin/springBoot-fm
https://github.com/qd-hzc/caiwufenxi
 
差旅管理
https://github.com/LanSunSL/trbu-256
https://github.com/shmilychan/HRSystem-SSM
 
供应链
商品中心
订单中心
客户管理
ERP管理
 
 



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
- [x] spring_boot_demo_activiti（集成 Activiti 实现流程控制引擎）
- [x] ~~spring_boot_demo_async（Spring boot 实现异步调用）~~

