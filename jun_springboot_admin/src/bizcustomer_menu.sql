-- 默认上级目录菜单为其他
INSERT INTO sys_permission (id, name, pid, url,target, type,order_num, deleted, status)
    VALUES ('1439380058992787457', 'bizCustomer' ,'21', '/index/bizCustomer','_self', '2', '10',1, 1);
-- 菜单对应按钮SQL
INSERT INTO sys_permission (id,pid, name, url, perms, type, deleted, status)
    VALUES ('1439380058992787461', '1439380058992787457', '列表' , 'bizCustomer/listByPage','bizCustomer:list', '3',1, 1);
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status)
    VALUES ('1439380058992787458', '1439380058992787457', '新增' , 'bizCustomer/add','bizCustomer:add', '3',1, 1);
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status)
    VALUES ('1439380058992787459', '1439380058992787457', '修改' , 'bizCustomer/update','bizCustomer:update', '3',1, 1);
INSERT INTO sys_permission (id,pid, name, url, perms,  type, deleted, status)
    VALUES ('1439380058992787460', '1439380058992787457', '删除' , 'bizCustomer/delete','bizCustomer:delete', '3',1, 1);
