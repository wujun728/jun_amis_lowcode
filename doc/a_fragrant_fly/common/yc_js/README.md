nodejs工具插件合集
介绍
功能接口 
1，Time时间戳转换处理 
2，Arr 数组处理 
3，Base64 base64解码加密处理 
4，File 文件处理 
5，Id 唯一id生产 
6，Md5 MD5生成工具 
7，Storage 缓存处理 
8，Url url地址处理 
9，Validate 表单验证
10 area 全国地区表 为了压缩空间 目前只有省级

软件架构
软件架构说明

安装教程
[git 地址](https://gitee.com/wokaixin/yc.git)
npm 一键安装
npm install yc-js
使用说明
vue 全部中引入
import yc from 'yc-js';
或者按需引入
import {Time,Arr,Base64,File,Id,Md5,Storage,Url,Validate,area} from 'yc';
然后就可以使用了
比如 yc.Arr
yc.Time
 
具体都有哪些接口 可以打印yc查看

参与贡献
作者 yichen
交流uni-app+vue qq群714566447