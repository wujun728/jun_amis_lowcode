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
	  
	  layedit.set({
          //暴露layupload参数设置接口 --详细查看layupload参数说明
          uploadImage: {
              url: 'your url',
              accept: 'image',
              acceptMime: 'image/*',
              exts: 'jpg|png|gif|bmp|jpeg',
              size: '10240'
          }
          , uploadVideo: {
              url: 'your url',
              accept: 'video',
              acceptMime: 'video/*',
              exts: 'mp4|flv|avi|rm|rmvb',
              size: '20480'
          }
          //右键删除图片/视频时的回调参数，post到后台删除服务器文件等操作，
          //传递参数：
          //图片： imgpath --图片路径
          //视频： filepath --视频路径 imgpath --封面路径
          , calldel: {
              url: 'your url'
          }
          //开发者模式 --默认为false
          , devmode: true
          //插入代码设置
          , codeConfig: {
              hide: true,  //是否显示编码语言选择框
              default: 'javascript' //hide为true时的默认语言格式
          }
          , tool: [
              'code', 'strong', 'italic', 'underline', 'del', 'addhr', '|', 'fontFomatt', 'colorpicker', 'face'
              , '|', 'left', 'center', 'right', '|', 'link', 'unlink', 'images', 'image_alt', 'video', 'anchors'
              , '|', 'table', 'fullScreen'
          ]
          , height: '90%'
      });
	  
	  //建立编辑器
	  var ieditor = layedit.build('demo');
      
	  //控制台查看编辑器内容
	  $('#btn').click(function() {
		  console.log('获得编辑器的内容：' + layedit.getContent(ieditor) + '</br>'
				  + '获得编辑器的纯文本内容：' + layedit.getText(ieditor) + '</br>'
				  + '获取编辑器选中的文本：' + layedit.getSelection(ieditor));
	  });
	    
  })