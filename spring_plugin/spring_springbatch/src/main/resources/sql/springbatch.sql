/*
SQLyog Ultimate v11.42 (64 bit)
MySQL - 5.5.19 : Database - springbatch
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springbatch` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springbatch`;

/*Table structure for table `ledgers` */

DROP TABLE IF EXISTS `ledgers`;

CREATE TABLE `ledgers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rcv_dt` date DEFAULT NULL,
  `mbr_nm` varchar(100) DEFAULT NULL,
  `chk_nbr` varchar(100) DEFAULT NULL,
  `chk_dt` date DEFAULT NULL,
  `pymt_typ` varchar(100) DEFAULT NULL,
  `dpst_amt` float(10,2) DEFAULT NULL,
  `pymt_amt` float(10,2) DEFAULT NULL,
  `comments` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;

/*Data for the table `ledgers` */

insert  into `ledgers`(`id`,`rcv_dt`,`mbr_nm`,`chk_nbr`,`chk_dt`,`pymt_typ`,`dpst_amt`,`pymt_amt`,`comments`) values (100,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(101,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(102,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(103,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(104,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(105,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(106,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(107,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(108,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(109,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(110,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(111,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(112,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(113,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(114,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(115,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(116,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(117,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(118,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(119,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(120,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(121,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(122,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(123,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(124,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(125,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(126,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(127,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(128,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(129,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(130,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(131,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(132,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(133,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(134,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(135,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(136,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(137,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(138,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(139,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(140,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(141,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(142,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(143,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(144,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(145,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(146,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(147,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(148,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(149,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(150,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(151,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(152,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(153,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(154,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(155,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(156,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(157,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(158,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(159,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(160,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(161,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(162,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(163,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(164,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(165,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(166,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(167,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(168,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(169,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(170,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(171,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(172,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(173,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(174,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(175,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(176,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(177,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(178,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(179,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(180,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(181,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(182,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(183,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(184,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(185,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(186,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(187,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(188,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(189,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments'),(190,'2013-10-09','Person1','1432','2013-10-09','Offertery',50.00,0.00,'comments'),(191,'2013-10-09','Person2','900','2013-10-09','Offertery',20.00,0.00,'comments'),(192,'2013-10-09','Person3','1802','2013-10-08','Membership',800.00,0.00,'comments'),(193,'2013-10-09','Person4','5281','2013-10-09','Membership',500.00,0.00,'split check'),(194,'2013-10-09','Person5','5281','2013-10-09','Memorial',50.00,0.00,'split check'),(195,'2013-10-09','Person6','1803','2013-10-08','Charity',100.00,0.00,'comments'),(196,'2013-10-09','Person7','1803','2013-10-08','Charity',50.00,0.00,'comments'),(197,'2013-10-09','Person8','1151','2013-10-01','Membership',500.00,0.00,'comments'),(198,'2013-10-06','Person8','1152','2013-10-01','Membership',250.00,0.00,'comments'),(199,'2013-10-01','Person8','1153','2013-10-01','Membership',50.00,0.00,'comments');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;