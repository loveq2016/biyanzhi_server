/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-08-19 21:15:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shuoshuo_images
-- ----------------------------
DROP TABLE IF EXISTS `shuoshuo_images`;
CREATE TABLE `shuoshuo_images` (
  `img_id` int(11) NOT NULL,
  `shuoshuo_id` int(11) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`img_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
