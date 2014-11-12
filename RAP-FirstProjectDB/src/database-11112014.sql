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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configure`
--

LOCK TABLES `configure` WRITE;
/*!40000 ALTER TABLE `configure` DISABLE KEYS */;
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
  CONSTRAINT `fk_device_1` FOREIGN KEY (`version1`) REFERENCES `versions` (`version`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_device_2` FOREIGN KEY (`deviceType1`) REFERENCES `device_type` (`iddevice_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device1`
--

LOCK TABLES `device1` WRITE;
/*!40000 ALTER TABLE `device1` DISABLE KEYS */;
INSERT INTO `device1` VALUES (144,126,'template1',1,'physical1','axis','PTZModdel','2014-10-20 00:00:00','',1,'note abc'),(145,126,'template2',1,'physical2','clare','NonPTZmodel','2014-10-20 00:00:00','',3,'note 123'),(146,127,'template3',3,'physicalabc','axis','PTZModel','2014-10-20 00:00:00',NULL,1,'note adsad'),(147,127,'template4',2,'physicasdasdasd','axis','NonPTZmodel','2014-10-20 00:00:00',NULL,3,'note adasdasd');
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
) ENGINE=InnoDB AUTO_INCREMENT=497 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devices`
--

LOCK TABLES `devices` WRITE;
/*!40000 ALTER TABLE `devices` DISABLE KEYS */;
INSERT INTO `devices` VALUES (389,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(390,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(391,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(392,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(393,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(394,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(395,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(396,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(397,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(398,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(399,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(400,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,'',NULL,NULL),(401,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(402,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(403,107,'Fireplace -On/Off Switch (device)','Fireplace','Fireplace Controller','ghi','Generic',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(404,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(405,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(406,107,'Clare Controls-PTZ Models (IP) (device)','CCTV','IP Camera','abc','Clare Controls',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` VALUES (34,'new projcet',1),(35,'new 2',1),(48,'new 2',11);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'Touch Solu Tion LKNInc'),(2,'3105 GMD'),(3,'Acoustic Interiors'),(4,'Adapt AV Solution'),(5,'Advanced Light & Sound'),(6,'AE Custom'),(7,'Aflintly AV'),(8,'Touch Solu Tion LKNInc'),(9,'Touch Solu Tion LKNInc'),(10,'Touch Solu Tion LKNInc'),(11,'Touch Solu Tion LKNInc');
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
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versions`
--

LOCK TABLES `versions` WRITE;
/*!40000 ALTER TABLE `versions` DISABLE KEYS */;
INSERT INTO `versions` VALUES (34,93,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.1'),(34,106,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.2'),(34,107,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','1.0.3'),(48,126,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','2.0.1'),(48,127,'2014-11-10 15:20:30','abc','2014-11-10 15:20:30','2.x','2.0.2');
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

-- Dump completed on 2014-11-11  9:02:17
