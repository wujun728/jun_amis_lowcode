/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 118.25.127.32:3307
 Source Schema         : active4j_boot

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 26/03/2020 09:59:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for func_ali_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `func_ali_pay_order`;
CREATE TABLE `func_ali_pay_order`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `CUS_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MSG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SUB_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SUB_MSG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TRADE_NO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `OUT_TRADE_NO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SELLER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `MERCHANT_ORDER_NO` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PRODUCT_CODE` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TOTAL_AMOUNT` double(16, 2) NOT NULL,
  `SUBJECT` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `BODY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `GOODS_DETAIL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PASSBACK_PARAMS` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `ORDER_TIME` datetime(0) NOT NULL,
  `BUYER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_ali_pay_order
-- ----------------------------
INSERT INTO `func_ali_pay_order` VALUES ('099c39595350812d9aa0e9ef60e4efb8', 0, 'admin', '2019-12-16 17:41:37', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576489287889882197', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', '', '付款', '2', NULL, '2019-12-16 17:41:36', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('16fa09c254500d546605857900a75eba', 1, 'admin', '2019-12-17 14:00:16', 'system', '2019-12-17 14:00:34', 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, '2019121722001438250545847189', '1576562416654428157', '2088221837684924', NULL, 'FAST_INSTANT_TRADE_PAY', 0.01, '演示支付宝支付付款0.01元', '演示支付宝支付付款0.01元', NULL, '付款', '3', '2019-12-17 14:00:34', '2019-12-17 14:00:16', '2088112392238256');
INSERT INTO `func_ali_pay_order` VALUES ('1f998c9413a0447514b3a01b3b2ddc48', 0, 'admin', '2019-12-16 17:07:04', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576487205831696224', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', '', '付款', '2', NULL, '2019-12-16 17:07:03', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('29fc76a54bd7490266eca902c88dc2db', 0, 'admin', '2019-12-16 18:04:56', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576490696178966542', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 5.00, '演示支付宝支付付款5.0元', '演示支付宝支付付款5.0元', NULL, '付款', '2', NULL, '2019-12-16 18:04:56', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('3663ba4deb968548bebea9d49b700ff7', 0, 'admin', '2019-12-16 17:32:23', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576488682485559622', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 0.10, '演示支付宝支付付款0.1元', '演示支付宝支付付款0.1元', '', '付款', '2', NULL, '2019-12-16 17:32:22', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('3c55299ab0a59155d7fe4918d1791b0d', 0, 'admin', '2019-12-16 17:59:12', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576490338089267914', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 0.10, '演示支付宝支付付款0.1元', '演示支付宝支付付款0.1元', NULL, '付款', '2', NULL, '2019-12-16 17:59:12', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('4dc22b470f60d9b4d408986c73dbe33e', 0, 'admin', '2019-12-16 17:44:32', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576489463479845967', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', '', '付款', '2', NULL, '2019-12-16 17:44:31', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('5d8f8e47b46e0f78e75fb60e9c8fd52d', 0, 'admin', '2019-12-16 17:34:41', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576488874463569335', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 0.10, '演示支付宝支付付款0.1元', '演示支付宝支付付款0.1元', '', '付款', '2', NULL, '2019-12-16 17:34:41', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('647e6eaef9554583d9328b00ff84185a', 0, 'admin', '2019-12-16 18:05:37', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576490737332496285', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 8.00, '演示支付宝支付付款8.0元', '演示支付宝支付付款8.0元', NULL, '付款', '2', NULL, '2019-12-16 18:05:37', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('76ef4ed5e7364fc7a74a944bcb193e9c', 0, 'admin', '2019-12-16 17:21:19', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576488079154389135', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 0.10, '演示支付宝支付付款0.1元', '演示支付宝支付付款0.1元', '', '付款', '2', NULL, '2019-12-16 17:21:19', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('89f6f5e8e732c4465c82d6419b1d61ff', 0, 'admin', '2019-12-16 17:43:25', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576489394610254771', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', '', '付款', '2', NULL, '2019-12-16 17:43:24', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('8d95f1bb3cf7790035d79376f8e63596', 0, 'admin', '2019-12-18 08:46:40', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576630000349493241', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', NULL, '付款', '2', NULL, '2019-12-18 08:46:40', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('9844cad24268e4231a934d65ff69169a', 0, 'admin', '2019-12-16 18:04:00', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576490640840177249', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', NULL, '付款', '2', NULL, '2019-12-16 18:04:00', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('a0895a2dba3863d961f80d2babd9fb7f', 0, 'admin', '2019-12-18 08:45:54', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576629954615426244', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', NULL, '付款', '2', NULL, '2019-12-18 08:45:54', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('c0521909f0562590483c8973b5b72ad8', 0, 'admin', '2019-12-17 13:55:50', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576562149983287122', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 0.10, '演示支付宝支付付款0.1元', '演示支付宝支付付款0.1元', NULL, '付款', '2', NULL, '2019-12-17 13:55:50', NULL);
INSERT INTO `func_ali_pay_order` VALUES ('c8a7b0b56aa3f689e711f03e24914f10', 1, 'admin', '2019-12-17 14:48:04', 'system', '2019-12-17 14:48:28', 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, '2019121722001438250546845832', '1576565283979231878', '2088221837684924', NULL, 'FAST_INSTANT_TRADE_PAY', 0.01, '演示支付宝支付付款0.01元', '演示支付宝支付付款0.01元', NULL, '付款', '3', '2019-12-17 14:48:28', '2019-12-17 14:48:04', '2088112392238256');
INSERT INTO `func_ali_pay_order` VALUES ('d0bc01902d1cc4de8a603b6d27b3c725', 0, 'admin', '2019-12-16 17:48:28', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', NULL, NULL, NULL, NULL, NULL, '1576489608631441224', NULL, NULL, 'FAST_INSTANT_TRADE_PAY', 1.00, '演示支付宝支付付款1.0元', '演示支付宝支付付款1.0元', '', '付款', '2', NULL, '2019-12-16 17:48:23', NULL);

-- ----------------------------
-- Table structure for func_export_example
-- ----------------------------
DROP TABLE IF EXISTS `func_export_example`;
CREATE TABLE `func_export_example`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SEX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通讯组名称',
  `AGE` int(10) NULL DEFAULT NULL,
  `PHONE` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BIRTHDAY` datetime(0) NULL DEFAULT NULL,
  `BALANCE` double(20, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_export_example
-- ----------------------------
INSERT INTO `func_export_example` VALUES ('123123', 0, 'system', '2019-12-17 10:57:43', NULL, NULL, '张三', '男', 21, '13338800000', '2019-12-02 10:58:08', 1234.98);
INSERT INTO `func_export_example` VALUES ('168f50533b41eecc0c6b273df2bf0502', 0, 'admin', '2019-12-17 17:23:23', NULL, NULL, '王红', '女', 27, '13338800002', '1994-04-03 00:00:00', 32.00);
INSERT INTO `func_export_example` VALUES ('2daad24275d0a933a89ddc1ec7c2f22e', 0, 'admin', '2019-12-17 17:03:13', NULL, NULL, '王红', '女', 27, '13338800002', NULL, 32.00);
INSERT INTO `func_export_example` VALUES ('3c37b8eba7e0b85df0c95c47c416c72d', 0, 'admin', '2019-12-17 17:23:23', NULL, NULL, '李四', '男', 30, '13338800001', '1989-12-12 00:00:00', 32341.32);
INSERT INTO `func_export_example` VALUES ('3fdf7ecf846639ef7f6d826c22e38189', 0, 'admin', '2019-12-17 17:11:45', NULL, NULL, '李四', '男', 30, '13338800001', NULL, 32341.32);
INSERT INTO `func_export_example` VALUES ('514fa3f4b6b45d41d3406d2524deaacf', 0, 'admin', '2019-12-17 17:23:23', NULL, NULL, '张三', '男', 26, '13338800000', '1993-12-03 00:00:00', 1234.90);
INSERT INTO `func_export_example` VALUES ('5365e7f244ae3e1d4f38ee0a491988ee', 0, 'admin', '2019-12-17 18:02:03', NULL, NULL, '张三', '男', 26, '13338800000', '1993-12-03 00:00:00', 1234.90);
INSERT INTO `func_export_example` VALUES ('5744f85848f258bde43b66e5b7cb12f4', 0, 'admin', '2019-12-17 17:03:13', NULL, NULL, '李四', '男', 30, '13338800001', NULL, 32341.32);
INSERT INTO `func_export_example` VALUES ('5880bc6371d1b4f0db5e2d7cdb974d0d', 0, 'admin', '2019-12-17 17:18:26', NULL, NULL, '王红', '女', 27, '13338800002', '1994-04-03 00:00:00', 32.00);
INSERT INTO `func_export_example` VALUES ('7a3e9176386169504363a6a35b83d429', 0, 'admin', '2019-12-17 17:01:31', NULL, NULL, '王红', '女', 27, '13338800002', NULL, 32.00);
INSERT INTO `func_export_example` VALUES ('7ba5441b2fc97c08ce307357888c789c', 0, 'admin', '2019-12-17 17:18:26', NULL, NULL, '张三', '男', 26, '13338800000', '1993-12-03 00:00:00', 1234.90);
INSERT INTO `func_export_example` VALUES ('7d9f232affbe35e369c085c6d1d18299', 0, 'admin', '2019-12-17 17:16:49', NULL, NULL, '王红', '女', 27, '13338800002', NULL, 32.00);
INSERT INTO `func_export_example` VALUES ('8e99bb78f875fa4edccb2dfdc0b8a453', 0, 'admin', '2019-12-17 17:11:45', NULL, NULL, '张三', '男', 26, '13338800000', NULL, 1234.90);
INSERT INTO `func_export_example` VALUES ('a409417fb82edb9f5a37af5c1534d06d', 0, 'admin', '2019-12-17 17:18:26', NULL, NULL, '李四', '男', 30, '13338800001', '1989-12-12 00:00:00', 32341.32);
INSERT INTO `func_export_example` VALUES ('af5a9dea7d073f3a54e985db7c94fedd', 0, 'admin', '2019-12-17 17:16:49', NULL, NULL, '李四', '男', 30, '13338800001', '1989-12-12 00:00:00', 32341.32);
INSERT INTO `func_export_example` VALUES ('b04e0758ed3a95c04cb3019ba4bfc9ba', 0, 'admin', '2019-12-17 17:01:31', NULL, NULL, '张三', '男', 26, '13338800000', NULL, 1234.90);
INSERT INTO `func_export_example` VALUES ('b2227185e0dbcb7950864270d410ff02', 0, 'admin', '2019-12-17 17:16:49', NULL, NULL, '张三', '男', 26, '13338800000', NULL, 1234.90);
INSERT INTO `func_export_example` VALUES ('b59c109c8823360f8e27c29e16ed543e', 0, 'admin', '2019-12-17 18:02:03', NULL, NULL, '王红', '女', 27, '13338800002', '1994-04-03 00:00:00', 32.00);
INSERT INTO `func_export_example` VALUES ('c09f32fd98e3587ef45c9ebfc619f641', 0, 'admin', '2019-12-17 18:02:03', NULL, NULL, NULL, '男', 0, NULL, NULL, 0.00);
INSERT INTO `func_export_example` VALUES ('c95a8e8dd80dca5eece05303003d0492', 0, 'admin', '2019-12-17 17:11:45', NULL, NULL, '王红', '女', 27, '13338800002', NULL, 32.00);
INSERT INTO `func_export_example` VALUES ('cb82c61bfbd2706e0d7ef883ab19c58f', 0, 'admin', '2019-12-17 17:01:31', NULL, NULL, '李四', '男', 30, '13338800001', NULL, 32341.32);
INSERT INTO `func_export_example` VALUES ('d658e3b879496c5b9174a4f1522f9573', 0, 'admin', '2019-12-17 17:03:13', NULL, NULL, '张三', '男', 26, '13338800000', NULL, 1234.90);
INSERT INTO `func_export_example` VALUES ('dcdc99fb28aead25ea8b6177a114feed', 0, 'admin', '2019-12-17 18:02:03', NULL, NULL, '李四', '男', 30, '13338800001', '1989-12-12 00:00:00', 32341.32);

-- ----------------------------
-- Table structure for func_quartz_job
-- ----------------------------
DROP TABLE IF EXISTS `func_quartz_job`;
CREATE TABLE `func_quartz_job`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `JOB_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '通讯组名称',
  `JOB_GROUP` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_EXECUTE_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DESCRIPTION` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INVOKE_PARAMS` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PREVIOS_TIME` datetime(0) NULL DEFAULT NULL,
  `CONCURRENT_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SHORT_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MISFIRE_POLICY` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_bs_quartz_job_job_no_1`(`JOB_NO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_quartz_job
-- ----------------------------
INSERT INTO `func_quartz_job` VALUES ('5d12d1ffc526fa70007c0869e38167e9', 4, 'admin', '2019-12-13 09:24:31', 'system', '2019-12-13 09:26:08', '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '3', '2', '0/10 * * * * ?', '', 'timerTask.taskMultipleParams(\'a\', true, 100L, 54.23D, 21)', '2019-12-23 09:51:40', '0', '定时任务（有参）', '3');

-- ----------------------------
-- Table structure for func_quartz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `func_quartz_job_log`;
CREATE TABLE `func_quartz_job_log`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `JOB_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '通讯组名称',
  `JOB_GROUP` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `JOB_MESSAGE` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `INVOKE_PARAMS` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `START_TIME` datetime(0) NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `SHORT_NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `EXCEPTION_INFO` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_bs_quartz_job_job_no_1`(`JOB_NO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_quartz_job_log
-- ----------------------------
INSERT INTO `func_quartz_job_log` VALUES ('01cb928b15da3f1ede18f9a9d968bcfd', 0, 'system', '2019-12-23 09:51:20', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：99毫秒', 'timerTask.taskMultipleParams(\'a\', true, 100L, 54.23D, 21)', '2019-12-23 09:51:20', '2019-12-23 09:51:20', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('1b6463e7c764551d8591f09da8454ff4', 0, 'system', '2019-12-13 09:25:35', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5102毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:25:30', '2019-12-13 09:25:35', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('2b11d5b8d419a927a67d48cbfd7b9d16', 0, 'system', '2019-12-13 09:26:25', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5118毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:26:20', '2019-12-13 09:26:25', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('414868a108894d4db33b3ab746c98585', 0, 'system', '2019-12-13 09:25:25', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5112毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:25:20', '2019-12-13 09:25:25', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('448af91dea6996f0c17f02d039627f2d', 0, 'system', '2019-12-23 09:51:30', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：98毫秒', 'timerTask.taskMultipleParams(\'a\', true, 100L, 54.23D, 21)', '2019-12-23 09:51:30', '2019-12-23 09:51:30', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('ae849a9c6a410f89cbe22df2c1644496', 0, 'system', '2019-12-13 09:26:05', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5125毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:26:00', '2019-12-13 09:26:05', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('d6509cdef8085cede5b265f6dc46d753', 0, 'system', '2019-12-13 09:25:55', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5105毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:25:50', '2019-12-13 09:25:55', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('d9286af8c37a48176af0e24d26e1e2aa', 0, 'system', '2019-12-23 09:51:40', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：92毫秒', 'timerTask.taskMultipleParams(\'a\', true, 100L, 54.23D, 21)', '2019-12-23 09:51:40', '2019-12-23 09:51:40', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('e79a9503751e9a64760e8e5727685597', 0, 'system', '2019-12-13 09:25:45', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5104毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:25:40', '2019-12-13 09:25:45', '定时任务（有参）', NULL);
INSERT INTO `func_quartz_job_log` VALUES ('f93ccf21c1c1ba56177c3967ae678491', 0, 'system', '2019-12-13 09:25:15', NULL, NULL, '04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0', '定时任务（有参） 总耗时：5099毫秒', 'timerTask.taskParams(\'active4j\')', '2019-12-13 09:25:10', '2019-12-13 09:25:15', '定时任务（有参）', NULL);

-- ----------------------------
-- Table structure for func_sys_message
-- ----------------------------
DROP TABLE IF EXISTS `func_sys_message`;
CREATE TABLE `func_sys_message`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `TITLE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息标题',
  `CONTENT` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息类型',
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息状态 ',
  `PUBLIC_TIME` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_sys_message
-- ----------------------------
INSERT INTO `func_sys_message` VALUES ('42f1ce014b1fbbc89cffd8274cc32058', 1, 'admin', '2019-12-19 14:23:32', 'admin', '2019-12-19 14:23:35', 'dewdfr', 'r334r34r34', '1', '1', '2019-12-19 14:23:35');
INSERT INTO `func_sys_message` VALUES ('5dc6045dd3582faca500ffe48dd5e5ca', 1, 'admin', '2019-12-19 11:32:36', 'admin', '2019-12-19 11:32:40', 'ef', 'ferf', '1', '1', '2019-12-19 11:32:40');
INSERT INTO `func_sys_message` VALUES ('5ef3c695332f680d5675e410ba53d65d', 2, 'admin', '2019-12-19 10:36:39', 'admin', '2019-12-19 10:36:42', '平台正式上线啦！', '12月平台正式上线，欢迎体验！', '1', '1', '2019-12-19 10:36:42');
INSERT INTO `func_sys_message` VALUES ('df8d2430ecf69205fda107f18ab0b564', 1, 'admin', '2019-12-19 14:23:26', 'admin', '2019-12-19 14:23:38', 'deww', 'dewdwe', '0', '1', '2019-12-19 14:23:38');

-- ----------------------------
-- Table structure for func_sys_user_message
-- ----------------------------
DROP TABLE IF EXISTS `func_sys_user_message`;
CREATE TABLE `func_sys_user_message`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `TITLE` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息标题',
  `CONTENT` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息类型',
  `READ_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '阅读状态',
  `DELETE_STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除状态',
  `SYS_MESSAGE_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统消息ID',
  `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `PUBLIC_TIME` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `func_sys_user_message_idx_one_sd`(`SYS_MESSAGE_ID`) USING BTREE,
  INDEX `func_sys_user_message_id_fidwa_w`(`USER_ID`) USING BTREE,
  CONSTRAINT `func_sys_user_message_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_sys_user_message
-- ----------------------------
INSERT INTO `func_sys_user_message` VALUES ('2b0aa64c803c76dc742b0dd9b37d86c7', 1, 'system', '2019-12-19 11:32:40', 'admin', '2019-12-19 14:05:12', 'ef', 'ferf', '1', '0', '1', '5dc6045dd3582faca500ffe48dd5e5ca', 'd4f2018bfc34b5c92942abebff2a829f', '2019-12-19 11:32:40');
INSERT INTO `func_sys_user_message` VALUES ('43a1025c1518bade75a8caf1219e3997', 2, 'system', '2019-12-19 10:36:42', 'admin', '2019-12-19 14:22:24', '平台正式上线啦！', '12月平台正式上线，欢迎体验！', '0', '1', '1', '5ef3c695332f680d5675e410ba53d65d', 'd4f2018bfc34b5c92942abebff2a829f', '2019-12-19 10:36:42');
INSERT INTO `func_sys_user_message` VALUES ('67f2425c6c3abb7a6e8eec68ec92ef3a', 0, 'system', '2019-12-19 10:36:42', NULL, NULL, '平台正式上线啦！', '12月平台正式上线，欢迎体验！', '0', '0', '0', '5ef3c695332f680d5675e410ba53d65d', '94e270789548580b931e5a0ee66a0b5b', '2019-12-19 10:36:42');
INSERT INTO `func_sys_user_message` VALUES ('8428048cd7406b52b36531be2b29dd34', 14, 'system', '2019-12-19 14:23:38', 'admin', '2019-12-19 14:23:48', 'deww', 'dewdwe', '0', '1', '0', 'df8d2430ecf69205fda107f18ab0b564', 'd4f2018bfc34b5c92942abebff2a829f', '2019-12-19 14:23:38');
INSERT INTO `func_sys_user_message` VALUES ('a19d3e817b91a8c2165ba9455b157f7b', 0, 'system', '2019-12-19 14:23:38', NULL, NULL, 'deww', 'dewdwe', '0', '0', '0', 'df8d2430ecf69205fda107f18ab0b564', '94e270789548580b931e5a0ee66a0b5b', '2019-12-19 14:23:38');
INSERT INTO `func_sys_user_message` VALUES ('a83454570788da26a3959e9ba874dbad', 0, 'system', '2019-12-19 14:23:35', NULL, NULL, 'dewdfr', 'r334r34r34', '1', '0', '0', '42f1ce014b1fbbc89cffd8274cc32058', '94e270789548580b931e5a0ee66a0b5b', '2019-12-19 14:23:35');
INSERT INTO `func_sys_user_message` VALUES ('d6d0ddd466139d9c82c5ebb94d361a6b', 0, 'system', '2019-12-19 11:32:40', NULL, NULL, 'ef', 'ferf', '1', '0', '0', '5dc6045dd3582faca500ffe48dd5e5ca', '94e270789548580b931e5a0ee66a0b5b', '2019-12-19 11:32:40');
INSERT INTO `func_sys_user_message` VALUES ('f01ec6b26936c53109ba58027f9da8b4', 4, 'system', '2019-12-19 14:23:35', 'admin', '2019-12-19 14:23:55', 'dewdfr', 'r334r34r34', '1', '1', '0', '42f1ce014b1fbbc89cffd8274cc32058', 'd4f2018bfc34b5c92942abebff2a829f', '2019-12-19 14:23:35');

-- ----------------------------
-- Table structure for func_wx_pay_pre_order
-- ----------------------------
DROP TABLE IF EXISTS `func_wx_pay_pre_order`;
CREATE TABLE `func_wx_pay_pre_order`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `APP_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `MCH_ID` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DEVICE_INFO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NONCE_STR` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `SIGN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SIGN_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BODY` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `DETAIL` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ATTACH` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `OUT_TRADE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `FEE_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TOTAL_FEE` int(11) NOT NULL,
  `TRADE_TYPE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SP_BILL_CREATE_IP` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PRODUCT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `OPEN_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TIME_START` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TIME_EXPIRE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `NOTIFY_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `RECEIPT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `LIMIT_PAY` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SCENE_INFO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `PREPAY_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `TRANSACTION_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `CODE_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ERR_CODE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ERR_MSG` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `SUBSCRIBE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `BANK_TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `END_TIME` datetime(0) NULL DEFAULT NULL,
  `CUS_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of func_wx_pay_pre_order
-- ----------------------------
INSERT INTO `func_wx_pay_pre_order` VALUES ('0d16246ba1c07bea0def1f8de7c928fb', 0, 'admin', '2019-12-13 10:04:01', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'eb39wnjoep9oz4m86cqa', NULL, 'MD5', '演示微信支付付款10.0元', '演示微信支付付款10.0元', '付款', '1576202639068322676', 'CNY', 1000, 'NATIVE', '本地', '1576202639068322676', NULL, '20191213100400', '20191213103400', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('0da24ffee37aa5032106516b654b9d49', 0, 'admin', '2019-12-13 10:08:29', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', '7jmjy9tbcpl2nso1o706', NULL, 'MD5', '演示微信支付付款30.0元', '演示微信支付付款30.0元', '付款', '1576202908279592629', 'CNY', 3000, 'NATIVE', '本地', '1576202908279592629', NULL, '20191213100829', '20191213103829', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('0f12195e70067067404d6691d1435137', 1, 'admin', '2019-12-13 13:27:40', 'system', '2019-12-13 13:27:58', 'wx9745811931dd2d75', '1266938001', 'WEB', '74wy4h34vl47j4t6a1kp', NULL, 'MD5', '演示微信支付付款0.01元', '演示微信支付付款0.01元', '付款', '1576214859277275266', 'CNY', 1, 'NATIVE', '49.71.205.8', '1576214859277275266', 'oHRS7wgPTgszHHlnsn_Agcett7M8', '20191213132739', '20191213135739', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, 'wx13132740032828a3ecbb124b1745393100', '4200000434201912134546238250', 'weixin://wxpay/bizpayurl?pr=g3lgLTB', '3', NULL, NULL, 'Y', 'OTHERS', '2019-12-13 13:27:58', 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('1bab48a71361087aeab2543df26b8efe', 0, 'admin', '2019-12-16 18:04:23', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 't18t6lemod7qc7fx91ai', NULL, 'MD5', '演示微信支付付款2.0元', '演示微信支付付款2.0元', '付款', '1576490662289981986', 'CNY', 200, 'NATIVE', '本地', '1576490662289981986', NULL, '20191216180422', '20191216183422', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('5a41c40686c9d0278afe1b9da9668563', 0, 'admin', '2019-12-13 10:17:59', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'swykfad5bc6zuuykqc32', NULL, 'MD5', '演示微信支付付款0.01元', '演示微信支付付款0.01元', '付款', '1576203479502777739', 'CNY', 1, 'NATIVE', '49.71.205.8', '1576203479502777739', NULL, '20191213101759', '20191213104759', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, 'wx13101759728077915af68a5c1481454700', NULL, 'weixin://wxpay/bizpayurl?pr=cdnlq9I', '2', NULL, NULL, NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('6376db79044c1735471c04532baa9650', 0, 'admin', '2019-12-13 10:17:09', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'xf108a3b1z9qqdn75q6p', NULL, 'MD5', '演示微信支付付款0.01元', '演示微信支付付款0.01元', '付款', '1576203429051542569', 'CNY', 1, 'NATIVE', '49.71.205.8', '1576203429051542569', NULL, '20191213101709', '20191213104709', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, 'wx131017097573677f190e1d261492091600', NULL, 'weixin://wxpay/bizpayurl?pr=6N8AyRj', '2', NULL, NULL, NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('6c892d00e1e679eb1d79efc80d13aece', 0, 'admin', '2019-12-13 09:39:04', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'h23tjgpb43v02dxnzsai', NULL, 'MD5', '演示微信支付付款1.0元', '演示微信支付付款1.0元', '付款', '1576201141959627534', 'CNY', 100, 'NATIVE', '本地', '1576201141959627534', NULL, '20191213093904', '20191213100904', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('754ba4b56501e1721181148942b7f175', 0, 'admin', '2019-12-13 09:41:42', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'gwxr3vddsy4hx4182i2b', NULL, 'MD5', '演示微信支付付款6.0元', '演示微信支付付款6.0元', '付款', '1576201299631731364', 'CNY', 600, 'NATIVE', '本地', '1576201299631731364', NULL, '20191213094142', '20191213101142', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('a3c97dc610a8d872b1ccf371859d31a7', 0, 'admin', '2019-12-13 10:06:45', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'n48eq4w96q1sb5zv3fgx', NULL, 'MD5', '演示微信支付付款80.0元', '演示微信支付付款80.0元', '付款', '1576202803417518768', 'CNY', 8000, 'NATIVE', '本地', '1576202803417518768', NULL, '20191213100644', '20191213103644', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('aaf58bd2c07fee9a7a8383673be323f0', 0, 'admin', '2019-12-13 10:34:42', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'f52iixbkf9gkg2kic8f7', NULL, 'MD5', '演示微信支付付款0.01元', '演示微信支付付款0.01元', '付款', '1576204482153121276', 'CNY', 1, 'NATIVE', '49.71.205.8', '1576204482153121276', NULL, '20191213103442', '20191213110442', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, 'wx13103442821603ea161b31c31630469900', NULL, 'weixin://wxpay/bizpayurl?pr=J8A428y', '2', NULL, NULL, NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('c29a671cfc734361a22cc71e360c665f', 0, 'admin', '2019-12-13 09:34:04', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'huku9i6l22qxqkrxf24g', NULL, 'MD5', '演示微信支付付款1.0元', '演示微信支付付款1.0元', '付款', '1576200839213982181', 'CNY', 100, 'NATIVE', '本地', '1576200839213982181', NULL, '20191213093403', '20191213100403', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('d43e760649662d8e33f75e876cff7128', 0, 'admin', '2019-12-13 11:28:55', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'm9ytri91pmodv3zt94x3', NULL, 'MD5', '演示微信支付付款0.01元', '演示微信支付付款0.01元', '付款', '1576207735199443761', 'CNY', 1, 'NATIVE', '49.71.205.8', '1576207735199443761', NULL, '20191213112855', '20191213115855', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, 'wx13112855882001b15ad76ea51024438200', NULL, 'weixin://wxpay/bizpayurl?pr=LOrlKcK', '2', NULL, NULL, NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('f3a1bbb58f33e852a586c99fd8ca0b31', 0, 'admin', '2019-12-13 09:36:06', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', '5s33xtu32yyk5ufykx78', NULL, 'MD5', '演示微信支付付款1.0元', '演示微信支付付款1.0元', '付款', '1576200964517764951', 'CNY', 100, 'NATIVE', '本地', '1576200964517764951', NULL, '20191213093606', '20191213100606', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('f49b4aa7abc95290629f06e407f2855b', 0, 'admin', '2019-12-18 08:48:14', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'lxafgy86ez8nzakzmdjn', NULL, 'MD5', '演示微信支付付款1.0元', '演示微信支付付款1.0元', '付款', '1576630094092142986', 'CNY', 100, 'NATIVE', '本地', '1576630094092142986', NULL, '20191218084814', '20191218091814', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');
INSERT INTO `func_wx_pay_pre_order` VALUES ('f69cc233d57ac35a872a6a54b92b2cab', 0, 'admin', '2019-12-13 10:01:45', NULL, NULL, 'wx9745811931dd2d75', '1266938001', 'WEB', 'r1qlso4qq09g69s4xh2d', NULL, 'MD5', '演示微信支付付款8.0元', '演示微信支付付款8.0元', '付款', '1576202503923342364', 'CNY', 800, 'NATIVE', '本地', '1576202503923342364', NULL, '20191213100145', '20191213103145', 'http://www.active4j.com/demo/func/weixin/payback', 'N', 'no_credit', NULL, NULL, NULL, NULL, '1', 'invalid spbill_create_ip', 'invalid spbill_create_ip', NULL, NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('schedulerFactoryBean', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', '0/10 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('schedulerFactoryBean', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', NULL, 'com.zhonghe.active4j.func.timer.util.QuartzJobDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174001451554152545A5F4A4F425F504152414D5F4B455973720036636F6D2E7A686F6E6768652E616374697665346A2E66756E632E74696D65722E656E746974792E51756172747A4A6F62456E74697479ECE5D0648AE9F1CA02000D4C0010636F6E63757272656E745374617475737400124C6A6176612F6C616E672F537472696E673B4C000E63726F6E45787072657373696F6E71007E00094C000B6465736372697074696F6E71007E00094C000C696E766F6B65506172616D7371007E00094C00106A6F624578656375746553746174757371007E00094C00086A6F6247726F757071007E00094C00076A6F624E616D6571007E00094C00056A6F624E6F71007E00094C00096A6F6253746174757371007E00094C000D6D697366697265506F6C69637971007E00094C00086E65787454696D657400104C6A6176612F7574696C2F446174653B4C000C70726576696F757354696D6571007E000A4C000973686F72744E616D6571007E00097872002D636F6D2E7A686F6E6768652E616374697665346A2E636F6D6D6F6E2E656E746974792E42617365456E74697479CA009F72649BFAED0200064C000A6372656174654461746571007E000A4C000A6372656174654E616D6571007E00094C0002696471007E00094C000A7570646174654461746571007E000A4C000A7570646174654E616D6571007E00094C000876657273696F6E737400134C6A6176612F6C616E672F496E74656765723B78720035636F6D2E62616F6D69646F752E6D796261746973706C75732E657874656E73696F6E2E6163746976657265636F72642E4D6F64656C000000000000000102000078707372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016EFCDB74987874000561646D696E74002035643132643166666335323666613730303037633038363965333831363765397371007E000F77080000016EFCDCEF807874000673797374656D737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000027400013074000E302F3130202A202A202A202A203F74000074003974696D65725461736B2E7461736B4D756C7469706C65506172616D73282761272C20747275652C203130304C2C2035342E3233442C203231297400013374000744454641554C5474002744454641554C543034333137393734613536643432653839353663333362346138633333653666740020303433313739373461353664343265383935366333336234613863333365366671007E001C740001337371007E000F77080000016F307372B0787371007E000F77080000016EFCDD1E6078740018E5AE9AE697B6E4BBBBE58AA1EFBC88E69C89E58F82EFBC897800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('schedulerFactoryBean', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('schedulerFactoryBean', 'DESKTOP-MGDC3091585110348605', 1585113563935, 20000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('schedulerFactoryBean', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', 'DEFAULT04317974a56d42e8956c33b4a8c33e6f', 'DEFAULT', NULL, 1577065910000, 1577065900000, 5, 'PAUSED', 'CRON', 1577065862000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS `sys_depart`;
CREATE TABLE `sys_depart`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `DEPT_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门编号',
  `NAME` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `SHORT_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门简称',
  `PARENT_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级部门',
  `TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门类型',
  `ORDER_NO` int(11) NULL DEFAULT NULL COMMENT '排序',
  `MEMO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_parent_id_id_dept_123`(`PARENT_ID`) USING BTREE,
  CONSTRAINT `sys_depart_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_depart` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO `sys_depart` VALUES ('5e5ae11bae54d1ce1e8512f138cb9c75', 5, 'system', '2019-03-07 15:53:42', NULL, NULL, '00211', '开发1组', '开发1组', 'bccea46c2e2ff20592ca91a245313d41', '3', 1, '4');
INSERT INTO `sys_depart` VALUES ('6575819c40dccab6e91ef10306e24c9f', 0, 'system', '2019-03-08 10:21:35', NULL, NULL, '00067', '市场部', '市场部', '88f8b2b2c14220d7c923e037b6bbbd53', '2', 7, '');
INSERT INTO `sys_depart` VALUES ('77124de3c7cff7680d08a80621cfe924', 3, 'system', '2019-03-07 15:51:37', 'admin', '2019-12-27 13:29:15', '003', '上海华谷', '上海华谷', 'decde2a1436ea808ef7634e404a32ba0', '1', 2, '');
INSERT INTO `sys_depart` VALUES ('829927e2cc55f3ab61bca76cbc93528e', 9, 'admin', '2019-12-11 13:19:21', 'admin', '2019-12-11 13:21:05', '9888', 'gg', '测试部', '88f8b2b2c14220d7c923e037b6bbbd53', '2', 1, '1');
INSERT INTO `sys_depart` VALUES ('88f8b2b2c14220d7c923e037b6bbbd53', 0, 'system', '2019-03-07 15:08:09', NULL, NULL, '002', '江苏众禾', '江苏众禾', 'decde2a1436ea808ef7634e404a32ba0', '1', 1, '');
INSERT INTO `sys_depart` VALUES ('acf479c72e6308c64a4fcfe202b895e1', 4, 'system', '2019-03-08 17:50:15', 'admin', '2019-12-02 14:47:37', '000987', '商务部', '商务部', '77124de3c7cff7680d08a80621cfe924', '2', 1, '1');
INSERT INTO `sys_depart` VALUES ('b311af8d9e65ce2b5a5f8825626ee68f', 0, 'system', '2019-03-08 10:22:57', NULL, NULL, '00098', '客服部', '客服部', '88f8b2b2c14220d7c923e037b6bbbd53', '2', 9, '');
INSERT INTO `sys_depart` VALUES ('b65ce375c39071b8ed7c88c82569b2e3', 0, 'system', '2019-03-07 15:53:18', NULL, NULL, '0022', '财务部', '财务部', '88f8b2b2c14220d7c923e037b6bbbd53', '2', 2, '');
INSERT INTO `sys_depart` VALUES ('bccea46c2e2ff20592ca91a245313d41', 0, 'system', '2019-03-07 15:53:00', NULL, NULL, '0021', '开发部', '开发部', '88f8b2b2c14220d7c923e037b6bbbd53', '2', 1, '');
INSERT INTO `sys_depart` VALUES ('decde2a1436ea808ef7634e404a32ba0', 0, 'system', '2019-03-07 15:07:40', NULL, NULL, '001', '众禾集团', '众禾集团', NULL, '0', 1, '');

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `CODE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '代码',
  `MEMO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('1a5772765c61f9f392bec2ea06a64abf', 0, 'system', '2019-03-10 17:35:46', NULL, NULL, '部门类型', 'dept_type', '');
INSERT INTO `sys_dic` VALUES ('1cb39245c539b98ae7c6462c9cc0187f', 0, 'admin', '2019-12-12 12:22:05', NULL, NULL, '定时任务日志状态', 'func_timer_job_log_status', '');
INSERT INTO `sys_dic` VALUES ('27cbd24dcd0cceccd6653c961615e17c', 1, 'admin', '2019-04-30 14:05:15', 'admin', '2019-04-30 14:05:49', '流程权限类型', 'wf_per_type', '');
INSERT INTO `sys_dic` VALUES ('31e363292efa3c6aab49181fa7b07827', 1, 'admin', '2019-12-11 14:24:44', 'admin', '2019-12-11 23:59:11', '定时任务执行策略', 'func_timer_job_misfire_policy', '');
INSERT INTO `sys_dic` VALUES ('35a581255721a7e78ca81043bbf294aa', 0, 'system', '2019-03-12 15:25:52', NULL, NULL, '普通性别', 'common_sex', '');
INSERT INTO `sys_dic` VALUES ('628ef3a04d85f2786abca81a5ca33bcd', 0, 'admin', '2019-12-18 16:30:41', NULL, NULL, '系统消息类型', 'func_sys_message_type', '');
INSERT INTO `sys_dic` VALUES ('74cfcbad42d6bfed0c2a55616c58f438', 0, 'admin', '2019-04-30 14:03:37', NULL, NULL, '流程状态', 'workflow_status', '');
INSERT INTO `sys_dic` VALUES ('94023ef33adb568a46bb11f30ed917f7', 1, 'admin', '2019-12-11 14:28:50', 'admin', '2019-12-12 00:03:57', '定时任务并发执行', 'func_timer_job_concurrent_status', '');
INSERT INTO `sys_dic` VALUES ('978fae0e13cd51cfebf72dd3c7a868c8', 0, 'system', '2019-03-11 16:06:12', NULL, NULL, '菜单类型', 'menu_type', '');
INSERT INTO `sys_dic` VALUES ('c0c36e0a8c0010d14e422cfa984a4a24', 0, 'admin', '2019-12-11 09:44:34', NULL, NULL, '定时任务分组', 'func_timer_job_group', '');
INSERT INTO `sys_dic` VALUES ('c7ff1f3813c44ca8bab6bb4bb86dc4ec', 0, 'admin', '2019-12-11 14:32:47', NULL, NULL, '定时任务状态', 'func_timer_job_status', '');
INSERT INTO `sys_dic` VALUES ('d0129fe65794c0e5a723da5e237afbc0', 0, 'admin', '2019-04-08 17:16:15', NULL, NULL, '日志类型', 'log_type', '');
INSERT INTO `sys_dic` VALUES ('d8a11bd7801b5fc92508fa04fa8f4101', 0, 'admin', '2019-04-29 15:17:21', NULL, NULL, '表单类型', 'form_type', '');
INSERT INTO `sys_dic` VALUES ('e356781b1559ff32bbb8d861f39531c8', 0, 'admin', '2019-12-18 16:32:44', NULL, NULL, '系统消息状态', 'func_sys_message_status', '');
INSERT INTO `sys_dic` VALUES ('e529af628e2588b706224962dc964a68', 1, 'admin', '2019-12-11 09:39:48', 'admin', '2019-12-11 14:50:06', '定时任务执行状态', 'func_timer_job_execute_status', '');
INSERT INTO `sys_dic` VALUES ('e9653b38876bc1610ba0f6503c243821', 0, 'admin', '2019-05-05 11:06:43', NULL, NULL, '是否全部用户', 'user_isall', '');

-- ----------------------------
-- Table structure for sys_dic_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_value`;
CREATE TABLE `sys_dic_value`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `LABEL` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示值',
  `VALUE` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '值',
  `PARENT_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_dictionary_iiddw`(`PARENT_ID`) USING BTREE,
  CONSTRAINT `sys_dic_value_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_dic` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dic_value
-- ----------------------------
INSERT INTO `sys_dic_value` VALUES ('02335323f2a7098e23e493c225e70548', 0, 'admin', '2019-04-30 14:05:30', NULL, NULL, '所有人', '0', '27cbd24dcd0cceccd6653c961615e17c');
INSERT INTO `sys_dic_value` VALUES ('059352da3f177ef9fe5e0c01ddfde1cd', 2, 'admin', '2019-04-08 17:17:45', 'admin', '2019-04-09 10:03:37', '正常', '8', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('0e1e6c7de0aa987258735b701e7a9a2e', 1, 'admin', '2019-04-08 17:17:30', 'admin', '2019-04-09 10:03:23', '删除', '5', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('13e7c99f305c853692ecece15114fad3', 0, 'admin', '2019-12-11 14:26:08', NULL, NULL, '执行一次', '2', '31e363292efa3c6aab49181fa7b07827');
INSERT INTO `sys_dic_value` VALUES ('14010cdf51ab359a82511631ccdcb188', 1, 'admin', '2019-04-08 17:17:38', 'admin', '2019-04-09 10:03:30', '更新', '6', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('198806d58e9ee11e43b3ffe027e6db00', 0, 'admin', '2019-12-11 09:44:04', NULL, NULL, '异常', '4', 'e529af628e2588b706224962dc964a68');
INSERT INTO `sys_dic_value` VALUES ('1fda9bcb1873451e5137108d65420d21', 0, 'admin', '2019-04-30 14:04:02', NULL, NULL, '正常', '0', '74cfcbad42d6bfed0c2a55616c58f438');
INSERT INTO `sys_dic_value` VALUES ('20ef4f9427760880e4fc3c657c1b1766', 0, 'admin', '2019-04-09 10:03:04', NULL, NULL, '保存', '3', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('219100eec20d671759ac4f42602772d0', 0, 'system', '2019-03-11 10:50:45', NULL, NULL, '集团', '0', '1a5772765c61f9f392bec2ea06a64abf');
INSERT INTO `sys_dic_value` VALUES ('2474463c867b752d43b07ef97902c17c', 0, 'admin', '2019-12-12 12:22:26', NULL, NULL, '失败', '1', '1cb39245c539b98ae7c6462c9cc0187f');
INSERT INTO `sys_dic_value` VALUES ('2476375b26028b94edb5690c194fdf4c', 0, 'admin', '2019-12-11 14:25:56', NULL, NULL, '立即执行', '1', '31e363292efa3c6aab49181fa7b07827');
INSERT INTO `sys_dic_value` VALUES ('2ea92e68d251e855682fe9e379d91b28', 12, 'system', '2019-03-11 11:01:15', NULL, NULL, '公司', '1', '1a5772765c61f9f392bec2ea06a64abf');
INSERT INTO `sys_dic_value` VALUES ('31b327f4fd151b028e10f3a0e35b0657', 0, 'admin', '2019-12-11 09:43:33', NULL, NULL, '正在执行', '1', 'e529af628e2588b706224962dc964a68');
INSERT INTO `sys_dic_value` VALUES ('3c8975f209294ea49b917fc9024e4b3f', 0, 'admin', '2019-12-11 14:29:33', NULL, NULL, '允许', '1', '94023ef33adb568a46bb11f30ed917f7');
INSERT INTO `sys_dic_value` VALUES ('52d62197d1da22a915b1a0a02db8d27a', 0, 'admin', '2019-12-11 09:43:44', NULL, NULL, '完成', '2', 'e529af628e2588b706224962dc964a68');
INSERT INTO `sys_dic_value` VALUES ('57beaf816d3dfbc8b4096c22510bd7c2', 0, 'admin', '2019-12-11 09:45:23', NULL, NULL, '默认', 'DEFAULT', 'c0c36e0a8c0010d14e422cfa984a4a24');
INSERT INTO `sys_dic_value` VALUES ('5dffda58dd871fa30ba4d6c71372db40', 0, 'admin', '2019-04-30 14:04:12', NULL, NULL, '停用', '1', '74cfcbad42d6bfed0c2a55616c58f438');
INSERT INTO `sys_dic_value` VALUES ('5e8f7671643b280bda78e73bf97fe7c7', 0, 'admin', '2019-12-11 09:45:42', NULL, NULL, '系统', 'SYSTEM', 'c0c36e0a8c0010d14e422cfa984a4a24');
INSERT INTO `sys_dic_value` VALUES ('6131271c913c60a7699a47a89067f681', 0, 'admin', '2019-12-18 16:31:37', NULL, NULL, '其他', '1', '628ef3a04d85f2786abca81a5ca33bcd');
INSERT INTO `sys_dic_value` VALUES ('624d25148b3fb90276bbd678f1fca8f3', 0, 'admin', '2019-04-29 15:17:47', NULL, NULL, '系统表单', '0', 'd8a11bd7801b5fc92508fa04fa8f4101');
INSERT INTO `sys_dic_value` VALUES ('646135b8c26dc5b88561f6355592b16c', 0, 'admin', '2019-12-11 14:35:26', NULL, NULL, '暂停', '3', 'c7ff1f3813c44ca8bab6bb4bb86dc4ec');
INSERT INTO `sys_dic_value` VALUES ('6bd6c55f725788fecb301b9e9e329068', 0, 'admin', '2019-04-29 15:17:56', NULL, NULL, '自定义表单', '1', 'd8a11bd7801b5fc92508fa04fa8f4101');
INSERT INTO `sys_dic_value` VALUES ('6e48ee8c2ed55c6b89eb70db5b97782a', 2, 'admin', '2019-04-08 17:17:53', 'admin', '2019-04-09 10:03:45', '异常', '9', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('742830dd88d79578b23e34c418f7e13d', 0, 'admin', '2019-04-30 14:06:09', NULL, NULL, '指定人', '1', '27cbd24dcd0cceccd6653c961615e17c');
INSERT INTO `sys_dic_value` VALUES ('7da6b3a834ee8befe068c692ec64ca6c', 0, 'admin', '2019-12-18 16:32:59', NULL, NULL, '草稿', '0', 'e356781b1559ff32bbb8d861f39531c8');
INSERT INTO `sys_dic_value` VALUES ('7e2737ce4f20845ca469484656aba7d8', 0, 'admin', '2019-12-11 14:29:29', NULL, NULL, '禁止', '0', '94023ef33adb568a46bb11f30ed917f7');
INSERT INTO `sys_dic_value` VALUES ('83a7717c1ebda30a0ff630d72c532b4b', 0, 'admin', '2019-04-30 14:04:21', NULL, NULL, '过期', '2', '74cfcbad42d6bfed0c2a55616c58f438');
INSERT INTO `sys_dic_value` VALUES ('84e6fce2d1392b360320a56cfac6ef9a', 0, 'admin', '2019-12-11 14:35:21', NULL, NULL, '启用', '0', 'c7ff1f3813c44ca8bab6bb4bb86dc4ec');
INSERT INTO `sys_dic_value` VALUES ('854dff9cffbe5a10fca7877bf6b00c6a', 1, 'admin', '2019-04-08 17:17:19', 'admin', '2019-04-09 10:03:13', '新增', '4', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('8774016ebdb85e70f04343b464a6aeef', 0, 'admin', '2019-12-11 14:26:22', NULL, NULL, '放弃执行', '3', '31e363292efa3c6aab49181fa7b07827');
INSERT INTO `sys_dic_value` VALUES ('8b71110a29603364d37e0f42b1069f6d', 0, 'admin', '2019-12-11 09:43:53', NULL, NULL, '暂停', '3', 'e529af628e2588b706224962dc964a68');
INSERT INTO `sys_dic_value` VALUES ('8f648461c8ef5f63c006275779f7e70d', 0, 'system', '2019-03-11 11:46:12', NULL, NULL, '部门', '2', '1a5772765c61f9f392bec2ea06a64abf');
INSERT INTO `sys_dic_value` VALUES ('9400d8c15d1ae420c32e637783af0273', 0, 'system', '2019-03-12 15:26:06', NULL, NULL, '女', '1', '35a581255721a7e78ca81043bbf294aa');
INSERT INTO `sys_dic_value` VALUES ('956b43dd145c39b1e136e2c4bc993161', 0, 'admin', '2019-04-09 17:16:06', NULL, NULL, '定时任务', '7', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('9f851168584a5309961d52b577b9e67b', 0, 'system', '2019-03-11 11:46:48', NULL, NULL, '小组', '3', '1a5772765c61f9f392bec2ea06a64abf');
INSERT INTO `sys_dic_value` VALUES ('ab5c50b32a108803f8523a34513b6723', 0, 'admin', '2019-12-18 16:31:31', NULL, NULL, '系统消息', '0', '628ef3a04d85f2786abca81a5ca33bcd');
INSERT INTO `sys_dic_value` VALUES ('b9719d534eaa66c5f192061e77bf5d9f', 0, 'admin', '2019-12-11 09:41:41', NULL, NULL, '就绪', '0', 'e529af628e2588b706224962dc964a68');
INSERT INTO `sys_dic_value` VALUES ('bb228cfce228e724e00af22b584fda9a', 0, 'system', '2019-03-11 16:06:38', NULL, NULL, '按钮', '1', '978fae0e13cd51cfebf72dd3c7a868c8');
INSERT INTO `sys_dic_value` VALUES ('bb829e28d001c6266e6964df87f82c40', 0, 'admin', '2019-04-08 17:17:02', NULL, NULL, '登录', '1', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('bc571709588245c1b0d5e4e1898c277e', 0, 'system', '2019-03-12 15:26:01', NULL, NULL, '男', '0', '35a581255721a7e78ca81043bbf294aa');
INSERT INTO `sys_dic_value` VALUES ('d912369343f15295e29d59f19dc1938a', 1, 'admin', '2019-04-08 17:17:11', 'admin', '2019-04-09 12:06:05', '登出', '2', 'd0129fe65794c0e5a723da5e237afbc0');
INSERT INTO `sys_dic_value` VALUES ('db1a5c18d5c03eefca83f4909a6d95ca', 0, 'admin', '2019-05-05 11:07:11', NULL, NULL, '全部用户', '0', 'e9653b38876bc1610ba0f6503c243821');
INSERT INTO `sys_dic_value` VALUES ('de2bfb39d66e37657ef770c39c881cc6', 0, 'admin', '2019-12-18 16:33:07', NULL, NULL, '已发布', '1', 'e356781b1559ff32bbb8d861f39531c8');
INSERT INTO `sys_dic_value` VALUES ('e0dd5c24da2ff7e0d98c84ec31250d54', 0, 'admin', '2019-12-12 12:22:20', NULL, NULL, '成功', '0', '1cb39245c539b98ae7c6462c9cc0187f');
INSERT INTO `sys_dic_value` VALUES ('f681b0e6a8fed1d73884dbb348a39446', 2, 'system', '2019-03-11 16:06:31', NULL, NULL, '菜单', '0', '978fae0e13cd51cfebf72dd3c7a868c8');
INSERT INTO `sys_dic_value` VALUES ('fa78d2e912be8d2f74d1e92bb525c13f', 0, 'admin', '2019-05-05 11:07:27', NULL, NULL, '指定用户', '1', 'e9653b38876bc1610ba0f6503c243821');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志类型',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `USER_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `CLASS_NAME` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类名',
  `METHOD_NAME` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `PARAMS` varchar(2500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `IP` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `BROSWER` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `OPERATOR_TIME` datetime(0) NOT NULL COMMENT '操作时间',
  `MEMO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('12077b562dfd4eae91c797ddc5ba8653', 0, 'system', '2020-03-18 10:57:44', NULL, NULL, '1', '用户登录', 'system', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & 50fv & ', '本地', 'CHROME8', '2020-03-18 10:57:44', '用户成功登录');
INSERT INTO `sys_log` VALUES ('370369eafa58579b1de7c7910e639279', 0, 'system', '2020-03-13 14:00:21', NULL, NULL, '8', '服务器监控', 'admin', 'com.zhonghe.active4j.monitor.controller.ServerMonitorController', 'index', '{server=com.zhonghe.active4j.monitor.model.ServerInfoModel@c216b} & ', '本地', 'CHROME', '2020-03-13 14:00:21', '服务器监控');
INSERT INTO `sys_log` VALUES ('3a04fad0cf7b77603d9deeda682642b1', 0, 'system', '2020-03-25 12:08:04', NULL, NULL, '3', '保存部门信息', 'admin', 'com.zhonghe.active4j.system.controller.SysDeptController', 'save', 'com.zhonghe.active4j.system.entity.SysDepartEntity@40a13322 & ', '本地', 'CHROME8', '2020-03-25 12:08:04', '新增或编辑保存了部门信息');
INSERT INTO `sys_log` VALUES ('422b978f85c349306882a4e5b615654f', 0, 'system', '2020-03-25 12:07:41', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & lnwr & ', '本地', 'CHROME8', '2020-03-25 12:07:41', '用户成功登录');
INSERT INTO `sys_log` VALUES ('439cd1c67838678699c6255a74e77656', 0, 'system', '2020-03-15 13:41:40', NULL, NULL, '3', '保存用户信息', 'admin', 'com.zhonghe.active4j.system.controller.SysUserController', 'save', 'com.zhonghe.active4j.system.entity.SysUserEntity@6cbfef76 & a088b47539612eebeace406a8f458910 & ', '本地', 'CHROME8', '2020-03-15 13:41:40', '新增或编辑保存了用户信息');
INSERT INTO `sys_log` VALUES ('4c7a74a2a4349dbff95bb02a4cc6293a', 0, 'system', '2020-03-13 13:27:26', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & 3tav & ', '本地', 'CHROME', '2020-03-13 13:27:26', '用户成功登录');
INSERT INTO `sys_log` VALUES ('4f06e4f9e77681cb899fbba05d37141e', 0, 'system', '2020-03-13 13:47:54', NULL, NULL, '8', '进入控制台', 'admin', 'com.zhonghe.active4j.demo.main.controller.ProjectController', 'homepage2', '{} & ', '本地', 'CHROME', '2020-03-13 13:47:54', '进入控制台');
INSERT INTO `sys_log` VALUES ('54d83ceaeaaaeeded6e64d9bf51bfcfc', 0, 'system', '2020-03-15 13:41:15', NULL, NULL, '1', '用户登录', 'system', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & ciqn & ', '本地', 'CHROME8', '2020-03-15 13:41:15', '用户成功登录');
INSERT INTO `sys_log` VALUES ('6b1b99e5b262c0912d165fe3b6d36372', 0, 'system', '2020-03-25 12:27:16', NULL, NULL, '3', '保存角色信息', 'admin', 'com.zhonghe.active4j.system.controller.SysRoleController', 'save', 'com.zhonghe.active4j.system.entity.SysRoleEntity@2b2e0619 & ', '本地', 'CHROME8', '2020-03-25 12:27:16', '新增或编辑保存了角色信息');
INSERT INTO `sys_log` VALUES ('6ca28a81caf7fb014b49a59d9d0e0332', 0, 'system', '2020-03-25 12:26:16', NULL, NULL, '3', '保存部门信息', 'admin', 'com.zhonghe.active4j.system.controller.SysDeptController', 'save', 'com.zhonghe.active4j.system.entity.SysDepartEntity@139989cf & ', '本地', 'CHROME8', '2020-03-25 12:26:16', '新增或编辑保存了部门信息');
INSERT INTO `sys_log` VALUES ('87b6a3c921b7b0e361632848571b0651', 0, 'system', '2020-03-13 14:04:46', NULL, NULL, '2', '用户登出', 'system', 'com.zhonghe.active4j.system.controller.LoginController', 'logout', '', '本地', 'CHROME', '2020-03-13 14:04:46', '用户已登出');
INSERT INTO `sys_log` VALUES ('9d88ec84cfa1dc14b0c3abcbd9da1d1c', 0, 'system', '2020-03-15 12:30:49', NULL, NULL, '1', '用户登录', 'system', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & sdjf & ', '本地', 'CHROME8', '2020-03-15 12:30:49', '用户成功登录');
INSERT INTO `sys_log` VALUES ('9e26c2e8abbe739720e9a451eab167be', 0, 'system', '2020-03-25 12:10:15', NULL, NULL, '3', '保存部门信息', 'admin', 'com.zhonghe.active4j.system.controller.SysDeptController', 'save', 'com.zhonghe.active4j.system.entity.SysDepartEntity@3ee1757 & ', '本地', 'CHROME8', '2020-03-25 12:10:15', '新增或编辑保存了部门信息');
INSERT INTO `sys_log` VALUES ('a048dd58263487aaedf4495a93d36eba', 0, 'system', '2020-03-17 15:00:08', NULL, NULL, '1', '用户登录', 'system', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & mnki & ', '本地', 'CHROME8', '2020-03-17 15:00:08', '用户成功登录');
INSERT INTO `sys_log` VALUES ('a2ed08355aceb94f1dbc2ff9fb9e22e3', 0, 'system', '2020-03-18 10:57:50', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & pu8x & ', '本地', 'CHROME8', '2020-03-18 10:57:50', '用户成功登录');
INSERT INTO `sys_log` VALUES ('a308309020b4799f8fd955d542ca96ec', 0, 'system', '2020-03-25 12:27:26', NULL, NULL, '3', '保存角色信息', 'admin', 'com.zhonghe.active4j.system.controller.SysRoleController', 'save', 'com.zhonghe.active4j.system.entity.SysRoleEntity@3cb08050 & ', '本地', 'CHROME8', '2020-03-25 12:27:26', '新增或编辑保存了角色信息');
INSERT INTO `sys_log` VALUES ('c5f1010b71878ab129109ad0372247f4', 0, 'system', '2020-03-15 12:30:53', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & k47w & ', '本地', 'CHROME8', '2020-03-15 12:30:53', '用户成功登录');
INSERT INTO `sys_log` VALUES ('c8567b5b96b10feaaa53c81731d6d96a', 0, 'system', '2020-03-25 12:11:08', NULL, NULL, '3', '保存角色信息', 'admin', 'com.zhonghe.active4j.system.controller.SysRoleController', 'save', 'com.zhonghe.active4j.system.entity.SysRoleEntity@50a68296 & ', '本地', 'CHROME8', '2020-03-25 12:11:08', '新增或编辑保存了角色信息');
INSERT INTO `sys_log` VALUES ('cde6fc84e52012acfa6c9b8d37031849', 0, 'system', '2020-03-13 13:58:32', NULL, NULL, '8', '进入控制台', 'admin', 'com.zhonghe.active4j.demo.main.controller.ProjectController', 'homepage1', '{} & ', '本地', 'CHROME', '2020-03-13 13:58:32', '进入控制台');
INSERT INTO `sys_log` VALUES ('d4031a698862233ca6244cd4645a9f16', 0, 'system', '2020-03-25 12:26:32', NULL, NULL, '3', '保存部门信息', 'admin', 'com.zhonghe.active4j.system.controller.SysDeptController', 'save', 'com.zhonghe.active4j.system.entity.SysDepartEntity@334fb170 & ', '本地', 'CHROME8', '2020-03-25 12:26:32', '新增或编辑保存了部门信息');
INSERT INTO `sys_log` VALUES ('d4385021a3df567df23af551826fda69', 0, 'system', '2020-03-13 14:04:50', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & irza & ', '本地', 'CHROME', '2020-03-13 14:04:50', '用户成功登录');
INSERT INTO `sys_log` VALUES ('df2b2a66fd8543ac4e4ccef1490471b1', 0, 'system', '2020-03-15 13:41:21', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & lbwm & ', '本地', 'CHROME8', '2020-03-15 13:41:21', '用户成功登录');
INSERT INTO `sys_log` VALUES ('e0400b595f67e6a335cbc58c2a68ab2f', 0, 'system', '2020-03-17 15:00:14', NULL, NULL, '1', '用户登录', 'admin', 'com.zhonghe.active4j.system.controller.LoginController', 'loginAction', 'admin & 123456 & meky & ', '本地', 'CHROME8', '2020-03-17 15:00:14', '用户成功登录');
INSERT INTO `sys_log` VALUES ('fbca3408c8fc0daed1e3b723f0c37dd3', 0, 'system', '2020-03-13 14:01:00', NULL, NULL, '8', '现在用户列表', 'admin', 'com.zhonghe.active4j.monitor.controller.OnlineSessionController', 'index', '{} & ', '本地', 'CHROME', '2020-03-13 14:01:00', '现在用户列表');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `PARENT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父角色ID',
  `TYPE` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
  `URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `ICON` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `ORDER_NO` int(11) NULL DEFAULT NULL COMMENT '排序',
  `MEMO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_parent_id_id_123456`(`PARENT_ID`) USING BTREE,
  CONSTRAINT `sys_menu_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_menu` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0537fa19001c1758fe1d7e8ae93cb61a', 0, 'admin', '2019-12-16 11:03:49', NULL, NULL, '阿里云短信', '28d1d2a835dc364315cc7f9832d664a0', '0', 'func/sms/alisms', '', 3, '');
INSERT INTO `sys_menu` VALUES ('06428eaef123e23852946540710eafe7', 0, 'admin', '2019-12-10 09:56:58', NULL, NULL, '风格定制', '524ab0ea6d1bd06ea8f7430acf258b41', '0', 'comp/layer/theme', '', 3, '');
INSERT INTO `sys_menu` VALUES ('0d079901d30c6bdbcae1349de1aabd30', 0, 'admin', '2019-12-10 10:04:12', NULL, NULL, '功能示例一', 'e04db2a1c2d8d911b6961f0c29ffe02a', '0', 'comp/laydate/demo1', '', 1, '');
INSERT INTO `sys_menu` VALUES ('0f5a97c074f42292c477c1ecb8e58769', 0, 'admin', '2019-12-20 14:18:13', NULL, NULL, '保存字典', '3c02556f5054c9fdf6faf6574004e792', '1', 'sys:dic:save', '', 3, '');
INSERT INTO `sys_menu` VALUES ('10fd29fb35ea748ae75dcd84895997c0', 1, 'admin', '2019-12-17 10:54:27', 'admin', '2019-12-17 10:54:47', '导入导出', '91be39ac09cdc71c7435ffb94f611795', '0', 'func/export/list', '', 7, '');
INSERT INTO `sys_menu` VALUES ('1498b9a569c6e2e6562e79a834cc93f4', 2, 'admin', '2019-12-16 09:59:48', 'admin', '2019-12-16 15:12:04', '上传下载', '91be39ac09cdc71c7435ffb94f611795', '0', 'func/upload/upload', '', 6, '');
INSERT INTO `sys_menu` VALUES ('19587742caad08e1c5eaa9483b02f49b', 1, 'admin', '2019-12-20 14:06:09', 'admin', '2019-12-20 14:06:20', '保存', '755956ba2ea812d8749a062f34b7da2d', '1', 'sys:user:save', '', 2, '');
INSERT INTO `sys_menu` VALUES ('1b52228fe7c8b9cf259f9f7c9bfcd844', 0, 'admin', '2019-12-10 10:25:12', NULL, NULL, '赋值已知数据', '254d304678038641f87aff42be68ef74', '0', 'comp/table/data', '', 3, '');
INSERT INTO `sys_menu` VALUES ('1fc380fb7a2b3992e5f0dce39a683ce3', 1, 'admin', '2019-12-10 16:49:08', 'admin', '2019-12-10 16:50:30', '腾讯云短信', '28d1d2a835dc364315cc7f9832d664a0', '0', 'func/sms/qcloudsms', '', 2, '');
INSERT INTO `sys_menu` VALUES ('207ae629bfbf53290f3c64f1933d5ae6', 0, 'admin', '2019-12-11 12:29:41', NULL, NULL, '示例主页二', '36df3e985a7c01432cc579728425bf09', '0', 'demo/main/homepage2', '', 3, '');
INSERT INTO `sys_menu` VALUES ('213830032d2b72edf575f30d71361a51', 1, 'admin', '2019-12-11 10:33:03', 'admin', '2019-12-11 10:34:56', '定时任务', '91be39ac09cdc71c7435ffb94f611795', '0', 'func/timer/job/list', '', 4, '');
INSERT INTO `sys_menu` VALUES ('22691e0c807693f3ad52dcb043a2e2f7', 0, 'admin', '2019-12-10 12:26:46', NULL, NULL, '流加载', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/flow/index', '', 22, '');
INSERT INTO `sys_menu` VALUES ('24ca613859f753247f86b55a13e4f7d1', 0, 'admin', '2019-12-10 11:05:04', NULL, NULL, '固定列', '254d304678038641f87aff42be68ef74', '0', 'comp/table/fixed', '', 15, '');
INSERT INTO `sys_menu` VALUES ('254d304678038641f87aff42be68ef74', 0, 'admin', '2019-12-10 10:22:12', NULL, NULL, '数据表格', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 15, '');
INSERT INTO `sys_menu` VALUES ('2774f4f1c30a34a75dfcfe5f8945d370', 1, 'admin', '2019-12-09 22:16:25', 'admin', '2019-12-09 22:16:45', '示例组件', NULL, '0', '', '<i class=\'layui-icon layui-icon-layer\'></i>', 5, '');
INSERT INTO `sys_menu` VALUES ('28d1d2a835dc364315cc7f9832d664a0', 0, 'admin', '2019-12-10 14:31:19', NULL, NULL, '短信服务', '91be39ac09cdc71c7435ffb94f611795', '0', '', '', 3, '');
INSERT INTO `sys_menu` VALUES ('2f9b5ee346d667a5c146b3a25009bc8c', 2, 'admin', '2019-04-01 10:57:42', 'admin', '2019-04-01 10:58:05', '日志管理', '623262eac50915905031d8d67d8702ea', '0', 'sys/log/list', '', 5, '');
INSERT INTO `sys_menu` VALUES ('324c09d31cc6910fa5ae805a737d32a8', 0, 'admin', '2019-12-10 11:05:45', NULL, NULL, '数据操作', '254d304678038641f87aff42be68ef74', '0', 'comp/table/operate', '', 16, '');
INSERT INTO `sys_menu` VALUES ('326147b3715dd8698f4d84058404e83d', 0, 'admin', '2019-12-10 10:27:50', NULL, NULL, '自定义分页', '254d304678038641f87aff42be68ef74', '0', 'comp/table/resetPage', '', 6, '');
INSERT INTO `sys_menu` VALUES ('33ad4150fead0f227d7c6adb3d4fde2f', 0, 'admin', '2019-12-11 12:30:10', NULL, NULL, '示例主页三', '36df3e985a7c01432cc579728425bf09', '0', 'demo/main/homepage3', '', 4, '');
INSERT INTO `sys_menu` VALUES ('36df3e985a7c01432cc579728425bf09', 2, 'admin', '2019-12-03 10:59:35', 'admin', '2019-12-03 11:00:07', '项目介绍', NULL, '0', '', '<i class=\'layui-icon layui-icon-home\'></i>', 0, '');
INSERT INTO `sys_menu` VALUES ('38aba0fbe14e8c0f596ff40fb29532aa', 0, 'admin', '2019-12-10 12:21:52', NULL, NULL, '颜色选择器', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/colorpicker/index', '', 18, '');
INSERT INTO `sys_menu` VALUES ('391f39b83b80cc3bd68e115d935f8109', 0, 'admin', '2019-12-10 11:43:35', NULL, NULL, '功能演示一', 'f9679bc12890ed472d0fec02dd3fad64', '0', 'comp/upload/demo1', '', 1, '');
INSERT INTO `sys_menu` VALUES ('3b362bf507f42d306368728df86877e0', 0, 'admin', '2019-12-10 11:40:26', NULL, NULL, '功能演示一', '76cf30bf5db55a9204e38dec11079e2a', '0', 'comp/laypage/demo1', '', 1, '');
INSERT INTO `sys_menu` VALUES ('3c02556f5054c9fdf6faf6574004e792', 3, 'system', '2019-03-11 16:02:27', 'admin', '2019-11-28 16:53:07', '数据字典管理', '623262eac50915905031d8d67d8702ea', '0', 'sys/dic/list', '', 6, '');
INSERT INTO `sys_menu` VALUES ('3c83e94d8c9499d3962e405a2dddeebb', 0, 'admin', '2019-12-10 09:46:17', NULL, NULL, '时间线', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/timeline/index', '', 9, '');
INSERT INTO `sys_menu` VALUES ('3d0d3eed4985181a82a6387a30974a9c', 0, 'admin', '2019-12-10 14:31:41', NULL, NULL, '云潮云短信', '28d1d2a835dc364315cc7f9832d664a0', '0', 'func/sms/yunchaoyun', '', 3, '');
INSERT INTO `sys_menu` VALUES ('3e5df1746ba7a3be358e41cedff4c825', 0, 'admin', '2019-12-20 14:19:52', NULL, NULL, '删除', '3c02556f5054c9fdf6faf6574004e792', '1', 'sys:dic:delete', '', 5, '');
INSERT INTO `sys_menu` VALUES ('3ea0bdfc67e946ddc62dd82d9858b540', 0, 'admin', '2019-12-10 09:31:38', NULL, NULL, '进度条', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/progress/index', '', 6, '');
INSERT INTO `sys_menu` VALUES ('40cad6aa986f987303647287352f7788', 0, 'admin', '2019-12-09 22:36:42', NULL, NULL, '全端复杂组合', '87c8451f951888e483fe7ec7668507fe', '0', 'comp/grid/all', '', 4, '');
INSERT INTO `sys_menu` VALUES ('427d532a84547250e510a3dcc388c92f', 0, 'admin', '2019-12-20 14:12:10', NULL, NULL, '删除', '6ee28a2031c8d1e701613082ea6ebc5e', '1', 'sys:dept:delete', '', 3, '');
INSERT INTO `sys_menu` VALUES ('4388d13e00a1a94300c5fa7ff32d9d90', 2, 'admin', '2019-12-13 13:39:56', 'admin', '2019-12-13 14:13:15', 'tinymce 编辑器', 'dd22d35e3d8fc63fa51ff4e01668fab6', '0', 'func/layedit/tinymce', '', 3, '');
INSERT INTO `sys_menu` VALUES ('4553ac5aa6eb328b8f5e68506c528715', 0, 'admin', '2019-12-10 10:29:15', NULL, NULL, '开启头部工具栏', '254d304678038641f87aff42be68ef74', '0', 'comp/table/toolbar', '', 7, '');
INSERT INTO `sys_menu` VALUES ('4618aa278da605c57895074aee4e095d', 0, 'admin', '2019-12-10 10:26:33', NULL, NULL, '转化静态表格', '254d304678038641f87aff42be68ef74', '0', 'comp/table/tostatic', '', 4, '');
INSERT INTO `sys_menu` VALUES ('4b2e9da372c5c7b5d4d39c0f9a4e402d', 0, 'admin', '2019-12-03 16:14:43', NULL, NULL, '服务器监控', '7476783545496331c211296efaa561a2', '0', 'monitor/server/index', '', 2, '');
INSERT INTO `sys_menu` VALUES ('4f8fa64d0ec7fce7c2cedc5ac6d09489', 1, 'admin', '2019-12-06 14:11:37', 'admin', '2019-12-09 16:31:48', '邮件发送', '91be39ac09cdc71c7435ffb94f611795', '0', 'sys/email/form', '', 1, '');
INSERT INTO `sys_menu` VALUES ('50677a25a6a1cc3e3d71834ef84c2c39', 0, 'admin', '2019-12-10 10:09:31', NULL, NULL, '静态表格', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/table/static', '', 14, '');
INSERT INTO `sys_menu` VALUES ('524ab0ea6d1bd06ea8f7430acf258b41', 0, 'admin', '2019-12-10 09:52:31', NULL, NULL, '通用弹层', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 12, '');
INSERT INTO `sys_menu` VALUES ('53a9a384c24cbc1351fc4443afabded7', 4, 'admin', '2019-12-09 17:25:37', 'admin', '2019-12-10 11:00:55', '微信支付', 'fabb29abae35721ad0d893ed896d2d10', '0', 'func/pay/wx/form', '', 1, '');
INSERT INTO `sys_menu` VALUES ('54c7509f7a0fca385c935e4919dc10e7', 0, 'admin', '2019-12-10 10:27:17', NULL, NULL, '开启分页', '254d304678038641f87aff42be68ef74', '0', 'comp/table/page', '', 5, '');
INSERT INTO `sys_menu` VALUES ('59f249d45a9478beae170b4a4ce03b61', 0, 'admin', '2019-12-10 10:22:53', NULL, NULL, '简单数据表格', '254d304678038641f87aff42be68ef74', '0', 'comp/table/simple', '', 1, '');
INSERT INTO `sys_menu` VALUES ('5a717dd0bdd94123900508d172088d48', 0, 'admin', '2019-12-10 11:04:27', NULL, NULL, '设置单元格样式', '254d304678038641f87aff42be68ef74', '0', 'comp/table/style', '', 14, '');
INSERT INTO `sys_menu` VALUES ('60fda72e475b1536cb166b0af7741697', 0, 'admin', '2019-12-19 16:00:16', NULL, NULL, '验证码', '91be39ac09cdc71c7435ffb94f611795', '0', 'func/captcha/captcha', '', 9, '');
INSERT INTO `sys_menu` VALUES ('612265d33877f681c26b2180f90a3e62', 0, 'admin', '2019-12-10 10:07:50', NULL, NULL, '特殊示例', 'e04db2a1c2d8d911b6961f0c29ffe02a', '0', 'comp/laydate/special-demo', '', 4, '');
INSERT INTO `sys_menu` VALUES ('623262eac50915905031d8d67d8702ea', 1, 'system', '2019-03-11 15:51:08', 'admin', '2019-04-09 10:18:40', '系统管理', NULL, '0', '', '<i class=\'layui-icon layui-icon-component\'></i>', 1, '');
INSERT INTO `sys_menu` VALUES ('66f6ab8a5f40facb94ab9ff93cd14aaf', 0, 'admin', '2019-12-10 12:28:10', NULL, NULL, '工具', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/util/index', '', 22, '');
INSERT INTO `sys_menu` VALUES ('67090df9bf7c076aee440df201436458', 0, 'admin', '2019-12-10 09:41:19', NULL, NULL, '徽章', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/badge/index', '', 8, '');
INSERT INTO `sys_menu` VALUES ('6aa4271aff8644eeb1561f57f8320446', 0, 'admin', '2019-12-20 14:12:52', NULL, NULL, '删除', '755956ba2ea812d8749a062f34b7da2d', '1', 'sys:user:delete', '', 3, '');
INSERT INTO `sys_menu` VALUES ('6abd38f3c7631bccffb49ade84dba77b', 0, 'admin', '2019-12-10 12:30:11', NULL, NULL, '代码修饰', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/code/index', '', 23, '');
INSERT INTO `sys_menu` VALUES ('6c6da7e850d5a779a1ca3f3763cbce0d', 0, 'admin', '2019-12-10 11:06:22', NULL, NULL, '解析任意数据格式', '254d304678038641f87aff42be68ef74', '0', 'comp/table/parseData', '', 17, '');
INSERT INTO `sys_menu` VALUES ('6ee28a2031c8d1e701613082ea6ebc5e', 4, 'system', '2019-03-11 16:01:24', 'admin', '2019-11-28 16:52:57', '部门管理', '623262eac50915905031d8d67d8702ea', '0', 'sys/dept/list', '', 2, '');
INSERT INTO `sys_menu` VALUES ('700bb6c4a8d5cd6b9d7016e4faae8628', 0, 'admin', '2019-12-03 11:02:11', NULL, NULL, '项目主页', '36df3e985a7c01432cc579728425bf09', '0', 'demo/main/index', '', 1, '');
INSERT INTO `sys_menu` VALUES ('73c52763764317a58bb2b7db884e8788', 0, 'admin', '2019-12-10 11:43:58', NULL, NULL, '功能演示二', 'f9679bc12890ed472d0fec02dd3fad64', '0', 'comp/upload/demo2', '', 2, '');
INSERT INTO `sys_menu` VALUES ('7476783545496331c211296efaa561a2', 1, 'admin', '2019-12-03 11:02:46', 'admin', '2019-12-03 11:03:18', '系统监控', NULL, '0', '', '<i class=\'layui-icon layui-icon-console\'></i>', 2, '');
INSERT INTO `sys_menu` VALUES ('7514b093df0e52d76814ce5a6fb5c868', 0, 'admin', '2019-12-10 12:24:56', NULL, NULL, '评分', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/rate/index', '', 20, '');
INSERT INTO `sys_menu` VALUES ('755956ba2ea812d8749a062f34b7da2d', 1, 'system', '2019-03-18 23:39:30', 'admin', '2019-11-28 16:52:50', '用户管理', '623262eac50915905031d8d67d8702ea', '0', 'sys/user/list', '', 1, '');
INSERT INTO `sys_menu` VALUES ('75e8d5210cf7c50af4db8f560c020974', 0, 'admin', '2019-12-20 14:09:09', NULL, NULL, '新增', '7de00ce887ea0c9c7144636112801a0c', '1', 'sys/role/add', '', 1, '');
INSERT INTO `sys_menu` VALUES ('76cf30bf5db55a9204e38dec11079e2a', 1, 'admin', '2019-12-10 11:36:46', 'admin', '2019-12-10 11:36:53', '分页', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 16, '');
INSERT INTO `sys_menu` VALUES ('77e7ae5ca528b246f72e4983374da469', 0, 'admin', '2019-12-10 09:26:21', NULL, NULL, '选项卡', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/tabs/index', '', 5, '');
INSERT INTO `sys_menu` VALUES ('7a5be98a72343beb5bd4f73e2793acb3', 0, 'admin', '2019-12-10 12:23:25', NULL, NULL, '滑块组件', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/slider/index', '', 19, '');
INSERT INTO `sys_menu` VALUES ('7bf0c837223ed4b2693eadc933d5de56', 0, 'admin', '2019-12-20 14:14:52', NULL, NULL, '删除', 'ecacd9efd6c237b80e46df8b1507a904', '1', 'sys:menu:delete', '', 3, '');
INSERT INTO `sys_menu` VALUES ('7c6037cdf7687a75d823b18b76c2d03f', 0, 'admin', '2019-12-10 11:43:13', NULL, NULL, '上传', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 12, '');
INSERT INTO `sys_menu` VALUES ('7de00ce887ea0c9c7144636112801a0c', 0, 'system', '2019-03-18 23:39:01', NULL, NULL, '角色管理', '623262eac50915905031d8d67d8702ea', '0', 'sys/role/list', '', 3, '');
INSERT INTO `sys_menu` VALUES ('7e106236ac4833f27ffa0250d3492071', 1, 'admin', '2019-12-11 12:28:55', 'admin', '2019-12-11 12:29:14', '示例主页一', '36df3e985a7c01432cc579728425bf09', '0', 'demo/main/homepage1', '', 2, '');
INSERT INTO `sys_menu` VALUES ('7e3a22591b016b3ce6ccc881e7b9d294', 0, 'admin', '2019-12-20 14:10:55', NULL, NULL, '保存权限', '7de00ce887ea0c9c7144636112801a0c', '1', 'sys:role:saverolemenu', '', 3, '');
INSERT INTO `sys_menu` VALUES ('83b3db7973133104ba24135b453939ce', 0, 'admin', '2019-12-20 14:17:26', NULL, NULL, '新增字典值', '3c02556f5054c9fdf6faf6574004e792', '1', 'sys/dic/addval', '', 2, '');
INSERT INTO `sys_menu` VALUES ('84cc6a4dbda00fc1d3a24d55c9cd14e6', 1, 'admin', '2019-12-09 22:17:11', 'admin', '2019-12-09 22:23:48', '按钮', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/button/index', '', 1, '');
INSERT INTO `sys_menu` VALUES ('8601b3790fb4c239f5ea5a61b56cd208', 3, 'admin', '2019-12-18 16:29:12', 'admin', '2019-12-19 11:20:38', '系统消息', '91be39ac09cdc71c7435ffb94f611795', '0', 'func/message/sys/list', '', 8, '');
INSERT INTO `sys_menu` VALUES ('87c8451f951888e483fe7ec7668507fe', 0, 'admin', '2019-12-09 22:32:02', NULL, NULL, '栅格', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 0, '');
INSERT INTO `sys_menu` VALUES ('8afeeaa1d6f5c139f8b8cecf6c451ef2', 0, 'admin', '2019-12-10 11:07:04', NULL, NULL, '监听行事件', '254d304678038641f87aff42be68ef74', '0', 'comp/table/onrow', '', 19, '');
INSERT INTO `sys_menu` VALUES ('8eec007fbd23c0a318ba3e74f9c3d2a9', 1, 'admin', '2019-12-09 22:35:03', 'admin', '2019-12-09 22:35:24', '移动桌面端组合', '87c8451f951888e483fe7ec7668507fe', '0', 'comp/grid/mobile-pc', '', 3, '');
INSERT INTO `sys_menu` VALUES ('91be39ac09cdc71c7435ffb94f611795', 2, 'admin', '2019-12-06 14:00:23', 'admin', '2019-12-06 14:00:38', '常用功能', NULL, '0', '', '<i class=\'layui-icon layui-icon-set\'></i>', 3, '');
INSERT INTO `sys_menu` VALUES ('9cee434d97d9f75d2f35b2c87bef99bf', 0, 'admin', '2019-12-10 10:31:48', NULL, NULL, '开启复选框', '254d304678038641f87aff42be68ef74', '0', 'comp/table/checkbox', '', 10, '');
INSERT INTO `sys_menu` VALUES ('9de651e122d377f59f08febfaa3a5418', 1, 'admin', '2019-12-10 11:08:55', 'admin', '2019-12-10 11:09:14', '监听单元格事件', '254d304678038641f87aff42be68ef74', '0', 'comp/table/cellEvent', '', 22, '');
INSERT INTO `sys_menu` VALUES ('9e278b9a4ee34914d1dd1969e800eb67', 0, 'admin', '2019-12-10 11:40:56', NULL, NULL, '功能演示二', '76cf30bf5db55a9204e38dec11079e2a', '0', 'comp/laypage/demo2', '', 2, '');
INSERT INTO `sys_menu` VALUES ('9f34459fff390562f430323f70c51f40', 1, 'admin', '2019-12-09 11:28:06', 'admin', '2019-12-09 11:28:13', '在线用户列表', '7476783545496331c211296efaa561a2', '0', 'monitor/online/index', '', 3, '');
INSERT INTO `sys_menu` VALUES ('a5efa8ea44340e69710c398838c78989', 0, 'admin', '2019-12-20 14:18:47', NULL, NULL, '保存字典值', '3c02556f5054c9fdf6faf6574004e792', '1', 'sys:dic:saveval', '', 4, '');
INSERT INTO `sys_menu` VALUES ('a6645e6e95d7babef01e71effdd91d6d', 1, 'admin', '2019-12-10 11:03:01', 'admin', '2019-12-10 11:03:08', '开启单元格编辑', '254d304678038641f87aff42be68ef74', '0', 'comp/table/cellEdit', '', 12, '');
INSERT INTO `sys_menu` VALUES ('a68e3126a3a35f5a50141ca027c35ce8', 0, 'admin', '2019-12-10 09:48:01', NULL, NULL, '动画', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/anim/index', '', 10, '');
INSERT INTO `sys_menu` VALUES ('a7194554d65798806bac6732d36f67a4', 0, 'admin', '2019-12-10 09:55:16', NULL, NULL, '功能演示', '524ab0ea6d1bd06ea8f7430acf258b41', '0', 'comp/layer/list', '', 1, '');
INSERT INTO `sys_menu` VALUES ('a9adea345ac1afeb3292e576bbfec804', 0, 'admin', '2019-12-10 09:16:38', NULL, NULL, '表单', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 3, '');
INSERT INTO `sys_menu` VALUES ('aa04121cc3d2e79286103acc24c35770', 0, 'admin', '2019-12-20 14:07:25', NULL, NULL, '新增', '755956ba2ea812d8749a062f34b7da2d', '1', 'sys/user/add', '', 1, '');
INSERT INTO `sys_menu` VALUES ('ab83dd17afdebc0ffe0d20318e23ea07', 0, 'admin', '2019-12-10 10:07:05', NULL, NULL, '设定主题', 'e04db2a1c2d8d911b6961f0c29ffe02a', '0', 'comp/laydate/theme', '', 3, '');
INSERT INTO `sys_menu` VALUES ('ac698e2709fdcb90ee8b9ed88e5905d1', 1, 'admin', '2019-12-10 10:04:38', 'admin', '2019-12-10 10:07:16', '功能示例二', 'e04db2a1c2d8d911b6961f0c29ffe02a', '0', 'comp/laydate/demo2', '', 2, '');
INSERT INTO `sys_menu` VALUES ('adc1f1a492ac837bfec6292265c3b30d', 0, 'admin', '2019-12-13 16:56:37', NULL, NULL, 'kz.layedit 编辑器', 'dd22d35e3d8fc63fa51ff4e01668fab6', '0', 'func/layedit/kzlayedit', '', 2, '');
INSERT INTO `sys_menu` VALUES ('ae1dd4b597a544660759211fa1481b1b', 3, 'system', '2019-03-11 16:50:17', 'admin', '2019-12-20 14:12:19', '新增', '6ee28a2031c8d1e701613082ea6ebc5e', '1', 'sys/dept/add', '', 1, '');
INSERT INTO `sys_menu` VALUES ('ae6e226f18c93b6fc93c1b6817242741', 0, 'admin', '2019-12-10 09:19:09', NULL, NULL, '表单元素', 'a9adea345ac1afeb3292e576bbfec804', '0', 'comp/form/element', '', 1, '');
INSERT INTO `sys_menu` VALUES ('af52b09768353133a4d3aab4f167d4c4', 0, 'admin', '2019-12-20 14:14:24', NULL, NULL, '保存', 'ecacd9efd6c237b80e46df8b1507a904', '1', 'sys:menu:save', '', 2, '');
INSERT INTO `sys_menu` VALUES ('afd48d274868f87f05fa4aad2d767bf9', 0, 'admin', '2019-12-10 12:25:33', NULL, NULL, '轮播', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/carousel/index', '', 21, '');
INSERT INTO `sys_menu` VALUES ('b2d074869324db44caae589ceabacdcb', 0, 'admin', '2019-12-10 10:29:56', NULL, NULL, '开启合计行', '254d304678038641f87aff42be68ef74', '0', 'comp/table/totalRow', '', 8, '');
INSERT INTO `sys_menu` VALUES ('b7eadf3fdfe67c7ff3223c838213a584', 0, 'admin', '2019-12-10 09:56:14', NULL, NULL, '特殊示例', '524ab0ea6d1bd06ea8f7430acf258b41', '0', 'comp/layer/special-demo', '', 2, '');
INSERT INTO `sys_menu` VALUES ('bad0d5cfbdf372cf8025d32053fa4be3', 0, 'admin', '2019-12-10 09:32:34', NULL, NULL, '面板', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/panel/index', '', 7, '');
INSERT INTO `sys_menu` VALUES ('be00c02c463f91af6433918bee8219c4', 0, 'admin', '2019-12-10 09:19:52', NULL, NULL, '表单组合', 'a9adea345ac1afeb3292e576bbfec804', '0', 'comp/form/group', '', 2, '');
INSERT INTO `sys_menu` VALUES ('bf5b7351e458a15a1ad7cf4421eb9493', 1, 'admin', '2019-12-20 14:11:30', 'admin', '2019-12-20 14:25:30', '删除', '7de00ce887ea0c9c7144636112801a0c', '1', 'sys:role:delete', '', 4, '');
INSERT INTO `sys_menu` VALUES ('c8ac3f04aea6be178a23cb5f93ec71aa', 0, 'admin', '2019-12-10 11:09:44', NULL, NULL, '复杂表头', '254d304678038641f87aff42be68ef74', '0', 'comp/table/thead', '', 23, '');
INSERT INTO `sys_menu` VALUES ('c98b436c0bdf3e1db2aacbbaa6fcaca4', 0, 'admin', '2019-12-20 14:16:47', NULL, NULL, '新增字典', '3c02556f5054c9fdf6faf6574004e792', '1', 'sys/dic/add', '', 1, '');
INSERT INTO `sys_menu` VALUES ('c9f52990bc82165f63e45df34077896c', 1, 'admin', '2019-12-20 14:09:55', 'admin', '2019-12-20 14:19:13', '保存角色', '7de00ce887ea0c9c7144636112801a0c', '1', 'sys:role:save', '', 2, '');
INSERT INTO `sys_menu` VALUES ('d080eec50903b82466fe1aa19f78b01d', 1, 'admin', '2019-12-02 14:47:11', 'admin', '2019-12-02 14:53:25', '保存', '6ee28a2031c8d1e701613082ea6ebc5e', '1', 'sys:dept:save', '', 2, '');
INSERT INTO `sys_menu` VALUES ('d4f0ee03c841e22d7cb1cbd271c63100', 2, 'admin', '2019-12-13 09:45:30', 'admin', '2019-12-13 09:46:53', 'layedit 编辑器', 'dd22d35e3d8fc63fa51ff4e01668fab6', '0', 'func/layedit/layedit', '', 1, '');
INSERT INTO `sys_menu` VALUES ('da49ca0fe2ea8be4bb557c251c6180c3', 4, 'admin', '2019-03-29 16:13:44', 'admin', '2019-03-29 16:13:54', 'druid 监控', '7476783545496331c211296efaa561a2', '0', 'druid/sql.html', '', 1, '');
INSERT INTO `sys_menu` VALUES ('dd22d35e3d8fc63fa51ff4e01668fab6', 0, 'admin', '2019-12-13 09:40:56', NULL, NULL, '富文本编辑器', '91be39ac09cdc71c7435ffb94f611795', '0', '', '', 5, '');
INSERT INTO `sys_menu` VALUES ('de471ff81fd3b7b0708ea3fd1a493f78', 0, 'admin', '2019-12-10 11:03:46', NULL, NULL, '加入表单元素', '254d304678038641f87aff42be68ef74', '0', 'comp/table/form', '', 13, '');
INSERT INTO `sys_menu` VALUES ('df3fb69cec5024ffdc1967ed729541b6', 0, 'admin', '2019-12-10 11:07:46', NULL, NULL, '数据表格的重载', '254d304678038641f87aff42be68ef74', '0', 'comp/table/reload', '', 20, '');
INSERT INTO `sys_menu` VALUES ('e04db2a1c2d8d911b6961f0c29ffe02a', 0, 'admin', '2019-12-10 09:57:46', NULL, NULL, '时间日期', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 13, '');
INSERT INTO `sys_menu` VALUES ('e22d3ebde93e719f9cbe9b9004c2eae8', 1, 'admin', '2019-12-09 22:37:29', 'admin', '2019-12-09 22:38:36', '低于桌面堆叠排列', '87c8451f951888e483fe7ec7668507fe', '0', 'comp/grid/stack', '', 5, '');
INSERT INTO `sys_menu` VALUES ('ecacd9efd6c237b80e46df8b1507a904', 3, 'system', '2019-03-18 23:38:36', 'admin', '2019-11-28 16:53:21', '菜单管理', '623262eac50915905031d8d67d8702ea', '0', 'sys/menu/list', '', 4, '');
INSERT INTO `sys_menu` VALUES ('ee2ca956201793d7f035d2ae6e944c93', 0, 'admin', '2019-12-10 10:24:21', NULL, NULL, '列宽自动分配', '254d304678038641f87aff42be68ef74', '0', 'comp/table/auto', '', 2, '');
INSERT INTO `sys_menu` VALUES ('ee31b443c0eb5d142e29012f559d0ef6', 0, 'admin', '2019-12-10 10:31:04', NULL, NULL, '高度最大适应', '254d304678038641f87aff42be68ef74', '0', 'comp/table/height', '', 9, '');
INSERT INTO `sys_menu` VALUES ('ee7e7bb6e0da465b0b686473acc9259f', 0, 'admin', '2019-12-10 10:32:29', NULL, NULL, '开启单选框', '254d304678038641f87aff42be68ef74', '0', 'comp/table/radio', '', 11, '');
INSERT INTO `sys_menu` VALUES ('f4bc6809cec463e0206deb80319dfb97', 1, 'admin', '2019-12-09 22:33:36', 'admin', '2019-12-09 22:34:29', '按移动端排列', '87c8451f951888e483fe7ec7668507fe', '0', 'comp/grid/mobile', '', 2, '');
INSERT INTO `sys_menu` VALUES ('f5872bede681da8b0a7d5a2c1a2b3146', 0, 'admin', '2019-12-10 11:08:18', NULL, NULL, '设置初始排序', '254d304678038641f87aff42be68ef74', '0', 'comp/table/initSort', '', 21, '');
INSERT INTO `sys_menu` VALUES ('f8c3125fc92513c4ef9153aca7d6e272', 1, 'admin', '2019-12-09 22:38:22', 'admin', '2019-12-09 22:39:54', '九宫格', '87c8451f951888e483fe7ec7668507fe', '0', 'comp/grid/speed-dial', '', 6, '');
INSERT INTO `sys_menu` VALUES ('f9679bc12890ed472d0fec02dd3fad64', 1, 'admin', '2019-12-10 11:41:26', 'admin', '2019-12-10 11:41:33', '上传', '2774f4f1c30a34a75dfcfe5f8945d370', '0', '', '', 17, '');
INSERT INTO `sys_menu` VALUES ('fabb29abae35721ad0d893ed896d2d10', 0, 'admin', '2019-12-09 17:24:40', NULL, NULL, '支付', '91be39ac09cdc71c7435ffb94f611795', '0', '', '', 2, '');
INSERT INTO `sys_menu` VALUES ('fb0521a1d21f63283a59a228a103a122', 1, 'admin', '2019-12-09 17:26:11', 'admin', '2019-12-16 14:29:40', '支付宝支付', 'fabb29abae35721ad0d893ed896d2d10', '0', 'func/pay/ali/form', '', 2, '');
INSERT INTO `sys_menu` VALUES ('fddbacbd7477af853faac22ca34d3a55', 0, 'admin', '2019-12-10 09:51:44', NULL, NULL, '辅助', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/auxiliar/index', '', 11, '');
INSERT INTO `sys_menu` VALUES ('fe70cf2fa1985a461186efcaf4633283', 2, 'admin', '2019-12-09 22:32:45', 'admin', '2019-12-09 22:32:58', '等比例列表排列', '87c8451f951888e483fe7ec7668507fe', '0', 'comp/grid/list', '', 1, '');
INSERT INTO `sys_menu` VALUES ('ff358bb4ca406915664f8cb8b848e9da', 0, 'admin', '2019-12-20 14:13:56', NULL, NULL, '新增', 'ecacd9efd6c237b80e46df8b1507a904', '1', 'sys/menu/add', '', 1, '');
INSERT INTO `sys_menu` VALUES ('ff544ef46b64157f10b8c20a3ae33d0d', 0, 'admin', '2019-12-10 09:24:43', NULL, NULL, '导航', '2774f4f1c30a34a75dfcfe5f8945d370', '0', 'comp/nav/index', '', 4, '');

-- ----------------------------
-- Table structure for sys_online_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_online_session`;
CREATE TABLE `sys_online_session`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `SESSION_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'sessionId',
  `USER_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `REAL_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `DEPT_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `AVATAR` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `HOST` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主机IP',
  `BROWSER` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `OS` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `BEGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `LAST_TIME` datetime(0) NULL DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_online_session
-- ----------------------------
INSERT INTO `sys_online_session` VALUES ('d0e236e95dbf9d389dc5f225f027298d', 2, 'system', '2020-03-25 12:07:41', 'system', '2020-03-25 12:35:57', '922446f5-926e-4c0e-845d-e400dd87c61a', 'd4f2018bfc34b5c92942abebff2a829f', 'admin', '管理员', '开发部', NULL, '本地', 'Chrome 8', 'Windows 10', '1', '2020-03-25 12:07:35', '2020-03-25 12:33:55');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `PARENT_ID` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父角色ID',
  `ROLE_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `ORDER_NO` int(11) NULL DEFAULT NULL COMMENT '排序',
  `MEMO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_ID_123456`(`PARENT_ID`) USING BTREE,
  CONSTRAINT `sys_role_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `sys_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('338f5cdec10b84302344ef84e4570d0d', 0, 'system', '2019-03-14 16:30:03', NULL, NULL, '销售人员', 'ea291b3717f834d2add7b14e2a2464ca', '009', 0, '');
INSERT INTO `sys_role` VALUES ('3c67201c91bde61d709e4a2460f4e825', 0, 'system', '2019-03-14 16:30:16', NULL, NULL, '技术经理', 'ea291b3717f834d2add7b14e2a2464ca', '006', 1, '');
INSERT INTO `sys_role` VALUES ('4b860179c135ea23961cc1fb88926f97', 3, 'system', '2019-03-12 10:53:09', 'admin', '2020-03-25 12:11:08', '技术人员', '3c67201c91bde61d709e4a2460f4e825', '001', 1, '');
INSERT INTO `sys_role` VALUES ('a088b47539612eebeace406a8f458910', 3, 'system', '2019-03-14 16:29:52', 'admin', '2019-12-02 15:43:16', '市场人员', 'ea291b3717f834d2add7b14e2a2464ca', '004', 3, '2');
INSERT INTO `sys_role` VALUES ('ea291b3717f834d2add7b14e2a2464ca', 0, 'system', '2019-03-11 23:07:26', NULL, NULL, '管理员', NULL, '001', 1, '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `ROLE_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联角色ID',
  `MENU_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联菜单ID',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_role_menu_role_1234`(`ROLE_ID`) USING BTREE,
  INDEX `fk_role_menu_menu_uud766`(`MENU_ID`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`MENU_ID`) REFERENCES `sys_menu` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('00fd96f5540ed1bad8d8fb87b362ca4a', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '06428eaef123e23852946540710eafe7');
INSERT INTO `sys_role_menu` VALUES ('02bd067aeba9f0c1cf7267ad466ab7d0', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'fddbacbd7477af853faac22ca34d3a55');
INSERT INTO `sys_role_menu` VALUES ('03243b173f351276f3264c89222e79c6', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ff544ef46b64157f10b8c20a3ae33d0d');
INSERT INTO `sys_role_menu` VALUES ('07747265958e7c80da5b4c0e9ceeada9', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '10fd29fb35ea748ae75dcd84895997c0');
INSERT INTO `sys_role_menu` VALUES ('0857047dca6ec31ef1e398ce8ae032c6', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'f5872bede681da8b0a7d5a2c1a2b3146');
INSERT INTO `sys_role_menu` VALUES ('13709a922f553e09310973aab9410ec6', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ae6e226f18c93b6fc93c1b6817242741');
INSERT INTO `sys_role_menu` VALUES ('13c8584f4ed5e9727629adcb6aa69e39', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '53a9a384c24cbc1351fc4443afabded7');
INSERT INTO `sys_role_menu` VALUES ('161e39917d3d05e51b87ec9690fa71c7', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '19587742caad08e1c5eaa9483b02f49b');
INSERT INTO `sys_role_menu` VALUES ('1833d993b5fecdc433f62b3d4efb1211', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'afd48d274868f87f05fa4aad2d767bf9');
INSERT INTO `sys_role_menu` VALUES ('1dc270e65398fb3e2bc573f1c55bcc6d', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '700bb6c4a8d5cd6b9d7016e4faae8628');
INSERT INTO `sys_role_menu` VALUES ('1dce7a5d27918c7cbcfcdf98460ff355', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '623262eac50915905031d8d67d8702ea');
INSERT INTO `sys_role_menu` VALUES ('21e6be549c2f93910b949c79420d14ee', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'dd22d35e3d8fc63fa51ff4e01668fab6');
INSERT INTO `sys_role_menu` VALUES ('22f9dde83c14258486b270da1fbe9a29', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '3ea0bdfc67e946ddc62dd82d9858b540');
INSERT INTO `sys_role_menu` VALUES ('24efafb9ead083128edc79552105aee5', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '50677a25a6a1cc3e3d71834ef84c2c39');
INSERT INTO `sys_role_menu` VALUES ('28e2c107eae86c7534fa96bb42fbcd9a', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'be00c02c463f91af6433918bee8219c4');
INSERT INTO `sys_role_menu` VALUES ('2941f1a883c74a7156b02a537c5bcb43', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'd080eec50903b82466fe1aa19f78b01d');
INSERT INTO `sys_role_menu` VALUES ('2ae2fd64c32c1db8dc9f228865293241', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ae1dd4b597a544660759211fa1481b1b');
INSERT INTO `sys_role_menu` VALUES ('2ed05a286e1ee59836cf8e4ceb8650d3', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '9cee434d97d9f75d2f35b2c87bef99bf');
INSERT INTO `sys_role_menu` VALUES ('317811c8e2017e9646a17a2e6698f353', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '73c52763764317a58bb2b7db884e8788');
INSERT INTO `sys_role_menu` VALUES ('32f23506c217e0ef90b9e21418dbd345', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7bf0c837223ed4b2693eadc933d5de56');
INSERT INTO `sys_role_menu` VALUES ('344ed59f6c4c59880a05e69aa33971f2', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '87c8451f951888e483fe7ec7668507fe');
INSERT INTO `sys_role_menu` VALUES ('38727eb30aee29c4c646c9607c3041ff', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'a7194554d65798806bac6732d36f67a4');
INSERT INTO `sys_role_menu` VALUES ('3932623fa286bd1bbc9231806497903c', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '3e5df1746ba7a3be358e41cedff4c825');
INSERT INTO `sys_role_menu` VALUES ('397bdfec75b26e4e511725a632db6fa3', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '8afeeaa1d6f5c139f8b8cecf6c451ef2');
INSERT INTO `sys_role_menu` VALUES ('3a8d7d25695ebd94b63a594b3cb9e573', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'fb0521a1d21f63283a59a228a103a122');
INSERT INTO `sys_role_menu` VALUES ('3b3e1460a8e1c2ad2ff07a434c725440', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '3c83e94d8c9499d3962e405a2dddeebb');
INSERT INTO `sys_role_menu` VALUES ('3d87523593e5974ed6d34799353a59cb', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'c98b436c0bdf3e1db2aacbbaa6fcaca4');
INSERT INTO `sys_role_menu` VALUES ('3e4d94504492eaf818f6db6987df851c', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'df3fb69cec5024ffdc1967ed729541b6');
INSERT INTO `sys_role_menu` VALUES ('3f41744dda4103b74b686097128fd934', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '9f34459fff390562f430323f70c51f40');
INSERT INTO `sys_role_menu` VALUES ('40b1ee107bc1fa0db17a363cac1e1ffc', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'f9679bc12890ed472d0fec02dd3fad64');
INSERT INTO `sys_role_menu` VALUES ('4136bba75ef96fd8a9400ddd47e71338', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ee31b443c0eb5d142e29012f559d0ef6');
INSERT INTO `sys_role_menu` VALUES ('42d8d710490f246e77d665003b37663b', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '755956ba2ea812d8749a062f34b7da2d');
INSERT INTO `sys_role_menu` VALUES ('448befd8da1ac39cbe3e8b78b2455d19', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '3d0d3eed4985181a82a6387a30974a9c');
INSERT INTO `sys_role_menu` VALUES ('46526fa5e21e54ec82ac1e248c9ef5ce', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '76cf30bf5db55a9204e38dec11079e2a');
INSERT INTO `sys_role_menu` VALUES ('4852090667d399f024127c9ae70d00db', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '77e7ae5ca528b246f72e4983374da469');
INSERT INTO `sys_role_menu` VALUES ('49a9cf2890c43be1bcc201c23272aa47', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '0d079901d30c6bdbcae1349de1aabd30');
INSERT INTO `sys_role_menu` VALUES ('49dc1a0f9dfd272a93f9347437ae1740', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '8eec007fbd23c0a318ba3e74f9c3d2a9');
INSERT INTO `sys_role_menu` VALUES ('4a3079e4e6d91f1873d9d5d23334cbea', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7c6037cdf7687a75d823b18b76c2d03f');
INSERT INTO `sys_role_menu` VALUES ('4aa5d02ea3e526ea70e0d71cb1885255', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'a68e3126a3a35f5a50141ca027c35ce8');
INSERT INTO `sys_role_menu` VALUES ('4be998cda6dc0335b8c18b041d389ff9', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '2774f4f1c30a34a75dfcfe5f8945d370');
INSERT INTO `sys_role_menu` VALUES ('4db3c55d921b015462fc2dbf1763d4c5', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'e22d3ebde93e719f9cbe9b9004c2eae8');
INSERT INTO `sys_role_menu` VALUES ('4de50c91c9a89934bde23e867924eae4', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'c8ac3f04aea6be178a23cb5f93ec71aa');
INSERT INTO `sys_role_menu` VALUES ('4fc7ac5e8493f8fb67be1777d737635c', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '4388d13e00a1a94300c5fa7ff32d9d90');
INSERT INTO `sys_role_menu` VALUES ('50dfec2c1138c77de50ab31f57e1b1e7', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '8601b3790fb4c239f5ea5a61b56cd208');
INSERT INTO `sys_role_menu` VALUES ('571dddffa1d7178274ab7bd973f75e62', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '1b52228fe7c8b9cf259f9f7c9bfcd844');
INSERT INTO `sys_role_menu` VALUES ('5a60892f12a450a2b3dbabf61750485b', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ab83dd17afdebc0ffe0d20318e23ea07');
INSERT INTO `sys_role_menu` VALUES ('5a86e141d661a3b77e9d9e2610db1d0c', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'fe70cf2fa1985a461186efcaf4633283');
INSERT INTO `sys_role_menu` VALUES ('5b2502e988a62311a0f3528355992b74', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '67090df9bf7c076aee440df201436458');
INSERT INTO `sys_role_menu` VALUES ('5bc60d573f2a62d6379105d8092273b6', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '84cc6a4dbda00fc1d3a24d55c9cd14e6');
INSERT INTO `sys_role_menu` VALUES ('5c1fb3c268e2e7ac1b5fc434a5f45227', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '207ae629bfbf53290f3c64f1933d5ae6');
INSERT INTO `sys_role_menu` VALUES ('5e7b89afe3791d65eb3927e38a29754b', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '4553ac5aa6eb328b8f5e68506c528715');
INSERT INTO `sys_role_menu` VALUES ('607be5b711ecdfa1cc59aa07824a0bda', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '1498b9a569c6e2e6562e79a834cc93f4');
INSERT INTO `sys_role_menu` VALUES ('6402b6e17524dab21aad11edcf25614a', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '66f6ab8a5f40facb94ab9ff93cd14aaf');
INSERT INTO `sys_role_menu` VALUES ('65b2d12a53ad7dfec4b319aae9c37ad7', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '38aba0fbe14e8c0f596ff40fb29532aa');
INSERT INTO `sys_role_menu` VALUES ('691ab2156c04170f32fd572fae60b4e1', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '5a717dd0bdd94123900508d172088d48');
INSERT INTO `sys_role_menu` VALUES ('6b602256017e4af43ca0b9a96b88855a', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '54c7509f7a0fca385c935e4919dc10e7');
INSERT INTO `sys_role_menu` VALUES ('6d6608e8372687afb0225ade5935dd20', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'bf5b7351e458a15a1ad7cf4421eb9493');
INSERT INTO `sys_role_menu` VALUES ('70816e1dd9b65adaa9d6c7317a9a0397', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'adc1f1a492ac837bfec6292265c3b30d');
INSERT INTO `sys_role_menu` VALUES ('76e46f07d0bb51d7bd3985269ffb6a9b', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'd4f0ee03c841e22d7cb1cbd271c63100');
INSERT INTO `sys_role_menu` VALUES ('786bd89257a3b8f06cba1a1df8d1c32e', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '4618aa278da605c57895074aee4e095d');
INSERT INTO `sys_role_menu` VALUES ('7a1c549e31b130ea788aebde24eb109d', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7e3a22591b016b3ce6ccc881e7b9d294');
INSERT INTO `sys_role_menu` VALUES ('7b2a9c839bea6c8a8bd3ff53eb70f0d4', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '254d304678038641f87aff42be68ef74');
INSERT INTO `sys_role_menu` VALUES ('7b6c0495efc0f2e00c8602553b29c015', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'af52b09768353133a4d3aab4f167d4c4');
INSERT INTO `sys_role_menu` VALUES ('7c8297ea9ed9b0091d58cf7b938c7238', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'de471ff81fd3b7b0708ea3fd1a493f78');
INSERT INTO `sys_role_menu` VALUES ('7daeed848ee9b7cce803506cc722729a', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'f4bc6809cec463e0206deb80319dfb97');
INSERT INTO `sys_role_menu` VALUES ('82a5582660bf9c034c0e775ec97cc01c', 0, 'admin', '2019-12-20 13:47:34', NULL, NULL, 'a088b47539612eebeace406a8f458910', '623262eac50915905031d8d67d8702ea');
INSERT INTO `sys_role_menu` VALUES ('835c69f7f50e152b1eb02eb6b224060e', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '1fc380fb7a2b3992e5f0dce39a683ce3');
INSERT INTO `sys_role_menu` VALUES ('86f7733443d88b8147db20c09aececa2', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ac698e2709fdcb90ee8b9ed88e5905d1');
INSERT INTO `sys_role_menu` VALUES ('87d7346467c3eca206465df81750ff90', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '36df3e985a7c01432cc579728425bf09');
INSERT INTO `sys_role_menu` VALUES ('8ba600ce6a345535b321bdc2ab311685', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '427d532a84547250e510a3dcc388c92f');
INSERT INTO `sys_role_menu` VALUES ('8d08b05efa79bb377c39fc6ee2a0043c', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ee7e7bb6e0da465b0b686473acc9259f');
INSERT INTO `sys_role_menu` VALUES ('90b3aa45dc9848a4e9f55b830420e6fa', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '28d1d2a835dc364315cc7f9832d664a0');
INSERT INTO `sys_role_menu` VALUES ('9243733387962a2318c95f28b40ce895', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ff358bb4ca406915664f8cb8b848e9da');
INSERT INTO `sys_role_menu` VALUES ('9510f6fe515d65a432ed6b1775af7d42', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '326147b3715dd8698f4d84058404e83d');
INSERT INTO `sys_role_menu` VALUES ('9592af507e3bd3e8eea65543d7be62d2', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7514b093df0e52d76814ce5a6fb5c868');
INSERT INTO `sys_role_menu` VALUES ('972bfd73a2d2950fe8bdd6fe107d0023', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '524ab0ea6d1bd06ea8f7430acf258b41');
INSERT INTO `sys_role_menu` VALUES ('97f28dd237e1373bcc75fce736a44008', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'e04db2a1c2d8d911b6961f0c29ffe02a');
INSERT INTO `sys_role_menu` VALUES ('9832cc09d35f3133ca4479fd887748f0', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '91be39ac09cdc71c7435ffb94f611795');
INSERT INTO `sys_role_menu` VALUES ('9a6cc463ce48d89e31b04c426ea8df2b', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '22691e0c807693f3ad52dcb043a2e2f7');
INSERT INTO `sys_role_menu` VALUES ('9d25b523daca24ec4ad39251cf9fddbc', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7e106236ac4833f27ffa0250d3492071');
INSERT INTO `sys_role_menu` VALUES ('9e1d0e90203ff11dd765b072652aa726', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ecacd9efd6c237b80e46df8b1507a904');
INSERT INTO `sys_role_menu` VALUES ('a7574174eb6452e958f675a04d37390c', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '612265d33877f681c26b2180f90a3e62');
INSERT INTO `sys_role_menu` VALUES ('ad72441373c094143102c78a27821f92', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '9de651e122d377f59f08febfaa3a5418');
INSERT INTO `sys_role_menu` VALUES ('ae10d2677b2db1e18906dd9cfe691bfe', 0, 'admin', '2019-12-20 13:47:34', NULL, NULL, 'a088b47539612eebeace406a8f458910', '755956ba2ea812d8749a062f34b7da2d');
INSERT INTO `sys_role_menu` VALUES ('af43a1f832d4c7a9a467214ed7bd67af', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'a5efa8ea44340e69710c398838c78989');
INSERT INTO `sys_role_menu` VALUES ('b19781966293e8487693d2fa4630c781', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'a9adea345ac1afeb3292e576bbfec804');
INSERT INTO `sys_role_menu` VALUES ('b31652fdba134a885dc8a1442d6a4388', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '0f5a97c074f42292c477c1ecb8e58769');
INSERT INTO `sys_role_menu` VALUES ('b363795737f83357c185e8ea26a04ed3', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'b7eadf3fdfe67c7ff3223c838213a584');
INSERT INTO `sys_role_menu` VALUES ('b3d3522c530ab6dd848467086c9231b1', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '4b2e9da372c5c7b5d4d39c0f9a4e402d');
INSERT INTO `sys_role_menu` VALUES ('b7ed8fd9cd4a1293a73abcd4189eaebd', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7a5be98a72343beb5bd4f73e2793acb3');
INSERT INTO `sys_role_menu` VALUES ('b98103738cc9a744258a02588c04fdcc', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '40cad6aa986f987303647287352f7788');
INSERT INTO `sys_role_menu` VALUES ('b99e946201dc8cb5acbc631f96228081', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '60fda72e475b1536cb166b0af7741697');
INSERT INTO `sys_role_menu` VALUES ('bb87ec8f8614dc6434f0729ff1db00f4', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '33ad4150fead0f227d7c6adb3d4fde2f');
INSERT INTO `sys_role_menu` VALUES ('c128df6dee0936d850d1344f446f145e', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '3b362bf507f42d306368728df86877e0');
INSERT INTO `sys_role_menu` VALUES ('c3b5109a23ef275dab2ee50fd36a8db0', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '0537fa19001c1758fe1d7e8ae93cb61a');
INSERT INTO `sys_role_menu` VALUES ('c55d1f62daac983b5a0ba1747020e7a2', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '24ca613859f753247f86b55a13e4f7d1');
INSERT INTO `sys_role_menu` VALUES ('c581a2b08507c23bfa8d4653b6af5acc', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'b2d074869324db44caae589ceabacdcb');
INSERT INTO `sys_role_menu` VALUES ('c62b7ede5e627c19f6fd3b70e63fb397', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '83b3db7973133104ba24135b453939ce');
INSERT INTO `sys_role_menu` VALUES ('c686a10e8f7d77589c52611024cb88e4', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '391f39b83b80cc3bd68e115d935f8109');
INSERT INTO `sys_role_menu` VALUES ('c850171fccf3c913fa310db37a4db5c6', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'fabb29abae35721ad0d893ed896d2d10');
INSERT INTO `sys_role_menu` VALUES ('cbe279ab65aac3cd782fa1fcad2166fa', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '75e8d5210cf7c50af4db8f560c020974');
INSERT INTO `sys_role_menu` VALUES ('ce4190b2102fee2fb8ba27ace9d7b90c', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '6ee28a2031c8d1e701613082ea6ebc5e');
INSERT INTO `sys_role_menu` VALUES ('d085ec2453e9da0ef0eb91b5e1334d0c', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '4f8fa64d0ec7fce7c2cedc5ac6d09489');
INSERT INTO `sys_role_menu` VALUES ('d3a31d26ba4140adcdc767ce89e17ba0', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'f8c3125fc92513c4ef9153aca7d6e272');
INSERT INTO `sys_role_menu` VALUES ('d5aaeb8e8c29805a9bcf6fb10af3c14f', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'a6645e6e95d7babef01e71effdd91d6d');
INSERT INTO `sys_role_menu` VALUES ('d683c3a6d4b8fc48c319c5a6f915f546', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '6abd38f3c7631bccffb49ade84dba77b');
INSERT INTO `sys_role_menu` VALUES ('d7a8f64ab1845c7031c82bfa40c5f909', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '324c09d31cc6910fa5ae805a737d32a8');
INSERT INTO `sys_role_menu` VALUES ('d7afa503cfb15416ca1a6a09971b7780', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'ee2ca956201793d7f035d2ae6e944c93');
INSERT INTO `sys_role_menu` VALUES ('db2eb4e499d911fb5344adf3939d7e98', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'c9f52990bc82165f63e45df34077896c');
INSERT INTO `sys_role_menu` VALUES ('dc5c46e3dd30797829c92464df415d06', 0, 'admin', '2019-12-20 13:47:34', NULL, NULL, 'a088b47539612eebeace406a8f458910', '6ee28a2031c8d1e701613082ea6ebc5e');
INSERT INTO `sys_role_menu` VALUES ('e1baff17966fac5b2eede5cc509380fe', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7476783545496331c211296efaa561a2');
INSERT INTO `sys_role_menu` VALUES ('e341997f0e0846b16e616b360003ddff', 0, 'admin', '2019-12-20 13:47:34', NULL, NULL, 'a088b47539612eebeace406a8f458910', 'ae1dd4b597a544660759211fa1481b1b');
INSERT INTO `sys_role_menu` VALUES ('ead40023df200a8094130141f2f77a4e', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '59f249d45a9478beae170b4a4ce03b61');
INSERT INTO `sys_role_menu` VALUES ('ebcd4969b76b942f1916cce921f89ecf', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '3c02556f5054c9fdf6faf6574004e792');
INSERT INTO `sys_role_menu` VALUES ('f11d9900ba1f06c8bc2a6eba3ecd369d', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '6aa4271aff8644eeb1561f57f8320446');
INSERT INTO `sys_role_menu` VALUES ('f1973ee6f1101788444333d597004e9c', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'bad0d5cfbdf372cf8025d32053fa4be3');
INSERT INTO `sys_role_menu` VALUES ('f560b5d3df95782d974fbdc817676453', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '6c6da7e850d5a779a1ca3f3763cbce0d');
INSERT INTO `sys_role_menu` VALUES ('f9ac5c7b91baa60692fd1633db9fa1ec', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '7de00ce887ea0c9c7144636112801a0c');
INSERT INTO `sys_role_menu` VALUES ('fbe8b3c93e0833b3ba24a99d6c644c7b', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'da49ca0fe2ea8be4bb557c251c6180c3');
INSERT INTO `sys_role_menu` VALUES ('fc96a857f96fcaa9a52bba6244d1f843', 0, 'admin', '2020-01-13 11:37:20', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', 'aa04121cc3d2e79286103acc24c35770');
INSERT INTO `sys_role_menu` VALUES ('fcd98d032c4791281dd32ef4a55c9aea', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '213830032d2b72edf575f30d71361a51');
INSERT INTO `sys_role_menu` VALUES ('febee455130daa0a500ac2140988e69a', 0, 'admin', '2020-01-13 11:37:22', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '9e278b9a4ee34914d1dd1969e800eb67');
INSERT INTO `sys_role_menu` VALUES ('ffa444091c36eecd85d5dfb45badc7dc', 0, 'admin', '2020-01-13 11:37:21', NULL, NULL, 'ea291b3717f834d2add7b14e2a2464ca', '2f9b5ee346d667a5c146b3a25009bc8c');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `USER_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `REAL_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `PASSWORD` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `SALT` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '盐',
  `HEAD_IMG_URL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `STATUS` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `SEX` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `DEPT_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所属部门',
  `EMAIL` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `TEL_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `USER_NO` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `MEMO` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_user_depart_id_dfeaaa`(`DEPT_ID`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`DEPT_ID`) REFERENCES `sys_depart` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('94e270789548580b931e5a0ee66a0b5b', 10, 'system', '2019-03-14 17:40:16', 'admin', '2019-03-20 14:17:44', 'zhangsan', '张三', '5a7d50ad7c5b28f2c6d40a559f0294b2', 'XQIcWe9Jx', NULL, '1', '0', 'decde2a1436ea808ef7634e404a32ba0', '2@qq.com', '15951172388', '003', '');
INSERT INTO `sys_user` VALUES ('a361c780daefec7ab3ac824dbaf4afbb', 2, 'admin', '2019-12-20 13:45:58', 'admin', '2019-12-20 13:46:47', 'test5', '测试53', '85c5e205d9052696a3b1b32057c9d7dd', 'r9D', NULL, '1', '0', '6575819c40dccab6e91ef10306e24c9f', '2@qq.com', '13338800000', '005', '');
INSERT INTO `sys_user` VALUES ('d4f2018bfc34b5c92942abebff2a829f', 23, 'system', '2019-03-12 14:55:20', 'admin', '2019-12-19 13:57:46', 'admin', '管理员', 'f0a90af51410e581b174d7b71398afd7', 'a9pbpoi', NULL, '1', '0', 'bccea46c2e2ff20592ca91a245313d41', '2@qq.com', '15951172388', '001', '2');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `VERSIONS` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `CREATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `CREATE_DATE` datetime(0) NOT NULL COMMENT '创建时间',
  `UPDATE_NAME` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `UPDATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `USER_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联用户ID',
  `ROLE_ID` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联角色ID',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_user_role_user_id_1234`(`USER_ID`) USING BTREE,
  INDEX `fk_user_role_role_8uuddefe`(`ROLE_ID`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('4a010e03b01ebc4c5db109517340b83c', 0, 'admin', '2020-03-15 13:41:40', NULL, NULL, 'a361c780daefec7ab3ac824dbaf4afbb', 'a088b47539612eebeace406a8f458910');
INSERT INTO `sys_user_role` VALUES ('60997b0d86d4c19c956fdea82e7da914', 0, 'system', '2019-03-18 23:20:08', NULL, NULL, 'd4f2018bfc34b5c92942abebff2a829f', 'ea291b3717f834d2add7b14e2a2464ca');
INSERT INTO `sys_user_role` VALUES ('b83bca0950e21064a7fecf1af5ea9b16', 0, 'admin', '2019-11-28 16:28:17', NULL, NULL, '94e270789548580b931e5a0ee66a0b5b', '338f5cdec10b84302344ef84e4570d0d');

SET FOREIGN_KEY_CHECKS = 1;
