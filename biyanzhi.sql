/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-05-19 18:19:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `picture`
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
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES ('75', '0', '2015-05-19 18:04', '', '', '', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-05-19-18-04-33.jpg', '1280', '720');
INSERT INTO `picture` VALUES ('76', '0', '2015-05-19 18:06', '', '', '', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-05-19-18-06-22JPEG', '720', '540');
INSERT INTO `picture` VALUES ('77', '0', '2015-05-19 18:06', '', '', '', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-05-19-18-06-43.jpg', '1920', '2560');
INSERT INTO `picture` VALUES ('78', '0', '2015-05-19 18:07', '', '', '', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-05-19-18-07-03.jpg', '800', '480');

-- ----------------------------
-- Table structure for `picture_image`
-- ----------------------------
DROP TABLE IF EXISTS `picture_image`;
CREATE TABLE `picture_image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `picture_id` int(11) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture_image
-- ----------------------------

-- ----------------------------
-- Table structure for `picture_score`
-- ----------------------------
DROP TABLE IF EXISTS `picture_score`;
CREATE TABLE `picture_score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `picture_id` int(11) DEFAULT NULL,
  `picture_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture_score
-- ----------------------------
