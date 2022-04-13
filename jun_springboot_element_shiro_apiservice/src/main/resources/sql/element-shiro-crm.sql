/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : element-shiro-crm

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 25/01/2022 14:42:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
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
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('ElementShiroScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');
INSERT INTO `qrtz_cron_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

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
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
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
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('ElementShiroScheduler', 'TASK_1', 'DEFAULT', NULL, 'com.element.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720030636F6D2E656C656D656E742E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000017D3BD5EB987874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);
INSERT INTO `qrtz_job_details` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', NULL, 'com.element.modules.job.utils.ScheduleJob', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720030636F6D2E656C656D656E742E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000017D3BD5EB987874000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B0200007870000000000000000174000672656E72656E74000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

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
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('RenrenScheduler', 'TRIGGER_ACCESS');

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
INSERT INTO `qrtz_scheduler_state` VALUES ('ElementShiroScheduler', 'LAPTOP-KJ675VND1643091594718', 1643092961712, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('RenrenScheduler', 'LAPTOP-KJ675VND1637386673795', 1637389435476, 15000);

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
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('ElementShiroScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1643094000000, 1643092200000, 5, 'WAITING', 'CRON', 1637389453000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B455973720030636F6D2E656C656D656E742E6D6F64756C65732E6A6F622E656E746974792E5363686564756C654A6F62456E7469747900000000000000010200074C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7074000E3020302F3330202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000017400047465737474000CE58F82E695B0E6B58BE8AF95737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0011000000007800);
INSERT INTO `qrtz_triggers` VALUES ('RenrenScheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1637389800000, 1637388000000, 5, 'WAITING', 'CRON', 1637386673000, 0, NULL, 2, '');

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES (1, 'testTask', 'test', '0 0/30 * * * ?', 0, '参数测试', '2021-11-20 13:33:35');

-- ----------------------------
-- Table structure for schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job_log`;
CREATE TABLE `schedule_job_log`  (
  `log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(20) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `params` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule_job_log
-- ----------------------------
INSERT INTO `schedule_job_log` VALUES (1, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-14 23:01:17');
INSERT INTO `schedule_job_log` VALUES (2, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-14 23:30:00');
INSERT INTO `schedule_job_log` VALUES (3, 1, 'testTask', 'test', 0, NULL, 2, '2022-01-15 00:00:00');
INSERT INTO `schedule_job_log` VALUES (4, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 15:00:00');
INSERT INTO `schedule_job_log` VALUES (5, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 15:30:00');
INSERT INTO `schedule_job_log` VALUES (6, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 16:00:00');
INSERT INTO `schedule_job_log` VALUES (7, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 16:30:00');
INSERT INTO `schedule_job_log` VALUES (8, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 17:00:00');
INSERT INTO `schedule_job_log` VALUES (9, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 17:30:00');
INSERT INTO `schedule_job_log` VALUES (10, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 18:00:00');
INSERT INTO `schedule_job_log` VALUES (11, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 18:30:00');
INSERT INTO `schedule_job_log` VALUES (12, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 19:00:00');
INSERT INTO `schedule_job_log` VALUES (13, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 19:30:00');
INSERT INTO `schedule_job_log` VALUES (14, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 20:00:00');
INSERT INTO `schedule_job_log` VALUES (15, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 20:30:00');
INSERT INTO `schedule_job_log` VALUES (16, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 21:00:00');
INSERT INTO `schedule_job_log` VALUES (17, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 21:30:00');
INSERT INTO `schedule_job_log` VALUES (18, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-15 22:00:00');
INSERT INTO `schedule_job_log` VALUES (19, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 22:30:00');
INSERT INTO `schedule_job_log` VALUES (20, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-15 23:00:00');
INSERT INTO `schedule_job_log` VALUES (21, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-16 13:00:00');
INSERT INTO `schedule_job_log` VALUES (22, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-16 13:30:00');
INSERT INTO `schedule_job_log` VALUES (23, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-16 20:30:00');
INSERT INTO `schedule_job_log` VALUES (24, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-16 21:00:00');
INSERT INTO `schedule_job_log` VALUES (25, 1, 'testTask', 'test', 0, NULL, 2, '2022-01-16 21:30:00');
INSERT INTO `schedule_job_log` VALUES (26, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-16 22:00:00');
INSERT INTO `schedule_job_log` VALUES (27, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-16 22:30:00');
INSERT INTO `schedule_job_log` VALUES (28, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-16 23:00:00');
INSERT INTO `schedule_job_log` VALUES (29, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-18 19:30:00');
INSERT INTO `schedule_job_log` VALUES (30, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-18 20:00:00');
INSERT INTO `schedule_job_log` VALUES (31, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-18 20:30:00');
INSERT INTO `schedule_job_log` VALUES (32, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-18 21:00:00');
INSERT INTO `schedule_job_log` VALUES (33, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-18 21:30:00');
INSERT INTO `schedule_job_log` VALUES (34, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-18 22:00:00');
INSERT INTO `schedule_job_log` VALUES (35, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-18 22:30:00');
INSERT INTO `schedule_job_log` VALUES (36, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-18 23:00:00');
INSERT INTO `schedule_job_log` VALUES (37, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-20 17:00:00');
INSERT INTO `schedule_job_log` VALUES (38, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 17:30:00');
INSERT INTO `schedule_job_log` VALUES (39, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 18:00:00');
INSERT INTO `schedule_job_log` VALUES (40, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 18:30:00');
INSERT INTO `schedule_job_log` VALUES (41, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 19:00:00');
INSERT INTO `schedule_job_log` VALUES (42, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-20 19:30:00');
INSERT INTO `schedule_job_log` VALUES (43, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 20:00:00');
INSERT INTO `schedule_job_log` VALUES (44, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-20 20:30:00');
INSERT INTO `schedule_job_log` VALUES (45, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 21:00:00');
INSERT INTO `schedule_job_log` VALUES (46, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-20 21:30:00');
INSERT INTO `schedule_job_log` VALUES (47, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-20 22:00:00');
INSERT INTO `schedule_job_log` VALUES (48, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 13:30:00');
INSERT INTO `schedule_job_log` VALUES (49, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 14:00:00');
INSERT INTO `schedule_job_log` VALUES (50, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 14:30:00');
INSERT INTO `schedule_job_log` VALUES (51, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 15:00:00');
INSERT INTO `schedule_job_log` VALUES (52, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-21 15:30:00');
INSERT INTO `schedule_job_log` VALUES (53, 1, 'testTask', 'test', 0, NULL, 2, '2022-01-21 16:00:00');
INSERT INTO `schedule_job_log` VALUES (54, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 16:30:00');
INSERT INTO `schedule_job_log` VALUES (55, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 17:00:00');
INSERT INTO `schedule_job_log` VALUES (56, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-21 17:30:00');
INSERT INTO `schedule_job_log` VALUES (57, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 18:00:00');
INSERT INTO `schedule_job_log` VALUES (58, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 18:30:00');
INSERT INTO `schedule_job_log` VALUES (59, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 19:00:00');
INSERT INTO `schedule_job_log` VALUES (60, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-21 19:30:00');
INSERT INTO `schedule_job_log` VALUES (61, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 20:00:00');
INSERT INTO `schedule_job_log` VALUES (62, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-21 20:30:00');
INSERT INTO `schedule_job_log` VALUES (63, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 21:00:00');
INSERT INTO `schedule_job_log` VALUES (64, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 21:30:00');
INSERT INTO `schedule_job_log` VALUES (65, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-21 22:00:00');
INSERT INTO `schedule_job_log` VALUES (66, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 22:30:00');
INSERT INTO `schedule_job_log` VALUES (67, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 23:00:00');
INSERT INTO `schedule_job_log` VALUES (68, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-21 23:30:00');
INSERT INTO `schedule_job_log` VALUES (69, 1, 'testTask', 'test', 0, NULL, 6, '2022-01-22 00:00:00');
INSERT INTO `schedule_job_log` VALUES (70, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-22 20:30:00');
INSERT INTO `schedule_job_log` VALUES (71, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-22 21:00:00');
INSERT INTO `schedule_job_log` VALUES (72, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-22 21:30:00');
INSERT INTO `schedule_job_log` VALUES (73, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-22 22:00:00');
INSERT INTO `schedule_job_log` VALUES (74, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-22 22:30:00');
INSERT INTO `schedule_job_log` VALUES (75, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-22 23:00:00');
INSERT INTO `schedule_job_log` VALUES (76, 1, 'testTask', 'test', 0, NULL, 1, '2022-01-22 23:30:00');
INSERT INTO `schedule_job_log` VALUES (77, 1, 'testTask', 'test', 0, NULL, 16, '2022-01-23 00:00:00');
INSERT INTO `schedule_job_log` VALUES (78, 1, 'testTask', 'test', 0, NULL, 0, '2022-01-25 14:30:00');

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('05bd3d70-fd07-4f6f-8693-e41e8bcce98a', '8', '2022-01-07 13:55:05');
INSERT INTO `sys_captcha` VALUES ('85b0ad53-1890-4fa7-8101-72c758330a60', '11', '2022-01-20 18:23:08');
INSERT INTO `sys_captcha` VALUES ('d8c446ab-74c7-4caf-897f-688853c79aaf', '7', '2022-01-21 13:14:55');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'key',
  `param_value` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'value',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `param_key`(`param_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'CLOUD_STORAGE_CONFIG_KEY', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}', 0, '云存储配置信息');
