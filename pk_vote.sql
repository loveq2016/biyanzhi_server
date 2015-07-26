/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-07-26 17:12:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pk_vote
-- ----------------------------
DROP TABLE IF EXISTS `pk_vote`;
CREATE TABLE `pk_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pk_id` int(11) DEFAULT NULL,
  `vote_user_id` int(11) DEFAULT NULL COMMENT '投票用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
