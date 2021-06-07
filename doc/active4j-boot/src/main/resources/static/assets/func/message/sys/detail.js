  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
	  var form = layui.form;
	  var admin = layui.admin;
	  var $ = layui.$;
	    
  })