INSERT INTO `sys_config` VALUES (2, 'test', 'test', 1, 'test');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"用户列表\",\"url\":\"sys/user\",\"type\":1,\"icon\":\"admin\",\"orderNum\":1,\"list\":[]}]', 7, '0:0:0:0:0:0:0:1', '2021-11-21 10:47:54');
INSERT INTO `sys_log` VALUES (2, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[5]', 0, '0:0:0:0:0:0:0:1', '2021-11-21 10:50:49');
INSERT INTO `sys_log` VALUES (3, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[5]', 1, '0:0:0:0:0:0:0:1', '2021-11-21 10:50:56');
INSERT INTO `sys_log` VALUES (4, 'admin', '修改密码', 'com.element.modules.sys.controller.SysUserController.password()', '[{\"password\":\"admin\",\"newPassword\":\"admin123\"}]', 9, '0:0:0:0:0:0:0:1', '2021-11-21 12:44:06');
INSERT INTO `sys_log` VALUES (5, 'admin', '修改定时任务', 'com.element.modules.job.controller.ScheduleJobController.update()', '[{\"jobId\":1,\"beanName\":\"testTask\",\"params\":\"test\",\"cronExpression\":\"0 0/30 * * * ?\",\"status\":0,\"remark\":\"参数测试\"}]', 65, '0:0:0:0:0:0:0:1', '2021-11-21 13:01:51');
INSERT INTO `sys_log` VALUES (6, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[30]', 0, '0:0:0:0:0:0:0:1', '2021-11-21 13:54:02');
INSERT INTO `sys_log` VALUES (7, 'admin', '保存配置', 'com.element.modules.sys.controller.SysConfigController.save()', '[{\"id\":2,\"paramKey\":\"test\",\"paramValue\":\"test\",\"remark\":\"test\"}]', 47, '0:0:0:0:0:0:0:1', '2021-11-21 18:25:06');
INSERT INTO `sys_log` VALUES (8, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"123456@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 20, '127.0.0.1', '2021-12-29 16:17:10');
INSERT INTO `sys_log` VALUES (9, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"123456@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 3, '127.0.0.1', '2021-12-29 16:19:06');
INSERT INTO `sys_log` VALUES (10, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"1234567@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 5, '127.0.0.1', '2021-12-29 16:19:14');
INSERT INTO `sys_log` VALUES (11, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"1234567@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 5, '127.0.0.1', '2021-12-29 16:19:36');
INSERT INTO `sys_log` VALUES (12, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"1234567@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 3, '127.0.0.1', '2021-12-29 16:20:00');
INSERT INTO `sys_log` VALUES (13, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"1234567@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 21490, '127.0.0.1', '2021-12-29 16:21:07');
INSERT INTO `sys_log` VALUES (14, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"1234567@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 3, '127.0.0.1', '2021-12-29 16:25:19');
INSERT INTO `sys_log` VALUES (15, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"1234567@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 6, '127.0.0.1', '2021-12-29 16:28:07');
INSERT INTO `sys_log` VALUES (16, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"123456@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 7, '127.0.0.1', '2021-12-29 16:28:14');
INSERT INTO `sys_log` VALUES (17, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"userId\":1,\"username\":\"admin\",\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"email\":\"123456@qq.com\",\"mobile\":\"13612345678\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 6, '127.0.0.1', '2021-12-29 16:29:09');
INSERT INTO `sys_log` VALUES (18, 'admin', '保存用户', 'com.element.modules.sys.controller.SysUserController.save()', '[{\"userId\":2,\"username\":\"test\",\"password\":\"e016671c526e56d3cb80b236764ed4999682bb245cf207126a4c51075968e243\",\"salt\":\"gQi9yUdd3C3I8syPHjON\",\"email\":\"1234@qq.com\",\"mobile\":\"18877514905\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Dec 29, 2021 4:40:25 PM\"}]', 12, '127.0.0.1', '2021-12-29 16:40:26');
INSERT INTO `sys_log` VALUES (19, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"userId\":2,\"username\":\"test\",\"salt\":\"gQi9yUdd3C3I8syPHjON\",\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"status\":1,\"roleIdList\":[],\"createUserId\":1}]', 6, '127.0.0.1', '2021-12-29 16:40:40');
INSERT INTO `sys_log` VALUES (20, 'admin', '删除用户', 'com.element.modules.sys.controller.SysUserController.delete()', '[[2]]', 6, '127.0.0.1', '2021-12-29 16:40:45');
INSERT INTO `sys_log` VALUES (21, 'admin', '保存用户', 'com.element.modules.sys.controller.SysUserController.save()', '[{\"userId\":3,\"username\":\"test\",\"password\":\"91d3fa823ffd7cb56776db61d3827d3210f43d235613d884481d74355fc220f3\",\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"status\":1,\"roleIdList\":[],\"createUserId\":1,\"createTime\":\"Dec 29, 2021 4:41:15 PM\"}]', 6, '127.0.0.1', '2021-12-29 16:41:15');
INSERT INTO `sys_log` VALUES (22, 'admin', '保存角色', 'com.element.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":1,\"roleName\":\"测试用户\",\"remark\":\"测试用户\",\"createUserId\":1,\"menuIdList\":[29,30,-666666],\"createTime\":\"Dec 29, 2021 9:14:32 PM\"}]', 84, '127.0.0.1', '2021-12-29 21:14:33');
INSERT INTO `sys_log` VALUES (23, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"测试用户\",\"remark\":\"测试用户\",\"createUserId\":1,\"menuIdList\":[-666666]}]', 6, '127.0.0.1', '2021-12-29 21:16:54');
INSERT INTO `sys_log` VALUES (24, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"测试用户1\",\"remark\":\"测试用户1\",\"createUserId\":1,\"menuIdList\":[29,30,-666666]}]', 7, '127.0.0.1', '2021-12-29 21:17:04');
INSERT INTO `sys_log` VALUES (25, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"roleId\":1,\"roleName\":\"测试用户\",\"remark\":\"测试用户\",\"createUserId\":1,\"menuIdList\":[29,30,-666666]}]', 6, '127.0.0.1', '2021-12-29 21:17:14');
INSERT INTO `sys_log` VALUES (26, 'admin', '删除角色', 'com.element.modules.sys.controller.SysRoleController.delete()', '[[1]]', 9, '127.0.0.1', '2021-12-29 21:17:53');
INSERT INTO `sys_log` VALUES (27, 'admin', '保存角色', 'com.element.modules.sys.controller.SysRoleController.save()', '[{\"roleId\":2,\"roleName\":\"测试用户\",\"remark\":\"测试用户\",\"createUserId\":1,\"menuIdList\":[29,30,-666666],\"createTime\":\"Dec 29, 2021 9:18:09 PM\"}]', 5, '127.0.0.1', '2021-12-29 21:18:09');
INSERT INTO `sys_log` VALUES (28, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理1\",\"type\":0,\"icon\":\"component\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 5, '127.0.0.1', '2021-12-29 21:44:11');
INSERT INTO `sys_log` VALUES (29, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"component\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 4, '127.0.0.1', '2021-12-29 21:44:18');
INSERT INTO `sys_log` VALUES (30, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"type\":0,\"icon\":\"component\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 3, '127.0.0.1', '2021-12-29 22:11:00');
INSERT INTO `sys_log` VALUES (31, 'admin', '保存菜单', 'com.element.modules.sys.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":29,\"name\":\"文章列表\",\"component\":\"example/list\",\"type\":1,\"icon\":\"list\",\"orderNum\":1,\"list\":[],\"children\":[]}]', 6, '127.0.0.1', '2021-12-29 22:14:47');
INSERT INTO `sys_log` VALUES (32, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":31,\"parentId\":29,\"name\":\"文章列表\",\"component\":\"example/list\",\"type\":1,\"icon\":\"list\",\"orderNum\":1,\"list\":[],\"children\":[]}]', 3, '127.0.0.1', '2021-12-29 22:15:09');
INSERT INTO `sys_log` VALUES (33, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":30,\"parentId\":29,\"name\":\"创建文章\",\"component\":\"example/create\",\"type\":1,\"icon\":\"list\",\"orderNum\":1,\"list\":[],\"children\":[]}]', 4, '127.0.0.1', '2021-12-29 22:15:18');
INSERT INTO `sys_log` VALUES (34, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[31]', 0, '127.0.0.1', '2021-12-29 22:26:01');
INSERT INTO `sys_log` VALUES (35, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[31]', 21, '127.0.0.1', '2021-12-30 22:22:59');
INSERT INTO `sys_log` VALUES (36, 'admin', '暂停定时任务', 'com.element.modules.job.controller.ScheduleJobController.pause()', '[[1]]', 28, '127.0.0.1', '2021-12-30 22:23:40');
INSERT INTO `sys_log` VALUES (37, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":29,\"parentId\":0,\"name\":\"示例页面\",\"path\":\"example\",\"type\":0,\"icon\":\"education\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 10, '127.0.0.1', '2021-12-30 22:24:36');
INSERT INTO `sys_log` VALUES (38, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"path\":\"sys\",\"type\":0,\"icon\":\"example\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 10, '127.0.0.1', '2021-12-30 22:25:08');
INSERT INTO `sys_log` VALUES (39, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":29,\"parentId\":0,\"name\":\"示例页面\",\"path\":\"example\",\"type\":0,\"icon\":\"example\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 5, '127.0.0.1', '2021-12-30 22:25:21');
INSERT INTO `sys_log` VALUES (40, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":30,\"parentId\":29,\"name\":\"创建文章\",\"path\":\"create\",\"component\":\"example/create\",\"type\":1,\"icon\":\"education\",\"orderNum\":1,\"list\":[],\"children\":[]}]', 12, '127.0.0.1', '2021-12-30 22:25:36');
INSERT INTO `sys_log` VALUES (41, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":1,\"parentId\":0,\"name\":\"系统管理\",\"path\":\"sys\",\"type\":0,\"icon\":\"component\",\"orderNum\":0,\"list\":[],\"children\":[]}]', 10, '127.0.0.1', '2021-12-30 22:26:07');
INSERT INTO `sys_log` VALUES (42, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":2,\"parentId\":1,\"name\":\"用户列表\",\"path\":\"user\",\"component\":\"modules/sys/user\",\"type\":1,\"icon\":\"peoples\",\"orderNum\":1,\"list\":[],\"children\":[]}]', 11, '127.0.0.1', '2021-12-30 22:26:25');
INSERT INTO `sys_log` VALUES (43, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":3,\"parentId\":1,\"name\":\"角色管理\",\"path\":\"role\",\"component\":\"modules/sys/role\",\"type\":1,\"icon\":\"people\",\"orderNum\":2,\"list\":[],\"children\":[]}]', 12, '127.0.0.1', '2021-12-30 22:26:41');
INSERT INTO `sys_log` VALUES (44, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":27,\"parentId\":1,\"name\":\"系统日志\",\"path\":\"log\",\"component\":\"modules/sys/log\",\"perms\":\"sys:log:list\",\"type\":1,\"icon\":\"documentation\",\"orderNum\":6,\"list\":[],\"children\":[]}]', 12, '127.0.0.1', '2021-12-30 22:27:10');
INSERT INTO `sys_log` VALUES (45, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":5,\"parentId\":1,\"name\":\"参数管理\",\"path\":\"config\",\"component\":\"modules/sys/config\",\"perms\":\"sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete\",\"type\":1,\"icon\":\"edit\",\"orderNum\":4,\"list\":[],\"children\":[]}]', 12, '127.0.0.1', '2021-12-30 22:27:29');
INSERT INTO `sys_log` VALUES (46, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":6,\"parentId\":1,\"name\":\"定时任务\",\"path\":\"job\",\"component\":\"modules/job/schedule\",\"type\":1,\"icon\":\"clipboard\",\"orderNum\":5,\"list\":[],\"children\":[]}]', 5, '127.0.0.1', '2021-12-30 22:28:48');
INSERT INTO `sys_log` VALUES (47, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":3,\"parentId\":1,\"name\":\"角色管理\",\"path\":\"role\",\"component\":\"modules/sys/role\",\"type\":1,\"icon\":\"user\",\"orderNum\":2,\"list\":[],\"children\":[]}]', 12, '127.0.0.1', '2021-12-30 22:29:02');
INSERT INTO `sys_log` VALUES (48, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"menuId\":28,\"parentId\":1,\"name\":\"系统图标\",\"path\":\"icons\",\"component\":\"icons/index\",\"type\":1,\"icon\":\"star\",\"orderNum\":7,\"list\":[],\"children\":[]}]', 12, '127.0.0.1', '2021-12-30 22:29:52');
INSERT INTO `sys_log` VALUES (49, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[6,7,8,9,10,11,12,13,14,28,29,30,-666666,1],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 78, '127.0.0.1', '2022-01-06 11:10:43');
INSERT INTO `sys_log` VALUES (50, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[2],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 26, '127.0.0.1', '2022-01-06 11:10:56');
INSERT INTO `sys_log` VALUES (51, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"icon\":\"order-setting\",\"menuId\":1,\"name\":\"系统管理\",\"orderNum\":0,\"parentId\":0,\"path\":\"sys\",\"type\":0}]', 8, '0:0:0:0:0:0:0:1', '2022-01-07 13:51:13');
INSERT INTO `sys_log` VALUES (52, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/sys/user\",\"icon\":\"ums-admin\",\"menuId\":2,\"name\":\"用户列表\",\"orderNum\":1,\"parentId\":1,\"path\":\"user\",\"type\":1}]', 6, '0:0:0:0:0:0:0:1', '2022-01-07 14:05:01');
INSERT INTO `sys_log` VALUES (53, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/sys/role\",\"icon\":\"ums-resource\",\"menuId\":3,\"name\":\"角色管理\",\"orderNum\":2,\"parentId\":1,\"path\":\"role\",\"type\":1}]', 5, '0:0:0:0:0:0:0:1', '2022-01-07 14:05:19');
INSERT INTO `sys_log` VALUES (54, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/sys/role\",\"icon\":\"example\",\"menuId\":3,\"name\":\"角色管理\",\"orderNum\":2,\"parentId\":1,\"path\":\"role\",\"type\":1}]', 4, '0:0:0:0:0:0:0:1', '2022-01-07 14:05:42');
INSERT INTO `sys_log` VALUES (55, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/sys/menu\",\"icon\":\"product-list\",\"menuId\":4,\"name\":\"菜单管理\",\"orderNum\":3,\"parentId\":1,\"path\":\"menu\",\"type\":1}]', 4, '0:0:0:0:0:0:0:1', '2022-01-07 14:06:04');
INSERT INTO `sys_log` VALUES (56, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/example/create\",\"icon\":\"order\",\"menuId\":30,\"name\":\"创建文章\",\"orderNum\":1,\"parentId\":29,\"path\":\"create\",\"type\":1}]', 3, '0:0:0:0:0:0:0:1', '2022-01-07 14:08:36');
INSERT INTO `sys_log` VALUES (57, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/sys/config\",\"icon\":\"order-return-reason\",\"menuId\":5,\"name\":\"参数管理\",\"orderNum\":4,\"parentId\":1,\"path\":\"config\",\"perms\":\"sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete\",\"type\":1}]', 4, '0:0:0:0:0:0:0:1', '2022-01-07 14:09:02');
INSERT INTO `sys_log` VALUES (58, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/job/schedule\",\"icon\":\"sms-flash\",\"menuId\":6,\"name\":\"定时任务\",\"orderNum\":5,\"parentId\":1,\"path\":\"job\",\"type\":1}]', 4, '0:0:0:0:0:0:0:1', '2022-01-07 14:09:23');
INSERT INTO `sys_log` VALUES (59, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"modules/sys/log\",\"icon\":\"ums-menu\",\"menuId\":27,\"name\":\"系统日志\",\"orderNum\":6,\"parentId\":1,\"path\":\"log\",\"perms\":\"sys:log:list\",\"type\":1}]', 4, '0:0:0:0:0:0:0:1', '2022-01-07 14:09:35');
INSERT INTO `sys_log` VALUES (60, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"component\":\"icons/index\",\"icon\":\"sms-new\",\"menuId\":28,\"name\":\"系统图标\",\"orderNum\":7,\"parentId\":1,\"path\":\"icons\",\"type\":1}]', 4, '0:0:0:0:0:0:0:1', '2022-01-07 14:09:46');
INSERT INTO `sys_log` VALUES (61, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"123456@qq.com\",\"mobile\":\"13612345678\",\"roleIdList\":[],\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"userId\":1,\"username\":\"admin\"}]', 109, '0:0:0:0:0:0:0:1', '2022-01-07 22:14:26');
INSERT INTO `sys_log` VALUES (62, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[2],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 11, '0:0:0:0:0:0:0:1', '2022-01-07 22:14:29');
INSERT INTO `sys_log` VALUES (63, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[2],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 15, '0:0:0:0:0:0:0:1', '2022-01-07 22:14:38');
INSERT INTO `sys_log` VALUES (64, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[7,8,9,10,12,13,14,28,29,30,-666666,1,6],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 118, '0:0:0:0:0:0:0:1', '2022-01-08 12:53:03');
INSERT INTO `sys_log` VALUES (65, 'test', '立即执行任务', 'com.element.modules.job.controller.ScheduleJobController.run()', '[[1]]', 26, '0:0:0:0:0:0:0:1', '2022-01-08 12:53:44');
INSERT INTO `sys_log` VALUES (66, 'admin', '立即执行任务', 'com.element.modules.job.controller.ScheduleJobController.run()', '[[1]]', 12, '0:0:0:0:0:0:0:1', '2022-01-14 23:01:17');
INSERT INTO `sys_log` VALUES (67, 'admin', '恢复定时任务', 'com.element.modules.job.controller.ScheduleJobController.resume()', '[[1]]', 10, '0:0:0:0:0:0:0:1', '2022-01-14 23:01:23');
INSERT INTO `sys_log` VALUES (68, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"icon\":\"order-setting\",\"menuId\":1,\"name\":\"系统管理\",\"orderNum\":0,\"parentId\":0,\"path\":\"sys\",\"type\":0}]', 6, '0:0:0:0:0:0:0:1', '2022-01-20 17:34:51');
INSERT INTO `sys_log` VALUES (69, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[11]', 1, '0:0:0:0:0:0:0:1', '2022-01-20 17:46:46');
INSERT INTO `sys_log` VALUES (70, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[12]', 0, '0:0:0:0:0:0:0:1', '2022-01-20 17:46:50');
INSERT INTO `sys_log` VALUES (71, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[13]', 0, '0:0:0:0:0:0:0:1', '2022-01-20 17:46:53');
INSERT INTO `sys_log` VALUES (72, 'admin', '删除菜单', 'com.element.modules.sys.controller.SysMenuController.delete()', '[13]', 0, '0:0:0:0:0:0:0:1', '2022-01-20 17:46:56');
INSERT INTO `sys_log` VALUES (73, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[29,30,-666666],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 59, '0:0:0:0:0:0:0:1', '2022-01-20 17:47:28');
INSERT INTO `sys_log` VALUES (74, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[-666666],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 12, '0:0:0:0:0:0:0:1', '2022-01-20 17:49:37');
INSERT INTO `sys_log` VALUES (75, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 20, '0:0:0:0:0:0:0:1', '2022-01-20 17:50:54');
INSERT INTO `sys_log` VALUES (76, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[27,28,-666666],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 58, '0:0:0:0:0:0:0:1', '2022-01-20 17:58:47');
INSERT INTO `sys_log` VALUES (77, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[-666666],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 5, '0:0:0:0:0:0:0:1', '2022-01-20 17:59:58');
INSERT INTO `sys_log` VALUES (78, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[27,28,-666666],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 8, '0:0:0:0:0:0:0:1', '2022-01-20 18:00:35');
INSERT INTO `sys_log` VALUES (79, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[27,28],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 6, '0:0:0:0:0:0:0:1', '2022-01-20 18:15:00');
INSERT INTO `sys_log` VALUES (80, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[6,7,8,9,11,12,27,28],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 11, '0:0:0:0:0:0:0:1', '2022-01-20 18:15:45');
INSERT INTO `sys_log` VALUES (81, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[2],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 20, '0:0:0:0:0:0:0:1', '2022-01-20 18:16:30');
INSERT INTO `sys_log` VALUES (82, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[6,7,8,9,11,12,27,28,-666666,1],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 11, '0:0:0:0:0:0:0:1', '2022-01-20 18:20:21');
INSERT INTO `sys_log` VALUES (83, 'admin', '保存角色', 'com.element.modules.sys.controller.SysRoleController.save()', '[{\"createTime\":1642674134398,\"createUserId\":1,\"menuIdList\":[25,26,27,28,-666666,1],\"remark\":\"管理员\",\"roleId\":3,\"roleName\":\"admin\"}]', 9, '0:0:0:0:0:0:0:1', '2022-01-20 18:22:14');
INSERT INTO `sys_log` VALUES (84, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[25,26,27,28,-666666,1],\"remark\":\"管理员\",\"roleId\":3,\"roleName\":\"管理员\"}]', 9, '0:0:0:0:0:0:0:1', '2022-01-20 18:22:40');
INSERT INTO `sys_log` VALUES (85, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[3],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 5, '0:0:0:0:0:0:0:1', '2022-01-20 18:22:54');
INSERT INTO `sys_log` VALUES (86, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"12345@qq.com\",\"mobile\":\"18877514905\",\"roleIdList\":[2],\"salt\":\"7JqTzvcHvFns2Teglr6f\",\"status\":1,\"userId\":3,\"username\":\"test\"}]', 4, '0:0:0:0:0:0:0:1', '2022-01-20 18:23:56');
INSERT INTO `sys_log` VALUES (87, 'admin', '修改用户', 'com.element.modules.sys.controller.SysUserController.update()', '[{\"createUserId\":1,\"email\":\"123456@qq.com\",\"mobile\":\"13612345678\",\"roleIdList\":[],\"salt\":\"YzcmCZNvbXocrsz9dm8e\",\"status\":1,\"userId\":1,\"username\":\"admin\"}]', 9, '0:0:0:0:0:0:0:1', '2022-01-20 20:47:58');
INSERT INTO `sys_log` VALUES (88, 'admin', '修改角色', 'com.element.modules.sys.controller.SysRoleController.update()', '[{\"createUserId\":1,\"menuIdList\":[7,9,12,27,28,-666666,1,6],\"remark\":\"测试用户\",\"roleId\":2,\"roleName\":\"测试用户\"}]', 111, '0:0:0:0:0:0:0:1', '2022-01-25 14:21:16');
INSERT INTO `sys_log` VALUES (89, 'test', '修改定时任务', 'com.element.modules.job.controller.ScheduleJobController.update()', '[{\"beanName\":\"testTask\",\"cronExpression\":\"0 0/30 * * * ?\",\"jobId\":1,\"params\":\"test\",\"remark\":\"参数测试\",\"status\":0}]', 45, '0:0:0:0:0:0:0:1', '2022-01-25 14:27:36');
INSERT INTO `sys_log` VALUES (90, 'admin', '修改菜单', 'com.element.modules.sys.controller.SysMenuController.update()', '[{\"children\":[],\"icon\":\"example\",\"menuId\":27,\"name\":\"示例页面\",\"orderNum\":1,\"parentId\":0,\"path\":\"example\",\"type\":0}]', 20, '0:0:0:0:0:0:0:1', '2022-01-25 14:41:15');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单路由',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单组件路径',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '显示顺序',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单状态（0显示 1隐藏）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', 'sys', NULL, NULL, 0, 'order-setting', 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (2, 1, '用户列表', 'user', NULL, 'modules/sys/user', 1, 'ums-admin', 1, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (3, 1, '角色管理', 'role', NULL, 'modules/sys/role', 1, 'example', 2, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (4, 1, '菜单管理', 'menu', NULL, 'modules/sys/menu', 1, 'product-list', 3, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (5, 1, '参数管理', 'config', 'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 'modules/sys/config', 1, 'order-return-reason', 4, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (6, 1, '定时任务', 'job', NULL, 'modules/job/schedule', 1, 'sms-flash', 5, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (8, 6, '新增', NULL, 'sys:schedule:save', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (9, 6, '修改', NULL, 'sys:schedule:update', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (11, 6, '删除', NULL, 'sys:schedule:delete', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (12, 6, '日志列表', NULL, 'sys:schedule:log', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (13, 2, '查看', NULL, 'sys:user:list,sys:user:info', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (14, 2, '新增', NULL, 'sys:user:save,sys:role:select', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (15, 2, '修改', NULL, 'sys:user:update,sys:role:select', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (16, 2, '删除', NULL, 'sys:user:delete', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (17, 3, '查看', NULL, 'sys:role:list,sys:role:info', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (18, 3, '新增', NULL, 'sys:role:save,sys:menu:list', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (19, 3, '修改', NULL, 'sys:role:update,sys:menu:list', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (20, 3, '删除', NULL, 'sys:role:delete', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (21, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (22, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (23, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (24, 4, '删除', NULL, 'sys:menu:delete', NULL, 2, NULL, 0, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (25, 1, '系统日志', 'log', 'sys:log:list', 'modules/sys/log', 1, 'ums-menu', 6, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (26, 1, '系统图标', 'icons', NULL, 'icons/index', 1, 'sms-new', 7, 1, NULL, '2021-12-28 17:32:07', '2021-12-28 17:32:07');
INSERT INTO `sys_menu` VALUES (27, 0, '示例页面', 'example', NULL, NULL, 0, 'example', 1, 1, NULL, '2021-12-28 17:35:38', '2021-12-28 17:35:40');
INSERT INTO `sys_menu` VALUES (28, 27, '创建文章', 'create', NULL, 'modules/example/create', 1, 'order', 1, 1, NULL, '2021-12-28 17:37:03', '2021-12-28 17:37:07');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, '测试用户', '测试用户', 1, '2021-12-29 21:18:09');
INSERT INTO `sys_role` VALUES (3, '管理员', '管理员', 1, '2022-01-20 18:22:14');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (17, 3, 25);
INSERT INTO `sys_role_menu` VALUES (18, 3, 26);
INSERT INTO `sys_role_menu` VALUES (19, 3, 27);
INSERT INTO `sys_role_menu` VALUES (20, 3, 28);
INSERT INTO `sys_role_menu` VALUES (21, 3, -666666);
INSERT INTO `sys_role_menu` VALUES (22, 3, 1);
INSERT INTO `sys_role_menu` VALUES (23, 2, 7);
INSERT INTO `sys_role_menu` VALUES (24, 2, 9);
INSERT INTO `sys_role_menu` VALUES (25, 2, 12);
INSERT INTO `sys_role_menu` VALUES (26, 2, 27);
INSERT INTO `sys_role_menu` VALUES (27, 2, 28);
INSERT INTO `sys_role_menu` VALUES (28, 2, -666666);
INSERT INTO `sys_role_menu` VALUES (29, 2, 1);
INSERT INTO `sys_role_menu` VALUES (30, 2, 6);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '0445e39c6be4b7f7aa7aa8445c217a6dd78e48e84b2898fa95a09a2f409a0e1a', 'YzcmCZNvbXocrsz9dm8e', '123456@qq.com', '13612345678', 1, 1, '2021-11-21 11:11:11');
INSERT INTO `sys_user` VALUES (3, 'test', '91d3fa823ffd7cb56776db61d3827d3210f43d235613d884481d74355fc220f3', '7JqTzvcHvFns2Teglr6f', '12345@qq.com', '18877514905', 1, 1, '2021-12-29 16:41:15');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (3, 3, 2);

-- ----------------------------
-- Table structure for sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token`  (
  `user_id` bigint(20) NOT NULL,
  `token` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_token
-- ----------------------------
INSERT INTO `sys_user_token` VALUES (1, '59d6da61b3f13e908936173ef862652c', '2022-01-26 02:40:29', '2022-01-25 14:40:29');
INSERT INTO `sys_user_token` VALUES (3, 'b7d2c83f79dbdf0650f6a695d1a5a3f2', '2022-01-26 02:41:31', '2022-01-25 14:41:31');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'jack', '18812345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2021-11-21 22:37:41');

SET FOREIGN_KEY_CHECKS = 1;
