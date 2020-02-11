/*
Navicat MySQL Data Transfer

Source Server         : zyx
Source Server Version : 50622
Source Host           : localhost:3308
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-02-10 14:38:17
*/

SET FOREIGN_KEY_CHECKS=0;

drop database if exists `community`;
create database `community` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gitAccountId` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `gitName` varchar(100) DEFAULT NULL,
  `gitBio` varchar(255) DEFAULT NULL,
  `accountName` varchar(100) DEFAULT NULL,
  `token` char(36) DEFAULT NULL,
  `createTime` bigint(20) NOT NULL,
  `updateTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', '37427911', null, null, '赵宇霄', null, null, '9b46821a-9f72-47e9-9f15-e87324810065', '1581056885838', '1581056885838');
INSERT INTO `user` VALUES ('11', null, '123', '123', null, null, '123', '33d823d3-0516-4b51-8ddb-0bf6b3042a17', '1581237904021', '1581237989147');
