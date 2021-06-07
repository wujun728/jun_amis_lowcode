  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'layedit', 'upload'], function(){
	  var form = layui.form;
	  var admin = layui.admin;
	  var $ = layui.$;
	  var layedit = layui.layedit;
	  var upload = layui.upload;
	  
	  //插入图片接口
	  layedit.set({
          //暴露layupload参数设置接口 --详细查看layupload参数说明
          uploadImage: {
              url: CXL.ctxPath + '/func/upload/qcloudupload' //接口url，这里使用了腾讯云上传方式
              ,type: 'post' //默认post
              ,accept: 'images' //允许上传的文件类型
              ,acceptMime: 'image/*'
              ,exts: 'jpg|png|gif|bmp|jpeg'	//允许上传的文件后缀。如果 accept 未设定，那么限制的就是图片的文件格式
              ,size: '5120' //设置文件最大可允许上传的大小，单位 KB。不支持ie8/9
          }
	  });
	
	  //注意：layedit.set 一定要放在 build 前面，否则配置全局接口将无效。
	  
	  //建立编辑器
	  var editIndex = layedit.build('content', {
		  				height: 180 //设置编辑器高度
					  });
	  
	  //监听提交
	  form.on('submit(set_system_email)', function(data){
		  
		  var mailbox = $("#mailbox").val();
		  var subject = $("#subject").val();
		  var content = layedit.getContent(editIndex);
		  
	      //提交 Ajax 成功后，关闭当前弹层并重载表格
	      $.ajax({
	             type: "post",
	             url: CXL.ctxPath + '/sys/email/send',
	             data: {mailbox:mailbox,subject:subject,content:content},
	             success: function(res) {
		    		 if(res.success) {
		    			 CXL.success(res.msg);
		    		 }else {
		    			 CXL.warn(res.msg);
		    		 }
		    	 }
	         });
	      
	     
     
	  });
	    
	    
  })