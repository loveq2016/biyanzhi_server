/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-08-19 21:15:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shuoshuo
-- ----------------------------
DROP TABLE IF EXISTS `shuoshuo`;
CREATE TABLE `shuoshuo` (
  `shuoshuo_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `publisher_name` varchar(255) DEFAULT NULL,
  `publisher_avatar` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  PRIMARY KEY (`shuoshuo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
