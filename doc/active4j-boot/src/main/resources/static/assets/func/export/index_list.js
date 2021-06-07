  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'table', 'upload'], function(){
    var admin = layui.admin;
    var table = layui.table;
    var $ = layui.$;
    var upload = layui.upload;
    
    /**
     * 表格参数
     */
    var Page = {
        tableId: 'func-export-table',
    	url: CXL.ctxPath + '/func/export/datagrid',
    	toolbar:'#func-export-toolbar',
    	where:{field:'createDate', order:'desc'},
    	initColumn: function() {
    		return [[
    			     {type: 'numbers'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'name', title: '姓名', width:120}
    		        ,{field:'sex', title: '性别', width:90}
    		        ,{field:'age', title: '年龄', width:90}
    		        ,{field:'phone', title: '手机号', width:160}
    		        ,{field:'birthday',title: '生日', width:130, templet: '<div>{{ layui.laytpl.toDateStringNull(d.birthday) }}</div>'}
    		        ,{field:'balance',title: '余额/元', width:120}
    			]];	 
    	}
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
         var queryData = {};
    	 //查询字段
         queryData['name'] = $("#name").val();
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
    
    //文件上传
    var uploadInst = upload.render({
	    elem: '#uploadBtn' //绑定元素
	    ,url: CXL.ctxPath + '/func/export/upload' //上传接口
	    ,size: 1024 //限定大小1M
	    ,accept:'file'
	    ,acceptMime:'application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
	    ,exts:"xls|xlsx"
	    ,before:function(obj) {
	    	var loading = layer.load(0, {
	    		shade: false,
	        });
	    }
	    ,done: function(res){
	        layer.closeAll('loading'); //关闭loading
	      //上传完毕回调
	      if(res.success) {
	    	  Page.searchAction();
	    	  CXL.success(res.msg);
	    	  //赋值数据
	    	  $("#fileInput").val(res.attributes.src);
	    	  //刷新表格
	      }else {
	    	  CXL.warn(res.msg);
	    	  $("#uploadBtn").html("重新上传");
	      }
	    }
	    ,error: function(){
	      layer.closeAll('loading'); //关闭loading
	      $("#uploadBtn").html("重新上传");
	    }
    })
    
    //导出xlx文件，这里不能用ajax请求，ajax请求无法弹出下载保存对话框
    Page.doXls = function() {
    	var name = $("#name").val();
    	location.href = CXL.ctxPath + '/func/export/xls?name=' + name; 
    }
    
    //导出xlxs文件，这里不能用ajax请求，ajax请求无法弹出下载保存对话框
    Page.doXlsx = function() {
    	var name = $("#name").val();
    	location.href = CXL.ctxPath + '/func/export/xlsx?name=' + name; 
    }
    
    //导出csv文件，这里不能用ajax请求，ajax请求无法弹出下载保存对话框
    Page.doCsv = function() {
    	var name = $("#name").val();
    	location.href = CXL.ctxPath + '/func/export/csv?name=' + name; 
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
    	 var checkStatus = table.checkStatus(obj.config.id);
         switch(obj.event){
           case 'xls':
        	   Page.doXls();
           break;
           case 'xlsx':
        	   Page.doXlsx();
           break;
           case 'csv':
               Page.doCsv();
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
  
  
