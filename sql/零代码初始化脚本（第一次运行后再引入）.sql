drop table if exists my_form_def;
create table my_form_def
(
    id           varchar(36)            not null comment '主键ID'
        primary key,
    name         varchar(100)           null comment '表单名称',
    defination   text                   null comment '表单定义',
    ref_proc_key varchar(255)           null comment '关联流程',
    create_name  varchar(36)            null comment '创建者名称',
    create_by    varchar(64) default '' null comment '创建者',
    create_time  datetime               null comment '创建时间',
    update_time  datetime               null comment '更新时间'
)
    comment '表单定义';

drop table if exists my_workflow_formdata;
create table my_workflow_formdata
(
    id             varchar(36)            not null comment '主键ID'
        primary key,
    business_key   varchar(64)            null comment '事务key',
    task_node_name varchar(64)            null comment '任务节点名称',
    pass           varchar(10)            null comment '1同意、2不同意',
    comment        varchar(500)           null comment '批注',
    create_name    varchar(36)            null comment '创建者名称',
    create_by      varchar(64) default '' null comment '创建者',
    create_time    datetime               null comment '创建时间',
    update_time    datetime               null comment '更新时间'
)
    comment '审批记录表';

create table my_form_attr
(
    id          varchar(36)            not null comment '主键ID'
        primary key,
    form_id     varchar(100)           null comment '表单id',
    attr_name   varchar(255)           null comment '属性名称',
    attr_code   varchar(255)           null comment '属性code',
    is_show     int(1)      default 1  null comment '是否显示：1 是、0 否',
    show_order  int(11)     default 0  null comment '排序',
    create_name varchar(36)            null comment '创建者名称',
    create_by   varchar(64) default '' null comment '创建者',
    create_time datetime               null comment '创建时间',
    update_time datetime               null comment '更新时间'
)
    comment '表单属性';


INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2000, '表单中心', 0, 5, 'formcenter', null, null, 1, 0, 'M', '0', '0', null, 'form', 'admin', '2022-08-08 05:55:14', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2001, '流程中心', 0, 6, 'flowcenter', null, null, 1, 0, 'M', '0', '0', null, 'swagger', 'admin', '2022-08-08 05:55:56', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2002, '应用中心', 0, 7, 'appcenter', null, null, 1, 0, 'M', '0', '0', null, 'guide', 'admin', '2022-08-08 05:57:05', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2003, '数据中心', 0, 8, 'datacenter', null, null, 1, 0, 'M', '0', '0', null, 'dict', 'admin', '2022-08-08 05:57:38', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2004, '表单管理', 2000, 2, 'form-manage', 'formcenter/list/index', null, 1, 0, 'C', '0', '0', null, 'component', 'admin', '2022-08-08 06:01:26', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2005, '表单设计', 2000, 1, 'form-design', 'formcenter/design/index', null, 1, 0, 'C', '0', '0', null, 'edit', 'admin', '2022-08-08 06:29:08', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2006, '流程定义', 2001, 1, 'flow-define', 'flowcenter/definition/index', null, 1, 0, 'C', '0', '0', null, 'druid', 'admin', '2022-08-08 06:42:54', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2007, '应用列表', 2002, 1, 'app-list', 'appcenter/list/index', null, 1, 0, 'C', '0', '0', null, 'excel', 'admin', '2022-08-08 07:07:07', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2008, '代办任务', 2002, 2, 'app-todo', 'appcenter/todo/index', null, 1, 0, 'C', '0', '0', null, 'online', 'admin', '2022-08-08 07:08:18', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2009, '我发起的', 2002, 3, 'app-started', 'appcenter/started/index', null, 1, 0, 'C', '0', '0', null, 'guide', 'admin', '2022-08-08 07:08:54', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, path, component, query, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2010, '数据列表', 2003, 1, 'datacenter-list', 'datacenter/list/index', null, 1, 0, 'C', '0', '0', null, 'documentation', 'admin', '2022-08-08 07:37:36', '', null, '');


-- ----------------------------
-- 修复Activiti7的M4版本缺失字段Bug
-- ----------------------------
alter table ACT_RE_DEPLOYMENT
    add column PROJECT_RELEASE_VERSION_ varchar(255) DEFAULT NULL;
alter table ACT_RE_DEPLOYMENT
    add column VERSION_ varchar(255) DEFAULT NULL;

