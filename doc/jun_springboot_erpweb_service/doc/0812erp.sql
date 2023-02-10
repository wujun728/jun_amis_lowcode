/*
 Navicat Premium Data Transfer

 Source Server         : Alimysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 116.62.44.5:3310
 Source Schema         : 0812erp

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 14/01/2020 10:22:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `connectionperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES (1, '小张超市', '111', '武汉', '027-9123131', '张大明', '138123123123', '中国银行', '654431331343413', '213123@sina.com', '430000', 0);
INSERT INTO `bus_customer` VALUES (2, '小明超市', '222', '深圳', '0755-9123131', '张小明', '138123123123', '中国银行', '654431331343413', '213123@sina.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (3, '快七超市', '430000', '武汉', '027-11011011', '雷生', '13434134131', '招商银行', '6543123341334133', '6666@66.com', '545341', 1);
INSERT INTO `bus_customer` VALUES (5, '33', '33', '333', '333', '333', '333', '333', '333', '333', '333', 1);

-- ----------------------------
-- Table structure for bus_goods
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `produceplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodspackage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `promitcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `dangernum` int(11) NULL DEFAULT NULL,
  `goodsimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(11) NULL DEFAULT NULL,
  `providerid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sq0btr2v2lq8gt8b4gb8tlk0i`(`providerid`) USING BTREE,
  CONSTRAINT `bus_goods_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_goods
-- ----------------------------
INSERT INTO `bus_goods` VALUES (1, '娃哈哈', '武汉', '120ML', '瓶', 'PH12345', 'PZ1234', '小孩子都爱的', 2, 1040, 10, '/2018-12-25/userface1.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (2, '旺旺雪饼[小包]', '仙桃', '包', '袋', 'PH12312312', 'PZ12312', '好吃不上火', 4, 1000, 10, '/2018-12-25/userface2.jpg', 1, 1);
INSERT INTO `bus_goods` VALUES (3, '旺旺大礼包', '仙桃', '盒', '盒', '11', '11', '111', 28, 1070, 100, 'group1/M00/00/00/rBB2Ol4ZmsmAfgrBAAGFB81JOyo42.jpeg', 1, 1);
INSERT INTO `bus_goods` VALUES (4, '娃哈哈', '武汉', '200ML', '瓶', '11', '111', '12321', 3, 1000, 10, 'group1/M00/00/00/rBB2Ol4ZfFmARgz_AAAw-Yt4VQ4282.png', 1, 3);
INSERT INTO `bus_goods` VALUES (5, '娃哈哈', '武汉', '300ML', '瓶', '1234', '12321', '22221111', 3, 1000, 100, 'group1/M00/00/00/rBB2Ol4ZfESANUzrAAAnSbLcDqs110.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (10, '123', '213', '123', '123', '123', '123', '123', 123123, 213123, 123, '', 1, 2);

-- ----------------------------
-- Table structure for bus_inport
-- ----------------------------
DROP TABLE IF EXISTS `bus_inport`;
CREATE TABLE `bus_inport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inporttime` datetime(0) NULL DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inportprice` double NULL DEFAULT NULL,
  `providerid` int(11) NULL DEFAULT NULL,
  `goodsid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_1o4bujsyd2kl4iqw48fie224v`(`providerid`) USING BTREE,
  INDEX `FK_ho29poeu4o2dxu4rg1ammeaql`(`goodsid`) USING BTREE,
  CONSTRAINT `bus_inport_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_inport_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `bus_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_inport
-- ----------------------------
INSERT INTO `bus_inport` VALUES (1, '微信', '2018-05-07 00:00:00', '张三', 100, '备注', 3.5, 1, 1);
INSERT INTO `bus_inport` VALUES (2, '支付宝', '2018-05-07 00:00:00', '张三', 1000, '无', 2.5, 3, 3);
INSERT INTO `bus_inport` VALUES (3, '银联', '2018-05-07 00:00:00', '张三', 100, '1231', 111, 3, 3);
INSERT INTO `bus_inport` VALUES (4, '银联', '2018-05-07 00:00:00', '张三', 1000, '无', 2, 3, 1);
INSERT INTO `bus_inport` VALUES (5, '银联', '2018-05-07 00:00:00', '张三', 100, '无', 1, 3, 1);
INSERT INTO `bus_inport` VALUES (6, '银联', '2018-05-07 00:00:00', '张三', 100, '1231', 2.5, 1, 2);
INSERT INTO `bus_inport` VALUES (8, '支付宝', '2018-05-07 00:00:00', '张三', 100, '', 1, 3, 1);
INSERT INTO `bus_inport` VALUES (10, '支付宝', '2018-08-07 17:17:57', '超级管理员', 100, 'sadfasfdsa', 1.5, 3, 1);
INSERT INTO `bus_inport` VALUES (11, '支付宝', '2018-09-17 16:12:25', '超级管理员', 21, '21', 21, 1, 3);
INSERT INTO `bus_inport` VALUES (12, '微信', '2018-12-25 16:48:24', '超级管理员', 100, '123213213', 12321321, 3, 1);
INSERT INTO `bus_inport` VALUES (14, '支付宝', '2019-09-28 03:47:16', '超级管理员', 70, '1111', 4.5, 1, 3);
INSERT INTO `bus_inport` VALUES (16, '支付宝', '2020-01-13 02:08:23', '超级管理员', 100, '', 0.8, 3, 1);

-- ----------------------------
-- Table structure for bus_outport
-- ----------------------------
DROP TABLE IF EXISTS `bus_outport`;
CREATE TABLE `bus_outport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providerid` int(11) NULL DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outporttime` datetime(0) NULL DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outportprice` double(10, 2) NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsid` int(11) NULL DEFAULT NULL,
  `inportid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_outport
-- ----------------------------
INSERT INTO `bus_outport` VALUES (8, 1, '支付宝', '2019-11-16 08:21:43', '超级管理员', 4.50, 10, '坏了', 3, 14);
INSERT INTO `bus_outport` VALUES (9, 1, '支付宝', '2019-11-16 08:43:06', '超级管理员', 4.50, 10, '111', 3, 14);
INSERT INTO `bus_outport` VALUES (10, 3, '支付宝', '2020-01-13 02:27:41', '超级管理员', 0.80, 10, '过期了', 1, 16);

-- ----------------------------
-- Table structure for bus_provider
-- ----------------------------
DROP TABLE IF EXISTS `bus_provider`;
CREATE TABLE `bus_provider`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `connectionperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_provider
-- ----------------------------
INSERT INTO `bus_provider` VALUES (1, '旺旺食品', '434000', '仙桃', '0728-4124312', '小明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 0);
INSERT INTO `bus_provider` VALUES (2, '达利园食品', '430000', '汉川', '0728-4124312', '大明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (3, '娃哈哈集团', '513131', '杭州', '21312', '小晨', '12312', '建设银行', '512314141541', '123131', '312312', 1);
INSERT INTO `bus_provider` VALUES (5, '11', '11', '1', '111', '11', '11', '11', '11', '11', '111', 1);
INSERT INTO `bus_provider` VALUES (6, '3213', '312', '12312', '312312', '312321', '3123123', '1231232', '3123', '3123', '1321312', 1);

-- ----------------------------
-- Table structure for bus_sales
-- ----------------------------
DROP TABLE IF EXISTS `bus_sales`;
CREATE TABLE `bus_sales`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NULL DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salestime` datetime(0) NULL DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `saleprice` decimal(10, 2) NULL DEFAULT NULL,
  `goodsid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_sales
-- ----------------------------
INSERT INTO `bus_sales` VALUES (2, 1, '支付宝', '2020-01-13 07:06:08', '超级管理员', 50, '好人啊', 1.50, 1);

-- ----------------------------
-- Table structure for bus_salesback
-- ----------------------------
DROP TABLE IF EXISTS `bus_salesback`;
CREATE TABLE `bus_salesback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) NULL DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salesbacktime` datetime(0) NULL DEFAULT NULL,
  `salebackprice` double(10, 2) NULL DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salesid` int(11) NULL DEFAULT NULL,
  `goodsid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_salesback
-- ----------------------------
INSERT INTO `bus_salesback` VALUES (1, 1, '支付宝', '2020-01-13 07:25:22', 1.50, '超级管理员', 50, '好人啊', 2, 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级部门ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `spread` int(11) NULL DEFAULT 0 COMMENT '是否展开0不展开1展开',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `available` int(11) NULL DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(11) NULL DEFAULT NULL COMMENT '排序码【为了调事显示顺序】',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '总经办', 1, '大BOSS', '深圳1', 1, 1, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (2, 1, '销售部', 1, '程序员屌丝', '武汉', 1, 2, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (3, 1, '运营部', 0, '无', '武汉', 1, 3, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (4, 1, '生产部', 0, '无', '武汉', 1, 4, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (5, 2, '销售一部', 0, '销售一部', '武汉', 1, 5, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (6, 2, '销售二部', 0, '销售二部', '武汉', 1, 6, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (7, 3, '运营一部', 0, '运营一部', '武汉', 1, 7, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (8, 2, '销售三部', 0, '销售三部', '11', 1, 8, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (9, 2, '销售四部', 0, '销售四部', '222', 1, 9, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (10, 2, '销售五部', 0, '销售五部', '33', 1, 10, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (35, 4, '生产一部', 0, '生产一部', '武汉', 1, 12, '2019-11-11 11:36:44');

-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loginip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logintime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 542 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES (540, '超级管理员-system', '127.0.0.1', '2020-01-14 02:22:05');
INSERT INTO `sys_loginfo` VALUES (541, '超级管理员-system', '127.0.0.1', '2020-01-14 02:22:06');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级菜单ID',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型[topmenu/leftmenu/permission]',
  `typecode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'topmenu:system/business\r\npermission:menu:addMenu\r\n',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '_self',
  `spread` int(11) NULL DEFAULT NULL COMMENT '是否展开',
  `ordernum` int(11) NULL DEFAULT NULL COMMENT '排序码',
  `available` int(11) NULL DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, 'topmenu', 'business', '业务管理', 'fa fa-newspaper-o', '', '_self', 1, 2, 1);
INSERT INTO `sys_menu` VALUES (2, 0, 'topmenu', 'system', '系统管理', 'fa fa-gear', '', '_self', 0, 1, 1);
INSERT INTO `sys_menu` VALUES (3, 1, 'leftmenu', 'business', '基础数据管理', 'fa fa-gear', '', '_self', 1, 3, 1);
INSERT INTO `sys_menu` VALUES (4, 1, 'leftmenu', 'business', '进货管理', 'fa fa-gear', '', '_self', 0, 4, 1);
INSERT INTO `sys_menu` VALUES (5, 1, 'leftmenu', 'business', '销售管理', 'fa fa-gear', '', '_self', 0, 5, 1);
INSERT INTO `sys_menu` VALUES (6, 3, 'leftmenu', 'business', '客户管理', 'fa fa-gear', 'resources/page/business/customer/customerManager.html', '_self', 0, 6, 1);
INSERT INTO `sys_menu` VALUES (7, 3, 'leftmenu', 'business', '供应商管理', 'fa fa-gear', 'resources/page/business/provider/providerManager.html', '_self', 0, 7, 1);
INSERT INTO `sys_menu` VALUES (8, 3, 'leftmenu', 'business', '商品管理', 'fa fa-gear', 'resources/page/business/goods/goodsManager.html', '_self', 0, 8, 1);
INSERT INTO `sys_menu` VALUES (9, 4, 'leftmenu', 'business', '商品进货', 'fa fa-gear', 'resources/page/business/inport/inportManager.html', '_self', 0, 9, 1);
INSERT INTO `sys_menu` VALUES (10, 4, 'leftmenu', 'business', '商品退货查询', 'fa fa-gear', 'resources/page/business/outport/outportManager.html', '_self', 0, 10, 1);
INSERT INTO `sys_menu` VALUES (11, 5, 'leftmenu', 'business', '商品销售', 'fa fa-gear', 'resources/page/business/sales/salesManager.html', '_self', 0, 11, 1);
INSERT INTO `sys_menu` VALUES (12, 5, 'leftmenu', 'business', '销售退货查询', 'fa fa-gear', 'resources/page/business/salesback/salesbackManager.html', '_self', 0, 12, 1);
INSERT INTO `sys_menu` VALUES (13, 2, 'leftmenu', 'system', '系统管理', 'fa fa-gear', '', '_self', 0, 13, 1);
INSERT INTO `sys_menu` VALUES (14, 2, 'leftmenu', 'system', '其它管理', 'fa fa-gear', '', '_self', 0, 14, 1);
INSERT INTO `sys_menu` VALUES (15, 13, 'leftmenu', 'system', '部门管理', 'fa fa-gear', 'resources/page/system/dept/deptManager.html', '_self', 0, 15, 1);
INSERT INTO `sys_menu` VALUES (16, 13, 'leftmenu', 'system', '菜单管理', 'fa fa-gear', 'resources/page/system/menu/menuManager.html', '_self', 0, 16, 1);
INSERT INTO `sys_menu` VALUES (17, 13, 'leftmenu', 'system', '角色管理', 'fa fa-gear', 'resources/page/system/role/roleManager.html', '_self', 0, 17, 1);
INSERT INTO `sys_menu` VALUES (18, 13, 'leftmenu', 'system', '用户管理', 'fa fa-gear', 'resources/page/system/user/userManager.html', '_self', 0, 18, 1);
INSERT INTO `sys_menu` VALUES (19, 14, 'leftmenu', 'system', '登陆日志', 'fa fa-gear', 'resources/page/system/loginfo/loginfoManager.html', '_self', 0, 19, 1);
INSERT INTO `sys_menu` VALUES (20, 14, 'leftmenu', 'system', '数据源监控', 'fa fa-gear', 'http://127.0.0.1:8080/druid/', '_self', 0, 20, 1);
INSERT INTO `sys_menu` VALUES (21, 14, 'leftmenu', 'system', '系统公告', 'fa fa-gear', 'resources/page/system/notice/noticeManager.html', '_self', 0, 21, 1);
INSERT INTO `sys_menu` VALUES (22, 14, 'leftmenu', 'system', '图标管理', 'fa fa-gear', 'resources/page/system/common/icon.html', '_self', 0, 22, 1);
INSERT INTO `sys_menu` VALUES (23, 6, 'permission', 'customer:query', '客户查询', NULL, NULL, '_self', NULL, 23, 1);
INSERT INTO `sys_menu` VALUES (24, 6, 'permission', 'customer:add', '客户添加', NULL, NULL, '_self', NULL, 24, 1);
INSERT INTO `sys_menu` VALUES (25, 6, 'permission', 'customer:delete', '客户删除', NULL, NULL, '_self', NULL, 25, 1);
INSERT INTO `sys_menu` VALUES (26, 6, 'permission', 'customer:update', '客户更新', NULL, NULL, '_self', NULL, 26, 1);
INSERT INTO `sys_menu` VALUES (32, 15, 'permission', 'dept:add', '部门添加', '', '', '_self', 0, 27, 1);
INSERT INTO `sys_menu` VALUES (33, 15, 'permission', 'dept:update', '部门修改', '', '', '_self', 0, 28, 1);
INSERT INTO `sys_menu` VALUES (34, 15, 'permission', 'dept:delete', '部门删除', '', '', '_self', 0, 29, 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `opername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '关于系统V1.3更新公告', '2', '2018-08-07 00:00:00', '管理员');
INSERT INTO `sys_notice` VALUES (10, '关于系统V1.2更新公告', '12312312<img src=\"/resources/layui/images/face/42.gif\" alt=\"[抓狂]\">', '2018-08-07 00:00:00', '超级管理员');
INSERT INTO `sys_notice` VALUES (11, '关于系统V1.1更新公告', '21321321321<img src=\"/resources/layui/images/face/18.gif\" alt=\"[右哼哼]\">', '2018-08-07 00:00:00', '超级管理员');
INSERT INTO `sys_notice` VALUES (18, '测试测试', '<div><div><p>本文以淘宝为例，介绍从一百个并发到千万级并发下服务端架构的演进过程，同时列举出每个演进阶段遇到的相关技术，让大家对架构的演进有一个整体的认知，文章最后汇总了一些架构设计的原则。</p>\n<p>在介绍架构之前，为了避免部分读者对架构设计中的一些概念不了解，下面对几个最基础的概念进行介绍：</p>\n<p>①分布式：系统中的多个模块在不同服务器上部署，即可称为分布式系统，如 Tomcat 和数据库分别部署在不同的服务器上，或两个相同功能的 Tomcat 分别部署在不同服务器上。</p>\n<p>②高可用：系统中部分节点失效时，其他节点能够接替它继续提供服务，则可认为系统具有高可用性。</p>\n<p>③集群：一个特定领域的软件部署在多台服务器上并作为一个整体提供一类服务，这个整体称为集群。</p>\n<p>如 Zookeeper 中的 Master 和 Slave 分别部署在多台服务器上，共同组成一个整体提供集中配置服务。</p>\n<p>在常见的集群中，客户端往往能够连接任意一个节点获得服务，并且当集群中一个节点掉线时，其他节点往往能够自动的接替它继续提供服务，这时候说明集群具有高可用性。</p>\n<p>④负载均衡：请求发送到系统时，通过某些方式把请求均匀分发到多个节点上，使系统中每个节点能够均匀的处理请求负载，则可认为系统是负载均衡的。</p>\n<p>⑤正向代理和反向代理：系统内部要访问外部网络时，统一通过一个代理服务器把请求转发出去，在外部网络看来就是代理服务器发起的访问，此时代理服务器实现的是正向代理。</p>\n<p>当外部请求进入系统时，代理服务器把该请求转发到系统中的某台服务器上，对外部请求来说，与之交互的只有代理服务器，此时代理服务器实现的是反向代理。</p>\n<p>简单来说，正向代理是代理服务器代替系统内部来访问外部网络的过程，反向代理是外部请求访问系统时通过代理服务器转发到内部服务器的过程。</p>\n<p><strong>架构演进</strong></p></div><br><br>作者：戏入子迷<br>链接：https://www.jianshu.com/p/537b3ee7229d<br>来源：简书<br>著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</div>', '2020-01-06 07:32:52', '超级管理员');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(11) NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有所有菜单权限', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (4, '基础数据管理员', '基础数据管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (5, '仓库管理员', '仓库管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (6, '销售管理员', '销售管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (7, '系统管理员', '系统管理员', 1, '2019-04-10 14:06:32');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `rid` int(11) NOT NULL COMMENT '角色外键',
  `mid` int(11) NOT NULL COMMENT '菜单外键',
  PRIMARY KEY (`mid`, `rid`) USING BTREE,
  INDEX `FK_tcsr9ucxypb3ce1q5qngsfk33`(`rid`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`mid`) REFERENCES `sys_menu` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 6);
INSERT INTO `sys_role_menu` VALUES (1, 7);
INSERT INTO `sys_role_menu` VALUES (1, 8);
INSERT INTO `sys_role_menu` VALUES (1, 9);
INSERT INTO `sys_role_menu` VALUES (1, 10);
INSERT INTO `sys_role_menu` VALUES (1, 11);
INSERT INTO `sys_role_menu` VALUES (1, 12);
INSERT INTO `sys_role_menu` VALUES (1, 13);
INSERT INTO `sys_role_menu` VALUES (1, 14);
INSERT INTO `sys_role_menu` VALUES (1, 15);
INSERT INTO `sys_role_menu` VALUES (1, 16);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 18);
INSERT INTO `sys_role_menu` VALUES (1, 19);
INSERT INTO `sys_role_menu` VALUES (1, 20);
INSERT INTO `sys_role_menu` VALUES (1, 21);
INSERT INTO `sys_role_menu` VALUES (1, 22);
INSERT INTO `sys_role_menu` VALUES (1, 23);
INSERT INTO `sys_role_menu` VALUES (1, 24);
INSERT INTO `sys_role_menu` VALUES (1, 25);
INSERT INTO `sys_role_menu` VALUES (1, 26);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (4, 1);
INSERT INTO `sys_role_menu` VALUES (4, 3);
INSERT INTO `sys_role_menu` VALUES (4, 4);
INSERT INTO `sys_role_menu` VALUES (4, 5);
INSERT INTO `sys_role_menu` VALUES (4, 6);
INSERT INTO `sys_role_menu` VALUES (4, 7);
INSERT INTO `sys_role_menu` VALUES (4, 8);
INSERT INTO `sys_role_menu` VALUES (4, 9);
INSERT INTO `sys_role_menu` VALUES (4, 10);
INSERT INTO `sys_role_menu` VALUES (4, 11);
INSERT INTO `sys_role_menu` VALUES (4, 12);
INSERT INTO `sys_role_menu` VALUES (4, 23);
INSERT INTO `sys_role_menu` VALUES (4, 24);
INSERT INTO `sys_role_menu` VALUES (4, 25);
INSERT INTO `sys_role_menu` VALUES (4, 26);
INSERT INTO `sys_role_menu` VALUES (5, 1);
INSERT INTO `sys_role_menu` VALUES (5, 3);
INSERT INTO `sys_role_menu` VALUES (5, 4);
INSERT INTO `sys_role_menu` VALUES (5, 8);
INSERT INTO `sys_role_menu` VALUES (5, 9);
INSERT INTO `sys_role_menu` VALUES (5, 10);
INSERT INTO `sys_role_menu` VALUES (6, 1);
INSERT INTO `sys_role_menu` VALUES (6, 3);
INSERT INTO `sys_role_menu` VALUES (6, 5);
INSERT INTO `sys_role_menu` VALUES (6, 6);
INSERT INTO `sys_role_menu` VALUES (6, 11);
INSERT INTO `sys_role_menu` VALUES (6, 12);
INSERT INTO `sys_role_menu` VALUES (6, 23);
INSERT INTO `sys_role_menu` VALUES (6, 24);
INSERT INTO `sys_role_menu` VALUES (6, 25);
INSERT INTO `sys_role_menu` VALUES (6, 26);
INSERT INTO `sys_role_menu` VALUES (7, 2);
INSERT INTO `sys_role_menu` VALUES (7, 13);
INSERT INTO `sys_role_menu` VALUES (7, 14);
INSERT INTO `sys_role_menu` VALUES (7, 15);
INSERT INTO `sys_role_menu` VALUES (7, 16);
INSERT INTO `sys_role_menu` VALUES (7, 17);
INSERT INTO `sys_role_menu` VALUES (7, 18);
INSERT INTO `sys_role_menu` VALUES (7, 19);
INSERT INTO `sys_role_menu` VALUES (7, 20);
INSERT INTO `sys_role_menu` VALUES (7, 21);
INSERT INTO `sys_role_menu` VALUES (7, 22);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `rid` int(11) NOT NULL COMMENT '角色外键',
  `uid` int(11) NOT NULL COMMENT '用户外键',
  PRIMARY KEY (`uid`, `rid`) USING BTREE,
  INDEX `FK_203gdpkwgtow7nxlo2oap5jru`(`rid`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 2);
INSERT INTO `sys_role_user` VALUES (4, 3);
INSERT INTO `sys_role_user` VALUES (6, 4);
INSERT INTO `sys_role_user` VALUES (7, 5);
INSERT INTO `sys_role_user` VALUES (7, 6);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `deptid` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `hiredate` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `ordernum` int(11) NULL DEFAULT NULL,
  `type` int(255) NULL DEFAULT NULL COMMENT '用户类型[0超级管理员1普通用户]',
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `available` int(11) NULL DEFAULT 1 COMMENT ' 是否可用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_loginname`(`loginname`) USING BTREE,
  INDEX `FK_3rrcpvho2w1mx1sfiuuyir1h`(`deptid`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`deptid`) REFERENCES `sys_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'system', '系统深处的男人', 0, '超级管理员', '532ac00e86893901af5f0be6b704dbc7', 1, '2018-06-25 11:06:34', 1, 0, 'group1/M00/00/00/rBB2Ol4ZmqCAQFnqAAV3Pf0yf3E524.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB', 1);
INSERT INTO `sys_user` VALUES (2, '李四', 'ls', '武汉', 0, 'KING', 'b07b848d69e0553b80e601d31571797e', 1, '2018-06-25 11:06:36', 2, 1, 'group1/M00/00/00/rBB2Ol4YRryAf4A_AAAnAemaOoE551.jpg', 'FC1EE06AE4354D3FBF7FDD15C8FCDA71', 1);
INSERT INTO `sys_user` VALUES (3, '王五', 'ww', '武汉', 1, '管理员', '3c3f971eae61e097f59d52360323f1c8', 3, '2018-06-25 11:06:38', 3, 1, NULL, '3D5F956E053C4E85B7D2681386E235D2', 1);
INSERT INTO `sys_user` VALUES (4, '赵六', 'zl', '武汉', 1, '程序员', '2e969742a7ea0c7376e9551d578e05dd', 4, '2018-06-25 11:06:40', 4, 1, NULL, '6480EE1391E34B0886ACADA501E31145', 1);
INSERT INTO `sys_user` VALUES (5, '孙七', 'sq', '武汉', 1, '程序员', '47b4c1ad6e4b54dd9387a09cb5a03de1', 2, '2018-06-25 11:06:43', 5, 1, NULL, 'FE3476C3E3674E5690C737C269FCBF8E', 1);
INSERT INTO `sys_user` VALUES (6, '刘八', 'lb', '深圳', 1, '程序员', 'bcee2b05b4b591106829aec69a094806', 4, '2018-08-06 11:21:12', 6, 1, NULL, 'E6CCF54A09894D998225878BBD139B20', 1);
INSERT INTO `sys_user` VALUES (8, '习大大', 'xidada', '北京', 1, '北京', '4731e3b6ff0327d46423e7d3d891d54e', 7, '2019-09-25 08:47:38', 7, 1, NULL, '407CDF71F56648B4A81643A9C7F1021F', 1);

SET FOREIGN_KEY_CHECKS = 1;
