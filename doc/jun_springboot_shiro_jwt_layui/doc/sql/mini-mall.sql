/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mini-mall

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-11-12 14:50:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_ad_item
-- ----------------------------
DROP TABLE IF EXISTS `a_ad_item`;
CREATE TABLE `a_ad_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_space_id` int(11) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '广告名称',
  `object_id` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `content` text COMMENT '内容',
  `weight` double DEFAULT '0' COMMENT '权重',
  `create_admin` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_ad_item
-- ----------------------------

-- ----------------------------
-- Table structure for a_ad_space
-- ----------------------------
DROP TABLE IF EXISTS `a_ad_space`;
CREATE TABLE `a_ad_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '广告名称',
  `no` varchar(20) DEFAULT NULL COMMENT '广告编号',
  `template` text COMMENT '模板内容',
  `number` int(11) DEFAULT NULL COMMENT '容量',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `width` int(5) DEFAULT NULL COMMENT '图片',
  `height` int(5) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `no` (`no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_ad_space
-- ----------------------------
INSERT INTO `a_ad_space` VALUES ('4', '首页轮播图', 'SY001', '<div class=\"focus\" id=\"slides\">\n    <ul class=\"tab-nav\">\n    <#list 1..model.adItemVos?size as i>\n      <li class=\"<#if i == 1 >onpic</#if>\">${i}</li>\n    </#list>\n    </ul>\n    <div class=\"focusbg\"></div>\n    <div class=\"tab-content\">\n    <#list model.adItemVos as ai >\n		<#assign content=ai.content?eval />\n			<div class=\"tab-pannel\">\n		   			<a href=\"${content.link}\" target=\"_blank\"><img class=\"lazy\" src=\"${model.domain}/images/grey.gif\" data-original=\"${model.domain}${ content.picPath}\" width=\"${model.width}\" height=\"${model.height}\" alt=\"${ai.name}\"/></a>\n			</div>\n	  </#list>\n	  </div>\n  </div>\n', '5', '2', '750', '355', null, '2020-10-27 14:57:25');
INSERT INTO `a_ad_space` VALUES ('7', '首页风格分类', 'SY002', '<div class=\"txt\">\n      <ul>\n      <#list model.adItemVos as ai >\n		<#assign content=ai.content?eval />\n        <li><a href=\"${content.link}\" target=\"_blank\" title=\"${content.text}\">${content.text}</a><#if ai_has_next>|</#if></li>\n	  </#list>\n      </ul>\n    </div>\n', '10', '4', '240', '48', null, '2020-10-29 10:26:51');
INSERT INTO `a_ad_space` VALUES ('8', '首页格调分类', 'SY003', '<div class=\"txt\">\n      <ul>\n      <#list model.adItemVos as ai >\n		<#assign content=ai.content?eval />\n        <li><a href=\"${content.link}\" target=\"_blank\" title=\"${content.text}\">${content.text}</a><#if ai_has_next>|</#if></li>\n	  </#list>\n      </ul>\n    </div>\n', '5', '4', '240', '48', null, '2020-10-29 10:28:41');
INSERT INTO `a_ad_space` VALUES ('9', '首页首推商品', 'SY006', ' <div class=\"grid1000 item1\">\n    <div class=\"next Left\"><a href=\"javascript:;\">上一页</a></div>\n   <div class=\"txt Slide\">\n      <ul>\n      <#list model.adItemVos as ai >\n	  <#assign content=ai.content?eval />\n      <#assign product=content.product />\n     \n       <li>\n          <div><a href=\"/html/product/detail?id=${product.id}\" target=\"_blank\" title=\"${product.name}\">\n          <img class=\"lazy\" src=\"/images/grey.gif\" data-original=\"${product.mainPic}\" width=\"90\" height=\"90\" alt=\"${product.name}\"></a></div>\n          <h4><a href=\"/html/product/detail?id=${product.id}\" target=\"_blank\">${product.name}</a></h4>\n          <b>￥${product.price}</b> <span>已售${product.salesNum + product.virtualSalesNum }件</span> </li>\n       \n	  </#list>\n      \n      </ul>\n    </div> \n    <div class=\"prev Right\"><a href=\"javascript:;\">下一页</a></div>\n  </div>\n', '10', '1', '1000', '142', null, '2020-10-29 10:42:09');
INSERT INTO `a_ad_space` VALUES ('10', '首页材质分类', 'SY004', '<div class=\"txt\">\n      <ul>\n      <#list model.adItemVos as ai >\n		<#assign content=ai.content?eval />\n        <li><a href=\"${content.link}\" target=\"_blank\" title=\"${content.text}\">${content.text}</a><#if ai_has_next>|</#if></li>\n	  </#list>\n      </ul>\n    </div>\n', '5', '4', '240', '48', null, '2020-10-29 10:30:14');
INSERT INTO `a_ad_space` VALUES ('11', '首页款式分类', 'SY005', '<div class=\"txt\">\n      <ul>\n      <#list model.adItemVos as ai >\n		<#assign content=ai.content?eval />\n        <li><a href=\"${content.link}\" target=\"_blank\" title=\"${content.text}\">${content.text}</a><#if ai_has_next>|</#if></li>\n	  </#list>\n      </ul>\n    </div>\n', '10', '4', '240', '48', null, '2020-10-29 10:33:33');
INSERT INTO `a_ad_space` VALUES ('12', '台灯专区', 'SY007', 'SY007', '6', '1', null, null, null, '2020-10-25 22:44:41');
INSERT INTO `a_ad_space` VALUES ('13', '首页广告位', 'SY008', '', '10', '1', null, null, null, '2020-10-25 22:44:41');
INSERT INTO `a_ad_space` VALUES ('14', '吊灯专区', 'SY009', '', '10', '1', null, null, null, '2020-10-25 22:44:41');
INSERT INTO `a_ad_space` VALUES ('15', '首页广告位', 'SY010', '', '10', '1', null, null, null, '2020-10-25 22:44:41');
INSERT INTO `a_ad_space` VALUES ('16', '首页广告位', 'SY011', 'SY008', '10', '1', null, null, null, '2020-10-25 22:44:41');
INSERT INTO `a_ad_space` VALUES ('22', 'aaa', '111', 'sdasd', '22', '1', '1', '2', '2020-10-25 12:00:31', '2020-10-25 22:44:41');
INSERT INTO `a_ad_space` VALUES ('23', 'hhh', 'xxx', 'dadfadfdasf', '10', '3', '100', '200', '2020-10-27 12:39:33', '2020-10-27 12:39:33');

-- ----------------------------
-- Table structure for o_gateway_order
-- ----------------------------
DROP TABLE IF EXISTS `o_gateway_order`;
CREATE TABLE `o_gateway_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `gateway_trade_no` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `notify_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `amount` double(10,2) DEFAULT NULL,
  `pay_type` tinyint(4) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_gateway_order
