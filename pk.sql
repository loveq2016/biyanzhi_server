/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-07-26 17:12:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pk
-- ----------------------------
DROP TABLE IF EXISTS `pk`;
CREATE TABLE `pk` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk1_user_id` int(11) DEFAULT NULL,
  `pk1_user_gender` varchar(255) DEFAULT NULL,
  `pk1_user_picture` varchar(255) DEFAULT NULL,
  `pk1_ticket_count` int(11) DEFAULT NULL,
  `pk2_user_id` int(11) DEFAULT NULL,
  `pk2_user_picture` varchar(255) DEFAULT NULL,
  `pk2_ticket_count` int(11) DEFAULT NULL,
  `pk_time` varchar(255) DEFAULT NULL,
  `pk_state` int(11) DEFAULT NULL,
  `pk_winer_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
