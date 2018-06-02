-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: sla_2018_testing
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sla_address`
--

DROP TABLE IF EXISTS `sla_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_address` (
  `id_address` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `city` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `postal_code` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `id_customer` int(11) NOT NULL,
  PRIMARY KEY (`id_address`),
  KEY `sla_address_sla_customer_fk` (`id_customer`),
  CONSTRAINT `sla_address_sla_customer_fk` FOREIGN KEY (`id_customer`) REFERENCES `sla_customer` (`id_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_address`
--

LOCK TABLES `sla_address` WRITE;
/*!40000 ALTER TABLE `sla_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `sla_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_chip_card`
--

DROP TABLE IF EXISTS `sla_chip_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_chip_card` (
  `id_chip_card` int(11) NOT NULL AUTO_INCREMENT,
  `rfid_id` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `registered_timestamp` datetime(6) NOT NULL,
  `removed` decimal(38,0) DEFAULT NULL,
  `removed_timestamp` datetime(6) DEFAULT NULL,
  `deposit_price` int(11) DEFAULT NULL,
  `card_rented` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_chip_card`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_chip_card`
--

LOCK TABLES `sla_chip_card` WRITE;
/*!40000 ALTER TABLE `sla_chip_card` DISABLE KEYS */;
INSERT INTO `sla_chip_card` VALUES (1,'123456','2018-04-26 00:18:42.000000',NULL,NULL,1001,0),(2,'5678','2018-04-26 00:19:07.000000',NULL,NULL,5,0),(3,'123','2018-05-07 19:00:43.000000',NULL,NULL,18000,0),(4,'456','2018-05-08 21:13:43.000000',NULL,NULL,100,0),(5,'235351432','2018-05-15 09:48:12.000000',NULL,NULL,32432432,0);
/*!40000 ALTER TABLE `sla_chip_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_customer`
--

DROP TABLE IF EXISTS `sla_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_customer` (
  `id_customer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `registered` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_customer`
--

LOCK TABLES `sla_customer` WRITE;
/*!40000 ALTER TABLE `sla_customer` DISABLE KEYS */;
INSERT INTO `sla_customer` VALUES (12,'adsad','afda','1','1',NULL),(13,'ahoj','ahoj','1','1',NULL),(14,'Prvni','Objednavac','objednavac@objednavac.cz','123456789',NULL),(15,'Prvni','Objednavac','objednavac@objednavac.cz','123456789',NULL),(16,'Prvni','Objednbavac','a','112',NULL),(17,'prvni','objedn','adf','3343',NULL),(18,'prvni','Objednavac','q','213',NULL),(19,'sd','sd','sd','ds',NULL),(20,'SX','Z','','',NULL),(21,'AS','','','',NULL),(22,'','','','',NULL),(23,'a','','','',NULL),(24,'test','test','test','1',NULL),(25,'admin','admin','aaa','0',NULL),(26,'SDSA','','','',NULL),(27,'adsdsafsa','','','',NULL),(28,'dsadsadsads','<ssfdvdvxvxvcxvxc','vfdvcxvcxvcx','dxvdxvxff',NULL),(29,'user','user','user','user',NULL),(30,'dsfdsa','dsfas','safa','324543254325',NULL);
/*!40000 ALTER TABLE `sla_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_employee`
--

DROP TABLE IF EXISTS `sla_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `id_number` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `foreign_languages` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  `id_job` int(11) NOT NULL,
  PRIMARY KEY (`id_employee`),
  KEY `sla_emolyee_sla_job_fk` (`id_job`),
  CONSTRAINT `sla_emolyee_sla_job_fk` FOREIGN KEY (`id_job`) REFERENCES `sla_job` (`id_job`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_employee`
--

LOCK TABLES `sla_employee` WRITE;
/*!40000 ALTER TABLE `sla_employee` DISABLE KEYS */;
INSERT INTO `sla_employee` VALUES (2,'admin','admin','0','0',NULL,2),(3,'Jan','Novak','-1281841191','756439875','Anglictina',1);
/*!40000 ALTER TABLE `sla_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_job`
--

DROP TABLE IF EXISTS `sla_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_job` (
  `id_job` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`id_job`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_job`
--

LOCK TABLES `sla_job` WRITE;
/*!40000 ALTER TABLE `sla_job` DISABLE KEYS */;
INSERT INTO `sla_job` VALUES (1,'POKLADNA'),(2,'VEDOUCI');
/*!40000 ALTER TABLE `sla_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_order`
--

DROP TABLE IF EXISTS `sla_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_order` (
  `id_order` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime(6) DEFAULT NULL,
  `paid` tinyint(4) NOT NULL DEFAULT '0',
  `paid_datetime` datetime(6) DEFAULT NULL,
  `id_customer` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `sla_customer_fk` (`id_customer`),
  CONSTRAINT `sla_customer_fk` FOREIGN KEY (`id_customer`) REFERENCES `sla_customer` (`id_customer`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_order`
--

LOCK TABLES `sla_order` WRITE;
/*!40000 ALTER TABLE `sla_order` DISABLE KEYS */;
INSERT INTO `sla_order` VALUES (1,'2018-05-05 14:26:02.000000',1,'2018-05-09 07:37:20.000000',15),(2,'2018-05-05 14:31:19.000000',1,'2018-05-09 07:43:40.000000',16),(3,'2018-05-05 14:34:57.000000',1,'2018-05-09 07:48:11.000000',17),(4,'2018-05-05 14:39:48.000000',0,NULL,18),(5,'2018-05-05 15:58:40.000000',0,NULL,19),(6,'2018-05-05 16:00:57.000000',0,NULL,20),(7,'2018-05-05 16:03:54.000000',0,NULL,21),(8,'2018-05-05 16:11:45.000000',1,'2018-05-10 13:22:14.000000',22),(9,'2018-05-05 16:33:41.000000',0,NULL,23),(10,'2018-05-05 16:35:06.000000',0,NULL,24),(11,'2018-05-05 16:37:15.000000',0,NULL,25),(12,'2018-05-05 16:40:24.000000',1,'2018-05-09 13:19:25.000000',26),(13,'2018-05-05 16:42:31.000000',0,NULL,27),(14,'2018-05-07 09:06:57.000000',0,NULL,25),(15,'2018-05-07 18:58:43.000000',0,NULL,NULL),(16,'2018-05-07 18:59:58.000000',1,'2018-05-09 13:19:08.000000',28),(17,'2018-05-07 19:01:18.000000',0,NULL,NULL),(18,'2018-05-08 09:26:32.000000',0,NULL,NULL),(19,'2018-05-08 09:54:30.000000',0,NULL,NULL),(20,'2018-05-08 10:05:00.000000',0,NULL,NULL),(21,'2018-05-08 10:43:57.000000',0,NULL,NULL),(22,'2018-05-08 10:51:04.000000',0,NULL,NULL),(23,'2018-05-08 15:13:07.000000',0,NULL,NULL),(24,'2018-05-08 15:15:22.000000',0,NULL,NULL),(25,'2018-05-08 15:26:19.000000',0,NULL,NULL),(26,'2018-05-08 19:13:45.000000',0,NULL,NULL),(27,'2018-05-15 09:59:12.000000',0,NULL,30);
/*!40000 ALTER TABLE `sla_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_order_skipass_article`
--

DROP TABLE IF EXISTS `sla_order_skipass_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_order_skipass_article` (
  `id_order_skipass_article` int(11) NOT NULL AUTO_INCREMENT,
  `sla_order_id_order` int(11) NOT NULL,
  `card_rented` tinyint(4) NOT NULL DEFAULT '0',
  `card_returned` tinyint(4) NOT NULL DEFAULT '0',
  `id_skipass` int(11) NOT NULL,
  PRIMARY KEY (`id_order_skipass_article`),
  KEY `sla_order_fk` (`sla_order_id_order`),
  KEY `sla_skipass_fk` (`id_skipass`),
  CONSTRAINT `sla_order_fk` FOREIGN KEY (`sla_order_id_order`) REFERENCES `sla_order` (`id_order`),
  CONSTRAINT `sla_skipass_fk` FOREIGN KEY (`id_skipass`) REFERENCES `sla_skipass` (`id_skipass`)
) ENGINE=InnoDB AUTO_INCREMENT=263 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_order_skipass_article`
--

LOCK TABLES `sla_order_skipass_article` WRITE;
/*!40000 ALTER TABLE `sla_order_skipass_article` DISABLE KEYS */;
INSERT INTO `sla_order_skipass_article` VALUES (1,1,0,0,1),(2,1,0,0,1),(3,1,0,0,2),(4,2,0,0,1),(5,2,0,0,1),(6,2,0,0,2),(7,3,0,0,1),(8,3,0,0,1),(9,3,0,0,2),(10,4,0,0,1),(11,4,0,0,1),(12,4,0,0,2),(13,5,0,0,1),(14,6,0,0,1),(15,7,0,0,1),(16,8,0,0,1),(17,8,0,0,1),(18,8,0,0,1),(19,8,0,0,1),(20,8,0,0,1),(21,8,0,0,2),(22,8,0,0,2),(23,8,0,0,2),(24,9,0,0,2),(25,10,0,0,1),(26,11,0,0,1),(27,12,0,0,1),(28,13,0,0,1),(29,14,0,0,1),(30,14,0,0,1),(31,14,0,0,1),(32,14,0,0,1),(33,14,0,0,1),(34,14,0,0,2),(35,14,0,0,2),(36,14,0,0,2),(37,14,0,0,2),(38,14,0,0,2),(39,14,0,0,2),(40,14,0,0,2),(41,14,0,0,2),(42,14,0,0,2),(43,15,0,0,1),(44,15,0,0,1),(45,16,0,0,1),(46,16,0,0,1),(47,16,0,0,1),(48,16,0,0,2),(49,16,0,0,2),(50,16,0,0,2),(51,16,0,0,2),(52,16,0,0,2),(53,16,0,0,2),(54,16,0,0,2),(55,16,0,0,2),(56,16,0,0,2),(57,16,0,0,2),(58,16,0,0,2),(59,16,0,0,2),(60,16,0,0,2),(61,16,0,0,2),(62,16,0,0,2),(63,16,0,0,2),(64,17,0,0,1),(65,17,0,0,1),(66,17,0,0,1),(67,17,0,0,1),(68,17,0,0,1),(69,17,0,0,1),(70,17,0,0,1),(71,17,0,0,1),(72,17,0,0,1),(73,17,0,0,1),(74,17,0,0,1),(75,17,0,0,1),(76,17,0,0,1),(77,17,0,0,1),(78,17,0,0,1),(79,17,0,0,1),(80,17,0,0,1),(81,17,0,0,1),(82,17,0,0,1),(83,17,0,0,1),(84,17,0,0,1),(85,17,0,0,1),(86,17,0,0,1),(87,17,0,0,1),(88,18,0,0,1),(89,18,0,0,1),(90,18,0,0,1),(91,18,0,0,1),(92,18,0,0,1),(93,18,0,0,1),(94,18,0,0,1),(95,18,0,0,1),(96,18,0,0,1),(97,18,0,0,1),(98,18,0,0,1),(99,18,0,0,1),(100,18,0,0,1),(101,18,0,0,1),(102,18,0,0,2),(103,18,0,0,2),(104,18,0,0,2),(105,18,0,0,2),(106,19,0,0,1),(107,19,0,0,1),(108,19,0,0,1),(109,19,0,0,1),(110,19,0,0,1),(111,19,0,0,1),(112,19,0,0,1),(113,19,0,0,1),(114,19,0,0,1),(115,19,0,0,1),(116,19,0,0,1),(117,19,0,0,1),(118,19,0,0,1),(119,19,0,0,1),(120,19,0,0,1),(121,19,0,0,1),(122,19,0,0,1),(123,19,0,0,1),(124,19,0,0,1),(125,19,0,0,1),(126,19,0,0,1),(127,19,0,0,1),(128,19,0,0,1),(129,19,0,0,1),(130,19,0,0,2),(131,19,0,0,2),(132,19,0,0,2),(133,19,0,0,2),(134,19,0,0,2),(135,19,0,0,2),(136,19,0,0,2),(137,19,0,0,2),(138,19,0,0,2),(139,19,0,0,2),(140,19,0,0,2),(141,19,0,0,2),(142,19,0,0,2),(143,19,0,0,2),(144,19,0,0,2),(145,19,0,0,2),(146,19,0,0,2),(147,19,0,0,2),(148,19,0,0,2),(149,19,0,0,2),(150,19,0,0,2),(151,19,0,0,2),(152,19,0,0,2),(153,19,0,0,2),(154,19,0,0,2),(155,19,0,0,2),(156,19,0,0,2),(157,19,0,0,2),(158,19,0,0,2),(159,19,0,0,2),(160,19,0,0,2),(161,19,0,0,2),(162,19,0,0,2),(163,19,0,0,2),(164,19,0,0,2),(165,19,0,0,2),(166,19,0,0,2),(167,19,0,0,2),(168,19,0,0,2),(169,19,0,0,2),(170,19,0,0,2),(171,19,0,0,2),(172,19,0,0,2),(173,19,0,0,2),(174,19,0,0,2),(175,19,0,0,2),(176,19,0,0,2),(177,19,0,0,2),(178,19,0,0,2),(179,19,0,0,2),(180,20,0,0,1),(181,21,0,0,1),(182,21,0,0,1),(183,21,0,0,1),(184,21,0,0,1),(185,21,0,0,1),(186,21,0,0,1),(187,21,0,0,1),(188,21,0,0,2),(189,21,0,0,2),(190,21,0,0,2),(191,21,0,0,2),(192,21,0,0,2),(193,21,0,0,2),(194,21,0,0,2),(195,21,0,0,2),(196,21,0,0,2),(197,21,0,0,2),(198,21,0,0,2),(199,22,0,0,2),(200,22,0,0,2),(201,22,0,0,2),(202,22,0,0,2),(203,23,0,0,1),(204,23,0,0,1),(205,23,0,0,1),(206,23,0,0,1),(207,23,0,0,1),(208,23,0,0,1),(209,23,0,0,1),(210,23,0,0,1),(211,23,0,0,1),(212,23,0,0,1),(213,23,0,0,1),(214,25,0,0,1),(215,25,0,0,1),(216,25,0,0,1),(217,25,0,0,1),(218,25,0,0,1),(219,25,0,0,1),(220,25,0,0,1),(221,25,0,0,1),(222,25,0,0,1),(223,25,0,0,1),(224,25,0,0,2),(225,25,0,0,2),(226,25,0,0,2),(227,25,0,0,2),(228,26,0,0,3),(229,26,0,0,3),(230,26,0,0,3),(231,26,0,0,6),(232,26,0,0,6),(233,26,0,0,6),(234,26,0,0,1),(235,26,0,0,1),(236,26,0,0,1),(237,26,0,0,8),(238,26,0,0,8),(239,26,0,0,8),(240,27,0,0,1),(241,27,0,0,1),(242,27,0,0,2),(243,27,0,0,5),(244,27,0,0,5),(245,27,0,0,5),(246,27,0,0,5),(247,27,0,0,5),(248,27,0,0,5),(249,27,0,0,5),(250,27,0,0,5),(251,27,0,0,5),(252,27,0,0,5),(253,27,0,0,5),(254,27,0,0,5),(255,27,0,0,5),(256,27,0,0,5),(257,27,0,0,5),(258,27,0,0,5),(259,27,0,0,5),(260,27,0,0,5),(261,27,0,0,5),(262,27,0,0,5);
/*!40000 ALTER TABLE `sla_order_skipass_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_rent`
--

DROP TABLE IF EXISTS `sla_rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_rent` (
  `id_rent` int(11) NOT NULL AUTO_INCREMENT,
  `from_date` datetime NOT NULL,
  `TO_DATE` datetime NOT NULL,
  `deposit_paid` tinyint(4) NOT NULL,
  `deposit_returned` tinyint(4) NOT NULL,
  `id_order_skipass_article` int(11) NOT NULL,
  `id_chip_card` int(11) NOT NULL,
  `id_employee` int(11) NOT NULL,
  PRIMARY KEY (`id_rent`),
  KEY `sla_chip_card_fk` (`id_chip_card`),
  KEY `sla_emolyee_fk` (`id_employee`),
  KEY `sla_o_skipas_article_fk` (`id_order_skipass_article`),
  CONSTRAINT `sla_chip_card_fk` FOREIGN KEY (`id_chip_card`) REFERENCES `sla_chip_card` (`id_chip_card`),
  CONSTRAINT `sla_emolyee_fk` FOREIGN KEY (`id_employee`) REFERENCES `sla_employee` (`id_employee`),
  CONSTRAINT `sla_o_skipas_article_fk` FOREIGN KEY (`id_order_skipass_article`) REFERENCES `sla_order_skipass_article` (`id_order_skipass_article`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_rent`
--

LOCK TABLES `sla_rent` WRITE;
/*!40000 ALTER TABLE `sla_rent` DISABLE KEYS */;
INSERT INTO `sla_rent` VALUES (1,'2018-05-06 22:22:48','2018-05-06 22:27:07',1,1,5,1,2),(2,'2018-05-07 09:01:19','2018-05-07 09:02:45',1,1,6,2,2),(3,'2018-05-08 13:32:23','2018-05-08 13:36:42',1,1,4,1,2),(4,'2018-05-08 13:36:05','2018-05-08 13:40:24',1,1,1,1,2),(5,'2018-05-08 13:40:53','2018-05-08 13:45:12',1,1,1,1,2),(6,'2018-05-08 13:43:34','2018-05-08 13:47:53',1,1,1,1,2),(7,'2018-05-08 13:43:38','2018-05-08 13:47:57',1,1,1,1,2),(8,'2018-05-08 13:54:04','2018-05-08 13:58:23',1,1,1,1,2),(9,'2018-05-08 13:56:25','2018-05-08 14:00:44',1,1,1,1,2),(10,'2018-05-08 14:04:32','2018-05-08 14:08:51',1,1,4,1,2),(11,'2018-05-08 14:08:21','2018-05-08 14:12:40',1,1,4,2,2),(12,'2018-05-08 14:12:11','2018-05-08 14:16:30',1,1,4,1,2),(13,'2018-05-08 14:15:35','2018-05-08 14:19:54',1,1,4,1,2),(14,'2018-05-08 15:17:30','2018-05-08 15:21:49',1,1,2,1,2),(15,'2018-05-08 15:17:56','2018-05-08 15:22:15',1,1,1,1,2),(16,'2018-05-08 15:26:39','2018-05-08 15:30:58',1,1,214,1,2),(17,'2018-05-08 15:26:57','2018-05-08 15:31:16',1,1,1,1,2),(18,'2018-05-08 18:32:34','2018-05-08 18:36:53',1,0,1,1,2),(19,'2018-05-09 08:55:26','2018-05-09 08:59:45',1,1,2,1,2);
/*!40000 ALTER TABLE `sla_rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_role`
--

DROP TABLE IF EXISTS `sla_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8_czech_ci DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_role`
--

LOCK TABLES `sla_role` WRITE;
/*!40000 ALTER TABLE `sla_role` DISABLE KEYS */;
INSERT INTO `sla_role` VALUES (1,'ROLE_CUSTOMER'),(2,'ROLE_MANAGER'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `sla_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_salaries`
--

DROP TABLE IF EXISTS `sla_salaries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_salaries` (
  `id_salaries` int(11) NOT NULL AUTO_INCREMENT,
  `salary` int(11) NOT NULL,
  `from_date` datetime NOT NULL,
  `TO_DATE` datetime DEFAULT NULL,
  `id_employee` int(11) NOT NULL,
  PRIMARY KEY (`id_salaries`),
  KEY `salaries_sla_emolyee_fk` (`id_employee`),
  CONSTRAINT `salaries_sla_emolyee_fk` FOREIGN KEY (`id_employee`) REFERENCES `sla_employee` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_salaries`
--

LOCK TABLES `sla_salaries` WRITE;
/*!40000 ALTER TABLE `sla_salaries` DISABLE KEYS */;
/*!40000 ALTER TABLE `sla_salaries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_skipass`
--

DROP TABLE IF EXISTS `sla_skipass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_skipass` (
  `id_skipass` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  `number_of_days` int(11) NOT NULL,
  PRIMARY KEY (`id_skipass`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_skipass`
--

LOCK TABLES `sla_skipass` WRITE;
/*!40000 ALTER TABLE `sla_skipass` DISABLE KEYS */;
INSERT INTO `sla_skipass` VALUES (1,1700,3),(2,659,1),(3,7000,11),(5,131,2),(6,1000000,3),(7,500,22),(8,9991,134);
/*!40000 ALTER TABLE `sla_skipass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_user`
--

DROP TABLE IF EXISTS `sla_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_czech_ci NOT NULL,
  `id_customer` int(11) DEFAULT NULL,
  `id_employee` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `sla_user_sla_customer_fk` (`id_customer`),
  KEY `sla_user_sla_employee_fk` (`id_employee`),
  CONSTRAINT `sla_user_sla_customer_fk` FOREIGN KEY (`id_customer`) REFERENCES `sla_customer` (`id_customer`),
  CONSTRAINT `sla_user_sla_employee_fk` FOREIGN KEY (`id_employee`) REFERENCES `sla_employee` (`id_employee`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_user`
--

LOCK TABLES `sla_user` WRITE;
/*!40000 ALTER TABLE `sla_user` DISABLE KEYS */;
INSERT INTO `sla_user` VALUES (12,'admin','$2a$11$0cd7rmTNH8EiLVXi1XiYhO7I72MQHGpMdvP8JYJoNPcuXJ6ervfiC',25,2),(13,'1','$2a$11$5dYlkjgSS8IKj5pMcxuu.eOEk3dd5lrjYkNkcW7zA6B2rxEnHcOq6',12,NULL),(14,'1','$2a$11$zK0s3MFcpcn1YnTO6mVMcuOhD8T4DqFIbRZ4yRpRNMfKueQqZYz4a',13,NULL),(15,'test','$2a$11$DhBJfXyLuBWaFG1HFle1aunoBPEYivEu9NGcpi80K2blMWClSD6zy',24,NULL),(16,'user','$2a$11$/J2VizHBWT4xhr0QVILD3OkXQ4u0Fwc8yxjpVNXKiKfaKhUwXbcZm',29,NULL),(17,'novakjan','$2a$11$H/nwo8J4JHUvJZVofBAM8u2qL/hQaM3QdjJwNobPd8uQ8SFqxBq7K',NULL,3);
/*!40000 ALTER TABLE `sla_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sla_user_role`
--

DROP TABLE IF EXISTS `sla_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sla_user_role` (
  `id_user_role` int(11) NOT NULL AUTO_INCREMENT,
  `id_role` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_user_role`),
  KEY `sla_user_role_sla_role_fk` (`id_role`),
  KEY `sla_user_role_sla_user_fk` (`id_user`),
  CONSTRAINT `sla_user_role_sla_role_fk` FOREIGN KEY (`id_role`) REFERENCES `sla_role` (`id_role`),
  CONSTRAINT `sla_user_role_sla_user_fk` FOREIGN KEY (`id_user`) REFERENCES `sla_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sla_user_role`
--

LOCK TABLES `sla_user_role` WRITE;
/*!40000 ALTER TABLE `sla_user_role` DISABLE KEYS */;
INSERT INTO `sla_user_role` VALUES (5,3,12),(6,1,13),(7,1,14),(8,1,15),(9,1,16),(10,3,17);
/*!40000 ALTER TABLE `sla_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'sla_2018_testing'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-01 23:36:30
