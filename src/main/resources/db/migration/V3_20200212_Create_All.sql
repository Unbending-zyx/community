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
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '123爱12', '321不爱123', '1581581028928', '1582428651272', '11', '86', '0', '16', '爱谁谁');
INSERT INTO `article` VALUES ('2', '2', '3', '1581581028928', '1581581028928', '11', '3', '0', '0', '1');
INSERT INTO `article` VALUES ('3', '3', '3', '1581581028928', '1581581028928', '11', '0', '0', '0', null);
INSERT INTO `article` VALUES ('4', '4', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('5', '5', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('6', '6', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('7', '7', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('8', '8', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('9', '9', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('10', '10', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('11', '11', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('12', '12', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('13', '1456456789', '4', '1581581028928', '1582615127580', '11', '2', '0', '0', '0');
INSERT INTO `article` VALUES ('14', '14', '4', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('15', '15', '5', '1581581028928', '1581581028928', '11', '0', '0', '0', '0');
INSERT INTO `article` VALUES ('16', '16', '6', '1581581028928', '1581581028928', '11', '0', '0', '0', '123');
INSERT INTO `article` VALUES ('17', '123456', '123456789', '1581834933288', '1581834933288', '11', '0', '0', '0', '123');
INSERT INTO `article` VALUES ('18', '123爱12', '321不爱123', '1582428022608', '1582428022608', '11', '0', '0', '0', '爱谁谁');
INSERT INTO `article` VALUES ('19', '123爱', '321不爱123', '1582428156840', '1582428156840', '11', '0', '0', '0', '爱谁谁');
INSERT INTO `article` VALUES ('20', '789', '789', '1582615085686', '1582615085686', '11', '0', '0', '0', '789');
INSERT INTO `article` VALUES ('21', '这是一个测试', '测试', '1583113334089', '1583113334089', '11', '67', '0', '2', 'test');
INSERT INTO `article` VALUES ('22', 'Spring Boot', 'Spring Boot', '1583239599357', '1583239599357', '11', '7', '0', '0', 'Spring Boot,Spring,Java');
INSERT INTO `article` VALUES ('23', 'Spring MVC', 'Spring MVC', '1583239631612', '1583239631612', '11', '2', '0', '0', 'Spring MVC,Spring,Java');
INSERT INTO `article` VALUES ('24', 'Java', 'Java', '1583239655883', '1583239655883', '11', '28', '0', '1', 'Java');
INSERT INTO `article` VALUES ('25', '文章测试', '测试标签能否正常使用', '1583330287395', '1583330287395', '11', '1', '0', '0', '找小哥哥,找小姐姐');
INSERT INTO `article` VALUES ('26', '测试关联', '同上', '1583330362753', '1583330362753', '11', '29', '0', '5', '找小哥哥');
INSERT INTO `article` VALUES ('27', '测试markdown', '```java\n@Override\n    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n        User user=(User) request.getSession().getAttribute(\"user\");\n        if (user!=null){\n            return true;\n        }\n        response.sendRedirect(\"/login/show\");\n        return false;\n    }\n```', '1583486241022', '1583486241022', '11', '21', '0', '0', '问题求解');
INSERT INTO `article` VALUES ('28', '测试markdown修改7', '```java\n@Override\n    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n        User user=(User) request.getSession().getAttribute(\"user\");\n        if (user!=null){\n            return true;\n        }\n        response.sendRedirect(\"/login/show\");\n        return false;\n    }\n```', '1583509387929', '1583512160737', '11', '7', '0', '0', '问题求解');
INSERT INTO `article` VALUES ('29', '123', '```java\n@Override\n    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n        User user=(User) request.getSession().getAttribute(\"user\");\n        if (user!=null){\n            return true;\n        }\n        response.sendRedirect(\"/login/show\");\n        return false;\n    }\n```', '1583512221828', '1583512221828', '11', '6', '0', '2', '游戏开黑');
INSERT INTO `article` VALUES ('30', '789改再次修改', '**这是一个测试**', '1583599292031', '1583599371204', '13', '6', '0', '1', '问题求解,考研伙伴');
INSERT INTO `article` VALUES ('31', '123发布的话题', '123发布的话题', '1583745825673', '1583745825673', '11', '3', '0', '0', '找小哥哥,找小姐姐');

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
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1', '1', '1111', '1111', '1', '123456789', '1', '0');
INSERT INTO `comment` VALUES ('2', '1', '11', '1582538672926', '1582538672926', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('3', '1', '11', '1582538921118', '1582538921118', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('4', '1', '11', '1582548828731', '1582548828731', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('5', '1', '11', '1582549660614', '1582549660614', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('6', '1', '11', '1582551262892', '1582551262892', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('7', '1', '11', '1582771892335', '1582771892335', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('8', '1', '11', '1582772462482', '1582772462482', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('9', '1', '11', '1582794598424', '1582794598424', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('10', '2', '11', '1582794762606', '1582794762606', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('11', '1', '11', '1582794769404', '1582794769404', '0', '12345678963258', '1', '0');
INSERT INTO `comment` VALUES ('13', '1', '11', '1582877462237', '1582877462237', '0', '123456', '1', '0');
INSERT INTO `comment` VALUES ('14', '1', '11', '1582878875652', '1582878875652', '0', '123456', '1', '0');
INSERT INTO `comment` VALUES ('15', '1', '11', '1582879069752', '1582879069752', '0', '123456741258369', '1', '0');
INSERT INTO `comment` VALUES ('16', '1', '11', '1582985300528', '1582985300528', '0', '1', '1', '0');
INSERT INTO `comment` VALUES ('17', '1', '11', '1583115218566', '1583115218566', '0', '22222222', '1', '0');
INSERT INTO `comment` VALUES ('18', '1', '11', '1583130492604', '1583130492604', '4', '123456', '21', '16');
INSERT INTO `comment` VALUES ('19', '1', '11', '1583130545530', '1583130545530', '1', '这是测试的回复', '21', '1');
INSERT INTO `comment` VALUES ('20', '2', '11', '1583130492604', '1583130492604', '10', '这是二级评论测试', '19', '0');
INSERT INTO `comment` VALUES ('21', '2', '11', '1583156299540', '1583156299540', '0', '这是第二次二级评论测试', '19', '0');
INSERT INTO `comment` VALUES ('22', '2', '11', '1583156392992', '1583156392992', '0', '这是第三次二级评论的测试', '19', '0');
INSERT INTO `comment` VALUES ('23', '2', '11', '1583156806197', '1583156806197', '0', '第四次测试二级评论', '19', '0');
INSERT INTO `comment` VALUES ('24', '2', '11', '1583156841998', '1583156841998', '0', '第五次测试二级评论', '19', '0');
INSERT INTO `comment` VALUES ('25', '2', '11', '1583156880618', '1583156880618', '0', '第五次测试', '19', '0');
INSERT INTO `comment` VALUES ('26', '2', '11', '1583157087623', '1583157087623', '0', '6次测试', '19', '0');
INSERT INTO `comment` VALUES ('27', '2', '11', '1583158245119', '1583158245119', '0', '这是18号评论的第一次评论', '18', '0');
INSERT INTO `comment` VALUES ('28', '2', '11', '1583158303776', '1583158303776', '0', '这是18号评论的第二次评论', '18', '0');
INSERT INTO `comment` VALUES ('29', '2', '11', '1583158538423', '1583158538423', '0', '这是18号评论的第三层测试', '18', '0');
INSERT INTO `comment` VALUES ('30', '2', '11', '1583158691685', '1583158691685', '0', '18的第四次测试', '18', '0');
INSERT INTO `comment` VALUES ('31', '2', '11', '1583158794422', '1583158794422', '0', '18的第五次测试', '18', '0');
INSERT INTO `comment` VALUES ('32', '2', '11', '1583158883798', '1583158883798', '0', '18的第六次测试', '18', '0');
INSERT INTO `comment` VALUES ('33', '2', '11', '1583158895664', '1583158895664', '0', '18的第七次测试', '18', '0');
INSERT INTO `comment` VALUES ('34', '2', '11', '1583158909185', '1583158909185', '0', '7次测试', '19', '0');
INSERT INTO `comment` VALUES ('35', '2', '11', '1583160592891', '1583160592891', '0', '18号8次测试', '18', '0');
INSERT INTO `comment` VALUES ('36', '2', '11', '1583225201890', '1583225201890', '0', '18的9次测试', '18', '0');
INSERT INTO `comment` VALUES ('37', '2', '11', '1583225454106', '1583225454106', '0', '18的10次测试', '18', '0');
INSERT INTO `comment` VALUES ('38', '2', '11', '1583225509752', '1583225509752', '0', '18的11次测试', '18', '0');
INSERT INTO `comment` VALUES ('39', '2', '11', '1583226644833', '1583226644833', '0', '18的12次测试', '18', '0');
INSERT INTO `comment` VALUES ('40', '2', '11', '1583226945402', '1583226945402', '0', '18的13次测试', '18', '0');
INSERT INTO `comment` VALUES ('41', '2', '11', '1583227059352', '1583227059352', '0', '18 14', '18', '0');
INSERT INTO `comment` VALUES ('42', '2', '11', '1583227269843', '1583227269843', '0', '18 15', '18', '0');
INSERT INTO `comment` VALUES ('43', '2', '11', '1583227647767', '1583227647767', '0', '18 16', '18', '0');
INSERT INTO `comment` VALUES ('44', '1', '11', '1583290114094', '1583290114094', '11', '123', '24', '2');
INSERT INTO `comment` VALUES ('45', '2', '11', '1583290122345', '1583290122345', '0', '这是评论', '44', '0');
INSERT INTO `comment` VALUES ('46', '2', '11', '1583290156521', '1583290156521', '0', '再次评论', '44', '0');
INSERT INTO `comment` VALUES ('47', '1', '11', '1583376510567', '1583376510567', '0', '这是测试通知', '1', '0');
INSERT INTO `comment` VALUES ('50', '1', '11', '1583397046412', '1583397046412', '0', '123', '26', '2');
INSERT INTO `comment` VALUES ('51', '1', '11', '1583397054040', '1583397054040', '0', '456', '26', '0');
INSERT INTO `comment` VALUES ('52', '1', '11', '1583400366702', '1583400366702', '0', '123', '26', '0');
INSERT INTO `comment` VALUES ('53', '1', '11', '1583400370835', '1583400370835', '0', '456', '26', '0');
INSERT INTO `comment` VALUES ('54', '1', '11', '1583400375211', '1583400375211', '0', '789', '26', '0');
INSERT INTO `comment` VALUES ('55', '2', '11', '1583400391081', '1583400391081', '0', '123', '50', '0');
INSERT INTO `comment` VALUES ('56', '2', '11', '1583400394795', '1583400394795', '0', '456', '50', '0');
INSERT INTO `comment` VALUES ('57', '1', '11', '1583593596578', '1583593596578', '0', '123', '29', '3');
INSERT INTO `comment` VALUES ('58', '2', '11', '1583593862124', '1583593862124', '0', '456', '57', '0');
INSERT INTO `comment` VALUES ('59', '2', '11', '1583593866228', '1583593866228', '0', '789', '57', '0');
INSERT INTO `comment` VALUES ('60', '1', '13', '1583599241057', '1583599241057', '0', '这是一次评论', '29', '0');
INSERT INTO `comment` VALUES ('61', '2', '13', '1583599258061', '1583599258061', '0', '评论他人的回复', '57', '0');
INSERT INTO `comment` VALUES ('62', '1', '13', '1583599327413', '1583599327413', '0', '123456', '30', '1');
INSERT INTO `comment` VALUES ('63', '2', '13', '1583599336069', '1583599336069', '0', '回复', '62', '0');

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
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES ('4', '11', '11', '26', '1', '1583400366725', '0', '123', '测试关联');
INSERT INTO `notification` VALUES ('5', '11', '11', '26', '1', '1583400370838', '0', '123', '测试关联');
INSERT INTO `notification` VALUES ('6', '11', '11', '26', '1', '1583400375214', '1', '123', '测试关联');
INSERT INTO `notification` VALUES ('7', '11', '11', '26', '2', '1583400391148', '1', '123', '测试关联');
INSERT INTO `notification` VALUES ('8', '11', '11', '26', '2', '1583400394854', '1', '123', '测试关联');
INSERT INTO `notification` VALUES ('9', '11', '11', '29', '1', '1583593596708', '0', '123', '123');
INSERT INTO `notification` VALUES ('10', '11', '11', '29', '2', '1583593862234', '0', '123', '123');
INSERT INTO `notification` VALUES ('11', '11', '11', '29', '2', '1583593866376', '0', '123', '123');
INSERT INTO `notification` VALUES ('12', '13', '11', '29', '1', '1583599241071', '0', '789', '123');
INSERT INTO `notification` VALUES ('13', '13', '11', '29', '2', '1583599258199', '0', '789', '123');
INSERT INTO `notification` VALUES ('14', '13', '13', '30', '1', '1583599327425', '0', '789', '789改');
INSERT INTO `notification` VALUES ('15', '13', '13', '30', '2', '1583599336076', '1', '789', '789改');

-- ----------------------------
-- Table structure for `schema_version`
-- ----------------------------
DROP TABLE IF EXISTS `schema_version`;
CREATE TABLE `schema_version` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `schema_version_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of schema_version
-- ----------------------------
INSERT INTO `schema_version` VALUES ('1', '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', null, 'root', '2020-02-11 15:01:13', '0', '1');

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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10', '37427911', null, null, '赵宇霄', null, null, '9b46821a-9f72-47e9-9f15-e87324810065', '1581056885838', '1581056885838', '/static/image/avatar.jpeg');
INSERT INTO `user` VALUES ('11', null, '123', '123', null, null, '123', '5c50578a-eb6a-4320-b11e-8639a5fdb188', '1581237904021', '1583848064811', '/static/image/avatar.jpeg');
INSERT INTO `user` VALUES ('12', null, '456', '456', null, '456456456', '456', '46bff0a8-a1a5-491e-ac1d-cbc01eec9aed', '1582361056270', '1583745798385', '/static/image/avatar.jpeg');
INSERT INTO `user` VALUES ('13', null, '789', '789', null, '789', '789', 'aaeca63c-92e5-4df4-9c93-9a87e2e6f9ae', '1583599217359', '1583599224052', '/static/image/avatar.jpeg');
