/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-06-14 20:43:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `picture_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  `comment_time` varchar(255) NOT NULL,
  `reply_someone_id` int(11) DEFAULT NULL,
  `reply_someone_name` varchar(255) DEFAULT NULL,
  `publisher_name` varchar(255) DEFAULT NULL,
  `publisher_avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for guanzhu
-- ----------------------------
DROP TABLE IF EXISTS `guanzhu`;
CREATE TABLE `guanzhu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `guanzhu_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `picture_id` int(11) NOT NULL AUTO_INCREMENT,
  `publisher_id` int(11) DEFAULT NULL,
  `publish_time` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `publisher_name` varchar(255) DEFAULT NULL,
  `publisher_avatar` varchar(255) DEFAULT NULL,
  `picture_image_url` varchar(255) DEFAULT NULL,
  `picture_image_height` int(11) DEFAULT NULL,
  `picture_image_width` int(11) DEFAULT NULL,
  PRIMARY KEY (`picture_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for picture_score
-- ----------------------------
DROP TABLE IF EXISTS `picture_score`;
CREATE TABLE `picture_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `picture_id` int(11) DEFAULT NULL,
  `picture_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sms_code
-- ----------------------------
DROP TABLE IF EXISTS `sms_code`;
CREATE TABLE `sms_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_cellphone` varchar(255) DEFAULT NULL,
  `sms_code` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1714 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_cellphone` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_birthday` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_chat_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=938 DEFAULT CHARSET=utf8;
