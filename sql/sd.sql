/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.6.26 : Database - sd_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sd_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sd_db`;

/*Table structure for table `attendence` */

CREATE TABLE `attendence` (
  `workerID` int(32) NOT NULL,
  `a_ID` int(32) NOT NULL,
  `clockTime` datetime DEFAULT NULL,
  PRIMARY KEY (`a_ID`),
  KEY `workerID` (`workerID`),
  CONSTRAINT `attendence_ibfk_1` FOREIGN KEY (`workerID`) REFERENCES `workinfo` (`workerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `others` */

CREATE TABLE `others` (
  `o_id` int(11) NOT NULL,
  `officetime` time DEFAULT NULL,
  `offworktime` time DEFAULT NULL,
  `latetime` time DEFAULT NULL,
  `leavetime` time DEFAULT NULL,
  PRIMARY KEY (`o_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `workinfo` */

CREATE TABLE `workinfo` (
  `workerID` int(32) NOT NULL COMMENT '员工ID',
  `workerName` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`workerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
