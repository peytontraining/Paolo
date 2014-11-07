-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: equipment
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.12.04.1

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
-- Table structure for table `app_module`
--

DROP TABLE IF EXISTS `app_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_module` (
  `idmodule` int(11) NOT NULL AUTO_INCREMENT,
  `nameModule` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmodule`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_module`
--

LOCK TABLES `app_module` WRITE;
/*!40000 ALTER TABLE `app_module` DISABLE KEYS */;
INSERT INTO `app_module` VALUES (1,'CCTV'),(2,'FirePlace');
/*!40000 ALTER TABLE `app_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configure`
--

DROP TABLE IF EXISTS `configure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configure` (
  `idconfigure` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `value` varchar(45) DEFAULT NULL,
  `mandactory` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `device` int(11) DEFAULT NULL,
  PRIMARY KEY (`idconfigure`),
  KEY `fk_configure_1` (`device`),
  CONSTRAINT `fk_configure_1` FOREIGN KEY (`device`) REFERENCES `devices` (`idDevice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configure`
--

LOCK TABLES `configure` WRITE;
/*!40000 ALTER TABLE `configure` DISABLE KEYS */;
INSERT INTO `configure` VALUES (1,'driver','driverabc','yes','des abc',2),(8,'camera.type','camera abc','yes','des 2',2),(9,'user','anonymos','no','user 2',2),(10,'password','anonymos','no','pass2',2);
/*!40000 ALTER TABLE `configure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device1`
--

DROP TABLE IF EXISTS `device1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device1` (
  `idDevice1` int(11) NOT NULL AUTO_INCREMENT,
  `version1` int(11) DEFAULT NULL,
  `name1` varchar(100) DEFAULT NULL,
  `deviceType1` int(11) DEFAULT NULL,
  `physicalLocation1` varchar(100) DEFAULT NULL,
  `manufacturer1` varchar(100) DEFAULT NULL,
  `modelNumber1` varchar(45) DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  `needUpdate` varchar(45) DEFAULT NULL,
  `driver` int(11) DEFAULT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idDevice1`),
  KEY `fk_device_1` (`version1`),
  KEY `fk_device_2` (`deviceType1`),
  KEY `fk_device1_1` (`driver`),
  CONSTRAINT `fk_device1_1` FOREIGN KEY (`driver`) REFERENCES `driver` (`iddriver`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_device_1` FOREIGN KEY (`version1`) REFERENCES `versions` (`version`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_device_2` FOREIGN KEY (`deviceType1`) REFERENCES `device_type` (`iddevice_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device1`
--

LOCK TABLES `device1` WRITE;
/*!40000 ALTER TABLE `device1` DISABLE KEYS */;
INSERT INTO `device1` VALUES (122,11,'ClareControlabc',1,'physical11','manu11','model11','2014-10-20 00:00:00',NULL,1,'physycal 11, manu11, model 11, can not use to abc'),(123,12,'ClareVision',1,'physical12','manu12','model12','2014-10-21 00:00:00',NULL,1,NULL),(124,11,'DNR1',2,'physical11','manu11','model11','2014-10-20 00:00:00',NULL,1,NULL),(125,12,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'def'),(126,12,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'fire ok'),(127,12,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'add device'),(128,86,'DNR1',2,'physical11','manu11','model11','2014-10-20 00:00:00',NULL,1,NULL),(129,84,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'def'),(130,84,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'fire ok'),(131,84,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'add device'),(132,84,'ClareVision',1,'physical12','manu12','model12','2014-10-21 00:00:00',NULL,1,NULL),(133,86,'ClareControlabc',1,'physical11','manu11','model11','2014-10-20 00:00:00',NULL,1,'physycal 11, manu11, model 11, can not use to abc'),(134,88,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'add device'),(135,91,'ClareControlabc',1,'physical11','manu11','model11','2014-10-20 00:00:00',NULL,1,'physycal 11, manu11, model 11, can not use to abc'),(136,88,'ClareVision',1,'physical12','manu12','model12','2014-10-21 00:00:00',NULL,1,NULL),(137,88,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'fire ok'),(138,88,'FirePlace2',3,'physical12','manu12','model12','2014-10-22 00:00:00',NULL,3,'def'),(139,91,'DNR1',2,'physical11','manu11','model11','2014-10-20 00:00:00',NULL,1,NULL);
/*!40000 ALTER TABLE `device1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type`
--

DROP TABLE IF EXISTS `device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_type` (
  `iddevice_type` int(11) NOT NULL AUTO_INCREMENT,
  `nameDeviceType` varchar(45) DEFAULT NULL,
  `appModule` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddevice_type`),
  KEY `fk_device_type_1` (`appModule`),
  CONSTRAINT `fk_device_type_1` FOREIGN KEY (`appModule`) REFERENCES `app_module` (`idmodule`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_type`
--

LOCK TABLES `device_type` WRITE;
/*!40000 ALTER TABLE `device_type` DISABLE KEYS */;
INSERT INTO `device_type` VALUES (1,'IP Cameras',1),(2,'DVR/NVR',1),(3,'FirePlaceController',2);
/*!40000 ALTER TABLE `device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devices`
--

DROP TABLE IF EXISTS `devices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devices` (
  `idDevice` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `appModule` varchar(100) DEFAULT NULL,
  `deviceType` varchar(100) DEFAULT NULL,
  `physicalLocation` varchar(100) DEFAULT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `driver` int(11) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `masterTemplate` int(11) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  `modelNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDevice`),
  KEY `fk_version_1` (`version`),
  KEY `fk_devices_1` (`version`),
  KEY `fk_devices_2` (`driver`),
  KEY `fk_devices_3` (`masterTemplate`),
  CONSTRAINT `fk_devices_1` FOREIGN KEY (`version`) REFERENCES `versions` (`version`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_devices_2` FOREIGN KEY (`driver`) REFERENCES `driver` (`iddriver`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_devices_3` FOREIGN KEY (`masterTemplate`) REFERENCES `device1` (`idDevice1`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=389 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (2,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,12,'abcClare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,'',NULL,NULL),(4,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,12,'abcFireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,'',NULL,NULL),(6,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(143,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(144,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(145,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(146,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(147,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(148,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(149,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(150,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(151,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(152,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(153,12,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(154,12,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(245,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(246,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(247,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,'',NULL,NULL),(248,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(249,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(250,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(251,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(252,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(253,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(254,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(255,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(256,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(257,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(258,82,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(259,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(260,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(261,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(262,82,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(263,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(264,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(265,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(266,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(267,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(268,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(269,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(270,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(271,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(272,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(273,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(274,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(275,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(276,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(277,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(278,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(279,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(280,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(281,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(282,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(283,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(284,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,'',NULL,NULL),(285,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(286,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(287,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(288,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(289,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(290,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(291,84,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(292,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(293,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(294,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(295,87,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(296,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(297,87,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(298,84,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(299,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(300,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(301,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(302,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,'',NULL,NULL),(303,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(304,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(305,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(306,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(307,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(308,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(309,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(310,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(311,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(312,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(313,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(314,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(315,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(316,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(317,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(318,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(319,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(320,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(321,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(322,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(323,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(324,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(325,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(326,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(327,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(328,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(329,88,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(330,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(331,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(332,90,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(333,90,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(334,88,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(335,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(336,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(337,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(338,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(339,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(340,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(341,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(342,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(343,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(344,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(345,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(346,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(347,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(348,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(349,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(350,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(351,94,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,'',NULL,NULL),(352,94,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(353,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(354,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(355,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(356,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(357,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(358,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(359,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(360,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(361,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(362,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(363,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(364,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(365,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(366,95,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(367,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,'',NULL,NULL),(368,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(369,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(370,95,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `devices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `driver`
--

DROP TABLE IF EXISTS `driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `driver` (
  `iddriver` int(11) NOT NULL AUTO_INCREMENT,
  `nameDriver` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddriver`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `driver`
--

LOCK TABLES `driver` WRITE;
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` VALUES (1,'Camera'),(3,'Fire place');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `idEquipment` int(11) NOT NULL AUTO_INCREMENT,
  `nameEquipment` varchar(45) DEFAULT NULL,
  `typeEquipment` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEquipment`),
  KEY `fk_equipment_1` (`typeEquipment`),
  CONSTRAINT `fk_equipment_1` FOREIGN KEY (`typeEquipment`) REFERENCES `type` (`idtype`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (34,'new projcet',1),(35,'new 2',1),(40,'new 2',8),(41,'new projcet',8),(42,'new 2',9),(43,'new projcet',9),(44,'newa',1);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `type` (
  `idtype` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtype`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Touch Solu Tion LKNInc'),(2,'3105 GMD'),(3,'Acoustic Interiors'),(4,'Adapt AV Solution'),(5,'Advanced Light & Sound'),(6,'AE Custom'),(7,'Aflintly AV'),(8,'Touch Solu Tion LKNInc'),(9,'Touch Solu Tion LKNInc');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versions`
--

DROP TABLE IF EXISTS `versions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versions` (
  `idEquipment` int(11) NOT NULL,
  `version` int(11) NOT NULL AUTO_INCREMENT,
  `deployTime` datetime DEFAULT NULL,
  `deploySource` varchar(45) DEFAULT NULL,
  `saveTime` datetime DEFAULT NULL,
  `targetVersion` varchar(45) DEFAULT NULL,
  `versionName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`version`),
  KEY `fk_properties_1` (`idEquipment`),
  CONSTRAINT `fk_properties_1` FOREIGN KEY (`idEquipment`) REFERENCES `equipment` (`idEquipment`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versions`
--

LOCK TABLES `versions` WRITE;
/*!40000 ALTER TABLE `versions` DISABLE KEYS */;
INSERT INTO `versions` VALUES (34,11,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.2'),(34,12,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.3'),(34,82,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.1'),(41,84,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.3'),(41,85,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.4'),(41,86,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.2'),(41,87,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.1'),(43,88,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.3'),(43,89,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.4'),(43,90,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.1'),(43,91,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.2'),(44,92,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.*'),(34,93,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.5'),(34,94,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.6'),(34,95,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.7');
/*!40000 ALTER TABLE `versions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-07 16:01:29
