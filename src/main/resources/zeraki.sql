-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.22-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for zeraki
DROP DATABASE IF EXISTS `zeraki`;
CREATE DATABASE IF NOT EXISTS `zeraki` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `zeraki`;

-- Dumping structure for table zeraki.app_user
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE IF NOT EXISTS `app_user` (
  `id` varchar(255) NOT NULL,
  `account_type` int(11) DEFAULT NULL,
  `auth_username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table zeraki.app_user: ~0 rows (approximately)
/*!40000 ALTER TABLE `app_user` DISABLE KEYS */;
INSERT INTO `app_user` (`id`, `account_type`, `auth_username`, `email`, `firstname`, `lastname`, `password`, `timestamp`) VALUES
	('b37517bd-88fd-4351-bd4a-4f2075d1b031', 0, 'admin@zeraki.com', 'admin@zeraki.com', 'admin', 'super', '$2a$10$3m.32gCBeAHlvFklfQTUHe83LhkG8mkEHWwyhEF33tHbgsAbpFAiK', '2022-01-22 13:22:36');
/*!40000 ALTER TABLE `app_user` ENABLE KEYS */;

-- Dumping structure for table zeraki.course
DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `id` varchar(50) NOT NULL,
  `institution_id` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `institution_id_name` (`institution_id`,`name`),
  CONSTRAINT `FK__institution` FOREIGN KEY (`institution_id`) REFERENCES `institution` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table zeraki.course: ~3 rows (approximately)
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`id`, `institution_id`, `name`, `keywords`, `timestamp`) VALUES
	('8a16a3e9-5ff5-4b2a-a219-f40f7be754a9', '124e6b1c-8ee6-40e3-af79-3183082e95ab', 'Information Technology', 'Information Technology', '2022-01-23 01:33:24'),
	('a7432372-5b91-4a6a-8e47-d110f8601a85', '124e6b1c-8ee6-40e3-af79-3183082e95ab', 'Computer Science Edited', 'Computer Science Edited', '2022-01-23 01:06:32');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;

-- Dumping structure for table zeraki.institution
DROP TABLE IF EXISTS `institution`;
CREATE TABLE IF NOT EXISTS `institution` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `location` varchar(50) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table zeraki.institution: ~4 rows (approximately)
/*!40000 ALTER TABLE `institution` DISABLE KEYS */;
INSERT INTO `institution` (`id`, `name`, `location`, `keywords`, `timestamp`) VALUES
	('124e6b1c-8ee6-40e3-af79-3183082e95ab', 'Maseno University', 'Kisumu', 'Kisumu Maseno University', '2022-01-22 23:19:42'),
	('7c27ea51-a956-4668-a128-71eb82c6394a', 'University Of Nairobi', 'Nairobi Kenya', 'Nairobi Kenya University Of Nairobi', '2022-01-22 22:35:56'),
	('d15ced99-0a35-4d4c-8e69-de6615dde093', 'Moi University', 'Eldoret', 'Eldoret Moi University', '2022-01-22 23:19:17');
/*!40000 ALTER TABLE `institution` ENABLE KEYS */;

-- Dumping structure for table zeraki.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `course_id` varchar(50) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_course` (`course_id`),
  CONSTRAINT `FK_student_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table zeraki.student: ~1 rows (approximately)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `name`, `course_id`, `gender`, `timestamp`, `keywords`) VALUES
	('993a4af7-a4cd-4593-ac06-fcd8d26a8d01', 'Chando Steve Yogo edited', 'a7432372-5b91-4a6a-8e47-d110f8601a85', NULL, '2022-01-23 13:07:57', 'Chando Steve Yogo editedComputer Science Edited Kisumu Maseno University');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table zeraki.token
DROP TABLE IF EXISTS `token`;
CREATE TABLE IF NOT EXISTS `token` (
  `id` varchar(255) NOT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table zeraki.token: ~0 rows (approximately)
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` (`id`, `refresh_token`, `token`) VALUES
	('b37517bd-88fd-4351-bd4a-4f2075d1b031', 'wr091yx87n3qyg0uehe0sp7hhrox48uhvun873k7mqe8vda5vj', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkB6ZXJha2kuY29tIiwiZXhwIjoxNjQyOTM1NDg0fQ.Ytxh9h_xWB9Wr4f0_Mh6_alqrFTJq3pSInktzyzh0_wU4g_Ij5AO41gkkOZ9-6HmHaBoGdWNvJxHnBv6IbtLhQ');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;

-- Dumping structure for table zeraki.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table zeraki.user_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `role`, `timestamp`, `user_id`) VALUES
	('2276a2b3-3806-4498-b631-94a6416e45a7', 'ROLE_ADMIN', NULL, 'b37517bd-88fd-4351-bd4a-4f2075d1b031');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
