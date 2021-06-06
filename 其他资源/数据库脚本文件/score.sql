/*
 Navicat Premium Data Transfer

 Source Server         : Mysql8
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : score

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/06/2021 18:33:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_application
-- ----------------------------
DROP TABLE IF EXISTS `t_application`;
CREATE TABLE `t_application`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `scoId` int NULL DEFAULT NULL COMMENT '成绩id',
  `teaId` int NULL DEFAULT NULL COMMENT '教师id',
  `stuUserName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生账户',
  `status` int NOT NULL COMMENT '审批状态，1-已通过，2-驳回，0-待处理',
  `stuName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `subId` int NOT NULL COMMENT '科目id',
  `subName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '科目名',
  `teaName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `subGrade` double(255, 2) NULL DEFAULT NULL COMMENT '科目总分',
  `grade` double(255, 2) NULL DEFAULT NULL COMMENT '学生得分',
  `applyGrade` double(255, 2) NULL DEFAULT NULL COMMENT '修改分数',
  `applyDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请描述',
  `imgUri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_application
-- ----------------------------
INSERT INTO `t_application` VALUES (44, 2, 4, '3333', 2, '张三(学生)', 2, '大学语文', '王五(教师)', 100.00, 80.00, 60.00, '成绩有误，已与任课教师确认，请审批通过。', 'http://localhost:9000/fileupload/1622033950220.png');
INSERT INTO `t_application` VALUES (45, 3, 4, '3333', 1, '张三(学生)', 3, '数学II', '王五(教师)', 100.00, 30.00, 100.00, '分数有误，已与任课教师确认，请审批通过。', 'http://localhost:9000/fileupload/1622033997142.png');
INSERT INTO `t_application` VALUES (46, 4, 3, '3333', 0, '张三(学生)', 4, '数学III', '李四(教师)', 100.00, 90.00, 30.00, '分数有误，已与任课教师确认，请审批通过。', 'http://localhost:9000/fileupload/1622034011618.png');
INSERT INTO `t_application` VALUES (47, 1, 3, '3333', 0, '张三(学生)', 1, '数学I', '李四(教师)', 100.00, 30.00, 100.00, '12', 'http://localhost:9000/fileupload/1622120153649.png');

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam`  (
  `id` int NOT NULL COMMENT '考试编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试名称',
  `course_id` int NULL DEFAULT NULL COMMENT '考试课程(subjec表的sub_id)',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '考试类型',
  `time` datetime NULL DEFAULT NULL COMMENT '考试时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (1, '考试1', 8, '期末考试', '2021-04-24 16:37:25');

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '\r\n',
  `grade` double(255, 0) NULL DEFAULT NULL,
  `sub_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES (1, '3333', NULL, '1');
INSERT INTO `t_score` VALUES (2, '3333', NULL, '2');
INSERT INTO `t_score` VALUES (3, '3333', NULL, '3');
INSERT INTO `t_score` VALUES (4, '3333', 90, '4');
INSERT INTO `t_score` VALUES (5, '6666', 30, '1');
INSERT INTO `t_score` VALUES (6, '6666', 80, '2');
INSERT INTO `t_score` VALUES (7, '6666', 30, '3');
INSERT INTO `t_score` VALUES (8, '6666', 90, '4');
INSERT INTO `t_score` VALUES (9, '7777', 30, '1');
INSERT INTO `t_score` VALUES (10, '7777', 80, '2');
INSERT INTO `t_score` VALUES (11, '7777', 30, '3');
INSERT INTO `t_score` VALUES (12, '7777', 90, '4');
INSERT INTO `t_score` VALUES (13, '8888', 30, '1');
INSERT INTO `t_score` VALUES (14, '8888', 80, '2');
INSERT INTO `t_score` VALUES (15, '8888', 30, '3');
INSERT INTO `t_score` VALUES (16, '8888', 90, '4');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int NOT NULL COMMENT '关联用户id作为老师id',
  `sub_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程编号',
  `sub_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sub_pass` double(12, 0) NULL DEFAULT NULL COMMENT '及格分数',
  `sub_grade` double(12, 0) NULL DEFAULT NULL,
  `sub_now_count` int UNSIGNED NULL DEFAULT NULL COMMENT '科目剩余数量',
  `sub_total_count` int UNSIGNED NULL DEFAULT NULL COMMENT '科目总数量',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES (1, 3, 'math-1', '数学I', 45, 100, 20, 50, '2021-05-26 19:57:56', '2021-01-23 11:01:38');
INSERT INTO `t_subject` VALUES (2, 4, 'YuWen', '大学语文', 60, 100, 20, 50, '2021-01-23 09:17:34', '2021-05-26 19:58:01');
INSERT INTO `t_subject` VALUES (3, 4, 'math-2', '数学II', 45, 100, 20, 50, '2021-01-23 11:23:29', '2021-01-23 11:12:52');
INSERT INTO `t_subject` VALUES (4, 3, 'math-3', '数学III', 45, 100, 20, 50, '2021-05-26 19:58:00', '2021-01-23 16:35:40');

-- ----------------------------
-- Table structure for wx_permission
-- ----------------------------
DROP TABLE IF EXISTS `wx_permission`;
CREATE TABLE `wx_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` bigint NULL DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `status` int NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_permission
-- ----------------------------
INSERT INTO `wx_permission` VALUES (19, NULL, '查询全部用户', 'wx:user:findAllUser', NULL, NULL, NULL, 1, '2021-01-21 10:06:24', NULL);
INSERT INTO `wx_permission` VALUES (20, NULL, '冻结账户', 'wx:user:forbidden', NULL, NULL, NULL, 1, '2021-01-21 11:18:04', NULL);
INSERT INTO `wx_permission` VALUES (21, NULL, '账户解冻', 'wx:user:useful', NULL, NULL, NULL, 1, '2021-01-21 11:23:04', NULL);
INSERT INTO `wx_permission` VALUES (22, NULL, '删除用户', 'wx:user:delete', NULL, NULL, NULL, 1, '2021-01-21 18:46:24', NULL);
INSERT INTO `wx_permission` VALUES (23, NULL, '给用户分配角色', 'wx:user:userRole', NULL, NULL, NULL, 1, '2021-01-21 19:27:05', NULL);
INSERT INTO `wx_permission` VALUES (24, NULL, '分页查询全部科目', 'wx:subject:selectAllSubject', NULL, NULL, NULL, 1, '2021-01-22 14:35:24', NULL);
INSERT INTO `wx_permission` VALUES (25, NULL, '修改科目内容', 'wx:subject:updateSubject', NULL, NULL, NULL, 1, '2021-01-22 16:25:41', NULL);
INSERT INTO `wx_permission` VALUES (26, NULL, '增加新的科目', 'wx:subject:addSubject', NULL, NULL, NULL, 1, '2021-01-23 10:45:12', NULL);
INSERT INTO `wx_permission` VALUES (27, NULL, '删除科目', 'wx:subject:delSubject', NULL, NULL, NULL, 1, '2021-01-23 15:13:56', NULL);
INSERT INTO `wx_permission` VALUES (28, NULL, '查询全部角色', 'wx:roe:selectAllRole', NULL, NULL, NULL, 1, '2021-01-23 16:15:08', NULL);
INSERT INTO `wx_permission` VALUES (29, NULL, '新增角色', 'wx:roe:addRole', NULL, NULL, NULL, 1, '2021-01-23 16:17:30', NULL);
INSERT INTO `wx_permission` VALUES (30, NULL, '查询全部的权限', 'wx:permission:selectAllPermission', NULL, NULL, NULL, 1, '2021-01-23 16:21:18', NULL);
INSERT INTO `wx_permission` VALUES (31, NULL, '新增权限', 'wx:permission:addPermission', NULL, NULL, NULL, 1, '2021-01-23 16:23:19', NULL);
INSERT INTO `wx_permission` VALUES (33, NULL, '测试', 'wx:roe:addRole', NULL, NULL, NULL, 1, '2021-03-31 15:29:24', NULL);
INSERT INTO `wx_permission` VALUES (34, NULL, 'dynamic', 'wx:socre:dynamic', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (35, NULL, 'query', 'wx:socre:query', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (36, NULL, 'dynamic-tea', 'wx:socre:dynamic-tea', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (37, NULL, 'update', 'wx:socre:update', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (38, NULL, 'editBatch', 'wx:socre:editBatch', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (39, NULL, 'resetBatch', 'wx:socre:resetBatch', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (40, NULL, 'tea-detail', 'wx:socre:tea-detail', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (41, NULL, 'imgUp', 'wx:socre:imgUp', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (42, NULL, 'apply', 'wx:socre:apply', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (43, NULL, 'applyExist', 'wx:socre:applyExist', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (44, NULL, 'applications-tea', 'wx:socre:applications-tea', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (45, NULL, 'applications-stu', 'wx:socre:applications-stu', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (46, NULL, 'apply-deal', 'wx:socre:apply-deal', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);
INSERT INTO `wx_permission` VALUES (47, NULL, 'fallBackApply', 'wx:socre:fallBackApply', NULL, NULL, NULL, 1, '2021-05-27 20:21:09', NULL);

-- ----------------------------
-- Table structure for wx_role
-- ----------------------------
DROP TABLE IF EXISTS `wx_role`;
CREATE TABLE `wx_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int NULL DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` int NULL DEFAULT 1 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_role
-- ----------------------------
INSERT INTO `wx_role` VALUES (1, '管理员', '系统管理员', 15, '2020-07-30 15:46:11', 1, 0);
INSERT INTO `wx_role` VALUES (2, '老师', '教师', 9, '2021-01-23 15:18:40', 1, 0);
INSERT INTO `wx_role` VALUES (3, '学生', '学生', 9, '2020-07-30 15:53:56', 1, 0);
INSERT INTO `wx_role` VALUES (8, '测试', '测试', NULL, '2021-03-31 14:44:57', 1, 0);

-- ----------------------------
-- Table structure for wx_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `wx_role_permission_relation`;
CREATE TABLE `wx_role_permission_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `permission_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户角色和权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_role_permission_relation
-- ----------------------------
INSERT INTO `wx_role_permission_relation` VALUES (21, 2, 19);
INSERT INTO `wx_role_permission_relation` VALUES (22, 1, 20);
INSERT INTO `wx_role_permission_relation` VALUES (23, 1, 21);
INSERT INTO `wx_role_permission_relation` VALUES (24, 1, 22);
INSERT INTO `wx_role_permission_relation` VALUES (25, 1, 23);
INSERT INTO `wx_role_permission_relation` VALUES (26, 1, 19);
INSERT INTO `wx_role_permission_relation` VALUES (27, 3, 24);
INSERT INTO `wx_role_permission_relation` VALUES (28, 2, 24);
INSERT INTO `wx_role_permission_relation` VALUES (29, 1, 24);
INSERT INTO `wx_role_permission_relation` VALUES (30, 2, 25);
INSERT INTO `wx_role_permission_relation` VALUES (31, 1, 25);
INSERT INTO `wx_role_permission_relation` VALUES (32, 3, 26);
INSERT INTO `wx_role_permission_relation` VALUES (33, 2, 26);
INSERT INTO `wx_role_permission_relation` VALUES (34, 2, 27);
INSERT INTO `wx_role_permission_relation` VALUES (35, 1, 28);
INSERT INTO `wx_role_permission_relation` VALUES (36, 1, 29);
INSERT INTO `wx_role_permission_relation` VALUES (37, 1, 30);
INSERT INTO `wx_role_permission_relation` VALUES (38, 1, 31);
INSERT INTO `wx_role_permission_relation` VALUES (39, 7, 19);
INSERT INTO `wx_role_permission_relation` VALUES (40, 8, 19);
INSERT INTO `wx_role_permission_relation` VALUES (41, 8, 22);
INSERT INTO `wx_role_permission_relation` VALUES (42, 8, 23);
INSERT INTO `wx_role_permission_relation` VALUES (43, 8, 20);
INSERT INTO `wx_role_permission_relation` VALUES (44, 8, 21);
INSERT INTO `wx_role_permission_relation` VALUES (45, 8, 24);
INSERT INTO `wx_role_permission_relation` VALUES (46, 8, 25);
INSERT INTO `wx_role_permission_relation` VALUES (47, 8, 26);
INSERT INTO `wx_role_permission_relation` VALUES (48, 1, 34);
INSERT INTO `wx_role_permission_relation` VALUES (49, 1, 37);
INSERT INTO `wx_role_permission_relation` VALUES (50, 1, 38);
INSERT INTO `wx_role_permission_relation` VALUES (51, 1, 39);
INSERT INTO `wx_role_permission_relation` VALUES (52, 2, 36);
INSERT INTO `wx_role_permission_relation` VALUES (53, 2, 37);
INSERT INTO `wx_role_permission_relation` VALUES (54, 2, 44);
INSERT INTO `wx_role_permission_relation` VALUES (55, 2, 46);
INSERT INTO `wx_role_permission_relation` VALUES (56, 3, 35);
INSERT INTO `wx_role_permission_relation` VALUES (57, 3, 41);
INSERT INTO `wx_role_permission_relation` VALUES (58, 3, 42);
INSERT INTO `wx_role_permission_relation` VALUES (59, 3, 43);
INSERT INTO `wx_role_permission_relation` VALUES (60, 3, 45);
INSERT INTO `wx_role_permission_relation` VALUES (61, 3, 47);
INSERT INTO `wx_role_permission_relation` VALUES (62, 3, 40);

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES (1, '张三(学生)', '3333', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2345349823@qq.com', '13243657894', '2020-07-29 13:55:30', '2020-07-29 13:55:39', 1, '男');
INSERT INTO `wx_user` VALUES (2, '管理员', '0000', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2668486789@qq.com', '12474698789', '2020-07-29 13:32:47', '2020-07-29 15:38:50', 1, '男');
INSERT INTO `wx_user` VALUES (3, '李四(教师)', '4444', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2668486789@qq.com', '12474698789', '2020-07-29 13:32:47', '2020-07-29 15:38:50', 1, '男');
INSERT INTO `wx_user` VALUES (4, '王五(教师)', '5555', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2345349823@qq.com', '13243657894', '2020-07-29 13:55:30', '2020-07-29 13:55:39', 1, '男');
INSERT INTO `wx_user` VALUES (5, '赵六(学生)', '6666', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2345349823@qq.com', '13243657894', '2020-07-29 13:55:30', '2020-07-29 13:55:39', 1, '男');
INSERT INTO `wx_user` VALUES (6, '洪七(学生)', '7777', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2345349823@qq.com', '13243657894', '2020-07-29 13:55:30', '2020-07-29 13:55:39', 1, '男');
INSERT INTO `wx_user` VALUES (7, '刘八(学生)', '8888', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', '', '2345349823@qq.com', '13243657894', '2020-07-29 13:55:30', '2020-07-29 13:55:39', 1, '男');

-- ----------------------------
-- Table structure for wx_user_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_permission_relation`;
CREATE TABLE `wx_user_permission_relation`  (
  `id` bigint NOT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `permission_id` bigint NULL DEFAULT NULL,
  `type` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_user_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for wx_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `wx_user_role_relation`;
CREATE TABLE `wx_user_role_relation`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NULL DEFAULT NULL,
  `role_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户和角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_user_role_relation
-- ----------------------------
INSERT INTO `wx_user_role_relation` VALUES (1, 2, 1);
INSERT INTO `wx_user_role_relation` VALUES (2, 1, 3);
INSERT INTO `wx_user_role_relation` VALUES (3, 5, 3);
INSERT INTO `wx_user_role_relation` VALUES (4, 6, 3);
INSERT INTO `wx_user_role_relation` VALUES (5, 7, 3);
INSERT INTO `wx_user_role_relation` VALUES (6, 3, 2);
INSERT INTO `wx_user_role_relation` VALUES (7, 4, 2);

SET FOREIGN_KEY_CHECKS = 1;
