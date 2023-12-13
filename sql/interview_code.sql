/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50739
Source Host           : localhost:3306
Source Database       : interview_code

Target Server Type    : MYSQL
Target Server Version : 50739
File Encoding         : 65001

Date: 2023-12-13 10:25:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID，自增唯一',
  `tag_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '标签名',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_tag_id` (`id`) COMMENT '为用户id添加索引'
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '沿海', '2023-12-11 14:19:04', null);
INSERT INTO `tag` VALUES ('2', '女性', '2023-12-11 17:41:38', '2023-12-11 17:41:39');
INSERT INTO `tag` VALUES ('3', '活跃', '2023-12-11 17:41:38', '2023-12-11 17:41:39');
INSERT INTO `tag` VALUES ('4', '广东', '2023-12-11 17:41:38', '2023-12-11 17:41:39');
INSERT INTO `tag` VALUES ('5', '广西', '2023-12-11 17:41:38', '2023-12-11 17:41:39');
INSERT INTO `tag` VALUES ('6', '新增', '2023-12-11 17:41:38', '2023-12-11 17:41:39');
INSERT INTO `tag` VALUES ('7', '男性', '2023-12-12 15:42:52', '2023-12-12 15:42:57');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID，自增唯一',
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户名，非空',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '密码，非空',
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`id`) COMMENT '为用户id添加索引',
  KEY `idx_username` (`username`) COMMENT '为用户名添加索引'
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'tutu', '123456', null, '2023-12-11 13:56:12', '2023-12-11 13:56:15');
INSERT INTO `user` VALUES ('2', 'tutu', '123456', null, null, null);
INSERT INTO `user` VALUES ('3', 'susu', null, null, '2023-12-11 16:51:10', '2023-12-11 16:51:10');
INSERT INTO `user` VALUES ('4', 'kaka', null, null, '2023-12-11 16:51:10', '2023-12-11 16:51:10');
INSERT INTO `user` VALUES ('5', 'guaiguai', null, null, '2023-12-11 16:51:10', '2023-12-11 16:51:10');
INSERT INTO `user` VALUES ('6', 'lala', null, null, '2023-12-11 16:51:10', '2023-12-11 16:51:10');
INSERT INTO `user` VALUES ('7', 'gege', null, null, '2023-12-11 16:51:10', '2023-12-11 16:51:10');
INSERT INTO `user` VALUES ('8', 'feifei', null, null, '2023-12-11 16:51:10', '2023-12-11 16:51:10');
INSERT INTO `user` VALUES ('9', 'susu', null, null, '2023-12-11 16:52:50', '2023-12-11 16:52:50');
INSERT INTO `user` VALUES ('10', 'kaka', null, null, '2023-12-11 16:52:50', '2023-12-11 16:52:50');
INSERT INTO `user` VALUES ('11', 'guaiguai', null, null, '2023-12-11 16:52:50', '2023-12-11 16:52:50');
INSERT INTO `user` VALUES ('12', 'lala', null, null, '2023-12-11 16:52:50', '2023-12-11 16:52:50');
INSERT INTO `user` VALUES ('13', 'gege', null, null, '2023-12-11 16:52:50', '2023-12-11 16:52:50');
INSERT INTO `user` VALUES ('14', 'feifei', null, null, '2023-12-11 16:52:50', '2023-12-11 16:52:50');
INSERT INTO `user` VALUES ('15', 'susu', null, null, '2023-12-11 16:56:03', '2023-12-11 16:56:03');
INSERT INTO `user` VALUES ('16', 'kaka', null, null, '2023-12-11 16:56:03', '2023-12-11 16:56:03');
INSERT INTO `user` VALUES ('17', 'guaiguai', null, null, '2023-12-11 16:56:03', '2023-12-11 16:56:03');
INSERT INTO `user` VALUES ('18', 'lala', null, null, '2023-12-11 16:56:03', '2023-12-11 16:56:03');
INSERT INTO `user` VALUES ('19', 'gege', null, null, '2023-12-11 16:56:03', '2023-12-11 16:56:03');
INSERT INTO `user` VALUES ('20', 'feifei', null, null, '2023-12-11 16:56:03', '2023-12-11 16:56:03');
INSERT INTO `user` VALUES ('21', 'susu', '1123456', null, '2023-12-11 17:27:04', '2023-12-11 17:27:04');
INSERT INTO `user` VALUES ('22', 'kaka', '2123456', null, '2023-12-11 17:27:04', '2023-12-11 17:27:04');
INSERT INTO `user` VALUES ('23', 'guaiguai', '12123456', null, '2023-12-11 17:27:04', '2023-12-11 17:27:04');
INSERT INTO `user` VALUES ('24', 'lala', '11123456', null, '2023-12-11 17:27:04', '2023-12-11 17:27:04');
INSERT INTO `user` VALUES ('25', 'gege', '11123456', null, '2023-12-11 17:27:04', '2023-12-11 17:27:04');
INSERT INTO `user` VALUES ('26', 'feifei', '111123456', null, '2023-12-11 17:27:04', '2023-12-11 17:27:04');
INSERT INTO `user` VALUES ('27', 'susu', '1123456', null, '2023-12-11 17:27:54', '2023-12-11 17:27:54');
INSERT INTO `user` VALUES ('28', 'kaka', '2123456', null, '2023-12-11 17:27:54', '2023-12-11 17:27:54');
INSERT INTO `user` VALUES ('29', 'guaiguai', '12123456', null, '2023-12-11 17:27:54', '2023-12-11 17:27:54');
INSERT INTO `user` VALUES ('30', 'lala', '11123456', null, '2023-12-11 17:27:54', '2023-12-11 17:27:54');
INSERT INTO `user` VALUES ('31', 'gege', '11123456', null, '2023-12-11 17:27:54', '2023-12-11 17:27:54');
INSERT INTO `user` VALUES ('32', 'feifei', '111123456', null, '2023-12-11 17:27:54', '2023-12-11 17:27:54');

