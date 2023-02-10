/*
Navicat MySQL Data Transfer

Source Server         : 3310远程数据库
Source Server Version : 50729
Source Host           : localhost:3310
Source Database       : 2020erp

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2020-04-28 17:38:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `connectionperson` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES ('1', '小张超市', '111', '武汉', '027-9123131', '张大明', '138123123123', '中国银行', '654431331343413', '213123@sina.com', '430000', '1');
INSERT INTO `bus_customer` VALUES ('2', '小明超市', '222', '深圳', '0755-9123131', '张小明', '138123123123', '中国银行', '654431331343413', '213123@sina.com', '430000', '1');
INSERT INTO `bus_customer` VALUES ('3', '快七超市', '430000', '武汉', '027-11011011', '雷生', '13434134131', '招商银行', '6543123341334133', '6666@66.com', '545341', '1');
INSERT INTO `bus_customer` VALUES ('9', '小杨', '43000', '湖南省', '0223-3366-11561', 'nb666', '17773596535', '长沙银行', '65312151231321', '1692700664@qq.com', '121512315', '1');

-- ----------------------------
-- Table structure for bus_goods
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) DEFAULT NULL,
  `produceplace` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `goodspackage` varchar(255) DEFAULT NULL,
  `productcode` varchar(255) DEFAULT NULL,
  `promitcode` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `dangernum` int(11) DEFAULT NULL,
  `goodsimg` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_sq0btr2v2lq8gt8b4gb8tlk0i` (`providerid`) USING BTREE,
  CONSTRAINT `bus_goods_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_goods
-- ----------------------------
INSERT INTO `bus_goods` VALUES ('16', 'nb666手工礼品', '湖南', '300k', '盒', '1973', '16116', '很有用处的哟0123', '666', '109', '100', 'group1/M00/00/00/rBHkkV6li26AP7qEAAhxrMIZycU013.jpg', '0', '9');
INSERT INTO `bus_goods` VALUES ('17', '流程走一波', '11222', '111', '11123123123', '11', '11', '哼哼', '11', '30', '11', 'group1/M00/00/00/rBHkkV6mZeCADRvdAAC2ksid_yA16.jpeg', '1', '9');

-- ----------------------------
-- Table structure for bus_inport
-- ----------------------------
DROP TABLE IF EXISTS `bus_inport`;
CREATE TABLE `bus_inport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) DEFAULT NULL,
  `inporttime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `inportprice` double DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_1o4bujsyd2kl4iqw48fie224v` (`providerid`) USING BTREE,
  KEY `FK_ho29poeu4o2dxu4rg1ammeaql` (`goodsid`) USING BTREE,
  CONSTRAINT `bus_inport_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`),
  CONSTRAINT `bus_inport_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `bus_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_inport
-- ----------------------------
INSERT INTO `bus_inport` VALUES ('32', '支付宝', '2020-04-27 14:27:32', '超级管理员', '100', 'aaa', '12', '9', '17');

-- ----------------------------
-- Table structure for bus_outport
-- ----------------------------
DROP TABLE IF EXISTS `bus_outport`;
CREATE TABLE `bus_outport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `outporttime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `outportprice` double(10,2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  `inportid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_outport
-- ----------------------------
INSERT INTO `bus_outport` VALUES ('17', '9', '支付宝', '2020-04-27 14:29:19', '超级管理员', '12.00', '50', 'aaa', '17', '32');

-- ----------------------------
-- Table structure for bus_provider
-- ----------------------------
DROP TABLE IF EXISTS `bus_provider`;
CREATE TABLE `bus_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `connectionperson` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_provider
-- ----------------------------
INSERT INTO `bus_provider` VALUES ('1', '旺旺食品有限公司', '434000', '仙桃33', '0728-4124312', '小明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', '1');
INSERT INTO `bus_provider` VALUES ('2', '达利园食品有限公司', '430000', '汉川', '0728-4124312', '大明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', '1');
INSERT INTO `bus_provider` VALUES ('3', '娃哈哈有限公司', '513131', '杭州', '21312', '小晨', '12312', '建设银行', '512314141541', '123131', '312312', '1');
INSERT INTO `bus_provider` VALUES ('9', 'nb666小店铺', '43000', '湖南省 郴州市 北湖区  高楼大厦', '0845-111561', '小杨', '17773596535', '长沙银行', '653165151231651', '1692700664@qq.com', '00000', '0');

-- ----------------------------
-- Table structure for bus_sales
-- ----------------------------
DROP TABLE IF EXISTS `bus_sales`;
CREATE TABLE `bus_sales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) DEFAULT NULL,
  `salestime` datetime DEFAULT NULL,
  `operateperson` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `saleprice` decimal(10,2) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_sales
-- ----------------------------
INSERT INTO `bus_sales` VALUES ('8', '9', '支付宝', '2020-04-27 14:38:26', '超级管理员', '0', 'asdqwd', '11.00', '17');

-- ----------------------------
-- Table structure for bus_salesback
-- ----------------------------
DROP TABLE IF EXISTS `bus_salesback`;
CREATE TABLE `bus_salesback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL COMMENT '客户ID',
  `paytype` varchar(255) DEFAULT NULL COMMENT '支付类型',
  `salesbacktime` datetime DEFAULT NULL COMMENT '退货时间',
  `salebackprice` double(10,2) DEFAULT NULL COMMENT '退货价格',
  `operateperson` varchar(255) DEFAULT NULL COMMENT '联系人',
  `number` int(11) DEFAULT NULL COMMENT '数量',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `salesid` int(11) DEFAULT NULL COMMENT '销售ID',
  `goodsid` int(11) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of bus_salesback
-- ----------------------------
INSERT INTO `bus_salesback` VALUES ('6', '9', '支付宝', '2020-04-27 14:41:14', '11.00', '超级管理员', '30', 'asdqwd', '8', '17');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) DEFAULT NULL COMMENT '父级部门ID',
  `title` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `spread` int(11) DEFAULT '0' COMMENT '是否展开0不展开1展开',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码【为了调事显示顺序】',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '0', '总经办', '1', '大BOSS', '深圳1', '1', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('2', '1', '销售部', '1', '程序员屌丝', '武汉', '1', '2', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('3', '1', '运营部', '0', '无', '武汉', '1', '3', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('4', '1', '生产部', '0', '无', '武汉', '1', '4', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('5', '2', '销售一部', '0', '销售一部', '武汉', '1', '5', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('6', '2', '销售二部', '0', '销售二部', '武汉', '1', '6', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('7', '3', '运营一部', '0', '运营一部', '武汉', '1', '7', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('8', '2', '销售三部', '0', '销售三部', '11', '1', '8', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('9', '2', '销售四部', '0', '销售四部', '222', '1', '9', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('10', '2', '销售五部', '0', '销售五部', '33', '1', '10', '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES ('35', '4', '生产一部', '0', '生产一部', '武汉', '1', '12', '2019-11-11 11:36:44');
INSERT INTO `sys_dept` VALUES ('38', '5', '人员名单', '1', '人员名单', '哼', '1', '13', '2020-04-18 01:17:12');
INSERT INTO `sys_dept` VALUES ('39', '1', '嘻嘻', '0', 'aaaa', '背景', '1', '14', '2020-04-18 08:50:16');
INSERT INTO `sys_dept` VALUES ('40', '39', '老baby', '0', 'nb', '啊啊啊啊', '1', '15', '2020-04-18 08:53:58');
INSERT INTO `sys_dept` VALUES ('41', '39', '思思很乖', '0', '额前恶趣味', '啊啊啊', '1', '16', '2020-04-18 09:02:09');
INSERT INTO `sys_dept` VALUES ('42', '40', '不低呀', '0', '啊啊', '什么鬼', '1', '17', '2020-04-18 09:03:20');
INSERT INTO `sys_dept` VALUES ('43', '42', '对了', '0', '阿斯顿你请问开了电脑', '嗯嗯', '1', '18', '2020-04-18 09:04:48');

-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=812 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES ('550', '超级管理员-system', '127.0.0.1', '2020-04-16 08:38:11');
INSERT INTO `sys_loginfo` VALUES ('551', '超级管理员-system', '127.0.0.1', '2020-04-16 10:08:15');
INSERT INTO `sys_loginfo` VALUES ('552', '超级管理员-system', '127.0.0.1', '2020-04-16 10:33:04');
INSERT INTO `sys_loginfo` VALUES ('553', '超级管理员-system', '127.0.0.1', '2020-04-16 10:38:50');
INSERT INTO `sys_loginfo` VALUES ('554', '超级管理员-system', '127.0.0.1', '2020-04-16 11:27:42');
INSERT INTO `sys_loginfo` VALUES ('555', '超级管理员-system', '127.0.0.1', '2020-04-16 12:07:40');
INSERT INTO `sys_loginfo` VALUES ('556', '超级管理员-system', '127.0.0.1', '2020-04-16 12:39:54');
INSERT INTO `sys_loginfo` VALUES ('557', '超级管理员-system', '127.0.0.1', '2020-04-16 12:48:58');
INSERT INTO `sys_loginfo` VALUES ('558', '超级管理员-system', '127.0.0.1', '2020-04-16 13:31:43');
INSERT INTO `sys_loginfo` VALUES ('559', '超级管理员-system', '127.0.0.1', '2020-04-16 14:50:43');
INSERT INTO `sys_loginfo` VALUES ('560', '超级管理员-system', '127.0.0.1', '2020-04-16 15:18:26');
INSERT INTO `sys_loginfo` VALUES ('561', '超级管理员-system', '127.0.0.1', '2020-04-17 11:40:25');
INSERT INTO `sys_loginfo` VALUES ('562', '超级管理员-system', '127.0.0.1', '2020-04-17 14:13:46');
INSERT INTO `sys_loginfo` VALUES ('563', '超级管理员-system', '127.0.0.1', '2020-04-17 17:09:14');
INSERT INTO `sys_loginfo` VALUES ('564', '超级管理员-system', '127.0.0.1', '2020-04-17 17:35:03');
INSERT INTO `sys_loginfo` VALUES ('565', '超级管理员-system', '127.0.0.1', '2020-04-18 01:50:25');
INSERT INTO `sys_loginfo` VALUES ('566', '超级管理员-system', '127.0.0.1', '2020-04-18 04:31:36');
INSERT INTO `sys_loginfo` VALUES ('567', '超级管理员-system', '127.0.0.1', '2020-04-18 05:17:58');
INSERT INTO `sys_loginfo` VALUES ('568', '超级管理员-system', '127.0.0.1', '2020-04-18 05:19:00');
INSERT INTO `sys_loginfo` VALUES ('569', '超级管理员-system', '127.0.0.1', '2020-04-18 08:46:23');
INSERT INTO `sys_loginfo` VALUES ('570', '超级管理员-system', '127.0.0.1', '2020-04-18 08:53:19');
INSERT INTO `sys_loginfo` VALUES ('571', '超级管理员-system', '127.0.0.1', '2020-04-18 08:53:38');
INSERT INTO `sys_loginfo` VALUES ('572', '超级管理员-system', '127.0.0.1', '2020-04-18 08:57:28');
INSERT INTO `sys_loginfo` VALUES ('573', '超级管理员-system', '127.0.0.1', '2020-04-18 09:01:25');
INSERT INTO `sys_loginfo` VALUES ('574', '超级管理员-system', '127.0.0.1', '2020-04-18 09:22:22');
INSERT INTO `sys_loginfo` VALUES ('575', '超级管理员-system', '127.0.0.1', '2020-04-18 09:30:49');
INSERT INTO `sys_loginfo` VALUES ('576', '超级管理员-system', '127.0.0.1', '2020-04-18 09:36:47');
INSERT INTO `sys_loginfo` VALUES ('577', '超级管理员-system', '127.0.0.1', '2020-04-18 10:44:51');
INSERT INTO `sys_loginfo` VALUES ('578', '超级管理员-system', '127.0.0.1', '2020-04-18 10:46:22');
INSERT INTO `sys_loginfo` VALUES ('579', '超级管理员-system', '127.0.0.1', '2020-04-18 10:54:19');
INSERT INTO `sys_loginfo` VALUES ('580', '超级管理员-system', '127.0.0.1', '2020-04-18 12:13:02');
INSERT INTO `sys_loginfo` VALUES ('581', '超级管理员-system', '127.0.0.1', '2020-04-18 12:50:33');
INSERT INTO `sys_loginfo` VALUES ('582', '超级管理员-system', '127.0.0.1', '2020-04-18 13:39:27');
INSERT INTO `sys_loginfo` VALUES ('583', '超级管理员-system', '127.0.0.1', '2020-04-18 14:40:59');
INSERT INTO `sys_loginfo` VALUES ('584', '超级管理员-system', '127.0.0.1', '2020-04-18 15:07:12');
INSERT INTO `sys_loginfo` VALUES ('585', '超级管理员-system', '127.0.0.1', '2020-04-18 15:07:24');
INSERT INTO `sys_loginfo` VALUES ('586', '超级管理员-system', '127.0.0.1', '2020-04-18 15:07:43');
INSERT INTO `sys_loginfo` VALUES ('587', '超级管理员-system', '127.0.0.1', '2020-04-18 15:08:36');
INSERT INTO `sys_loginfo` VALUES ('588', '超级管理员-system', '127.0.0.1', '2020-04-18 15:09:21');
INSERT INTO `sys_loginfo` VALUES ('589', '超级管理员-system', '127.0.0.1', '2020-04-18 15:09:26');
INSERT INTO `sys_loginfo` VALUES ('590', '超级管理员-system', '127.0.0.1', '2020-04-18 15:10:16');
INSERT INTO `sys_loginfo` VALUES ('591', '超级管理员-system', '127.0.0.1', '2020-04-18 15:10:58');
INSERT INTO `sys_loginfo` VALUES ('592', '超级管理员-system', '127.0.0.1', '2020-04-18 15:12:00');
INSERT INTO `sys_loginfo` VALUES ('593', '超级管理员-system', '127.0.0.1', '2020-04-18 15:15:34');
INSERT INTO `sys_loginfo` VALUES ('594', '超级管理员-system', '127.0.0.1', '2020-04-18 15:16:13');
INSERT INTO `sys_loginfo` VALUES ('595', '超级管理员-system', '127.0.0.1', '2020-04-18 15:16:50');
INSERT INTO `sys_loginfo` VALUES ('596', '超级管理员-system', '127.0.0.1', '2020-04-18 15:18:06');
INSERT INTO `sys_loginfo` VALUES ('597', '超级管理员-system', '127.0.0.1', '2020-04-18 15:19:05');
INSERT INTO `sys_loginfo` VALUES ('598', '超级管理员-system', '127.0.0.1', '2020-04-18 15:28:47');
INSERT INTO `sys_loginfo` VALUES ('599', '超级管理员-system', '127.0.0.1', '2020-04-18 15:38:03');
INSERT INTO `sys_loginfo` VALUES ('600', '超级管理员-system', '127.0.0.1', '2020-04-18 15:50:23');
INSERT INTO `sys_loginfo` VALUES ('601', '超级管理员-system', '127.0.0.1', '2020-04-18 15:53:57');
INSERT INTO `sys_loginfo` VALUES ('602', '超级管理员-system', '127.0.0.1', '2020-04-18 15:56:35');
INSERT INTO `sys_loginfo` VALUES ('603', '超级管理员-system', '127.0.0.1', '2020-04-18 15:57:29');
INSERT INTO `sys_loginfo` VALUES ('604', '超级管理员-system', '127.0.0.1', '2020-04-18 16:27:53');
INSERT INTO `sys_loginfo` VALUES ('605', '超级管理员-system', '127.0.0.1', '2020-04-18 16:31:34');
INSERT INTO `sys_loginfo` VALUES ('606', '超级管理员-system', '127.0.0.1', '2020-04-19 00:47:36');
INSERT INTO `sys_loginfo` VALUES ('607', '超级管理员-system', '127.0.0.1', '2020-04-19 01:35:45');
INSERT INTO `sys_loginfo` VALUES ('608', '超级管理员-system', '127.0.0.1', '2020-04-19 01:58:49');
INSERT INTO `sys_loginfo` VALUES ('609', '超级管理员-system', '127.0.0.1', '2020-04-19 05:05:24');
INSERT INTO `sys_loginfo` VALUES ('610', '超级管理员-system', '127.0.0.1', '2020-04-19 05:06:26');
INSERT INTO `sys_loginfo` VALUES ('611', '超级管理员-system', '127.0.0.1', '2020-04-19 05:13:13');
INSERT INTO `sys_loginfo` VALUES ('612', '超级管理员-system', '127.0.0.1', '2020-04-19 05:49:57');
INSERT INTO `sys_loginfo` VALUES ('613', '超级管理员-system', '127.0.0.1', '2020-04-20 01:36:31');
INSERT INTO `sys_loginfo` VALUES ('614', '超级管理员-system', '127.0.0.1', '2020-04-20 03:04:48');
INSERT INTO `sys_loginfo` VALUES ('615', '超级管理员-system', '127.0.0.1', '2020-04-20 04:05:38');
INSERT INTO `sys_loginfo` VALUES ('616', '超级管理员-system', '127.0.0.1', '2020-04-20 06:53:30');
INSERT INTO `sys_loginfo` VALUES ('617', '超级管理员-system', '127.0.0.1', '2020-04-20 09:43:56');
INSERT INTO `sys_loginfo` VALUES ('618', '超级管理员-system', '127.0.0.1', '2020-04-20 09:57:05');
INSERT INTO `sys_loginfo` VALUES ('619', '超级管理员-system', '127.0.0.1', '2020-04-20 11:22:28');
INSERT INTO `sys_loginfo` VALUES ('620', '超级管理员-system', '127.0.0.1', '2020-04-20 15:13:43');
INSERT INTO `sys_loginfo` VALUES ('621', '超级管理员-system', '127.0.0.1', '2020-04-20 15:20:06');
INSERT INTO `sys_loginfo` VALUES ('622', '超级管理员-system', '127.0.0.1', '2020-04-20 15:21:54');
INSERT INTO `sys_loginfo` VALUES ('623', '超级管理员-system', '127.0.0.1', '2020-04-20 15:33:39');
INSERT INTO `sys_loginfo` VALUES ('624', '超级管理员-system', '127.0.0.1', '2020-04-20 17:24:13');
INSERT INTO `sys_loginfo` VALUES ('625', '超级管理员-system', '127.0.0.1', '2020-04-20 17:41:43');
INSERT INTO `sys_loginfo` VALUES ('626', '超级管理员-system', '127.0.0.1', '2020-04-21 03:26:10');
INSERT INTO `sys_loginfo` VALUES ('627', '超级管理员-system', '127.0.0.1', '2020-04-21 04:44:01');
INSERT INTO `sys_loginfo` VALUES ('628', '超级管理员-system', '127.0.0.1', '2020-04-21 06:12:44');
INSERT INTO `sys_loginfo` VALUES ('629', '超级管理员-system', '127.0.0.1', '2020-04-21 06:16:22');
INSERT INTO `sys_loginfo` VALUES ('630', '超级管理员-system', '127.0.0.1', '2020-04-21 07:23:20');
INSERT INTO `sys_loginfo` VALUES ('631', '超级管理员-system', '127.0.0.1', '2020-04-21 07:26:21');
INSERT INTO `sys_loginfo` VALUES ('632', '超级管理员-system', '127.0.0.1', '2020-04-21 11:05:20');
INSERT INTO `sys_loginfo` VALUES ('633', '超级管理员-system', '127.0.0.1', '2020-04-21 14:22:33');
INSERT INTO `sys_loginfo` VALUES ('634', '超级管理员-system', '127.0.0.1', '2020-04-21 14:31:25');
INSERT INTO `sys_loginfo` VALUES ('635', '超级管理员-system', '127.0.0.1', '2020-04-21 16:34:47');
INSERT INTO `sys_loginfo` VALUES ('636', '超级管理员-system', '127.0.0.1', '2020-04-21 16:39:12');
INSERT INTO `sys_loginfo` VALUES ('637', '超级管理员-system', '127.0.0.1', '2020-04-22 00:24:26');
INSERT INTO `sys_loginfo` VALUES ('638', '超级管理员-system', '127.0.0.1', '2020-04-22 04:41:37');
INSERT INTO `sys_loginfo` VALUES ('639', '超级管理员-system', '127.0.0.1', '2020-04-22 05:03:47');
INSERT INTO `sys_loginfo` VALUES ('640', '超级管理员-system', '127.0.0.1', '2020-04-22 05:06:01');
INSERT INTO `sys_loginfo` VALUES ('641', '超级管理员-system', '127.0.0.1', '2020-04-22 05:07:38');
INSERT INTO `sys_loginfo` VALUES ('642', '超级管理员-system', '127.0.0.1', '2020-04-22 05:08:33');
INSERT INTO `sys_loginfo` VALUES ('643', '超级管理员-system', '127.0.0.1', '2020-04-22 05:10:53');
INSERT INTO `sys_loginfo` VALUES ('644', '超级管理员-system', '127.0.0.1', '2020-04-22 05:12:46');
INSERT INTO `sys_loginfo` VALUES ('645', '超级管理员-system', '127.0.0.1', '2020-04-22 05:16:25');
INSERT INTO `sys_loginfo` VALUES ('646', '超级管理员-system', '127.0.0.1', '2020-04-22 05:21:19');
INSERT INTO `sys_loginfo` VALUES ('647', '超级管理员-system', '127.0.0.1', '2020-04-22 06:39:19');
INSERT INTO `sys_loginfo` VALUES ('648', '超级管理员-system', '127.0.0.1', '2020-04-22 08:40:09');
INSERT INTO `sys_loginfo` VALUES ('649', '超级管理员-system', '127.0.0.1', '2020-04-22 10:42:10');
INSERT INTO `sys_loginfo` VALUES ('650', '超级管理员-system', '127.0.0.1', '2020-04-22 10:47:11');
INSERT INTO `sys_loginfo` VALUES ('651', '超级管理员-system', '127.0.0.1', '2020-04-22 11:19:12');
INSERT INTO `sys_loginfo` VALUES ('652', '超级管理员-system', '127.0.0.1', '2020-04-22 11:25:50');
INSERT INTO `sys_loginfo` VALUES ('653', '超级管理员-system', '127.0.0.1', '2020-04-22 13:08:43');
INSERT INTO `sys_loginfo` VALUES ('654', '超级管理员-system', '127.0.0.1', '2020-04-22 13:17:55');
INSERT INTO `sys_loginfo` VALUES ('655', '超级管理员-system', '127.0.0.1', '2020-04-22 15:13:02');
INSERT INTO `sys_loginfo` VALUES ('656', '超级管理员-system', '127.0.0.1', '2020-04-22 17:42:49');
INSERT INTO `sys_loginfo` VALUES ('657', '刘总-liuzong', '127.0.0.1', '2020-04-22 17:45:31');
INSERT INTO `sys_loginfo` VALUES ('658', '超级管理员-system', '127.0.0.1', '2020-04-22 17:49:49');
INSERT INTO `sys_loginfo` VALUES ('659', '刘总-liuzong', '127.0.0.1', '2020-04-22 17:50:15');
INSERT INTO `sys_loginfo` VALUES ('660', '刘总-liuzong', '127.0.0.1', '2020-04-22 18:00:12');
INSERT INTO `sys_loginfo` VALUES ('661', '超级管理员-system', '127.0.0.1', '2020-04-23 04:20:09');
INSERT INTO `sys_loginfo` VALUES ('662', '超级管理员-system', '127.0.0.1', '2020-04-23 04:53:31');
INSERT INTO `sys_loginfo` VALUES ('663', '超级管理员-system', '127.0.0.1', '2020-04-23 08:59:35');
INSERT INTO `sys_loginfo` VALUES ('664', '超级管理员-system', '127.0.0.1', '2020-04-23 10:09:52');
INSERT INTO `sys_loginfo` VALUES ('665', '超级管理员-system', '127.0.0.1', '2020-04-23 10:10:50');
INSERT INTO `sys_loginfo` VALUES ('666', '超级管理员-system', '127.0.0.1', '2020-04-23 10:23:07');
INSERT INTO `sys_loginfo` VALUES ('667', '李四-ls', '127.0.0.1', '2020-04-23 10:28:39');
INSERT INTO `sys_loginfo` VALUES ('668', '超级管理员-system', '127.0.0.1', '2020-04-23 10:32:23');
INSERT INTO `sys_loginfo` VALUES ('669', '超级管理员-system', '127.0.0.1', '2020-04-23 10:35:05');
INSERT INTO `sys_loginfo` VALUES ('670', '李四-ls', '127.0.0.1', '2020-04-23 10:35:21');
INSERT INTO `sys_loginfo` VALUES ('671', '李四-ls', '127.0.0.1', '2020-04-23 10:35:49');
INSERT INTO `sys_loginfo` VALUES ('672', '李四-ls', '127.0.0.1', '2020-04-23 10:36:04');
INSERT INTO `sys_loginfo` VALUES ('673', '李四-ls', '127.0.0.1', '2020-04-23 10:39:29');
INSERT INTO `sys_loginfo` VALUES ('674', '刘总-liuzong', '127.0.0.1', '2020-04-23 10:41:34');
INSERT INTO `sys_loginfo` VALUES ('675', '王五-ww', '127.0.0.1', '2020-04-23 10:42:28');
INSERT INTO `sys_loginfo` VALUES ('676', '刘总-liuzong', '127.0.0.1', '2020-04-23 10:43:52');
INSERT INTO `sys_loginfo` VALUES ('677', '李四-ls', '127.0.0.1', '2020-04-23 10:46:06');
INSERT INTO `sys_loginfo` VALUES ('678', '刘总-liuzong', '127.0.0.1', '2020-04-23 10:58:54');
INSERT INTO `sys_loginfo` VALUES ('679', '刘总-liuzong', '127.0.0.1', '2020-04-23 11:02:06');
INSERT INTO `sys_loginfo` VALUES ('680', '李四-ls', '127.0.0.1', '2020-04-23 11:03:54');
INSERT INTO `sys_loginfo` VALUES ('681', '李四-ls', '127.0.0.1', '2020-04-23 11:07:16');
INSERT INTO `sys_loginfo` VALUES ('682', '李四-ls', '127.0.0.1', '2020-04-23 13:39:00');
INSERT INTO `sys_loginfo` VALUES ('683', '李四-ls', '127.0.0.1', '2020-04-24 04:23:07');
INSERT INTO `sys_loginfo` VALUES ('684', '超级管理员-system', '127.0.0.1', '2020-04-24 05:35:59');
INSERT INTO `sys_loginfo` VALUES ('685', '李四-ls', '127.0.0.1', '2020-04-24 05:50:30');
INSERT INTO `sys_loginfo` VALUES ('686', '李四-ls', '127.0.0.1', '2020-04-24 05:56:35');
INSERT INTO `sys_loginfo` VALUES ('687', '李四-ls', '127.0.0.1', '2020-04-24 06:49:27');
INSERT INTO `sys_loginfo` VALUES ('688', '李四-ls', '127.0.0.1', '2020-04-24 08:13:56');
INSERT INTO `sys_loginfo` VALUES ('689', '超级管理员-system', '127.0.0.1', '2020-04-24 08:16:36');
INSERT INTO `sys_loginfo` VALUES ('690', '超级管理员-system', '127.0.0.1', '2020-04-24 08:19:46');
INSERT INTO `sys_loginfo` VALUES ('691', '超级管理员-system', '127.0.0.1', '2020-04-24 08:22:12');
INSERT INTO `sys_loginfo` VALUES ('692', '超级管理员-system', '127.0.0.1', '2020-04-24 08:24:35');
INSERT INTO `sys_loginfo` VALUES ('693', '超级管理员-system', '127.0.0.1', '2020-04-24 08:25:33');
INSERT INTO `sys_loginfo` VALUES ('694', '超级管理员-system', '127.0.0.1', '2020-04-24 08:26:05');
INSERT INTO `sys_loginfo` VALUES ('695', '超级管理员-system', '127.0.0.1', '2020-04-24 08:26:39');
INSERT INTO `sys_loginfo` VALUES ('696', '超级管理员-system', '127.0.0.1', '2020-04-24 08:27:07');
INSERT INTO `sys_loginfo` VALUES ('697', '李四-ls', '127.0.0.1', '2020-04-24 09:43:03');
INSERT INTO `sys_loginfo` VALUES ('698', '李四-ls', '127.0.0.1', '2020-04-24 09:47:17');
INSERT INTO `sys_loginfo` VALUES ('699', '李四-ls', '127.0.0.1', '2020-04-24 09:48:39');
INSERT INTO `sys_loginfo` VALUES ('700', '刘总-liuzong', '127.0.0.1', '2020-04-24 09:51:15');
INSERT INTO `sys_loginfo` VALUES ('701', '刘总-liuzong', '127.0.0.1', '2020-04-24 10:03:25');
INSERT INTO `sys_loginfo` VALUES ('702', '李四-ls', '127.0.0.1', '2020-04-24 10:12:01');
INSERT INTO `sys_loginfo` VALUES ('703', '刘总-liuzong', '127.0.0.1', '2020-04-24 10:13:07');
INSERT INTO `sys_loginfo` VALUES ('704', '刘总-liuzong', '127.0.0.1', '2020-04-24 10:13:59');
INSERT INTO `sys_loginfo` VALUES ('705', '刘总-liuzong', '127.0.0.1', '2020-04-24 10:15:39');
INSERT INTO `sys_loginfo` VALUES ('706', '超级管理员-system', '127.0.0.1', '2020-04-24 10:45:10');
INSERT INTO `sys_loginfo` VALUES ('707', '超级管理员-system', '127.0.0.1', '2020-04-24 11:05:30');
INSERT INTO `sys_loginfo` VALUES ('708', '超级管理员-system', '127.0.0.1', '2020-04-24 11:32:21');
INSERT INTO `sys_loginfo` VALUES ('709', '李四-ls', '127.0.0.1', '2020-04-24 11:39:09');
INSERT INTO `sys_loginfo` VALUES ('710', '刘总-liuzong', '127.0.0.1', '2020-04-24 11:41:05');
INSERT INTO `sys_loginfo` VALUES ('711', '超级管理员-system', '127.0.0.1', '2020-04-24 11:44:44');
INSERT INTO `sys_loginfo` VALUES ('712', '超级管理员-system', '127.0.0.1', '2020-04-24 11:45:06');
INSERT INTO `sys_loginfo` VALUES ('713', '超级管理员-system', '127.0.0.1', '2020-04-24 11:54:22');
INSERT INTO `sys_loginfo` VALUES ('714', '超级管理员-system', '127.0.0.1', '2020-04-24 11:54:39');
INSERT INTO `sys_loginfo` VALUES ('715', '超级管理员-system', '127.0.0.1', '2020-04-24 11:55:35');
INSERT INTO `sys_loginfo` VALUES ('716', '超级管理员-system', '127.0.0.1', '2020-04-24 11:56:37');
INSERT INTO `sys_loginfo` VALUES ('717', '李四-ls', '127.0.0.1', '2020-04-24 11:57:53');
INSERT INTO `sys_loginfo` VALUES ('718', '刘新宇-liuxinyu', '127.0.0.1', '2020-04-24 12:43:08');
INSERT INTO `sys_loginfo` VALUES ('719', '超级管理员-system', '127.0.0.1', '2020-04-24 12:43:33');
INSERT INTO `sys_loginfo` VALUES ('720', '王五-ww', '127.0.0.1', '2020-04-24 12:44:13');
INSERT INTO `sys_loginfo` VALUES ('721', '超级管理员-system', '127.0.0.1', '2020-04-24 12:44:41');
INSERT INTO `sys_loginfo` VALUES ('722', '超级管理员-system', '127.0.0.1', '2020-04-24 13:31:03');
INSERT INTO `sys_loginfo` VALUES ('723', '二货-erhuo', '127.0.0.1', '2020-04-24 15:17:31');
INSERT INTO `sys_loginfo` VALUES ('724', '李四-ls', '127.0.0.1', '2020-04-24 15:23:59');
INSERT INTO `sys_loginfo` VALUES ('725', '超级管理员-system', '127.0.0.1', '2020-04-24 15:46:45');
INSERT INTO `sys_loginfo` VALUES ('726', '烦操-fancao', '127.0.0.1', '2020-04-24 16:15:17');
INSERT INTO `sys_loginfo` VALUES ('727', '超级管理员-system', '127.0.0.1', '2020-04-25 05:32:15');
INSERT INTO `sys_loginfo` VALUES ('728', '李四-ls', '127.0.0.1', '2020-04-25 06:12:29');
INSERT INTO `sys_loginfo` VALUES ('729', '超级管理员-system', '127.0.0.1', '2020-04-25 06:12:38');
INSERT INTO `sys_loginfo` VALUES ('730', '超级管理员-system', '127.0.0.1', '2020-04-25 12:29:21');
INSERT INTO `sys_loginfo` VALUES ('731', '李四-ls', '127.0.0.1', '2020-04-25 14:49:56');
INSERT INTO `sys_loginfo` VALUES ('732', '超级管理员-system', '127.0.0.1', '2020-04-26 04:38:15');
INSERT INTO `sys_loginfo` VALUES ('733', '超级管理员-system', '127.0.0.1', '2020-04-26 04:53:25');
INSERT INTO `sys_loginfo` VALUES ('734', '超级管理员-system', '127.0.0.1', '2020-04-26 04:58:32');
INSERT INTO `sys_loginfo` VALUES ('735', '超级管理员-system', '127.0.0.1', '2020-04-26 06:32:36');
INSERT INTO `sys_loginfo` VALUES ('736', '超级管理员-system', '127.0.0.1', '2020-04-26 06:37:09');
INSERT INTO `sys_loginfo` VALUES ('737', '超级管理员-system', '127.0.0.1', '2020-04-26 07:56:32');
INSERT INTO `sys_loginfo` VALUES ('738', '超级管理员-system', '127.0.0.1', '2020-04-26 07:57:15');
INSERT INTO `sys_loginfo` VALUES ('739', '李四-ls', '127.0.0.1', '2020-04-26 09:01:54');
INSERT INTO `sys_loginfo` VALUES ('740', '超级管理员-system', '127.0.0.1', '2020-04-26 11:56:00');
INSERT INTO `sys_loginfo` VALUES ('741', '超级管理员-system', '127.0.0.1', '2020-04-26 12:14:31');
INSERT INTO `sys_loginfo` VALUES ('742', '超级管理员-system', '127.0.0.1', '2020-04-26 13:09:38');
INSERT INTO `sys_loginfo` VALUES ('743', '超级管理员-system', '127.0.0.1', '2020-04-26 13:11:50');
INSERT INTO `sys_loginfo` VALUES ('744', '超级管理员-system', '127.0.0.1', '2020-04-26 13:12:05');
INSERT INTO `sys_loginfo` VALUES ('745', '超级管理员-system', '127.0.0.1', '2020-04-26 13:18:28');
INSERT INTO `sys_loginfo` VALUES ('746', '超级管理员-system', '127.0.0.1', '2020-04-26 15:10:06');
INSERT INTO `sys_loginfo` VALUES ('747', '李四-ls', '127.0.0.1', '2020-04-26 15:14:22');
INSERT INTO `sys_loginfo` VALUES ('748', '超级管理员-system', '127.0.0.1', '2020-04-26 15:58:12');
INSERT INTO `sys_loginfo` VALUES ('749', '超级管理员-system', '127.0.0.1', '2020-04-26 16:36:36');
INSERT INTO `sys_loginfo` VALUES ('750', '超级管理员-system', '127.0.0.1', '2020-04-26 16:37:01');
INSERT INTO `sys_loginfo` VALUES ('751', '超级管理员-system', '127.0.0.1', '2020-04-26 16:38:29');
INSERT INTO `sys_loginfo` VALUES ('752', '超级管理员-system', '127.0.0.1', '2020-04-26 16:42:23');
INSERT INTO `sys_loginfo` VALUES ('753', '超级管理员-system', '127.0.0.1', '2020-04-27 03:56:23');
INSERT INTO `sys_loginfo` VALUES ('754', '超级管理员-system', '127.0.0.1', '2020-04-27 03:57:25');
INSERT INTO `sys_loginfo` VALUES ('755', '超级管理员-system', '127.0.0.1', '2020-04-27 05:15:36');
INSERT INTO `sys_loginfo` VALUES ('756', '超级管理员-system', '127.0.0.1', '2020-04-27 05:19:04');
INSERT INTO `sys_loginfo` VALUES ('757', '李四-ls', '127.0.0.1', '2020-04-27 05:19:32');
INSERT INTO `sys_loginfo` VALUES ('758', '超级管理员-system', '127.0.0.1', '2020-04-27 05:19:41');
INSERT INTO `sys_loginfo` VALUES ('759', '超级管理员-system', '127.0.0.1', '2020-04-27 05:20:25');
INSERT INTO `sys_loginfo` VALUES ('760', '超级管理员-system', '127.0.0.1', '2020-04-27 05:20:51');
INSERT INTO `sys_loginfo` VALUES ('761', '超级管理员-system', '127.0.0.1', '2020-04-27 05:21:55');
INSERT INTO `sys_loginfo` VALUES ('762', '超级管理员-system', '127.0.0.1', '2020-04-27 05:24:33');
INSERT INTO `sys_loginfo` VALUES ('763', '超级管理员-system', '127.0.0.1', '2020-04-27 05:25:05');
INSERT INTO `sys_loginfo` VALUES ('764', '超级管理员-system', '127.0.0.1', '2020-04-27 05:25:20');
INSERT INTO `sys_loginfo` VALUES ('765', '超级管理员-system', '127.0.0.1', '2020-04-27 05:25:26');
INSERT INTO `sys_loginfo` VALUES ('766', '超级管理员-system', '127.0.0.1', '2020-04-27 05:25:31');
INSERT INTO `sys_loginfo` VALUES ('767', '超级管理员-system', '127.0.0.1', '2020-04-27 05:26:09');
INSERT INTO `sys_loginfo` VALUES ('768', '超级管理员-system', '127.0.0.1', '2020-04-27 06:07:34');
INSERT INTO `sys_loginfo` VALUES ('769', '超级管理员-system', '127.0.0.1', '2020-04-27 07:00:56');
INSERT INTO `sys_loginfo` VALUES ('770', '超级管理员-system', '127.0.0.1', '2020-04-27 07:15:48');
INSERT INTO `sys_loginfo` VALUES ('771', '超级管理员-system', '127.0.0.1', '2020-04-27 08:04:33');
INSERT INTO `sys_loginfo` VALUES ('772', '李四-ls', '127.0.0.1', '2020-04-27 10:02:27');
INSERT INTO `sys_loginfo` VALUES ('773', '超级管理员-system', '127.0.0.1', '2020-04-27 10:17:15');
INSERT INTO `sys_loginfo` VALUES ('774', '超级管理员-system', '127.0.0.1', '2020-04-27 11:24:49');
INSERT INTO `sys_loginfo` VALUES ('775', '超级管理员-system', '127.0.0.1', '2020-04-27 12:41:11');
INSERT INTO `sys_loginfo` VALUES ('776', '超级管理员-system', '127.0.0.1', '2020-04-27 13:32:20');
INSERT INTO `sys_loginfo` VALUES ('777', '超级管理员-system', '127.0.0.1', '2020-04-27 14:12:38');
INSERT INTO `sys_loginfo` VALUES ('778', '超级管理员-system', '127.0.0.1', '2020-04-27 15:22:05');
INSERT INTO `sys_loginfo` VALUES ('779', '超级管理员-system', '127.0.0.1', '2020-04-28 04:06:57');
INSERT INTO `sys_loginfo` VALUES ('780', '超级管理员-system', '127.0.0.1', '2020-04-28 05:00:06');
INSERT INTO `sys_loginfo` VALUES ('781', '超级管理员-system', '127.0.0.1', '2020-04-28 05:58:59');
INSERT INTO `sys_loginfo` VALUES ('782', '超级管理员-system', '127.0.0.1', '2020-04-28 06:02:10');
INSERT INTO `sys_loginfo` VALUES ('783', '超级管理员-system', '127.0.0.1', '2020-04-28 06:02:41');
INSERT INTO `sys_loginfo` VALUES ('784', '超级管理员-system', '127.0.0.1', '2020-04-28 06:04:21');
INSERT INTO `sys_loginfo` VALUES ('785', '超级管理员-system', '223.152.95.106', '2020-04-28 07:14:16');
INSERT INTO `sys_loginfo` VALUES ('786', '超级管理员-system', '223.152.95.106', '2020-04-28 07:23:38');
INSERT INTO `sys_loginfo` VALUES ('787', '超级管理员-system', '120.231.221.201', '2020-04-28 07:39:17');
INSERT INTO `sys_loginfo` VALUES ('788', '超级管理员-system', '127.0.0.1', '2020-04-28 07:52:32');
INSERT INTO `sys_loginfo` VALUES ('789', '超级管理员-system', '127.0.0.1', '2020-04-28 07:52:59');
INSERT INTO `sys_loginfo` VALUES ('790', '超级管理员-system', '127.0.0.1', '2020-04-28 07:54:59');
INSERT INTO `sys_loginfo` VALUES ('791', '超级管理员-system', '127.0.0.1', '2020-04-28 07:56:21');
INSERT INTO `sys_loginfo` VALUES ('792', '超级管理员-system', '223.152.95.106', '2020-04-28 08:02:16');
INSERT INTO `sys_loginfo` VALUES ('793', '超级管理员-system', '223.152.95.106', '2020-04-28 08:02:54');
INSERT INTO `sys_loginfo` VALUES ('794', '超级管理员-system', '127.0.0.1', '2020-04-28 08:03:41');
INSERT INTO `sys_loginfo` VALUES ('795', '超级管理员-system', '127.0.0.1', '2020-04-28 08:04:44');
INSERT INTO `sys_loginfo` VALUES ('796', '超级管理员-system', '127.0.0.1', '2020-04-28 08:04:51');
INSERT INTO `sys_loginfo` VALUES ('797', '超级管理员-system', '127.0.0.1', '2020-04-28 08:05:32');
INSERT INTO `sys_loginfo` VALUES ('798', '超级管理员-system', '127.0.0.1', '2020-04-28 08:06:38');
INSERT INTO `sys_loginfo` VALUES ('799', '超级管理员-system', '127.0.0.1', '2020-04-28 08:14:01');
INSERT INTO `sys_loginfo` VALUES ('800', '超级管理员-system', '127.0.0.1', '2020-04-28 08:18:53');
INSERT INTO `sys_loginfo` VALUES ('801', '超级管理员-system', '127.0.0.1', '2020-04-28 08:21:30');
INSERT INTO `sys_loginfo` VALUES ('802', '超级管理员-system', '223.152.95.106', '2020-04-28 08:31:17');
INSERT INTO `sys_loginfo` VALUES ('803', '超级管理员-system', '223.152.95.106', '2020-04-28 08:39:57');
INSERT INTO `sys_loginfo` VALUES ('804', '超级管理员-system', '36.148.57.48', '2020-04-28 08:39:58');
INSERT INTO `sys_loginfo` VALUES ('805', '超级管理员-system', '120.231.221.201', '2020-04-28 08:40:42');
INSERT INTO `sys_loginfo` VALUES ('806', '超级管理员-system', '223.152.95.106', '2020-04-28 08:44:08');
INSERT INTO `sys_loginfo` VALUES ('807', '超级管理员-system', '223.152.95.106', '2020-04-28 08:47:11');
INSERT INTO `sys_loginfo` VALUES ('808', '超级管理员-system', '127.0.0.1', '2020-04-28 08:53:07');
INSERT INTO `sys_loginfo` VALUES ('809', '超级管理员-system', '223.152.95.106', '2020-04-28 08:55:53');
INSERT INTO `sys_loginfo` VALUES ('810', '超级管理员-system', '223.152.95.106', '2020-04-28 09:31:16');
INSERT INTO `sys_loginfo` VALUES ('811', '超级管理员-system', '223.152.95.106', '2020-04-28 09:34:44');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) DEFAULT NULL COMMENT '父级菜单ID',
  `type` varchar(255) DEFAULT NULL COMMENT '类型[topmenu/leftmenu/permission]',
  `typecode` varchar(255) DEFAULT NULL COMMENT 'topmenu:system/business\r\npermission:menu:addMenu\r\n',
  `title` varchar(255) DEFAULT NULL COMMENT '名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `href` varchar(255) DEFAULT NULL COMMENT '连接地址',
  `target` varchar(255) DEFAULT '_self',
  `spread` int(11) DEFAULT NULL COMMENT '是否展开',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码',
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', 'topmenu', 'business', '业务管理', 'fa fa-android', '', '_self', '0', '1', '1');
INSERT INTO `sys_menu` VALUES ('2', '0', 'topmenu', 'system', '系统管理', 'fa fa-gear', '', '_self', '0', '2', '1');
INSERT INTO `sys_menu` VALUES ('3', '0', 'topmenu', 'nb666', 'nb666个人简介', 'fa fa-address-book', '', '_self', '0', '3', '1');
INSERT INTO `sys_menu` VALUES ('4', '1', 'leftmenu', 'business', '基础数据管理', 'fa fa-paint-brush', '', '_self', '0', '4', '1');
INSERT INTO `sys_menu` VALUES ('5', '1', 'leftmenu', 'business', '进货管理', 'fa fa-gear', '', '_self', '0', '5', '1');
INSERT INTO `sys_menu` VALUES ('6', '1', 'leftmenu', 'business', '销售管理', 'fa fa-gear', '', '_self', '0', '6', '1');
INSERT INTO `sys_menu` VALUES ('7', '4', 'leftmenu', 'business', '客户管理', 'fa fa fa fa fa-address-card-o', 'resources/page/business/customer/customerManager.html', '_self', '0', '7', '1');
INSERT INTO `sys_menu` VALUES ('8', '4', 'leftmenu', 'business', '供应商管理', 'fa fa fa-gear', 'resources/page/business/provider/providerManager.html', '_self', '0', '8', '1');
INSERT INTO `sys_menu` VALUES ('9', '4', 'leftmenu', 'business', '商品管理', 'fa fa fa-gear', 'resources/page/business/goods/goodsManager.html', '_self', '0', '9', '1');
INSERT INTO `sys_menu` VALUES ('10', '5', 'leftmenu', 'business', '商品进货', 'fa fa fa-gear', 'resources/page/business/inport/inportManager.html', '_self', '0', '10', '1');
INSERT INTO `sys_menu` VALUES ('11', '5', 'leftmenu', 'business', '商品退货查询', 'fa fa fa fa fa fa fa-gear', 'resources/page/business/outport/outportManager.html', '_self', '0', '11', '1');
INSERT INTO `sys_menu` VALUES ('12', '6', 'leftmenu', 'business', '商品销售', 'fa fa fa fa-gear', 'resources/page/business/sales/salesManaher.html', '_self', '0', '12', '1');
INSERT INTO `sys_menu` VALUES ('13', '6', 'leftmenu', 'business', '销售退货查询', 'fa fa fa fa fa fa-gear', 'resources/page/business/salesback/salesbackManager.html', '_self', '0', '13', '1');
INSERT INTO `sys_menu` VALUES ('14', '2', 'leftmenu', 'system', '系统管理', 'fa fa-gear', '', '_self', '0', '14', '1');
INSERT INTO `sys_menu` VALUES ('15', '2', 'leftmenu', 'system', '其它管理', 'fa fa-gear', '', '_self', '0', '15', '1');
INSERT INTO `sys_menu` VALUES ('16', '14', 'leftmenu', 'system', '部门管理', 'fa fa-gear', 'resources/page/system/dept/deptManager.html', '_self', '0', '16', '1');
INSERT INTO `sys_menu` VALUES ('17', '14', 'leftmenu', 'system', '菜单管理', 'fa fa-gear', 'resources/page/system/menu/MenuManager.html', '_self', '0', '17', '1');
INSERT INTO `sys_menu` VALUES ('18', '14', 'leftmenu', 'system', '角色管理', 'fa fa fa-gear', 'resources/page/system/role/roleManager.html', '_self', '0', '18', '1');
INSERT INTO `sys_menu` VALUES ('19', '14', 'leftmenu', 'system', '用户管理', 'fa fa fa-gear', 'resources/page/system/user/userManager.html', '_self', '0', '19', '1');
INSERT INTO `sys_menu` VALUES ('20', '15', 'leftmenu', 'system', '登陆日志', 'fa fa-gear', 'resources/page/system/loginfo/loginfoManager.html', '_self', '0', '20', '1');
INSERT INTO `sys_menu` VALUES ('21', '15', 'leftmenu', 'system', '数据源监控', 'fa fa-gear', 'http://localhost:8080/druid/index.html', '_self', '0', '21', '1');
INSERT INTO `sys_menu` VALUES ('22', '15', 'leftmenu', 'system', '系统公告', 'fa fa-gear', 'resources/page/system/notice/noticeManager.html', '_self', '0', '22', '1');
INSERT INTO `sys_menu` VALUES ('23', '15', 'leftmenu', 'system', '图标管理', 'fa fa-gear', 'resources/page/icon.html', '_self', '0', '23', '1');
INSERT INTO `sys_menu` VALUES ('24', '7', 'permission', 'customer:query', '客户查询', 'fa fa-gear', null, '_self', '0', '24', '1');
INSERT INTO `sys_menu` VALUES ('25', '7', 'permission', 'customer:add', '客户添加', 'fa fa-gear', null, '_self', '0', '25', '1');
INSERT INTO `sys_menu` VALUES ('26', '7', 'permission', 'customer:delete', '客户删除', 'fa fa-gear', null, '_self', '0', '26', '1');
INSERT INTO `sys_menu` VALUES ('27', '7', 'permission', 'customer:update', '客户更新', 'fa fa-gear', null, '_self', '0', '27', '1');
INSERT INTO `sys_menu` VALUES ('28', '3', 'leftmenu', 'nb666', 'Java工程师', 'fa fa-bitbucket', '', '_self', '0', '28', '1');
INSERT INTO `sys_menu` VALUES ('29', '3', 'leftmenu', 'nb666', '前端工程师', 'fa fa-android', '', '_self', '0', '29', '1');
INSERT INTO `sys_menu` VALUES ('30', '3', 'leftmenu', 'nb666', '运维小能手', 'fa fa-hard-of-hearing', '', '_self', '0', '30', '1');
INSERT INTO `sys_menu` VALUES ('31', '3', 'leftmenu', 'nb666', 'GO语言', 'fa fa-google', '', '_self', '0', '31', '1');
INSERT INTO `sys_menu` VALUES ('36', '15', 'leftmenu', 'system', '操作日志', 'fa fa-gear', 'resources/page/system/systemLog/systemLog.html', '_self', '0', '32', '1');
INSERT INTO `sys_menu` VALUES ('42', '0', 'topmenu', 'other', '其它管理', 'fa fa fa fa-500px', '', '_self', '0', '33', '1');
INSERT INTO `sys_menu` VALUES ('43', '42', 'leftmenu', '必须得输入', '图标选择器', 'fa fa fa fa-image', 'resources/page/icon-picker.html', '_self', '0', '34', '1');
INSERT INTO `sys_menu` VALUES ('46', '0', 'topmenu', 'test', '测试呀', 'fa fa-address-card', '', '_self', '0', '37', '1');
INSERT INTO `sys_menu` VALUES ('47', '7', 'permission', 'customer:export', '客户导出', 'fa fa fa fa-joomla', '', '_self', '0', '38', '1');
INSERT INTO `sys_menu` VALUES ('51', '16', 'permission', 'dept:add', '部门添加', 'fa fa-android', '', '_self', '0', '42', '1');
INSERT INTO `sys_menu` VALUES ('52', '16', 'permission', 'dept:update', '部门修改', 'fa fa-android', '', '_self', '0', '43', '1');
INSERT INTO `sys_menu` VALUES ('53', '16', 'permission', 'dept:delete', '部门删除', 'fa fa-android', '', '_self', '0', '44', '1');
INSERT INTO `sys_menu` VALUES ('54', '17', 'permission', 'menu:add', '菜单添加', 'fa fa-sort-numeric-asc', '', '_self', '0', '45', '1');
INSERT INTO `sys_menu` VALUES ('55', '17', 'permission', 'menu:update', '菜单修改', 'fa fa-edit', '', '_self', '0', '46', '1');
INSERT INTO `sys_menu` VALUES ('56', '17', 'permission', 'menu:delete', '菜单删除', 'fa fa-delicious', '', '_self', '0', '47', '1');
INSERT INTO `sys_menu` VALUES ('57', '46', 'leftmenu', 'swgger2', '丝袜哥(swgger2) API文档 查看', 'fa fa fa fa-asterisk', 'http://127.0.0.1:8080/swagger-ui.html', '_self', '0', '48', '1');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `createtime` datetime DEFAULT NULL,
  `opername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '知识简单解答', '<div style=\"touch-action: pan-y; color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\"><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">本文以淘宝为例，介绍从一百个并发到千万级并发下服务端架构的演进过程，同时列举出每个演进阶段遇到的相关技术，让大家对架构的演进有一个整体的认知，文章最后汇总了一些架构设计的原则。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">在介绍架构之前，为了避免部分读者对架构设计中的一些概念不了解，下面对几个最基础的概念进行介绍：</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">①分布式：系统中的多个模块在不同服务器上部署，即可称为分布式系统，如 Tomcat 和数据库分别部署在不同的服务器上，或两个相同功能的 Tomcat 分别部署在不同服务器上。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">②高可用：系统中部分节点失效时，其他节点能够接替它继续提供服务，则可认为系统具有高可用性。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">③集群：一个特定领域的软件部署在多台服务器上并作为一个整体提供一类服务，这个整体称为集群。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">如 Zookeeper 中的 Master 和 Slave 分别部署在多台服务器上，共同组成一个整体提供集中配置服务。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">在常见的集群中，客户端往往能够连接任意一个节点获得服务，并且当集群中一个节点掉线时，其他节点往往能够自动的接替它继续提供服务，这时候说明集群具有高可用性。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">④负载均衡：请求发送到系统时，通过某些方式把请求均匀分发到多个节点上，使系统中每个节点能够均匀的处理请求负载，则可认为系统是负载均衡的。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">⑤正向代理和反向代理：系统内部要访问外部网络时，统一通过一个代理服务器把请求转发出去，在外部网络看来就是代理服务器发起的访问，此时代理服务器实现的是正向代理。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">当外部请求进入系统时，代理服务器把该请求转发到系统中的某台服务器上，对外部请求来说，与之交互的只有代理服务器，此时代理服务器实现的是反向代理。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\">简单来说，正向代理是代理服务器代替系统内部来访问外部网络的过程，反向代理是外部请求访问系统时通过代理服务器转发到内部服务器的过程。</p><p style=\"touch-action: pan-y; margin-top: 0px; margin-bottom: 0px;\"><strong style=\"touch-action: pan-y;\">架构演进</strong></p></div><p><br style=\"touch-action: pan-y; color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\"><br style=\"touch-action: pan-y; color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\"><span style=\"color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\">作者：戏入子迷</span><br style=\"touch-action: pan-y; color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\"><span style=\"color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\">链接：https://www.jianshu.com/p/537b3ee7229d</span><br style=\"touch-action: pan-y; color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\"><span style=\"color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\">来源：简书</span><br style=\"touch-action: pan-y; color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\"><span style=\"color: rgb(255, 255, 255); font-size: 12px; text-align: justify; background-color: rgb(47, 64, 86);\">著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span>', '2020-04-16 08:50:28', '超级管理员');
INSERT INTO `sys_notice` VALUES ('6', '啊啊啊11111', '<p>啊啊啊<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/50/pcmoren_huaixiao_org.png\" alt=\"[坏笑]\" data-w-e=\"1\"></p>', '2020-04-16 11:27:48', '超级管理员');
INSERT INTO `sys_notice` VALUES ('7', '测试数据呀', '<p>啊达娃打网球</p>', '2020-04-16 13:31:52', '超级管理员');
INSERT INTO `sys_notice` VALUES ('8', '哼哼[污]', '<p style=\"text-align: center;\">哼哼<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\">\n\n</p><p><br></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span>哼哼<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\"></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span>哼哼<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\"></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span>哼哼<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\"></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span>哼哼<img src=\"http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal/3c/pcmoren_wu_org.png\" alt=\"[污]\"></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span></p>', '2020-04-16 13:32:21', '超级管理员');
INSERT INTO `sys_notice` VALUES ('9', '嘻嘻', '<p>草泥马的</p>', '2020-04-16 14:51:00', '超级管理员');
INSERT INTO `sys_notice` VALUES ('10', '敏感操作', '<p>layer.prompt({<br>formType: 1,<br>value: \'\',<br>maxlength: 6, //可输入文本的最大长度，默认500<br>title: \'敏感操作，请输入口令\'<br>}, function(value, index, elem) {<br>if (value == \'yby123\') {<br>$.post(api   \"notice/deleteNotice\", {<br>id: data.id<br>}, function(res) {<br>layer.msg(res.msg);<br>obj.del();<br>layer.close(index);<br>})<br>} else {<br>layer.msg(\"口令错误\");<br>}<br>});</p><p></p><p><br></p><p>\n\n</p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span></p>', '2020-04-16 15:18:36', '超级管理员');
INSERT INTO `sys_notice` VALUES ('11', '<font color=\"red\">springBoot 前后端分离项目 跨域问题</font>', '<h3><pre>package com.jun.plugin.system.config;<br><br>import org.springframework.context.annotation.Bean;<br>import org.springframework.context.annotation.Configuration;<br>import org.springframework.web.cors.CorsConfiguration;<br>import org.springframework.web.cors.UrlBasedCorsConfigurationSource;<br>import org.springframework.web.filter.CorsFilter;<br><br>/**<br> * ClassName: CorsAutoConfig<br> * Description: layui<br> * date: 2020/4/14 18:30<br> *<br> * <br> * <br> * @date 前后端  解决跨域问题<br> * @since JDK 1.8<br> */<br>@Configuration<br>public class CorsAutoConfig {<br>    @Bean<br>    public CorsFilter corsFilter() {<br>        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();<br>        CorsConfiguration corsConfiguration = new CorsConfiguration();<br>        corsConfiguration.addAllowedHeader(\"*\");<br>        corsConfiguration.addAllowedMethod(\"*\");<br>        // 表示什么域名跨域 *表示全部都跨域<br>        corsConfiguration.addAllowedOrigin(\"*\");<br>        // 注入进去<br>        urlBasedCorsConfigurationSource.registerCorsConfiguration(\"/**\", corsConfiguration);<br><br>        CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);<br>        return corsFilter;<br>    }<br>}</pre><br>\n\n<p><br></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span></p></h3>', '2020-04-21 07:40:39', '超级管理员');
INSERT INTO `sys_notice` VALUES ('12', '生理期肚子疼怎么办', '<p>①多喝热的药草茶或热柠檬汁。<br><br>②喝红糖水，如果红糖水里面含有姜的话会更好，能够帮助减轻一些疼痛。<br><br>③找来一小把花椒，将其碾碎。加入水烧开，将花椒放入锅中，同时切入一些姜片，待煮沸两次即可停火。将其导入杯中，待水温适宜就喝一杯。<br><br>④洗浴，在温水缸里加入1杯海盐及1杯碳酸氢钠，泡20分钟，有助于松弛肌肉及缓和经痛。<br><br>⑤在腹部放置热敷垫或热水瓶，一次数分钟。最好不要直接挨着皮肤，热水容易烫伤，可以直接隔着内衣放上去。<br><br>⑥买个暖贴，贴在自己的小腹，以及腰部。隔着衣服贴上去就可以了。<br><br>⑦五红汤，用红糖、枸杞、花生、红枣、红豆一起煨制糖水喝下去，补血活血，所谓痛则不通，通畅之后就舒缓许多。<br>', '2020-04-22 11:19:28', '超级管理员');
INSERT INTO `sys_notice` VALUES ('13', '<font color=\"red\">都是新鲜感啦</font>', '<p style=\"text-align: left;\">情感方百面上的新鲜感：一个人刚开始接触一个陌生人或者新事物，这时候对该对象产生巨大的好奇感，人们常称之为新鲜感。处于新鲜感时期的人会对该对象无时无刻的关注，会想方设法的去深入了解甚至有直接接触\n\n</p><p style=\"text-align: left;\"><span style=\"font-weight: bold;\">保持新鲜感的方法</span>：</p><p style=\"text-align: left;\">一、情感方面上的新鲜感：情感方面多指爱情上的新鲜感保持，热恋过后的情侣会对另一半褪去了当初回的新鲜感，失去了新鲜感的情侣很容易导致分手。保持爱情上的新鲜感可以给对方逢年过节准备适当的惊喜，在自身方面也许不断进步让伴侣保持对你的好奇心。</p><p style=\"text-align: left;\">二、事物方面上的新鲜感：食材上的新鲜感保持有助于顾客的选购，保持食材的新鲜感可以在蔬菜水果表面上喷上水珠，其次答需要保持食材表面的干净清洁，食材表面的光泽色彩会直接影响给人新鲜感的感觉。</p><p style=\"text-align: left;\"><br></p><p>\n\n</p><p></p><div style=\"text-align: left;\"><span style=\"color: rgb(194, 79, 74);\">作者：nb666</span></div>', '2020-04-22 15:15:11', '超级管理员');
INSERT INTO `sys_notice` VALUES ('14', '完成登陆问题', '<p>完成根据用户拥有的角色来进行 显示不同的菜单</p><pre><code># 查询菜单 不包括权限<br>select * from sys_menu <br>where available = 1<br>and (type = \'topmenu\' or type = \'leftmenu\') and <br>id in(<br>			#  查询 菜单id<br>				select DISTINCT mid AS id from sys_role_menu where<br>				rid in (<br>				# 查询 角色id<br>				select ru.rid from sys_role_user as ru where ru.uid = 4<br>				)<br><br>);</code></pre><p><br>\n\n</p><p><br></p><p><span style=\"color: rgb(194, 79, 74);\">\n								作者：nb666 <br>\n								链接：https://www.nb666.top/ <br>\n								来源：前后端分离 <br>\n								著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</span></p>', '2020-04-22 17:55:46', '刘总');
INSERT INTO `sys_notice` VALUES ('16', '长期熬夜的危害', '<p style=\"text-align: center;\"></p><blockquote><ul><li style=\"text-align: center;\"><span style=\"color: rgb(249, 150, 59);\">很重要啊啊啊啊啊啊</span></li></ul></blockquote>', '2020-04-24 04:26:38', '李四');
INSERT INTO `sys_notice` VALUES ('17', '改造菜单循序', '<h1>使用链形存储数据</h1><p><br></p><h1>利用数组机制进行存储数据</h1><h1><p><br></p><p><br></p><p>作者：nb666', '2020-04-27 10:25:53', '超级管理员');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '拥有所有菜单权限', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('4', '基础数据管理员', '基础数据管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('5', '仓库管理员', '仓库管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('6', '销售管理员', '销售管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('7', '系统管理员', '系统管理员', '1', '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES ('16', '测试人员', 'nb666呀', '1', '2020-04-20 03:15:17');
INSERT INTO `sys_role` VALUES ('17', '默认角色', '只有用户权限', '1', '2020-04-24 16:12:13');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `rid` int(11) NOT NULL COMMENT '角色外键',
  `mid` int(11) NOT NULL COMMENT '菜单外键',
  PRIMARY KEY (`mid`,`rid`) USING BTREE,
  KEY `FK_tcsr9ucxypb3ce1q5qngsfk33` (`rid`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1');
INSERT INTO `sys_role_menu` VALUES ('1', '2');
INSERT INTO `sys_role_menu` VALUES ('1', '3');
INSERT INTO `sys_role_menu` VALUES ('1', '4');
INSERT INTO `sys_role_menu` VALUES ('1', '5');
INSERT INTO `sys_role_menu` VALUES ('1', '6');
INSERT INTO `sys_role_menu` VALUES ('1', '7');
INSERT INTO `sys_role_menu` VALUES ('1', '8');
INSERT INTO `sys_role_menu` VALUES ('1', '9');
INSERT INTO `sys_role_menu` VALUES ('1', '10');
INSERT INTO `sys_role_menu` VALUES ('1', '11');
INSERT INTO `sys_role_menu` VALUES ('1', '12');
INSERT INTO `sys_role_menu` VALUES ('1', '13');
INSERT INTO `sys_role_menu` VALUES ('1', '14');
INSERT INTO `sys_role_menu` VALUES ('1', '15');
INSERT INTO `sys_role_menu` VALUES ('1', '16');
INSERT INTO `sys_role_menu` VALUES ('1', '17');
INSERT INTO `sys_role_menu` VALUES ('1', '18');
INSERT INTO `sys_role_menu` VALUES ('1', '19');
INSERT INTO `sys_role_menu` VALUES ('1', '20');
INSERT INTO `sys_role_menu` VALUES ('1', '21');
INSERT INTO `sys_role_menu` VALUES ('1', '22');
INSERT INTO `sys_role_menu` VALUES ('1', '23');
INSERT INTO `sys_role_menu` VALUES ('1', '24');
INSERT INTO `sys_role_menu` VALUES ('1', '25');
INSERT INTO `sys_role_menu` VALUES ('1', '26');
INSERT INTO `sys_role_menu` VALUES ('1', '27');
INSERT INTO `sys_role_menu` VALUES ('1', '28');
INSERT INTO `sys_role_menu` VALUES ('1', '29');
INSERT INTO `sys_role_menu` VALUES ('1', '30');
INSERT INTO `sys_role_menu` VALUES ('1', '31');
INSERT INTO `sys_role_menu` VALUES ('1', '36');
INSERT INTO `sys_role_menu` VALUES ('1', '42');
INSERT INTO `sys_role_menu` VALUES ('1', '43');
INSERT INTO `sys_role_menu` VALUES ('1', '46');
INSERT INTO `sys_role_menu` VALUES ('1', '47');
INSERT INTO `sys_role_menu` VALUES ('1', '51');
INSERT INTO `sys_role_menu` VALUES ('1', '52');
INSERT INTO `sys_role_menu` VALUES ('1', '53');
INSERT INTO `sys_role_menu` VALUES ('1', '54');
INSERT INTO `sys_role_menu` VALUES ('1', '55');
INSERT INTO `sys_role_menu` VALUES ('1', '56');
INSERT INTO `sys_role_menu` VALUES ('4', '1');
INSERT INTO `sys_role_menu` VALUES ('4', '4');
INSERT INTO `sys_role_menu` VALUES ('4', '7');
INSERT INTO `sys_role_menu` VALUES ('4', '8');
INSERT INTO `sys_role_menu` VALUES ('4', '9');
INSERT INTO `sys_role_menu` VALUES ('4', '24');
INSERT INTO `sys_role_menu` VALUES ('4', '25');
INSERT INTO `sys_role_menu` VALUES ('4', '26');
INSERT INTO `sys_role_menu` VALUES ('4', '27');
INSERT INTO `sys_role_menu` VALUES ('4', '47');
INSERT INTO `sys_role_menu` VALUES ('6', '1');
INSERT INTO `sys_role_menu` VALUES ('6', '6');
INSERT INTO `sys_role_menu` VALUES ('6', '12');
INSERT INTO `sys_role_menu` VALUES ('6', '13');
INSERT INTO `sys_role_menu` VALUES ('7', '2');
INSERT INTO `sys_role_menu` VALUES ('7', '14');
INSERT INTO `sys_role_menu` VALUES ('7', '15');
INSERT INTO `sys_role_menu` VALUES ('7', '16');
INSERT INTO `sys_role_menu` VALUES ('7', '17');
INSERT INTO `sys_role_menu` VALUES ('7', '18');
INSERT INTO `sys_role_menu` VALUES ('7', '19');
INSERT INTO `sys_role_menu` VALUES ('7', '20');
INSERT INTO `sys_role_menu` VALUES ('7', '21');
INSERT INTO `sys_role_menu` VALUES ('7', '22');
INSERT INTO `sys_role_menu` VALUES ('7', '23');
INSERT INTO `sys_role_menu` VALUES ('7', '36');
INSERT INTO `sys_role_menu` VALUES ('7', '51');
INSERT INTO `sys_role_menu` VALUES ('7', '52');
INSERT INTO `sys_role_menu` VALUES ('7', '53');
INSERT INTO `sys_role_menu` VALUES ('16', '2');
INSERT INTO `sys_role_menu` VALUES ('16', '14');
INSERT INTO `sys_role_menu` VALUES ('16', '16');
INSERT INTO `sys_role_menu` VALUES ('16', '17');
INSERT INTO `sys_role_menu` VALUES ('16', '51');
INSERT INTO `sys_role_menu` VALUES ('16', '54');
INSERT INTO `sys_role_menu` VALUES ('16', '55');
INSERT INTO `sys_role_menu` VALUES ('16', '56');
INSERT INTO `sys_role_menu` VALUES ('17', '2');
INSERT INTO `sys_role_menu` VALUES ('17', '14');
INSERT INTO `sys_role_menu` VALUES ('17', '19');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `rid` int(11) NOT NULL COMMENT '角色外键',
  `uid` int(11) NOT NULL COMMENT '用户外键',
  PRIMARY KEY (`uid`,`rid`) USING BTREE,
  KEY `FK_203gdpkwgtow7nxlo2oap5jru` (`rid`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '2');
INSERT INTO `sys_role_user` VALUES ('4', '3');
INSERT INTO `sys_role_user` VALUES ('6', '4');
INSERT INTO `sys_role_user` VALUES ('7', '5');
INSERT INTO `sys_role_user` VALUES ('7', '6');
INSERT INTO `sys_role_user` VALUES ('16', '15');
INSERT INTO `sys_role_user` VALUES ('17', '23');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `loginname` varchar(255) NOT NULL COMMENT '登陆名',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `deptid` int(11) DEFAULT NULL COMMENT '部门ID',
  `hiredate` datetime DEFAULT NULL COMMENT '入职时间',
  `ordernum` int(11) DEFAULT NULL,
  `type` int(255) DEFAULT NULL COMMENT '用户类型[0超级管理员1普通用户]',
  `imgpath` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  `available` int(11) DEFAULT '1' COMMENT ' 是否可用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sys_user_loginname` (`loginname`) USING BTREE,
  KEY `FK_3rrcpvho2w1mx1sfiuuyir1h` (`deptid`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`deptid`) REFERENCES `sys_dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '超级管理员', 'system', '系统深处的男人', '1', '超级管理员', '532ac00e86893901af5f0be6b704dbc7', '1', '2018-06-25 11:06:34', '1', '0', 'group1/M00/00/00/rBHkkV6i06GADDoTAAhxrMIZycU237.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB', '1');
INSERT INTO `sys_user` VALUES ('2', '李四', 'ls', '武汉', '1', 'KING', 'b07b848d69e0553b80e601d31571797e', '1', '2018-06-25 11:06:36', '2', '1', 'group1/M00/00/00/rBHkkV6i1EqAW31vAAhxrMIZycU818.jpg', 'FC1EE06AE4354D3FBF7FDD15C8FCDA71', '1');
INSERT INTO `sys_user` VALUES ('3', '王五', 'ww', '武汉', '1', '管理员', '3c3f971eae61e097f59d52360323f1c8', '3', '2018-06-25 11:06:38', '3', '1', 'group1/M00/00/00/rBHkkV6i3yyAXW1CAAqH8g4jHPk024.jpg', '3D5F956E053C4E85B7D2681386E235D2', '1');
INSERT INTO `sys_user` VALUES ('4', '赵六', 'zl', '武汉', '1', '程序员', '2e969742a7ea0c7376e9551d578e05dd', '4', '2018-06-25 11:06:40', '4', '1', '/group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif', '6480EE1391E34B0886ACADA501E31145', '1');
INSERT INTO `sys_user` VALUES ('5', '孙七', 'sq', '武汉', '1', '程序员', '47b4c1ad6e4b54dd9387a09cb5a03de1', '2', '2018-06-25 11:06:43', '5', '1', '/group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif', 'FE3476C3E3674E5690C737C269FCBF8E', '1');
INSERT INTO `sys_user` VALUES ('6', '刘八', 'lb', '深圳', '1', '程序员', 'bcee2b05b4b591106829aec69a094806', '4', '2018-08-06 11:21:12', '6', '1', '/group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif', 'E6CCF54A09894D998225878BBD139B20', '1');
INSERT INTO `sys_user` VALUES ('8', '习大大', 'xidada', '北京', '1', '北京', '4731e3b6ff0327d46423e7d3d891d54e', '7', '2019-09-25 08:47:38', '7', '1', '/group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif', '407CDF71F56648B4A81643A9C7F1021F', '1');
INSERT INTO `sys_user` VALUES ('12', '刘新宇', 'liuxinyu', '嘀嘀嘀', '1', '嘀嘀嘀', '4d142e0f18e240763ef4845bf44bc20d', '4', '2020-04-22 05:01:37', '8', '1', 'group1/M00/00/00/rBHkkV6i3uuAFaYBAAHHW5ZHRFQ055.jpg', 'e22ae806fe6340a695e65ebdefa5a525', '1');
INSERT INTO `sys_user` VALUES ('13', 'nb666呀', 'nb666ya', '宝贝', '1', '嘻嘻', 'bacc4a35cb37e14f6fddd59326fbf83c', '41', '2020-04-22 06:32:48', '9', '1', '/group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif', '85cf45c5b05842d29a80d685873e8fd7', '1');
INSERT INTO `sys_user` VALUES ('14', '二货', 'erhuo', '嘻嘻', '1', '哼', 'a34708ec798018e0009eca8bfa05c44d', '41', '2020-04-22 06:46:14', '10', '1', '/group1/M00/00/00/rBHkkV6dtmuALC5bAAjYBWxrayQ371.gif', '9f5279cbc4c6443fa38e44b3b6f40888', '1');
INSERT INTO `sys_user` VALUES ('15', '咳咳咳', 'kekeke', '老baby', '0', '老baby  修改头像', '50df3d36e11f2db6c43331a1bb848995', '40', '2020-04-22 06:57:47', '11', '1', 'group1/M00/00/00/rBHkkV6i0MiAKE3gAAEIZvbBarc013.jpg', 'b5004ba88b7e4e2bb9ad47b589ebcae1', '1');
INSERT INTO `sys_user` VALUES ('16', '难受呀', 'nanshouya', '啊带我去', '1', '啊啊啊啊安全网', 'd175631d68f5facc60c2bb77fbae9ea3', '3', '2020-04-22 06:58:45', '12', '1', '/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg', 'f6a21b9741854df5b01aa958b112f93f', '1');
INSERT INTO `sys_user` VALUES ('19', '1111', '1111', '123', '1', '12312312312', '3d5a009de3d763aab738ab83d4b524a4', '9', '2020-04-24 05:38:06', '14', '1', '/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg', 'c031012411ab49078fc84a303fa36140', '1');
INSERT INTO `sys_user` VALUES ('20', '2', '2', '22', '1', '22', 'bdb0117c1715ca4739faf8eb9caabec2', '39', '2020-04-24 05:38:21', '15', '1', '/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg', '64ca1212652a4d7ebb2c9e42093e9d82', '1');
INSERT INTO `sys_user` VALUES ('21', '3', '3', '33', '1', '33', '770c9e91ede61990cf38c26741cf0324', '39', '2020-04-23 16:00:00', '16', '1', '/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg', 'ce6a56f6a7674c248a7196bb8463e7ee', '1');
INSERT INTO `sys_user` VALUES ('22', '4', '4', '4', '1', '4', 'b02d41bea2b156c6289d1008d0f49a85', '39', '2020-04-24 05:42:26', '17', '1', '/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg', 'daaa49f98ed442ff848df141df5704ee', '1');
INSERT INTO `sys_user` VALUES ('23', '烦操', 'fancao', '难受', '1', '哎', 'de1be8135e8b4056488c9e0bd41aabec', '40', '2020-04-24 16:14:55', '18', '1', '/group1/M00/00/00/rBHkkV6f6SSAKajEAAhxrMIZycU316.jpg', '3ef1089d3e3948c0a92c56f6e821be58', '1');

-- ----------------------------
-- Table structure for systemlog
-- ----------------------------
DROP TABLE IF EXISTS `systemlog`;
CREATE TABLE `systemlog` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `thisName` varchar(50) DEFAULT NULL,
  `optime` datetime DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `function` varchar(255) DEFAULT NULL,
  `params` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3964 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemlog
-- ----------------------------
INSERT INTO `systemlog` VALUES ('2565', '未知用户', '2020-04-18 11:01:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.SystemLogController:batchDeleteSystemLog', '[[2563,2564,2561,2562]]');
INSERT INTO `systemlog` VALUES ('2566', '未知用户', '2020-04-18 11:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.SystemLogController:loadAllSystemLog', '[{\"page\":1,\"limit\":10,\"available\":null,\"thisName\":null,\"ip\":null,\"address\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2567', '未知用户', '2020-04-18 11:01:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2568', '未知用户', '2020-04-18 11:01:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2569', '未知用户', '2020-04-18 11:01:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2570', '未知用户', '2020-04-18 11:01:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2571', '未知用户', '2020-04-18 12:11:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.SystemLogController:loadAllSystemLog', '[{\"page\":1,\"limit\":10,\"available\":null,\"thisName\":null,\"ip\":null,\"address\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2572', '未知用户', '2020-04-18 12:11:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2573', '未知用户', '2020-04-18 12:11:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2574', '未知用户', '2020-04-18 12:11:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2575', '未知用户', '2020-04-18 12:11:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2576', '未知用户', '2020-04-18 12:11:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2577', '未知用户', '2020-04-18 12:11:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2578', '未知用户', '2020-04-18 12:12:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2579', '未知用户', '2020-04-18 12:12:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2580', '未知用户', '2020-04-18 12:12:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2581', '未知用户', '2020-04-18 12:12:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2582', '未知用户', '2020-04-18 12:12:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2583', '未知用户', '2020-04-18 12:13:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2584', '超级管理员', '2020-04-18 12:13:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2585', '超级管理员', '2020-04-18 12:13:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2586', '超级管理员', '2020-04-18 12:13:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2587', '超级管理员', '2020-04-18 12:13:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2588', '超级管理员', '2020-04-18 12:13:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2589', '超级管理员', '2020-04-18 12:13:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2590', '超级管理员', '2020-04-18 12:13:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2591', '超级管理员', '2020-04-18 12:13:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2592', '超级管理员', '2020-04-18 12:13:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2593', '超级管理员', '2020-04-18 12:13:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2594', '超级管理员', '2020-04-18 12:13:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2595', '超级管理员', '2020-04-18 12:13:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2596', '超级管理员', '2020-04-18 12:13:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2597', '超级管理员', '2020-04-18 12:13:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2598', '超级管理员', '2020-04-18 12:13:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2599', '超级管理员', '2020-04-18 12:13:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2600', '超级管理员', '2020-04-18 12:13:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2601', '超级管理员', '2020-04-18 12:13:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2602', '超级管理员', '2020-04-18 12:34:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2603', '超级管理员', '2020-04-18 12:34:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2604', '超级管理员', '2020-04-18 12:34:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2605', '超级管理员', '2020-04-18 12:34:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2606', '超级管理员', '2020-04-18 12:34:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2607', '超级管理员', '2020-04-18 12:34:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2608', '超级管理员', '2020-04-18 12:34:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2609', '超级管理员', '2020-04-18 12:34:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2610', '超级管理员', '2020-04-18 12:34:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginfoController:loadAllLoginfo', '[{\"page\":1,\"limit\":10,\"available\":null,\"loginname\":null,\"loginip\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2611', '超级管理员', '2020-04-18 12:34:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2612', '超级管理员', '2020-04-18 12:34:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2613', '超级管理员', '2020-04-18 12:34:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2614', '超级管理员', '2020-04-18 12:34:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2615', '超级管理员', '2020-04-18 12:34:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":10,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2616', '超级管理员', '2020-04-18 12:34:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2617', '超级管理员', '2020-04-18 12:34:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.SystemLogController:loadAllSystemLog', '[{\"page\":1,\"limit\":10,\"available\":null,\"thisName\":null,\"ip\":null,\"address\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2618', '未知用户', '2020-04-18 12:50:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2619', '超级管理员', '2020-04-18 12:50:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2620', '超级管理员', '2020-04-18 12:50:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2621', '超级管理员', '2020-04-18 12:50:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2622', '超级管理员', '2020-04-18 12:50:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2623', '超级管理员', '2020-04-18 12:50:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2624', '超级管理员', '2020-04-18 12:50:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2625', '超级管理员', '2020-04-18 12:50:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2626', '未知用户', '2020-04-18 12:53:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2627', '未知用户', '2020-04-18 12:53:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2628', '未知用户', '2020-04-18 12:53:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2629', '未知用户', '2020-04-18 12:53:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2630', '未知用户', '2020-04-18 12:53:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2631', '未知用户', '2020-04-18 12:53:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2632', '未知用户', '2020-04-18 12:53:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2633', '未知用户', '2020-04-18 12:53:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2634', '未知用户', '2020-04-18 12:53:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2635', '未知用户', '2020-04-18 12:53:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[0]');
INSERT INTO `systemlog` VALUES ('2636', '未知用户', '2020-04-18 12:55:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2637', '未知用户', '2020-04-18 12:55:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2638', '未知用户', '2020-04-18 12:55:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2639', '未知用户', '2020-04-18 12:55:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2640', '未知用户', '2020-04-18 12:55:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2641', '未知用户', '2020-04-18 12:55:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2642', '未知用户', '2020-04-18 12:57:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2643', '未知用户', '2020-04-18 12:57:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2644', '未知用户', '2020-04-18 12:57:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2645', '未知用户', '2020-04-18 12:57:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2646', '未知用户', '2020-04-18 12:57:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2647', '未知用户', '2020-04-18 12:57:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2648', '未知用户', '2020-04-18 12:57:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2649', '未知用户', '2020-04-18 12:57:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2650', '未知用户', '2020-04-18 12:57:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2651', '未知用户', '2020-04-18 12:57:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2652', '未知用户', '2020-04-18 12:57:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2653', '未知用户', '2020-04-18 12:57:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2654', '未知用户', '2020-04-18 12:57:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2655', '未知用户', '2020-04-18 12:57:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2656', '未知用户', '2020-04-18 12:57:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2657', '未知用户', '2020-04-18 12:57:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2658', '未知用户', '2020-04-18 12:58:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2659', '未知用户', '2020-04-18 12:58:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2660', '未知用户', '2020-04-18 12:58:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2661', '未知用户', '2020-04-18 12:58:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2662', '未知用户', '2020-04-18 12:58:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2663', '未知用户', '2020-04-18 12:58:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2664', '未知用户', '2020-04-18 12:59:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2665', '未知用户', '2020-04-18 12:59:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2666', '未知用户', '2020-04-18 12:59:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2667', '未知用户', '2020-04-18 12:59:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2668', '未知用户', '2020-04-18 12:59:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2669', '未知用户', '2020-04-18 12:59:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2670', '未知用户', '2020-04-18 13:00:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2671', '未知用户', '2020-04-18 13:00:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2672', '未知用户', '2020-04-18 13:00:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2673', '未知用户', '2020-04-18 13:00:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2674', '未知用户', '2020-04-18 13:00:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2675', '未知用户', '2020-04-18 13:00:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2676', '未知用户', '2020-04-18 13:01:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2677', '未知用户', '2020-04-18 13:01:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2678', '未知用户', '2020-04-18 13:01:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2679', '未知用户', '2020-04-18 13:01:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2680', '未知用户', '2020-04-18 13:01:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2681', '未知用户', '2020-04-18 13:01:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2682', '未知用户', '2020-04-18 13:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2683', '未知用户', '2020-04-18 13:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2684', '未知用户', '2020-04-18 13:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2685', '未知用户', '2020-04-18 13:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2686', '未知用户', '2020-04-18 13:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2687', '未知用户', '2020-04-18 13:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2688', '未知用户', '2020-04-18 13:02:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2689', '未知用户', '2020-04-18 13:02:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2690', '未知用户', '2020-04-18 13:04:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2691', '未知用户', '2020-04-18 13:04:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2692', '未知用户', '2020-04-18 13:04:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2693', '未知用户', '2020-04-18 13:04:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2694', '未知用户', '2020-04-18 13:04:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2695', '未知用户', '2020-04-18 13:04:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2696', '未知用户', '2020-04-18 13:07:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2697', '未知用户', '2020-04-18 13:07:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2698', '未知用户', '2020-04-18 13:07:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2699', '未知用户', '2020-04-18 13:07:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2700', '未知用户', '2020-04-18 13:07:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2701', '未知用户', '2020-04-18 13:07:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2702', '未知用户', '2020-04-18 13:07:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2703', '未知用户', '2020-04-18 13:07:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2704', '未知用户', '2020-04-18 13:07:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2705', '未知用户', '2020-04-18 13:07:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2706', '未知用户', '2020-04-18 13:07:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2707', '未知用户', '2020-04-18 13:07:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2708', '未知用户', '2020-04-18 13:08:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[0]');
INSERT INTO `systemlog` VALUES ('2709', '未知用户', '2020-04-18 13:08:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2710', '未知用户', '2020-04-18 13:08:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[0]');
INSERT INTO `systemlog` VALUES ('2711', '未知用户', '2020-04-18 13:08:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[0]');
INSERT INTO `systemlog` VALUES ('2712', '未知用户', '2020-04-18 13:08:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[14]');
INSERT INTO `systemlog` VALUES ('2713', '未知用户', '2020-04-18 13:08:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2714', '未知用户', '2020-04-18 13:08:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2715', '未知用户', '2020-04-18 13:08:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2716', '未知用户', '2020-04-18 13:08:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2717', '未知用户', '2020-04-18 13:08:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2718', '未知用户', '2020-04-18 13:08:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2719', '未知用户', '2020-04-18 13:09:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2720', '未知用户', '2020-04-18 13:09:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2721', '未知用户', '2020-04-18 13:09:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2722', '未知用户', '2020-04-18 13:09:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2723', '未知用户', '2020-04-18 13:09:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2724', '未知用户', '2020-04-18 13:09:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2725', '未知用户', '2020-04-18 13:09:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2726', '未知用户', '2020-04-18 13:09:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2727', '未知用户', '2020-04-18 13:09:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2728', '未知用户', '2020-04-18 13:09:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2729', '未知用户', '2020-04-18 13:09:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2730', '未知用户', '2020-04-18 13:09:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2731', '未知用户', '2020-04-18 13:11:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2732', '未知用户', '2020-04-18 13:11:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2733', '未知用户', '2020-04-18 13:11:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2734', '未知用户', '2020-04-18 13:11:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2735', '未知用户', '2020-04-18 13:11:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2736', '未知用户', '2020-04-18 13:11:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2737', '未知用户', '2020-04-18 13:11:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2738', '未知用户', '2020-04-18 13:11:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2739', '未知用户', '2020-04-18 13:11:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2740', '未知用户', '2020-04-18 13:11:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2741', '未知用户', '2020-04-18 13:11:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2742', '未知用户', '2020-04-18 13:11:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2743', '未知用户', '2020-04-18 13:12:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2744', '未知用户', '2020-04-18 13:12:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2745', '未知用户', '2020-04-18 13:12:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2746', '未知用户', '2020-04-18 13:12:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2747', '未知用户', '2020-04-18 13:12:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2748', '未知用户', '2020-04-18 13:12:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2749', '未知用户', '2020-04-18 13:13:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2750', '未知用户', '2020-04-18 13:13:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2751', '未知用户', '2020-04-18 13:13:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2752', '未知用户', '2020-04-18 13:13:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2753', '未知用户', '2020-04-18 13:13:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2754', '未知用户', '2020-04-18 13:13:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2755', '未知用户', '2020-04-18 13:13:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2756', '未知用户', '2020-04-18 13:13:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2757', '未知用户', '2020-04-18 13:13:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2758', '未知用户', '2020-04-18 13:13:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2759', '未知用户', '2020-04-18 13:13:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2760', '未知用户', '2020-04-18 13:13:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2761', '未知用户', '2020-04-18 13:13:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2762', '未知用户', '2020-04-18 13:13:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2763', '未知用户', '2020-04-18 13:13:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2764', '未知用户', '2020-04-18 13:13:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2765', '未知用户', '2020-04-18 13:13:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2766', '未知用户', '2020-04-18 13:13:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2767', '未知用户', '2020-04-18 13:13:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2768', '未知用户', '2020-04-18 13:13:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2769', '未知用户', '2020-04-18 13:13:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2770', '未知用户', '2020-04-18 13:14:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2771', '未知用户', '2020-04-18 13:14:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2772', '未知用户', '2020-04-18 13:14:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2773', '未知用户', '2020-04-18 13:14:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2774', '未知用户', '2020-04-18 13:14:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2775', '未知用户', '2020-04-18 13:14:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2776', '未知用户', '2020-04-18 13:15:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2777', '未知用户', '2020-04-18 13:15:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2778', '未知用户', '2020-04-18 13:15:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2779', '未知用户', '2020-04-18 13:15:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2780', '未知用户', '2020-04-18 13:15:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2781', '未知用户', '2020-04-18 13:15:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2782', '未知用户', '2020-04-18 13:15:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2783', '未知用户', '2020-04-18 13:15:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2784', '未知用户', '2020-04-18 13:15:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2785', '未知用户', '2020-04-18 13:15:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2786', '未知用户', '2020-04-18 13:15:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2787', '未知用户', '2020-04-18 13:15:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2788', '未知用户', '2020-04-18 13:18:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2789', '未知用户', '2020-04-18 13:18:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2790', '未知用户', '2020-04-18 13:18:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2791', '未知用户', '2020-04-18 13:18:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2792', '未知用户', '2020-04-18 13:18:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2793', '未知用户', '2020-04-18 13:18:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2794', '未知用户', '2020-04-18 13:18:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2795', '未知用户', '2020-04-18 13:18:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2796', '未知用户', '2020-04-18 13:18:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2797', '未知用户', '2020-04-18 13:18:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2798', '未知用户', '2020-04-18 13:18:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2799', '未知用户', '2020-04-18 13:18:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2800', '未知用户', '2020-04-18 13:18:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":7,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":1,\"ordernum\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2801', '未知用户', '2020-04-18 13:18:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2802', '未知用户', '2020-04-18 13:18:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":7,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":0,\"ordernum\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2803', '未知用户', '2020-04-18 13:18:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2804', '未知用户', '2020-04-18 13:18:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":4,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":0,\"ordernum\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2805', '未知用户', '2020-04-18 13:18:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2806', '未知用户', '2020-04-18 13:18:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":14,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":0,\"ordernum\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2807', '未知用户', '2020-04-18 13:18:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2808', '未知用户', '2020-04-18 13:20:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2809', '未知用户', '2020-04-18 13:20:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2810', '未知用户', '2020-04-18 13:20:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2811', '未知用户', '2020-04-18 13:20:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2812', '未知用户', '2020-04-18 13:20:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2813', '未知用户', '2020-04-18 13:20:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2814', '未知用户', '2020-04-18 13:20:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[0]');
INSERT INTO `systemlog` VALUES ('2815', '未知用户', '2020-04-18 13:20:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2816', '未知用户', '2020-04-18 13:21:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[2]');
INSERT INTO `systemlog` VALUES ('2817', '未知用户', '2020-04-18 13:21:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[2]');
INSERT INTO `systemlog` VALUES ('2818', '未知用户', '2020-04-18 13:21:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[14]');
INSERT INTO `systemlog` VALUES ('2819', '未知用户', '2020-04-18 13:21:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2820', '未知用户', '2020-04-18 13:21:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2821', '未知用户', '2020-04-18 13:21:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2822', '未知用户', '2020-04-18 13:21:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2823', '未知用户', '2020-04-18 13:21:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2824', '未知用户', '2020-04-18 13:21:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2825', '未知用户', '2020-04-18 13:21:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[14]');
INSERT INTO `systemlog` VALUES ('2826', '未知用户', '2020-04-18 13:21:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2827', '未知用户', '2020-04-18 13:21:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[7]');
INSERT INTO `systemlog` VALUES ('2828', '未知用户', '2020-04-18 13:21:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2829', '未知用户', '2020-04-18 13:21:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2830', '未知用户', '2020-04-18 13:29:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2831', '未知用户', '2020-04-18 13:29:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2832', '未知用户', '2020-04-18 13:29:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2833', '未知用户', '2020-04-18 13:29:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2834', '未知用户', '2020-04-18 13:29:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2835', '未知用户', '2020-04-18 13:29:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2836', '未知用户', '2020-04-18 13:29:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2837', '未知用户', '2020-04-18 13:29:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2838', '未知用户', '2020-04-18 13:30:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2839', '未知用户', '2020-04-18 13:30:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2840', '未知用户', '2020-04-18 13:30:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2841', '未知用户', '2020-04-18 13:30:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2842', '未知用户', '2020-04-18 13:30:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2843', '未知用户', '2020-04-18 13:30:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2844', '未知用户', '2020-04-18 13:30:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2845', '未知用户', '2020-04-18 13:30:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2846', '未知用户', '2020-04-18 13:30:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2847', '未知用户', '2020-04-18 13:30:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2848', '未知用户', '2020-04-18 13:30:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2849', '未知用户', '2020-04-18 13:30:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2850', '未知用户', '2020-04-18 13:30:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2851', '未知用户', '2020-04-18 13:30:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2852', '未知用户', '2020-04-18 13:30:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2853', '未知用户', '2020-04-18 13:30:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2854', '未知用户', '2020-04-18 13:30:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2855', '未知用户', '2020-04-18 13:30:22', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2856', '未知用户', '2020-04-18 13:30:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2857', '未知用户', '2020-04-18 13:30:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2858', '未知用户', '2020-04-18 13:30:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2859', '未知用户', '2020-04-18 13:30:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2860', '未知用户', '2020-04-18 13:34:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2861', '未知用户', '2020-04-18 13:35:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2862', '未知用户', '2020-04-18 13:35:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2863', '未知用户', '2020-04-18 13:35:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2864', '未知用户', '2020-04-18 13:35:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2865', '未知用户', '2020-04-18 13:35:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2866', '未知用户', '2020-04-18 13:35:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2867', '未知用户', '2020-04-18 13:35:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2868', '未知用户', '2020-04-18 13:35:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2869', '未知用户', '2020-04-18 13:35:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2870', '未知用户', '2020-04-18 13:35:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2871', '未知用户', '2020-04-18 13:35:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2872', '未知用户', '2020-04-18 13:35:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2873', '未知用户', '2020-04-18 13:35:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2874', '未知用户', '2020-04-18 13:35:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2875', '未知用户', '2020-04-18 13:35:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2876', '未知用户', '2020-04-18 13:35:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2877', '未知用户', '2020-04-18 13:35:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2878', '未知用户', '2020-04-18 13:35:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2879', '未知用户', '2020-04-18 13:35:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2880', '未知用户', '2020-04-18 13:35:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2881', '未知用户', '2020-04-18 13:35:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2882', '未知用户', '2020-04-18 13:35:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2883', '未知用户', '2020-04-18 13:35:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2884', '未知用户', '2020-04-18 13:35:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2885', '未知用户', '2020-04-18 13:35:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2886', '未知用户', '2020-04-18 13:35:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2887', '未知用户', '2020-04-18 13:35:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2888', '未知用户', '2020-04-18 13:35:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2889', '未知用户', '2020-04-18 13:35:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2890', '未知用户', '2020-04-18 13:35:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2891', '未知用户', '2020-04-18 13:35:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2892', '未知用户', '2020-04-18 13:35:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2893', '未知用户', '2020-04-18 13:35:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2894', '未知用户', '2020-04-18 13:35:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2895', '未知用户', '2020-04-18 13:35:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2896', '未知用户', '2020-04-18 13:35:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2897', '未知用户', '2020-04-18 13:35:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2898', '未知用户', '2020-04-18 13:35:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2899', '未知用户', '2020-04-18 13:35:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2900', '未知用户', '2020-04-18 13:35:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2901', '未知用户', '2020-04-18 13:35:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2902', '未知用户', '2020-04-18 13:35:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2903', '未知用户', '2020-04-18 13:35:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2904', '未知用户', '2020-04-18 13:35:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2905', '未知用户', '2020-04-18 13:35:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2906', '未知用户', '2020-04-18 13:35:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2907', '未知用户', '2020-04-18 13:35:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2908', '未知用户', '2020-04-18 13:35:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2909', '未知用户', '2020-04-18 13:35:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2910', '未知用户', '2020-04-18 13:35:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2911', '未知用户', '2020-04-18 13:35:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2912', '未知用户', '2020-04-18 13:35:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2913', '未知用户', '2020-04-18 13:35:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2914', '未知用户', '2020-04-18 13:35:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2915', '未知用户', '2020-04-18 13:36:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2916', '未知用户', '2020-04-18 13:36:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2917', '未知用户', '2020-04-18 13:36:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2918', '未知用户', '2020-04-18 13:36:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2919', '未知用户', '2020-04-18 13:36:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2920', '未知用户', '2020-04-18 13:36:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2921', '未知用户', '2020-04-18 13:36:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2922', '未知用户', '2020-04-18 13:36:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2923', '未知用户', '2020-04-18 13:36:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2924', '未知用户', '2020-04-18 13:36:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2925', '未知用户', '2020-04-18 13:36:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2926', '未知用户', '2020-04-18 13:36:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2927', '未知用户', '2020-04-18 13:36:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2928', '未知用户', '2020-04-18 13:39:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2929', '未知用户', '2020-04-18 13:39:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2930', '未知用户', '2020-04-18 13:39:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2931', '未知用户', '2020-04-18 13:39:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('2932', '超级管理员', '2020-04-18 13:39:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2933', '超级管理员', '2020-04-18 13:39:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2934', '超级管理员', '2020-04-18 13:39:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2935', '超级管理员', '2020-04-18 13:39:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2936', '超级管理员', '2020-04-18 13:39:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2937', '超级管理员', '2020-04-18 13:39:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2938', '超级管理员', '2020-04-18 13:39:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2939', '超级管理员', '2020-04-18 13:39:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2940', '超级管理员', '2020-04-18 13:39:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2941', '超级管理员', '2020-04-18 13:39:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2942', '超级管理员', '2020-04-18 13:39:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2943', '超级管理员', '2020-04-18 13:39:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2944', '超级管理员', '2020-04-18 13:39:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2945', '超级管理员', '2020-04-18 13:39:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2946', '超级管理员', '2020-04-18 13:39:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2947', '超级管理员', '2020-04-18 13:39:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2948', '超级管理员', '2020-04-18 13:39:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2949', '超级管理员', '2020-04-18 13:39:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2950', '超级管理员', '2020-04-18 13:39:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2951', '超级管理员', '2020-04-18 13:39:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2952', '超级管理员', '2020-04-18 13:39:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2953', '超级管理员', '2020-04-18 13:41:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2954', '超级管理员', '2020-04-18 13:45:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2955', '超级管理员', '2020-04-18 13:45:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2956', '超级管理员', '2020-04-18 13:45:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2957', '超级管理员', '2020-04-18 13:45:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2958', '超级管理员', '2020-04-18 13:45:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2959', '超级管理员', '2020-04-18 13:45:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2960', '超级管理员', '2020-04-18 13:45:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2961', '超级管理员', '2020-04-18 13:48:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2962', '超级管理员', '2020-04-18 13:48:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2963', '超级管理员', '2020-04-18 13:48:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2964', '超级管理员', '2020-04-18 13:48:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2965', '超级管理员', '2020-04-18 13:48:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2966', '超级管理员', '2020-04-18 13:48:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2967', '超级管理员', '2020-04-18 13:49:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2968', '超级管理员', '2020-04-18 13:49:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2969', '超级管理员', '2020-04-18 13:49:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2970', '超级管理员', '2020-04-18 13:49:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2971', '超级管理员', '2020-04-18 13:49:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2972', '超级管理员', '2020-04-18 13:49:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2973', '超级管理员', '2020-04-18 13:49:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2974', '超级管理员', '2020-04-18 13:49:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2975', '超级管理员', '2020-04-18 13:49:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2976', '超级管理员', '2020-04-18 13:49:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2977', '超级管理员', '2020-04-18 13:49:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2978', '超级管理员', '2020-04-18 13:49:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2979', '超级管理员', '2020-04-18 13:49:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2980', '超级管理员', '2020-04-18 13:49:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2981', '超级管理员', '2020-04-18 13:49:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2982', '超级管理员', '2020-04-18 13:49:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2983', '超级管理员', '2020-04-18 13:49:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2984', '超级管理员', '2020-04-18 13:49:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2985', '超级管理员', '2020-04-18 13:49:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2986', '超级管理员', '2020-04-18 13:49:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2987', '超级管理员', '2020-04-18 13:50:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2988', '超级管理员', '2020-04-18 13:50:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2989', '超级管理员', '2020-04-18 13:50:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2990', '超级管理员', '2020-04-18 13:50:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2991', '超级管理员', '2020-04-18 13:50:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2992', '超级管理员', '2020-04-18 13:50:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('2993', '超级管理员', '2020-04-18 13:50:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('2994', '超级管理员', '2020-04-18 13:51:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2995', '超级管理员', '2020-04-18 13:51:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('2996', '超级管理员', '2020-04-18 13:51:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2997', '超级管理员', '2020-04-18 13:51:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('2998', '超级管理员', '2020-04-18 13:51:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('2999', '超级管理员', '2020-04-18 13:51:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3000', '超级管理员', '2020-04-18 13:51:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3001', '超级管理员', '2020-04-18 13:53:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3002', '超级管理员', '2020-04-18 13:53:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3003', '超级管理员', '2020-04-18 13:53:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3004', '超级管理员', '2020-04-18 13:53:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3005', '超级管理员', '2020-04-18 13:53:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3006', '超级管理员', '2020-04-18 13:53:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3007', '超级管理员', '2020-04-18 13:54:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3008', '超级管理员', '2020-04-18 13:54:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3009', '超级管理员', '2020-04-18 13:54:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3010', '超级管理员', '2020-04-18 13:54:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3011', '超级管理员', '2020-04-18 13:54:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3012', '超级管理员', '2020-04-18 13:54:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3013', '超级管理员', '2020-04-18 13:54:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3014', '超级管理员', '2020-04-18 13:56:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3015', '超级管理员', '2020-04-18 13:56:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3016', '超级管理员', '2020-04-18 13:56:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3017', '超级管理员', '2020-04-18 13:56:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3018', '超级管理员', '2020-04-18 13:56:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3019', '超级管理员', '2020-04-18 13:56:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3020', '超级管理员', '2020-04-18 14:17:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3021', '超级管理员', '2020-04-18 14:17:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3022', '超级管理员', '2020-04-18 14:18:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3023', '超级管理员', '2020-04-18 14:18:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3024', '超级管理员', '2020-04-18 14:18:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3025', '超级管理员', '2020-04-18 14:18:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3026', '超级管理员', '2020-04-18 14:18:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3027', '超级管理员', '2020-04-18 14:18:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3028', '超级管理员', '2020-04-18 14:18:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3029', '超级管理员', '2020-04-18 14:18:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3030', '超级管理员', '2020-04-18 14:19:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3031', '超级管理员', '2020-04-18 14:19:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3032', '超级管理员', '2020-04-18 14:19:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3033', '超级管理员', '2020-04-18 14:19:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3034', '超级管理员', '2020-04-18 14:19:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3035', '超级管理员', '2020-04-18 14:19:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3036', '超级管理员', '2020-04-18 14:19:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3037', '超级管理员', '2020-04-18 14:19:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3038', '超级管理员', '2020-04-18 14:19:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3039', '超级管理员', '2020-04-18 14:19:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3040', '超级管理员', '2020-04-18 14:19:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3041', '超级管理员', '2020-04-18 14:19:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3042', '超级管理员', '2020-04-18 14:19:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3043', '超级管理员', '2020-04-18 14:19:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3044', '超级管理员', '2020-04-18 14:19:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3045', '超级管理员', '2020-04-18 14:20:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3046', '超级管理员', '2020-04-18 14:20:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3047', '超级管理员', '2020-04-18 14:31:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3048', '超级管理员', '2020-04-18 14:31:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3049', '超级管理员', '2020-04-18 14:31:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3050', '超级管理员', '2020-04-18 14:31:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3051', '超级管理员', '2020-04-18 14:31:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3052', '超级管理员', '2020-04-18 14:31:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3053', '超级管理员', '2020-04-18 14:31:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3054', '超级管理员', '2020-04-18 14:31:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3055', '未知用户', '2020-04-18 14:33:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3056', '未知用户', '2020-04-18 14:33:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3057', '未知用户', '2020-04-18 14:33:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3058', '未知用户', '2020-04-18 14:33:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3059', '未知用户', '2020-04-18 14:33:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3060', '未知用户', '2020-04-18 14:33:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3061', '未知用户', '2020-04-18 14:33:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3062', '未知用户', '2020-04-18 14:33:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3063', '未知用户', '2020-04-18 14:33:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3064', '未知用户', '2020-04-18 14:38:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3065', '未知用户', '2020-04-18 14:38:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3066', '未知用户', '2020-04-18 14:38:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3067', '未知用户', '2020-04-18 14:38:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3068', '未知用户', '2020-04-18 14:38:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3069', '未知用户', '2020-04-18 14:38:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3070', '未知用户', '2020-04-18 14:40:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3071', '超级管理员', '2020-04-18 14:41:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3072', '超级管理员', '2020-04-18 14:41:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3073', '超级管理员', '2020-04-18 14:41:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3074', '超级管理员', '2020-04-18 14:41:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3075', '超级管理员', '2020-04-18 14:41:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3076', '超级管理员', '2020-04-18 14:41:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3077', '超级管理员', '2020-04-18 14:41:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3078', '超级管理员', '2020-04-18 14:41:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3079', '超级管理员', '2020-04-18 14:41:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3080', '超级管理员', '2020-04-18 14:41:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:queryDeptMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3081', '超级管理员', '2020-04-18 14:41:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3082', '超级管理员', '2020-04-18 14:43:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3083', '超级管理员', '2020-04-18 14:43:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3084', '超级管理员', '2020-04-18 14:43:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3085', '超级管理员', '2020-04-18 14:43:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3086', '超级管理员', '2020-04-18 14:43:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3087', '超级管理员', '2020-04-18 14:43:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3088', '超级管理员', '2020-04-18 14:44:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3089', '超级管理员', '2020-04-18 14:44:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3090', '超级管理员', '2020-04-18 14:44:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3091', '超级管理员', '2020-04-18 14:44:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3092', '超级管理员', '2020-04-18 14:44:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3093', '超级管理员', '2020-04-18 14:44:51', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3094', '超级管理员', '2020-04-18 14:45:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3095', '超级管理员', '2020-04-18 14:45:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3096', '超级管理员', '2020-04-18 14:45:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3097', '超级管理员', '2020-04-18 14:45:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3098', '超级管理员', '2020-04-18 14:45:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3099', '超级管理员', '2020-04-18 14:45:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3100', '超级管理员', '2020-04-18 14:45:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3101', '超级管理员', '2020-04-18 14:45:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3102', '超级管理员', '2020-04-18 14:45:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3103', '超级管理员', '2020-04-18 14:45:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3104', '超级管理员', '2020-04-18 14:45:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3105', '超级管理员', '2020-04-18 14:45:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3106', '超级管理员', '2020-04-18 14:46:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3107', '超级管理员', '2020-04-18 14:46:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3108', '超级管理员', '2020-04-18 14:46:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3109', '超级管理员', '2020-04-18 14:46:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3110', '超级管理员', '2020-04-18 14:46:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3111', '超级管理员', '2020-04-18 14:46:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3112', '超级管理员', '2020-04-18 14:46:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3113', '超级管理员', '2020-04-18 14:46:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3114', '超级管理员', '2020-04-18 14:46:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3115', '超级管理员', '2020-04-18 14:46:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3116', '超级管理员', '2020-04-18 14:46:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3117', '超级管理员', '2020-04-18 14:46:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3118', '超级管理员', '2020-04-18 14:46:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3119', '超级管理员', '2020-04-18 14:46:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3120', '超级管理员', '2020-04-18 14:46:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3121', '超级管理员', '2020-04-18 14:46:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3122', '超级管理员', '2020-04-18 14:46:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3123', '超级管理员', '2020-04-18 14:46:58', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3124', '超级管理员', '2020-04-18 14:47:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3125', '超级管理员', '2020-04-18 14:47:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3126', '超级管理员', '2020-04-18 14:47:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3127', '超级管理员', '2020-04-18 14:47:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3128', '超级管理员', '2020-04-18 14:47:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3129', '超级管理员', '2020-04-18 14:47:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3130', '超级管理员', '2020-04-18 14:47:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3131', '超级管理员', '2020-04-18 14:47:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3132', '超级管理员', '2020-04-18 14:47:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3133', '超级管理员', '2020-04-18 14:47:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3134', '超级管理员', '2020-04-18 14:47:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3135', '超级管理员', '2020-04-18 14:47:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3136', '超级管理员', '2020-04-18 14:47:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3137', '超级管理员', '2020-04-18 14:47:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3138', '超级管理员', '2020-04-18 14:47:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3139', '超级管理员', '2020-04-18 14:48:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3140', '超级管理员', '2020-04-18 14:48:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3141', '超级管理员', '2020-04-18 14:48:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3142', '超级管理员', '2020-04-18 14:48:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3143', '超级管理员', '2020-04-18 14:48:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3144', '超级管理员', '2020-04-18 14:48:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3145', '超级管理员', '2020-04-18 14:48:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3146', '超级管理员', '2020-04-18 14:48:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3147', '超级管理员', '2020-04-18 14:48:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3148', '超级管理员', '2020-04-18 14:48:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3149', '超级管理员', '2020-04-18 14:48:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3150', '超级管理员', '2020-04-18 14:48:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3151', '超级管理员', '2020-04-18 14:48:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3152', '超级管理员', '2020-04-18 14:48:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3153', '超级管理员', '2020-04-18 14:48:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3154', '超级管理员', '2020-04-18 14:48:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3155', '超级管理员', '2020-04-18 14:48:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3156', '超级管理员', '2020-04-18 14:48:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3157', '超级管理员', '2020-04-18 14:48:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3158', '超级管理员', '2020-04-18 14:48:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3159', '超级管理员', '2020-04-18 14:48:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3160', '超级管理员', '2020-04-18 14:48:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3161', '超级管理员', '2020-04-18 14:49:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3162', '超级管理员', '2020-04-18 14:49:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3163', '超级管理员', '2020-04-18 14:49:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3164', '超级管理员', '2020-04-18 14:49:15', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3165', '超级管理员', '2020-04-18 14:49:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3166', '超级管理员', '2020-04-18 14:49:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3167', '超级管理员', '2020-04-18 14:49:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3168', '超级管理员', '2020-04-18 14:49:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3169', '超级管理员', '2020-04-18 14:49:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3170', '超级管理员', '2020-04-18 14:49:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3171', '超级管理员', '2020-04-18 14:49:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3172', '超级管理员', '2020-04-18 14:49:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3173', '超级管理员', '2020-04-18 14:49:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3174', '超级管理员', '2020-04-18 14:49:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3175', '超级管理员', '2020-04-18 14:49:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3176', '超级管理员', '2020-04-18 14:49:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3177', '超级管理员', '2020-04-18 14:49:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3178', '超级管理员', '2020-04-18 14:49:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3179', '超级管理员', '2020-04-18 14:49:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3180', '超级管理员', '2020-04-18 14:49:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3181', '超级管理员', '2020-04-18 14:50:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3182', '超级管理员', '2020-04-18 14:50:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3183', '超级管理员', '2020-04-18 14:50:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3184', '超级管理员', '2020-04-18 14:50:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3185', '超级管理员', '2020-04-18 14:50:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3186', '超级管理员', '2020-04-18 14:53:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3187', '超级管理员', '2020-04-18 14:53:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3188', '超级管理员', '2020-04-18 14:53:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3189', '超级管理员', '2020-04-18 14:53:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3190', '超级管理员', '2020-04-18 14:53:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3191', '超级管理员', '2020-04-18 14:54:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3192', '超级管理员', '2020-04-18 14:54:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3193', '超级管理员', '2020-04-18 14:54:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3194', '超级管理员', '2020-04-18 14:54:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3195', '超级管理员', '2020-04-18 14:54:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3196', '超级管理员', '2020-04-18 14:56:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3197', '超级管理员', '2020-04-18 14:56:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3198', '超级管理员', '2020-04-18 14:56:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3199', '超级管理员', '2020-04-18 14:56:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3200', '超级管理员', '2020-04-18 14:56:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3201', '超级管理员', '2020-04-18 14:56:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3202', '超级管理员', '2020-04-18 14:56:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3203', '超级管理员', '2020-04-18 14:56:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3204', '超级管理员', '2020-04-18 14:56:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3205', '超级管理员', '2020-04-18 14:56:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3206', '超级管理员', '2020-04-18 14:57:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3207', '超级管理员', '2020-04-18 14:57:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3208', '超级管理员', '2020-04-18 14:57:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3209', '超级管理员', '2020-04-18 14:57:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3210', '超级管理员', '2020-04-18 14:57:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3211', '超级管理员', '2020-04-18 14:57:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3212', '超级管理员', '2020-04-18 14:57:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3213', '超级管理员', '2020-04-18 14:57:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3214', '超级管理员', '2020-04-18 14:57:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3215', '超级管理员', '2020-04-18 14:57:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3216', '超级管理员', '2020-04-18 14:57:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3217', '超级管理员', '2020-04-18 14:57:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3218', '超级管理员', '2020-04-18 14:58:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3219', '超级管理员', '2020-04-18 14:58:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3220', '超级管理员', '2020-04-18 14:58:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3221', '超级管理员', '2020-04-18 14:58:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3222', '超级管理员', '2020-04-18 14:58:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3223', '超级管理员', '2020-04-18 14:58:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3224', '超级管理员', '2020-04-18 14:59:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3225', '超级管理员', '2020-04-18 15:00:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3226', '超级管理员', '2020-04-18 15:00:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3227', '超级管理员', '2020-04-18 15:00:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3228', '超级管理员', '2020-04-18 15:00:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3229', '超级管理员', '2020-04-18 15:00:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3230', '超级管理员', '2020-04-18 15:00:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3231', '超级管理员', '2020-04-18 15:00:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3232', '超级管理员', '2020-04-18 15:00:46', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3233', '超级管理员', '2020-04-18 15:00:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3234', '超级管理员', '2020-04-18 15:00:47', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3235', '超级管理员', '2020-04-18 15:00:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3236', '超级管理员', '2020-04-18 15:00:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3237', '超级管理员', '2020-04-18 15:00:55', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3238', '超级管理员', '2020-04-18 15:00:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3239', '超级管理员', '2020-04-18 15:00:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3240', '超级管理员', '2020-04-18 15:00:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3241', '超级管理员', '2020-04-18 15:01:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3242', '超级管理员', '2020-04-18 15:01:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3243', '超级管理员', '2020-04-18 15:01:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3244', '超级管理员', '2020-04-18 15:01:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3245', '超级管理员', '2020-04-18 15:01:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3246', '超级管理员', '2020-04-18 15:01:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3247', '超级管理员', '2020-04-18 15:01:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3248', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3249', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3250', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3251', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3252', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3253', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3254', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3255', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3256', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3257', '超级管理员', '2020-04-18 15:01:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3258', '超级管理员', '2020-04-18 15:01:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3259', '超级管理员', '2020-04-18 15:01:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3260', '超级管理员', '2020-04-18 15:01:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3261', '超级管理员', '2020-04-18 15:01:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3262', '超级管理员', '2020-04-18 15:01:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3263', '超级管理员', '2020-04-18 15:01:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3264', '超级管理员', '2020-04-18 15:01:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3265', '超级管理员', '2020-04-18 15:01:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3266', '超级管理员', '2020-04-18 15:01:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3267', '超级管理员', '2020-04-18 15:01:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3268', '超级管理员', '2020-04-18 15:01:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3269', '超级管理员', '2020-04-18 15:01:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3270', '超级管理员', '2020-04-18 15:01:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3271', '超级管理员', '2020-04-18 15:01:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3272', '超级管理员', '2020-04-18 15:01:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3273', '超级管理员', '2020-04-18 15:01:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3274', '超级管理员', '2020-04-18 15:02:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3275', '超级管理员', '2020-04-18 15:02:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3276', '超级管理员', '2020-04-18 15:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3277', '超级管理员', '2020-04-18 15:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3278', '超级管理员', '2020-04-18 15:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3279', '超级管理员', '2020-04-18 15:02:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3280', '超级管理员', '2020-04-18 15:03:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3281', '超级管理员', '2020-04-18 15:03:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3282', '超级管理员', '2020-04-18 15:03:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3283', '超级管理员', '2020-04-18 15:03:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3284', '超级管理员', '2020-04-18 15:03:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3285', '超级管理员', '2020-04-18 15:03:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3286', '超级管理员', '2020-04-18 15:03:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3287', '超级管理员', '2020-04-18 15:03:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3288', '超级管理员', '2020-04-18 15:03:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3289', '超级管理员', '2020-04-18 15:03:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3290', '超级管理员', '2020-04-18 15:03:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3291', '超级管理员', '2020-04-18 15:03:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3292', '超级管理员', '2020-04-18 15:03:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3293', '超级管理员', '2020-04-18 15:03:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3294', '超级管理员', '2020-04-18 15:03:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3295', '超级管理员', '2020-04-18 15:03:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3296', '超级管理员', '2020-04-18 15:03:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3297', '超级管理员', '2020-04-18 15:03:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3298', '超级管理员', '2020-04-18 15:03:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3299', '超级管理员', '2020-04-18 15:03:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3300', '超级管理员', '2020-04-18 15:03:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3301', '超级管理员', '2020-04-18 15:03:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3302', '超级管理员', '2020-04-18 15:03:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3303', '超级管理员', '2020-04-18 15:03:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3304', '超级管理员', '2020-04-18 15:03:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3305', '超级管理员', '2020-04-18 15:04:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3306', '超级管理员', '2020-04-18 15:04:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3307', '超级管理员', '2020-04-18 15:04:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3308', '超级管理员', '2020-04-18 15:04:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3309', '超级管理员', '2020-04-18 15:04:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3310', '超级管理员', '2020-04-18 15:04:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3311', '超级管理员', '2020-04-18 15:04:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3312', '超级管理员', '2020-04-18 15:04:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3313', '超级管理员', '2020-04-18 15:04:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3314', '超级管理员', '2020-04-18 15:04:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3315', '超级管理员', '2020-04-18 15:04:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3316', '超级管理员', '2020-04-18 15:04:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3317', '超级管理员', '2020-04-18 15:04:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3318', '超级管理员', '2020-04-18 15:04:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3319', '超级管理员', '2020-04-18 15:04:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3320', '超级管理员', '2020-04-18 15:04:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3321', '超级管理员', '2020-04-18 15:04:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3322', '超级管理员', '2020-04-18 15:04:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3323', '超级管理员', '2020-04-18 15:04:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3324', '超级管理员', '2020-04-18 15:04:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:queryMenuMaxOrderNum', '[]');
INSERT INTO `systemlog` VALUES ('3325', '超级管理员', '2020-04-18 15:06:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:addMenu', '[{\"id\":null,\"pid\":null,\"type\":\"topmenu\",\"typecode\":null,\"title\":\"测试1\",\"icon\":\"fa-amazon\",\"href\":\"\",\"target\":\"_self\",\"spread\":null,\"ordernum\":33,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3326', '超级管理员', '2020-04-18 15:06:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3327', '超级管理员', '2020-04-18 15:07:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3328', '超级管理员', '2020-04-18 15:07:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3329', '超级管理员', '2020-04-18 15:07:12', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3330', '超级管理员', '2020-04-18 15:07:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3331', '超级管理员', '2020-04-18 15:07:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3332', '超级管理员', '2020-04-18 15:07:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3333', '超级管理员', '2020-04-18 15:07:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3334', '超级管理员', '2020-04-18 15:07:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3335', '超级管理员', '2020-04-18 15:07:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3336', '超级管理员', '2020-04-18 15:07:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3337', '超级管理员', '2020-04-18 15:07:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3338', '超级管理员', '2020-04-18 15:07:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3339', '超级管理员', '2020-04-18 15:07:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3340', '超级管理员', '2020-04-18 15:07:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3341', '超级管理员', '2020-04-18 15:07:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3342', '未知用户', '2020-04-18 15:08:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3343', '超级管理员', '2020-04-18 15:08:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3344', '超级管理员', '2020-04-18 15:08:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3345', '超级管理员', '2020-04-18 15:09:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3346', '超级管理员', '2020-04-18 15:09:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3347', '超级管理员', '2020-04-18 15:09:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3348', '超级管理员', '2020-04-18 15:09:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3349', '超级管理员', '2020-04-18 15:10:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3350', '超级管理员', '2020-04-18 15:10:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3351', '超级管理员', '2020-04-18 15:10:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3352', '超级管理员', '2020-04-18 15:10:57', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3353', '超级管理员', '2020-04-18 15:10:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3354', '超级管理员', '2020-04-18 15:10:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3355', '超级管理员', '2020-04-18 15:12:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3356', '超级管理员', '2020-04-18 15:12:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3357', '超级管理员', '2020-04-18 15:12:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3358', '未知用户', '2020-04-18 15:15:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3359', '未知用户', '2020-04-18 15:15:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3360', '未知用户', '2020-04-18 15:15:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3361', '超级管理员', '2020-04-18 15:15:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3362', '超级管理员', '2020-04-18 15:15:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3363', '超级管理员', '2020-04-18 15:15:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"admin\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3364', '超级管理员', '2020-04-18 15:16:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3365', '超级管理员', '2020-04-18 15:16:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3366', '超级管理员', '2020-04-18 15:16:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3367', '超级管理员', '2020-04-18 15:16:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3368', '超级管理员', '2020-04-18 15:16:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3369', '超级管理员', '2020-04-18 15:16:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3370', '超级管理员', '2020-04-18 15:18:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3371', '超级管理员', '2020-04-18 15:18:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3372', '超级管理员', '2020-04-18 15:18:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system，\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3373', '超级管理员', '2020-04-18 15:18:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3374', '超级管理员', '2020-04-18 15:18:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3375', '超级管理员', '2020-04-18 15:18:07', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3376', '超级管理员', '2020-04-18 15:18:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"admin\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3377', '超级管理员', '2020-04-18 15:19:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3378', '超级管理员', '2020-04-18 15:19:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3379', '超级管理员', '2020-04-18 15:19:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3380', '超级管理员', '2020-04-18 15:19:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3381', '超级管理员', '2020-04-18 15:19:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3382', '未知用户', '2020-04-20 01:36:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3383', '超级管理员', '2020-04-20 01:37:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3384', '超级管理员', '2020-04-20 01:37:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3385', '超级管理员', '2020-04-20 01:38:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3386', '超级管理员', '2020-04-20 01:38:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3387', '未知用户', '2020-04-20 01:44:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3388', '未知用户', '2020-04-20 01:44:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3389', '未知用户', '2020-04-20 02:00:25', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3390', '未知用户', '2020-04-20 02:02:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3391', '未知用户', '2020-04-20 02:02:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3392', '未知用户', '2020-04-20 02:02:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3393', '未知用户', '2020-04-20 02:02:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3394', '未知用户', '2020-04-20 02:03:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3395', '未知用户', '2020-04-20 02:03:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3396', '未知用户', '2020-04-20 02:21:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3397', '未知用户', '2020-04-20 02:21:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3398', '未知用户', '2020-04-20 02:21:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3399', '未知用户', '2020-04-20 02:21:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[14]');
INSERT INTO `systemlog` VALUES ('3400', '未知用户', '2020-04-20 02:21:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuById', '[14]');
INSERT INTO `systemlog` VALUES ('3401', '未知用户', '2020-04-20 02:22:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":18,\"pid\":14,\"type\":\"leftmenu\",\"typecode\":\"system\",\"title\":\"角色管理\",\"icon\":\"fa fa-gear\",\"href\":\"resources/page/system/role/roleManager.html\",\"target\":\"_self\",\"spread\":null,\"ordernum\":18,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3402', '未知用户', '2020-04-20 02:22:21', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3403', '未知用户', '2020-04-20 02:22:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3404', '未知用户', '2020-04-20 02:22:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3405', '未知用户', '2020-04-20 02:22:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3406', '未知用户', '2020-04-20 02:22:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3407', '未知用户', '2020-04-20 02:22:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3408', '未知用户', '2020-04-20 02:22:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3409', '未知用户', '2020-04-20 02:22:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3410', '未知用户', '2020-04-20 02:22:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3411', '未知用户', '2020-04-20 02:22:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3412', '未知用户', '2020-04-20 02:22:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3413', '未知用户', '2020-04-20 02:22:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3414', '未知用户', '2020-04-20 02:22:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3415', '未知用户', '2020-04-20 03:04:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3416', '未知用户', '2020-04-20 03:04:04', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3417', '未知用户', '2020-04-20 03:04:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3418', '超级管理员', '2020-04-20 03:04:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3419', '超级管理员', '2020-04-20 03:04:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3420', '超级管理员', '2020-04-20 03:04:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3421', '超级管理员', '2020-04-20 03:04:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3422', '超级管理员', '2020-04-20 03:05:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3423', '超级管理员', '2020-04-20 03:05:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3424', '超级管理员', '2020-04-20 03:05:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:updateDept', '[{\"id\":39,\"pid\":null,\"title\":null,\"spread\":0,\"remark\":null,\"address\":null,\"available\":null,\"ordernum\":null,\"createtime\":null}]');
INSERT INTO `systemlog` VALUES ('3425', '超级管理员', '2020-04-20 03:05:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.DeptController:loadAllDept', '[{\"page\":null,\"limit\":null,\"available\":null,\"title\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3426', '超级管理员', '2020-04-20 03:05:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3427', '超级管理员', '2020-04-20 03:05:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":10,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3428', '超级管理员', '2020-04-20 03:06:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3429', '超级管理员', '2020-04-20 03:06:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3430', '超级管理员', '2020-04-20 03:06:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3431', '超级管理员', '2020-04-20 03:06:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3432', '超级管理员', '2020-04-20 03:06:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3433', '超级管理员', '2020-04-20 03:06:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3434', '超级管理员', '2020-04-20 03:08:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3435', '超级管理员', '2020-04-20 03:08:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3436', '超级管理员', '2020-04-20 03:08:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3437', '超级管理员', '2020-04-20 03:08:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3438', '超级管理员', '2020-04-20 03:08:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3439', '超级管理员', '2020-04-20 03:08:19', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3440', '超级管理员', '2020-04-20 03:08:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3441', '超级管理员', '2020-04-20 03:08:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3442', '超级管理员', '2020-04-20 03:08:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3443', '超级管理员', '2020-04-20 03:08:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3444', '超级管理员', '2020-04-20 03:08:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3445', '超级管理员', '2020-04-20 03:08:35', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3446', '超级管理员', '2020-04-20 03:08:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3447', '超级管理员', '2020-04-20 03:08:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3448', '超级管理员', '2020-04-20 03:08:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3449', '超级管理员', '2020-04-20 03:08:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3450', '超级管理员', '2020-04-20 03:08:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3451', '超级管理员', '2020-04-20 03:08:38', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3452', '超级管理员', '2020-04-20 03:08:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":1}]');
INSERT INTO `systemlog` VALUES ('3453', '超级管理员', '2020-04-20 03:13:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3454', '超级管理员', '2020-04-20 03:13:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3455', '超级管理员', '2020-04-20 03:14:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:getMenuChildrenCountById', '[44]');
INSERT INTO `systemlog` VALUES ('3456', '超级管理员', '2020-04-20 03:14:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:deleteMenu', '[44]');
INSERT INTO `systemlog` VALUES ('3457', '超级管理员', '2020-04-20 03:15:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:addRole', '[{\"id\":null,\"name\":\"测试人员\",\"remark\":\"nb666呀\",\"available\":null,\"createtime\":null}]');
INSERT INTO `systemlog` VALUES ('3458', '超级管理员', '2020-04-20 03:15:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3459', '超级管理员', '2020-04-20 03:18:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3460', '超级管理员', '2020-04-20 03:18:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3461', '超级管理员', '2020-04-20 03:18:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3462', '超级管理员', '2020-04-20 03:18:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3463', '超级管理员', '2020-04-20 03:18:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3464', '超级管理员', '2020-04-20 03:18:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3465', '超级管理员', '2020-04-20 03:18:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3466', '超级管理员', '2020-04-20 03:18:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3467', '超级管理员', '2020-04-20 03:18:34', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3468', '超级管理员', '2020-04-20 03:18:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3469', '超级管理员', '2020-04-20 03:18:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3470', '超级管理员', '2020-04-20 03:19:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3471', '超级管理员', '2020-04-20 03:19:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3472', '超级管理员', '2020-04-20 03:19:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3473', '超级管理员', '2020-04-20 03:19:10', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3474', '超级管理员', '2020-04-20 03:19:11', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3475', '超级管理员', '2020-04-20 03:19:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3476', '超级管理员', '2020-04-20 03:19:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3477', '超级管理员', '2020-04-20 03:19:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3478', '超级管理员', '2020-04-20 03:19:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3479', '超级管理员', '2020-04-20 03:19:18', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3480', '超级管理员', '2020-04-20 03:19:23', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3481', '超级管理员', '2020-04-20 03:19:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3482', '超级管理员', '2020-04-20 03:19:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3483', '超级管理员', '2020-04-20 03:19:24', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3484', '超级管理员', '2020-04-20 03:19:26', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3485', '超级管理员', '2020-04-20 03:19:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3486', '超级管理员', '2020-04-20 03:19:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3487', '超级管理员', '2020-04-20 03:19:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3488', '超级管理员', '2020-04-20 03:19:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3489', '超级管理员', '2020-04-20 03:19:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3490', '超级管理员', '2020-04-20 03:19:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3491', '超级管理员', '2020-04-20 03:19:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3492', '超级管理员', '2020-04-20 03:19:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3493', '超级管理员', '2020-04-20 03:19:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3494', '超级管理员', '2020-04-20 03:19:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3495', '超级管理员', '2020-04-20 03:20:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3496', '超级管理员', '2020-04-20 03:20:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3497', '超级管理员', '2020-04-20 03:20:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3498', '超级管理员', '2020-04-20 03:20:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3499', '超级管理员', '2020-04-20 03:20:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3500', '超级管理员', '2020-04-20 03:20:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3501', '超级管理员', '2020-04-20 03:20:27', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3502', '超级管理员', '2020-04-20 03:20:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3503', '超级管理员', '2020-04-20 03:20:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3504', '超级管理员', '2020-04-20 03:20:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3505', '超级管理员', '2020-04-20 03:20:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3506', '超级管理员', '2020-04-20 03:20:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3507', '超级管理员', '2020-04-20 03:20:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3508', '超级管理员', '2020-04-20 03:20:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3509', '超级管理员', '2020-04-20 03:20:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3510', '超级管理员', '2020-04-20 03:20:56', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3511', '超级管理员', '2020-04-20 03:20:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3512', '超级管理员', '2020-04-20 03:20:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3513', '超级管理员', '2020-04-20 03:21:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3514', '超级管理员', '2020-04-20 03:21:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3515', '超级管理员', '2020-04-20 03:21:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3516', '超级管理员', '2020-04-20 03:21:00', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3517', '超级管理员', '2020-04-20 03:21:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3518', '超级管理员', '2020-04-20 03:21:28', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3519', '超级管理员', '2020-04-20 03:21:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3520', '超级管理员', '2020-04-20 03:21:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3521', '超级管理员', '2020-04-20 03:21:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3522', '超级管理员', '2020-04-20 03:21:29', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3523', '超级管理员', '2020-04-20 03:21:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3524', '超级管理员', '2020-04-20 03:21:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3525', '超级管理员', '2020-04-20 03:21:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3526', '超级管理员', '2020-04-20 03:21:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3527', '超级管理员', '2020-04-20 03:21:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3528', '超级管理员', '2020-04-20 03:21:50', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3529', '未知用户', '2020-04-20 04:05:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3530', '未知用户', '2020-04-20 04:05:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3531', '未知用户', '2020-04-20 04:05:37', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:doLogin', '[\"system\",\"123456\"]');
INSERT INTO `systemlog` VALUES ('3532', '超级管理员', '2020-04-20 04:05:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3533', '超级管理员', '2020-04-20 04:05:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3534', '超级管理员', '2020-04-20 04:05:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3535', '超级管理员', '2020-04-20 04:05:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3536', '超级管理员', '2020-04-20 04:05:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3537', '超级管理员', '2020-04-20 04:05:44', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3538', '超级管理员', '2020-04-20 04:05:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3539', '超级管理员', '2020-04-20 04:05:52', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3540', '超级管理员', '2020-04-20 04:05:59', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":\"超级\",\"remark\":\"\"}]');
INSERT INTO `systemlog` VALUES ('3541', '超级管理员', '2020-04-20 04:06:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":\"\",\"remark\":\"\"}]');
INSERT INTO `systemlog` VALUES ('3542', '超级管理员', '2020-04-20 04:07:02', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:updateRole', '[{\"id\":1,\"name\":null,\"remark\":null,\"available\":0,\"createtime\":null}]');
INSERT INTO `systemlog` VALUES ('3543', '超级管理员', '2020-04-20 04:07:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":\"\",\"remark\":\"\"}]');
INSERT INTO `systemlog` VALUES ('3544', '超级管理员', '2020-04-20 04:07:05', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:updateRole', '[{\"id\":1,\"name\":null,\"remark\":null,\"available\":1,\"createtime\":null}]');
INSERT INTO `systemlog` VALUES ('3545', '超级管理员', '2020-04-20 04:07:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":\"\",\"remark\":\"\"}]');
INSERT INTO `systemlog` VALUES ('3546', '超级管理员', '2020-04-20 04:07:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3547', '超级管理员', '2020-04-20 04:07:40', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3548', '超级管理员', '2020-04-20 04:07:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3549', '超级管理员', '2020-04-20 04:07:41', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3550', '超级管理员', '2020-04-20 04:07:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3551', '超级管理员', '2020-04-20 04:07:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3552', '超级管理员', '2020-04-20 04:08:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3553', '超级管理员', '2020-04-20 04:08:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3554', '超级管理员', '2020-04-20 04:08:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3555', '超级管理员', '2020-04-20 04:08:01', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3556', '超级管理员', '2020-04-20 04:08:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3557', '超级管理员', '2020-04-20 04:08:03', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3558', '超级管理员', '2020-04-20 04:08:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3559', '超级管理员', '2020-04-20 04:08:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3560', '超级管理员', '2020-04-20 04:08:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3561', '超级管理员', '2020-04-20 04:08:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3562', '超级管理员', '2020-04-20 04:08:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3563', '超级管理员', '2020-04-20 04:08:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3564', '超级管理员', '2020-04-20 04:08:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3565', '超级管理员', '2020-04-20 04:08:30', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3566', '超级管理员', '2020-04-20 04:08:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3567', '超级管理员', '2020-04-20 04:08:31', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3568', '超级管理员', '2020-04-20 04:08:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3569', '超级管理员', '2020-04-20 04:08:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3570', '超级管理员', '2020-04-20 04:08:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:updateRole', '[{\"id\":4,\"name\":null,\"remark\":null,\"available\":0,\"createtime\":null}]');
INSERT INTO `systemlog` VALUES ('3571', '超级管理员', '2020-04-20 04:08:36', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3572', '超级管理员', '2020-04-20 04:08:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:updateRole', '[{\"id\":4,\"name\":null,\"remark\":null,\"available\":1,\"createtime\":null}]');
INSERT INTO `systemlog` VALUES ('3573', '超级管理员', '2020-04-20 04:08:39', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3574', '超级管理员', '2020-04-20 04:17:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3575', '超级管理员', '2020-04-20 04:17:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3576', '超级管理员', '2020-04-20 04:17:16', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3577', '超级管理员', '2020-04-20 04:17:13', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3578', '超级管理员', '2020-04-20 04:17:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3579', '超级管理员', '2020-04-20 04:17:17', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3580', '超级管理员', '2020-04-20 04:22:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3581', '超级管理员', '2020-04-20 04:22:06', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3582', '超级管理员', '2020-04-20 04:22:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3583', '超级管理员', '2020-04-20 04:22:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3584', '超级管理员', '2020-04-20 04:22:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3585', '超级管理员', '2020-04-20 04:22:08', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3586', '超级管理员', '2020-04-20 04:22:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3587', '超级管理员', '2020-04-20 04:22:09', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3588', '超级管理员', '2020-04-20 04:22:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":48,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":null,\"ordernum\":null,\"available\":0}]');
INSERT INTO `systemlog` VALUES ('3589', '超级管理员', '2020-04-20 04:22:14', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3590', '超级管理员', '2020-04-20 04:22:20', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":1}]');
INSERT INTO `systemlog` VALUES ('3591', '超级管理员', '2020-04-20 04:22:42', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":49,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":null,\"ordernum\":null,\"available\":0}]');
INSERT INTO `systemlog` VALUES ('3592', '超级管理员', '2020-04-20 04:22:43', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3593', '超级管理员', '2020-04-20 04:22:48', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":48,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":null,\"ordernum\":null,\"available\":1}]');
INSERT INTO `systemlog` VALUES ('3594', '超级管理员', '2020-04-20 04:22:49', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3595', '超级管理员', '2020-04-20 04:22:53', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:updateMenu', '[{\"id\":49,\"pid\":null,\"type\":null,\"typecode\":null,\"title\":null,\"icon\":null,\"href\":null,\"target\":null,\"spread\":null,\"ordernum\":null,\"available\":1}]');
INSERT INTO `systemlog` VALUES ('3596', '超级管理员', '2020-04-20 04:22:54', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.MenuController:loadAllMenu', '[{\"page\":null,\"limit\":null,\"available\":null}]');
INSERT INTO `systemlog` VALUES ('3597', '超级管理员', '2020-04-20 04:23:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3598', '超级管理员', '2020-04-20 04:23:32', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
INSERT INTO `systemlog` VALUES ('3599', '超级管理员', '2020-04-20 04:23:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3600', '超级管理员', '2020-04-20 04:23:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:checkLogin', '[]');
INSERT INTO `systemlog` VALUES ('3601', '超级管理员', '2020-04-20 04:23:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.NoticeController:loadAllNotice', '[{\"page\":1,\"limit\":5,\"available\":null,\"title\":null,\"opername\":null,\"endTime\":null,\"startTime\":null}]');
INSERT INTO `systemlog` VALUES ('3602', '超级管理员', '2020-04-20 04:23:33', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.RoleController:loadAllRole', '[{\"page\":1,\"limit\":10,\"available\":null,\"name\":null,\"remark\":null}]');
INSERT INTO `systemlog` VALUES ('3604', '超级管理员', '2020-04-20 04:23:45', '127.0.0.1', '湖南郴州', 'com.jun.plugin.system.controller.LoginController:loadIndexMenu', '[]');
