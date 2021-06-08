/*
Navicat PGSQL Data Transfer

Source Server         : postgres local
Source Server Version : 90605
Source Host           : 127.0.0.1:5432
Source Database       : ifast
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90605
File Encoding         : 65001

Date: 2019-01-02 11:56:56
*/


-- ----------------------------
-- Sequence structure for sys_config_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_config_id_seq";
CREATE SEQUENCE "public"."sys_config_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_dept_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_dept_id_seq";
CREATE SEQUENCE "public"."sys_dept_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_dict_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_dict_id_seq";
CREATE SEQUENCE "public"."sys_dict_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_file_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_file_id_seq";
CREATE SEQUENCE "public"."sys_file_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_log_id_seq";
CREATE SEQUENCE "public"."sys_log_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_menu_id_seq";
CREATE SEQUENCE "public"."sys_menu_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_role_id_seq";
CREATE SEQUENCE "public"."sys_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_role_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_role_menu_id_seq";
CREATE SEQUENCE "public"."sys_role_menu_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_task_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_task_id_seq";
CREATE SEQUENCE "public"."sys_task_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_user_id_seq";
CREATE SEQUENCE "public"."sys_user_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for sys_user_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sys_user_role_id_seq";
CREATE SEQUENCE "public"."sys_user_role_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for wx_mp_config_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."wx_mp_config_id_seq";
CREATE SEQUENCE "public"."wx_mp_config_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for wx_mp_fans_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."wx_mp_fans_id_seq";
CREATE SEQUENCE "public"."wx_mp_fans_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for wx_mp_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."wx_mp_menu_id_seq";
CREATE SEQUENCE "public"."wx_mp_menu_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for wx_mp_wechat_keys_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."wx_mp_wechat_keys_id_seq";
CREATE SEQUENCE "public"."wx_mp_wechat_keys_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for app_demo_base
-- ----------------------------
DROP TABLE IF EXISTS "public"."app_demo_base";
CREATE TABLE "public"."app_demo_base" (
"id" int8 NOT NULL,
"title" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"publish" timestamp(6),
"content" text COLLATE "default",
"deleted" bool DEFAULT false,
"version" int8 DEFAULT '0'::bigint,
"create_at" timestamp(6) DEFAULT now(),
"update_at" timestamp(6) DEFAULT now(),
"create_by" int8,
"update_by" int8,
"price" numeric(10) DEFAULT NULL::numeric
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of app_demo_base
-- ----------------------------
INSERT INTO "public"."app_demo_base" VALUES ('1027118616500408321', 'Vue 2.0 Hello World', '2018-08-17 11:59:00', '这是我的征文', 'f', '7', '2018-08-08 17:05:35', '2018-08-22 15:34:46', '1', '1', null);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_config";
CREATE TABLE "public"."sys_config" (
"id" int8 DEFAULT nextval('sys_config_id_seq'::regclass) NOT NULL,
"k" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"v" varchar(1000) COLLATE "default" DEFAULT NULL::character varying,
"remark" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"create_time" timestamp(6),
"kv_type" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO "public"."sys_config" VALUES ('2', 'oss_qiniu', '{\"AccessKey\" : \"8-HMj9EgGNIP-xuOCpSzTn-OMyGOFtR3TxLdn4Uu\",\"SecretKey\" : \"SjpGg3V6PsMdJgn42PeEd5Ik-6aNyuwdqV5CPM6A\",\"bucket\" : \"ifast\",\"AccessUrl\" : \"http://p6r7ke2jc.bkt.clouddn.com/\"}', '七牛对象存储配置', '2018-04-06 14:31:26', '4300');
INSERT INTO "public"."sys_config" VALUES ('3', 'author', 'Aron', '代码生成器配置', '2018-05-27 19:57:04', '4401');
INSERT INTO "public"."sys_config" VALUES ('4', 'email', 'izenglong@163.com', '代码生成器配置', '2018-05-27 19:57:04', '4401');
INSERT INTO "public"."sys_config" VALUES ('5', 'package', 'com.ifast', '代码生成器配置', '2018-05-27 19:57:04', '4401');
INSERT INTO "public"."sys_config" VALUES ('6', 'autoRemovePre', 'true', '代码生成器配置', '2018-05-27 19:57:04', '4401');
INSERT INTO "public"."sys_config" VALUES ('7', 'tablePrefix', 'app', '代码生成器配置', '2018-05-27 19:57:04', '4401');
INSERT INTO "public"."sys_config" VALUES ('8', 'tinyint', 'Integer', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('9', 'smallint', 'Integer', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('10', 'mediumint', 'Integer', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('11', 'int', 'Integer', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('12', 'integer', 'Integer', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('13', 'bigint', 'Long', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('14', 'float', 'Float', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('15', 'double', 'Double', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('16', 'decimal', 'BigDecimal', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('17', 'bit', 'Boolean', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('18', 'char', 'String', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('19', 'varchar', 'String', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('20', 'tinytext', 'String', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('21', 'text', 'String', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('22', ' text', 'String', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('23', 'longtext', 'String', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('24', 'date', 'Date', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('25', ' TIMESTAMP', 'Date', '代码生成器配置', '2018-05-27 19:57:04', '4400');
INSERT INTO "public"."sys_config" VALUES ('26', 'timestamp', 'Date', '代码生成器配置', '2018-05-27 19:57:04', '4400');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_dept";
CREATE TABLE "public"."sys_dept" (
"id" int8 DEFAULT nextval('sys_dept_id_seq'::regclass) NOT NULL,
"parent_id" int8,
"name" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"order_num" int8,
"del_flag" int8 DEFAULT '0'::bigint
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO "public"."sys_dept" VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO "public"."sys_dept" VALUES ('7', '6', '研发一部', '1', '1');
INSERT INTO "public"."sys_dept" VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO "public"."sys_dept" VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO "public"."sys_dept" VALUES ('11', '0', '产品部', '3', '1');
INSERT INTO "public"."sys_dept" VALUES ('12', '11', '产品一部', '1', '1');
INSERT INTO "public"."sys_dept" VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO "public"."sys_dept" VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO "public"."sys_dept" VALUES ('15', '13', '测试二部', '2', '1');
INSERT INTO "public"."sys_dept" VALUES ('16', '9', '销售一部', '0', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_dict";
CREATE TABLE "public"."sys_dict" (
"id" int8 DEFAULT nextval('sys_dict_id_seq'::regclass) NOT NULL,
"name" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"value" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"type" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"description" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"sort" numeric(10) DEFAULT NULL::numeric,
"parent_id" int8 DEFAULT '0'::bigint,
"create_by" int8,
"create_date" timestamp(6),
"update_by" int8,
"update_date" timestamp(6),
"remarks" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"del_flag" char(1) COLLATE "default" DEFAULT '0'::bpchar
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO "public"."sys_dict" VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类\0\0', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO "public"."sys_dict" VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO "public"."sys_dict" VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO "public"."sys_dict" VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', ' ');
INSERT INTO "public"."sys_dict" VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/blog/open/page/about', ' ');
INSERT INTO "public"."sys_dict" VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', ' ');
INSERT INTO "public"."sys_dict" VALUES ('120', '文章', 'article', 'blog_type', '博客类型', null, null, null, null, null, null, '', ' ');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_file";
CREATE TABLE "public"."sys_file" (
"id" int8 DEFAULT nextval('sys_file_id_seq'::regclass) NOT NULL,
"type" int8,
"url" varchar(200) COLLATE "default" DEFAULT NULL::character varying,
"create_date" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO "public"."sys_file" VALUES ('1080274794512949250', '0', 'http://localhost:8088/upload/20190102\b\c\logo.jpeg', '2019-01-02 09:29:15.847');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_log";
CREATE TABLE "public"."sys_log" (
"id" int8 DEFAULT nextval('sys_log_id_seq'::regclass) NOT NULL,
"user_id" int8,
"username" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"operation" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"time" int8,
"method" varchar(200) COLLATE "default" DEFAULT NULL::character varying,
"params" varchar(5000) COLLATE "default" DEFAULT NULL::character varying,
"ip" varchar(64) COLLATE "default" DEFAULT NULL::character varying,
"gmt_create" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO "public"."sys_log" VALUES ('1078908533854019586', '1', 'admin', '登录', '79', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 15:00:13.917');
INSERT INTO "public"."sys_log" VALUES ('1078917211520868354', '1', 'admin', '登录', '22', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 15:34:43.194');
INSERT INTO "public"."sys_log" VALUES ('1078917591575072769', '1', 'admin', '登录', '83', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 15:36:13.406');
INSERT INTO "public"."sys_log" VALUES ('1078918245672566785', '1', 'admin', '登录', '77', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 15:38:49.408');
INSERT INTO "public"."sys_log" VALUES ('1078925313431310337', '1', 'admin', '登录', '79', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 16:06:54.491');
INSERT INTO "public"."sys_log" VALUES ('1078925638087217153', '1', 'admin', '登录', '13', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 16:08:12.245');
INSERT INTO "public"."sys_log" VALUES ('1078936683862323201', '1', 'admin', '登录', '2281', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2018-12-29 16:52:03.77');
INSERT INTO "public"."sys_log" VALUES ('1080264866356342786', '1', 'admin', '登录', '175', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2019-01-02 08:49:48.798');
INSERT INTO "public"."sys_log" VALUES ('1080274794663944193', '1', 'admin', '上传文件', '15665', 'POST /common/sysFile/upload', '{}', '127.0.0.1', '2019-01-02 09:29:16.235');
INSERT INTO "public"."sys_log" VALUES ('1080307351002550274', '1', 'admin', '登录', '32', 'POST /login', '{"username":["admin"],"password":["1"]}', '127.0.0.1', '2019-01-02 11:38:37.95');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_menu";
CREATE TABLE "public"."sys_menu" (
"id" int8 DEFAULT nextval('sys_menu_id_seq'::regclass) NOT NULL,
"parent_id" int8,
"name" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"url" varchar(200) COLLATE "default" DEFAULT NULL::character varying,
"perms" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"type" int8,
"icon" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"order_num" int8,
"gmt_create" timestamp(6),
"gmt_modified" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO "public"."sys_menu" VALUES ('1', '0', '基础管理', '', '', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', null);
INSERT INTO "public"."sys_menu" VALUES ('2', '3', '系统菜单', 'sys/menu/', 'sys:menu:menu', '1', 'fa fa-th-list', '2', '2017-08-09 22:55:15', null);
INSERT INTO "public"."sys_menu" VALUES ('3', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO "public"."sys_menu" VALUES ('6', '3', '用户管理', 'sys/user/', 'sys:user:user', '1', 'fa fa-user', '0', '2017-08-10 14:12:11', null);
INSERT INTO "public"."sys_menu" VALUES ('7', '3', '角色管理', 'sys/role', 'sys:role:role', '1', 'fa fa-paw', '1', '2017-08-10 14:13:19', null);
INSERT INTO "public"."sys_menu" VALUES ('12', '6', '新增', '', 'sys:user:add', '2', '', '0', '2017-08-14 10:51:35', null);
INSERT INTO "public"."sys_menu" VALUES ('13', '6', '编辑', '', 'sys:user:edit', '2', '', '0', '2017-08-14 10:52:06', null);
INSERT INTO "public"."sys_menu" VALUES ('14', '6', '删除', null, 'sys:user:remove', '2', null, '0', '2017-08-14 10:52:24', null);
INSERT INTO "public"."sys_menu" VALUES ('15', '7', '新增', '', 'sys:role:add', '2', '', '0', '2017-08-14 10:56:37', null);
INSERT INTO "public"."sys_menu" VALUES ('20', '2', '新增', '', 'sys:menu:add', '2', '', '0', '2017-08-14 10:59:32', null);
INSERT INTO "public"."sys_menu" VALUES ('21', '2', '编辑', '', 'sys:menu:edit', '2', '', '0', '2017-08-14 10:59:56', null);
INSERT INTO "public"."sys_menu" VALUES ('22', '2', '删除', '', 'sys:menu:remove', '2', '', '0', '2017-08-14 11:00:26', null);
INSERT INTO "public"."sys_menu" VALUES ('24', '6', '批量删除', '', 'sys:user:batchRemove', '2', '', '0', '2017-08-14 17:27:18', null);
INSERT INTO "public"."sys_menu" VALUES ('25', '6', '停用', null, 'sys:user:disable', '2', null, '0', '2017-08-14 17:27:43', null);
INSERT INTO "public"."sys_menu" VALUES ('26', '6', '重置密码', '', 'sys:user:resetPwd', '2', '', '0', '2017-08-14 17:28:34', null);
INSERT INTO "public"."sys_menu" VALUES ('27', '91', '系统日志', 'common/log', 'common:log', '1', 'fa fa-warning', '0', '2017-08-14 22:11:53', null);
INSERT INTO "public"."sys_menu" VALUES ('28', '27', '刷新', null, 'sys:log:list', '2', null, '0', '2017-08-14 22:30:22', null);
INSERT INTO "public"."sys_menu" VALUES ('29', '27', '删除', null, 'sys:log:remove', '2', null, '0', '2017-08-14 22:30:43', null);
INSERT INTO "public"."sys_menu" VALUES ('30', '27', '清空', null, 'sys:log:clear', '2', null, '0', '2017-08-14 22:31:02', null);
INSERT INTO "public"."sys_menu" VALUES ('48', '77', '代码生成', 'common/generator', 'common:generator', '1', 'fa fa-code', '3', null, null);
INSERT INTO "public"."sys_menu" VALUES ('55', '7', '编辑', '', 'sys:role:edit', '2', '', null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('56', '7', '删除', '', 'sys:role:remove', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('57', '91', '运行监控', '/druid/index.html', '', '1', 'fa fa-caret-square-o-right', '1', null, null);
INSERT INTO "public"."sys_menu" VALUES ('61', '2', '批量删除', '', 'sys:menu:batchRemove', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('62', '7', '批量删除', '', 'sys:role:batchRemove', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('71', '1', '文件管理', '/common/sysFile', 'oss:file:file', '1', 'fa fa-folder-open', '2', null, null);
INSERT INTO "public"."sys_menu" VALUES ('72', '77', '计划任务', 'common/job', 'common:taskScheduleJob', '1', 'fa fa-hourglass-1', '4', null, null);
INSERT INTO "public"."sys_menu" VALUES ('73', '3', '部门管理', '/sys/dept', 'system:sysDept:sysDept', '1', 'fa fa-users', '3', null, null);
INSERT INTO "public"."sys_menu" VALUES ('74', '73', '增加', '/sys/dept/add', 'system:sysDept:add', '2', null, '1', null, null);
INSERT INTO "public"."sys_menu" VALUES ('75', '73', '刪除', 'sys/dept/remove', 'system:sysDept:remove', '2', null, '2', null, null);
INSERT INTO "public"."sys_menu" VALUES ('76', '73', '编辑', '/sys/dept/edit', 'system:sysDept:edit', '2', null, '3', null, null);
INSERT INTO "public"."sys_menu" VALUES ('77', '0', '系统工具', '', '', '0', 'fa fa-gear', '4', null, null);
INSERT INTO "public"."sys_menu" VALUES ('78', '1', '数据字典', '/common/sysDict', 'common:sysDict:sysDict', '1', 'fa fa-book', '1', null, null);
INSERT INTO "public"."sys_menu" VALUES ('79', '78', '增加', '/common/sysDict/add', 'common:sysDict:add', '2', null, '2', null, null);
INSERT INTO "public"."sys_menu" VALUES ('80', '78', '编辑', '/common/sysDict/edit', 'common:sysDict:edit', '2', null, '2', null, null);
INSERT INTO "public"."sys_menu" VALUES ('81', '78', '删除', '/common/sysDict/remove', 'common:sysDict:remove', '2', '', '3', null, null);
INSERT INTO "public"."sys_menu" VALUES ('83', '78', '批量删除', '/common/sysDict/batchRemove', 'common:sysDict:batchRemove', '2', '', '4', null, null);
INSERT INTO "public"."sys_menu" VALUES ('91', '0', '系统监控', '', '', '0', 'fa fa-video-camera', '5', null, null);
INSERT INTO "public"."sys_menu" VALUES ('92', '91', '在线用户', 'sys/online', '', '1', 'fa fa-user', null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('97', '0', '图表管理', '', '', '0', 'fa fa-bar-chart', '7', null, null);
INSERT INTO "public"."sys_menu" VALUES ('98', '97', '百度chart', '/chart/graph_echarts.html', '', '1', 'fa fa-area-chart', null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('175', '1', '系统配置', '/common/config', null, '1', 'fa fa-file-code-o', '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('176', '175', '查看', null, 'common:config:config', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('177', '175', '新增', null, 'common:config:add', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('178', '175', '修改', null, 'common:config:edit', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('179', '175', '删除', null, 'common:config:remove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('180', '175', '批量删除', null, 'common:config:batchRemove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('181', '199', '公众号管理', '/wxmp/mpConfig', null, '1', 'fa fa-file-code-o', '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('182', '181', '查看', null, 'wxmp:mpConfig:mpConfig', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('183', '181', '新增', null, 'wxmp:mpConfig:add', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('184', '181', '修改', null, 'wxmp:mpConfig:edit', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('185', '181', '删除', null, 'wxmp:mpConfig:remove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('186', '181', '批量删除', null, 'wxmp:mpConfig:batchRemove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('187', '199', '微信粉丝表', '/wxmp/mpFans', null, '1', 'fa fa-file-code-o', '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('188', '187', '查看', null, 'wxmp:mpFans:mpFans', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('189', '187', '新增', null, 'wxmp:mpFans:add', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('190', '187', '修改', null, 'wxmp:mpFans:edit', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('191', '187', '删除', null, 'wxmp:mpFans:remove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('192', '187', '批量删除', null, 'wxmp:mpFans:batchRemove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('193', '71', '增加', '/common/sysFile/add', 'oss:file:add', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('194', '71', '列表', '/common/sysFile/list', 'oss:file:list', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('195', '71', '编辑', '/common/sysFile/edit', 'oss:file:update', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('196', '71', '查询', '/common/sysFile/info', 'oss:file:info', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('197', '71', '删除', '/common/sysFile/remove', 'oss:file:remove', '2', null, null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('199', '0', '微信公众号', null, null, '0', 'fa fa-file-code-o', '3', null, null);
INSERT INTO "public"."sys_menu" VALUES ('205', '187', '同步', null, 'wxmp:mpFans:sync', '2', 'fa fa-refresh', '5', null, null);
INSERT INTO "public"."sys_menu" VALUES ('206', '1', '[Demo]基础表', '/demo/demoBase', '', '1', 'fa fa-file-code-o', '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('207', '206', '查看', null, 'demo:demoBase:demoBase', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('208', '206', '新增', null, 'demo:demoBase:add', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('209', '206', '修改', null, 'demo:demoBase:edit', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('210', '206', '删除', null, 'demo:demoBase:remove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('211', '206', '批量删除', null, 'demo:demoBase:batchRemove', '2', null, '6', null, null);
INSERT INTO "public"."sys_menu" VALUES ('1000', '0', 'api测试-用户更新', '', 'api:user:update', '2', '', null, null, null);
INSERT INTO "public"."sys_menu" VALUES ('1001', '0', 'api测试-appUser角色', '', 'api:user:role', '2', '', null, null, null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
"id" int8 DEFAULT nextval('sys_role_id_seq'::regclass) NOT NULL,
"role_name" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"role_sign" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"remark" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"user_id_create" int8,
"gmt_create" timestamp(6),
"gmt_modified" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES ('1', '超级用户角色', 'adminRole', '超级管理员', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO "public"."sys_role" VALUES ('56', '普通用户', null, '普通用户', null, null, null);
INSERT INTO "public"."sys_role" VALUES ('101', '前端API', 'apiRole', '前端API', null, null, null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_menu";
CREATE TABLE "public"."sys_role_menu" (
"id" int8 DEFAULT nextval('sys_role_menu_id_seq'::regclass) NOT NULL,
"role_id" int8,
"menu_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO "public"."sys_role_menu" VALUES ('367', '44', '1');
INSERT INTO "public"."sys_role_menu" VALUES ('368', '44', '32');
INSERT INTO "public"."sys_role_menu" VALUES ('369', '44', '33');
INSERT INTO "public"."sys_role_menu" VALUES ('370', '44', '34');
INSERT INTO "public"."sys_role_menu" VALUES ('371', '44', '35');
INSERT INTO "public"."sys_role_menu" VALUES ('372', '44', '28');
INSERT INTO "public"."sys_role_menu" VALUES ('373', '44', '29');
INSERT INTO "public"."sys_role_menu" VALUES ('374', '44', '30');
INSERT INTO "public"."sys_role_menu" VALUES ('375', '44', '38');
INSERT INTO "public"."sys_role_menu" VALUES ('376', '44', '4');
INSERT INTO "public"."sys_role_menu" VALUES ('377', '44', '27');
INSERT INTO "public"."sys_role_menu" VALUES ('378', '45', '38');
INSERT INTO "public"."sys_role_menu" VALUES ('379', '46', '3');
INSERT INTO "public"."sys_role_menu" VALUES ('380', '46', '20');
INSERT INTO "public"."sys_role_menu" VALUES ('381', '46', '21');
INSERT INTO "public"."sys_role_menu" VALUES ('382', '46', '22');
INSERT INTO "public"."sys_role_menu" VALUES ('383', '46', '23');
INSERT INTO "public"."sys_role_menu" VALUES ('384', '46', '11');
INSERT INTO "public"."sys_role_menu" VALUES ('385', '46', '12');
INSERT INTO "public"."sys_role_menu" VALUES ('386', '46', '13');
INSERT INTO "public"."sys_role_menu" VALUES ('387', '46', '14');
INSERT INTO "public"."sys_role_menu" VALUES ('388', '46', '24');
INSERT INTO "public"."sys_role_menu" VALUES ('389', '46', '25');
INSERT INTO "public"."sys_role_menu" VALUES ('390', '46', '26');
INSERT INTO "public"."sys_role_menu" VALUES ('391', '46', '15');
INSERT INTO "public"."sys_role_menu" VALUES ('392', '46', '2');
INSERT INTO "public"."sys_role_menu" VALUES ('393', '46', '6');
INSERT INTO "public"."sys_role_menu" VALUES ('394', '46', '7');
INSERT INTO "public"."sys_role_menu" VALUES ('598', '50', '38');
INSERT INTO "public"."sys_role_menu" VALUES ('632', '38', '42');
INSERT INTO "public"."sys_role_menu" VALUES ('737', '51', '38');
INSERT INTO "public"."sys_role_menu" VALUES ('738', '51', '39');
INSERT INTO "public"."sys_role_menu" VALUES ('739', '51', '40');
INSERT INTO "public"."sys_role_menu" VALUES ('740', '51', '41');
INSERT INTO "public"."sys_role_menu" VALUES ('741', '51', '4');
INSERT INTO "public"."sys_role_menu" VALUES ('742', '51', '32');
INSERT INTO "public"."sys_role_menu" VALUES ('743', '51', '33');
INSERT INTO "public"."sys_role_menu" VALUES ('744', '51', '34');
INSERT INTO "public"."sys_role_menu" VALUES ('745', '51', '35');
INSERT INTO "public"."sys_role_menu" VALUES ('746', '51', '27');
INSERT INTO "public"."sys_role_menu" VALUES ('747', '51', '28');
INSERT INTO "public"."sys_role_menu" VALUES ('748', '51', '29');
INSERT INTO "public"."sys_role_menu" VALUES ('749', '51', '30');
INSERT INTO "public"."sys_role_menu" VALUES ('750', '51', '1');
INSERT INTO "public"."sys_role_menu" VALUES ('1064', '54', '53');
INSERT INTO "public"."sys_role_menu" VALUES ('1095', '55', '2');
INSERT INTO "public"."sys_role_menu" VALUES ('1096', '55', '6');
INSERT INTO "public"."sys_role_menu" VALUES ('1097', '55', '7');
INSERT INTO "public"."sys_role_menu" VALUES ('1098', '55', '3');
INSERT INTO "public"."sys_role_menu" VALUES ('1099', '55', '50');
INSERT INTO "public"."sys_role_menu" VALUES ('1100', '55', '49');
INSERT INTO "public"."sys_role_menu" VALUES ('1101', '55', '1');
INSERT INTO "public"."sys_role_menu" VALUES ('1856', '53', '28');
INSERT INTO "public"."sys_role_menu" VALUES ('1857', '53', '29');
INSERT INTO "public"."sys_role_menu" VALUES ('1858', '53', '30');
INSERT INTO "public"."sys_role_menu" VALUES ('1859', '53', '27');
INSERT INTO "public"."sys_role_menu" VALUES ('1860', '53', '57');
INSERT INTO "public"."sys_role_menu" VALUES ('1861', '53', '71');
INSERT INTO "public"."sys_role_menu" VALUES ('1862', '53', '48');
INSERT INTO "public"."sys_role_menu" VALUES ('1863', '53', '72');
INSERT INTO "public"."sys_role_menu" VALUES ('1864', '53', '1');
INSERT INTO "public"."sys_role_menu" VALUES ('1865', '53', '7');
INSERT INTO "public"."sys_role_menu" VALUES ('1866', '53', '55');
INSERT INTO "public"."sys_role_menu" VALUES ('1867', '53', '56');
INSERT INTO "public"."sys_role_menu" VALUES ('1868', '53', '62');
INSERT INTO "public"."sys_role_menu" VALUES ('1869', '53', '15');
INSERT INTO "public"."sys_role_menu" VALUES ('1870', '53', '2');
INSERT INTO "public"."sys_role_menu" VALUES ('1871', '53', '61');
INSERT INTO "public"."sys_role_menu" VALUES ('1872', '53', '20');
INSERT INTO "public"."sys_role_menu" VALUES ('1873', '53', '21');
INSERT INTO "public"."sys_role_menu" VALUES ('1874', '53', '22');
INSERT INTO "public"."sys_role_menu" VALUES ('2247', '63', '-1');
INSERT INTO "public"."sys_role_menu" VALUES ('2248', '63', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2249', '63', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2250', '63', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2251', '63', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2252', '64', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2253', '64', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2254', '64', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2255', '64', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2256', '64', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2257', '64', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2258', '65', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2259', '65', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2260', '65', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2262', '67', '48');
INSERT INTO "public"."sys_role_menu" VALUES ('2263', '68', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2264', '68', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2265', '69', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2266', '69', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2267', '69', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2268', '69', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2269', '69', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2270', '69', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2271', '70', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2272', '70', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2273', '70', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2274', '70', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2275', '70', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2276', '70', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2277', '71', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2278', '72', '59');
INSERT INTO "public"."sys_role_menu" VALUES ('2279', '73', '48');
INSERT INTO "public"."sys_role_menu" VALUES ('2280', '74', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2281', '74', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2282', '75', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2283', '75', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2284', '76', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2285', '76', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2286', '76', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2287', '76', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2288', '76', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2289', '76', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2292', '78', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2293', '78', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2294', '78', null);
INSERT INTO "public"."sys_role_menu" VALUES ('2295', '78', null);
INSERT INTO "public"."sys_role_menu" VALUES ('2296', '78', null);
INSERT INTO "public"."sys_role_menu" VALUES ('2308', '80', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2309', '80', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2310', '80', '-1');
INSERT INTO "public"."sys_role_menu" VALUES ('2311', '80', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2312', '80', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2328', '79', '72');
INSERT INTO "public"."sys_role_menu" VALUES ('2329', '79', '48');
INSERT INTO "public"."sys_role_menu" VALUES ('2330', '79', '77');
INSERT INTO "public"."sys_role_menu" VALUES ('2331', '79', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2332', '79', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2333', '79', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2334', '79', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2335', '79', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2336', '79', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2337', '79', '-1');
INSERT INTO "public"."sys_role_menu" VALUES ('2338', '77', '89');
INSERT INTO "public"."sys_role_menu" VALUES ('2339', '77', '88');
INSERT INTO "public"."sys_role_menu" VALUES ('2340', '77', '87');
INSERT INTO "public"."sys_role_menu" VALUES ('2341', '77', '86');
INSERT INTO "public"."sys_role_menu" VALUES ('2342', '77', '85');
INSERT INTO "public"."sys_role_menu" VALUES ('2343', '77', '84');
INSERT INTO "public"."sys_role_menu" VALUES ('2344', '77', '72');
INSERT INTO "public"."sys_role_menu" VALUES ('2345', '77', '-1');
INSERT INTO "public"."sys_role_menu" VALUES ('2346', '77', '77');
INSERT INTO "public"."sys_role_menu" VALUES ('3178', '56', '68');
INSERT INTO "public"."sys_role_menu" VALUES ('3179', '56', '60');
INSERT INTO "public"."sys_role_menu" VALUES ('3180', '56', '59');
INSERT INTO "public"."sys_role_menu" VALUES ('3181', '56', '58');
INSERT INTO "public"."sys_role_menu" VALUES ('3182', '56', '51');
INSERT INTO "public"."sys_role_menu" VALUES ('3183', '56', '50');
INSERT INTO "public"."sys_role_menu" VALUES ('3184', '56', '49');
INSERT INTO "public"."sys_role_menu" VALUES ('3185', '56', '-1');
INSERT INTO "public"."sys_role_menu" VALUES ('4487', '1', '193');
INSERT INTO "public"."sys_role_menu" VALUES ('4488', '1', '194');
INSERT INTO "public"."sys_role_menu" VALUES ('4489', '1', '195');
INSERT INTO "public"."sys_role_menu" VALUES ('4490', '1', '196');
INSERT INTO "public"."sys_role_menu" VALUES ('4491', '1', '197');
INSERT INTO "public"."sys_role_menu" VALUES ('4492', '1', '79');
INSERT INTO "public"."sys_role_menu" VALUES ('4493', '1', '80');
INSERT INTO "public"."sys_role_menu" VALUES ('4494', '1', '81');
INSERT INTO "public"."sys_role_menu" VALUES ('4495', '1', '83');
INSERT INTO "public"."sys_role_menu" VALUES ('4496', '1', '176');
INSERT INTO "public"."sys_role_menu" VALUES ('4497', '1', '177');
INSERT INTO "public"."sys_role_menu" VALUES ('4498', '1', '178');
INSERT INTO "public"."sys_role_menu" VALUES ('4499', '1', '179');
INSERT INTO "public"."sys_role_menu" VALUES ('4500', '1', '180');
INSERT INTO "public"."sys_role_menu" VALUES ('4501', '1', '207');
INSERT INTO "public"."sys_role_menu" VALUES ('4502', '1', '208');
INSERT INTO "public"."sys_role_menu" VALUES ('4503', '1', '209');
INSERT INTO "public"."sys_role_menu" VALUES ('4504', '1', '210');
INSERT INTO "public"."sys_role_menu" VALUES ('4505', '1', '211');
INSERT INTO "public"."sys_role_menu" VALUES ('4506', '1', '20');
INSERT INTO "public"."sys_role_menu" VALUES ('4507', '1', '21');
INSERT INTO "public"."sys_role_menu" VALUES ('4508', '1', '22');
INSERT INTO "public"."sys_role_menu" VALUES ('4509', '1', '61');
INSERT INTO "public"."sys_role_menu" VALUES ('4510', '1', '12');
INSERT INTO "public"."sys_role_menu" VALUES ('4511', '1', '13');
INSERT INTO "public"."sys_role_menu" VALUES ('4512', '1', '14');
INSERT INTO "public"."sys_role_menu" VALUES ('4513', '1', '24');
INSERT INTO "public"."sys_role_menu" VALUES ('4514', '1', '25');
INSERT INTO "public"."sys_role_menu" VALUES ('4515', '1', '26');
INSERT INTO "public"."sys_role_menu" VALUES ('4516', '1', '15');
INSERT INTO "public"."sys_role_menu" VALUES ('4517', '1', '55');
INSERT INTO "public"."sys_role_menu" VALUES ('4518', '1', '56');
INSERT INTO "public"."sys_role_menu" VALUES ('4519', '1', '62');
INSERT INTO "public"."sys_role_menu" VALUES ('4520', '1', '74');
INSERT INTO "public"."sys_role_menu" VALUES ('4521', '1', '75');
INSERT INTO "public"."sys_role_menu" VALUES ('4522', '1', '76');
INSERT INTO "public"."sys_role_menu" VALUES ('4523', '1', '48');
INSERT INTO "public"."sys_role_menu" VALUES ('4524', '1', '72');
INSERT INTO "public"."sys_role_menu" VALUES ('4525', '1', '28');
INSERT INTO "public"."sys_role_menu" VALUES ('4526', '1', '29');
INSERT INTO "public"."sys_role_menu" VALUES ('4527', '1', '30');
INSERT INTO "public"."sys_role_menu" VALUES ('4528', '1', '57');
INSERT INTO "public"."sys_role_menu" VALUES ('4529', '1', '92');
INSERT INTO "public"."sys_role_menu" VALUES ('4530', '1', '71');
INSERT INTO "public"."sys_role_menu" VALUES ('4531', '1', '78');
INSERT INTO "public"."sys_role_menu" VALUES ('4532', '1', '175');
INSERT INTO "public"."sys_role_menu" VALUES ('4533', '1', '206');
INSERT INTO "public"."sys_role_menu" VALUES ('4534', '1', '1');
INSERT INTO "public"."sys_role_menu" VALUES ('4535', '1', '2');
INSERT INTO "public"."sys_role_menu" VALUES ('4536', '1', '6');
INSERT INTO "public"."sys_role_menu" VALUES ('4537', '1', '7');
INSERT INTO "public"."sys_role_menu" VALUES ('4538', '1', '73');
INSERT INTO "public"."sys_role_menu" VALUES ('4539', '1', '3');
INSERT INTO "public"."sys_role_menu" VALUES ('4540', '1', '77');
INSERT INTO "public"."sys_role_menu" VALUES ('4541', '1', '27');
INSERT INTO "public"."sys_role_menu" VALUES ('4542', '1', '91');
INSERT INTO "public"."sys_role_menu" VALUES ('4543', '1', '-1');
INSERT INTO "public"."sys_role_menu" VALUES ('4544', '1034088931742957569', '1034089959238385666');
INSERT INTO "public"."sys_role_menu" VALUES ('4545', '1034088931742957569', '1034090238251876354');
INSERT INTO "public"."sys_role_menu" VALUES ('4546', '1034088931742957569', '-1');

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_task";
CREATE TABLE "public"."sys_task" (
"id" int8 DEFAULT nextval('sys_task_id_seq'::regclass) NOT NULL,
"cron_expression" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"method_name" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"is_concurrent" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"description" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"update_by" varchar(64) COLLATE "default" DEFAULT NULL::character varying,
"bean_class" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"create_date" timestamp(6),
"job_status" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"job_group" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"update_date" timestamp(6),
"create_by" varchar(64) COLLATE "default" DEFAULT NULL::character varying,
"spring_bean" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"job_name" varchar(255) COLLATE "default" DEFAULT NULL::character varying
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO "public"."sys_task" VALUES ('2', '0/10 * * * * ?', 'run1', '1', '', '4028ea815a3d2a8c015a3d2f8d2a0002', 'com.ifast.job.TestJob', '2017-05-19 18:30:56', '0', 'group1', '2017-05-19 18:31:07', null, '', 'TestJob');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
"id" int8 DEFAULT nextval('sys_user_id_seq'::regclass) NOT NULL,
"username" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"name" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"password" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"dept_id" int8,
"email" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"mobile" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"status" int8,
"user_id_create" int8,
"gmt_create" timestamp(6),
"gmt_modified" timestamp(6),
"sex" int8,
"birth" timestamp(6),
"pic_id" int8,
"live_address" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"hobby" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"province" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"city" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"district" varchar(255) COLLATE "default" DEFAULT NULL::character varying
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES ('1', 'admin', '超级管理员', '33808479d49ca8a3cdc93d4f976d1e3d', '6', 'izenglong@163.com', '15277778888', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2018-04-02 00:00:00', '151', '创客基地', '', '广东省', '广州市', '番禺区');
INSERT INTO "public"."sys_user" VALUES ('2', 'test', '临时用户', 'b132f5f968c9373261f74025c23c2222', '6', 'test@ifast.com', '15278792752', '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36', '96', '2018-08-22 00:00:00', null, '', '', '北京市', '北京市市辖区', '东城区');
INSERT INTO "public"."sys_user" VALUES ('101', 'appUser', 'user', 'fc4d8bf7d69f03344a58f9381dd75dfe', '12', 'appUser@ifast.com', null, '1', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_role";
CREATE TABLE "public"."sys_user_role" (
"id" int8 DEFAULT nextval('sys_user_role_id_seq'::regclass) NOT NULL,
"user_id" int8,
"role_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "public"."sys_user_role" VALUES ('73', '30', '48');
INSERT INTO "public"."sys_user_role" VALUES ('74', '30', '49');
INSERT INTO "public"."sys_user_role" VALUES ('75', '30', '50');
INSERT INTO "public"."sys_user_role" VALUES ('76', '31', '48');
INSERT INTO "public"."sys_user_role" VALUES ('77', '31', '49');
INSERT INTO "public"."sys_user_role" VALUES ('78', '31', '52');
INSERT INTO "public"."sys_user_role" VALUES ('79', '32', '48');
INSERT INTO "public"."sys_user_role" VALUES ('80', '32', '49');
INSERT INTO "public"."sys_user_role" VALUES ('81', '32', '50');
INSERT INTO "public"."sys_user_role" VALUES ('82', '32', '51');
INSERT INTO "public"."sys_user_role" VALUES ('83', '32', '52');
INSERT INTO "public"."sys_user_role" VALUES ('84', '33', '38');
INSERT INTO "public"."sys_user_role" VALUES ('85', '33', '49');
INSERT INTO "public"."sys_user_role" VALUES ('86', '33', '52');
INSERT INTO "public"."sys_user_role" VALUES ('87', '34', '50');
INSERT INTO "public"."sys_user_role" VALUES ('88', '34', '51');
INSERT INTO "public"."sys_user_role" VALUES ('89', '34', '52');
INSERT INTO "public"."sys_user_role" VALUES ('110', '1', '1');
INSERT INTO "public"."sys_user_role" VALUES ('111', '2', '1');
INSERT INTO "public"."sys_user_role" VALUES ('117', '135', '1');
INSERT INTO "public"."sys_user_role" VALUES ('120', '134', '1');
INSERT INTO "public"."sys_user_role" VALUES ('121', '134', '48');
INSERT INTO "public"."sys_user_role" VALUES ('124', null, '48');
INSERT INTO "public"."sys_user_role" VALUES ('127', null, '1');
INSERT INTO "public"."sys_user_role" VALUES ('128', null, '1');
INSERT INTO "public"."sys_user_role" VALUES ('129', null, '1');
INSERT INTO "public"."sys_user_role" VALUES ('131', '137', '57');
INSERT INTO "public"."sys_user_role" VALUES ('133', '1000', '101');

-- ----------------------------
-- Table structure for wx_mp_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."wx_mp_config";
CREATE TABLE "public"."wx_mp_config" (
"id" int8 DEFAULT nextval('wx_mp_config_id_seq'::regclass) NOT NULL,
"token" varchar(120) COLLATE "default" DEFAULT NULL::character varying,
"app_id" varchar(64) COLLATE "default" NOT NULL,
"app_secret" varchar(128) COLLATE "default" NOT NULL,
"msg_mode" int8,
"msg_secret" varchar(128) COLLATE "default" DEFAULT NULL::character varying,
"mp_name" varchar(250) COLLATE "default" DEFAULT NULL::character varying,
"app_type" int8 NOT NULL,
"is_auth" int8,
"subscribe_url" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"app_key" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"logo" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"create_time" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of wx_mp_config
-- ----------------------------
INSERT INTO "public"."wx_mp_config" VALUES ('11', '72597b9628704ab09e8b9e8cbe9b540a', 'wxeb5638d307d3df71', '03b1501c72a91f2935037336e43e67e6', '0', '', '源码在线-测试', '5', '0', 'http://xxx.com/test', 'ymhTest', 'http://p6r7ke2jc.bkt.clouddn.com/ifast/20180822/photo_s-1534922328124.png', '2017-11-03 17:32:53');

-- ----------------------------
-- Table structure for wx_mp_fans
-- ----------------------------
DROP TABLE IF EXISTS "public"."wx_mp_fans";
CREATE TABLE "public"."wx_mp_fans" (
"id" int8 DEFAULT nextval('wx_mp_fans_id_seq'::regclass) NOT NULL,
"mp_id" int8 NOT NULL,
"openid" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"nickname" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"subscribe" int8,
"subscribe_time" timestamp(6),
"subscribe_key" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"sex" int8,
"city" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"country" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"province" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"language" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"headimgurl" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"unionid" varchar(125) COLLATE "default" DEFAULT NULL::character varying,
"remark" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"groupid" int8,
"status" int8,
"tagid_list" varchar(100) COLLATE "default" DEFAULT NULL::character varying
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of wx_mp_fans
-- ----------------------------
INSERT INTO "public"."wx_mp_fans" VALUES ('2899', '5', 'oorK7v4LWw3GMbwt_Ck6PuQ_8_D8', '你是我的眼', '0', null, null, '2', '广州', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/A7sq8BD8oewx50myY72SwetEVkBXbXDvT5jj6ytorRxqyGwtBu1XTnWGohGXhdTtTwh6VSAbUEUSWpibddJDChg/0', 'oI55m1o8VxrTFvV57WkzEFTHLRIM', null, null, '0', null);
INSERT INTO "public"."wx_mp_fans" VALUES ('2902', '7', 'otO0P09bRa-YRLNlPbJEL1bdDjkQ', 'Aron', '1', '2017-11-24 11:14:28', '', '1', '广州', '中国', '广东', 'zh_CN', 'http://wx.qlogo.cn/mmopen/BQD9yxMcK6CicIrF6tU8Pqucb2VgYicn1iaTMTwm9war1KLT9RlibbsJ9cYal7yypERODjt6XGXC4dVJdVs9woJZBHwI0ibmaGQxY/0', 'oVzGa0kCIhSXljL9wDALjN00ylOs', '', '0', '0', null);
INSERT INTO "public"."wx_mp_fans" VALUES ('2904', '9', 'ozbGr0vZhCS8Pe1lpTuy1tq9Wlls', 'SuSu', '0', '2017-11-26 21:03:25', '', '1', '江北', '中国', '重庆', 'zh_CN', 'http://wx.qlogo.cn/mmopen/8o7KgbIM6ibFyF3coD1mMMdm91kic6Tb68P0hq9lDccec0TllUm5MnBa4WEesfiaW1HUXWqNqCTNUsrYM5iceq9gnesbSPSaE0Yw/0', 'oJitl0bd590o0ONtSt1nB7hFh0Bo', '', '0', null, null);

-- ----------------------------
-- Table structure for wx_mp_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."wx_mp_menu";
CREATE TABLE "public"."wx_mp_menu" (
"id" int8 DEFAULT nextval('wx_mp_menu_id_seq'::regclass) NOT NULL,
"parent_idx" int8,
"menu_type" varchar(50) COLLATE "default" NOT NULL,
"menu_name" varchar(128) COLLATE "default" NOT NULL,
"menu_key" varchar(64) COLLATE "default" DEFAULT NULL::character varying,
"menu_url" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"reply_content" varchar(500) COLLATE "default" DEFAULT NULL::character varying,
"scan_type" int8,
"picture_type" int8,
"location" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"sort" int8,
"status" int8,
"create_by" int8,
"create_time" timestamp(6),
"update_by" int8,
"up" timestamp(6),
"mp_id" int8 NOT NULL,
"idx" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of wx_mp_menu
-- ----------------------------

-- ----------------------------
-- Table structure for wx_mp_wechat_keys
-- ----------------------------
DROP TABLE IF EXISTS "public"."wx_mp_wechat_keys";
CREATE TABLE "public"."wx_mp_wechat_keys" (
"id" int8 DEFAULT nextval('wx_mp_wechat_keys_id_seq'::regclass) NOT NULL,
"mp_id" int8 NOT NULL,
"media_id" varchar(50) COLLATE "default" DEFAULT NULL::character varying,
"keyword" varchar(200) COLLATE "default" DEFAULT NULL::character varying,
"type" int8,
"reply_type" varchar(20) COLLATE "default" DEFAULT NULL::character varying,
"content" text COLLATE "default",
"news_id" int8,
"music_title" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"music_cover" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"music_url" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"music_desc" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"image_url" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"voice_url" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"video_title" varchar(100) COLLATE "default" DEFAULT NULL::character varying,
"video_url" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"video_desc" varchar(255) COLLATE "default" DEFAULT NULL::character varying,
"create_time" timestamp(6),
"update_time" timestamp(6),
"status" int8 DEFAULT '1'::bigint NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of wx_mp_wechat_keys
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."sys_config_id_seq" OWNED BY "sys_config"."id";
ALTER SEQUENCE "public"."sys_dept_id_seq" OWNED BY "sys_dept"."id";
ALTER SEQUENCE "public"."sys_dict_id_seq" OWNED BY "sys_dict"."id";
ALTER SEQUENCE "public"."sys_file_id_seq" OWNED BY "sys_file"."id";
ALTER SEQUENCE "public"."sys_log_id_seq" OWNED BY "sys_log"."id";
ALTER SEQUENCE "public"."sys_menu_id_seq" OWNED BY "sys_menu"."id";
ALTER SEQUENCE "public"."sys_role_id_seq" OWNED BY "sys_role"."id";
ALTER SEQUENCE "public"."sys_role_menu_id_seq" OWNED BY "sys_role_menu"."id";
ALTER SEQUENCE "public"."sys_task_id_seq" OWNED BY "sys_task"."id";
ALTER SEQUENCE "public"."sys_user_id_seq" OWNED BY "sys_user"."id";
ALTER SEQUENCE "public"."sys_user_role_id_seq" OWNED BY "sys_user_role"."id";
ALTER SEQUENCE "public"."wx_mp_config_id_seq" OWNED BY "wx_mp_config"."id";
ALTER SEQUENCE "public"."wx_mp_fans_id_seq" OWNED BY "wx_mp_fans"."id";
ALTER SEQUENCE "public"."wx_mp_menu_id_seq" OWNED BY "wx_mp_menu"."id";
ALTER SEQUENCE "public"."wx_mp_wechat_keys_id_seq" OWNED BY "wx_mp_wechat_keys"."id";

-- ----------------------------
-- Primary Key structure for table app_demo_base
-- ----------------------------
ALTER TABLE "public"."app_demo_base" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_config
-- ----------------------------
ALTER TABLE "public"."sys_config" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dept
-- ----------------------------
ALTER TABLE "public"."sys_dept" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_dict
-- ----------------------------
CREATE INDEX "sys_dict_value" ON "public"."sys_dict" USING btree ("value");
CREATE INDEX "sys_dict_label" ON "public"."sys_dict" USING btree ("name");
CREATE INDEX "sys_dict_del_flag" ON "public"."sys_dict" USING btree ("del_flag");

-- ----------------------------
-- Primary Key structure for table sys_dict
-- ----------------------------
ALTER TABLE "public"."sys_dict" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_file
-- ----------------------------
ALTER TABLE "public"."sys_file" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_menu
-- ----------------------------
ALTER TABLE "public"."sys_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_menu
-- ----------------------------
ALTER TABLE "public"."sys_role_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_task
-- ----------------------------
ALTER TABLE "public"."sys_task" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "public"."sys_user_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table wx_mp_config
-- ----------------------------
ALTER TABLE "public"."wx_mp_config" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table wx_mp_fans
-- ----------------------------
ALTER TABLE "public"."wx_mp_fans" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table wx_mp_menu
-- ----------------------------
ALTER TABLE "public"."wx_mp_menu" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table wx_mp_wechat_keys
-- ----------------------------
ALTER TABLE "public"."wx_mp_wechat_keys" ADD PRIMARY KEY ("id");