-- ----------------------------
INSERT INTO `o_gateway_order` VALUES ('2', '13', '2013062248852447', '2013-06-22 22:07:12', '2013-06-22 22:21:26', '2', '0.01', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('3', '14', '2013062248875647', '2013-06-22 22:20:23', '2013-06-22 22:21:14', '2', '0.01', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('4', '16', null, '2013-06-23 10:56:04', null, '1', null, '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('5', '17', null, '2013-06-23 10:57:09', null, '1', null, '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('6', '18', '1216175101201306230290970146', '2013-06-23 11:45:13', '2013-06-23 12:13:58', '2', '0.01', '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('7', '19', '1216175101201306230290978401', '2013-06-23 12:00:46', '2013-06-23 12:01:22', '2', '0.01', '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('8', '20', null, '2013-06-23 14:36:51', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('9', '21', '2013062350000847', '2013-06-23 14:38:43', '2013-06-23 14:39:34', '2', '0.01', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('10', '22', null, '2013-06-24 10:21:52', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('11', '23', null, '2013-06-24 14:57:06', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('12', '24', '2013062402351663', '2013-06-24 15:02:38', '2013-06-24 15:03:43', '2', '0.01', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('13', '25', null, '2013-06-25 13:02:11', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('14', '27', null, '2013-06-25 22:46:52', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('15', '28', null, '2013-06-25 22:52:02', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('16', '30', null, '2013-07-04 15:02:12', null, '1', null, '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('17', '31', null, '2013-07-06 12:15:50', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('18', '32', '2013070804078047', '2013-07-08 21:48:39', '2013-07-08 21:49:31', '2', '158.00', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('19', '33', '1216175101201307080311987938', '2013-07-08 22:50:10', '2013-07-08 22:50:30', '2', '0.01', '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('20', '34', null, '2013-07-10 21:08:44', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('21', '35', null, '2013-08-08 18:24:33', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('22', '36', null, '2013-08-08 18:26:40', null, '1', null, '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('23', '37', null, '2013-08-28 16:25:31', null, '1', null, '2', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('24', '38', '2013100805938741', '2013-10-08 14:11:39', '2013-10-08 14:13:26', '2', '680.00', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('25', '39', '2013100811944274', '2013-10-08 14:14:11', '2013-10-08 14:14:58', '2', '38.00', '1', '2020-10-25 22:45:02');
INSERT INTO `o_gateway_order` VALUES ('26', '40', null, '2013-10-08 15:22:45', null, '1', null, '2', '2020-10-25 22:45:02');

-- ----------------------------
-- Table structure for o_logistic
-- ----------------------------
DROP TABLE IF EXISTS `o_logistic`;
CREATE TABLE `o_logistic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `code` varchar(50) DEFAULT NULL COMMENT '快递公司代码',
  `company_url` varchar(100) DEFAULT NULL COMMENT '公司网址',
  `weight` double DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_logistic
-- ----------------------------
INSERT INTO `o_logistic` VALUES ('1', '申通', 'shentong', 'http://www.sto.cn/', '0', null, '2020-10-25 22:45:34');
INSERT INTO `o_logistic` VALUES ('2', '圆通', 'yuantong', 'http://www.yto.net.cn/', '0', null, '2020-10-25 22:45:34');
INSERT INTO `o_logistic` VALUES ('3', '秦粤物流', 'qinyue', 'http://www.qinyue56.com/', '0', null, '2020-10-25 22:45:34');

-- ----------------------------
-- Table structure for o_order
-- ----------------------------
DROP TABLE IF EXISTS `o_order`;
CREATE TABLE `o_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `no` varchar(50) DEFAULT NULL COMMENT '订单号',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态',
  `total_amount` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '支付金额',
  `refund_amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `total_quantity` int(11) DEFAULT NULL COMMENT '订单数量',
  `consumer_user_id` int(11) DEFAULT NULL,
  `consignee_tel` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `consignee_cellphone` varchar(20) DEFAULT NULL COMMENT '收货人手机',
  `consignee_name` varchar(100) DEFAULT NULL COMMENT '收货人姓名',
  `consignee_address` varchar(255) DEFAULT NULL COMMENT '收货人地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `logisticid` int(11) DEFAULT NULL COMMENT '快递',
  `logistic_no` varchar(20) DEFAULT NULL COMMENT '快递单号',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `no` (`no`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_order
-- ----------------------------
INSERT INTO `o_order` VALUES ('13', '20130622220701', '8', '0.01', '0.01', '0.01', '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-22 22:07:01', '2013-06-22 22:21:26', null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('14', '20130622222017', '8', '10000.00', '10000.00', '0.01', '1', '52', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-22 22:20:17', '2013-06-22 22:21:14', null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('15', '20130622224027', '9', '0.01', null, null, '1', '52', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-22 22:40:27', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('16', '20130623105558', '9', '0.01', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-23 10:55:58', null, null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('17', '20130623105706', '9', '0.01', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-23 10:57:06', null, null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('18', '20130623114329', '8', '0.01', '0.01', '0.01', '1', '54', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-23 11:43:29', '2013-06-23 12:13:58', null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('19', '20130623120042', '5', '0.01', '0.01', null, '1', '54', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-23 12:00:42', '2013-06-23 12:01:22', '1', '234234234234', '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('20', '20130623143643', '9', '0.01', null, null, '1', '66', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-23 14:36:43', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('21', '20130623143840', '3', '0.01', '0.01', null, '1', '66', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-23 14:38:40', '2013-06-23 14:39:34', '1', '23423423424', '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('22', '20130624102143', '9', '0.01', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-24 10:21:43', null, null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('23', '20130624145700', '9', '0.01', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-24 14:57:00', null, null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('24', '20130624150233', '8', '0.01', '0.01', '0.01', '1', '61', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-24 15:02:33', '2013-06-24 15:03:43', null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('25', '20130625130206', '9', '1387.00', null, null, '1', '63', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-25 13:02:06', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('26', '20130625181832', '9', '252.00', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-25 18:18:32', null, null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('27', '20130625224642', '9', '35.00', null, null, '1', '61', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-25 22:46:42', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('28', '20130625225129', '9', '358.00', null, null, '1', '66', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-25 22:51:29', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('29', '20130626161127', '9', '99.00', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-26 16:11:27', null, null, null, '2020-11-11 17:26:26');
INSERT INTO `o_order` VALUES ('30', '20130704150202', '9', '0.01', null, null, '1', '52', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-04 15:02:02', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('31', '20130706121509', '9', '89.00', null, null, '1', '70', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-06 12:15:09', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('32', '20130708214833', '8', '158.00', '158.00', '158.00', '1', '71', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-06 21:48:33', '2013-07-07 21:49:31', '2', '5304384438', '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('33', '20130708225006', '8', '0.01', '0.01', '0.01', '1', '72', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-08 22:50:06', '2013-07-08 22:50:30', null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('34', '20130710210838', '9', '194.00', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-10 21:08:38', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('35', '20130808182422', '9', '48.00', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-08 18:24:22', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('36', '20130808182635', '9', '32.60', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-08 18:26:35', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('37', '20130828162521', '9', '16.80', null, null, '1', '53', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2020-11-28 16:25:21', null, null, null, '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('38', '20131008104028', '3', '680.00', '680.00', null, '1', '74', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2013-10-08 10:40:28', '2013-10-08 14:13:26', '3', '52606505', '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('39', '20131008140304', '3', '38.00', '38.00', null, '1', '74', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2013-10-08 14:03:04', '2013-10-08 14:14:58', '3', '52606505', '2020-11-12 14:29:52');
INSERT INTO `o_order` VALUES ('40', '20131008152231', '9', '48.00', null, null, '1', '75', '', '13888888888', '刘老大', '河北省 邢台市 邢台县 街道地址街道地址街道地址街道地址街道地址 ', '2013-10-08 15:22:31', null, null, null, '2020-11-12 14:29:52');

-- ----------------------------
-- Table structure for o_order_item
-- ----------------------------
DROP TABLE IF EXISTS `o_order_item`;
CREATE TABLE `o_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '订单金额',
  `price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `message` varchar(500) DEFAULT NULL COMMENT '留言',
  `snapshot_id` int(11) DEFAULT NULL COMMENT '商品快照',
  `name` varchar(255) DEFAULT NULL COMMENT '订单名称',
  `pic_url` varchar(100) DEFAULT NULL COMMENT '商品图片url',
  `product_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_t_order_t_snapshot` (`snapshot_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_order_item
-- ----------------------------
INSERT INTO `o_order_item` VALUES ('35', '13', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('36', '14', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('37', '15', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('38', '16', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('39', '17', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('40', '18', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('41', '19', '0.01', '0.01', '1', '', '1269', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('42', '20', '0.01', '0.01', '1', '要真货', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('43', '21', '0.01', '0.01', '1', '', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('44', '22', '0.01', '0.01', '1', '', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('45', '23', '0.01', '0.01', '1', '选个好点的', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('46', '24', '0.01', '0.01', '1', '', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('47', '25', '698.00', '698.00', '1', '', '1253', '灯具简约现代客厅灯水晶灯时尚吸顶灯亚克力温馨时尚创意卧室灯饰', '/images/product/255/thumbnail/20130622104607963.jpg', '255', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('48', '25', '158.00', '158.00', '1', '', '1247', '凯晶堡过道楼梯壁灯花朵现代简约田园浪漫壁灯饰灯具40150', '/images/product/260/thumbnail/20130622104655183.jpg', '260', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('49', '25', '38.00', '38.00', '1', '', '1466', '凯晶堡 灯饰灯具  过道灯 玄关灯 水晶过道灯具', '/images/product/482/thumbnail/20130622110727472.jpg', '482', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('50', '25', '35.00', '35.00', '1', '', '1462', '凯晶堡灯饰 灯具 厨卫防水吸顶灯卫浴节能灯 面包灯节能灯 4026', '/images/product/478/thumbnail/20130622110703510.jpg', '478', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('51', '25', '458.00', '458.00', '1', '', '1406', '灯饰灯具水晶灯LED灯水晶灯LED客厅灯吸顶灯长方形灯9791', '/images/product/422/thumbnail/20130622110210743.jpg', '422', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('52', '26', '252.00', '252.00', '1', '', '1281', '灯饰灯具 现代简约水晶莲花卧室灯吸顶灯 小号 ', '/images/product/297/thumbnail/20130622105108079.jpg', '297', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('53', '27', '35.00', '35.00', '1', '', '1254', '简约现代过道灯阳台灯阁楼吸顶灯玻璃灯', '/images/product/254/thumbnail/20130622104603839.jpg', '254', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('54', '28', '358.00', '358.00', '1', 'lll', '1252', '【新品】灯饰灯具黄色水晶灯中国古典客厅餐厅吊莲花吊灯', '/images/product/256/thumbnail/20130622104615755.jpg', '256', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('55', '29', '99.00', '99.00', '1', '', '1270', '木艺卧室灯中国风卧室灯木艺加亚克力卧室灯特价疯抢', '/images/product/291/thumbnail/20130622105059577.jpg', '291', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('56', '30', '0.01', '0.01', '1', '', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('57', '31', '89.00', '89.00', '1', '我要好看的\r\n', '1378', '灯饰灯具 田园浪漫花艺布艺壁灯过道灯床头灯CH039-1B', '/images/product/394/thumbnail/20130622105931639.jpg', '394', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('58', '32', '158.00', '158.00', '1', '帮我选个好点的灯哦', '1257', '新款凯晶堡灯饰过道玄关单头水晶莲花灯饰LED灯具装饰照明8518 ', '/images/product/275/thumbnail/20130622104850390.jpg', '275', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('59', '33', '0.01', '0.01', '1', '', '1490', '测试支付1分钱，请勿购买', '/images/product/506/thumbnail/20130622220256440.jpg', '506', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('60', '34', '178.00', '178.00', '1', '', '1249', '凯晶堡楼梯过道壁灯铁艺水晶装饰壁灯灯饰灯具4151', '/images/product/259/thumbnail/20130622104646783.jpg', '259', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('61', '34', '16.00', '16.00', '1', '', '1374', 'E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮', '/images/product/391/thumbnail/20130622105916513.jpg', '391', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('62', '35', '48.00', '48.00', '1', '', '1303', '水晶LED天花灯天花射灯LED射灯水晶壳射灯筒灯孔灯', '/images/product/320/thumbnail/20130622105328050.jpg', '320', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('63', '36', '32.60', '32.60', '1', '', '1338', 'LED射灯LED水晶射灯节能灯水晶天花灯圆形射灯开孔5.8cm~8.6cm', '/images/product/354/thumbnail/20130622105638164.jpg', '354', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('64', '37', '16.80', '16.80', '1', '', '1245', '凯晶堡灯饰灯具大功率LED节能灯E27球泡灯灯泡', '/images/product/262/thumbnail/20130622104704483.jpg', '262', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('65', '38', '680.00', '680.00', '1', '我要买韵律/大号/带遥控。发货尽量使用中铁快运。', '1321', '凯晶堡现代个性大功率水晶LED吸顶灯客厅卧室装饰灯饰灯具韵律', '/images/product/337/thumbnail/20130622105525373.jpg', '337', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('66', '39', '38.00', '38.00', '1', '', '1466', '凯晶堡 灯饰灯具  过道灯 玄关灯 水晶过道灯具', '/images/product/482/thumbnail/20130622110727472.jpg', '482', '2020-10-25 22:46:54', '2020-10-25 22:46:54');
INSERT INTO `o_order_item` VALUES ('67', '40', '48.00', '48.00', '1', '', '1303', '水晶LED天花灯天花射灯LED射灯水晶壳射灯筒灯孔灯', '/images/product/320/thumbnail/20130622105328050.jpg', '320', '2020-10-25 22:46:54', '2020-10-25 22:46:54');

-- ----------------------------
-- Table structure for o_order_log
-- ----------------------------
DROP TABLE IF EXISTS `o_order_log`;
CREATE TABLE `o_order_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_order_log
-- ----------------------------
INSERT INTO `o_order_log` VALUES ('1', '13', '1', '2013-06-22 22:07:01', null, '下单');
INSERT INTO `o_order_log` VALUES ('2', '14', '1', '2013-06-22 22:20:17', null, '下单');
INSERT INTO `o_order_log` VALUES ('3', '14', '2', '2013-06-22 22:21:14', null, '支付');
INSERT INTO `o_order_log` VALUES ('4', '13', '2', '2013-06-22 22:21:26', null, '支付');
INSERT INTO `o_order_log` VALUES ('5', '15', '1', '2013-06-22 22:40:27', null, '下单');
INSERT INTO `o_order_log` VALUES ('6', '16', '1', '2013-06-23 10:55:58', null, '下单');
INSERT INTO `o_order_log` VALUES ('7', '17', '1', '2013-06-23 10:57:06', null, '下单');
INSERT INTO `o_order_log` VALUES ('8', '18', '1', '2013-06-23 11:43:29', null, '下单');
INSERT INTO `o_order_log` VALUES ('9', '19', '1', '2013-06-23 12:00:42', null, '下单');
INSERT INTO `o_order_log` VALUES ('10', '19', '2', '2013-06-23 12:01:22', null, '支付');
INSERT INTO `o_order_log` VALUES ('11', '18', '2', '2013-06-23 12:13:58', null, '支付');
INSERT INTO `o_order_log` VALUES ('12', '20', '1', '2013-06-23 14:36:43', null, '下单');
INSERT INTO `o_order_log` VALUES ('13', '21', '1', '2013-06-23 14:38:40', null, '下单');
INSERT INTO `o_order_log` VALUES ('14', '21', '2', '2013-06-23 14:39:34', null, '支付');
INSERT INTO `o_order_log` VALUES ('15', '22', '1', '2013-06-24 10:21:43', null, '下单');
INSERT INTO `o_order_log` VALUES ('16', '23', '1', '2013-06-24 14:57:00', null, '下单');
INSERT INTO `o_order_log` VALUES ('17', '24', '1', '2013-06-24 15:02:33', null, '下单');
INSERT INTO `o_order_log` VALUES ('18', '24', '2', '2013-06-24 15:03:43', null, '支付');
INSERT INTO `o_order_log` VALUES ('19', '15', '9', '2013-06-24 22:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('20', '16', '9', '2013-06-24 22:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('21', '17', '9', '2013-06-24 22:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('22', '20', '9', '2013-06-24 22:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('23', '22', '9', '2013-06-25 10:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('24', '25', '1', '2013-06-25 13:02:06', null, '下单');
INSERT INTO `o_order_log` VALUES ('25', '23', '9', '2013-06-25 15:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('26', '26', '1', '2013-06-25 18:18:32', null, '下单');
INSERT INTO `o_order_log` VALUES ('27', '27', '1', '2013-06-25 22:46:42', null, '下单');
INSERT INTO `o_order_log` VALUES ('28', '28', '1', '2013-06-25 22:51:29', null, '下单');
INSERT INTO `o_order_log` VALUES ('29', '25', '9', '2013-06-26 13:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('30', '29', '1', '2013-06-26 16:11:27', null, '下单');
INSERT INTO `o_order_log` VALUES ('31', '26', '9', '2013-06-26 18:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('32', '27', '9', '2013-06-26 23:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('33', '28', '9', '2013-06-26 23:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('34', '29', '9', '2013-06-27 16:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('35', '24', '6', '2013-06-30 00:19:40', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('36', '24', '9', '2013-06-30 17:17:03', '20', '同意退款');
INSERT INTO `o_order_log` VALUES ('37', '30', '1', '2013-07-04 15:02:02', null, '下单');
INSERT INTO `o_order_log` VALUES ('38', '30', '10', '2013-07-05 15:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('39', '31', '1', '2013-07-06 12:15:09', null, '下单');
INSERT INTO `o_order_log` VALUES ('40', '31', '10', '2013-07-07 12:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('41', '32', '1', '2013-07-08 21:48:33', null, '下单');
INSERT INTO `o_order_log` VALUES ('42', '32', '2', '2013-07-08 21:49:31', null, '支付');
INSERT INTO `o_order_log` VALUES ('43', '32', '3', '2013-07-08 21:55:22', '2', '发货');
INSERT INTO `o_order_log` VALUES ('44', '32', '4', '2013-07-08 21:59:23', null, '收货');
INSERT INTO `o_order_log` VALUES ('45', '32', '8', '2013-07-08 22:15:48', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('46', '32', '9', '2013-07-08 22:17:43', '2', '同意退款');
INSERT INTO `o_order_log` VALUES ('47', '33', '1', '2013-07-08 22:50:06', null, '下单');
INSERT INTO `o_order_log` VALUES ('48', '33', '2', '2013-07-08 22:50:30', null, '支付');
INSERT INTO `o_order_log` VALUES ('49', '34', '1', '2013-07-10 21:08:38', null, '下单');
INSERT INTO `o_order_log` VALUES ('50', '34', '10', '2013-07-11 21:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('51', '13', '6', '2013-07-27 13:57:54', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('52', '13', '9', '2013-07-27 13:59:06', '2', '同意退款');
INSERT INTO `o_order_log` VALUES ('53', '33', '6', '2013-07-27 14:02:27', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('54', '33', '7', '2013-07-27 14:02:51', '2', '拒绝退款');
INSERT INTO `o_order_log` VALUES ('55', '19', '3', '2013-07-27 18:46:39', '2', '发货');
INSERT INTO `o_order_log` VALUES ('56', '19', '4', '2013-07-27 18:46:45', null, '收货');
INSERT INTO `o_order_log` VALUES ('57', '19', '5', '2013-07-27 18:50:10', null, '评价');
INSERT INTO `o_order_log` VALUES ('58', '14', '6', '2013-07-28 12:48:54', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('59', '14', '9', '2013-07-28 12:50:14', '2', '同意退款');
INSERT INTO `o_order_log` VALUES ('60', '18', '6', '2013-07-28 12:54:51', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('61', '18', '9', '2013-07-28 12:55:17', '2', '同意退款');
INSERT INTO `o_order_log` VALUES ('62', '33', '6', '2013-07-28 12:58:15', null, '申请退款');
INSERT INTO `o_order_log` VALUES ('63', '33', '9', '2013-07-28 12:58:56', '2', '同意退款');
INSERT INTO `o_order_log` VALUES ('64', '35', '1', '2013-08-08 18:24:22', null, '下单');
INSERT INTO `o_order_log` VALUES ('65', '36', '1', '2013-08-08 18:26:35', null, '下单');
INSERT INTO `o_order_log` VALUES ('66', '37', '1', '2013-08-28 16:25:21', null, '下单');
INSERT INTO `o_order_log` VALUES ('67', '35', '10', '2013-09-04 21:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('68', '36', '10', '2013-09-04 21:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('69', '37', '10', '2013-09-04 21:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('70', '35', '10', '2013-09-04 21:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('71', '36', '10', '2013-09-04 21:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('72', '37', '10', '2013-09-04 21:00:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('73', '38', '1', '2013-10-08 10:40:28', null, '下单');
INSERT INTO `o_order_log` VALUES ('74', '39', '1', '2013-10-08 14:03:04', null, '下单');
INSERT INTO `o_order_log` VALUES ('75', '38', '2', '2013-10-08 14:13:26', null, '支付');
INSERT INTO `o_order_log` VALUES ('76', '39', '2', '2013-10-08 14:14:58', null, '支付');
INSERT INTO `o_order_log` VALUES ('77', '40', '1', '2013-10-08 15:22:31', null, '下单');
INSERT INTO `o_order_log` VALUES ('78', '40', '10', '2013-10-09 15:30:00', null, '自动关闭');
INSERT INTO `o_order_log` VALUES ('80', '39', '3', '2013-10-10 10:39:26', '2', '发货');
INSERT INTO `o_order_log` VALUES ('81', '38', '3', '2013-10-10 10:39:50', '2', '发货');
INSERT INTO `o_order_log` VALUES ('82', '21', '3', '2013-10-10 18:04:11', '2', '发货');

-- ----------------------------
-- Table structure for o_refund_apply
-- ----------------------------
DROP TABLE IF EXISTS `o_refund_apply`;
CREATE TABLE `o_refund_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '单订号',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '退款金额',
  `reason` varchar(1000) DEFAULT NULL COMMENT '退款原因',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  `reply` varchar(1000) DEFAULT NULL COMMENT '理员管回复',
  `reply_time` datetime DEFAULT NULL,
  `replay_admin_id` int(11) DEFAULT NULL,
  `pics` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_refund_apply
-- ----------------------------
INSERT INTO `o_refund_apply` VALUES ('1', '24', '0.01', '00000', '2', '2013-06-30 00:19:39', '同意退款', '2013-06-30 17:17:03', '20', '[\"20130630001939117.jpg\"]');
INSERT INTO `o_refund_apply` VALUES ('2', '32', '158.00', '我要退', '2', '2013-07-08 22:15:48', '同意退款', '2013-07-08 22:17:43', '2', '[\"20130708221526852.jpg\",\"20130708221534093.jpg\",\"20130708221545560.jpg\"]');
INSERT INTO `o_refund_apply` VALUES ('3', '13', '0.01', '我要退！我要退！！！', '2', '2013-07-27 13:57:54', '同意退款', '2013-07-27 13:59:06', '2', '[\"20130727135729332.jpg\",\"20130727135735860.jpg\",\"20130727135739796.jpg\"]');
INSERT INTO `o_refund_apply` VALUES ('4', '33', '0.01', '退退退', '3', '2013-07-27 14:02:27', '就不退！就不退！！', '2013-07-27 14:02:51', '2', '[\"20130727140213045.png\",\"20130727140225856.jpg\"]');
INSERT INTO `o_refund_apply` VALUES ('5', '14', '0.01', '我要退退退退退.....！！！！', '2', '2013-07-28 12:48:54', '同意退款', '2013-07-28 12:50:14', '2', '[\"20130728124812533.jpg\",\"20130728124852235.jpg\",\"20130728124835264.png\"]');
INSERT INTO `o_refund_apply` VALUES ('6', '18', '0.01', 'dfgsdfgsdfg', '2', '2013-07-28 12:54:51', '同意退款', '2013-07-28 12:55:17', '2', '[\"20130728125450847.jpg\",\"20130728125415969.jpg\",\"20130728125435706.jpg\"]');
INSERT INTO `o_refund_apply` VALUES ('7', '33', '0.01', '退款理由', '2', '2013-07-28 12:58:15', '同意退款', '2013-07-28 12:58:56', '2', '');

-- ----------------------------
-- Table structure for p_category
-- ----------------------------
DROP TABLE IF EXISTS `p_category`;
CREATE TABLE `p_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '上级id',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `leaf` bit(1) DEFAULT NULL COMMENT '是否是叶子',
  `weight` double DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_admin` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_category
-- ----------------------------
INSERT INTO `p_category` VALUES ('233', '-1', '广告营销', '', '\0', '1', '2020-10-21 14:19:30', 'admin');
INSERT INTO `p_category` VALUES ('234', '233', '视频广告', '', '', '1', '2020-10-21 14:20:06', 'admin');
INSERT INTO `p_category` VALUES ('235', '233', '杂志', '', '', '1', '2020-10-21 14:20:40', 'admin');
INSERT INTO `p_category` VALUES ('236', '233', '报纸', '', '', '5', '2020-10-21 14:20:51', 'admin');
INSERT INTO `p_category` VALUES ('237', '-1', '1111', '1111', '\0', '111', '2020-10-22 13:30:38', 'admin');
INSERT INTO `p_category` VALUES ('240', '233', 'ggg', 'gggg', '\0', '222', '2020-11-03 14:52:23', 'admin');
INSERT INTO `p_category` VALUES ('244', '237', 'fhfgh', 'fghgfh', '', '6', '2020-11-03 14:58:44', 'admin');
INSERT INTO `p_category` VALUES ('245', '237', 'kjkh', 'hjk', '', '7', '2020-11-03 15:00:29', 'admin');
INSERT INTO `p_category` VALUES ('251', '237', '32', '323', '\0', '3232', '2020-11-03 16:50:28', 'admin');
INSERT INTO `p_category` VALUES ('252', '237', '1212', '2121', '\0', '212', '2020-11-03 16:50:49', 'admin');
INSERT INTO `p_category` VALUES ('255', '251', 'fgfg', 'dfgdg', '', '45', '2020-11-03 17:04:02', 'admin');
INSERT INTO `p_category` VALUES ('257', '251', 'tertiary', '2323', '', '333', '2020-11-03 17:21:14', 'admin');
INSERT INTO `p_category` VALUES ('263', '251', 'llk', 'lk', '', '8', '2020-11-03 17:44:14', 'admin');
INSERT INTO `p_category` VALUES ('265', '251', 'uytyu', 'tutyu', '', '7', '2020-11-03 17:45:42', 'admin');
INSERT INTO `p_category` VALUES ('266', '251', 'dfgsfg', 'dfgsfdg', '', '4', '2020-11-03 17:45:57', 'admin');
INSERT INTO `p_category` VALUES ('267', '251', 'hhhg', 'ffg', '', '5', '2020-11-04 16:47:59', 'admin');

-- ----------------------------
-- Table structure for p_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `p_evaluation`;
CREATE TABLE `p_evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `type` tinyint(4) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `agree` int(11) DEFAULT '0' COMMENT '认为此评价有用人数',
  `product_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `attitude_score` double(2,1) DEFAULT NULL COMMENT '服务态度得分',
  `describe_score` double(2,1) DEFAULT NULL COMMENT '描述相符得分',
  `speed_score` double(2,1) DEFAULT NULL COMMENT '发货速度得分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for p_product
-- ----------------------------
DROP TABLE IF EXISTS `p_product`;
CREATE TABLE `p_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `main_pic` varchar(100) DEFAULT NULL COMMENT '主图',
  `pic_filenames` varchar(500) DEFAULT NULL COMMENT '轮播图',
  `price` decimal(10,2) DEFAULT NULL COMMENT '销售价',
  `original_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `cost_price` decimal(10,2) DEFAULT NULL COMMENT '成本价',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_admin` varchar(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `category_id` int(11) DEFAULT NULL COMMENT '分类',
  `all_category_ids` varchar(150) DEFAULT NULL COMMENT '所有的分类id',
  `stock` int(6) DEFAULT NULL COMMENT '库存',
  `sales_num` int(6) DEFAULT '0' COMMENT '销量',
  `virtual_sales_num` int(6) DEFAULT '0' COMMENT '虚拟销量',
  `evaluation_num` int(6) DEFAULT '0' COMMENT '评价数量',
  `evaluation_score` double(3,2) DEFAULT '0.00' COMMENT '商品评价的总平均分',
  `properties` varchar(1000) DEFAULT NULL,
  `note` varchar(50) DEFAULT NULL COMMENT '备用字段',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_product
-- ----------------------------

-- ----------------------------
-- Table structure for p_product_detail
-- ----------------------------
DROP TABLE IF EXISTS `p_product_detail`;
CREATE TABLE `p_product_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `detail` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of p_product_detail
-- ----------------------------
INSERT INTO `p_product_detail` VALUES ('1', '702', '<p>sdsdfsdfdf</p>');
INSERT INTO `p_product_detail` VALUES ('2', '703', '<p>dfsdfdsf</p>');
INSERT INTO `p_product_detail` VALUES ('3', '704', '<p>dfsdfdsf</p>');
INSERT INTO `p_product_detail` VALUES ('4', '314', '<p>hhhhh</p>');
INSERT INTO `p_product_detail` VALUES ('5', '260', '<p>hhhfd</p>');
INSERT INTO `p_product_detail` VALUES ('6', '257', '<p>jhjghdfgh</p>');
INSERT INTO `p_product_detail` VALUES ('7', '700', '<p>ooooo</p>');
INSERT INTO `p_product_detail` VALUES ('8', '258', '<p>大方的说法手动阀发送</p><p>dfgdagfdfgsdfgsd</p><p><br></p><p>的分公司发光时代</p>');
INSERT INTO `p_product_detail` VALUES ('9', '695', '<p>sdfasdf</p>');

-- ----------------------------
-- Table structure for p_product_log
-- ----------------------------
DROP TABLE IF EXISTS `p_product_log`;
CREATE TABLE `p_product_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `admin_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_product_log
-- ----------------------------

-- ----------------------------
-- Table structure for p_property
-- ----------------------------
DROP TABLE IF EXISTS `p_property`;
CREATE TABLE `p_property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryid` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '属性名',
  `property_values` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1768 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_property
-- ----------------------------
INSERT INTO `p_property` VALUES ('1599', '214', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1600', '214', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1601', '214', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1602', '214', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1603', '214', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1604', '214', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1605', '214', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1606', '214', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1607', '214', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1608', '214', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1609', '214', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1610', '214', '电压（家装）', '110v,12V,220V,24V,36V,90-260V,其它');
INSERT INTO `p_property` VALUES ('1611', '214', '货号', '其他');
INSERT INTO `p_property` VALUES ('1612', '215', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1613', '215', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1614', '215', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1615', '215', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1616', '215', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1617', '215', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健,金凯欧');
INSERT INTO `p_property` VALUES ('1618', '215', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1619', '215', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1620', '215', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1621', '215', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1622', '215', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1623', '215', '货号', '其他');
INSERT INTO `p_property` VALUES ('1624', '216', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1625', '216', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1626', '216', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1627', '216', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1628', '216', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1629', '216', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健,金凯欧');
INSERT INTO `p_property` VALUES ('1630', '216', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1631', '216', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1632', '216', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1633', '216', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1634', '216', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1635', '216', '货号', '其他');
INSERT INTO `p_property` VALUES ('1636', '217', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1637', '217', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1638', '217', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1639', '217', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1640', '217', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1641', '217', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1642', '217', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1643', '217', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1644', '217', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1645', '217', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1646', '217', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1647', '217', '货号', '其他');
INSERT INTO `p_property` VALUES ('1648', '218', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1649', '218', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1650', '218', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1651', '218', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1652', '218', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1653', '218', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1654', '218', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1655', '218', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1656', '218', '适合的光源功率', '0-5W,100-300W,11-15W,16-20W,21-30W,300W以上,31-40W,41-50W,51-60W,6-10W,61-70W,71-80W,81-100W,100W,108W（36W  H管*3根）,10W（T5-2D灯管）,110W（55W  H管）,110W（55W  H管*2）,114W（28W+38W+48),120W（40W*3）,160W（40W *4根,160W（40W*4）,160W（T6-40W灯管*4根）,165W（55W  H管*3根）,16W（T5-2D灯管）,192W（24W *8）,21W（T5-2D灯管）,220V,220W（55W  H管*4根）,22W（T5环管-22W灯管）');
INSERT INTO `p_property` VALUES ('1657', '218', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1658', '218', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1659', '218', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1660', '218', '适用空间', '餐厅,饭厅,客厅,客厅灯,其它,书房,卧室,卫生间,厨房,儿童房,会议室,小客厅,阳台,阁楼,房间');
INSERT INTO `p_property` VALUES ('1661', '218', '货号', '其他');
INSERT INTO `p_property` VALUES ('1662', '219', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1663', '219', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1664', '219', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1665', '219', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1666', '219', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1667', '219', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1668', '219', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1669', '219', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1670', '219', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1671', '219', '货号', '其他');
INSERT INTO `p_property` VALUES ('1672', '220', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1673', '220', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1674', '220', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,Tonsomo唐三毛,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1675', '220', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1676', '220', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1677', '220', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1678', '220', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1679', '220', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1680', '220', '货号', '其他');
INSERT INTO `p_property` VALUES ('1681', '221', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1682', '221', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1683', '221', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,荧辉,三立,同健,金凯欧');
INSERT INTO `p_property` VALUES ('1684', '221', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1685', '221', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1686', '221', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1687', '221', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1688', '221', '货号', '其他');
INSERT INTO `p_property` VALUES ('1689', '222', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1690', '222', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1691', '222', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1692', '222', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1693', '222', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1694', '222', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,荧辉,三立,同健,金凯欧');
INSERT INTO `p_property` VALUES ('1695', '222', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1696', '222', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1697', '222', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1698', '222', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1699', '222', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1700', '222', '货号', '其他');
INSERT INTO `p_property` VALUES ('1701', '223', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1702', '223', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1703', '223', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1704', '223', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1705', '223', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1706', '223', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1707', '223', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1708', '223', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1709', '223', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1710', '223', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,荧辉,三立,同健,金凯欧');
INSERT INTO `p_property` VALUES ('1711', '223', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1712', '223', '电压（家装）', '110v,12V,220V,24V,36V,90-260V,其它');
INSERT INTO `p_property` VALUES ('1713', '223', '货号', '其他');
INSERT INTO `p_property` VALUES ('1714', '226', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1715', '226', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1716', '226', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1717', '226', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1718', '226', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1719', '226', '光源功率', '0-5W,6-10W,11-15W,16-20W,21-30W,31-40W,41-50W,51-60W,61-70W,71-80W,81-100W,100-300W,300W以上');
INSERT INTO `p_property` VALUES ('1720', '226', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,DP久量,华夏风,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1721', '226', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1722', '226', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1723', '226', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1724', '226', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1725', '226', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1726', '228', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1727', '228', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1728', '228', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1729', '228', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1730', '228', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1731', '228', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,华夏风,荧辉,三立,同健,金凯欧');
INSERT INTO `p_property` VALUES ('1732', '228', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1733', '228', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1734', '228', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1735', '228', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1736', '228', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1737', '228', '货号', '其他');
INSERT INTO `p_property` VALUES ('1738', '229', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1739', '229', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1740', '229', '品牌', '其他,Panasonic松下,Philips飞利浦,Midea美的,Osram欧司朗,OPPLE欧普照明,NVC雷士,Siemens西门子,Simon西蒙,梅兰日兰,FIREFLY萤火虫,伦灯灯饰,闪弦sxuan,华艺,CHNT正泰电工,PPG,AOZZO奥朵,Gerui Lighting格睿灯饰,Yunyalighting韵雅灯饰,格灯堡,光印,好视力,华森,品信,松鼠家居,随缘,大丰,雷克,NATSEN耐特森,Patriot艾邦照明,TCL,LONON朗能,JOSEN,Maple Leaf Times枫时代,G&#38;Y广域,新特丽,西顿,顺凯,佛山照明,KaiJingfort凯晶堡,诺金顿,Zenping正品,佛森,DP久量,华夏风,荧辉,三立,同健');
INSERT INTO `p_property` VALUES ('1741', '229', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1742', '229', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1743', '229', '货号', '其他');
INSERT INTO `p_property` VALUES ('1744', '230', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1745', '230', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1746', '230', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1747', '230', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1748', '230', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1750', '230', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1751', '230', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1752', '230', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1753', '230', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1754', '230', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1755', '230', '货号', '其他');
INSERT INTO `p_property` VALUES ('1756', '231', '工艺', '电镀铬色,拉丝垂吊,电动冲孔,磨花,雕刻,镂空雕花,热弯,染色,锻打,手工编织,鎏金,喷漆磨砂,其他工艺');
INSERT INTO `p_property` VALUES ('1757', '231', '光源个数', '1,2,3,4,5,6,7,8,9,10,10-15,15-20,20以上');
INSERT INTO `p_property` VALUES ('1758', '231', '灯身材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,丝线,高分子聚合物,合金');
INSERT INTO `p_property` VALUES ('1759', '231', '颜色', '白色,米色,卡其,粉红,红色,紫色,橙色,黄色,金色,棕色,绿色,蓝色,黑色,银色,其他,军绿色,天蓝色,巧克力色,浅灰色,浅绿色,浅黄色,深灰色,深紫色,深蓝色,紫罗兰,花色,透明,褐色,酒红色');
INSERT INTO `p_property` VALUES ('1760', '231', '灯罩材质', '铁艺,木质竹质,不锈钢,水晶,PVC塑料,石材,布艺,铜,铝质,草藤柳编,人造云石,玻璃钢,亚克力,玻璃琉璃,毛皮,树脂,陶瓷软陶,光纤,聚丙烯PP塑料,贝壳,云石,石膏,丝线,高分子聚合物,纸,合金');
INSERT INTO `p_property` VALUES ('1762', '231', '适用空间', '卧室,客厅,餐厅,厨房,卫浴,书房,玄关,儿童房,阳台,飘窗,其他,过道,楼梯,室外');
INSERT INTO `p_property` VALUES ('1763', '231', '光源类型', '白炽灯,节能灯,荧光灯,卤钨灯,LED灯,高压钠灯,高压汞灯,金卤灯,PAR灯,其他,石英灯,卤素灯,无极灯');
INSERT INTO `p_property` VALUES ('1764', '231', '是否带光源', '带普通光源,不带光源,带LED光源');
INSERT INTO `p_property` VALUES ('1765', '231', '风格', '田园,现代简约,中式,欧式,韩式,日式,混搭,卡通,复古怀旧,北欧宜家,其他,美式乡村,地中海,东南亚,新古典后现代,明清古典');
INSERT INTO `p_property` VALUES ('1766', '231', '照射面积', '1-3平方米,3-5平方米,5-10平方米,10-15平方米,15-30平方米,30平方米以上');
INSERT INTO `p_property` VALUES ('1767', '231', '货号', '其他');

-- ----------------------------
-- Table structure for p_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `p_snapshot`;
CREATE TABLE `p_snapshot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_snapshot
-- ----------------------------

-- ----------------------------
-- Table structure for s_admin
-- ----------------------------
DROP TABLE IF EXISTS `s_admin`;
CREATE TABLE `s_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `real_name` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_admin` varchar(50) DEFAULT NULL COMMENT '创建人',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1：正常，2：冻结',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员';

-- ----------------------------
-- Records of s_admin
-- ----------------------------
INSERT INTO `s_admin` VALUES ('2', 'admin', 'e10adc3949ba59abbe56e057f20f883e', null, null, '超级管理员', '2013-03-17 22:30:31', '2020-11-12 14:39:53', '2', '1', '2020-11-12 14:39:53');

-- ----------------------------
-- Table structure for s_admin_login_record
-- ----------------------------
DROP TABLE IF EXISTS `s_admin_login_record`;
CREATE TABLE `s_admin_login_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员id',
  `admin_user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间',
  `os` varchar(50) DEFAULT NULL COMMENT '操作系统',
  `device` varchar(50) DEFAULT NULL COMMENT '设备',
  `browser` varchar(50) DEFAULT NULL COMMENT '浏览器',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of s_admin_login_record
-- ----------------------------
INSERT INTO `s_admin_login_record` VALUES ('1', '2', 'admin', '0:0:0:0:0:0:0:1', null, null, null, null);
INSERT INTO `s_admin_login_record` VALUES ('2', '2', 'admin', '0:0:0:0:0:0:0:1', null, 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('3', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-04 19:06:39', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('4', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-10 14:50:00', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('5', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-10 14:52:01', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('6', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 11:05:23', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('7', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 11:52:30', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('8', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 16:00:49', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('9', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 16:56:11', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('10', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 17:06:08', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('11', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 17:10:20', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('12', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 17:11:38', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('13', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 17:16:28', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('14', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 17:26:41', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('15', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-11 18:17:34', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('16', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-12 11:08:47', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('17', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-12 11:43:09', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('18', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-12 14:37:44', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('19', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-12 14:38:02', 'Windows 10', 'Computer', 'Chrome 8');
INSERT INTO `s_admin_login_record` VALUES ('20', '2', 'admin', '0:0:0:0:0:0:0:1', '2020-11-12 14:39:53', 'Windows 10', 'Computer', 'Chrome 8');

-- ----------------------------
-- Table structure for s_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `s_admin_role`;
CREATE TABLE `s_admin_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of s_admin_role
-- ----------------------------
INSERT INTO `s_admin_role` VALUES ('31', '2', '8');
INSERT INTO `s_admin_role` VALUES ('32', '21', '8');
INSERT INTO `s_admin_role` VALUES ('33', '21', '10');
INSERT INTO `s_admin_role` VALUES ('34', '26', '10');
INSERT INTO `s_admin_role` VALUES ('35', '26', '12');
INSERT INTO `s_admin_role` VALUES ('36', '26', '14');
INSERT INTO `s_admin_role` VALUES ('38', '23', '8');
INSERT INTO `s_admin_role` VALUES ('39', '23', '10');
INSERT INTO `s_admin_role` VALUES ('40', '27', '14');

-- ----------------------------
-- Table structure for s_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_permission`;
CREATE TABLE `s_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL COMMENT '父级权限id',
  `title` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `type` tinyint(2) DEFAULT NULL COMMENT '权限类型：1->菜单；2->按钮（接口绑定权限）',
  `href` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `weight` double(5,2) DEFAULT NULL COMMENT '排序',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of s_permission
-- ----------------------------
INSERT INTO `s_permission` VALUES ('20', '33', '权限管理', 'permission:mgt', '', '1', '/page/permission-list.html', '2020-10-15 12:39:06', '3.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('28', '33', '角色管理', 'role:mgt', '', '1', '/page/role-list.html', '2020-10-16 18:45:45', '2.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('33', null, '系统管理', 'sys:mgt', 'layui-icon layui-icon-set', '1', '', '2020-10-19 10:23:37', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('34', '20', '添加权限', 'permission:add', '', '2', '', '2020-10-19 11:45:02', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('35', '20', '修改权限', 'permission:update', '', '2', '', '2020-10-19 12:52:11', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('36', '20', '删除权限', 'permission:del', '', '2', '', '2020-10-19 13:00:26', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('38', '28', '修改角色', 'role:update', '', '2', '', '2020-10-19 14:24:53', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('39', '28', '创建角色', 'role:add', '', '2', '', '2020-10-20 10:09:55', '2.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('40', '33', '管理员管理', 'admin:mgt', '', '1', '/page/admin-list.html', '2020-10-20 11:44:12', '10.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('41', '40', '创建管理员', 'admin:add', '', '2', '', '2020-10-20 13:03:53', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('42', '40', '删除管理员', 'admin:del', '', '2', '', '2020-10-20 13:05:06', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('43', '40', '修改管理员', 'admin:update', '', '2', '', '2020-10-20 13:05:42', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('44', '40', '冻结启用', 'admin:status', '', '2', '', '2020-10-20 13:09:39', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('45', '28', '删除角色', 'role:del', '', '2', '', '2020-10-20 13:25:26', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('48', null, '运营管理', 'biz:mgt', 'layui-icon layui-icon-service', '1', '', '2020-10-20 16:19:32', '10.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('49', '48', '商品管理', 'product:mgt', '', '1', '/page/product-list.html', '2020-10-20 16:20:12', '10.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('50', '48', '分类管理', 'category:mgt', '', '1', '/page/category-list.html', '2020-10-20 17:27:50', '5.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('51', null, '用户管理', 'user:mgt', 'layui-icon layui-icon-user', '1', '/page/user-list.html', '2020-10-20 18:52:52', '3.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('52', '49', '添加商品', 'product:add', '', '2', '', '2020-10-20 19:07:23', '8.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('53', '54', '订单列表', 'order:mgt', '', '1', '/page/order-list.html', '2020-10-20 19:08:39', '10.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('54', null, '交易管理', 'trade:mgt', 'layui-icon layui-icon-cart', '1', '', '2020-10-20 19:09:56', '5.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('55', '54', '待发货订单', 'order:pay-list', '', '1', '/page/order-paid-list.html', '2020-10-20 19:14:05', '5.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('56', '54', '待退款订单', 'order:refund-list', '', '1', '/page/order-refund-list.html', '2020-10-20 19:15:13', '3.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('57', '77', '广告位管理', 'ad-space:mgt', '', '1', '/page/ad-space-list.html', '2020-10-20 19:21:03', '3.00', '2020-11-11 06:11:14');
INSERT INTO `s_permission` VALUES ('58', '57', '添加广告位', 'ad-space:add', '', '2', '', '2020-10-20 19:22:07', '5.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('59', '50', '添加分类', 'category:add', '', '2', '', '2020-10-21 13:53:14', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('60', '50', '删除分类', 'category:del', '', '2', '', '2020-10-21 13:55:02', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('61', '50', '修改分类', 'category:update', '', '2', '', '2020-10-21 14:21:30', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('62', '49', '修改商品', 'product:update', '', '2', '', '2020-10-22 22:17:09', '5.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('63', '49', '删除商品', 'product:del', '', '2', '', '2020-10-22 22:20:37', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('64', '49', '上架商品', 'product:publish', '', '2', '', '2020-10-24 21:46:58', '2.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('65', '49', '下架商品', 'product:suspend', '', '2', '', '2020-10-24 21:47:32', '2.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('66', '57', '删除广告位', 'ad-space:del', '', '2', '', '2020-10-25 12:02:43', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('67', '57', '修改广告位', 'ad-space:update', '', '2', '', '2020-10-25 12:03:09', '2.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('68', '57', '广告管理', 'ad:mgt', '', '2', '', '2020-10-25 21:09:48', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('69', '68', '修改广告', 'ad:update', '', '2', '', '2020-10-25 22:40:39', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('70', '68', '删除广告', 'ad:del', '', '2', '', '2020-10-25 22:41:37', '1.00', '2020-10-25 22:48:18');
INSERT INTO `s_permission` VALUES ('71', '68', '添加广告', 'ad:add', '', '2', '', '2020-10-26 14:00:21', '3.00', '2020-10-26 14:00:21');
INSERT INTO `s_permission` VALUES ('72', '68', '发布广告', 'ad:publish', '', '2', '', '2020-10-27 14:27:37', '1.00', '2020-10-27 14:27:36');
INSERT INTO `s_permission` VALUES ('73', '51', '修改用户', 'user:update', '', '2', '', '2020-10-28 18:43:37', '1.00', '2020-10-28 18:43:36');
INSERT INTO `s_permission` VALUES ('74', '51', '删除用户', 'user:del', '', '2', '', '2020-10-28 18:44:12', '1.00', '2020-10-28 18:44:12');
INSERT INTO `s_permission` VALUES ('75', '51', '用户冻结启用', 'user:status', '', '2', '', '2020-10-31 11:59:36', '1.00', '2020-10-31 03:59:35');
INSERT INTO `s_permission` VALUES ('76', '33', '登录日志', 'admin:login-record', '', '1', '/page/login-record.html', '2020-11-04 19:17:19', '7.00', '2020-11-04 11:17:19');
INSERT INTO `s_permission` VALUES ('77', null, '推广管理', 'promote:mgt', 'layui-icon layui-icon-gift', '1', '', '2020-11-11 11:02:17', '8.00', '2020-11-11 03:15:02');

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_admin` varchar(50) DEFAULT '0',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES ('8', '管理员', '555', null, '0', '2020-10-25 22:48:02');
INSERT INTO `s_role` VALUES ('12', 'ffff', 'fff', '2020-10-20 10:11:07', '1', '2020-10-25 22:48:02');
INSERT INTO `s_role` VALUES ('14', 'jjjj', 'jjj', '2020-10-20 11:08:54', '4', '2020-10-25 22:48:02');

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL,
  `permission_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=805 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of s_role_permission
-- ----------------------------
INSERT INTO `s_role_permission` VALUES ('38', '9', '33');
INSERT INTO `s_role_permission` VALUES ('39', '9', '34');
INSERT INTO `s_role_permission` VALUES ('40', '9', '35');
INSERT INTO `s_role_permission` VALUES ('41', '9', '20');
INSERT INTO `s_role_permission` VALUES ('42', '9', '36');
INSERT INTO `s_role_permission` VALUES ('43', '9', '37');
INSERT INTO `s_role_permission` VALUES ('136', '10', '33');
INSERT INTO `s_role_permission` VALUES ('137', '10', '34');
INSERT INTO `s_role_permission` VALUES ('138', '10', '35');
INSERT INTO `s_role_permission` VALUES ('139', '10', '20');
INSERT INTO `s_role_permission` VALUES ('140', '10', '36');
INSERT INTO `s_role_permission` VALUES ('141', '10', '37');
INSERT INTO `s_role_permission` VALUES ('142', '10', '38');
INSERT INTO `s_role_permission` VALUES ('143', '10', '28');
INSERT INTO `s_role_permission` VALUES ('164', '14', '33');
INSERT INTO `s_role_permission` VALUES ('165', '14', '34');
INSERT INTO `s_role_permission` VALUES ('166', '14', '35');
INSERT INTO `s_role_permission` VALUES ('167', '14', '20');
INSERT INTO `s_role_permission` VALUES ('168', '14', '36');
INSERT INTO `s_role_permission` VALUES ('169', '14', '37');
INSERT INTO `s_role_permission` VALUES ('170', '14', '38');
INSERT INTO `s_role_permission` VALUES ('171', '14', '39');
INSERT INTO `s_role_permission` VALUES ('172', '14', '28');
INSERT INTO `s_role_permission` VALUES ('763', '8', '64');
INSERT INTO `s_role_permission` VALUES ('764', '8', '65');
INSERT INTO `s_role_permission` VALUES ('765', '8', '66');
INSERT INTO `s_role_permission` VALUES ('766', '8', '67');
INSERT INTO `s_role_permission` VALUES ('767', '8', '68');
INSERT INTO `s_role_permission` VALUES ('768', '8', '69');
INSERT INTO `s_role_permission` VALUES ('769', '8', '70');
INSERT INTO `s_role_permission` VALUES ('770', '8', '71');
INSERT INTO `s_role_permission` VALUES ('771', '8', '72');
INSERT INTO `s_role_permission` VALUES ('772', '8', '73');
INSERT INTO `s_role_permission` VALUES ('773', '8', '74');
INSERT INTO `s_role_permission` VALUES ('774', '8', '75');
INSERT INTO `s_role_permission` VALUES ('775', '8', '20');
INSERT INTO `s_role_permission` VALUES ('776', '8', '28');
INSERT INTO `s_role_permission` VALUES ('777', '8', '33');
INSERT INTO `s_role_permission` VALUES ('778', '8', '34');
INSERT INTO `s_role_permission` VALUES ('779', '8', '35');
INSERT INTO `s_role_permission` VALUES ('780', '8', '36');
INSERT INTO `s_role_permission` VALUES ('781', '8', '38');
INSERT INTO `s_role_permission` VALUES ('782', '8', '39');
INSERT INTO `s_role_permission` VALUES ('783', '8', '40');
INSERT INTO `s_role_permission` VALUES ('784', '8', '41');
INSERT INTO `s_role_permission` VALUES ('785', '8', '42');
INSERT INTO `s_role_permission` VALUES ('786', '8', '43');
INSERT INTO `s_role_permission` VALUES ('787', '8', '44');
INSERT INTO `s_role_permission` VALUES ('788', '8', '45');
INSERT INTO `s_role_permission` VALUES ('789', '8', '48');
INSERT INTO `s_role_permission` VALUES ('790', '8', '49');
INSERT INTO `s_role_permission` VALUES ('791', '8', '50');
INSERT INTO `s_role_permission` VALUES ('792', '8', '51');
INSERT INTO `s_role_permission` VALUES ('793', '8', '52');
INSERT INTO `s_role_permission` VALUES ('794', '8', '53');
INSERT INTO `s_role_permission` VALUES ('795', '8', '54');
INSERT INTO `s_role_permission` VALUES ('796', '8', '55');
INSERT INTO `s_role_permission` VALUES ('797', '8', '56');
INSERT INTO `s_role_permission` VALUES ('798', '8', '57');
INSERT INTO `s_role_permission` VALUES ('799', '8', '58');
INSERT INTO `s_role_permission` VALUES ('800', '8', '59');
INSERT INTO `s_role_permission` VALUES ('801', '8', '60');
INSERT INTO `s_role_permission` VALUES ('802', '8', '61');
INSERT INTO `s_role_permission` VALUES ('803', '8', '62');
INSERT INTO `s_role_permission` VALUES ('804', '8', '63');

-- ----------------------------
-- Table structure for u_cart
-- ----------------------------
DROP TABLE IF EXISTS `u_cart`;
CREATE TABLE `u_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_cart
-- ----------------------------
INSERT INTO `u_cart` VALUES ('27', '48', '2013-06-14 23:02:23', '{\"cartItems\":[{\"addTime\":1371222143165,\"categoryid\":215,\"id\":682,\"name\":\"凯晶堡楼梯过道壁灯铁艺水晶装饰壁灯灯饰灯具4151\",\"picUrl\":\"/images/product/682/thumbnail/20130614230038044.jpg\",\"price\":178,\"quantity\":1,\"stock\":100},{\"addTime\":1371223360435,\"categoryid\":214,\"id\":827,\"name\":\"灯饰灯具 后古典现代布艺吊灯花艺卧室客厅吊灯CH040-5 \",\"picUrl\":\"/images/product/827/thumbnail/20130614231032477.jpg\",\"price\":568,\"quantity\":1,\"stock\":60}]}');
INSERT INTO `u_cart` VALUES ('28', null, '2013-06-15 13:20:50', '{\"cartItems\":[{\"addTime\":1371273650618,\"categoryid\":217,\"id\":934,\"name\":\"灯饰 灯具 吸顶灯 客厅灯 卧室灯 玫瑰花灯 田园灯30811-3 预定 \",\"picUrl\":\"/images/product/934/thumbnail/20130614231647787.jpg\",\"price\":148,\"quantity\":1,\"stock\":79}]}');
INSERT INTO `u_cart` VALUES ('29', '66', '2013-06-15 13:30:54', '{\"cartItems\":[{\"addTime\":1371969517393,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('30', '53', '2013-06-15 22:49:27', '{\"cartItems\":[]}');
INSERT INTO `u_cart` VALUES ('31', null, '2013-06-16 16:59:04', '{\"cartItems\":[{\"addTime\":1371373144297,\"categoryid\":218,\"id\":933,\"name\":\"灯饰灯具 吸顶灯客厅灯卧室灯 田园灯饰灯具KGB30811-5 \",\"picUrl\":\"/images/product/933/thumbnail/20130614231644689.jpg\",\"price\":385,\"quantity\":1,\"stock\":15}]}');
INSERT INTO `u_cart` VALUES ('32', null, '2013-06-16 21:50:39', '{\"cartItems\":[{\"addTime\":1371390639034,\"categoryid\":218,\"id\":692,\"name\":\"【新品】灯饰金色黄色水晶吸顶灯客厅卧室照明灯具8268-500 \",\"picUrl\":\"/images/product/692/thumbnail/20130614230120990.jpg\",\"price\":868,\"quantity\":1,\"stock\":9}]}');
INSERT INTO `u_cart` VALUES ('33', null, '2013-06-17 15:56:00', '{\"cartItems\":[{\"addTime\":1371455760799,\"categoryid\":218,\"id\":937,\"name\":\"儿童房创意LED灯饰卧室吸顶灯具LED照明节能灯温馨浪漫灯1071\",\"picUrl\":\"/images/product/937/thumbnail/20130614231705246.jpg\",\"price\":265,\"quantity\":1,\"stock\":80}]}');
INSERT INTO `u_cart` VALUES ('34', '60', '2013-06-17 16:34:04', '{\"cartItems\":[{\"addTime\":1371458044962,\"categoryid\":216,\"id\":903,\"name\":\"灯饰灯具 镜前灯 浴室现代灯具 KGB5047短 \",\"picUrl\":\"/images/product/903/thumbnail/20130614231456498.jpg\",\"price\":58,\"quantity\":1,\"stock\":65}]}');
INSERT INTO `u_cart` VALUES ('35', null, '2013-06-17 17:04:42', '{\"cartItems\":[{\"addTime\":1371459893586,\"categoryid\":218,\"id\":937,\"name\":\"儿童房创意LED灯饰卧室吸顶灯具LED照明节能灯温馨浪漫灯1071\",\"picUrl\":\"/images/product/937/thumbnail/20130614231705246.jpg\",\"price\":265,\"quantity\":2,\"stock\":80}]}');
INSERT INTO `u_cart` VALUES ('36', '63', '2013-06-18 13:44:15', '{\"cartItems\":[]}');
INSERT INTO `u_cart` VALUES ('37', '64', '2013-06-18 13:50:32', '{\"cartItems\":[{\"addTime\":1371534632335,\"categoryid\":218,\"id\":933,\"name\":\"灯饰灯具 吸顶灯客厅灯卧室灯 田园灯饰灯具KGB30811-5 \",\"picUrl\":\"/images/product/933/thumbnail/20130614231644689.jpg\",\"price\":385,\"quantity\":1,\"stock\":15},{\"addTime\":1371620302080,\"categoryid\":218,\"id\":937,\"name\":\"儿童房创意LED灯饰卧室吸顶灯具LED照明节能灯温馨浪漫灯1071\",\"picUrl\":\"/images/product/937/thumbnail/20130614231705246.jpg\",\"price\":265,\"quantity\":1,\"stock\":80}]}');
INSERT INTO `u_cart` VALUES ('38', '52', '2013-06-18 13:59:24', '{\"cartItems\":[{\"addTime\":1371910667463,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('39', null, '2013-06-19 13:38:21', '{\"cartItems\":[{\"addTime\":1371620302080,\"categoryid\":218,\"id\":937,\"name\":\"儿童房创意LED灯饰卧室吸顶灯具LED照明节能灯温馨浪漫灯1071\",\"picUrl\":\"/images/product/937/thumbnail/20130614231705246.jpg\",\"price\":265,\"quantity\":1,\"stock\":80}]}');
INSERT INTO `u_cart` VALUES ('40', '54', '2013-06-19 13:41:00', '{\"cartItems\":[{\"addTime\":1371958979071,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('41', null, '2013-06-22 22:17:47', '{\"cartItems\":[{\"addTime\":1371910667463,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('42', null, '2013-06-23 11:42:59', '{\"cartItems\":[{\"addTime\":1371958979071,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('43', null, '2013-06-23 14:35:41', '{\"cartItems\":[{\"addTime\":1371969355625,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('44', null, '2013-06-23 14:38:27', '{\"cartItems\":[{\"addTime\":1371969507118,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":1,\"status\":1,\"stock\":1000}]}');
INSERT INTO `u_cart` VALUES ('45', null, '2013-06-23 17:09:03', '{\"cartItems\":[{\"addTime\":1371978543026,\"categoryid\":0,\"id\":358,\"name\":\"升级款LED灯带LED灯条超高亮SMD3528贴片灯带60珠【终极促销】\",\"picUrl\":\"/images/product/358/thumbnail/20130622105655855.jpg\",\"price\":8.8,\"quantity\":1,\"status\":1,\"stock\":17352}]}');
INSERT INTO `u_cart` VALUES ('46', null, '2013-06-23 17:09:03', '{\"cartItems\":[{\"addTime\":1371978543146,\"categoryid\":0,\"id\":358,\"name\":\"升级款LED灯带LED灯条超高亮SMD3528贴片灯带60珠【终极促销】\",\"picUrl\":\"/images/product/358/thumbnail/20130622105655855.jpg\",\"price\":8.8,\"quantity\":1,\"status\":1,\"stock\":17352}]}');
INSERT INTO `u_cart` VALUES ('47', null, '2013-06-23 17:09:04', '{\"cartItems\":[{\"addTime\":1371978544223,\"categoryid\":0,\"id\":358,\"name\":\"升级款LED灯带LED灯条超高亮SMD3528贴片灯带60珠【终极促销】\",\"picUrl\":\"/images/product/358/thumbnail/20130622105655855.jpg\",\"price\":8.8,\"quantity\":1,\"status\":1,\"stock\":17352}]}');
INSERT INTO `u_cart` VALUES ('48', null, '2013-06-23 17:09:04', '{\"cartItems\":[{\"addTime\":1371978544311,\"categoryid\":0,\"id\":358,\"name\":\"升级款LED灯带LED灯条超高亮SMD3528贴片灯带60珠【终极促销】\",\"picUrl\":\"/images/product/358/thumbnail/20130622105655855.jpg\",\"price\":8.8,\"quantity\":1,\"status\":1,\"stock\":17352}]}');
INSERT INTO `u_cart` VALUES ('49', null, '2013-06-23 17:09:05', '{\"cartItems\":[{\"addTime\":1371978545315,\"categoryid\":0,\"id\":358,\"name\":\"升级款LED灯带LED灯条超高亮SMD3528贴片灯带60珠【终极促销】\",\"picUrl\":\"/images/product/358/thumbnail/20130622105655855.jpg\",\"price\":8.8,\"quantity\":1,\"status\":1,\"stock\":17352}]}');
INSERT INTO `u_cart` VALUES ('50', null, '2013-06-23 17:09:05', '{\"cartItems\":[{\"addTime\":1371978576022,\"categoryid\":0,\"id\":358,\"name\":\"升级款LED灯带LED灯条超高亮SMD3528贴片灯带60珠【终极促销】\",\"picUrl\":\"/images/product/358/thumbnail/20130622105655855.jpg\",\"price\":8.8,\"quantity\":10,\"status\":1,\"stock\":17352}]}');
INSERT INTO `u_cart` VALUES ('51', null, '2013-06-24 10:21:23', '{\"cartItems\":[{\"addTime\":1372040484007,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":943}]}');
INSERT INTO `u_cart` VALUES ('52', null, '2013-06-24 12:33:52', '{\"cartItems\":[{\"addTime\":1372048432341,\"categoryid\":215,\"id\":259,\"name\":\"凯晶堡楼梯过道壁灯铁艺水晶装饰壁灯灯饰灯具4151\",\"picUrl\":\"/images/product/259/thumbnail/20130622104646783.jpg\",\"price\":178,\"quantity\":1,\"status\":1,\"stock\":100}]}');
INSERT INTO `u_cart` VALUES ('53', '61', '2013-06-24 15:01:20', '{\"cartItems\":[]}');
INSERT INTO `u_cart` VALUES ('54', null, '2013-06-25 12:52:47', '{\"cartItems\":[{\"addTime\":1372135967460,\"categoryid\":218,\"id\":255,\"name\":\"灯具简约现代客厅灯水晶灯时尚吸顶灯亚克力温馨时尚创意卧室灯饰\",\"picUrl\":\"/images/product/255/thumbnail/20130622104607963.jpg\",\"price\":698,\"quantity\":1,\"status\":1,\"stock\":20}]}');
INSERT INTO `u_cart` VALUES ('55', '68', '2013-06-27 11:37:15', '{\"cartItems\":[{\"addTime\":1372327101038,\"categoryid\":214,\"id\":506,\"name\":\"测试支付1分钱，请勿购买\",\"picUrl\":\"/images/product/506/thumbnail/20130622220256440.jpg\",\"price\":0.01,\"quantity\":3,\"status\":1,\"stock\":1006}]}');
INSERT INTO `u_cart` VALUES ('56', null, '2013-06-28 13:21:17', '{\"cartItems\":[{\"addTime\":1372396877832,\"categoryid\":215,\"id\":259,\"name\":\"凯晶堡楼梯过道壁灯铁艺水晶装饰壁灯灯饰灯具4151\",\"picUrl\":\"/images/product/259/thumbnail/20130622104646783.jpg\",\"price\":178,\"quantity\":1,\"status\":1,\"stock\":100}]}');
INSERT INTO `u_cart` VALUES ('57', null, '2013-06-28 13:21:18', '{\"cartItems\":[{\"addTime\":1372396878089,\"categoryid\":215,\"id\":259,\"name\":\"凯晶堡楼梯过道壁灯铁艺水晶装饰壁灯灯饰灯具4151\",\"picUrl\":\"/images/product/259/thumbnail/20130622104646783.jpg\",\"price\":178,\"quantity\":1,\"status\":1,\"stock\":100}]}');
INSERT INTO `u_cart` VALUES ('58', null, '2013-07-14 17:37:37', '{\"cartItems\":[{\"addTime\":1373794657094,\"categoryid\":218,\"id\":255,\"name\":\"灯具简约现代客厅灯水晶灯时尚吸顶灯亚克力温馨时尚创意卧室灯饰\",\"picUrl\":\"/images/product/255/thumbnail/20130622104607963.jpg\",\"price\":698,\"quantity\":1,\"status\":1,\"stock\":21}]}');
INSERT INTO `u_cart` VALUES ('59', null, '2013-08-06 20:17:54', '{\"cartItems\":[{\"addTime\":1375791474074,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('60', null, '2013-08-06 20:17:55', '{\"cartItems\":[{\"addTime\":1375791475897,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('61', null, '2013-08-06 20:17:57', '{\"cartItems\":[{\"addTime\":1375791477060,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('62', null, '2013-08-06 20:17:57', '{\"cartItems\":[{\"addTime\":1375791477423,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('63', null, '2013-08-06 20:17:57', '{\"cartItems\":[{\"addTime\":1375791477448,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('64', null, '2013-08-06 20:17:57', '{\"cartItems\":[{\"addTime\":1375791477477,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('65', null, '2013-08-06 20:17:57', '{\"cartItems\":[{\"addTime\":1375791477806,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('66', null, '2013-08-06 20:17:57', '{\"cartItems\":[{\"addTime\":1375791477858,\"categoryid\":0,\"id\":391,\"name\":\"E27大功率3*1W LED节能光源 LED节能灯球泡灯正品高亮10个包邮\",\"picUrl\":\"/images/product/391/thumbnail/20130622105916513.jpg\",\"price\":16,\"quantity\":1,\"status\":1,\"stock\":944}]}');
INSERT INTO `u_cart` VALUES ('67', '67', '2013-08-06 20:37:23', '{\"cartItems\":[{\"addTime\":1375792643305,\"categoryid\":218,\"id\":323,\"name\":\"新款现代简约客厅卧室灯饰LED水晶莲花装饰浪漫照明灯具8518\",\"picUrl\":\"/images/product/323/thumbnail/20130622105339894.jpg\",\"price\":438,\"quantity\":1,\"status\":1,\"stock\":30},{\"addTime\":1375795561225,\"categoryid\":218,\"id\":255,\"name\":\"灯具简约现代客厅灯水晶灯时尚吸顶灯亚克力温馨时尚创意卧室灯饰\",\"picUrl\":\"/images/product/255/thumbnail/20130622104607963.jpg\",\"price\":698,\"quantity\":1,\"status\":1,\"stock\":21}]}');
INSERT INTO `u_cart` VALUES ('68', null, '2013-08-08 18:28:18', '{\"cartItems\":[{\"addTime\":1375957698710,\"categoryid\":219,\"id\":356,\"name\":\"凯晶堡 LED天花灯射灯3W暖白开孔8-8.5水晶玻璃秒杀 水晶天花灯\",\"picUrl\":\"/images/product/356/thumbnail/20130622105642908.jpg\",\"price\":24.8,\"quantity\":1,\"status\":1,\"stock\":860}]}');
INSERT INTO `u_cart` VALUES ('69', null, '2013-08-08 18:29:15', '{\"cartItems\":[{\"addTime\":1375957799982,\"categoryid\":215,\"id\":488,\"name\":\"田园烂漫风 过道灯 卧室床头灯 壁灯 玫瑰花K B30811-2\",\"picUrl\":\"/images/product/488/thumbnail/20130622110808036.jpg\",\"price\":130,\"quantity\":5,\"status\":1,\"stock\":54}]}');
INSERT INTO `u_cart` VALUES ('70', null, '2013-08-12 16:46:13', '{\"cartItems\":[{\"addTime\":1376297173632,\"categoryid\":214,\"id\":274,\"name\":\"新款现代简约餐厅吊灯水晶莲花灯饰LED装饰照明灯具吊灯8518 \",\"picUrl\":\"/images/product/274/thumbnail/20130622104821585.jpg\",\"price\":398,\"quantity\":1,\"status\":1,\"stock\":59}]}');
INSERT INTO `u_cart` VALUES ('71', null, '2013-09-14 03:48:55', '{\"cartItems\":[{\"addTime\":1379101736016,\"categoryid\":220,\"id\":406,\"name\":\"灯饰灯具 现代简约灯可旋转调节摇臂灯  卧室灯吸顶灯\",\"picUrl\":\"/images/product/406/thumbnail/20130622110026826.jpg\",\"price\":138,\"quantity\":8,\"status\":1,\"stock\":54}]}');
INSERT INTO `u_cart` VALUES ('72', '75', '2013-10-08 15:18:53', '{\"cartItems\":[{\"addTime\":1381216734488,\"categoryid\":219,\"id\":320,\"name\":\"水晶LED天花灯天花射灯LED射灯水晶壳射灯筒灯孔灯\",\"picUrl\":\"/images/product/320/thumbnail/20130622105328050.jpg\",\"price\":48,\"quantity\":1,\"status\":1,\"stock\":51}]}');

-- ----------------------------
-- Table structure for u_consignee_info
-- ----------------------------
DROP TABLE IF EXISTS `u_consignee_info`;
CREATE TABLE `u_consignee_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `region` varchar(100) DEFAULT NULL COMMENT '地区',
  `address` varchar(255) DEFAULT NULL,
  `postcode` varchar(20) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `default_use` tinyint(1) DEFAULT '0' COMMENT '是否为默认收货地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_consignee_info
-- ----------------------------
INSERT INTO `u_consignee_info` VALUES ('1', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '123123', '64', '0');
INSERT INTO `u_consignee_info` VALUES ('2', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '2123123', '64', '0');
INSERT INTO `u_consignee_info` VALUES ('4', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '55555', '54', '0');
INSERT INTO `u_consignee_info` VALUES ('5', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '222222', '54', '1');
INSERT INTO `u_consignee_info` VALUES ('6', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '22222', '63', '1');
INSERT INTO `u_consignee_info` VALUES ('7', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '', '53', '1');
INSERT INTO `u_consignee_info` VALUES ('8', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '', '52', '1');
INSERT INTO `u_consignee_info` VALUES ('9', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '', '66', '1');
INSERT INTO `u_consignee_info` VALUES ('10', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '100038', '61', '1');
INSERT INTO `u_consignee_info` VALUES ('12', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '', '70', '1');
INSERT INTO `u_consignee_info` VALUES ('13', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '', '71', '1');
INSERT INTO `u_consignee_info` VALUES ('14', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '', '72', '1');
INSERT INTO `u_consignee_info` VALUES ('15', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '710016 ', '74', '1');
INSERT INTO `u_consignee_info` VALUES ('16', '刘老大', '13888888888', '', '北京 北京市 海淀区', '街道地址街道地址街道地址', '111111', '75', '1');

-- ----------------------------
-- Table structure for u_favorite
-- ----------------------------
DROP TABLE IF EXISTS `u_favorite`;
CREATE TABLE `u_favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `pic_url` varchar(100) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `objectid` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_favorite
-- ----------------------------
INSERT INTO `u_favorite` VALUES ('4', '灯饰灯具 后古典现代布艺吊灯花艺卧室客厅吊灯CH040-5 ', '/images/product/827/thumbnail/20130614231032477.jpg', '568.00', '1', '827', '2013-06-14 23:22:36', '48');
INSERT INTO `u_favorite` VALUES ('5', '新款灯饰灯具 简约现代米兰时尚卧室餐厅吊灯书房客厅灯烟灰色', '/images/product/708/thumbnail/20130614230256667.jpg', '228.00', '1', '708', '2013-06-14 23:26:49', '48');
INSERT INTO `u_favorite` VALUES ('8', '灯具简约现代客厅灯水晶灯时尚吸顶灯亚克力温馨时尚创意卧室灯饰', '/images/product/255/thumbnail/20130622104607963.jpg', '698.00', '1', '255', '2013-07-04 23:25:50', '53');

-- ----------------------------
-- Table structure for u_open_user
-- ----------------------------
DROP TABLE IF EXISTS `u_open_user`;
CREATE TABLE `u_open_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `user_info` varchar(2000) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `access_token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_open_user
-- ----------------------------
INSERT INTO `u_open_user` VALUES ('33', '233013963', '清远', '2013-06-15 10:21:24', '{\"id\":233013963,\"name\":\"清远\",\"avatar\":[{\"type\":\"avatar\",\"url\":\"http://hdn.xnimg.cn/photos/hdn111/20090322/22/25/head_rxaP_193858c204234.jpg\"},{\"type\":\"tiny\",\"url\":\"http://hdn.xnimg.cn/photos/hdn411/20090322/22/25/tiny_L7PO_193450m204234.jpg\"},{\"type\":\"main\",\"url\":\"http://hdn.xnimg.cn/photos/hdn111/20090322/22/25/main_prNd_193858c204234.jpg\"},{\"type\":\"large\",\"url\":\"http://hdn.xnimg.cn/photos/hdn111/20090322/22/25/large_FFCi_193552f204234.jpg\"}]}', '52', '4', '236566|6.bc8ec9e768cea038877870b4f4c7f89f.2592000.1377579600-233013963');
INSERT INTO `u_open_user` VALUES ('34', '3A64666F21027CFE4CFFB533B3197C08', '网事如云', '2013-06-15 10:21:55', '{\"ret\":0,\"msg\":\"\",\"nickname\":\"网事如云\",\"gender\":\"男\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/100432186/3A64666F21027CFE4CFFB533B3197C08/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/100432186/3A64666F21027CFE4CFFB533B3197C08/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/100432186/3A64666F21027CFE4CFFB533B3197C08/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/100432186/3A64666F21027CFE4CFFB533B3197C08/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/100432186/3A64666F21027CFE4CFFB533B3197C08/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}', '53', '1', 'DD1E906D00C7B79FC1178B618A83BFC7');
INSERT INTO `u_open_user` VALUES ('35', '2011601695', '程序猿清远', '2013-06-15 10:22:43', '{\"id\":2011601695,\"idstr\":\"2011601695\",\"screen_name\":\"程序猿清远\",\"name\":\"程序猿清远\",\"province\":\"11\",\"city\":\"8\",\"location\":\"北京 海淀区\",\"description\":\"在京奋斗的程序员\",\"url\":\"http://blog.sina.com.cn/u/2011601695\",\"profile_image_url\":\"http://tp4.sinaimg.cn/2011601695/50/5644713994/1\",\"profile_url\":\"2011liuhui\",\"domain\":\"2011liuhui\",\"weihao\":\"\",\"gender\":\"m\",\"followers_count\":68,\"friends_count\":36,\"statuses_count\":489,\"favourites_count\":8,\"created_at\":\"Mon Mar 07 11:57:06 +0800 2011\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Tue Sep 03 09:47:40 +0800 2013\",\"id\":3618381590175564,\"mid\":\"3618381590175564\",\"idstr\":\"3618381590175564\",\"text\":\"@中国联通网上营业厅 在网上交个费，昨天提示在升级晚上22点结束，今天再试又变成“尊敬的用户，现为系统升级时间，请您于09月05日22:30时后再进行操作！”还有完没完了！难道你们不知道系统升级都是在半夜吗？[怒]\",\"source\":\"<a href=\\\"http://app.weibo.com/t/feed/1sxHP2\\\" rel=\\\"nofollow\\\">专业版微博<\\/a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_urls\":[],\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0}},\"ptype\":0,\"allow_all_comment\":true,\"avatar_large\":\"http://tp4.sinaimg.cn/2011601695/180/5644713994/1\",\"avatar_hd\":\"http://tp4.sinaimg.cn/2011601695/180/5644713994/1\",\"verified_reason\":\"\",\"follow_me\":false,\"online_status\":0,\"bi_followers_count\":13,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0}', '54', '2', '2.00dUTIMCdDa7vB68e100cb440eUTyK');
INSERT INTO `u_open_user` VALUES ('36', '1393075244', '小磊磊的快乐', '2013-06-17 16:32:43', '{\"id\":1393075244,\"idstr\":\"1393075244\",\"screen_name\":\"小磊磊的快乐\",\"name\":\"小磊磊的快乐\",\"province\":\"11\",\"city\":\"1000\",\"location\":\"北京\",\"description\":\"\",\"url\":\"http://blog.sina.com.cn/wodexiluo\",\"profile_image_url\":\"http://tp1.sinaimg.cn/1393075244/50/1300771821/0\",\"profile_url\":\"u/1393075244\",\"domain\":\"\",\"weihao\":\"\",\"gender\":\"f\",\"followers_count\":97,\"friends_count\":488,\"statuses_count\":183,\"favourites_count\":3,\"created_at\":\"Sun Mar 07 20:12:06 +0800 2010\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Mon Jun 17 16:32:43 +0800 2013\",\"id\":3590217271422470,\"mid\":\"3590217271422470\",\"idstr\":\"3590217271422470\",\"text\":\"刚注册了凯晶堡灯饰，感觉小清新啊。>>http://t.cn/zT3Srlu @凯晶堡商城 \",\"source\":\"<a href=\\\"http://app.weibo.com/t/feed/2QjNER\\\" rel=\\\"nofollow\\\">凯晶堡商城<\\/a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_urls\":[],\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0}},\"allow_all_comment\":true,\"avatar_large\":\"http://tp1.sinaimg.cn/1393075244/180/1300771821/0\",\"verified_reason\":\"\",\"follow_me\":false,\"online_status\":1,\"bi_followers_count\":70,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0}', '60', '2', '2.00YVMRWBdDa7vB27445bf000ZjvQRC');
INSERT INTO `u_open_user` VALUES ('37', '2C0651145A832F53841A8E224F1303C6', 'flydream', '2013-06-17 16:33:03', '{\"ret\":0,\"msg\":\"\",\"nickname\":\"flydream\",\"gender\":\"女\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/100432186/2C0651145A832F53841A8E224F1303C6/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/100432186/2C0651145A832F53841A8E224F1303C6/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/100432186/2C0651145A832F53841A8E224F1303C6/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/100432186/2C0651145A832F53841A8E224F1303C6/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/100432186/2C0651145A832F53841A8E224F1303C6/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}', '61', '1', '706616C06513699524275E6C8258230B');
INSERT INTO `u_open_user` VALUES ('38', '724871037', '凯晶堡', '2013-06-18 13:43:38', '{\"uid\":\"724871037\",\"uname\":\"凯晶堡\",\"portrait\":\"e92be587afe699b6e5a0a1a230\"}', '63', '3', null);
INSERT INTO `u_open_user` VALUES ('39', '1107386369', 'znerl', '2013-06-18 13:50:19', '{\"uid\":\"1107386369\",\"uname\":\"znerl\",\"portrait\":\"2e427a6e65726cde05\"}', '64', '3', null);
INSERT INTO `u_open_user` VALUES ('40', '4179378585', 'abc简单的幸福', '2013-06-22 19:29:51', '{\"uid\":\"4179378585\",\"uname\":\"abc简单的幸福\",\"portrait\":\"0ff9616263e7ae80e58d95e79a84e5b9b8e7a68f4618\"}', '66', '3', null);
INSERT INTO `u_open_user` VALUES ('41', '75BA5E39E836B2980B5CE13778D9F01C', '凯晶堡灯饰', '2013-06-23 17:50:57', '{\"ret\":0,\"msg\":\"\",\"nickname\":\"凯晶堡灯饰\",\"gender\":\"男\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/100432186/75BA5E39E836B2980B5CE13778D9F01C/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/100432186/75BA5E39E836B2980B5CE13778D9F01C/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/100432186/75BA5E39E836B2980B5CE13778D9F01C/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/100432186/75BA5E39E836B2980B5CE13778D9F01C/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/100432186/75BA5E39E836B2980B5CE13778D9F01C/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}', '67', '1', 'D4FE3084D82486FEC7514511AC5F292D');
INSERT INTO `u_open_user` VALUES ('42', '3298743182', '凯晶堡商城', '2013-07-02 10:18:02', '{\"id\":3298743182,\"idstr\":\"3298743182\",\"screen_name\":\"凯晶堡商城\",\"name\":\"凯晶堡商城\",\"province\":\"11\",\"city\":\"8\",\"location\":\"北京 海淀区\",\"description\":\"凯晶堡灯饰商城，专注灯饰\",\"url\":\"\",\"profile_image_url\":\"http://tp3.sinaimg.cn/3298743182/50/5661057836/1\",\"profile_url\":\"kaijingbao\",\"domain\":\"kaijingbao\",\"weihao\":\"\",\"gender\":\"m\",\"followers_count\":33,\"friends_count\":24,\"statuses_count\":81,\"favourites_count\":0,\"created_at\":\"Sun Apr 07 13:54:32 +0800 2013\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Tue Jul 02 10:18:02 +0800 2013\",\"id\":3595558797314663,\"mid\":\"3595558797314663\",\"idstr\":\"3595558797314663\",\"text\":\"刚注册了凯晶堡灯饰，感觉小清新啊。>>http://t.cn/zT3Srlu @凯晶堡商城 \",\"source\":\"<a href=\\\"http://app.weibo.com/t/feed/2QjNER\\\" rel=\\\"nofollow\\\">凯晶堡商城<\\/a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_urls\":[],\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0}},\"allow_all_comment\":true,\"avatar_large\":\"http://tp3.sinaimg.cn/3298743182/180/5661057836/1\",\"avatar_hd\":\"\",\"verified_reason\":\"\",\"follow_me\":false,\"online_status\":0,\"bi_followers_count\":23,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0}', '68', '2', '2.00MnLPbDdDa7vB885560207bEG17GB');
INSERT INTO `u_open_user` VALUES ('43', '2192403917', 'fdsafdfdfer', '2013-07-02 10:53:07', '{\"id\":2192403917,\"idstr\":\"2192403917\",\"screen_name\":\"fdsafdfdfer\",\"name\":\"fdsafdfdfer\",\"province\":\"100\",\"city\":\"1000\",\"location\":\"其他\",\"description\":\"\",\"url\":\"\",\"profile_image_url\":\"http://tp2.sinaimg.cn/2192403917/50/0/1\",\"profile_url\":\"u/2192403917\",\"domain\":\"\",\"weihao\":\"\",\"gender\":\"m\",\"followers_count\":13,\"friends_count\":1,\"statuses_count\":46,\"favourites_count\":0,\"created_at\":\"Wed Jun 22 14:09:33 +0800 2011\",\"following\":false,\"allow_all_act_msg\":false,\"geo_enabled\":true,\"verified\":false,\"verified_type\":-1,\"remark\":\"\",\"status\":{\"created_at\":\"Fri Jun 28 09:49:17 +0800 2013\",\"id\":3594102010569001,\"mid\":\"3594102010569001\",\"idstr\":\"3594102010569001\",\"text\":\"灯饰灯具 时尚现代云朵玻璃吸顶灯客厅灯卧室灯00086-3_凯晶堡商城 http://t.cn/zHkSKWj\",\"source\":\"<a href=\\\"http://app.weibo.com/t/feed/2afzVX\\\" rel=\\\"nofollow\\\">百度分享<\\/a>\",\"favorited\":false,\"truncated\":false,\"in_reply_to_status_id\":\"\",\"in_reply_to_user_id\":\"\",\"in_reply_to_screen_name\":\"\",\"pic_urls\":[],\"geo\":null,\"reposts_count\":0,\"comments_count\":0,\"attitudes_count\":0,\"mlevel\":0,\"visible\":{\"type\":0,\"list_id\":0}},\"allow_all_comment\":true,\"avatar_large\":\"http://tp2.sinaimg.cn/2192403917/180/0/1\",\"avatar_hd\":\"\",\"verified_reason\":\"\",\"follow_me\":false,\"online_status\":0,\"bi_followers_count\":1,\"lang\":\"zh-cn\",\"star\":0,\"mbtype\":0,\"mbrank\":0,\"block_word\":0}', '69', '2', '2.00V6G45CdDa7vB93a2e71ed8mpPo3D');
INSERT INTO `u_open_user` VALUES ('44', '5874DD05B21A3FAA6CC44B0673E7D604', '1275310508', '2013-07-06 12:05:23', '{\"ret\":0,\"msg\":\"\",\"nickname\":\"1275310508\",\"gender\":\"男\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/100432186/5874DD05B21A3FAA6CC44B0673E7D604/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/100432186/5874DD05B21A3FAA6CC44B0673E7D604/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/100432186/5874DD05B21A3FAA6CC44B0673E7D604/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/100432186/5874DD05B21A3FAA6CC44B0673E7D604/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/100432186/5874DD05B21A3FAA6CC44B0673E7D604/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}', '70', '1', 'F4A6E7CB788703842E5575895CA4BCF7');
INSERT INTO `u_open_user` VALUES ('45', 'A78079048F48A735206E1D29B241B881', '啈冨瞳話', '2013-10-08 15:18:42', '{\"ret\":0,\"msg\":\"\",\"nickname\":\"啈冨瞳話\",\"gender\":\"男\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/100432186/A78079048F48A735206E1D29B241B881/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/100432186/A78079048F48A735206E1D29B241B881/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/100432186/A78079048F48A735206E1D29B241B881/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/100432186/A78079048F48A735206E1D29B241B881/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/100432186/A78079048F48A735206E1D29B241B881/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}', '75', '1', '2962B3FF4CAD6216A232FF3FB4CBE48D');
INSERT INTO `u_open_user` VALUES ('46', '7C1049CA6107CE2AA0CAFF477AEB9E9E', 'One by one', '2013-10-10 11:55:56', '{\"ret\":0,\"msg\":\"\",\"nickname\":\"One by one\",\"gender\":\"男\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/100432186/7C1049CA6107CE2AA0CAFF477AEB9E9E/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/100432186/7C1049CA6107CE2AA0CAFF477AEB9E9E/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/100432186/7C1049CA6107CE2AA0CAFF477AEB9E9E/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/100432186/7C1049CA6107CE2AA0CAFF477AEB9E9E/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/100432186/7C1049CA6107CE2AA0CAFF477AEB9E9E/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}', '76', '1', 'A376C3A086100DA0835B621DCDEC6EF9');

-- ----------------------------
-- Table structure for u_session_token
-- ----------------------------
DROP TABLE IF EXISTS `u_session_token`;
CREATE TABLE `u_session_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_token` varchar(100) NOT NULL,
  `user_id` int(11) NOT NULL,
  `remote_address` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `login_date` date DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `session_token` (`session_token`) USING BTREE,
  KEY `login_date` (`login_date`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=279 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_session_token
-- ----------------------------
INSERT INTO `u_session_token` VALUES ('139', 'c6c9ad3e-680a-41a5-89ad-e97699fc2545', '48', '222.128.173.26', null, '2020-11-14 23:16:36', '2020-11-11 17:18:56', '2020-11-14', null);
INSERT INTO `u_session_token` VALUES ('140', 'ead44c2c-9393-4666-beb3-3c3ad442b01e', '49', '221.221.144.71', null, '2020-11-15 09:57:58', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('141', 'e5fb33c0-7cea-4083-809f-abae0e51d7f7', '50', '221.221.144.71', null, '2020-11-15 10:09:36', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('142', '470a622b-a8d8-4249-9a5e-2fd93d2f4475', '51', '221.221.144.71', null, '2020-11-15 10:12:20', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('143', '1894737a-0a06-4091-9e62-5468fba0372c', '48', '221.221.144.71', null, '2020-11-15 10:13:11', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('144', '0a355768-b5b8-4e55-9e3d-7bb10b368490', '52', '221.221.144.71', null, '2020-11-15 10:21:24', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('145', '740e61d8-1f4d-411e-a1d4-ca510254fb9c', '53', '221.221.144.71', null, '2020-11-15 10:21:56', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('146', 'db6d509e-6b9a-4c72-b8f0-a6166380bc17', '54', '221.221.144.71', null, '2020-11-15 10:22:43', '2020-11-11 17:18:56', '2020-11-15', null);
INSERT INTO `u_session_token` VALUES ('147', '8d10625e-b44e-44dc-827b-803c612dd3aa', '53', '114.249.215.111', null, '2020-11-16 12:06:07', '2020-11-11 17:18:56', '2020-11-16', null);
INSERT INTO `u_session_token` VALUES ('148', '4b2df929-78b2-48f5-8d6a-b3ceee965bdf', '53', '114.249.215.111', null, '2020-11-16 16:57:24', '2020-11-11 17:18:56', '2020-11-16', null);
INSERT INTO `u_session_token` VALUES ('149', 'dc00c41e-5ee5-4559-951a-7a0d7a0d6356', '53', '114.249.215.111', null, '2020-11-16 21:50:51', '2020-11-11 17:18:56', '2020-11-16', null);
INSERT INTO `u_session_token` VALUES ('150', 'e481fc80-2e7b-4ea6-bc9f-ea71394a69b1', '55', '222.128.178.237', null, '2020-11-17 16:32:43', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('151', '839b8f4f-b65a-4bb5-9415-6a888fe73428', '56', '222.128.178.237', null, '2020-11-17 16:32:53', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('152', '24ed4cb0-1b10-4746-8948-c9c95aa53274', '57', '222.128.178.237', null, '2020-11-17 16:33:04', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('153', '662df5c4-19e6-44ed-864a-c365a67cfcf1', '58', '222.128.178.237', null, '2020-11-17 16:33:06', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('154', '31d10090-91f7-4330-a68a-df1c66d8cc4d', '59', '222.128.178.237', null, '2020-11-17 16:33:07', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('155', 'a4889dbd-65f7-4ffd-b046-7d693e914fa4', '53', '114.247.222.130', null, '2020-11-17 16:34:17', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('156', '391560ea-4b8b-47f9-b69d-c923a1628e53', '53', '114.247.222.130', null, '2020-11-17 16:34:39', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('157', '9de47059-32de-49b6-a56c-e75f968a729c', '60', '222.128.178.237', null, '2020-11-17 16:35:36', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('158', 'c9be7be9-7e28-4a06-bc24-a6e5198e72bd', '61', '222.128.178.237', null, '2020-11-17 16:37:27', '2020-11-11 17:18:56', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('159', '7cf53538-a03d-490d-ac22-b8438c1e4c92', '62', '221.226.111.98', null, '2020-11-18 12:22:57', '2020-11-11 17:18:56', '2020-11-18', null);
INSERT INTO `u_session_token` VALUES ('160', '26a41f85-3ab1-431a-81d0-223bc3708d27', '63', '114.247.222.130', null, '2020-11-18 13:43:38', '2020-11-11 17:18:56', '2020-11-18', null);
INSERT INTO `u_session_token` VALUES ('161', 'c416e4c9-38ab-4624-bc56-ae56e5ed0a97', '64', '114.247.222.130', null, '2020-11-18 13:50:19', '2020-11-11 17:18:56', '2020-11-18', null);
INSERT INTO `u_session_token` VALUES ('162', '12649326-df1f-4bb0-94ed-64f002aff390', '52', '114.247.222.130', null, '2020-11-18 14:07:24', '2020-11-11 17:18:56', '2020-11-18', null);
INSERT INTO `u_session_token` VALUES ('163', '4f8e78a7-bbb9-4e4f-83f9-97a4760c86c5', '65', '114.247.222.130', null, '2020-11-18 14:26:47', '2020-11-11 17:18:56', '2020-11-18', null);
INSERT INTO `u_session_token` VALUES ('164', '254b0411-bfa3-4fb6-9bdf-e39d22eaa8f5', '65', '114.247.222.130', null, '2020-11-18 14:26:58', '2020-11-11 17:18:56', '2020-11-18', null);
INSERT INTO `u_session_token` VALUES ('165', '778177b8-68ae-4532-a02a-a21d48ef444f', '64', '114.247.222.130', null, '2020-11-19 13:38:33', '2020-11-11 17:18:56', '2020-11-19', null);
INSERT INTO `u_session_token` VALUES ('166', 'e02d3333-8df3-413c-b3d6-182bc3a3295b', '54', '114.247.222.130', null, '2020-11-19 13:40:51', '2020-11-11 17:18:56', '2020-11-19', null);
INSERT INTO `u_session_token` VALUES ('167', 'd6db3e4f-e9f8-4ece-a5a9-b517191f790b', '63', '114.249.235.91', null, '2020-11-19 21:36:37', '2020-11-11 17:18:56', '2020-11-19', null);
INSERT INTO `u_session_token` VALUES ('168', 'e516825e-74f6-4324-ab1e-1ebbf027394e', '66', '222.128.182.243', null, '2020-11-22 19:29:51', '2020-11-11 17:18:56', '2020-11-22', null);
INSERT INTO `u_session_token` VALUES ('169', 'ddd3d98a-191e-421c-8306-ccdacdd90918', '53', '222.128.182.243', null, '2020-11-22 22:01:29', '2020-11-11 17:18:56', '2020-11-22', null);
INSERT INTO `u_session_token` VALUES ('170', '586e23d1-e295-42db-b38b-ba8d75181d13', '52', '222.128.182.243', null, '2020-11-22 22:18:33', '2020-11-11 17:18:56', '2020-11-22', null);
INSERT INTO `u_session_token` VALUES ('171', '5823b4e2-b190-49bb-9618-5a20b3c3c063', '53', '114.249.227.212', null, '2020-11-23 10:55:43', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('172', '80fbf35e-18bf-4857-ad38-6e15c3f66f78', '53', '114.249.227.212', null, '2020-11-23 10:56:58', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('173', 'e82eeb83-2920-4b94-93c2-d24a379ccdae', '54', '114.249.227.212', null, '2020-11-23 11:43:19', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('174', '4ea7c87b-5adf-4df9-9923-7f48fa5ca65f', '54', '114.249.227.212', null, '2020-11-23 12:00:17', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('175', '237f2e61-35f7-4006-a82b-4d0e90d6ee2c', '54', '114.249.227.212', null, '2020-11-23 12:00:28', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('176', '847418d9-d980-4409-ab0d-3b40e7dd0b57', '66', '114.249.227.212', null, '2020-11-23 14:36:04', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('177', '072b8308-23e4-435c-a13b-8eaf07dc46dc', '66', '114.249.227.212', null, '2020-11-23 14:38:34', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('178', '84016149-4ef3-4ac2-b061-2e0b9f3c5ca6', '63', '114.249.227.212', null, '2020-11-23 17:50:33', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('179', '07b1c431-cb12-4cb1-8d6e-9ab99eb78cbb', '67', '114.249.227.212', null, '2020-11-23 17:50:57', '2020-11-11 17:18:56', '2020-11-23', null);
INSERT INTO `u_session_token` VALUES ('180', '76e1b9f2-184e-4f11-93d7-f65368095450', '53', '114.247.222.130', null, '2020-11-24 10:21:36', '2020-11-11 17:18:56', '2020-11-24', null);
INSERT INTO `u_session_token` VALUES ('181', 'e0bbfa4b-78ac-4314-96f0-ac6b0709631e', '53', '114.247.222.130', null, '2020-11-24 13:39:36', '2020-11-11 17:18:56', '2020-11-24', null);
INSERT INTO `u_session_token` VALUES ('182', 'f5bef68d-f017-4a59-a336-dbc301236668', '61', '222.128.169.26', null, '2020-11-24 15:01:00', '2020-11-11 17:18:56', '2020-11-24', null);
INSERT INTO `u_session_token` VALUES ('183', '2896abd8-2846-4fa3-8c9f-766965727e85', '67', '114.247.222.130', null, '2020-11-24 17:45:22', '2020-11-11 17:18:56', '2020-11-24', null);
INSERT INTO `u_session_token` VALUES ('184', '36abdf1e-ea7c-413b-ae4f-59c7706fc8e2', '67', '114.249.225.230', null, '2020-11-24 21:57:01', '2020-11-11 17:18:56', '2020-11-24', null);
INSERT INTO `u_session_token` VALUES ('185', '3ccb6f91-e030-4888-9de0-f97fed151f76', '63', '114.247.222.130', null, '2020-11-25 12:55:04', '2020-11-11 17:18:56', '2020-11-25', null);
INSERT INTO `u_session_token` VALUES ('186', '8f89390a-e587-4596-906e-4eb4e765f13a', '53', '114.247.222.130', null, '2020-11-25 18:18:24', '2020-11-11 17:18:56', '2020-11-25', null);
INSERT INTO `u_session_token` VALUES ('187', 'a247c929-d770-47c2-89c1-6174a3c66864', '61', '114.249.232.209', null, '2020-11-25 22:46:36', '2020-11-11 17:18:56', '2020-11-25', null);
INSERT INTO `u_session_token` VALUES ('188', '7a36e46c-3cba-48eb-a23c-18bd11d47746', '66', '114.249.232.209', null, '2020-11-25 22:51:17', '2020-11-11 17:18:56', '2020-11-25', null);
INSERT INTO `u_session_token` VALUES ('189', 'd319dcec-26ca-4474-92b1-d5aaddb1ef08', '53', '114.247.222.130', null, '2020-11-26 16:09:23', '2020-11-11 17:18:56', '2020-11-26', null);
INSERT INTO `u_session_token` VALUES ('190', '42c15b47-92c5-4967-aefe-fee938ebb0b8', '53', '114.247.222.130', null, '2020-11-28 13:22:23', '2020-11-11 17:18:56', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('191', 'bb9a3fa8-ce35-4451-b42d-2777f8c15025', '61', '114.249.218.64', null, '2020-11-29 23:46:50', '2020-11-11 17:18:56', '2020-11-29', null);
INSERT INTO `u_session_token` VALUES ('192', 'af576554-90d3-46e9-913b-286944c5a7cc', '61', '114.249.218.64', null, '2020-11-30 00:18:58', '2020-11-11 17:18:56', '2020-11-30', null);
INSERT INTO `u_session_token` VALUES ('193', 'a24dbeb4-642b-4398-9d13-aede973fa36c', '61', '222.128.180.123', null, '2020-11-01 11:57:26', '2020-11-11 17:19:00', '2020-11-01', null);
INSERT INTO `u_session_token` VALUES ('194', 'a2282d56-c8f5-4305-b3af-55392b22d854', '68', '114.247.222.130', null, '2020-11-02 10:18:02', '2020-11-11 17:19:00', '2020-11-02', null);
INSERT INTO `u_session_token` VALUES ('195', '517e4574-7b60-4361-a37e-e494585a3807', '69', '114.247.222.130', null, '2020-11-02 10:53:08', '2020-11-11 17:19:00', '2020-11-02', null);
INSERT INTO `u_session_token` VALUES ('196', 'e6c61860-132e-4b9d-af23-d79cc05b6205', '53', '222.128.173.247', null, '2020-11-03 23:23:40', '2020-11-11 17:19:00', '2020-11-03', null);
INSERT INTO `u_session_token` VALUES ('197', '110e38e6-0fe3-42d0-862c-0c802d75989f', '61', '222.128.173.247', null, '2020-11-03 23:25:04', '2020-11-11 17:19:00', '2020-11-03', null);
INSERT INTO `u_session_token` VALUES ('198', 'f6e065a8-6701-4735-aa33-197cba9ed04e', '61', '222.128.173.247', null, '2020-11-03 23:29:47', '2020-11-11 17:19:00', '2020-11-03', null);
INSERT INTO `u_session_token` VALUES ('199', 'ce397f00-c097-4e0c-8f7a-6224fde51f1b', '53', '222.128.181.241', null, '2020-11-04 08:37:26', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('200', '806f7a4a-6a36-4f46-a2c8-ef6717671be3', '53', '114.247.222.130', null, '2020-11-04 14:54:21', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('201', '4afeb721-4617-4471-b930-1623af8d8b65', '68', '114.247.222.130', null, '2020-11-04 14:55:58', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('202', '96b32f12-4185-4fdc-aa62-8b73e304d3c7', '68', '114.247.222.130', null, '2020-11-04 15:00:38', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('203', '05db1aaf-1c5c-462c-ae0b-22968c45fe9f', '64', '114.247.222.130', null, '2020-11-04 15:00:46', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('204', 'e3e6f261-5843-475b-b808-0aa0444ba79e', '52', '114.247.222.130', null, '2020-11-04 15:01:07', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('205', '75bec695-ee44-45a5-8a33-40017c9d3584', '53', '114.249.233.202', null, '2020-11-04 23:25:07', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('206', '0f66a259-d606-4886-a97a-264a1875ceff', '61', '114.249.233.202', null, '2020-11-04 23:40:16', '2020-11-11 17:19:00', '2020-11-04', null);
INSERT INTO `u_session_token` VALUES ('207', '865cb9f6-d212-4017-a881-d2cca11ee646', '70', '114.249.230.6', null, '2020-11-06 12:05:23', '2020-11-11 17:19:00', '2020-11-06', null);
INSERT INTO `u_session_token` VALUES ('208', '39be3f37-3a7b-4491-8755-f497bb9dd860', '71', '114.247.222.130', null, '2020-11-08 16:43:45', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('209', 'c0fe16b8-af53-4172-a522-b0c6b0c266fd', '72', '221.221.154.106', null, '2020-11-08 21:11:33', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('210', '67fe855d-1815-478d-8cdd-a0fedd90cd1b', '72', '221.221.154.106', null, '2020-11-08 21:11:45', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('211', '539be6be-47ac-4c83-85ca-c650667cf5d7', '71', '221.221.154.106', null, '2020-11-08 21:37:18', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('212', '4b412958-0a87-4703-8d8f-d594cc695dea', '71', '221.221.159.230', null, '2020-11-08 21:57:06', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('213', '385c688a-2a86-4a33-a346-07caa46bd7d8', '71', '221.221.159.230', null, '2020-11-08 22:14:28', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('214', 'ca2bd3fc-714f-4649-af30-930445c89dbf', '71', '221.221.159.230', null, '2020-11-08 22:15:00', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('215', 'd72ccd6b-60e1-4cdf-9f47-fee35234f4bf', '72', '221.221.159.230', null, '2020-11-08 22:49:30', '2020-11-11 17:19:00', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('216', 'e54a9266-fa2b-4a83-b5c6-28f7f5976b66', '73', '114.247.222.130', null, '2020-11-09 10:49:42', '2020-11-11 17:19:00', '2020-11-09', null);
INSERT INTO `u_session_token` VALUES ('217', 'e2ec7716-43ad-4890-8926-ddbc79f724f1', '53', '114.249.227.41', null, '2020-11-10 20:54:58', '2020-11-11 17:19:00', '2020-11-10', null);
INSERT INTO `u_session_token` VALUES ('218', 'f68c0b96-aa34-40d3-b7eb-ea9150a55367', '53', '114.247.222.130', null, '2020-11-17 11:54:56', '2020-11-11 17:19:00', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('219', '29038a50-9101-45c5-a93d-2a8eeb9a6801', '53', '114.247.222.130', null, '2020-11-17 16:27:24', '2020-11-11 17:19:00', '2020-11-17', null);
INSERT INTO `u_session_token` VALUES ('220', 'fcacdf38-cd68-46bf-a9ed-1c47dc965b42', '53', '222.128.174.202', null, '2020-11-27 13:39:08', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('221', 'e9383c13-8ee7-414e-a024-e514aad345c3', '53', '222.128.174.202', null, '2020-11-27 13:57:12', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('222', '9d45a249-9787-477b-a891-19def5d7e550', '72', '222.128.174.202', null, '2020-11-27 14:01:49', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('223', '68c51dc2-1131-4491-ac3a-f42d89cfb51e', '72', '222.128.174.202', null, '2020-11-27 14:06:24', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('224', '074ec660-cc61-45b5-ae7d-351bb60792b1', '53', '222.128.174.202', null, '2020-11-27 14:15:41', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('225', 'b260abbe-a5e3-4f24-8d91-9f2347cb7279', '53', '222.128.174.202', null, '2020-11-27 18:44:38', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('226', '2be9f164-1eeb-4d2c-aec0-ac04baacfe2e', '54', '222.128.174.202', null, '2020-11-27 18:45:19', '2020-11-11 17:19:00', '2020-11-27', null);
INSERT INTO `u_session_token` VALUES ('227', '6e275205-9f2d-4307-9611-64f7e32ec3bb', '53', '222.128.169.109', null, '2020-11-28 12:46:38', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('228', '32af27d8-5b4b-49e3-b74d-04513c877224', '54', '222.128.169.109', null, '2020-11-28 12:46:49', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('229', 'f56b2adb-3d7b-401b-9317-799ba10866c8', '63', '222.128.169.109', null, '2020-11-28 12:47:00', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('230', '593a6886-8697-4d89-ad46-8681b146a386', '52', '222.128.169.109', null, '2020-11-28 12:47:32', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('231', '0cd86c60-8f9e-4350-a93d-a3b4ccde7cc9', '54', '222.128.169.109', null, '2020-11-28 12:53:56', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('232', '4118be45-3750-4153-9a5b-15283eff8a85', '63', '222.128.169.109', null, '2020-11-28 12:55:43', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('233', '7116ef7a-98d5-4031-8d30-686690a32ca3', '63', '222.128.169.109', null, '2020-11-28 12:55:51', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('234', '0d20ac51-8358-4a0b-8e7a-1cdcb58e2264', '64', '222.128.169.109', null, '2020-11-28 12:56:22', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('235', '578d7724-2018-4794-9a07-9485e0f355d9', '72', '222.128.169.109', null, '2020-11-28 12:57:37', '2020-11-11 17:19:00', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('236', 'e3f18b8d-5ea1-4d08-80bb-1a4a6b1d06c9', '62', '221.226.111.98', null, '2020-11-07 13:34:15', '2020-11-11 17:18:52', '2020-11-07', null);
INSERT INTO `u_session_token` VALUES ('237', 'bff13414-6ff7-414c-bff2-1f72f2f531bd', '61', '114.249.222.216', null, '2020-11-08 15:49:46', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('238', '029fbae9-4d15-4db9-a339-80c4771a11bd', '61', '114.249.222.216', null, '2020-11-08 15:50:06', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('239', 'a8087f1b-5cce-4064-ba3f-564b1c1f837c', '72', '114.247.222.130', null, '2020-11-08 15:54:55', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('240', '42ebd74f-b826-4f13-b7b4-05246104ab69', '61', '114.249.222.216', null, '2020-11-08 15:55:02', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('241', '4b6512f7-0794-4383-9eed-0b9e9d35bec0', '53', '114.247.222.130', null, '2020-11-08 18:23:14', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('242', '681c979c-33eb-436d-9dd5-959940170a8a', '67', '221.221.150.195', null, '2020-11-08 22:51:27', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('243', 'd918a232-501c-414a-96c6-725db88fb853', '54', '221.221.150.195', null, '2020-11-08 22:54:35', '2020-11-11 17:18:52', '2020-11-08', null);
INSERT INTO `u_session_token` VALUES ('244', 'e8fee419-e5c1-4334-a2ce-a0d71db62adb', '54', '114.249.215.249', null, '2020-11-10 20:47:57', '2020-11-11 17:18:52', '2020-11-10', null);
INSERT INTO `u_session_token` VALUES ('245', '93ea59b7-0be8-4bf6-9e0d-cc11a438e722', '54', '222.128.182.172', null, '2020-11-12 21:15:37', '2020-11-11 17:18:52', '2020-11-12', null);
INSERT INTO `u_session_token` VALUES ('246', 'ef25f184-223a-4a57-a594-427dcace1795', '53', '114.247.222.130', null, '2020-11-13 09:31:20', '2020-11-11 17:18:52', '2020-11-13', null);
INSERT INTO `u_session_token` VALUES ('247', '314bff50-32c8-48a4-acfa-b28df903b07e', '53', '114.247.222.130', null, '2020-11-13 14:46:16', '2020-11-11 17:18:52', '2020-11-13', null);
INSERT INTO `u_session_token` VALUES ('248', 'e6d7ab5c-188c-4598-9278-641800ca45c7', '54', '114.247.222.130', null, '2020-11-28 14:02:16', '2020-11-11 17:18:52', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('249', '9a7910df-eae9-4a52-a187-bd7f04361beb', '53', '114.247.222.130', null, '2020-11-28 16:24:53', '2020-11-11 17:18:52', '2020-11-28', null);
INSERT INTO `u_session_token` VALUES ('250', 'c5b11ede-d2db-4439-884c-937b39517463', '54', '114.247.222.130', null, '2020-09-04 12:30:05', '2020-11-11 17:15:16', '2020-09-04', null);
INSERT INTO `u_session_token` VALUES ('251', '8f02cbe8-cfef-4573-9c63-46943561008e', '54', '114.247.222.130', null, '2020-09-04 16:38:43', '2020-11-11 17:15:16', '2020-09-04', null);
INSERT INTO `u_session_token` VALUES ('252', 'd8d9033e-865d-4098-965e-94d3537b3881', '53', '114.247.222.130', null, '2020-09-17 13:47:55', '2020-11-11 17:15:16', '2020-09-17', null);
INSERT INTO `u_session_token` VALUES ('253', '023c8eac-6481-4491-b225-4c8ea523b0e3', '67', '114.247.222.130', null, '2020-09-24 10:44:42', '2020-11-11 17:15:16', '2020-09-24', null);
INSERT INTO `u_session_token` VALUES ('254', '9c1e4400-3c3d-4dfc-8b48-003b5a747b81', '74', '113.139.181.183', null, '2020-10-08 10:25:30', '2020-11-11 17:15:16', '2020-10-08', null);
INSERT INTO `u_session_token` VALUES ('255', '7cc7a726-0669-4078-aaa7-62f8bc61c519', '74', '1.84.143.73', null, '2020-10-08 13:52:03', '2020-11-11 17:15:16', '2020-10-08', null);
INSERT INTO `u_session_token` VALUES ('256', 'd89574af-e671-453c-b770-e1c4c6656a05', '75', '114.247.222.130', null, '2020-10-08 15:18:43', '2020-11-11 17:15:16', '2020-10-08', null);
INSERT INTO `u_session_token` VALUES ('257', 'f6ebd10e-976b-4a5e-adb2-dd1ff807016f', '74', '1.84.143.73', null, '2020-10-08 17:15:13', '2020-11-11 17:15:16', '2020-10-08', null);
INSERT INTO `u_session_token` VALUES ('258', '963bac16-ba8b-450f-b12b-ab351d290466', '74', '1.84.143.73', null, '2020-10-08 23:20:22', '2020-11-11 17:15:16', '2020-10-08', null);
INSERT INTO `u_session_token` VALUES ('259', 'cc4f3d0b-6a9b-4755-b64e-e8a97f8ed147', '74', '219.145.69.155', null, '2020-10-09 11:19:14', '2020-11-11 17:15:16', '2020-10-09', null);
INSERT INTO `u_session_token` VALUES ('260', '94ce8f45-ce3c-4cca-97ce-fd0f28a233a9', '74', '219.145.69.155', null, '2020-10-09 16:11:28', '2020-11-11 17:15:16', '2020-10-09', null);
INSERT INTO `u_session_token` VALUES ('261', 'b6b47b08-2cb8-4d7c-9a09-e3ab8604253c', '74', '114.247.222.130', null, '2020-10-09 18:15:52', '2020-11-11 17:15:16', '2020-10-09', null);
INSERT INTO `u_session_token` VALUES ('262', '17b6ddcc-2b4f-4a38-8fc7-3e25bd746540', '74', '114.247.222.130', null, '2020-10-10 10:42:52', '2020-11-11 17:15:16', '2020-10-10', null);
INSERT INTO `u_session_token` VALUES ('263', '64805935-2f98-4857-acaa-06884b4bb5df', '74', '114.247.222.130', null, '2020-10-10 10:45:42', '2020-11-11 17:15:16', '2020-10-10', null);
INSERT INTO `u_session_token` VALUES ('264', '2a1a89b1-97fd-48b5-9fef-9ee1f7856327', '76', '114.247.222.130', null, '2020-10-10 11:55:57', '2020-11-11 17:15:16', '2020-10-10', null);
INSERT INTO `u_session_token` VALUES ('265', '851dcf7c-8e27-4d65-9768-dc59cf3c82d6', '74', '125.76.171.115', null, '2020-10-10 18:32:01', '2020-11-11 17:15:16', '2020-10-10', null);
INSERT INTO `u_session_token` VALUES ('266', '7b0a27d1-59de-464b-8e20-e655c998d90c', '72', '114.249.211.222', null, '2020-10-10 19:40:38', '2020-11-11 17:15:16', '2020-10-10', null);
INSERT INTO `u_session_token` VALUES ('267', '7+F+SRJGrkwTMKMEIGpZ2+nHZlCmyQv/CZ01Z2MKvt8=', '77', null, null, '2020-10-28 16:00:24', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('268', 'euT9iJ7gO0SIZNqMRuZYn0Kfpz7Uf8041O/BfsnV6J8=', '79', null, null, '2020-10-28 16:05:22', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('269', 'G3jXmxcYyPO9pAvPldhaSW2MYeGuEqAeqCZs90lQenc=', '80', null, null, '2020-10-28 16:10:32', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('270', 'mMoAHiqx39/UeWbFQ0mFJU/BgUaR1DPCo6R75i0FBjI=', '81', null, null, '2020-10-28 16:11:20', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('271', '90k4886j+lxDCWPqzVIXzsbEM4AKAJr/LW7a1CubC10=', '82', null, null, '2020-10-28 16:17:30', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('272', 'eHOL1d4xABP/0HzyDeNoUZn5UVbLrBwK7Lmz/WjcpNw=', '83', '127.0.0.1', null, '2020-10-28 16:34:38', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('273', 'K1bX+7p5IRBLzljkbBJ0Dp2Ev/LRIL4hLBVZ1JNGekE=', '84', '127.0.0.1', null, '2020-10-28 16:52:02', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('274', 'KNqjTn5vFg07BsCgiCZwzkXrdMOmxWfmHA/TprmsMVE=', '85', '127.0.0.1', null, '2020-10-28 16:56:01', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('275', 'L0jmfel0aLI4M3WUJo/DK11uxB6YSX5q0fQjBQngqC0=', '86', '127.0.0.1', null, '2020-10-28 17:05:32', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('276', 'Q5ttRXYHYRv7v1jd8tiOT4KBz+wCQAMPEiu2mmlkuE4=', '79', '127.0.0.1', '2', '2020-10-28 17:55:45', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('277', 'Q5ttRXYHYRv7v1jd8tiOT/TBwzsgO+PQiDhaetECZNA=', '79', '127.0.0.1', '2', '2020-10-28 17:56:30', '2020-11-11 16:35:43', '2020-10-28', null);
INSERT INTO `u_session_token` VALUES ('278', 'Q5ttRXYHYRv7v1jd8tiOT5fkc1DDlxVRNe4FKlSnRys=', '79', '127.0.0.1', '1', '2020-10-28 17:57:00', '2020-11-11 16:35:43', '2020-10-28', null);

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('52', '清远', null, null, '2', '1', '2013-07-28 12:47:32', '2013-06-15 10:21:24');
INSERT INTO `u_user` VALUES ('53', '网事如云', null, null, '2', '1', '2013-09-17 13:47:55', '2013-06-15 10:21:55');
INSERT INTO `u_user` VALUES ('54', '程序猿清远', null, null, '2', '1', '2013-09-04 16:38:43', '2013-06-15 10:22:43');
INSERT INTO `u_user` VALUES ('60', '小磊磊的快乐', null, null, '2', '1', '2013-06-17 16:35:36', '2013-06-17 16:35:36');
INSERT INTO `u_user` VALUES ('61', 'flydream', null, null, '2', '1', '2013-08-08 15:55:02', '2013-06-17 16:37:27');
INSERT INTO `u_user` VALUES ('62', 'yuwu', '314361665@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '1', '1', '2013-08-07 13:34:15', '2013-06-18 12:22:57');
INSERT INTO `u_user` VALUES ('63', '凯晶堡', null, null, '2', '1', '2013-07-28 12:55:51', '2013-06-18 13:43:38');
INSERT INTO `u_user` VALUES ('64', 'znerl', null, null, '2', '1', '2013-07-28 12:56:22', '2013-06-18 13:50:19');
INSERT INTO `u_user` VALUES ('65', 'aaa', 'dsfdsf@df.df', '4297f44b13955235245b2497399d7a93', '1', '1', '2013-06-18 14:26:58', '2013-06-18 14:26:47');
INSERT INTO `u_user` VALUES ('66', 'abc简单的幸福', null, null, '2', '1', '2013-06-25 22:51:17', '2013-06-22 19:29:51');
INSERT INTO `u_user` VALUES ('67', '凯晶堡商城', null, null, '2', '1', '2013-09-24 10:44:42', '2013-06-23 17:50:57');
INSERT INTO `u_user` VALUES ('68', '凯晶堡商城0', null, null, '2', '1', '2013-07-04 15:00:38', '2013-07-02 10:18:02');
INSERT INTO `u_user` VALUES ('69', 'fdsafdfdfer', null, null, '2', '1', '2013-07-02 10:53:08', '2013-07-02 10:53:07');
INSERT INTO `u_user` VALUES ('70', '1275310508', null, null, '2', '1', '2013-07-06 12:05:23', '2013-07-06 12:05:23');
INSERT INTO `u_user` VALUES ('71', 'liuhui', 'liuhuilhlt@163.com', '4297f44b13955235245b2497399d7a93', '1', '1', '2013-07-08 22:15:00', '2013-07-08 16:43:45');
INSERT INTO `u_user` VALUES ('72', 'admin', '344694665@qq.com', '4297f44b13955235245b2497399d7a93', '1', '1', '2013-10-10 19:40:38', '2013-07-08 21:11:33');
INSERT INTO `u_user` VALUES ('73', 'sfdsadf', 'sdfs.df@df.df', '4297f44b13955235245b2497399d7a93', '1', '1', '2013-07-09 10:49:42', '2013-07-09 10:49:42');
INSERT INTO `u_user` VALUES ('74', '阿德里亚贵', '404648759@qq.com', '3e18b957772bab743c43f7f605440684', '1', '1', '2013-10-10 18:32:01', '2013-10-08 10:25:29');
INSERT INTO `u_user` VALUES ('75', '啈冨瞳話', null, null, '2', '1', '2013-10-08 15:18:43', '2013-10-08 15:18:42');
INSERT INTO `u_user` VALUES ('76', 'One by one', null, null, '2', '1', '2013-10-10 11:55:57', '2013-10-10 11:55:56');
INSERT INTO `u_user` VALUES ('77', 'asdasd', 'sdf@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:00:24');
INSERT INTO `u_user` VALUES ('79', 'ggg', 'sdfs@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:05:21');
INSERT INTO `u_user` VALUES ('80', 'hhhhh', 'addsfd@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:10:32');
INSERT INTO `u_user` VALUES ('81', 'gggg', 'addf22d@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:11:20');
INSERT INTO `u_user` VALUES ('82', 'oooo', 'adrdfd@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:17:30');
INSERT INTO `u_user` VALUES ('83', 'hhh555', 'sdssf@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:34:38');
INSERT INTO `u_user` VALUES ('84', 'gfdf', 'ggff@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:52:01');
INSERT INTO `u_user` VALUES ('85', 'hhddfgasd', 'addssf22d@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 16:56:01');
INSERT INTO `u_user` VALUES ('86', 'hhjjj', 'addfddd2@df.fd', '4297f44b13955235245b2497399d7a93', '1', '1', null, '2020-10-28 17:05:32');
