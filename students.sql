/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80017
Source Host           : 127.0.0.1:3306
Source Database       : stumanagerdba

Target Server Type    : MYSQL
Target Server Version : 80017
File Encoding         : 65001

Date: 2020-01-20 13:54:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `sno` varchar(255) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthDate` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('0987', '5555', '女', '1994-09-04', '管理学院', '会计');
INSERT INTO `students` VALUES ('2099', 'ooll', '女', '1998-01-01', '信息科学与技术学院', '物联网');
INSERT INTO `students` VALUES ('3131', 'ffawa', '女', '1996-06-07', '管理学院', '工商管理');
INSERT INTO `students` VALUES ('4114', 'grsg', '男', '1990-01-01', '轻工食品学院', '食品安全');
INSERT INTO `students` VALUES ('4119', 'sgs', '男', '1996-06-02', '经贸学院', '贸易');
INSERT INTO `students` VALUES ('4144', 'faa', '男', '1997-04-01', '农学院', '植物保护');
INSERT INTO `students` VALUES ('4242', 'yyy', '男', '1996-01-01', '环境科学与工程学院 ', '环境科学');
INSERT INTO `students` VALUES ('4343', 'tea', '女', '2000-08-06', '信息科学与技术学院', '物联网');
INSERT INTO `students` VALUES ('7654', 'ttt', '男', '2000-05-06', '生命科学学院', '生命科学');
INSERT INTO `students` VALUES ('9090', 'viviv', '男', '1999-02-28', '轻工食品学院', '食品安全');
INSERT INTO `students` VALUES ('9922', 'erw', '男', '1996-01-01', '信息科学与技术学院', '物联网');
