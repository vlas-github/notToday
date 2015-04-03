-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: nottoday
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.10.1-log

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
-- Table structure for table `News`
--

DROP TABLE IF EXISTS `News`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `News` (
  `_id` varchar(255) NOT NULL,
  `_active` int(11) DEFAULT NULL,
  `_change_date` datetime DEFAULT NULL,
  `_guid` varchar(255) DEFAULT NULL,
  `_next` varchar(255) DEFAULT NULL,
  `_previous` varchar(255) DEFAULT NULL,
  `_author` tinyblob,
  `_creation_date` datetime DEFAULT NULL,
  `_dislikes` int(11) DEFAULT NULL,
  `_english_text` varchar(255) DEFAULT NULL,
  `_likes` int(11) DEFAULT NULL,
  `_russian_text` varchar(255) DEFAULT NULL,
  `_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `News`
--

LOCK TABLES `News` WRITE;
/*!40000 ALTER TABLE `News` DISABLE KEYS */;
/*!40000 ALTER TABLE `News` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NewsTypeCatalog`
--

DROP TABLE IF EXISTS `NewsTypeCatalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NewsTypeCatalog` (
  `_id` varchar(255) NOT NULL,
  `_localization_code` varchar(255) DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NewsTypeCatalog`
--

LOCK TABLES `NewsTypeCatalog` WRITE;
/*!40000 ALTER TABLE `NewsTypeCatalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `NewsTypeCatalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RepeatCatalog`
--

DROP TABLE IF EXISTS `RepeatCatalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RepeatCatalog` (
  `_id` varchar(255) NOT NULL,
  `_localization_code` varchar(255) DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RepeatCatalog`
--

LOCK TABLES `RepeatCatalog` WRITE;
/*!40000 ALTER TABLE `RepeatCatalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `RepeatCatalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Task`
--

DROP TABLE IF EXISTS `Task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Task` (
  `_id` varchar(255) NOT NULL,
  `_active` int(11) DEFAULT NULL,
  `_change_date` datetime DEFAULT NULL,
  `_guid` varchar(255) DEFAULT NULL,
  `_next` varchar(255) DEFAULT NULL,
  `_previous` varchar(255) DEFAULT NULL,
  `_completed` int(11) DEFAULT NULL,
  `_date_is_set` int(11) DEFAULT NULL,
  `_deleted` int(11) DEFAULT NULL,
  `_description` varchar(255) DEFAULT NULL,
  `_execution_date` datetime DEFAULT NULL,
  `_have_subtasks` int(11) DEFAULT NULL,
  `_parent_task` varchar(255) DEFAULT NULL,
  `_repeat` varchar(255) DEFAULT NULL,
  `_text` varchar(255) DEFAULT NULL,
  `_time_is_set` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Task`
--

LOCK TABLES `Task` WRITE;
/*!40000 ALTER TABLE `Task` DISABLE KEYS */;
/*!40000 ALTER TABLE `Task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `_id` varchar(255) NOT NULL,
  `_email` varchar(255) DEFAULT NULL,
  `_is_account_non_locked` int(11) DEFAULT NULL,
  `_last_activity_date` datetime DEFAULT NULL,
  `_locality` varchar(255) DEFAULT NULL,
  `_login` varchar(255) DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_password` varchar(255) DEFAULT NULL,
  `_registration_date` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserAuthority`
--

DROP TABLE IF EXISTS `UserAuthority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserAuthority` (
  `_id` varchar(255) NOT NULL,
  `_user_id` varchar(255) DEFAULT NULL,
  `_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `FK_ps9wfqm5jxw9xr5lfkrhea1qi` (`_user_id`),
  CONSTRAINT `FK_ps9wfqm5jxw9xr5lfkrhea1qi` FOREIGN KEY (`_user_id`) REFERENCES `User` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserAuthority`
--

LOCK TABLES `UserAuthority` WRITE;
/*!40000 ALTER TABLE `UserAuthority` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserAuthority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserAuthorityCatalog`
--

DROP TABLE IF EXISTS `UserAuthorityCatalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserAuthorityCatalog` (
  `_id` varchar(255) NOT NULL,
  `_localization_code` varchar(255) DEFAULT NULL,
  `_name` varchar(255) DEFAULT NULL,
  `_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserAuthorityCatalog`
--

LOCK TABLES `UserAuthorityCatalog` WRITE;
/*!40000 ALTER TABLE `UserAuthorityCatalog` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserAuthorityCatalog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
