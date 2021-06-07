  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
    ,tinymce: 'tinymce/tinymce'
  }).use(['index', 'form', 'tinymce'], function(){
	  var form = layui.form;
	  var admin = layui.admin;
	  var $ = layui.$;
	  var tinymce = layui.tinymce;

	  //建立编辑器
      var edit = tinymce.render({
          elem: "#edit"
          , height: 300
      });
      
      //控制台查看编辑器内容
      $('#btn').click(function() {
		  console.log('获得编辑器的内容：' + edit.getContent());
	  });
	  
  })