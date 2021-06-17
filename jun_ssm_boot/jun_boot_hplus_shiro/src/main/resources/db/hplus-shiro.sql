/*
 Navicat Premium Data Transfer

 Source Server         : localhost1
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : hplus-shiro

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 28/10/2020 16:19:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `blob_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `calendar` blob NOT NULL,
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'TASK_CLASS_NAME1000000424164348', 'DEFAULT', '0/30 * * * * ?', 'GMT+08:00');
INSERT INTO `qrtz_cron_triggers` VALUES ('quartzScheduler', 'TASK_CLASS_NAME1000001839343040', 'DEFAULT', '0/30 * * * * ?', 'GMT+08:00');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fired_time` bigint(13) NOT NULL,
  `sched_time` bigint(13) NOT NULL,
  `priority` int(11) NOT NULL,
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'TASK_CLASS_NAME1000000424164348', 'DEFAULT', NULL, 'com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720024636F6D2E6E62636C6173732E6D6F64756C65732E6A6F622E6D6F64656C2E5379734A6F62000000000000000102000B4C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000672656D61726B71007E00094C000673746174757371007E00094C000A75706461746554696D6571007E000A7870740001317372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000171105502F87874000E302F3330202A202A202A202A203F740037636F6D2E6E62636C6173732E6A6F622E7461736B2E63616E63656C4F726465722E63616E63656C2827E58F96E6B688E6938DE4BD9C272974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000038D7EBE0EBBFC740011436C617373E8BF90E8A18CE7A4BAE4BE8B74000132740011436C617373E8BF90E8A18CE7A4BAE4BE8B740001317371007E000E770800000173EB0BF058787800);
INSERT INTO `qrtz_job_details` VALUES ('quartzScheduler', 'TASK_CLASS_NAME1000001839343040', 'DEFAULT', NULL, 'com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000F5441534B5F50524F5045525449455373720024636F6D2E6E62636C6173732E6D6F64756C65732E6A6F622E6D6F64656C2E5379734A6F62000000000000000102000B4C000A636F6E63757272656E747400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C000C696E766F6B6554617267657471007E00094C00086A6F6247726F757071007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C00076A6F624E616D6571007E00094C000D6D697366697265506F6C69637971007E00094C000672656D61726B71007E00094C000673746174757371007E00094C000A75706461746554696D6571007E000A7870740001317372000E6A6176612E7574696C2E44617465686A81014B5974190300007870770800000171103438707874000E302F3330202A202A202A202A203F7400196A6F625461736B546573742E6A6F624E6F506172616D73282974000744454641554C547372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000038D7F1268A5C07400104265616EE8BF90E8A18CE7A4BAE4BE8B740001327400104265616EE8BF90E8A18CE7A4BAE4BE8B740001317371007E000E770800000173E7118198787800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_checkin_time` bigint(13) NOT NULL,
  `checkin_interval` bigint(13) NOT NULL,
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('quartzScheduler', 'MS-20180212JYON1603872890217', 1603873182120, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `repeat_count` bigint(7) NOT NULL,
  `repeat_interval` bigint(12) NOT NULL,
  `times_triggered` bigint(10) NOT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `int_prop_1` int(11) NULL DEFAULT NULL,
  `int_prop_2` int(11) NULL DEFAULT NULL,
  `long_prop_1` bigint(20) NULL DEFAULT NULL,
  `long_prop_2` bigint(20) NULL DEFAULT NULL,
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL,
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL,
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `next_fire_time` bigint(13) NULL DEFAULT NULL,
  `prev_fire_time` bigint(13) NULL DEFAULT NULL,
  `priority` int(11) NULL DEFAULT NULL,
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` bigint(13) NOT NULL,
  `end_time` bigint(13) NULL DEFAULT NULL,
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `misfire_instr` smallint(2) NULL DEFAULT NULL,
  `job_data` blob NULL,
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'TASK_CLASS_NAME1000000424164348', 'DEFAULT', 'TASK_CLASS_NAME1000000424164348', 'DEFAULT', NULL, 1603872900000, -1, 5, 'PAUSED', 'CRON', 1603872891000, 0, NULL, 1, '');
INSERT INTO `qrtz_triggers` VALUES ('quartzScheduler', 'TASK_CLASS_NAME1000001839343040', 'DEFAULT', 'TASK_CLASS_NAME1000001839343040', 'DEFAULT', NULL, 1603872900000, -1, 5, 'PAUSED', 'CRON', 1603872891000, 0, NULL, 1, '');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'key',
  `sys_value` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'value',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态   1-有效，0-无效',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key`(`sys_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000000321617343 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1000000321617342, 'CONFIG_STORAGE', '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"localDomain\":\"http://localhost:8081\",\"localPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudRegion\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"hongtech-oss\",\"qiniuDomain\":\"http://test.oss.hongtech.io\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"setFlag\":1,\"type\":0}', '文件存储配置', 1, '2020-09-27 10:58:33', '2020-10-28 16:19:28');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `file_id` bigint(20) NOT NULL COMMENT '文件id',
  `original_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始文件名称',
  `file_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_size` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '友链图片地址',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件相对路径',
  `file_full_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件绝对路径',
  `file_hash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oss_type` tinyint(1) NULL DEFAULT NULL COMMENT 'oss存储类型',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1000000424164348, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', '0/30 * * * * ?', '2', '1', '1', '2020-03-25 14:15:39', '2020-08-14 11:38:15', 'Class运行示例');
INSERT INTO `sys_job` VALUES (1000001839343040, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', '0/30 * * * * ?', '2', '1', '1', '2020-03-25 13:39:50', '2020-08-13 17:05:51', 'Bean运行示例');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------
INSERT INTO `sys_job_log` VALUES (1000000002594739, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:28:30');
INSERT INTO `sys_job_log` VALUES (1000000008151120, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:17:09');
INSERT INTO `sys_job_log` VALUES (1000000015158356, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:19:30');
INSERT INTO `sys_job_log` VALUES (1000000017185283, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:28:00');
INSERT INTO `sys_job_log` VALUES (1000000022643720, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:52:00');
INSERT INTO `sys_job_log` VALUES (1000000027070459, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:52:00');
INSERT INTO `sys_job_log` VALUES (1000000030922849, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:25:30');
INSERT INTO `sys_job_log` VALUES (1000000033541917, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:34:30');
INSERT INTO `sys_job_log` VALUES (1000000035240143, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:55:30');
INSERT INTO `sys_job_log` VALUES (1000000035613907, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:58:00');
INSERT INTO `sys_job_log` VALUES (1000000036055849, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:47:00');
INSERT INTO `sys_job_log` VALUES (1000000036824706, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:17:00');
INSERT INTO `sys_job_log` VALUES (1000000040462496, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:43:30');
INSERT INTO `sys_job_log` VALUES (1000000041375059, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:13');
INSERT INTO `sys_job_log` VALUES (1000000043610950, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:40:30');
INSERT INTO `sys_job_log` VALUES (1000000046241292, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:49:00');
INSERT INTO `sys_job_log` VALUES (1000000052600742, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:13:30');
INSERT INTO `sys_job_log` VALUES (1000000054926146, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:21:30');
INSERT INTO `sys_job_log` VALUES (1000000059678053, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:00:00');
INSERT INTO `sys_job_log` VALUES (1000000064201543, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:23:30');
INSERT INTO `sys_job_log` VALUES (1000000066883618, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:32:00');
INSERT INTO `sys_job_log` VALUES (1000000068478266, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:47:00');
INSERT INTO `sys_job_log` VALUES (1000000070064425, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:14:00');
INSERT INTO `sys_job_log` VALUES (1000000072972256, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:17:30');
INSERT INTO `sys_job_log` VALUES (1000000075305280, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:30:00');
INSERT INTO `sys_job_log` VALUES (1000000081612038, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:25:00');
INSERT INTO `sys_job_log` VALUES (1000000082645879, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:31:30');
INSERT INTO `sys_job_log` VALUES (1000000083394743, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:17:30');
INSERT INTO `sys_job_log` VALUES (1000000084300845, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:06:00');
INSERT INTO `sys_job_log` VALUES (1000000086223021, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:00:00');
INSERT INTO `sys_job_log` VALUES (1000000087263512, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:01:00');
INSERT INTO `sys_job_log` VALUES (1000000087777590, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:14:30');
INSERT INTO `sys_job_log` VALUES (1000000092957175, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:21:30');
INSERT INTO `sys_job_log` VALUES (1000000093277252, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:28:00');
INSERT INTO `sys_job_log` VALUES (1000000094837884, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:46:00');
INSERT INTO `sys_job_log` VALUES (1000000103829281, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:17:00');
INSERT INTO `sys_job_log` VALUES (1000000107768630, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:15:00');
INSERT INTO `sys_job_log` VALUES (1000000112225155, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-08-17 15:35:02');
INSERT INTO `sys_job_log` VALUES (1000000118731163, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:59:00');
INSERT INTO `sys_job_log` VALUES (1000000120568784, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:22:00');
INSERT INTO `sys_job_log` VALUES (1000000124397850, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:56:30');
INSERT INTO `sys_job_log` VALUES (1000000128733435, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:33:00');
INSERT INTO `sys_job_log` VALUES (1000000129216005, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:39:00');
INSERT INTO `sys_job_log` VALUES (1000000130872894, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:45:00');
INSERT INTO `sys_job_log` VALUES (1000000135365658, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:24:00');
INSERT INTO `sys_job_log` VALUES (1000000138321665, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:12:30');
INSERT INTO `sys_job_log` VALUES (1000000154398167, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:58:30');
INSERT INTO `sys_job_log` VALUES (1000000156951455, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:21:00');
INSERT INTO `sys_job_log` VALUES (1000000157180263, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:02:30');
INSERT INTO `sys_job_log` VALUES (1000000165212670, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:19:30');
INSERT INTO `sys_job_log` VALUES (1000000175204369, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:28:00');
INSERT INTO `sys_job_log` VALUES (1000000189041662, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:42:00');
INSERT INTO `sys_job_log` VALUES (1000000190271876, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:16:00');
INSERT INTO `sys_job_log` VALUES (1000000198830547, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:22:00');
INSERT INTO `sys_job_log` VALUES (1000000201620531, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:58:30');
INSERT INTO `sys_job_log` VALUES (1000000203067737, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:09:30');
INSERT INTO `sys_job_log` VALUES (1000000211731880, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:26:30');
INSERT INTO `sys_job_log` VALUES (1000000213593848, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:00:30');
INSERT INTO `sys_job_log` VALUES (1000000218088604, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:50:00');
INSERT INTO `sys_job_log` VALUES (1000000218522389, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:01:00');
INSERT INTO `sys_job_log` VALUES (1000000219680316, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:19:00');
INSERT INTO `sys_job_log` VALUES (1000000222161362, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:34:00');
INSERT INTO `sys_job_log` VALUES (1000000225742625, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:14:30');
INSERT INTO `sys_job_log` VALUES (1000000227624487, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-13 17:10:57');
INSERT INTO `sys_job_log` VALUES (1000000238217050, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:33:30');
INSERT INTO `sys_job_log` VALUES (1000000238786210, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:43:00');
INSERT INTO `sys_job_log` VALUES (1000000239360971, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:35:00');
INSERT INTO `sys_job_log` VALUES (1000000248304993, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:41:00');
INSERT INTO `sys_job_log` VALUES (1000000252782946, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:42:00');
INSERT INTO `sys_job_log` VALUES (1000000262233874, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:55:00');
INSERT INTO `sys_job_log` VALUES (1000000266808718, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：28毫秒', '0', NULL, '2020-03-26 14:00:30');
INSERT INTO `sys_job_log` VALUES (1000000274221510, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:55:00');
INSERT INTO `sys_job_log` VALUES (1000000275465808, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:04:30');
INSERT INTO `sys_job_log` VALUES (1000000285887208, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:22:30');
INSERT INTO `sys_job_log` VALUES (1000000286492802, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:13:00');
INSERT INTO `sys_job_log` VALUES (1000000288666421, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:36:00');
INSERT INTO `sys_job_log` VALUES (1000000297515718, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:29:30');
INSERT INTO `sys_job_log` VALUES (1000000298619204, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:19:00');
INSERT INTO `sys_job_log` VALUES (1000000300016369, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:06:30');
INSERT INTO `sys_job_log` VALUES (1000000301490370, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:21:00');
INSERT INTO `sys_job_log` VALUES (1000000302614949, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:10');
INSERT INTO `sys_job_log` VALUES (1000000303680037, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:33:00');
INSERT INTO `sys_job_log` VALUES (1000000311334032, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:32:30');
INSERT INTO `sys_job_log` VALUES (1000000317114266, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:44:00');
INSERT INTO `sys_job_log` VALUES (1000000318938518, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:52:00');
INSERT INTO `sys_job_log` VALUES (1000000325761879, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:04:00');
INSERT INTO `sys_job_log` VALUES (1000000331792255, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:18:00');
INSERT INTO `sys_job_log` VALUES (1000000342395840, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:58:30');
INSERT INTO `sys_job_log` VALUES (1000000344983711, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:28:00');
INSERT INTO `sys_job_log` VALUES (1000000367938986, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:53:30');
INSERT INTO `sys_job_log` VALUES (1000000377624871, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:59:30');
INSERT INTO `sys_job_log` VALUES (1000000378523031, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:34:00');
INSERT INTO `sys_job_log` VALUES (1000000379665501, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:40:00');
INSERT INTO `sys_job_log` VALUES (1000000381336359, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:13:30');
INSERT INTO `sys_job_log` VALUES (1000000383946743, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:12:00');
INSERT INTO `sys_job_log` VALUES (1000000384928490, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:24:00');
INSERT INTO `sys_job_log` VALUES (1000000388235919, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:50:30');
INSERT INTO `sys_job_log` VALUES (1000000394817737, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:25:00');
INSERT INTO `sys_job_log` VALUES (1000000396317514, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:40:00');
INSERT INTO `sys_job_log` VALUES (1000000397844243, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:07:00');
INSERT INTO `sys_job_log` VALUES (1000000400042908, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:00:30');
INSERT INTO `sys_job_log` VALUES (1000000401948250, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：2毫秒', '0', NULL, '2020-03-26 14:00:30');
INSERT INTO `sys_job_log` VALUES (1000000402229319, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:49:00');
INSERT INTO `sys_job_log` VALUES (1000000412624911, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:38:30');
INSERT INTO `sys_job_log` VALUES (1000000419691292, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:24:00');
INSERT INTO `sys_job_log` VALUES (1000000420768245, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:09:30');
INSERT INTO `sys_job_log` VALUES (1000000421519134, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:26:30');
INSERT INTO `sys_job_log` VALUES (1000000425823080, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:53:00');
INSERT INTO `sys_job_log` VALUES (1000000453405509, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:03:30');
INSERT INTO `sys_job_log` VALUES (1000000454433944, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:42:30');
INSERT INTO `sys_job_log` VALUES (1000000456306387, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:22:30');
INSERT INTO `sys_job_log` VALUES (1000000457806273, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:27:30');
INSERT INTO `sys_job_log` VALUES (1000000459866254, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:32:30');
INSERT INTO `sys_job_log` VALUES (1000000460634298, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:54:30');
INSERT INTO `sys_job_log` VALUES (1000000463576858, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:52:30');
INSERT INTO `sys_job_log` VALUES (1000000463768071, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:00:00');
INSERT INTO `sys_job_log` VALUES (1000000463830110, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:05:00');
INSERT INTO `sys_job_log` VALUES (1000000468013379, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:46:00');
INSERT INTO `sys_job_log` VALUES (1000000470007075, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:15:30');
INSERT INTO `sys_job_log` VALUES (1000000474881453, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:56:00');
INSERT INTO `sys_job_log` VALUES (1000000475686424, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:48:00');
INSERT INTO `sys_job_log` VALUES (1000000482352711, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:50:00');
INSERT INTO `sys_job_log` VALUES (1000000484383247, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:35:30');
INSERT INTO `sys_job_log` VALUES (1000000488571763, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:45:30');
INSERT INTO `sys_job_log` VALUES (1000000490117678, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:24:00');
INSERT INTO `sys_job_log` VALUES (1000000496564592, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:07:30');
INSERT INTO `sys_job_log` VALUES (1000000497339058, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:13');
INSERT INTO `sys_job_log` VALUES (1000000504049176, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:48:30');
INSERT INTO `sys_job_log` VALUES (1000000508324444, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:49:30');
INSERT INTO `sys_job_log` VALUES (1000000510313878, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:24:30');
INSERT INTO `sys_job_log` VALUES (1000000512580124, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:34:00');
INSERT INTO `sys_job_log` VALUES (1000000515390705, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:02:00');
INSERT INTO `sys_job_log` VALUES (1000000518477314, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:09:00');
INSERT INTO `sys_job_log` VALUES (1000000537193674, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:39:30');
INSERT INTO `sys_job_log` VALUES (1000000543800572, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:37:00');
INSERT INTO `sys_job_log` VALUES (1000000549020368, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:33:30');
INSERT INTO `sys_job_log` VALUES (1000000550179479, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:15:30');
INSERT INTO `sys_job_log` VALUES (1000000560213622, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:14:00');
INSERT INTO `sys_job_log` VALUES (1000000560340037, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:07:00');
INSERT INTO `sys_job_log` VALUES (1000000564369995, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:32:00');
INSERT INTO `sys_job_log` VALUES (1000000567905150, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:34:30');
INSERT INTO `sys_job_log` VALUES (1000000570177840, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:19:30');
INSERT INTO `sys_job_log` VALUES (1000000581023753, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:27:30');
INSERT INTO `sys_job_log` VALUES (1000000587577238, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:12:00');
INSERT INTO `sys_job_log` VALUES (1000000588973632, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:13:00');
INSERT INTO `sys_job_log` VALUES (1000000595154108, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：32毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-04-25 10:59:42');
INSERT INTO `sys_job_log` VALUES (1000000595176182, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:01:30');
INSERT INTO `sys_job_log` VALUES (1000000596587601, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:01:30');
INSERT INTO `sys_job_log` VALUES (1000000599900168, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:04:30');
INSERT INTO `sys_job_log` VALUES (1000000600592712, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:35:30');
INSERT INTO `sys_job_log` VALUES (1000000601808502, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:24:30');
INSERT INTO `sys_job_log` VALUES (1000000612814359, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:34:00');
INSERT INTO `sys_job_log` VALUES (1000000615848132, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:08:00');
INSERT INTO `sys_job_log` VALUES (1000000620643803, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:47:30');
INSERT INTO `sys_job_log` VALUES (1000000628548710, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:02:00');
INSERT INTO `sys_job_log` VALUES (1000000632315806, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:54:00');
INSERT INTO `sys_job_log` VALUES (1000000633093353, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:36:30');
INSERT INTO `sys_job_log` VALUES (1000000634658757, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:13:00');
INSERT INTO `sys_job_log` VALUES (1000000637232315, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:38:00');
INSERT INTO `sys_job_log` VALUES (1000000637911134, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:32:00');
INSERT INTO `sys_job_log` VALUES (1000000639937123, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:55:00');
INSERT INTO `sys_job_log` VALUES (1000000642201894, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:32:30');
INSERT INTO `sys_job_log` VALUES (1000000644195398, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:04:00');
INSERT INTO `sys_job_log` VALUES (1000000647184030, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:16:00');
INSERT INTO `sys_job_log` VALUES (1000000650575444, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:39:00');
INSERT INTO `sys_job_log` VALUES (1000000654030172, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:11:30');
INSERT INTO `sys_job_log` VALUES (1000000656769327, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:38:00');
INSERT INTO `sys_job_log` VALUES (1000000669294322, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:34:00');
INSERT INTO `sys_job_log` VALUES (1000000678429220, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:17:30');
INSERT INTO `sys_job_log` VALUES (1000000679710552, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:37:30');
INSERT INTO `sys_job_log` VALUES (1000000679773130, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:25:30');
INSERT INTO `sys_job_log` VALUES (1000000682372786, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:29:30');
INSERT INTO `sys_job_log` VALUES (1000000683547339, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:55:30');
INSERT INTO `sys_job_log` VALUES (1000000684994453, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:17:08');
INSERT INTO `sys_job_log` VALUES (1000000685360215, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:20:30');
INSERT INTO `sys_job_log` VALUES (1000000692628620, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:07:00');
INSERT INTO `sys_job_log` VALUES (1000000707802017, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:27:00');
INSERT INTO `sys_job_log` VALUES (1000000715572360, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:26:00');
INSERT INTO `sys_job_log` VALUES (1000000716546324, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:15:58');
INSERT INTO `sys_job_log` VALUES (1000000723796421, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:26:00');
INSERT INTO `sys_job_log` VALUES (1000000727010563, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:33:30');
INSERT INTO `sys_job_log` VALUES (1000000727654734, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:16:30');
INSERT INTO `sys_job_log` VALUES (1000000730817189, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:33:00');
INSERT INTO `sys_job_log` VALUES (1000000739023904, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:35:30');
INSERT INTO `sys_job_log` VALUES (1000000739417638, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:23:30');
INSERT INTO `sys_job_log` VALUES (1000000744324480, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:29:30');
INSERT INTO `sys_job_log` VALUES (1000000753403893, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:38:30');
INSERT INTO `sys_job_log` VALUES (1000000754308282, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:46:30');
INSERT INTO `sys_job_log` VALUES (1000000759763116, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:02:30');
INSERT INTO `sys_job_log` VALUES (1000000761253338, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:45:00');
INSERT INTO `sys_job_log` VALUES (1000000764070979, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:21:00');
INSERT INTO `sys_job_log` VALUES (1000000764483841, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:54:30');
INSERT INTO `sys_job_log` VALUES (1000000767459381, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:47:30');
INSERT INTO `sys_job_log` VALUES (1000000775315803, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:01:30');
INSERT INTO `sys_job_log` VALUES (1000000776887542, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:33:00');
INSERT INTO `sys_job_log` VALUES (1000000778706636, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:15:00');
INSERT INTO `sys_job_log` VALUES (1000000779198372, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:16:30');
INSERT INTO `sys_job_log` VALUES (1000000781163883, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:49:30');
INSERT INTO `sys_job_log` VALUES (1000000790230642, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:22:00');
INSERT INTO `sys_job_log` VALUES (1000000791398627, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:52:30');
INSERT INTO `sys_job_log` VALUES (1000000791530563, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:01:00');
INSERT INTO `sys_job_log` VALUES (1000000791617209, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:57:30');
INSERT INTO `sys_job_log` VALUES (1000000798034475, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:05:00');
INSERT INTO `sys_job_log` VALUES (1000000800793093, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:20:00');
INSERT INTO `sys_job_log` VALUES (1000000800818347, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:05:30');
INSERT INTO `sys_job_log` VALUES (1000000801186287, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:22:00');
INSERT INTO `sys_job_log` VALUES (1000000801884945, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:10:30');
INSERT INTO `sys_job_log` VALUES (1000000806554696, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:22:03');
INSERT INTO `sys_job_log` VALUES (1000000806783527, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:36:30');
INSERT INTO `sys_job_log` VALUES (1000000807153811, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:35:00');
INSERT INTO `sys_job_log` VALUES (1000000807941016, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:42:30');
INSERT INTO `sys_job_log` VALUES (1000000814146267, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：27毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-13 16:47:48');
INSERT INTO `sys_job_log` VALUES (1000000814675462, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:56:00');
INSERT INTO `sys_job_log` VALUES (1000000814930810, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:43:00');
INSERT INTO `sys_job_log` VALUES (1000000815209238, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:44:30');
INSERT INTO `sys_job_log` VALUES (1000000822663305, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:58:30');
INSERT INTO `sys_job_log` VALUES (1000000829355302, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:29:00');
INSERT INTO `sys_job_log` VALUES (1000000830990341, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:44:00');
INSERT INTO `sys_job_log` VALUES (1000000832895051, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:02:30');
INSERT INTO `sys_job_log` VALUES (1000000838318256, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:16:30');
INSERT INTO `sys_job_log` VALUES (1000000842113996, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:31:30');
INSERT INTO `sys_job_log` VALUES (1000000842151769, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:40:30');
INSERT INTO `sys_job_log` VALUES (1000000847340458, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:49:00');
INSERT INTO `sys_job_log` VALUES (1000000849793029, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:50:30');
INSERT INTO `sys_job_log` VALUES (1000000855303867, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:00:30');
INSERT INTO `sys_job_log` VALUES (1000000857321246, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:42:30');
INSERT INTO `sys_job_log` VALUES (1000000857572857, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:03:30');
INSERT INTO `sys_job_log` VALUES (1000000859930610, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：2毫秒', '0', NULL, '2020-03-26 12:03:00');
INSERT INTO `sys_job_log` VALUES (1000000864879344, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:06:30');
INSERT INTO `sys_job_log` VALUES (1000000867812703, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:51:00');
INSERT INTO `sys_job_log` VALUES (1000000874296056, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:01:00');
INSERT INTO `sys_job_log` VALUES (1000000878897111, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:25:30');
INSERT INTO `sys_job_log` VALUES (1000000884992444, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:56:30');
INSERT INTO `sys_job_log` VALUES (1000000892006045, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:04:00');
INSERT INTO `sys_job_log` VALUES (1000000892367494, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:33:00');
INSERT INTO `sys_job_log` VALUES (1000000892531896, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:30:30');
INSERT INTO `sys_job_log` VALUES (1000000905133483, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:23:00');
INSERT INTO `sys_job_log` VALUES (1000000909231305, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:40:00');
INSERT INTO `sys_job_log` VALUES (1000000911469080, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:40:30');
INSERT INTO `sys_job_log` VALUES (1000000919791951, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:24:30');
INSERT INTO `sys_job_log` VALUES (1000000920200712, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:12:00');
INSERT INTO `sys_job_log` VALUES (1000000927653745, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:51:00');
INSERT INTO `sys_job_log` VALUES (1000000935904091, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:06:00');
INSERT INTO `sys_job_log` VALUES (1000000936410988, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:24:00');
INSERT INTO `sys_job_log` VALUES (1000000937333930, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:02:00');
INSERT INTO `sys_job_log` VALUES (1000000940270571, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:31:30');
INSERT INTO `sys_job_log` VALUES (1000000940654868, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:33:30');
INSERT INTO `sys_job_log` VALUES (1000000941022182, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:08:30');
INSERT INTO `sys_job_log` VALUES (1000000948062723, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:11');
INSERT INTO `sys_job_log` VALUES (1000000949368295, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:53:30');
INSERT INTO `sys_job_log` VALUES (1000000950284728, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:14:30');
INSERT INTO `sys_job_log` VALUES (1000000950880311, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:06:00');
INSERT INTO `sys_job_log` VALUES (1000000952597857, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:10:00');
INSERT INTO `sys_job_log` VALUES (1000000955466135, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:48:00');
INSERT INTO `sys_job_log` VALUES (1000000960481146, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:09:30');
INSERT INTO `sys_job_log` VALUES (1000000960665393, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:35:00');
INSERT INTO `sys_job_log` VALUES (1000000964120352, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:47:00');
INSERT INTO `sys_job_log` VALUES (1000000967478703, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:38:00');
INSERT INTO `sys_job_log` VALUES (1000000977060788, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:54:30');
INSERT INTO `sys_job_log` VALUES (1000000989338840, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:19:30');
INSERT INTO `sys_job_log` VALUES (1000000992378959, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:39:00');
INSERT INTO `sys_job_log` VALUES (1000000993507703, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:37:30');
INSERT INTO `sys_job_log` VALUES (1000000999972564, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:27:00');
INSERT INTO `sys_job_log` VALUES (1000001000925736, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:35:00');
INSERT INTO `sys_job_log` VALUES (1000001005151121, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:37:00');
INSERT INTO `sys_job_log` VALUES (1000001006231386, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:57:00');
INSERT INTO `sys_job_log` VALUES (1000001007608120, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:54:00');
INSERT INTO `sys_job_log` VALUES (1000001008635326, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:00:30');
INSERT INTO `sys_job_log` VALUES (1000001008851712, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:11:30');
INSERT INTO `sys_job_log` VALUES (1000001014492526, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:10:00');
INSERT INTO `sys_job_log` VALUES (1000001026678277, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:46:30');
INSERT INTO `sys_job_log` VALUES (1000001031888947, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:18:30');
INSERT INTO `sys_job_log` VALUES (1000001034220820, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:34:00');
INSERT INTO `sys_job_log` VALUES (1000001035187824, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:09:30');
INSERT INTO `sys_job_log` VALUES (1000001035556961, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:40:00');
INSERT INTO `sys_job_log` VALUES (1000001039786996, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:26:00');
INSERT INTO `sys_job_log` VALUES (1000001039846012, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-08-14 11:37:30');
INSERT INTO `sys_job_log` VALUES (1000001044512441, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:18:00');
INSERT INTO `sys_job_log` VALUES (1000001047895359, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:38:00');
INSERT INTO `sys_job_log` VALUES (1000001055562542, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:59:00');
INSERT INTO `sys_job_log` VALUES (1000001056246307, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:05:00');
INSERT INTO `sys_job_log` VALUES (1000001070213539, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:12');
INSERT INTO `sys_job_log` VALUES (1000001072973280, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:17:00');
INSERT INTO `sys_job_log` VALUES (1000001091412336, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:41:30');
INSERT INTO `sys_job_log` VALUES (1000001093379419, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:10:30');
INSERT INTO `sys_job_log` VALUES (1000001094678591, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:57:00');
INSERT INTO `sys_job_log` VALUES (1000001098026730, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:53:00');
INSERT INTO `sys_job_log` VALUES (1000001098668859, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:49:00');
INSERT INTO `sys_job_log` VALUES (1000001109337165, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:11:00');
INSERT INTO `sys_job_log` VALUES (1000001114536396, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:09');
INSERT INTO `sys_job_log` VALUES (1000001119960754, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:27:00');
INSERT INTO `sys_job_log` VALUES (1000001120414693, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:48:00');
INSERT INTO `sys_job_log` VALUES (1000001129859039, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:27:00');
INSERT INTO `sys_job_log` VALUES (1000001130851109, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:19:00');
INSERT INTO `sys_job_log` VALUES (1000001131097001, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:37:00');
INSERT INTO `sys_job_log` VALUES (1000001133628475, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:16:30');
INSERT INTO `sys_job_log` VALUES (1000001141564988, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:07:30');
INSERT INTO `sys_job_log` VALUES (1000001141671000, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:52:30');
INSERT INTO `sys_job_log` VALUES (1000001146668962, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:45:30');
INSERT INTO `sys_job_log` VALUES (1000001146831444, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:07:00');
INSERT INTO `sys_job_log` VALUES (1000001150906226, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:18:30');
INSERT INTO `sys_job_log` VALUES (1000001152575308, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:11');
INSERT INTO `sys_job_log` VALUES (1000001154622922, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:56:30');
INSERT INTO `sys_job_log` VALUES (1000001155054307, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:08:00');
INSERT INTO `sys_job_log` VALUES (1000001158913741, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:53:00');
INSERT INTO `sys_job_log` VALUES (1000001176774089, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:10:30');
INSERT INTO `sys_job_log` VALUES (1000001184914293, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:01:30');
INSERT INTO `sys_job_log` VALUES (1000001195013421, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:28:30');
INSERT INTO `sys_job_log` VALUES (1000001202104997, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:26:30');
INSERT INTO `sys_job_log` VALUES (1000001210475291, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:05:30');
INSERT INTO `sys_job_log` VALUES (1000001211652447, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:43:00');
INSERT INTO `sys_job_log` VALUES (1000001213012950, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:30');
INSERT INTO `sys_job_log` VALUES (1000001218593565, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:19:00');
INSERT INTO `sys_job_log` VALUES (1000001219373287, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:09:00');
INSERT INTO `sys_job_log` VALUES (1000001223758999, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:52:00');
INSERT INTO `sys_job_log` VALUES (1000001224150963, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:02:00');
INSERT INTO `sys_job_log` VALUES (1000001229683422, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:23:00');
INSERT INTO `sys_job_log` VALUES (1000001243928153, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:12:30');
INSERT INTO `sys_job_log` VALUES (1000001244582949, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:45:30');
INSERT INTO `sys_job_log` VALUES (1000001248473434, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:20:30');
INSERT INTO `sys_job_log` VALUES (1000001249744016, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:36:30');
INSERT INTO `sys_job_log` VALUES (1000001261722143, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:31:00');
INSERT INTO `sys_job_log` VALUES (1000001263262613, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:56:00');
INSERT INTO `sys_job_log` VALUES (1000001265800201, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：2毫秒', '0', NULL, '2020-09-26 14:15:58');
INSERT INTO `sys_job_log` VALUES (1000001266108664, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:29:00');
INSERT INTO `sys_job_log` VALUES (1000001269589590, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:20:30');
INSERT INTO `sys_job_log` VALUES (1000001275687543, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:08:00');
INSERT INTO `sys_job_log` VALUES (1000001286206650, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:54:00');
INSERT INTO `sys_job_log` VALUES (1000001286665234, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:33:30');
INSERT INTO `sys_job_log` VALUES (1000001288755639, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:19:30');
INSERT INTO `sys_job_log` VALUES (1000001290735509, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:31:00');
INSERT INTO `sys_job_log` VALUES (1000001293666808, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:41:30');
INSERT INTO `sys_job_log` VALUES (1000001293931964, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:55:30');
INSERT INTO `sys_job_log` VALUES (1000001295457590, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:01:30');
INSERT INTO `sys_job_log` VALUES (1000001296210979, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:26:00');
INSERT INTO `sys_job_log` VALUES (1000001300522353, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:28:30');
INSERT INTO `sys_job_log` VALUES (1000001312201243, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:09:00');
INSERT INTO `sys_job_log` VALUES (1000001317117984, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：2毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-14 11:37:26');
INSERT INTO `sys_job_log` VALUES (1000001333991513, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：42毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-14 11:37:29');
INSERT INTO `sys_job_log` VALUES (1000001334907453, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:26:00');
INSERT INTO `sys_job_log` VALUES (1000001340003975, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:06:00');
INSERT INTO `sys_job_log` VALUES (1000001341485586, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:15:00');
INSERT INTO `sys_job_log` VALUES (1000001348833417, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:55:00');
INSERT INTO `sys_job_log` VALUES (1000001354716224, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:16:00');
INSERT INTO `sys_job_log` VALUES (1000001365125816, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:03:30');
INSERT INTO `sys_job_log` VALUES (1000001368538674, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:47:30');
INSERT INTO `sys_job_log` VALUES (1000001368792422, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:15:58');
INSERT INTO `sys_job_log` VALUES (1000001372335606, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:42:00');
INSERT INTO `sys_job_log` VALUES (1000001374901062, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:42:00');
INSERT INTO `sys_job_log` VALUES (1000001375505741, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:44:00');
INSERT INTO `sys_job_log` VALUES (1000001377923070, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:20:00');
INSERT INTO `sys_job_log` VALUES (1000001378993486, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:18:00');
INSERT INTO `sys_job_log` VALUES (1000001379254876, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:04:30');
INSERT INTO `sys_job_log` VALUES (1000001394529378, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:14:00');
INSERT INTO `sys_job_log` VALUES (1000001398911540, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:39:30');
INSERT INTO `sys_job_log` VALUES (1000001399209350, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:57:30');
INSERT INTO `sys_job_log` VALUES (1000001405055205, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:18:30');
INSERT INTO `sys_job_log` VALUES (1000001406134686, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:51:00');
INSERT INTO `sys_job_log` VALUES (1000001426794060, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:32:30');
INSERT INTO `sys_job_log` VALUES (1000001430107797, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:54:00');
INSERT INTO `sys_job_log` VALUES (1000001436807785, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：44毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:12:21');
INSERT INTO `sys_job_log` VALUES (1000001440345131, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:16:00');
INSERT INTO `sys_job_log` VALUES (1000001446792997, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:46:30');
INSERT INTO `sys_job_log` VALUES (1000001447902730, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:15:57');
INSERT INTO `sys_job_log` VALUES (1000001452862715, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:41:00');
INSERT INTO `sys_job_log` VALUES (1000001461771279, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:58:00');
INSERT INTO `sys_job_log` VALUES (1000001461872340, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:23:30');
INSERT INTO `sys_job_log` VALUES (1000001467084873, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:59:30');
INSERT INTO `sys_job_log` VALUES (1000001467482670, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:36:00');
INSERT INTO `sys_job_log` VALUES (1000001469753978, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:25:00');
INSERT INTO `sys_job_log` VALUES (1000001475695193, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:31:30');
INSERT INTO `sys_job_log` VALUES (1000001476582801, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:59:30');
INSERT INTO `sys_job_log` VALUES (1000001489147907, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:36:00');
INSERT INTO `sys_job_log` VALUES (1000001491675547, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:27:30');
INSERT INTO `sys_job_log` VALUES (1000001492263527, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:15:54');
INSERT INTO `sys_job_log` VALUES (1000001494319209, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:04:30');
INSERT INTO `sys_job_log` VALUES (1000001494874308, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:38:30');
INSERT INTO `sys_job_log` VALUES (1000001503318534, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:53:30');
INSERT INTO `sys_job_log` VALUES (1000001504601976, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:33:00');
INSERT INTO `sys_job_log` VALUES (1000001505661999, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:35:00');
INSERT INTO `sys_job_log` VALUES (1000001506471595, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:13:00');
INSERT INTO `sys_job_log` VALUES (1000001508041144, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：19毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-14 11:37:22');
INSERT INTO `sys_job_log` VALUES (1000001508838595, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:26:30');
INSERT INTO `sys_job_log` VALUES (1000001509752790, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:11:30');
INSERT INTO `sys_job_log` VALUES (1000001510979377, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:42:30');
INSERT INTO `sys_job_log` VALUES (1000001512513888, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:25:30');
INSERT INTO `sys_job_log` VALUES (1000001520432719, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:48:30');
INSERT INTO `sys_job_log` VALUES (1000001522053746, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:30:00');
INSERT INTO `sys_job_log` VALUES (1000001522546307, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:54:30');
INSERT INTO `sys_job_log` VALUES (1000001523359082, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:43:00');
INSERT INTO `sys_job_log` VALUES (1000001532972314, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:24:30');
INSERT INTO `sys_job_log` VALUES (1000001542957672, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:32:00');
INSERT INTO `sys_job_log` VALUES (1000001546464701, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:30:30');
INSERT INTO `sys_job_log` VALUES (1000001548101677, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:13:30');
INSERT INTO `sys_job_log` VALUES (1000001551149323, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:12:30');
INSERT INTO `sys_job_log` VALUES (1000001553193307, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:51:30');
INSERT INTO `sys_job_log` VALUES (1000001556496776, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:51:00');
INSERT INTO `sys_job_log` VALUES (1000001561086032, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:57:00');
INSERT INTO `sys_job_log` VALUES (1000001563811663, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:02:00');
INSERT INTO `sys_job_log` VALUES (1000001564349354, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:27:00');
INSERT INTO `sys_job_log` VALUES (1000001567031645, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:41:00');
INSERT INTO `sys_job_log` VALUES (1000001574441480, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：4毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:14:49');
INSERT INTO `sys_job_log` VALUES (1000001575816492, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:18:00');
INSERT INTO `sys_job_log` VALUES (1000001587473005, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:20:30');
INSERT INTO `sys_job_log` VALUES (1000001587536187, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:21:30');
INSERT INTO `sys_job_log` VALUES (1000001590091440, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:02:00');
INSERT INTO `sys_job_log` VALUES (1000001590925615, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:51:30');
INSERT INTO `sys_job_log` VALUES (1000001595068373, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:57:30');
INSERT INTO `sys_job_log` VALUES (1000001597464076, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:23:00');
INSERT INTO `sys_job_log` VALUES (1000001601004225, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:50:00');
INSERT INTO `sys_job_log` VALUES (1000001605566622, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:17:00');
INSERT INTO `sys_job_log` VALUES (1000001608823068, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:10:00');
INSERT INTO `sys_job_log` VALUES (1000001611250625, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:37:30');
INSERT INTO `sys_job_log` VALUES (1000001612684405, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:22:30');
INSERT INTO `sys_job_log` VALUES (1000001614048760, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:15:30');
INSERT INTO `sys_job_log` VALUES (1000001615407299, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 14:01:00');
INSERT INTO `sys_job_log` VALUES (1000001616105456, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:05:30');
INSERT INTO `sys_job_log` VALUES (1000001626058875, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:06:30');
INSERT INTO `sys_job_log` VALUES (1000001628215271, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:46:30');
INSERT INTO `sys_job_log` VALUES (1000001642709591, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:11:00');
INSERT INTO `sys_job_log` VALUES (1000001652239279, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:21:30');
INSERT INTO `sys_job_log` VALUES (1000001655339911, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:53:30');
INSERT INTO `sys_job_log` VALUES (1000001662490933, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:26:30');
INSERT INTO `sys_job_log` VALUES (1000001666675628, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:35:30');
INSERT INTO `sys_job_log` VALUES (1000001667261910, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:34:30');
INSERT INTO `sys_job_log` VALUES (1000001668540246, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:35:30');
INSERT INTO `sys_job_log` VALUES (1000001670828664, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:22:30');
INSERT INTO `sys_job_log` VALUES (1000001672018210, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:48:30');
INSERT INTO `sys_job_log` VALUES (1000001673951260, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:30:30');
INSERT INTO `sys_job_log` VALUES (1000001675024754, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:17:12');
INSERT INTO `sys_job_log` VALUES (1000001681078859, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：2毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-14 11:37:28');
INSERT INTO `sys_job_log` VALUES (1000001683430829, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:50:00');
INSERT INTO `sys_job_log` VALUES (1000001687095554, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:08:00');
INSERT INTO `sys_job_log` VALUES (1000001687407843, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:03:00');
INSERT INTO `sys_job_log` VALUES (1000001697238351, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:08:30');
INSERT INTO `sys_job_log` VALUES (1000001697521830, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:59:00');
INSERT INTO `sys_job_log` VALUES (1000001697600895, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:32:30');
INSERT INTO `sys_job_log` VALUES (1000001698620561, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:48:00');
INSERT INTO `sys_job_log` VALUES (1000001706325621, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:10:30');
INSERT INTO `sys_job_log` VALUES (1000001709244614, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:12');
INSERT INTO `sys_job_log` VALUES (1000001710843636, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:21:00');
INSERT INTO `sys_job_log` VALUES (1000001715263714, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:53:00');
INSERT INTO `sys_job_log` VALUES (1000001718655602, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:15:00');
INSERT INTO `sys_job_log` VALUES (1000001719157375, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:18:30');
INSERT INTO `sys_job_log` VALUES (1000001719316339, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:50:30');
INSERT INTO `sys_job_log` VALUES (1000001719444887, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:13:00');
INSERT INTO `sys_job_log` VALUES (1000001720847137, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:38:30');
INSERT INTO `sys_job_log` VALUES (1000001721787841, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:45:00');
INSERT INTO `sys_job_log` VALUES (1000001727794536, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:14:00');
INSERT INTO `sys_job_log` VALUES (1000001729852687, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:35:30');
INSERT INTO `sys_job_log` VALUES (1000001731246470, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-04-25 10:59:47');
INSERT INTO `sys_job_log` VALUES (1000001732858501, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:29:30');
INSERT INTO `sys_job_log` VALUES (1000001732924870, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:46:00');
INSERT INTO `sys_job_log` VALUES (1000001734426501, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:59:00');
INSERT INTO `sys_job_log` VALUES (1000001740469558, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:21:30');
INSERT INTO `sys_job_log` VALUES (1000001740820950, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:34:30');
INSERT INTO `sys_job_log` VALUES (1000001744732382, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:14:30');
INSERT INTO `sys_job_log` VALUES (1000001749930618, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:41:30');
INSERT INTO `sys_job_log` VALUES (1000001753068708, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:30:30');
INSERT INTO `sys_job_log` VALUES (1000001757832764, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:34:30');
INSERT INTO `sys_job_log` VALUES (1000001759073901, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:49:30');
INSERT INTO `sys_job_log` VALUES (1000001759486277, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:47:00');
INSERT INTO `sys_job_log` VALUES (1000001760854160, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:45:00');
INSERT INTO `sys_job_log` VALUES (1000001765272903, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:04:00');
INSERT INTO `sys_job_log` VALUES (1000001766592454, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:37:00');
INSERT INTO `sys_job_log` VALUES (1000001768010552, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:10:00');
INSERT INTO `sys_job_log` VALUES (1000001770287384, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:39:30');
INSERT INTO `sys_job_log` VALUES (1000001771661899, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:52:30');
INSERT INTO `sys_job_log` VALUES (1000001772573301, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:13:30');
INSERT INTO `sys_job_log` VALUES (1000001776951559, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:32:30');
INSERT INTO `sys_job_log` VALUES (1000001777351188, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:22:30');
INSERT INTO `sys_job_log` VALUES (1000001778008154, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:20:00');
INSERT INTO `sys_job_log` VALUES (1000001778958563, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:19:00');
INSERT INTO `sys_job_log` VALUES (1000001785227449, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:56:00');
INSERT INTO `sys_job_log` VALUES (1000001786204792, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:02:30');
INSERT INTO `sys_job_log` VALUES (1000001788727094, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:16:30');
INSERT INTO `sys_job_log` VALUES (1000001793562599, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:03:30');
INSERT INTO `sys_job_log` VALUES (1000001794213289, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:57:30');
INSERT INTO `sys_job_log` VALUES (1000001806928215, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:16:00');
INSERT INTO `sys_job_log` VALUES (1000001811972835, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:00:00');
INSERT INTO `sys_job_log` VALUES (1000001819137342, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:20:30');
INSERT INTO `sys_job_log` VALUES (1000001819802644, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:25:30');
INSERT INTO `sys_job_log` VALUES (1000001822769448, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:33:30');
INSERT INTO `sys_job_log` VALUES (1000001824219690, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:12:30');
INSERT INTO `sys_job_log` VALUES (1000001826887580, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:05:00');
INSERT INTO `sys_job_log` VALUES (1000001827022844, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:44:30');
INSERT INTO `sys_job_log` VALUES (1000001832301219, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:10');
INSERT INTO `sys_job_log` VALUES (1000001836363014, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:14:30');
INSERT INTO `sys_job_log` VALUES (1000001845318657, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:18:30');
INSERT INTO `sys_job_log` VALUES (1000001847503692, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:41:00');
INSERT INTO `sys_job_log` VALUES (1000001849461023, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:13:30');
INSERT INTO `sys_job_log` VALUES (1000001849848830, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:02:30');
INSERT INTO `sys_job_log` VALUES (1000001852684996, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：6毫秒', '0', NULL, '2020-03-26 11:39:00');
INSERT INTO `sys_job_log` VALUES (1000001855383822, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:30:00');
INSERT INTO `sys_job_log` VALUES (1000001855672218, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:08:30');
INSERT INTO `sys_job_log` VALUES (1000001857838765, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:57:00');
INSERT INTO `sys_job_log` VALUES (1000001862778828, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:06:30');
INSERT INTO `sys_job_log` VALUES (1000001869099140, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:41:30');
INSERT INTO `sys_job_log` VALUES (1000001873414273, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:27:30');
INSERT INTO `sys_job_log` VALUES (1000001880790791, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:23:30');
INSERT INTO `sys_job_log` VALUES (1000001881486033, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:23:00');
INSERT INTO `sys_job_log` VALUES (1000001886541461, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:21:00');
INSERT INTO `sys_job_log` VALUES (1000001890692348, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:17:30');
INSERT INTO `sys_job_log` VALUES (1000001894013885, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:15:30');
INSERT INTO `sys_job_log` VALUES (1000001901337997, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:20:00');
INSERT INTO `sys_job_log` VALUES (1000001901479466, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:43:30');
INSERT INTO `sys_job_log` VALUES (1000001902962895, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:51:30');
INSERT INTO `sys_job_log` VALUES (1000001905058665, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:12:00');
INSERT INTO `sys_job_log` VALUES (1000001907566350, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:31:00');
INSERT INTO `sys_job_log` VALUES (1000001916001785, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:23:00');
INSERT INTO `sys_job_log` VALUES (1000001916364874, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:24:30');
INSERT INTO `sys_job_log` VALUES (1000001920861754, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-09-26 14:17:10');
INSERT INTO `sys_job_log` VALUES (1000001927494154, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:08:30');
INSERT INTO `sys_job_log` VALUES (1000001928071607, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:28:30');
INSERT INTO `sys_job_log` VALUES (1000001935663698, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:07:30');
INSERT INTO `sys_job_log` VALUES (1000001936448645, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:03:00');
INSERT INTO `sys_job_log` VALUES (1000001936848918, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:56:30');
INSERT INTO `sys_job_log` VALUES (1000001951914302, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:35:00');
INSERT INTO `sys_job_log` VALUES (1000001958726045, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:11:00');
INSERT INTO `sys_job_log` VALUES (1000001959895664, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:55:30');
INSERT INTO `sys_job_log` VALUES (1000001960298474, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:30:00');
INSERT INTO `sys_job_log` VALUES (1000001962018676, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:11:30');
INSERT INTO `sys_job_log` VALUES (1000001964385653, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:12:30');
INSERT INTO `sys_job_log` VALUES (1000001967782821, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:01:00');
INSERT INTO `sys_job_log` VALUES (1000001969566015, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:29:00');
INSERT INTO `sys_job_log` VALUES (1000001978173339, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:40:30');
INSERT INTO `sys_job_log` VALUES (1000001978933719, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:59:30');
INSERT INTO `sys_job_log` VALUES (1000001981607012, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:34:30');
INSERT INTO `sys_job_log` VALUES (1000001984242014, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:15:59');
INSERT INTO `sys_job_log` VALUES (1000001988491232, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：48毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\r\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\r\n	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)\r\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\r\n	at java.lang.Class.forName0(Native Method)\r\n	at java.lang.Class.forName(Class.java:264)\r\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\r\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\r\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\r\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\r\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\r\n', '2020-08-17 15:35:01');
INSERT INTO `sys_job_log` VALUES (1000001995417783, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:39:30');
INSERT INTO `sys_job_log` VALUES (1000001995917005, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:58:00');
INSERT INTO `sys_job_log` VALUES (1000002002233848, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:36:30');
INSERT INTO `sys_job_log` VALUES (1000002010674181, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:44:30');
INSERT INTO `sys_job_log` VALUES (1000002021616908, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:58:00');
INSERT INTO `sys_job_log` VALUES (1000002024731136, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:43:30');
INSERT INTO `sys_job_log` VALUES (1000002025759159, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:11:00');
INSERT INTO `sys_job_log` VALUES (1000002026857519, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:31:00');
INSERT INTO `sys_job_log` VALUES (1000002030124669, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:50:30');
INSERT INTO `sys_job_log` VALUES (1000002036554863, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:36:00');
INSERT INTO `sys_job_log` VALUES (1000002037306067, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:15:30');
INSERT INTO `sys_job_log` VALUES (1000002039807090, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：2毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:14:00');
INSERT INTO `sys_job_log` VALUES (1000002044239146, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:18:00');
INSERT INTO `sys_job_log` VALUES (1000002048970098, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:44:00');
INSERT INTO `sys_job_log` VALUES (1000002057692442, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:45:30');
INSERT INTO `sys_job_log` VALUES (1000002061539997, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:01:30');
INSERT INTO `sys_job_log` VALUES (1000002063331102, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:29:00');
INSERT INTO `sys_job_log` VALUES (1000002066115401, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:25:00');
INSERT INTO `sys_job_log` VALUES (1000002068987800, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:03:00');
INSERT INTO `sys_job_log` VALUES (1000002077366401, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 13:23:30');
INSERT INTO `sys_job_log` VALUES (1000002081139959, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-09-26 14:17:00');
INSERT INTO `sys_job_log` VALUES (1000002082666332, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:09:00');
INSERT INTO `sys_job_log` VALUES (1000002085645064, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:44:30');
INSERT INTO `sys_job_log` VALUES (1000002087096142, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 12:47:30');
INSERT INTO `sys_job_log` VALUES (1000002090153747, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 11:37:30');
INSERT INTO `sys_job_log` VALUES (1000002092500222, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 13:05:30');
INSERT INTO `sys_job_log` VALUES (1000002094698231, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：1毫秒', '0', NULL, '2020-03-26 12:07:30');
INSERT INTO `sys_job_log` VALUES (1000002095187203, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:32:00');
INSERT INTO `sys_job_log` VALUES (1000002096909752, 'Bean运行示例', 'DEFAULT', 'jobTaskTest.jobNoParams()', 'Bean运行示例 总共耗时：0毫秒', '0', NULL, '2020-03-26 11:49:30');
INSERT INTO `sys_job_log` VALUES (1000002119898288, 'Class运行示例', 'DEFAULT', 'com.nbclass.job.task.cancelOrder.cancel(\'取消操作\')', 'Class运行示例 总共耗时：2毫秒', '1', 'java.lang.ClassNotFoundException: com.nbclass.job.task.cancelOrder\n	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)\n	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:93)\n	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)\n	at java.lang.Class.forName0(Native Method)\n	at java.lang.Class.forName(Class.java:264)\n	at com.nbclass.modules.job.util.JobInvokeUtil.invokeMethod(JobInvokeUtil.java:33)\n	at com.nbclass.modules.job.util.QuartzDisallowConcurrentExecution.doExecute(QuartzDisallowConcurrentExecution.java:16)\n	at com.nbclass.modules.job.util.AbstractQuartzJob.execute(AbstractQuartzJob.java:37)\n	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)\n	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)\n', '2020-09-26 14:15:00');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL COMMENT '权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限访问路径',
  `perms` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `order_num` int(3) NULL DEFAULT 0 COMMENT '排序',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `status` int(1) NOT NULL COMMENT '状态：1有效；0删除',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (2, '权限管理', '权限管理', '#', NULL, 0, 0, 2, 'fa fa-list', 1, '2017-07-13 15:04:42', '2020-08-17 10:10:31');
INSERT INTO `sys_menu` VALUES (4, '系统工具', '系统工具', '#', NULL, 0, 0, 4, 'fa fa-gears', 1, '2018-07-06 15:20:38', '2020-07-30 11:40:24');
INSERT INTO `sys_menu` VALUES (201, '用户管理', '用户管理', '/users', 'users', 2, 1, 1, 'fa fa-circle-o', 1, '2017-07-13 15:05:47', '2020-08-14 11:28:19');
INSERT INTO `sys_menu` VALUES (202, '角色管理', '角色管理', '/roles', 'roles', 2, 1, 2, 'fa fa-circle-o', 1, '2017-07-17 14:39:09', '2020-08-14 13:41:48');
INSERT INTO `sys_menu` VALUES (203, '菜单管理', '菜单管理', '/menus', 'menus', 2, 1, 3, 'fa fa-circle-o', 1, '2017-09-26 07:33:51', '2020-08-17 09:07:38');
INSERT INTO `sys_menu` VALUES (401, '图标工具', '图标工具', '/icons', 'icons', 4, 1, 3, 'fa fa-circle-o', 1, '2018-07-06 15:21:00', '2020-05-16 15:43:03');
INSERT INTO `sys_menu` VALUES (20101, '列表查询', '用户列表查询', '/user/list', 'user:list', 201, 2, 0, NULL, 1, '2017-07-13 15:09:24', '2017-10-09 05:38:29');
INSERT INTO `sys_menu` VALUES (20102, '新增', '新增用户', '/user/add', 'user:add', 201, 2, 0, NULL, 1, '2017-07-13 15:06:50', '2018-02-28 17:58:46');
INSERT INTO `sys_menu` VALUES (20103, '编辑', '编辑用户', '/user/edit', 'user:edit', 201, 2, 0, NULL, 1, '2017-07-13 15:08:03', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20104, '删除', '删除用户', '/user/delete', 'user:delete', 201, 2, 0, NULL, 1, '2017-07-13 15:08:42', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20105, '批量删除', '批量删除用户', '/user/batch/delete', 'user:batchDelete', 201, 2, 0, '', 1, '2018-07-11 01:53:09', '2018-07-11 01:53:09');
INSERT INTO `sys_menu` VALUES (20106, '分配角色', '分配角色', '/user/assign/role', 'user:assignRole', 201, 2, 0, NULL, 1, '2017-07-13 15:09:24', '2017-10-09 05:38:29');
INSERT INTO `sys_menu` VALUES (20201, '列表查询', '角色列表查询', '/role/list', 'role:list', 202, 2, 0, NULL, 1, '2017-10-10 15:31:36', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20202, '新增', '新增角色', '/role/add', 'role:add', 202, 2, 0, NULL, 1, '2017-07-17 14:39:46', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20203, '编辑', '编辑角色', '/role/edit', 'role:edit', 202, 2, 0, NULL, 1, '2017-07-17 14:40:15', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20204, '删除', '删除角色', '/role/delete', 'role:delete', 202, 2, 0, NULL, 1, '2017-07-17 14:40:57', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20205, '批量删除', '批量删除角色', '/role/batch/delete', 'role:batchDelete', 202, 2, 0, '', 1, '2018-07-10 22:20:43', '2018-07-10 22:20:43');
INSERT INTO `sys_menu` VALUES (20206, '分配权限', '分配权限', '/role/assign/permission', 'role:assignPerms', 202, 2, 0, NULL, 1, '2017-09-26 07:33:05', '2018-02-27 10:53:14');
INSERT INTO `sys_menu` VALUES (20301, '列表查询', '资源列表', '/menu/list', 'menu:list', 203, 2, 0, NULL, 1, '2018-07-12 16:25:28', '2020-08-17 09:08:12');
INSERT INTO `sys_menu` VALUES (20302, '新增', '新增资源', '/menu/add', 'menu:add', 203, 2, 0, NULL, 1, '2017-09-26 08:06:58', '2020-08-17 09:08:30');
INSERT INTO `sys_menu` VALUES (20303, '编辑', '编辑资源', '/menu/edit', 'menu:edit', 203, 2, 0, NULL, 1, '2017-09-27 21:29:04', '2020-08-17 09:09:13');
INSERT INTO `sys_menu` VALUES (20304, '删除', '删除资源', '/menu/delete', 'menu:delete', 203, 2, 0, NULL, 1, '2017-09-27 21:29:50', '2020-08-17 09:08:51');
INSERT INTO `sys_menu` VALUES (1000000065021152, '文件列表', '文件列表', '/sysfile/list', 'sysfile:list', 1000000237237137, 2, 0, '', 1, '2020-04-03 16:38:00', '2020-04-03 16:38:00');
INSERT INTO `sys_menu` VALUES (1000000237237137, '文件管理', '文件管理', '/sysfile', 'sysfile', 4, 1, 2, 'fa fa-circle-o', 1, '2020-03-27 10:06:53', '2020-05-16 15:44:14');
INSERT INTO `sys_menu` VALUES (1000000366692472, '任务日志', '任务日志', '/sysJobLog', 'sysJobLog', 1000001175679498, 2, 0, NULL, 1, '2020-03-25 15:25:22', '2020-03-25 15:34:36');
INSERT INTO `sys_menu` VALUES (1000000525230921, '存储设置', '存储设置', '/sysfile/setStorage', 'sysfile:setStorage', 1000000237237137, 2, 0, '', 1, '2020-04-03 16:40:11', '2020-04-03 16:40:11');
INSERT INTO `sys_menu` VALUES (1000000635699437, '文件删除', '文件删除', '/sysfile/delete', 'sysfile:delete', 1000000237237137, 2, 0, '', 1, '2020-04-03 16:39:21', '2020-04-03 16:39:21');
INSERT INTO `sys_menu` VALUES (1000000705100126, '添加任务', '添加任务', '/sysJob/addSysJob', 'sysJob:add', 1000001175679498, 2, 0, '', 1, '2020-03-25 10:30:29', '2020-03-25 10:30:29');
INSERT INTO `sys_menu` VALUES (1000001175679498, '定时任务', '定时任务', '/sysJob', 'sysJob', 4, 1, 1, 'fa fa-circle-o', 1, '2020-03-24 16:20:00', '2020-05-16 15:44:07');
INSERT INTO `sys_menu` VALUES (1000001495923480, '列表查询', '列表查询', '/sysJob/list', 'sysJob:list', 1000001175679498, 2, 0, '', 1, '2020-03-24 16:28:55', '2020-03-24 16:28:55');
INSERT INTO `sys_menu` VALUES (1000001517087680, '执行任务', '执行任务', '/sysJob/run', 'sysJob:run', 1000001175679498, 2, 0, '', 1, '2020-03-25 10:28:24', '2020-03-25 10:28:24');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` int(1) NOT NULL COMMENT '状态：1有效 0删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1000000631354089, '测试人员', '测试人员', 0, '2020-08-17 10:34:34', '2020-09-29 10:11:12');
INSERT INTO `sys_role` VALUES (1000000999440798, '测试管理员', '测试管理员', 1, '2020-09-29 10:55:02', NULL);
INSERT INTO `sys_role` VALUES (1000001005486246, '测试人员', '测试人员', 0, '2020-08-13 11:38:09', '2020-09-28 16:52:24');
INSERT INTO `sys_role` VALUES (1000001765927057, '管理员', '管理员', 1, '2020-04-25 13:44:11', '2020-09-22 14:28:45');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_perm_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`role_perm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1000000009301801, 1000000999440798, 4);
INSERT INTO `sys_role_menu` VALUES (1000000028902259, 1000000631354089, 20102);
INSERT INTO `sys_role_menu` VALUES (1000000055946945, 1000000631354089, 1000000065021152);
INSERT INTO `sys_role_menu` VALUES (1000000068704393, 1000000631354089, 2);
INSERT INTO `sys_role_menu` VALUES (1000000095228021, 1000000631354089, 201);
INSERT INTO `sys_role_menu` VALUES (1000000114302230, 1000000631354089, 1000000705100126);
INSERT INTO `sys_role_menu` VALUES (1000000134967152, 1000000631354089, 1000001517087680);
INSERT INTO `sys_role_menu` VALUES (1000000192177847, 1000000999440798, 1000001495923480);
INSERT INTO `sys_role_menu` VALUES (1000000239587591, 1000000631354089, 203);
INSERT INTO `sys_role_menu` VALUES (1000000365116241, 1000000999440798, 401);
INSERT INTO `sys_role_menu` VALUES (1000000446223514, 1000000631354089, 1000001495923480);
INSERT INTO `sys_role_menu` VALUES (1000000523953107, 1000000999440798, 1000000705100126);
INSERT INTO `sys_role_menu` VALUES (1000000561240469, 1000000631354089, 20204);
INSERT INTO `sys_role_menu` VALUES (1000000638194324, 1000000631354089, 20105);
INSERT INTO `sys_role_menu` VALUES (1000000724472629, 1000000631354089, 20203);
INSERT INTO `sys_role_menu` VALUES (1000000758670055, 1000000999440798, 1000000237237137);
INSERT INTO `sys_role_menu` VALUES (1000000846145153, 1000000631354089, 1000000237237137);
INSERT INTO `sys_role_menu` VALUES (1000000855002441, 1000000631354089, 20104);
INSERT INTO `sys_role_menu` VALUES (1000000911765471, 1000000631354089, 20302);
INSERT INTO `sys_role_menu` VALUES (1000000921836867, 1000000631354089, 20106);
INSERT INTO `sys_role_menu` VALUES (1000000961545620, 1000000999440798, 1000000366692472);
INSERT INTO `sys_role_menu` VALUES (1000000973756333, 1000000999440798, 1000000525230921);
INSERT INTO `sys_role_menu` VALUES (1000001011625202, 1000000999440798, 1000001175679498);
INSERT INTO `sys_role_menu` VALUES (1000001041740907, 1000000631354089, 20103);
INSERT INTO `sys_role_menu` VALUES (1000001053505000, 1000000631354089, 202);
INSERT INTO `sys_role_menu` VALUES (1000001081542833, 1000000631354089, 1000001175679498);
INSERT INTO `sys_role_menu` VALUES (1000001134709310, 1000000999440798, 1000000065021152);
INSERT INTO `sys_role_menu` VALUES (1000001138479716, 1000000631354089, 20206);
INSERT INTO `sys_role_menu` VALUES (1000001160606423, 1000000631354089, 20201);
INSERT INTO `sys_role_menu` VALUES (1000001193889022, 1000000631354089, 1000000525230921);
INSERT INTO `sys_role_menu` VALUES (1000001199246140, 1000000631354089, 20205);
INSERT INTO `sys_role_menu` VALUES (1000001213614566, 1000000631354089, 20301);
INSERT INTO `sys_role_menu` VALUES (1000001388415023, 1000000999440798, 1000001517087680);
INSERT INTO `sys_role_menu` VALUES (1000001462034718, 1000000631354089, 20202);
INSERT INTO `sys_role_menu` VALUES (1000001535048665, 1000000631354089, 1000000635699437);
INSERT INTO `sys_role_menu` VALUES (1000001595556573, 1000000999440798, 1000000635699437);
INSERT INTO `sys_role_menu` VALUES (1000001656868196, 1000000631354089, 20101);
INSERT INTO `sys_role_menu` VALUES (1000001742204134, 1000000631354089, 1000000366692472);
INSERT INTO `sys_role_menu` VALUES (1000001806765635, 1000000631354089, 20303);
INSERT INTO `sys_role_menu` VALUES (1000001853055403, 1000000631354089, 4);
INSERT INTO `sys_role_menu` VALUES (1000001883331950, 1000000631354089, 401);
INSERT INTO `sys_role_menu` VALUES (1000001888023853, 1000000631354089, 20304);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `sex` int(255) NULL DEFAULT NULL COMMENT '年龄：1男2女',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `status` int(1) NOT NULL COMMENT '用户状态：1有效; 0删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'faf6c259fea5e87ece5c70a3c206c64c', 'cf16bb81cfbb0a8546807f63f2d18255', '2081997821@qq.com', '18877514906', 2, 26, 1, '2018-05-23 21:22:06', '2020-08-17 10:31:31', '2020-10-28 16:16:32');
INSERT INTO `sys_user` VALUES (10086, 'mysql', '44b0404465dc161af2eebf2e44c44e3f', 'e9199811281f4c5f309569088d9a16c7', '2081997821@qq.com', '18877514905', 1, 25, 1, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (1000000602923250, 'test', '993d91bc08da98336b8413fa400683c8', '441f3eb14290cb00a2a332ed525bae4d', '2081997821@qq.com', '18877514905', 1, 2, 1, '2020-08-17 10:33:13', '2020-09-28 14:30:04', '2020-10-14 10:43:16');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1000000352134193, 1000000602923250, 1000000999440798);

SET FOREIGN_KEY_CHECKS = 1;
