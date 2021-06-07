  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'table', 'laydate'], function(){
    var admin = layui.admin;
    var table = layui.table;
    var form = layui.form;
    var $ = layui.$;
    
    /**
     * 表格参数
     */
    var Page = {
        tableId: 'timer-job-table',
    	url: CXL.ctxPath + '/func/timer/job/datagrid',
    	toolbar:'#timer-job-toolbar',
    	where:{field:'createDate', order:'desc'},
    	initColumn: function() {
    		return [[
    				{type: 'numbers'}
    				,{type: 'checkbox'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'shortName', title: '任务简称', width:130}
    		        ,{field:'jobGroup',title: '任务分组', width:100}
    		        ,{field:'invokeParams',title: '调用参数', width:200}
    		        ,{field:'cronExpression',title: '执行表达式', width:120}
    		        ,{field:'jobStatus',title: '状态', width:100, templet: '#switchTpl', unresize: true}
    		        ,{field:'jobExecuteStatus',title: '执行状态', width:100, templet: '#timer-job-execute-status'}
    		        ,{field:'createDate',title: '创建时间', width:160, templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>'}
    		        ,{fixed: 'right', title:'操作', toolbar: '#timer-job-tool', width:180}
    			]];	 
    	},
    	addUrl: CXL.ctxPath + "/func/timer/job/add",
    	delUrl: CXL.ctxPath + "/func/timer/job/del",
    	editUrl: CXL.ctxPath + "/func/timer/job/edit",
    	detailUrl: CXL.ctxPath + "/func/timer/job/detail",
    	oneUrl: CXL.ctxPath + "/func/timer/job/one"
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
         var queryData = {};
    	 //查询字段
         queryData['shortName'] = $("#shortName").val();
         queryData['jobGroup'] = $("#jobGroup").val();
         queryData['jobStatus'] = $("#jobStatus").val();
         //排序字段
         queryData['field'] = 'createDate';
         queryData['order'] = 'desc';
         
         table.reload(Page.tableId, {where: queryData});
    }
    
    
    
    /**
     * 页面查询条件的重置
     */
    Page.resetAction = function() {
    	$(".layui-input").val('');
    	Page.searchAction();
    }
    
    
    /**
     * 表格的单击事件
     */
    Page.clickAction = function(obj) {
    	$("#clickValue").val(obj.data.id);
    }
    
    /**
     * 表格的双击事件
     */
    Page.doubleClickAction = function(obj) {
    	
    }
    
    /**
     * 监听任务状态操作
     */
    form.on('switch(doRun)', function(obj) {
    	//开关是否开启，true或者false
    	var checked = obj.elem.checked;
    	//如果是启用状态
    	if(checked) {
    		layer.open({
			  content: '确定要启用任务吗？'
			  ,btn: ['确定', '取消']
			  ,yes: function(index, layero) {
			  	var loading = layer.load(0, {
                    shade: false,
                });
    			//请求后端
    			$.ajax({
     	             type: "post",
     	             url: CXL.ctxPath + "/func/timer/job/start",
     	             data: {id:obj.value},
     	             success: function(res) {
     	            	layer.close(loading);
     		    		 if(res.success) {
     		    			 CXL.success(res.msg);
     		    			 //刷新表格
    		                 Page.searchAction();
     		    		 }else {
     		    			 CXL.warn(res.msg);
     		    			 obj.elem.checked = !checked;
     		    			 //重载表单
     		    			 form.render();
     		    		 }
     		    	 }
     	         });
    			 layer.close(index);
			  }
			  ,btn2: function(index, layero) {
				 // 按钮2的事件
				 obj.elem.checked = !checked;
	             form.render();
	             layer.close(index);
			  }
			  ,cancel: function(index, layero) { 
				 //右上角关闭回调
				 obj.elem.checked = !checked;
	             form.render();
	             layer.close(index);
			  }
    		});
    	}else {
    		layer.open({
  			  content: '确定要暂停任务吗？'
  			  ,btn: ['确定', '取消']
  			  ,yes: function(index, layero) {
  				var loading = layer.load(0, {
                    shade: false,
                });
        		//请求后端
        		$.ajax({
    	             type: "post",
    	             url: CXL.ctxPath + "/func/timer/job/pause",
    	             data: {id:obj.value},
    	             success: function(res) {
    	            	layer.close(loading);
    		    		 if(res.success) {
    		    			 CXL.success(res.msg);
    		    			 //刷新表格
    		                 Page.searchAction();
    		    		 }else {
    		    			 CXL.warn(res.msg);
    		    			 obj.elem.checked = !checked;
    		    			 //重载表单
     		    			 form.render();
    		    		 }
    		    	 }
    	         });
        		 layer.close(index);
  			  }
  			  ,btn2: function(index, layero) {
  				 // 按钮2的事件
  				 obj.elem.checked = !checked;
                 form.render();
                 layer.close(index);
  			  }
  			  ,cancel: function(index, layero) { 
  				 //右上角关闭回调
  				 obj.elem.checked = !checked;
                 form.render();
                 layer.close(index);
  			  }
    		});
    	}
    });
    
    
    /**
     * 新增
     */
    Page.openAdd = function () {
    	layer.open({
    		type : 2,
    		title : '新增任务',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['750px', '450px'],
    		content : Page.addUrl,
    		btn : [ '确定', '取消' ],
    		yes : function(index, layero) {
    			 //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#form-btn-save");
                submit.click();
                
                //刷新表格
                Page.searchAction();
    		}
    	});
    }
    
    /**
     * 编辑
     */
    Page.openEdit = function(data) {
    	layer.open({
    		type : 2,
    		title : '编辑任务',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['750px', '450px'],
    		content : Page.editUrl + "?id=" + data.id,
    		btn : [ '确定', '取消' ],
    		yes : function(index, layero) {
    			 //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#form-btn-save");
                submit.click();
                
                //刷新表格
                Page.searchAction();
    		}
    	});
    }
    
    /**
     * 查看明细
     */
    Page.openDetail = function(data) {
    	layer.open({
    		type : 2,
    		title : '查看任务',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['750px', '450px'],
    		content : Page.detailUrl + "?id=" + data.id
    	});
    }
    
    /**
     * 执行一次
     */
    Page.doOne = function(data) {
    	parent.layer.confirm('真的确定要立即执行一次任务么', function(index){
    		var loading = layer.load(0, {
                shade: false,
            });
    		
    	  //提交 Ajax进行后端删除
  	      $.ajax({
  	             type: "post",
  	             url: Page.oneUrl,
  	             data: {id:data.id},
  	             success: function(res) {
  	            	layer.close(loading);
  		    		 if(res.success) {
  		    			 CXL.success(res.msg);
  		    			//刷新表格
  		                Page.searchAction();
  		    		 }else {
  		    			 CXL.warn(res.msg);
  		    		 }
  		    	 }
  	         });
        	
          layer.close(index);
        });
    }
    
    /**
     * 删除
     */
    Page.doDel = function(data) {
    	if(data.length === 0) {
    		  CXL.warn('请选择一条数据');
    		  return;
          }else {
            var ids=[];
            for (var i = 0; i < data.length; i++) {
                ids += data[i].id + ',';
          }
            ids = ids.substr(0, ids.length-1);
        }
    	//弹出确认提示
    	parent.layer.confirm('真的确定要删除选中的' + data.length + '条数据么，此操作会忽略正在执行的任务', function(index) {
    		var loading = layer.load(0, {
                shade: false,
            });
    		
    	  //提交 Ajax进行后端删除
  	      $.ajax({
  	             type: "post",
  	             url: Page.delUrl,
  	             data: {ids:ids},
  	             success: function(res) {
  	            	layer.close(loading);
  		    		 if(res.success) {
  		    			 CXL.success(res.msg);
  		    			//刷新表格
  		                Page.searchAction();
  		    		 }else {
  		    			 CXL.warn(res.msg);
  		    		 }
  		    	 }
  	         });
        	
          layer.close(index);
        });
    }
    
    /**
     * 跳转日志
     */
    Page.openLog = function() {
    	var url = CXL.ctxPath + '/func/timer/joblog/list';
		top.layui.index.openTabsPage(url, "定时任务日志");  //完成页面跳转
    }
    
    /**
     * 表格渲染
     */
    table.render({
       elem: '#' + Page.tableId
      ,url: Page.url
      ,height: 'full-140'
      ,page: true
      ,limit : 30
      ,autoSort:false
      ,toolbar: Page.toolbar
      ,where: Page.where
      ,defaultToolbar: []
     
      ,cols: Page.initColumn()
    });
    
    /**
     * 表头工具栏
     */
    table.on('toolbar(' + Page.tableId + ')', function(obj){
      var checkStatus = table.checkStatus(obj.config.id)
      ,data = checkStatus.data; //获取选中的数据
      switch(obj.event){
	      case 'add':
	    	  Page.openAdd();
	      break;
	      case 'del':
	    	  Page.doDel(data);
	      break;
	      case 'log':
	    	  Page.openLog();
	      break;
      };
    });
    
    /**
     * 监听表格工具事件
     */
    table.on('tool(' + Page.tableId + ')', function(obj){ 
       //获得当前行数据
      var data = obj.data;
       //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
      var layEvent = obj.event; 
     
      //查看操作
      if(layEvent === 'detail'){
    	  Page.openDetail(data);
      } 
      //编辑操作
      else if(layEvent === 'edit'){
          Page.openEdit(data);
      }
      //执行一次操作
      else if(layEvent === 'one'){
          Page.doOne(data);
      }
    });
    
    /**
     * 搜索按钮事件
     */
    $('#btnSearch').click(function () {
    	Page.searchAction();
    });
    
    /**
     * 搜索按钮事件
     */
    $('#btnReset').click(function () {
    	Page.resetAction();
    });
    
    //监听行单击事件
    table.on('row(' + Page.tableId + ')', function(obj){
       Page.clickAction(obj);
    });
     
    //监听行双击事件
    table.on('rowDouble(' + Page.tableId + ')', function(obj){
    	Page.doubleClickAction(obj);
    });
    
  });
  
  
