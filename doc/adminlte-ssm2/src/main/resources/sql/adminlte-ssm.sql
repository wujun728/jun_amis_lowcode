/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : adminlte-ssm

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 28/05/2021 13:50:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `dept_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `dept_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('9a329b597d754c4180c6b40cdfb0f565', '产品部', '产品创新部');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户',
  `title` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志',
  `url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `params` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '参数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '日志时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('017fe2d5a4bef8907e0acff43894e54d', 'admin', '编辑部门', '/system/dept/doEdit', '{\"id\":[\"9a329b597d754c4180c6b40cdfb0f565\"],\"deptName\":[\"产品部\"],\"deptDesc\":[\"产品创新部\"]}', '2021-05-28 13:48:56');
INSERT INTO `sys_log` VALUES ('05b51b2cc55d1d7d0415884b39b31569', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-15 20:13:22');
INSERT INTO `sys_log` VALUES ('334afb9eb3330353f68d61aa8be50181', 'admin', '删除菜单', '/system/menu/delete', '{\"id\":[\"661a1f4a6ec94fa89ec6f90ce77a8eb3\"]}', '2021-05-15 21:34:57');
INSERT INTO `sys_log` VALUES ('431317b2be5651d36f1d2bbd938bdfcb', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-28 13:48:34');
INSERT INTO `sys_log` VALUES ('51cfe5cb45df141046f570b72a393066', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-15 22:27:05');
INSERT INTO `sys_log` VALUES ('551533caa74107b795189696f9c5d2b6', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-15 14:19:18');
INSERT INTO `sys_log` VALUES ('5be9765276ed69f522fb3c562f232cec', 'admin', '更新系统设置', '/system/setting/doSetting', '{\"id\":[\"1\",\"2\",\"3\"],\"sysValue\":[\"HT-ADMIN\",\"HT-ADMIN\",\"Copyright © 2020. All rights reserved.\"]}', '2021-05-15 20:18:04');
INSERT INTO `sys_log` VALUES ('5c6a873b2aed39ef675cd0eefcd53fde', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-16 11:35:26');
INSERT INTO `sys_log` VALUES ('738426b6581c5e1319bb66797718b00d', 'admin', '删除菜单', '/system/menu/delete', '{\"id\":[\"f8db3e5b12d04a46b07026e2828791fc\"]}', '2021-05-15 21:34:52');
INSERT INTO `sys_log` VALUES ('972ec98dc051657a81a3fecca8c3d0bc', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-15 21:35:45');
INSERT INTO `sys_log` VALUES ('b82f6d647e211fc7ed714adcbabf6a3d', 'admin', '用户登录成功', '/login/doLogin', '', '2021-05-15 21:08:49');
INSERT INTO `sys_log` VALUES ('c6e7971b8868521961b0242e46701c11', 'admin', '更新系统设置', '/system/setting/doSetting', '{\"id\":[\"1\",\"2\",\"3\"],\"sysValue\":[\"管理系统\",\"HT\",\"Copyright © 2020. All rights reserved.\"]}', '2021-05-15 21:26:27');
INSERT INTO `sys_log` VALUES ('dc96b9d8a57ab09a61261a3ea04ba8c3', 'admin', '删除菜单', '/system/menu/delete', '{\"id\":[\"42dd5ae31e3a43b3a197440e8ec19a94\"]}', '2021-05-15 20:14:31');
INSERT INTO `sys_log` VALUES ('fe749dc6ca4a4db3dc626d7332e878ba', 'admin', '删除菜单', '/system/menu/delete', '{\"id\":[\"f5a20c82110b4a3ea9e30ca01a038680\"]}', '2021-05-15 20:14:48');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `pid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级菜单ID',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连接地址',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `deep` int(11) NULL DEFAULT NULL COMMENT '深度',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `resource` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('0818e1c76bbd44eba3a698547ec4e637', '查询系统设置', '10', NULL, NULL, 0, 3, '010600', 'listSetting');
INSERT INTO `sys_menu` VALUES ('0c9b5fc8b44b41d1871a8fc8300247ec', '删除菜单', '4', NULL, NULL, 4, 3, '010303', 'deleteMenu');
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '0', NULL, 'fa fa-cogs', 1, 1, '01', NULL);
INSERT INTO `sys_menu` VALUES ('10', '系统配置', '1', '/system/setting/page', ' fa-cog', 6, 2, '0106', 'setting');
INSERT INTO `sys_menu` VALUES ('1db9105008404a3485b6fac30dba3c0e', '查看角色列表', '3', NULL, NULL, 0, 3, '010200', 'listRole');
INSERT INTO `sys_menu` VALUES ('2', '用户管理', '1', '/system/user/list/1', 'fa-user-circle-o', 1, 2, '0101', 'user');
INSERT INTO `sys_menu` VALUES ('22e38e885f9b40b9aae6a36deb78e89c', '部门管理', '1', '/system/dept/list/1', 'fa-graduation-cap', 4, 2, '0104', 'dept');
INSERT INTO `sys_menu` VALUES ('3', '角色管理', '1', '/system/role/list/1', 'fa-users', 2, 2, '0102', 'role');
INSERT INTO `sys_menu` VALUES ('363a778e78a346a68210b2590163a943', '编辑部门', '22e38e885f9b40b9aae6a36deb78e89c', NULL, NULL, 2, 3, '010402', 'editDept');
INSERT INTO `sys_menu` VALUES ('3f26102ef0e04c3c9328cb97067cc5fa', '创建菜单', '4', NULL, NULL, 1, 3, '010301', 'addMenu');
INSERT INTO `sys_menu` VALUES ('3fb6a7a5935b4efabf3de82e7e1baeb6', '新增部门', '22e38e885f9b40b9aae6a36deb78e89c', NULL, NULL, 1, 3, '010401', 'addDept');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', '1', '/system/menu/list/1', 'fa-list', 3, 2, '0103', 'menu');
INSERT INTO `sys_menu` VALUES ('4253190001c1480fb0d631d64d150535', '编辑用户', '2', NULL, NULL, 2, 3, '010102', 'editUser');
INSERT INTO `sys_menu` VALUES ('488ef1eff57b4827acade7c0744278ce', '查看菜单列表', '4', NULL, NULL, 0, 3, '010300', 'listMenu');
INSERT INTO `sys_menu` VALUES ('4e816a9854714d24b0413d929d637a2b', '查看部门列表', '22e38e885f9b40b9aae6a36deb78e89c', NULL, NULL, 0, 3, '010400', 'listDept');
INSERT INTO `sys_menu` VALUES ('5', '业务日志', '1', '/system/log/list/1', 'fa-info-circle', 5, 2, '0105', 'log');
INSERT INTO `sys_menu` VALUES ('5d3dd56c16ff4e32aecae1b3228159c7', '查看日志列表', '5', NULL, NULL, 0, 3, '010500', 'listLog');
INSERT INTO `sys_menu` VALUES ('60dda993d87647f5989c15f14f866df9', '新增角色', '3', NULL, NULL, 1, 3, '010201', 'addRole');
INSERT INTO `sys_menu` VALUES ('649b484b58414d91aefa5a26143e6557', '删除用户', '2', NULL, NULL, 3, 3, '010103', 'deleteUser');
INSERT INTO `sys_menu` VALUES ('686630c7cb624cc786dcdc9958971a6b', '编辑角色', '3', NULL, NULL, 2, 3, '010202', 'editRole');
INSERT INTO `sys_menu` VALUES ('79d78b8357174cac8f44abd275dec597', '删除部门', '22e38e885f9b40b9aae6a36deb78e89c', NULL, NULL, 3, 3, '010403', 'deleteDept');
INSERT INTO `sys_menu` VALUES ('915c309ebe5047b68645b3eb777dd8c9', '操作系统设置', '10', NULL, NULL, 1, 3, '010601', 'doSetting');
INSERT INTO `sys_menu` VALUES ('a5ebf29168234406939856bc6890c86b', '角色授权', '3', NULL, NULL, 4, 3, '010204', 'authRole');
INSERT INTO `sys_menu` VALUES ('a73802e513cc4465883530ec8074abbb', '新增用户', '2', NULL, NULL, 1, 3, '010101', 'addUser');
INSERT INTO `sys_menu` VALUES ('b4e7232189b14cf3ba160cf7b0d3bf37', '删除角色', '3', NULL, NULL, 3, 3, '010203', 'deleteRole');
INSERT INTO `sys_menu` VALUES ('d2bc30feb5474a1bb7e02d48d39a3f8a', '查看用户列表', '2', NULL, NULL, 0, 3, '010100', 'listUser');
INSERT INTO `sys_menu` VALUES ('dc5f478d98ed4297a8ae638fe90df050', '编辑菜单', '4', NULL, NULL, 3, 3, '010302', 'editMenu');
INSERT INTO `sys_menu` VALUES ('f899f3d3baec4571b1c786717f9906fd', '批量删除角色', '3', NULL, NULL, 5, 3, '010205', 'deleteBatchRole');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `role_state` int(2) NULL DEFAULT 1 COMMENT '状态,1-启用,-1禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('b1f9ce5543a049be9f169a3f5e6b72a8', '超级管理员', '超级管理员', 1, '2017-09-14 15:02:16');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('0209d5630f144ffbbee5b87bfb3633b5', 'b1f9ce5543a049be9f169a3f5e6b72a8', '1db9105008404a3485b6fac30dba3c0e');
INSERT INTO `sys_role_menu` VALUES ('04481a17a42b4c179c9d73fb72e58869', 'b1f9ce5543a049be9f169a3f5e6b72a8', '3fb6a7a5935b4efabf3de82e7e1baeb6');
INSERT INTO `sys_role_menu` VALUES ('0a1b67383a244c90a06b2973a9e77ee3', 'b1f9ce5543a049be9f169a3f5e6b72a8', '363a778e78a346a68210b2590163a943');
INSERT INTO `sys_role_menu` VALUES ('1546982656984864a00b403f1c075e02', 'b1f9ce5543a049be9f169a3f5e6b72a8', '5bb55dd90ba44db39d6e3d7f3f316af0');
INSERT INTO `sys_role_menu` VALUES ('234ba688192f4c9aaea36c0ef73b7b7b', 'b1f9ce5543a049be9f169a3f5e6b72a8', '0818e1c76bbd44eba3a698547ec4e637');
INSERT INTO `sys_role_menu` VALUES ('26253d20c5494d999e18298002580bb3', 'b1f9ce5543a049be9f169a3f5e6b72a8', '686630c7cb624cc786dcdc9958971a6b');
INSERT INTO `sys_role_menu` VALUES ('285f9f9815b0494f8cb8cae890dc9d4e', 'b1f9ce5543a049be9f169a3f5e6b72a8', '60dda993d87647f5989c15f14f866df9');
INSERT INTO `sys_role_menu` VALUES ('29b02d61b9374121960c392b0c095b0a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'b1487b113b324e79b7fbadbe28660349');
INSERT INTO `sys_role_menu` VALUES ('2fabcb4bac8d481491bcf8efe91c6e3a', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'a5ebf29168234406939856bc6890c86b');
INSERT INTO `sys_role_menu` VALUES ('34eb1b3c61564ac98e8bfff5ebd4b847', 'b1f9ce5543a049be9f169a3f5e6b72a8', '2');
INSERT INTO `sys_role_menu` VALUES ('4567d99cf52a496787fa65f3a23f1c32', 'b1f9ce5543a049be9f169a3f5e6b72a8', '5');
INSERT INTO `sys_role_menu` VALUES ('45f7c9c93cef4e449699b688c2af038c', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'b4e7232189b14cf3ba160cf7b0d3bf37');
INSERT INTO `sys_role_menu` VALUES ('4e4274f007bf4e3f93b90160839622a2', 'b1f9ce5543a049be9f169a3f5e6b72a8', '5d3dd56c16ff4e32aecae1b3228159c7');
INSERT INTO `sys_role_menu` VALUES ('637ec8b6ad784b4bb5165c3998c5c05e', 'b1f9ce5543a049be9f169a3f5e6b72a8', '4');
INSERT INTO `sys_role_menu` VALUES ('6a56a5747c3048299a4da7c184b10a69', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'd2bc30feb5474a1bb7e02d48d39a3f8a');
INSERT INTO `sys_role_menu` VALUES ('70a5c8804764426481a1d6b55f2d279d', 'b1f9ce5543a049be9f169a3f5e6b72a8', '4253190001c1480fb0d631d64d150535');
INSERT INTO `sys_role_menu` VALUES ('733a9ca9d52e4ef8865dd1295a94d37b', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'e0ff3505df7c4aff87c60bb510c6d274');
INSERT INTO `sys_role_menu` VALUES ('7b5f175c5de7451ba5460a0bde201436', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'f8db3e5b12d04a46b07026e2828791fc');
INSERT INTO `sys_role_menu` VALUES ('818f045d067f46968f71e615fa28a575', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'a73802e513cc4465883530ec8074abbb');
INSERT INTO `sys_role_menu` VALUES ('91031c79a6fb458a8c34e8c478d64ba8', 'b1f9ce5543a049be9f169a3f5e6b72a8', '42dd5ae31e3a43b3a197440e8ec19a94');
INSERT INTO `sys_role_menu` VALUES ('ab84311db94b41d0bb9ed4c393689d4f', 'b1f9ce5543a049be9f169a3f5e6b72a8', '488ef1eff57b4827acade7c0744278ce');
INSERT INTO `sys_role_menu` VALUES ('b650b5c3fbde4493a43d6328acba8486', 'b1f9ce5543a049be9f169a3f5e6b72a8', '915c309ebe5047b68645b3eb777dd8c9');
INSERT INTO `sys_role_menu` VALUES ('bd6b73f9f9684399916de064ef2b2783', 'b1f9ce5543a049be9f169a3f5e6b72a8', '4e816a9854714d24b0413d929d637a2b');
INSERT INTO `sys_role_menu` VALUES ('c360227f9dc742519a6d01cb5e3ad151', 'b1f9ce5543a049be9f169a3f5e6b72a8', '649b484b58414d91aefa5a26143e6557');
INSERT INTO `sys_role_menu` VALUES ('cbf7eb64790f420abe53a75e515fee60', 'b1f9ce5543a049be9f169a3f5e6b72a8', '79d78b8357174cac8f44abd275dec597');
INSERT INTO `sys_role_menu` VALUES ('cc8c2d7437fd4779a808322a18aa49c2', 'b1f9ce5543a049be9f169a3f5e6b72a8', '3f26102ef0e04c3c9328cb97067cc5fa');
INSERT INTO `sys_role_menu` VALUES ('cc9c02a620424fc9965e4a643cdea378', 'b1f9ce5543a049be9f169a3f5e6b72a8', '0c9b5fc8b44b41d1871a8fc8300247ec');
INSERT INTO `sys_role_menu` VALUES ('df33a02b1b7747fa94a0bde927bc3cc8', 'b1f9ce5543a049be9f169a3f5e6b72a8', '10');
INSERT INTO `sys_role_menu` VALUES ('e7c413029ab54535a57f0e083b09ba7b', 'b1f9ce5543a049be9f169a3f5e6b72a8', '1');
INSERT INTO `sys_role_menu` VALUES ('ec877f815a064ef2be395aaf3e7c6f5d', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'f899f3d3baec4571b1c786717f9906fd');
INSERT INTO `sys_role_menu` VALUES ('f21c2e4cef114496872833d683b06330', 'b1f9ce5543a049be9f169a3f5e6b72a8', '22e38e885f9b40b9aae6a36deb78e89c');
INSERT INTO `sys_role_menu` VALUES ('fbc1e4a1b2ae4fca860b3f93e6bffe05', 'b1f9ce5543a049be9f169a3f5e6b72a8', '661a1f4a6ec94fa89ec6f90ce77a8eb3');
INSERT INTO `sys_role_menu` VALUES ('fd1d7e6b1f264e199d0b894acc4a9d8f', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'f5a20c82110b4a3ea9e30ca01a038680');
INSERT INTO `sys_role_menu` VALUES ('fdc18605311a4a37a65e1e39defa38be', 'b1f9ce5543a049be9f169a3f5e6b72a8', '3');
INSERT INTO `sys_role_menu` VALUES ('fef7263f7cef475596aef61c56b7f710', 'b1f9ce5543a049be9f169a3f5e6b72a8', 'dc5f478d98ed4297a8ae638fe90df050');

-- ----------------------------
-- Table structure for sys_setting
-- ----------------------------
DROP TABLE IF EXISTS `sys_setting`;
CREATE TABLE `sys_setting`  (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `sys_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'KEY',
  `sys_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sys_value` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `sys_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_setting
-- ----------------------------
INSERT INTO `sys_setting` VALUES ('1', 'systemName', '系统名称', '管理系统', NULL, 0);
INSERT INTO `sys_setting` VALUES ('2', 'systemSubName', '系统简称', 'HT', NULL, 1);
INSERT INTO `sys_setting` VALUES ('3', 'bottomCopyright', '许可说明', 'Copyright © 2020. All rights reserved.', NULL, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `dept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门主键',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_state` int(2) NOT NULL DEFAULT 1 COMMENT '用户状态,1-启用,-1禁用',
  `user_desc` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `user_img` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://news.mydrivers.com/Img/20110518/04481549.png' COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('c79ba431f9f74dfbae585b87b0cde933', '9a329b597d754c4180c6b40cdfb0f565', 'admin', 'ac77580d5690fbd251e6ca594c9846a6', 1, '', 'http://news.mydrivers.com/Img/20110518/04481549.png', '2017-09-14 15:02:17');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户主键',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('7d32e28ee3d1452d952f29764e989bab', 'c79ba431f9f74dfbae585b87b0cde933', 'b1f9ce5543a049be9f169a3f5e6b72a8');

SET FOREIGN_KEY_CHECKS = 1;
