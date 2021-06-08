package com.hope.model.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 杂项管理表
 * 一些业务配置，经常变更的配置，存数据库，避免发版-方便维护
 *
 * @author aodeng
 */
@Data
@TableName("t_m_cls")
public class TmConfig {

    private Integer id;
    private String itemFirstCls;
    private String itemSecondCls;
    private String itemValue;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
}
/*
* CREATE TABLE `t_m_cls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_first_cls` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `item_second_cls` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `item_value` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark1` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文中描述',
  `remark2` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'URL图片',
  `remark3` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  `remark4` varchar(400) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT COMMENT='杂项管理表';*/