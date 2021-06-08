/*
MySQL Backup
Source Server Version: 5.7.21
Source Database: picadmin
Date: 2018/6/7 星期四 19:20:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module` (
  `mcode` varchar(255) NOT NULL COMMENT '权限编码',
  `mname` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `micon` varchar(255) DEFAULT NULL COMMENT '图标',
  `murl` varchar(255) DEFAULT NULL COMMENT '资源地址',
  `explains` varchar(255) DEFAULT NULL COMMENT '说明',
  `parent` varchar(255) DEFAULT NULL COMMENT '父节点',
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`mcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `rcode` varchar(255) NOT NULL COMMENT '角色编号',
  `rname` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `flag` int(1) DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`rcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_module`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_module`;
CREATE TABLE `sys_role_module` (
  `rcode` varchar(255) DEFAULT NULL,
  `mcode` varchar(255) DEFAULT NULL,
  KEY `sys_role_module_rfk` (`rcode`),
  KEY `sys_role_module_mfk` (`mcode`),
  CONSTRAINT `sys_role_module_mfk` FOREIGN KEY (`mcode`) REFERENCES `sys_module` (`mcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_role_module_rfk` FOREIGN KEY (`rcode`) REFERENCES `sys_role` (`rcode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT '1',
  `lgpic` varchar(255) DEFAULT NULL,
  `ltpic` varchar(255) DEFAULT NULL,
  `qqpid` varchar(255) DEFAULT NULL,
  `wxpid` varchar(255) DEFAULT NULL,
  `statu` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0正常激活，1冻结，',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1085 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` int(11) NOT NULL,
  `rcode` varchar(255) NOT NULL,
  KEY `uid` (`uid`),
  KEY `sys_user_role_rcodefk` (`rcode`),
  CONSTRAINT `sys_user_role_rcodefk` FOREIGN KEY (`rcode`) REFERENCES `sys_role` (`rcode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_user_role_uidfk` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `sys_module` VALUES ('Cssd','CSS动画','glyphicon glyphicon-briefcase','/css/aaa','aaaaa啊啊啊aaaaa','UI','1'), ('modulePage','权限列表菜单','glyphicon glyphicon-eye-open','/module/Page','管理所有操作权限a','userManage','3'), ('UI','页面开发模板','glyphicon glyphicon-th','','开发页面的常用组件和页面',NULL,'4'), ('ui-form-stru','表单构造器','glyphicon glyphicon-transfer','/ui/form_stru','标案基本元素构造工具','UI','1'), ('userEdit','用户信息修改','glyphicon glyphicon-pencil','/user/edit','修改用户信息','userPage','2'), ('userManage','用户管理','glyphicon glyphicon-users',NULL,'用户管理主菜单',NULL,'1'), ('userPage','用户列表菜单','glyphicon glyphicon-headphones','/user/listPage','用户列表','userManage','1'), ('userPage_query','用户查询','glyphicon glyphicon-query','/user/all','用户查询','userPage','1');
INSERT INTO `sys_role` VALUES ('admin','普通管理员','1'), ('persion','普通用户','1'), ('supadmin','超级管理员','1'), ('testRole','测试角色','1');
INSERT INTO `sys_role_module` VALUES ('admin','userManage'), ('admin','userPage'), ('admin','userPage_query'), ('admin','userEdit'), ('admin','modulePage'), ('admin','Cssd'), ('persion','userManage'), ('persion','userPage'), ('persion','userPage_query'), ('supadmin','userManage'), ('supadmin','userPage'), ('supadmin','userPage_query'), ('supadmin','userEdit'), ('supadmin','modulePage'), ('supadmin','Cssd');
INSERT INTO `sys_user` VALUES ('1000','蓝羽','f6cb7a7ebd15617a251013a6eb4f66db','1234322343','1111@qq.com','2018-04-12','1','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1002','熊四1002','d9fbe91284480b457d6c0c5be1644f18','123456','1113@qq.com','2018-04-10','1','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1003','熊五1003','781f62090358740d05069d57ab8cb2f2','123456','1114@qq.com','2018-04-09','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1005','熊二1005','123456','123456','1116@qq.com','2018-04-07','1','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1006','熊三1006','123456','123456','1117@qq.com','2018-04-06','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1007','熊四1007','123456','123456','1118@qq.com','2018-04-05','1','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1008','熊五1008','123456','123456','1119@qq.com','2018-04-04','1','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1009','熊六1009','123456','123456','1120@qq.com','2018-04-03','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1010','熊七1010','123456','123456','1121@qq.com','2018-04-02','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1011','熊三1011','123456','123456','1122@qq.com','2018-04-01','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1012','熊四1012','123456','123456','1123@qq.com','2018-03-31','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1013','熊五1013','123456','123456','1124@qq.com','2018-03-30','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1014','熊六1014','123456','123456','1125@qq.com','2018-03-29','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1015','熊七1015','123456','123456','1126@qq.com','2018-03-28','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1016','熊三1016','123456','123456','1127@qq.com','2018-03-27','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1017','熊四1017','123456','123456','1128@qq.com','2018-03-26','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1018','熊五1018','123456','123456','1129@qq.com','2018-03-25','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1019','熊六1019','123456','123456','1130@qq.com','2018-03-24','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1020','熊七1020','123456','123456','1131@qq.com','2018-03-23','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1021','熊三1021','123456','123456','1132@qq.com','2018-03-22','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1022','熊四1022','123456','123456','1133@qq.com','2018-03-21','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1023','熊五1023','123456','123456','1134@qq.com','2018-03-20','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1024','熊六1024','123456','123456','1135@qq.com','2018-03-19','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1025','熊七1025','123456','123456','1136@qq.com','2018-03-18','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1026','熊三1026','123456','123456','1137@qq.com','2018-03-17','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1027','熊四1027','123456','123456','1138@qq.com','2018-03-16','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1028','熊五1028','123456','123456','1139@qq.com','2018-03-15','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1029','熊六1029','123456','123456','1140@qq.com','2018-03-14','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1030','熊七1030','123456','123456','1141@qq.com','2018-03-13','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1031','熊三1031','123456','123456','1142@qq.com','2018-03-12','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1032','熊四1032','123456','123456','1143@qq.com','2018-03-11','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1033','熊五1033','123456','123456','1144@qq.com','2018-03-10','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1034','熊六1034','123456','123456','1145@qq.com','2018-03-09','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1035','熊七1035','123456','123456','1146@qq.com','2018-03-08','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1036','熊三1036','123456','123456','1147@qq.com','2018-03-07','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1037','熊四1037','123456','123456','1148@qq.com','2018-03-06','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1038','熊五1038','123456','123456','1149@qq.com','2018-03-05','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1039','熊六1039','123456','123456','1150@qq.com','2018-03-04','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1040','熊七1040','123456','123456','1151@qq.com','2018-03-03','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1041','熊三1041','123456','123456','1152@qq.com','2018-03-02','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1042','熊四1042','123456','123456','1153@qq.com','2018-03-01','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1043','熊五1043','123456','123456','1154@qq.com','2018-02-28','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1044','熊六1044','123456','123456','1155@qq.com','2018-02-27','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1045','熊七1045','123456','123456','1156@qq.com','2018-02-26','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1046','熊三1046','123456','123456','1157@qq.com','2018-02-25','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1047','熊四1047','123456','123456','1158@qq.com','2018-02-24','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1048','熊五1048','123456','123456','1159@qq.com','2018-02-23','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1049','熊六1049','123456','123456','1160@qq.com','2018-02-22','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1050','熊七1050','123456','123456','1161@qq.com','2018-02-21','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1051','熊三1051','123456','123456','1162@qq.com','2018-02-20','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1052','熊四1052','123456','123456','1163@qq.com','2018-02-19','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1053','熊五1053','123456','123456','1164@qq.com','2018-02-18','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1054','熊六1054','123456','123456','1165@qq.com','2018-02-17','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1055','熊七1055','123456','123456','1166@qq.com','2018-02-16','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1056','熊三1056','123456','123456','1167@qq.com','2018-02-15','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1057','熊四1057','123456','123456','1168@qq.com','2018-02-14','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1058','熊五1058','123456','123456','1169@qq.com','2018-02-13','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1059','熊六1059','123456','123456','1170@qq.com','2018-02-12','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1060','熊七1060','123456','123456','1171@qq.com','2018-02-11','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1061','熊三1061','123456','123456','1172@qq.com','2018-02-10','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1062','熊四1062','123456','123456','1173@qq.com','2018-02-09','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1063','熊五1063','123456','123456','1174@qq.com','2018-02-08','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1064','熊六1064','123456','123456','1175@qq.com','2018-02-07','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1065','熊七1065','123456','123456','1176@qq.com','2018-02-06','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1066','熊三1066','123456','123456','1177@qq.com','2018-02-05','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1067','熊四1067','123456','123456','1178@qq.com','2018-02-04','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1068','熊五1068','123456','123456','1179@qq.com','2018-02-03','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1069','熊六1069','123456','123456','1180@qq.com','2018-02-02','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1070','熊七1070','123456','123456','1181@qq.com','2018-02-01','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1071','熊三1071','123456','123456','1182@qq.com','2018-01-31','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1072','熊四1072','123456','123456','1183@qq.com','2018-01-30','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1073','熊五1073','123456','123456','1184@qq.com','2018-01-29','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1074','熊六1074','123456','123456','1185@qq.com','2018-01-28','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1075','熊七1075','123456','123456','1186@qq.com','2018-01-27','2','/img/a1.jgp','/img/a1.jgp','5161','516','0'), ('1084','测试添加','f609612de829b5f395bd0fd3b5da8d2f','15316224788','1063097422@qq.com','2000-01-02','1',NULL,NULL,NULL,NULL,'0');
INSERT INTO `sys_user_role` VALUES ('1000','admin'), ('1072','testRole'), ('1084','persion'), ('1002','persion'), ('1002','testRole');
