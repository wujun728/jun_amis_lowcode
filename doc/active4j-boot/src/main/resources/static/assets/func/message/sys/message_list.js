  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'table'], function(){
    var admin = layui.admin;
    var table = layui.table;
    var $ = layui.$;
    
    /**
     * 表格参数
     */
    var Page = {
        tableId: 'func-message-table',
    	url: CXL.ctxPath + '/func/message/sys/datagrid',
    	toolbar:'#func-message-toolbar',
    	where:{field:'createDate', order:'desc'},
    	initColumn: function() {
    		return [[
    			     {type: 'numbers'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'title', title: '消息标题', width:200}
    		        ,{field:'content',title: '内容', width:300}
    		        ,{field:'type',title: '消息类型', width:120}
    		        ,{field:'status',title: '消息状态', width:120, templet: '#sys-message-status'}
    		        ,{field:'publicTime',title: '发布时间', width:180, templet: '<div>{{ layui.laytpl.toDateStringNull(d.publicTime) }}</div>'}
    		        ,{fixed: 'right', title:'操作', toolbar: '#func-message-tool', width:170}
    			]];	 
    	},
    	addUrl: CXL.ctxPath + "/func/message/sys/add",
    	deleteUrl : CXL.ctxPath + "/func/message/sys/delete"
    	
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
         var queryData = {};
    	 //查询字段
         queryData['title'] = $("#title").val();
         queryData['type'] = $("#type").val();
         queryData['status'] = $("#status").val();
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
     * 新增
     */
    Page.openAdd = function () {
    	layer.open({
    		type : 2,
    		title : '新增',
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
    		area: ['750px', '450px'],
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
    		area: ['750px', '450px'],
    		content : CXL.ctxPath + "/func/message/sys/detail" + "?id=" + data.id
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
     * 发布
     */
    Page.doPublish = function() {
    	var id = $("#clickValue").val();
    	if(null == id || "" == id) {
    		CXL.warn("请选择消息");
    		return;
    	}
    	parent.layer.confirm('真的确定要发布该消息么', function(index){
      	  //提交 Ajax
	      $.ajax({
	             type: "post",
	             url: CXL.ctxPath + "/func/message/sys/publish?id=" + id,
	             success: function(res) {
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
      switch(obj.event){
        case 'doAdd':
        	Page.openAdd();break;
        case 'doPublish':
        	Page.doPublish();
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
    
  });
  
  
