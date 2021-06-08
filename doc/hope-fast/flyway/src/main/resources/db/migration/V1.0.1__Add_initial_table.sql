#hope数据库
SET FOREIGN_KEY_CHECKS=0;

#数据库配置主键id生成规则表
CREATE TABLE `pb_code_ident` (
  `PCI_Table` varchar(64) NOT NULL,
  `PCI_Type` varchar(64) DEFAULT NULL,
  `PCI_Length` int DEFAULT NULL,
  `PCI_Head` varchar(8) DEFAULT NULL,
  `PCI_Fill` varchar(64) DEFAULT NULL,
  `PCI_Date` datetime DEFAULT NULL,
  `PCI_Default` decimal(18,0) DEFAULT NULL,
  `PCI_Identity` decimal(16,0) DEFAULT NULL,
  PRIMARY KEY (`PCI_Table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

#杂项配置表
CREATE TABLE `t_m_cls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_first_cls` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `item_second_cls` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `item_value` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark1` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '',
  `remark2` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '',
  `remark3` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark4` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT COMMENT='杂项管理表';

#登录token表
CREATE TABLE `APP_TOKEN` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_user_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'ID（guid）app_用户id',
  `token` varchar(36) DEFAULT NULL COMMENT 'token',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT '0' COMMENT 'token状态: 0:有效 1:失效',
  `remark` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '扩展字段:token类型（0:是app端 1:是pc端）',
  `valid_period` datetime DEFAULT NULL COMMENT '有效期,到期redis自动失效，查询有效token需要做条件判断',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=utf8mb4 COMMENT='登录token表';

#用户信息表
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL COMMENT '用户账号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

