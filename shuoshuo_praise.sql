/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-08-19 21:15:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shuoshuo_praise
-- ----------------------------
DROP TABLE IF EXISTS `shuoshuo_praise`;
CREATE TABLE `shuoshuo_praise` (
  `id` int(11) NOT NULL,
  `shuoshuo_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shuoshuo_praise
-- ----------------------------
