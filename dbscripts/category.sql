/*
Navicat MySQL Data Transfer

Source Server         : localenv
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : devdata

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2016-11-13 13:34:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` char(2) DEFAULT NULL,
  `desc` varchar(20) NOT NULL,
  `createdtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '1', 'Java进阶', '2016-11-13 11:01:53');
INSERT INTO `category` VALUES ('2', '2', '前端开发', '2016-11-13 11:02:01');
INSERT INTO `category` VALUES ('3', '3', '云计算', '2016-11-13 11:02:15');
INSERT INTO `category` VALUES ('4', '4', '数据库', '2016-11-13 11:02:22');
INSERT INTO `category` VALUES ('5', '5', '其他语言', '2016-11-13 13:42:18');
