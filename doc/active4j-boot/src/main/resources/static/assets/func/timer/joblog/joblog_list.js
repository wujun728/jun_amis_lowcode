  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'table', 'laydate'], function(){
    var admin = layui.admin;
    var table = layui.table;
    var laydate = layui.laydate;
    var $ = layui.$;
    
    /**
     * 表格参数
     */
    var Page = {
        tableId: 'timer-job-log-table',
    	url: CXL.ctxPath + '/func/timer/joblog/datagrid',
    	toolbar:'#timer-job-log-toolbar',
    	where:{field:'startTime', order:'desc'},
    	initColumn: function() {
    		return [[
    				{type: 'numbers'}
    				,{type: 'checkbox'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'shortName', title: '任务简称', width:130}
    		        ,{field:'jobGroup',title: '任务分组', width:100}
    		        ,{field:'invokeParams',title: '调用参数', width:200}
    		        ,{field:'jobMessage',title: '日志信息', width:200}
    		        ,{field:'status',title: '状态', width:80, templet: '#timer-job-log-status'}
    		        ,{field:'startTime',title: '执行时间', width:160, templet: '<div>{{ layui.laytpl.toDateString(d.startTime) }}</div>'}
    		        ,{fixed: 'right', title:'操作', toolbar: '#timer-job-log-tool', width:100}
    			]];	 
    	},
    	delUrl: CXL.ctxPath + "/func/timer/joblog/del",
    	emptyUrl: CXL.ctxPath + "/func/timer/joblog/empty",
    	detailUrl: CXL.ctxPath + "/func/timer/joblog/detail"
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
         var queryData = {};
    	 //查询字段
         queryData['shortName'] = $("#shortName").val();
         queryData['jobGroup'] = $("#jobGroup").val();
         queryData['status'] = $("#status").val();
         queryData['startTime_begin'] = $("#timeBegin").val();
         queryData['startTime_end'] = $("#timeEnd").val();
         //排序字段
         queryData['field'] = 'startTime';
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
     * 查看明细
     */
    Page.openDetail = function(data) {
    	layer.open({
    		type : 2,
    		title : '查看日志',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['750px', '450px'],
    		content : Page.detailUrl + "?id=" + data.id
    	});
    }
    
    /**
     * 删除
     */
    Page.doDel = function(obj, num) {
    	parent.layer.confirm('真的确定要删除选中的' + num + '条数据么', function(index) {
    		var loading = layer.load(0, {
                shade: false,
            });
    		
    	  //提交 Ajax进行后端删除
  	      $.ajax({
  	             type: "post",
  	             url: Page.delUrl,
  	             data: {ids:obj},
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
     * 清空
     */
    Page.doEmpty = function() {
    	parent.layer.confirm('真的确定要清空所有的任务日志么', function(index) {
    		var loading = layer.load(0, {
                shade: false,
            });
    		
    	  //提交 Ajax进行后端删除
  	      $.ajax({
  	             type: "post",
  	             url: Page.emptyUrl,
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
	    	  if(data.length === 0) {
	    		  CXL.warn('请选择一条任务');
	          }else {
	            var ids=[];
	            for (var i = 0; i < data.length; i++) {
	                ids += data[i].id + ',';
	            }
	            ids = ids.substr(0, ids.length-1);
	            Page.doDel(ids, data.length);
	          }
	      break;
	      case 'empty':
	    	  Page.doEmpty();
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
    
    /**
     * 时间选择器渲染
     * 开始时间
     */
  	laydate.render({
  		elem: '#timeBegin' //指定元素
  		,type: 'datetime'
  	});
  	
  	/**
     * 时间选择器渲染
     * 结束时间
     */
  	laydate.render({
  		elem: '#timeEnd' //指定元素
  		,type: 'datetime'
  	});
    
  });
  
  
