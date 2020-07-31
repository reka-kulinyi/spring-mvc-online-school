DROP DATABASE  IF EXISTS `spring_online_school`;

CREATE DATABASE IF NOT EXISTS `spring_online_school`;
USE `spring_online_school`;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`password` VARCHAR(150) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL UNIQUE,
	`created_at` DATETIME DEFAULT NOW(),
	PRIMARY KEY(`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Populating table `user` with data
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- Default password for all the populated users is: fun123
--

INSERT INTO `user` (email, password, first_name, last_name, created_at)
VALUES 
('john@hamstermail.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Taylor', '2019-05-25'),
('rose@hamstermail.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Rose','Smith', '2020-01-12'),
('joe@catmail.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Joe','Wright', date_sub(NOW(), INTERVAL 1 DAY)),
('mary@catmail.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Smith', '2018-11-26'),
('james@catmail.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','James','Anderson', '2020-04-30'),
('tom@adminmail.com','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Tom','Admin', '2018-10-24');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) DEFAULT NULL,
	PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

--
-- Adding data for table `roles`
--

INSERT INTO `role` (name)
VALUES 
('ROLE_ADMIN'),('ROLE_TEACHER'),('ROLE_STUDENT');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Adding data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 2),
(2, 2),
(3, 2),
(4, 3),
(5, 3),
(6, 1);

--
-- Table structure for table 'subject'
--

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) DEFAULT NULL,
	PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=latin1;

--
-- Adding data for table 'subject'
--

INSERT INTO `subject` (name)
VALUES 
('Maths'),('English'), ('Spanish'), ('German'), ('French'), ('Italian'), ('Physics') , ('Literature'), ('History'), ('Chemistry');

--
-- Table structure for table 'course'
--

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `subject_id` INT(11) NOT NULL,
  `instructor_id` INT(11) NOT NULL,
  `price` DECIMAL(7, 2) DEFAULT 0.0,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_INSTRUCTOR_idx` (`instructor_id`),
  
  CONSTRAINT `FK_INSTRUCTOR` 
  FOREIGN KEY (`instructor_id`) 
  REFERENCES `user`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_SUBJECT` 
  FOREIGN KEY (`subject_id`) 
  REFERENCES `subject`(`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;

--
-- Adding data for table 'course'
--
INSERT INTO `course` (instructor_id, subject_id, price)
VALUES
(1, 1, 20.50), (1,2,15.30), (2,4,10.50), (2,7,8.25);


--
-- Table structure for table 'review'
--

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `student_id` INT(11) NOT NULL,
  `course_id` INT(11) NOT NULL,
  `review_text` VARCHAR(500) NOT NULL,
  
  PRIMARY KEY (`student_id`, `course_id`),
  
  KEY `FK_COURSE_idx` (`course_id`),
  
  CONSTRAINT `FK_COURSE` 
  FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_STUDENT` FOREIGN KEY (`student_id`) 
  REFERENCES `user`(`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;

--
-- Adding data for table 'review'
--
INSERT INTO `review` (student_id, course_id, review_text)
VALUES
(4, 1, 'Joe is an amazing teacher, now I have all As in Maths!'), 
(5, 2, 'Joe is an excellent instructor, I have so much more confidence now and I managed to pass my English language exam with flying colors'), 
(4, 3, 'Rose is an excellent German teacher, she helped me rewrite my CV in german');

--
-- Table structure for table 'student_course'
--

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `student_id` INT(11) NOT NULL,
  `course_id` INT(11) NOT NULL,
  
  PRIMARY KEY (`student_id`, `course_id`),
  
  KEY `FK_STUDENT_idx` (`student_id`),
  
  CONSTRAINT `FK_STUDENT_05` FOREIGN KEY (`student_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_COURSE_05` FOREIGN KEY (`course_id`) 
  REFERENCES `course` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

--
-- Adding data for table 'student course'
--

INSERT INTO `student_course` (student_id, course_id)
VALUES 
(4, 1),
(4, 3),
(5, 2),
(5, 4);

