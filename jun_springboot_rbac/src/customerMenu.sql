-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户', '2015', '1', '/ow/customer', 'C', '0', 'ow:customer:view', '#', 'royal', '2021-09-25', 'royal', '2021-09-25', '客户菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户查询', @parentId, '1',  '#',  'F', '0', 'ow:customer:list',         '#', 'royal', '2021-09-25', 'royal', '2021-09-25', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户新增', @parentId, '2',  '#',  'F', '0', 'ow:customer:add',          '#', 'royal', '2021-09-25', 'royal', '2021-09-25', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户修改', @parentId, '3',  '#',  'F', '0', 'ow:customer:edit',         '#', 'royal', '2021-09-25', 'royal', '2021-09-25', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户删除', @parentId, '4',  '#',  'F', '0', 'ow:customer:remove',       '#', 'royal', '2021-09-25', 'royal', '2021-09-25', '');
