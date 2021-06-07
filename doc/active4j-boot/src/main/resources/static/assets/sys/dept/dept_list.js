  layui.config({
     base: CXL.ctxPath + '/layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'table', 'treetable'], function(){
    var admin = layui.admin;
    var table = layui.table;
    var treetable = layui.treetable;
    var $ = layui.$;
    
    /**
     * 表格参数
     */
    var Page = {
        tableId: 'sys-dept-table',
    	url: CXL.ctxPath + '/sys/dept/datagrid',
    	toolbar:'#sys-dept-toolbar',
    	where:{field:'orderNo', order:'asc'},
    	initColumn: function() {
    		return [[
    			     {type: 'numbers'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'shortName', title: '部门简称', width:280}
    		        ,{field:'deptNo',title: '部门编号', width:120}
    		        ,{field:'type', title: '类型', width:90}
    		        ,{field:'orderNo', title: '排序', width:90}
    		        ,{field:'createDate', title: '创建时间', width:180, templet: '<div>{{ layui.laytpl.toDateString(d.createDate) }}</div>'}
    		        ,{fixed: 'right', title:'操作', toolbar: '#sys-dept-tool', width:170}
    			]];	 
    	},
    	addUrl: CXL.ctxPath + "/sys/dept/add"
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
    	 //查询字段
         var deptNo = $("#deptNo").val();
         var shortName = $("#shortName").val();
         
         generateTable({field:'orderNo', order:'asc', 'deptNo': deptNo, 'shortName' : shortName});
    }
    
    /**
     * 刷新表格
     */
    window.refresh = function() {
    	//查询字段
        var deptNo = $("#deptNo").val();
        var shortName = $("#shortName").val();
        
        generateTable({field:'orderNo', order:'asc', 'deptNo': deptNo, 'shortName' : shortName});
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
    		area: ['650px', '700px'],
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
    		area: ['650px', '700px'],
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
  	             url: CXL.ctxPath + '/sys/dept/delete',
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
     * 渲染表格的方法
     */
    var generateTable = function(where) {
    	 /**
         * 表格渲染
         */
        treetable.render({
           elem: '#' + Page.tableId
          ,url: Page.url
          ,height: 'full-140'
          ,treeColIndex: 2
          ,treeSpid: -1
          ,treeIdName: 'id'
          ,treePidName: 'parentId'
          ,page: false
          ,autoSort:false
          ,toolbar: Page.toolbar
          ,where: where
          ,defaultToolbar: []
         
          ,cols: Page.initColumn()
        });
    }
    
    //渲染表格
    generateTable(Page.where);
    
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
    
  });
  
  
