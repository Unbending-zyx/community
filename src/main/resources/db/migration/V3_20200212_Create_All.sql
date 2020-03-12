/*
Navicat MySQL Data Transfer

Source Server         : zyx
Source Server Version : 50622
Source Host           : localhost:3308
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-03-12 11:35:03
*/

SET FOREIGN_KEY_CHECKS=0;
drop database if exists `community`;
create database `community` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(50) DEFAULT NULL COMMENT '文章名',
  `description` text COMMENT '文章内容',
  `articleCreateTime` bigint(20) DEFAULT NULL COMMENT '文章创建时间',
  `articleUpdateTime` bigint(20) DEFAULT NULL COMMENT '文章更新事件',
  `creatorId` int(11) DEFAULT NULL COMMENT '创建人id',
  `readingCount` int(11) DEFAULT '0' COMMENT '阅读数',
  `likeCount` int(11) DEFAULT '0' COMMENT '点赞数',
  `commentCount` int(11) DEFAULT '0' COMMENT '评论数',
  `tag` varchar(255) DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL COMMENT '用于确定是几级回复',
  `creatorId` int(11) NOT NULL COMMENT '创建该条评论的用户Id',
  `commentCreateTime` bigint(20) NOT NULL,
  `commentUpdateTime` bigint(20) NOT NULL,
  `likeCount` int(11) NOT NULL DEFAULT '0',
  `commentContent` varchar(255) NOT NULL,
  `parentId` int(11) NOT NULL,
  `commentCount` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for `notification`
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `senderId` int(11) NOT NULL COMMENT '发送者id',
  `receiverId` int(11) NOT NULL COMMENT '接收者id',
  `outerId` int(11) NOT NULL COMMENT '操作的父级id',
  `type` int(11) NOT NULL COMMENT '回复评论（2）或者回复话题（1）',
  `notificationCreateTime` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态  已读（1）或未读（0）',
  `senderName` varchar(100) NOT NULL,
  `outerTitle` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;


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
  `avatarUrl` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

