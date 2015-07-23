/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : biyanzhi

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-07-23 17:27:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pk`
-- ----------------------------
DROP TABLE IF EXISTS `pk`;
CREATE TABLE `pk` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT,
  `pk1_user_id` int(11) DEFAULT NULL,
  `pk1_user_picture` varchar(255) DEFAULT NULL,
  `pk1_ticket_count` int(11) DEFAULT NULL,
  `pk2_user_id` int(11) DEFAULT NULL,
  `pk2_user_picture` varchar(255) DEFAULT NULL,
  `pk2_ticket_count` int(11) DEFAULT NULL,
  `pk_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pk
-- ----------------------------
INSERT INTO `pk` VALUES ('1', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-17.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 14:05:10');
INSERT INTO `pk` VALUES ('2', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-20-01.png', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:17:12');
INSERT INTO `pk` VALUES ('3', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-18-54.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:36:26');
INSERT INTO `pk` VALUES ('4', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-04.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:36:42');
INSERT INTO `pk` VALUES ('5', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-24.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:43:15');
INSERT INTO `pk` VALUES ('6', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-18-33.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:46:05');
INSERT INTO `pk` VALUES ('7', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-18-22.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:46:41');
INSERT INTO `pk` VALUES ('8', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-18-15.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:46:48');
INSERT INTO `pk` VALUES ('9', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-18-08.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:46:57');
INSERT INTO `pk` VALUES ('10', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-20-01.png', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:47:02');
INSERT INTO `pk` VALUES ('11', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-24.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:47:07');
INSERT INTO `pk` VALUES ('12', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-17.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:47:10');
INSERT INTO `pk` VALUES ('13', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-04.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:48:34');
INSERT INTO `pk` VALUES ('14', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-18-22.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:50:00');
INSERT INTO `pk` VALUES ('15', '938', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-21-11-19-17.jpg', '0', '939', 'http://192.168.1.108:8080/biyanzhi/picture_image/2015-07-22-17-46-06.jpg', '0', '2015-07-23 15:50:22');
