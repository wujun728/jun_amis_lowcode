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
        tableId: 'monitor-online-table',
    	url: CXL.ctxPath + '/monitor/online/datagrid',
    	toolbar:'#monitor-online-toolbar',
    	where:{field:'createDate', order:'desc'},
    	initColumn: function() {
    		return [[
    			     {type: 'numbers'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'userName', title: '用户名', width:120}
    		        ,{field:'realName',title: '姓名', width:90}
    		        ,{field:'deptName',title: '所属部门', width:140}
    		        ,{field:'host',title: '登录IP', width:120}
    		        ,{field:'os',title: '浏览器', width:120}
    		        ,{field:'browser',title: '浏览器', width:120}
    		        ,{field:'status',title: '状态', width:80, templet:"#monitor-online-status"}
    		        ,{field:'createDate',title: '创建时间', width:180, templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>'}
    		        ,{field:'lastTime',title: '最后更新时间', width:180, templet: '<div>{{ layui.laytpl.toDateString(d.lastTime) }}</div>'}
    			]];	 
    	}
    	
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
         var queryData = {};
    	 //查询字段
         queryData['userName'] = $("#userName").val();
         queryData['status'] = $("#status").val();
         queryData['beginTime_begin'] = $("#timeBegin").val();
         queryData['beginTime_end'] = $("#timeEnd").val();
         
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
    	
    }
    
    /**
     * 表格的双击事件
     */
    Page.doubleClickAction = function(obj) {
    	
    }
    
    
    /**
     * 新增
     */
    Page.openAdd = function () {
    };
    
    /**
     * 编辑
     */
    Page.openEdit = function (data) {
    };
    
    /**
     * 查看
     */
    Page.openDetail = function(data) {
    }
    
    /**
     * 删除表格数据
     */
    Page.deleteAction = function(obj) {
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
        case 'doAdd':
        	Page.openAdd();
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
      //删除操作
      else if(layEvent === 'del'){
          Page.deleteAction(obj);
      } 
      //编辑操作
      else if(layEvent === 'edit'){
    	  Page.openEdit(data);
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
  
  