-- ----------------------------
-- Table structure for `user_tag`
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户标签表id',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `tag_id` int(11) NOT NULL COMMENT '标签ID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_tag` (`user_id`,`tag_id`),
  KEY `idx_user_id` (`user_id`) COMMENT '为用户ID添加索引',
  KEY `idx_tag_id` (`tag_id`) COMMENT '为标签ID添加索引',
  CONSTRAINT `user_tag_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_tag_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_tag
-- ----------------------------
INSERT INTO `user_tag` VALUES ('6', '2', '5', '2023-12-11 20:49:50', '2023-12-11 20:49:50');
INSERT INTO `user_tag` VALUES ('8', '12', '1', '2023-12-12 11:34:49', '2023-12-12 11:34:49');
INSERT INTO `user_tag` VALUES ('9', '12', '2', '2023-12-12 11:34:49', '2023-12-12 11:34:49');
INSERT INTO `user_tag` VALUES ('11', '5', '5', '2023-12-12 11:34:49', '2023-12-12 11:34:49');
INSERT INTO `user_tag` VALUES ('16', '20', '6', '2023-12-12 16:12:06', '2023-12-12 16:12:06');
INSERT INTO `user_tag` VALUES ('17', '20', '7', '2023-12-12 16:12:06', '2023-12-12 16:12:06');
INSERT INTO `user_tag` VALUES ('18', '20', '1', '2023-12-12 16:12:06', '2023-12-12 16:12:06');
INSERT INTO `user_tag` VALUES ('19', '20', '3', '2023-12-12 16:12:06', '2023-12-12 16:12:06');
INSERT INTO `user_tag` VALUES ('20', '19', '6', '2023-12-12 16:23:22', '2023-12-12 16:23:22');
INSERT INTO `user_tag` VALUES ('21', '19', '7', '2023-12-12 16:23:22', '2023-12-12 16:23:22');
INSERT INTO `user_tag` VALUES ('23', '19', '3', '2023-12-12 16:23:22', '2023-12-12 16:23:22');
INSERT INTO `user_tag` VALUES ('24', '8', '6', '2023-12-12 16:57:39', '2023-12-12 16:57:39');
INSERT INTO `user_tag` VALUES ('25', '8', '7', '2023-12-12 16:57:39', '2023-12-12 16:57:39');
INSERT INTO `user_tag` VALUES ('27', '8', '3', '2023-12-12 16:57:39', '2023-12-12 16:57:39');
INSERT INTO `user_tag` VALUES ('32', '11', '6', '2023-12-12 16:59:25', '2023-12-12 16:59:25');
INSERT INTO `user_tag` VALUES ('33', '11', '7', '2023-12-12 16:59:25', '2023-12-12 16:59:25');
INSERT INTO `user_tag` VALUES ('34', '11', '1', '2023-12-12 16:59:25', '2023-12-12 16:59:25');
INSERT INTO `user_tag` VALUES ('35', '11', '3', '2023-12-12 16:59:25', '2023-12-12 16:59:25');
INSERT INTO `user_tag` VALUES ('40', '10', '6', '2023-12-12 17:04:36', '2023-12-12 17:04:36');
INSERT INTO `user_tag` VALUES ('41', '10', '7', '2023-12-12 17:04:36', '2023-12-12 17:04:36');
INSERT INTO `user_tag` VALUES ('42', '10', '1', '2023-12-12 17:04:36', '2023-12-12 17:04:36');
INSERT INTO `user_tag` VALUES ('43', '10', '3', '2023-12-12 17:04:36', '2023-12-12 17:04:36');
INSERT INTO `user_tag` VALUES ('51', '9', '3', '2023-12-12 17:10:43', '2023-12-12 17:10:43');
INSERT INTO `user_tag` VALUES ('56', '9', '5', '2023-12-12 17:15:02', '2023-12-12 17:15:02');
INSERT INTO `user_tag` VALUES ('57', '12', '6', '2023-12-12 17:20:07', '2023-12-12 17:20:07');
INSERT INTO `user_tag` VALUES ('58', '12', '7', '2023-12-12 17:20:07', '2023-12-12 17:20:07');
INSERT INTO `user_tag` VALUES ('59', '12', '5', '2023-12-12 17:21:54', '2023-12-12 17:21:54');
INSERT INTO `user_tag` VALUES ('74', '8', '2', '2023-12-12 19:54:24', '2023-12-12 19:54:24');
INSERT INTO `user_tag` VALUES ('75', '2', '2', '2023-12-12 19:54:24', '2023-12-12 19:54:24');
INSERT INTO `user_tag` VALUES ('76', '9', '2', '2023-12-12 19:54:24', '2023-12-12 19:54:24');
INSERT INTO `user_tag` VALUES ('77', '5', '2', '2023-12-12 19:54:24', '2023-12-12 19:54:24');
INSERT INTO `user_tag` VALUES ('78', '4', '2', '2023-12-12 19:54:24', '2023-12-12 19:54:24');
INSERT INTO `user_tag` VALUES ('228', '1', '5', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('229', '1', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('230', '2', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('231', '3', '5', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('232', '3', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('233', '4', '5', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('234', '4', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('235', '5', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('236', '6', '5', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('237', '6', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('238', '7', '5', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('239', '7', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('240', '8', '5', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('241', '9', '6', '2023-12-12 20:39:18', '2023-12-12 20:39:18');
INSERT INTO `user_tag` VALUES ('242', '24', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('243', '24', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('244', '24', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('246', '23', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('247', '23', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('248', '23', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('250', '25', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('251', '25', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('252', '25', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('254', '22', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('255', '22', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('256', '22', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('258', '21', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('259', '21', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('260', '21', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('262', '26', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('263', '26', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('264', '26', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('266', '27', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('267', '27', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('268', '27', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('270', '28', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('271', '28', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('272', '28', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('274', '29', '2', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('275', '29', '3', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('276', '29', '4', '2023-12-12 20:43:42', '2023-12-12 20:43:42');
INSERT INTO `user_tag` VALUES ('278', '30', '1', '2023-12-12 22:40:53', '2023-12-12 22:40:53');
INSERT INTO `user_tag` VALUES ('279', '30', '6', '2023-12-12 22:40:53', '2023-12-12 22:40:53');
INSERT INTO `user_tag` VALUES ('280', '30', '2', '2023-12-12 22:40:53', '2023-12-12 22:40:53');
INSERT INTO `user_tag` VALUES ('281', '30', '3', '2023-12-12 22:40:53', '2023-12-12 22:40:53');
INSERT INTO `user_tag` VALUES ('282', '2', '1', '2023-12-12 23:16:00', '2023-12-12 23:16:00');
