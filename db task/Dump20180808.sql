-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: DayManagerDB
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Table structure for table `cash_flow`
--

DROP TABLE IF EXISTS `cash_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_flow` (
  `cf_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `cost` float NOT NULL,
  `cfi_id` int(11) NOT NULL,
  UNIQUE KEY `cf_id_UNIQUE` (`cf_id`),
  KEY `fk_cash_flow_users1_idx` (`usr_id`),
  KEY `fk_cash_flow_cash_flow_items1_idx` (`cfi_id`),
  CONSTRAINT `fk_cash_flow_cash_flow_items1` FOREIGN KEY (`cfi_id`) REFERENCES `cash_flow_items` (`cfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cash_flow_users1` FOREIGN KEY (`usr_id`) REFERENCES `users` (`usr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash_flow`
--

LOCK TABLES `cash_flow` WRITE;
/*!40000 ALTER TABLE `cash_flow` DISABLE KEYS */;
INSERT INTO `cash_flow` VALUES (1,1,'2018-10-18',25,1),(2,1,'2018-08-07',11,2);
/*!40000 ALTER TABLE `cash_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash_flow_items`
--

DROP TABLE IF EXISTS `cash_flow_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_flow_items` (
  `cfi_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) NOT NULL,
  UNIQUE KEY `item_name_UNIQUE` (`item_name`),
  UNIQUE KEY `cfi_id_UNIQUE` (`cfi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash_flow_items`
--

LOCK TABLES `cash_flow_items` WRITE;
/*!40000 ALTER TABLE `cash_flow_items` DISABLE KEYS */;
INSERT INTO `cash_flow_items` VALUES (1,'транспорт'),(2,'еда'),(3,'зарплата'),(4,'налоги'),(5,'кварплата'),(7,'бухлишко'),(8,'ааа');
/*!40000 ALTER TABLE `cash_flow_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  UNIQUE KEY `role_name_UNIQUE` (`role_name`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_manage`
--

DROP TABLE IF EXISTS `time_manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_manage` (
  `tm_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `time_begin` varchar(45) NOT NULL,
  `time_end` varchar(45) NOT NULL,
  `tmi_id` int(11) NOT NULL,
  UNIQUE KEY `tm_id_UNIQUE` (`tm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_manage`
--

LOCK TABLES `time_manage` WRITE;
/*!40000 ALTER TABLE `time_manage` DISABLE KEYS */;
INSERT INTO `time_manage` VALUES (1,1,'2018-08-07','17-00','23-00',2),(2,1,'2018-08-08','18-00','18-00',3);
/*!40000 ALTER TABLE `time_manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_manage_items`
--

DROP TABLE IF EXISTS `time_manage_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_manage_items` (
  `tmi_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(45) NOT NULL,
  UNIQUE KEY `item_name_UNIQUE` (`item_name`),
  UNIQUE KEY `tmi_id_UNIQUE` (`tmi_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_manage_items`
--

LOCK TABLES `time_manage_items` WRITE;
/*!40000 ALTER TABLE `time_manage_items` DISABLE KEYS */;
INSERT INTO `time_manage_items` VALUES (1,'транспорт'),(2,'учеба'),(3,'работа'),(4,'прогулки'),(5,'развлечения'),(7,'налоги'),(8,'интернет');
/*!40000 ALTER TABLE `time_manage_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_privates`
--

DROP TABLE IF EXISTS `user_privates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_privates` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  UNIQUE KEY `usr_mail_UNIQUE` (`mail`),
  UNIQUE KEY `usr_id_UNIQUE` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='Разделяем таблицу потому что некоторые модули программы будут делать запросы для получения только логина и пароля, либо только ника пользователя';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_privates`
--

LOCK TABLES `user_privates` WRITE;
/*!40000 ALTER TABLE `user_privates` DISABLE KEYS */;
INSERT INTO `user_privates` VALUES (14,'aryna@mail.ru','93097499'),(6,'test1@gmail.com','110251487'),(7,'test2@gmail.com','110251488'),(10,'test3@gmail.com','110251489'),(11,'test4@gmail.com','110251490'),(12,'test5@gmail.com','110251491'),(13,'test6@gmail.com','110251492'),(1,'usr1@mail.by','110251487'),(2,'usr2@mail.by','2222'),(3,'usr3@mail.by','3333'),(4,'usr4@mail.by','4444'),(5,'usr5@mail.by','5555');
/*!40000 ALTER TABLE `user_privates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_nickname` varchar(45) NOT NULL,
  `role_id` int(11) NOT NULL,
  UNIQUE KEY `usr_id_UNIQUE` (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'first_user',1),(2,'second_user',1),(3,'third_user',1),(4,'forth_user',1),(5,'fifth_user',1),(6,'test1',1),(7,'test2',1),(10,'test3',1),(11,'test4',1),(12,'test5',1),(13,'test6',1),(14,'aryyyyna',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_cash_flow_items`
--

DROP TABLE IF EXISTS `users_has_cash_flow_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_cash_flow_items` (
  `usr_id` int(11) NOT NULL,
  `cfi_id` int(11) NOT NULL,
  PRIMARY KEY (`usr_id`,`cfi_id`),
  KEY `fk_users_has_cash_flow_items_cash_flow_items1_idx` (`cfi_id`),
  KEY `fk_users_has_cash_flow_items_users1_idx` (`usr_id`),
  CONSTRAINT `fk_users_has_cash_flow_items_cash_flow_items1` FOREIGN KEY (`cfi_id`) REFERENCES `cash_flow_items` (`cfi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_cash_flow_items_users1` FOREIGN KEY (`usr_id`) REFERENCES `users` (`usr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_cash_flow_items`
--

LOCK TABLES `users_has_cash_flow_items` WRITE;
/*!40000 ALTER TABLE `users_has_cash_flow_items` DISABLE KEYS */;
INSERT INTO `users_has_cash_flow_items` VALUES (1,1),(4,1),(5,1),(1,2),(3,2),(4,2),(1,3),(3,3),(4,3),(2,4),(3,4),(4,4),(2,5),(4,5);
/*!40000 ALTER TABLE `users_has_cash_flow_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_has_time_manage_items`
--

DROP TABLE IF EXISTS `users_has_time_manage_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_has_time_manage_items` (
  `usr_id` int(11) NOT NULL,
  `tmi_id` int(11) NOT NULL,
  PRIMARY KEY (`usr_id`,`tmi_id`),
  KEY `fk_users_has_time_manage_items_time_manage_items1_idx` (`tmi_id`),
  KEY `fk_users_has_time_manage_items_users1_idx` (`usr_id`),
  CONSTRAINT `fk_users_has_time_manage_items_time_manage_items1` FOREIGN KEY (`tmi_id`) REFERENCES `time_manage_items` (`tmi_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_time_manage_items_users1` FOREIGN KEY (`usr_id`) REFERENCES `users` (`usr_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_has_time_manage_items`
--

LOCK TABLES `users_has_time_manage_items` WRITE;
/*!40000 ALTER TABLE `users_has_time_manage_items` DISABLE KEYS */;
INSERT INTO `users_has_time_manage_items` VALUES (1,1),(4,1),(5,1),(1,2),(4,2),(1,3),(3,3),(4,3),(2,4),(3,4),(4,4),(2,5),(3,5),(4,5);
/*!40000 ALTER TABLE `users_has_time_manage_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-08 16:27:47
