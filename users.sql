/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : 127.0.0.1:3306
Source Database       : stumanagerdba

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-01-20 13:51:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('root', '111');
INSERT INTO `users` VALUES ('root1', '1234');
INSERT INTO `users` VALUES ('root10', '123456');
INSERT INTO `users` VALUES ('root100', '123456');
INSERT INTO `users` VALUES ('root2', '1234');
INSERT INTO `users` VALUES ('root22', '222123');
INSERT INTO `users` VALUES ('root3', '1234');
INSERT INTO `users` VALUES ('root4', '1234');
INSERT INTO `users` VALUES ('root5', '1234');
INSERT INTO `users` VALUES ('root6', '6666');
INSERT INTO `users` VALUES ('root7', '1234');
INSERT INTO `users` VALUES ('root9', '123456');
