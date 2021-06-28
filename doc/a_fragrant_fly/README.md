# 一指香飞 自述文件 ( markdown格式)
***************************************
`一指香飞`，是有uni-app开发的一个掌上点餐系统。我们也简称`XF`。
#### 安卓手机app已经打包发布在百度网盘,欢迎下载安装体验。
```
更新日期：2019-02-13;
新添加用户登陆/注册/找回密码/用户中心/修改用户资料/服务器真实api接口
[h5web体验地址(这个不一定是最新版-如果要看最新版请下载源码自行编译)](http://xf.01film.cn)
[使用体验百度网盘下载地址——提取码：p3ta ](链接：https://pan.baidu.com/s/1sS2Nglji7CUwH_sLSpcePQ)
[git源码下载地址](链接：https://gitee.com/wokaixin/a_fragrant_fly)
```
![首页1](https://raw.githubusercontent.com/tanyichen/img/master/xf/index.png)
![首页2](https://raw.githubusercontent.com/tanyichen/img/master/xf/index1.png)
![商铺列表](https://raw.githubusercontent.com/tanyichen/img/master/xf/shoplist.png)
![商铺首页](https://raw.githubusercontent.com/tanyichen/img/master/xf/shop.png)
![订单详情](https://raw.githubusercontent.com/tanyichen/img/master/xf/orders.png)
![订单列表](https://raw.githubusercontent.com/tanyichen/img/master/xf/orders1.png)
![提示消息](https://raw.githubusercontent.com/tanyichen/img/master/xf/msg.png)
![提示消息-订单](https://raw.githubusercontent.com/tanyichen/img/master/xf/msgOrders.png)
![个人中心](https://raw.githubusercontent.com/tanyichen/img/master/xf/wode1.png)
![个人信息修改](https://raw.githubusercontent.com/tanyichen/img/master/xf/wodeupdate.png)
![头像表单](https://raw.githubusercontent.com/tanyichen/img/master/xf/headimg.png)
![地址表单](https://raw.githubusercontent.com/tanyichen/img/master/xf/address.png)
![商品表单1](https://raw.githubusercontent.com/tanyichen/img/master/xf/editor1.png)
![商品表单2](https://raw.githubusercontent.com/tanyichen/img/master/xf/editor2.png)
![商品表单3](https://raw.githubusercontent.com/tanyichen/img/master/xf/editor3.png)
####支持打包：
```
1、安卓；
2、ios；
3、微信小程序；
3、支付宝小程序；
4、百度小程序；
5、h5；

```
#### 使用说明
1. 下载编辑器HBuilderX.
2. 下载微信开发者工具.
3. 点击HBuilderX顶部菜单->文件->导入->从git导入.
4. 鼠标点击App.vue获取焦点,无需打开文件.
5. 点击HBuilderX编辑器顶部菜单->运行->运行到小程序模拟器.
6. 如果启动不了微信开发者工具，请手动启动微信开发者工具，手动添加项目(项目路径为unpackage/dev/mp-weixin)
7. 打包:
7.1 打包app：点击HBuilderX顶部导航->发行->原生APP云打包.
7.2 打包微信小程序：把项目路径unpackage/dev/mp-weixin文件夹拷贝出来即可。
#### 打包前注意
```
页面路径配置文件 pages.json内"condition"节点是用来调试的。current=0,代表当前启动首页是(list 节点下的的索引项);
发表项目一定要删除condition节点
```
## 特点
1. 一套代码多端通用
   支持编译封装成h5app安卓端/苹果端,微信小程序等终端运行程序。
2. 组件开发模式，复用性强
   采用mvvm组件开发模式，只要了解过vue和微信小程序的编程模式，即可看懂程序代码。
## 目录结构
```
┌─components            uni-app组件目录
│	│─pages				 可复用的页面内容组件目录
│	│	└─address		    地址管理
│	│─crop			     裁剪图片
│	│	└─crop				裁剪图片
│	│─mpvue-citypicker   弹出城市选择器
│	│	│─mpvueCityPicker	    弹出选择器
│	│	└─city-data				地区数据
│	│		│─area                  省-市-区
│	│		│─city                  省-市
│	│	    └─province		        省
│	│─mpvue-picker          弹出选择器
│	│	└─mpvuePicker	        弹出选择器
│	│─crop			     裁剪图片
│	│	│─crop				裁剪图片
│	└─template			 可复用的模板组件目录
│		│─box				盒子模板目录
│		│	└─number		商品数字加减模板
│		│─drawer			抽屉模板目录
│		│	│─bottom        底部弹出商品选择
│		│	└─drawer		抽屉模板
│		│─icon			icon图标库模板
│		│	└─icon			uni-app官方默认的icon
│		│─im-chat			聊天对话模板
│		│	│─chatinput		输入模板
│		│	│─orderMessage  新订单消息模板
│		│	└─messageshow	消息内容模板
│		│─index			入口页模板
│		│	└─shopList		店铺首页模板
│		│─nav				导航模板目录
│		│	└─bottom		底部导航
│		│─picker			弹出选择
│		│	└─mpvuePicker	默认弹出选择
│		│─product			商品模板
│		│	│─shopList      店铺列表模板
│		│	└─list			商品列表
│		│─swiper			滚动模块
│		│	└─big-ad		大屏广告图片左右滚动
│		│─uni-icon			icon图标库模板
│		│	└─uni-icon		uni-app官方默认的icon 含font-icon
│		│─uni-notice-bar	    滚动通告字栏
│		│	└─uni-notice-bar    滚动通告字栏
│		│─verify	            验证
│		│	└─captchaInput      验证码
│		└─unit			        元素装置
│			│─search		    搜索框
│			└─search		    加载旋转圈圈
├─common				可复用公共工具插件类
│	│─data				数据目录
│	│─css				公共css 目录
│	│	│─iconfont.css	项目需要的iconfont样式
│	│	└─tui.css			项目自定义的全局css样式
│	│─icon.css			uni-app官方默认icon
│	│─uni.css			uni-app官方默认css
│	└─yc-js				公共js工具插件目录
│		│─Base64.js		base64编码转换工具
│		│─Id.js		    id生成
│		│─Img.js		图片处理
│		│─index.js		入口
│		│─Json.js		json处理转换
│		│─Md5.js			Md5编码转换工具
│		│─Obj.js			数组对象处理
│		│─Storage.js		Storage缓存工具
│		│─Time.js			时间格式转换处理工具
│		│─Url.js			Url地址处理工具
│		└─Validate.js		input输入验证器
│     
├─request               AJAX请求封装
│	├─data				模拟请求所需的数据目录，实际开发中，请删除。
│	│	│─xxx.js			模拟数据
│	│	└─xxx.js			…………
│	│─api.js			api接口配置
│	└─index.js			AJAX请求封装
│     
├─hybrid                存放本地网页的目录，解决小程序只支持网络目录,[详见](https://uniapp.dcloud.io/component/web-view)
│	└─html
│		├─css
│		│	└─xxx.css
│		├─img
│		│	└─icon.png
│		├─js
│		│	└─xxx.js
│		└─local.html
│    
├─store     vuex
│	├─goods.js			商户商品购物车处理
│	│─index.js          vuex主入口封装
│	│—win.js		    窗口宽高元素处理
│	└─store.js        	登陆状态 用户信息处理
│ 
├─platforms             存放各平台专用页面的目录，[详见](https://uniapp.dcloud.io/platform)
│     
├─pages                 业务页面文件存放的目录
│	├─home
│	│	└─index.vue       index站点入口页面
│	│─chat				消息对话
│	│	│─chat.vue          消息对话
│	│	│—list.vue		    消息列表
│	│	└─order.vue         订单消息
│	│─goods				商场主目录
│	│	│─index.vue         店铺首页
│	│	│─about.vue         关于商家简介
│	│	│—list.vue		    商品列表页
│	│	│—cart.vue		    购物车页
│	│	│—collect.vue		我的收藏-店铺
│	│	│—detail.vue		商品详情页
│	│	│—order.vue		    订单页
│	│	│—shop.vue		    店铺首页
│	│	└─category.vue      分类页
│	│─user				用户目录
│	│	│—address.vue		    地址页
│	│	│—info.vue		        个人信息页
│	│	│—security.vue		    设置
│	│	│—update.vue		    信息修改
│	│	└─wode.vue              我的首页
│	│─login				登陆目录
│	│	│—login.vue		    登陆
│	│	│—pwd.vue		    密码找回
│	│	└─reg.vue		    注册页
│	│─order			    订单目录
│	│	│—list.vue		    订单列表
│	│	└─detail.vue	    订单详情
│	└─pay			    支付目录
│	 	└─payment.vue		    付款
│     
├─static                存放应用引用静态资源（如图片、视频等）的地方，注意：静态资源只能存放于此
│	├─ad				广告图片目录
│	│	└─xxx.jpg			图片
│	├─icon				图标图片目录
│	│	└─xxx.jpg			图片
│	├─nav				导航图片目录
│	│	└─xxx.jpg			图片
│	├─image				图片目录
│	│	└─xxx.jpg			图片
│	└─audio				媒体目录
│		└─xxx.mp3    		音频
│     
├─main.js               Vue初始化入口文件
│     
├─App.vue               应用配置，用来配置App全局样式以及监听 应用生命周期
│     
├─manifest.json         配置应用名称、appid、logo、版本等打包信息，详见
│     
└─pages.json            配置页面路由、导航条、选项卡等页面类信息，详见
```

#### 参与贡献

#### 项目创建人
tanyichen

## 结语
我们非常确定，`XF`将帮助移动端开发人员大幅提升开发效率。
希望您也能加入到`XF`程序的开发中来
技术交流QQ群：714566447
