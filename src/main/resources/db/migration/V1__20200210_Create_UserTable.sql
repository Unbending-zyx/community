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
