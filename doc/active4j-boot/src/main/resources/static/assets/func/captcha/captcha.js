  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form'], function(){
	  var form = layui.form;
	  var admin = layui.admin;
	  var $ = layui.$;
	  
	  //改变验证码
	  window.change = function() {
		  document.getElementById("image").src = CXL.ctxPath + '/func/captcha/captchaImage?type=math';
	  }
	  
	  //监听提交
	  form.on('submit(form-btn-save)', function(data){
		  var field = data.field; //获取提交的字段
	      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
	      //提交 Ajax 成功后，关闭当前弹层并重载表格
	      $.ajax({
			  type: "post",
	          url: CXL.ctxPath + '/func/captcha/verify',
	          data: field,
	          success: function(res) {
	        	  if(res.success) {
	        		  CXL.success("验证成功");
		    	  }else {
		    		  CXL.warn("验证失败");
		    	  }
		      }
		  });
	  });
	  
	  //点击算数验证码
	  $('#mathBtn').click(function() {
		  document.getElementById("image").src = CXL.ctxPath + '/func/captcha/captchaImage?type=math';
	  });
	  
	  //点击字符验证码
	  $('#charBtn').click(function() {
		  document.getElementById("image").src = CXL.ctxPath + '/func/captcha/captchaImage?type=char';
	  });
	  
  })