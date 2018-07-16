-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` char(36) NOT NULL DEFAULT '' COMMENT '用户唯一标识符',
  `username` varchar(30) NOT NULL COMMENT '用户名-唯一',
  `password` char(32) NOT NULL COMMENT '登录密码（口令）',
  `email` varchar(200) NOT NULL COMMENT '邮箱',
  `last_login_datetime` datetime DEFAULT NULL COMMENT '上次登录的时间',
  `role` char(36) NOT NULL DEFAULT '10' COMMENT '角色 admin: 0  ,  user:10',
  `activated` bit(1) NOT NULL DEFAULT b'0' COMMENT '用户是否已经激活',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_uindex` (`username`),
  UNIQUE KEY `user_email_uindex` (`email`),
  KEY `user_role_id_fk` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` char(36) NOT NULL COMMENT '任务分类表的UUID',
  `category_name` varchar(255) NOT NULL COMMENT '分类的名字',
  `user_id` char(36) NOT NULL COMMENT '用户UUID',
  `parent_id` char(36) DEFAULT '0' COMMENT '父级文件夹的UUID',
  PRIMARY KEY (`id`),
  KEY `category_user_id_fk` (`user_id`),
  CONSTRAINT `category_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';


DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` char(36) NOT NULL COMMENT '任务的UUID',
  `title` varchar(255) NOT NULL COMMENT '任务名称/标题',
  `began_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  `remind_time` datetime DEFAULT NULL COMMENT '提醒时间：存一串逗号分隔的字符串，比如0,1,5,10意思就是活动开始前的0分钟，1分钟，五分钟，10分钟，分别提醒。为空就不提醒',
  `user_id` char(36) NOT NULL COMMENT '用户ID',
  `notation` mediumtext COMMENT '备注',
  `is_finished` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `task_category_id` char(36) NOT NULL COMMENT '任务分类UUID',
  PRIMARY KEY (`id`),
  KEY `task_category_id_fk` (`task_category_id`),
  KEY `task_end_time_index` (`title`) USING BTREE,
  CONSTRAINT `task_category_id_fk` FOREIGN KEY (`task_category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';
