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
        tableId: 'sys-log-table',
    	url: CXL.ctxPath + '/sys/log/datagrid',
    	toolbar:'#sys-log-toolbar',
    	where:{field:'operatorTime', order:'desc'},
    	initColumn: function() {
    		return [[
    			     {type: 'numbers'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'userName', title: '用户名', width:120}
    		        ,{field:'name', title: '操作名称', width:120}
    		        ,{field:'type', title: '日志类型', width:90}
    		        ,{field:'operatorTime',title: '操作时间', width:180, templet: '<div>{{ layui.laytpl.toDateString(d.operatorTime) }}</div>'}
    		        ,{field:'broswer', title: '浏览器类型', width:100}
    		        ,{field:'ip', title: 'IP地址', width:100}
    		        ,{field:'className', title: '操作类名', width:140}
    		        ,{field:'methodName', title: '方法名', width:120}
    		        ,{field:'params', title: '参数', width:260}
    		        ,{field:'memo', title: '备注', width:120}
    			]];	 
    	},
    };
    
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
         var queryData = {};
    	 //查询字段
         queryData['userName'] = $("#userName").val();
         queryData['type'] = $("#type").val();
         queryData['operatorTime_begin'] = $("#timeBegin").val();
         queryData['operatorTime_end'] = $("#timeEnd").val();
         //排序字段
         queryData['field'] = 'operatorTime';
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
    	layer.open({
    		type : 2,
    		title : '新增',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['700px', '700px'],
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
    };
    
    /**
     * 编辑
     */
    Page.openEdit = function (data) {
    	layer.open({
    		type : 2,
    		title : '编辑',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['700px', '700px'],
    		content : Page.addUrl + "?id=" + data.id,
    		btn : [ '确定', '取消' ],
    		yes : function(index, layero) {
    			 //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#form-btn-save");
                submit.click();
                
                //刷新表格
                Page.searchAction();
    		}
    	});
    };
    
    /**
     * 查看
     */
    Page.openDetail = function(data) {
    	layer.open({
    		type : 2,
    		title : '查看',
    		shadeClose : true,
    		shade : 0.8,
    		area: ['650px', '700px'],
    		content : Page.addUrl + "?id=" + data.id
    	});
    }

    /**
     * 删除表格数据
     */
    Page.deleteAction = function(obj) {
    	parent.layer.confirm('真的确定要删除么', function(index){
    		
    	  //提交 Ajax进行后端删除
  	      $.ajax({
  	             type: "post",
  	             url: Page.deleteUrl,
  	             data: {id:obj.data.id},
  	             success: function(res) {
  		    		 if(res.success) {
  		    			 CXL.success(res.msg);
  		    			 //表格删除
  		    			 obj.del();
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
  
  
