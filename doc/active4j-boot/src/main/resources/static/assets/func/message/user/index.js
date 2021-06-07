  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'table', 'util'], function(){
	  var admin = layui.admin;
	  var table = layui.table;
	  var $ = layui.$;
	  
	  //区分各选项卡中的表格
	  var tabs = {
	    all: {
	      text: '全部消息'
	      ,id: 'LAY-app-message-all'
	    }
	  };
	  
	  
	  //标题内容模板
	  var tplTitle = function(d){
		  if('0' == d.readStatus) {
			  return '<a lay-href="' + CXL.ctxPath + '/func/message/user/detail?id=' + d.id + '" lay-text="消息详情">' + "<strong>" + d.title + '</strong>';
		  }else {
			  return '<a lay-href="' + CXL.ctxPath + '/func/message/user/detail?id=' + d.id + '" lay-text="消息详情">' + d.title;
		  }
	  };
	  
	  var tplType = function(d) {
		  return '【' + d.type + "】";
	  }
	  
	  //全部消息
	  table.render({
	    elem: '#LAY-app-message-all'
	    ,url: CXL.ctxPath + '/func/message/user/datagrid'
	    ,page: true
	    ,where:{field:'publicTime', order:'desc'}
	    ,limit : 20
	    ,height: 'full-240'
	    ,cols: [[
	      {type:'numbers'}
	      ,{type: 'checkbox'}
	      ,{field: 'title', title: '标题内容', minWidth: 300, templet: tplTitle}
	      ,{field: 'type', title: '消息类型', width: 200, templet: tplType}
	      ,{field: 'publicTime', title: '消息时间', width: 170, templet: '<div>{{ layui.util.timeAgo(d.publicTime) }}</div>'}
	    ]]
	    ,skin: 'line'
	  });
	  
	  //事件处理
	  var events = {
	    del: function(othis, type){
	      var thisTabs = tabs[type]
	      ,checkStatus = table.checkStatus(thisTabs.id)
	      ,data = checkStatus.data; //获得选中的数据
	      if(data.length === 0) return CXL.warn('请先选择相关数据');

	      layer.confirm('确定删除选中的数据吗？', function(index) {
	    	//获取ids
	    	var ids = CXL.getTextByJs(data);
	    	//请求删除
	    	$.ajax({
	    		type: "post",
 	            url: CXL.ctxPath + '/func/message/user/delete',
 	            data: {ids:ids},
 	            success: function(res) {
 		    		if(res.success) {
 		    			CXL.success("删除成功");
 		    			//表格刷新
 		    			table.reload(thisTabs.id); //刷新表格
 		    		}else {
 		    			CXL.warn(res.msg);
 		    		}
 		    	}
 	        });
	    	
	    	layer.close(index);
	      });
	    }
	    ,ready: function(othis, type){
	    	var thisTabs = tabs[type]
	    	,checkStatus = table.checkStatus(thisTabs.id)
	    	,data = checkStatus.data; //获得选中的数据
	    	if(data.length === 0) return CXL.warn('请先选择相关数据');
	      	
      	  	var ids = CXL.getTextByJs(data);
      	  	$.ajax({
	    		type: "post",
	            url: CXL.ctxPath + '/func/message/user/read',
	            data: {ids:ids},
	            success: function(res) {
		    		if(res.success) {
		    			CXL.success("标记已读成功");
		    			//表格刷新
		    			table.reload(thisTabs.id); //刷新表格
		    		}else {
		    			CXL.warn(res.msg);
		    		}
		    	}
	        });
	    }
	    ,readyAll: function(othis, type){
	      var thisTabs = tabs[type];
	      
	      layer.confirm('确定要将所有的系统消息标记为已读吗？', function(index) {
	    	  $.ajax({
	    		  type: "post",
		          url: CXL.ctxPath + '/func/message/user/readall',
		          success: function(res) {
		        	  if(res.success) {
		        		  CXL.success("标记全部已读成功");
			    		  //表格刷新
			    		  table.reload(thisTabs.id); //刷新表格
		        	  }else {
		        		  CXL.warn(res.msg);
			    	  }
			      }
		      });
	    	  
	    	  layer.close(index);
	       });
	    }
	  };
	  
	  $('.LAY-app-message-btns .layui-btn').on('click', function(){
	    var othis = $(this)
	    ,thisEvent = othis.data('events')
	    ,type = othis.data('type');
	    events[thisEvent] && events[thisEvent].call(this, othis, type);
	  });
	    
  })