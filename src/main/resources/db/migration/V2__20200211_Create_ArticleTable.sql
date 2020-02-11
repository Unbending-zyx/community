/*
Navicat MySQL Data Transfer

Source Server         : zyx
Source Server Version : 50622
Source Host           : localhost:3308
Source Database       : community

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2020-02-11 16:16:15
*/

SET FOREIGN_KEY_CHECKS=0;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of article
-- ----------------------------
