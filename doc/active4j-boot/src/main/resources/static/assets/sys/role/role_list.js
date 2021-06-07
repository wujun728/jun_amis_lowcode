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
        tableId: 'sys-role-table',
    	url: CXL.ctxPath + '/sys/role/datagrid',
    	toolbar:'#sys-role-toolbar',
    	where:{field:'orderNo', order:'asc'},
    	initColumn: function() {
    		return [[
    			     {type: 'numbers'}
    		        ,{field:'id', title: 'ID',hide:true, width:80}
    		        ,{field:'name', title: '角色名称', width:260}
    		        ,{field:'roleNo', title: '角色编号', width:90}
    		        ,{field:'orderNo', title: '排序', width:90}
    		        ,{fixed: 'right', title:'操作', toolbar: '#sys-role-tool', width:270}
    			]];	 
    	},
    	addUrl: CXL.ctxPath + "/sys/role/add",
    	deleteUrl : CXL.ctxPath + "/sys/role/delete",
    };
    
    /**
     * 页面查询
     */
    Page.searchAction = function() {
    	 //查询字段
        var roleNo = $("#roleNo").val();
        var name = $("#name").val();
        
        generateTable({field:'orderNo', order:'asc', 'roleNo': roleNo, 'name' : name});
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
     * 刷新表格
     */
    window.refresh = function() {
    	 var roleNo = $("#roleNo").val();
         var name = $("#name").val();
         
         generateTable({field:'orderNo', order:'asc', 'roleNo': roleNo, 'name' : name});
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
      }else if(layEvent === 'permission') {
    	  Page.openPermission(data);
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
    
    /**************************************************************权限处理****************************************************************/
    
    /**
     * 点击设置权限
     */
    Page.openPermission = function(data) {
    	var roleId = data.id;
    	//赋值
    	$("#selectRoleId").val(roleId);
    	//请求后台数据
    	$.ajax({
    		type:"post",
    		url : CXL.ctxPath + '/common/getmenutree?roleId=' + roleId,
    		success:function(res) {
    			if(res.success) {
    				var treeData = res.attributes.data;
    				var df = eval(treeData);
    				$("#menuTree").treeview({
    					multiSelect : true
    					,showCheckbox :true
    					,data : df
    					//节点被选中
						,onNodeChecked: function(event, node) {
							//选中父节点展开
							if(node.state.checked){
								$("#menuTree").treeview('expandNode', [ node.nodeId, {silent: true } ]);
							}
							checkedChildren(node);
							var parent = $('#menuTree').treeview('getParent', node );
							//循环遍历
							while(parent){
								$("#menuTree").treeview('checkNode', [parent, { silent: true } ]);
								parent = $('#menuTree').treeview('getParent', parent );
							}
						},
						//节点被取消选中
						onNodeUnchecked: function(event, node) {
							unCheckedChildren(node);
							checkBrothers(node);
						},
						//节点被选择
						onNodeSelected : function(event, node) {
							doShowButtonsAction(node.id);
						}
    				});
    			}
    		}
    	})
    }
    
    
    //递归寻找子节点  选中
	function checkedChildren(node){
		if(node){
			//获得子节点
			var childrens = node.nodes;
			if(childrens) {
				//如果存在子节点，则全部勾选上
				for(var i = 0; i < childrens.length; i++) {
					var childNode = childrens[i];
					$("#menuTree").treeview('checkNode', [childNode.nodeId, { silent: true } ]);
					checkedChildren(childNode);
				}
			}
		}
	}
  		
	//递归寻找子节点   取消选中
	function unCheckedChildren(node) {
		if(node) {
			//获得子节点
			var childrens = node.nodes;
			if(childrens) {
				//如果存在子节点，则全部勾选上
				for(var i = 0; i < childrens.length; i++) {
					var childNode = childrens[i];
					$("#menuTree").treeview('uncheckNode', [childNode.nodeId, { silent: true } ]);
					unCheckedChildren(childNode);
				}
			}
		}
	}
  		
	//根据兄弟节点的选中状态，判断父节点的选中状态，递归调用方法
	function checkBrothers(node) {
		var brothers = $('#menuTree').treeview('getSiblings', node);
		if(brothers) {
			var isChecked = false;
			//如果存在兄弟节点
			for(var i = 0; i < brothers.length; i++) {
				var brother = brothers[i];
				if(brother.state.checked) {
					isChecked = true;
					break;
				};
			}
			if(!isChecked) {
				var parent = $('#menuTree').treeview('getParent', node );
				$("#menuTree").treeview('uncheckNode', [parent, { silent: true } ]);
				checkBrothers(parent);
			};
		};
	}
	
	//全选
	$('#selectAll').click(function () {
		$("#menuTree").treeview('checkAll', { silent: true });
    });
	
	//取消全选
	$("#unSelectAll").click(function () {
		$("#menuTree").treeview('uncheckAll', { silent: true });
	});
	
	$("#saveRoleMenu").click(function () {
		var roleId = $("#selectRoleId").val();
		if(null != roleId && "" != roleId) {
			//获得所有被选中的节点数组
			var nodes = $("#menuTree").treeview('getChecked');
			if(nodes) {
				//将被选中的节点ID，拼接成字符串传入后台
				var nodesIds = new Array();
				for(var i = 0; i < nodes.length; i++) {
					nodesIds.push(nodes[i].id);
				}
				var ids = nodesIds.join();
				//请求后台数据
		    	$.ajax({
		    		type:"post",
		    		url : CXL.ctxPath + '/sys/role/saverolemenu?',
		    		data: {roleId:roleId, roleMenuIds:ids},
		    		success:function(res) {
		    			if(res.success) {
							CXL.success(res.msg);
						}else{
							CXL.warn(res.msg);
						}
		    		}
		    	});
			}
		}
		
	});
	
  });
  
  
