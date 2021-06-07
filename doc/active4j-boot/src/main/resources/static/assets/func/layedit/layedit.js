  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'upload', 'layedit'], function(){
	  var form = layui.form;
	  var admin = layui.admin;
	  var $ = layui.$;
	  var layedit = layui.layedit;
	  var upload = layui.upload;
	  
	  //插入图片接口
	  layedit.set({
          //暴露layupload参数设置接口 --详细查看layupload参数说明
          uploadImage: {
              url: 'your url' //接口url
              ,type: 'post' //默认post
              ,accept: 'images' //允许上传的文件类型
              ,acceptMime: 'image/*'
              ,exts: 'jpg|png|gif|bmp|jpeg'	//允许上传的文件后缀。如果 accept 未设定，那么限制的就是图片的文件格式
              ,size: '5120' //设置文件最大可允许上传的大小，单位 KB。不支持ie8/9
          }
	  });
	
	  //注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
	  //建立编辑器
	  var editIndex = layedit.build('demo', {
		  				height: 180 //设置编辑器高度
					  });
	  
	  //表单自定义校验，将富文本内容同步到textarea以便校验
	  form.verify({
		  demo: function() {
			  //用于同步编辑器内容到textarea（一般用于异步提交）
			  layedit.sync(editIndex);
		  }
	  });
	  
	  //控制台查看编辑器内容
	  $('#btn').click(function() {
		  console.log('获得编辑器的内容：' + layedit.getContent(editIndex) + '</br>'
				  + '获得编辑器的纯文本内容：' + layedit.getText(editIndex) + '</br>'
				  + '获取编辑器选中的文本：' + layedit.getSelection(editIndex));
	  });
	    
  })