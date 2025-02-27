DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_no` varchar(32) NOT NULL DEFAULT '' COMMENT '课程号',
  `course_name` varchar(32) NOT NULL DEFAULT '' COMMENT '课程名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_name` (`course_name`,`course_no`),
  UNIQUE KEY `course_no` (`course_no`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;


INSERT INTO `course` VALUES (1,'101','APEX'),(2,'102','原神'),(3,'103','CS2');


DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_no` varchar(32) NOT NULL DEFAULT '' COMMENT '学号',
  `course_no` varchar(32) NOT NULL DEFAULT '' COMMENT '课程号',
  `mark` int(11) NOT NULL DEFAULT '0' COMMENT '成绩',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_no` (`course_no`,`student_no`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;


INSERT INTO `score` VALUES (2,'229971116','101',89),(4,'229971314','101',77),(5,'229971166','101',99),(6,'229971166','102',10),(7,'229971314','103',66);


DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_no` varchar(32) NOT NULL DEFAULT '' COMMENT '学号',
  `student_name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `class_info` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_name` (`student_name`,`student_no`),
  UNIQUE KEY `student_no` (`student_no`),
  UNIQUE KEY `student_name_2` (`student_name`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;


INSERT INTO `student` VALUES (2,'229971116','Tropic','22软工5班'),(4,'229971314','celever1314','22软工6班'),(66,'229971166','fallen','22软工5班');


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `pwd` varchar(32) NOT NULL DEFAULT '123456' COMMENT '密码',
  `role` int(11) NOT NULL DEFAULT '2' COMMENT '1老师 2学生',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique1` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;


INSERT INTO `user` VALUES (1,'admin','123456',1),(2,'229971116','123456',2),(4,'229971314','123456',2),(6,'229971166','123456',2);