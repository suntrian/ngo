DROP DATABASE IF EXISTS `ngo`;

CREATE DATABASE IF NOT EXISTS `ngo`;

USE `ngo`;

########################################################################################################################
## 用户相关
########################################################################################################################

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT UNSIGNED PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT       COMMENT 'id',
  `username`    VARCHAR(32) NOT NULL UNIQUE                 COMMENT 'username',
  `usercode`    VARCHAR(32)                                 COMMENT 'usercode',
  `realname`    VARCHAR(32)                                 COMMENT 'realname',
  `nickname`    VARCHAR(32)                                 COMMENT 'nickname',
  `password`    VARCHAR(32) NOT NULL                        COMMENT 'password',
  `mobile`      VARCHAR(13) UNIQUE                          COMMENT 'telephone',
  `email`       VARCHAR(32) UNIQUE                          COMMENT 'email',
  `wechat`      VARCHAR(32) UNIQUE                          COMMENT 'wechat',
  `weibo`       VARCHAR(32) UNIQUE                          COMMENT 'weibo',
  `last_login`  DATETIME                                    COMMENT 'last login time',
  `last_login_ip` VARCHAR(32)                               COMMENT 'last login ip',
  KEY (`username`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT 'user account';

CREATE TABLE IF NOT EXISTS `user_profile` (
  `id`          INT UNSIGNED PRIMARY KEY                    COMMENT 'id 1-1 reference to user id',
  `gender`      TINYINT(1)                                  COMMENT 'gender',
  `avatar`      VARCHAR(32)                                 COMMENT 'photo',
  `birthday`    DATE                                        COMMENT 'birthday',
  `home_address`VARCHAR(64)                                 COMMENT 'home address',
  `city`        TINYINT(4) UNSIGNED                         COMMENT 'city code',
  `corp_address`VARCHAR(64)                                 COMMENT 'corporation',
  `create_time` DATETIME                                    COMMENT 'when account created',
  `comment`     VARCHAR(1024)                               COMMENT 'comment',
  CONSTRAINT `fk_profile_user` FOREIGN KEY (`id`) REFERENCES `user`(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT 'user profile';

########################################################################################################################
## 单位，部门相关
########################################################################################################################

CREATE TABLE IF NOT EXISTS `corporation` (
  `id` INT  UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE COMMENT 'id',
  `name`  VARCHAR(32) UNIQUE NOT NULL                       COMMENT 'corporation name',
  `domain`  VARCHAR(8)                                      COMMENT 'corporation domain',
  `type`    VARCHAR(8)                                      COMMENT 'corp type',
  `address` VARCHAR(64)                                     COMMENT 'address',
  `legal_representative` VARCHAR(8)                         COMMENT '',
  `code`    VARCHAR(32)                                     COMMENT 'corp code'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT 'corporation';

CREATE TABLE IF NOT EXISTS `department` (
  `id`  INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE COMMENT 'id',
  `name`        VARCHAR(32) NOT NULL                        COMMENT 'department name',
  `type`      TINYINT(2)                                  COMMENT '部门类型 研发 财务 生产 市场……',
  `parent`    INT DEFAULT 0                                 COMMENT '上级部门',
  `corporation` INT UNSIGNED                                COMMENT '所属公司',
  #FOREIGN KEY (`parent`) REFERENCES `department`(`id`),
  CONSTRAINT `fk_depart_corp` FOREIGN KEY (`corporation`) REFERENCES `corporation`(`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '部门';

CREATE TABLE IF NOT EXISTS `position` (
  `id` INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT UNIQUE COMMENT '',
  `name`      VARCHAR(32) NOT NULL                        COMMENT 'position name',
  `type`      TINYINT(2)                                  COMMENT '岗位类型-管理/行政/执行',
  `comment`   VARCHAR(256)                                COMMENT '岗位说明'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '岗位 无须与部门关联，所有公司可共享';

########################################################################################################################
## 项目相关
########################################################################################################################

CREATE TABLE  IF NOT EXISTS `project` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'id',
  `name` VARCHAR(32) NOT NULL                               COMMENT 'project name',
  `type` TINYINT(2)                                         COMMENT '项目类型',
  `status` TINYINT(2)                                       COMMENT '项目状态',
  `code`  VARCHAR(32)                                       COMMENT '项目编号',
  `level` TINYINT(2)                                        COMMENT '项目级别',
  `summary`   VARCHAR(256)                                  COMMENT '项目摘要',
  `comment`   VARCHAR(1024)                                 COMMENT '项目说明',
  `creator`   INT                                           COMMENT '创建人',
  `create_time` DATETIME                                    COMMENT '创建时间',
  `start_time` DATE                                         COMMENT '项目启动时间',
  `end_time`    DATE                                        COMMENT '项目结项时间',
  `manager`   INT                                           COMMENT '项目经理'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '项目';

CREATE TABLE IF NOT EXISTS `project_type` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'id',
  `name` VARCHAR(32)  NOT NULL                              COMMENT '类型名称'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '项目类型 可共用';

CREATE TABLE IF NOT EXISTS `project_role` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'id',
  `name` VARCHAR(32)  NOT NULL                              COMMENT '角色名称',
  `project_type` INT                                        COMMENT '项目类型',
  `weight` INT DEFAULT 0                                    COMMENT '项目角色权重 被使用次数'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '项目角色 和项目类型关联 全局共用';

########################################################################################################################
## 文档相关
########################################################################################################################

CREATE TABLE IF NOT EXISTS `document` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'id',
  `name` VARCHAR(32) NOT NULL                                   COMMENT 'name',
  `project` INT DEFAULT NULL                                    COMMENT '关联项目ID 默认NULL,可不关联项目',
  `version` INT                                                 COMMENT '文档版本',
  `type` TINYINT(4)                                             COMMENT '文档类型',
  `corporation` INT NOT NULL                                    COMMENT '文档所属企业',
  `creator` INT                                                 COMMENT '创建人ID',
  `create_time` DATETIME                                        COMMENT '创建时间',
  `comment` VARCHAR(1024)                                       COMMENT '文档说明'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '文档';

CREATE TABLE IF NOT EXISTS `document_version` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT '文档版本ID',
  `name` VARCHAR(32)  NOT NULL                                  COMMENT '文档版本名称',
  `document` INT NOT NULL                                       COMMENT '所属文档ID'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '文档版本';

CREATE TABLE IF NOT EXISTS `document_type`(
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'id',
  `name` VARCHAR(32) NOT NULL                                   COMMENT '文档类型名称',
  `project_type` VARCHAR(128) DEFAULT NULL                      COMMENT '项目类型ID逗号分隔值 与项目类型关联',
  `weight` INT  DEFAULT 0                                       COMMENT '文档类型权重 被使用次数'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '文档类型  和项目类型关联  全局共用';

CREATE TABLE IF NOT EXISTS `document_section` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'id',
  `name` VARCHAR(32) NOT NULL                                   COMMENT '文档节次名称',
  `document` INT NOT NULL                                       COMMENT '所属文档ID'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '文档节次 章节';

CREATE TABLE IF NOT EXISTS `document_item` (
  `id` INT UNSIGNED UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT '文档条目ID',
  `name` VARCHAR(32) DEFAULT NULL                             COMMENT '文档条目名称，可为空',
  `content` TEXT DEFAULT NULL                         COMMENT '文档条目内容',
  `document` INT NOT NULL                                       COMMENT '所属文档ID',
  `section` INT DEFAULT NULL                                    COMMENT '文档所属章节，为空时直属文档',
  `version` VARCHAR(128) DEFAULT NULL                           COMMENT '所属文档的版本，文档版本ID逗号分隔值'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '文档条目';

########################################################################################################################
## 测试相关
########################################################################################################################

CREATE TABLE IF NOT EXISTS `testcase` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE  COMMENT '测试用例ID',
  `name` VARCHAR(64)                                            COMMENT '测试用例名称'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '测试用例';

CREATE TABLE IF NOT EXISTS `ticket` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT 'bug id',
  `name` VARCHAR(32) NOT NULL                                   COMMENT 'bug name',
  `type` INT NOT NULL                                           COMMENT 'bug type'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '缺陷';


########################################################################################################################
## 任务相关
########################################################################################################################

CREATE TABLE IF NOT EXISTS `task` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT '任务ID',
  `name` VARCHAR(64)                                            COMMENT '任务名称',
  `project` INT DEFAULT NULL                                    COMMENT '所属项目ID 可不关联项目',
  `content` VARCHAR(2048)                                      COMMENT '任务内容',
  `type` INT                                                    COMMENT '任务类型',
  `status` INT                                                  COMMENT '任务状态',
  `plan_start` DATETIME                                         COMMENT '计划开始',
  `plan_finish` DATETIME                                        COMMENT '计划完成',
  `actual_start` DATETIME                                       COMMENT '实际开始',
  `actual_finish` DATETIME                                      COMMENT '实际完成'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '任务';

CREATE TABLE IF NOT EXISTS `task_type` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT '任务类型ID',
  `name` VARCHAR(32) NOT NULL                                  COMMENT '任务类型名称'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '任务类型';

CREATE TABLE IF NOT EXISTS `task_status` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT '任务状态ID',
  `name` VARCHAR(32) NOT NULL                                  COMMENT '任务状态'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '任务状态';

CREATE TABLE IF NOT EXISTS `task_handler` (
  `task` INT NOT NULL                                         COMMENT '任务ID',
  `user` INT NOT NULL                                         COMMENT '处置人ID',
  `type` INT NOT NULL                                         COMMENT '处置类型 负责人/参与人/验收人/...',
  PRIMARY KEY (`task`, `user`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '任务处置人';

CREATE TABLE IF NOT EXISTS `task_handler_type` (
  `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE COMMENT '任务处置人类型ID',
  `name` VARCHAR(32) NOT NULL                                  COMMENT '任务处置人类型名称'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '任务处置人类型';;

