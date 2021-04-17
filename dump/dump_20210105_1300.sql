CREATE DATABASE  IF NOT EXISTS `qrestaurant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `qrestaurant`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: localhost    Database: qrestaurant
-- ------------------------------------------------------
-- Server version	5.7.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Attendance`
--

DROP TABLE IF EXISTS `Attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Attendance` (
  `idAttendance` int(11) NOT NULL AUTO_INCREMENT,
  `arrivalTime` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  `idSocietyFK` int(11) NOT NULL,
  `idClientFK` int(11) NOT NULL,
  PRIMARY KEY (`idAttendance`),
  KEY `idSocietyFKAttendance_idx` (`idSocietyFK`),
  KEY `idClientFKAttendance_idx` (`idClientFK`),
  CONSTRAINT `idClientFKAttendance` FOREIGN KEY (`idClientFK`) REFERENCES `Client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSocietyFKAttendance` FOREIGN KEY (`idSocietyFK`) REFERENCES `Society` (`idSociety`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attendance`
--

LOCK TABLES `Attendance` WRITE;
/*!40000 ALTER TABLE `Attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `Attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `infected` tinyint(4) NOT NULL DEFAULT '0',
  `infectedAt` timestamp(6) NULL DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceClient`
--

DROP TABLE IF EXISTS `DeviceClient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DeviceClient` (
  `idDeviceClient` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(256) NOT NULL,
  `registrationToken` varchar(256) NOT NULL,
  `QRCode` varchar(256) NOT NULL,
  `firebaseRegistrationToken` varchar(256) DEFAULT NULL,
  `createAt` timestamp(6) NULL DEFAULT NULL,
  `idClientFK` int(11) NOT NULL,
  PRIMARY KEY (`idDeviceClient`),
  UNIQUE KEY `UUID_UNIQUE` (`uuid`),
  UNIQUE KEY `registrationToken_UNIQUE` (`registrationToken`),
  UNIQUE KEY `QRCode_UNIQUE` (`QRCode`),
  UNIQUE KEY `firebaseRegistrationToken_UNIQUE` (`firebaseRegistrationToken`),
  UNIQUE KEY `createAt_UNIQUE` (`createAt`),
  KEY `idClientFKDeviceClient_idx` (`idClientFK`),
  CONSTRAINT `idClientFKDeviceClient` FOREIGN KEY (`idClientFK`) REFERENCES `Client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceClient`
--

LOCK TABLES `DeviceClient` WRITE;
/*!40000 ALTER TABLE `DeviceClient` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceClient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceManager`
--

DROP TABLE IF EXISTS `DeviceManager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DeviceManager` (
  `idDeviceManager` int(11) NOT NULL AUTO_INCREMENT,
  `firebaseRegistrationToken` varchar(256) DEFAULT NULL,
  `createAt` datetime DEFAULT NULL,
  `refreshToken` varchar(256) NOT NULL,
  `uuid` varchar(256) NOT NULL,
  `operatingSystem` varchar(45) NOT NULL,
  `idManagerFK` int(11) NOT NULL,
  PRIMARY KEY (`idDeviceManager`),
  UNIQUE KEY `refreshToken_UNIQUE` (`refreshToken`),
  UNIQUE KEY `UUID_UNIQUE` (`uuid`),
  UNIQUE KEY `firebaseRegistrationToken_UNIQUE` (`firebaseRegistrationToken`),
  KEY `IdManagerFKDeviceManager_idx` (`idManagerFK`),
  CONSTRAINT `IdManagerFKDeviceManager` FOREIGN KEY (`idManagerFK`) REFERENCES `Manager` (`idManager`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceManager`
--

LOCK TABLES `DeviceManager` WRITE;
/*!40000 ALTER TABLE `DeviceManager` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceManager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manager`
--

DROP TABLE IF EXISTS `Manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Manager` (
  `idManager` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `password` varchar(512) NOT NULL,
  PRIMARY KEY (`idManager`),
  UNIQUE KEY `phone_UNIQUE` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manager`
--

LOCK TABLES `Manager` WRITE;
/*!40000 ALTER TABLE `Manager` DISABLE KEYS */;
/*!40000 ALTER TABLE `Manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Manager2Society`
--

DROP TABLE IF EXISTS `Manager2Society`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Manager2Society` (
  `idManager2Society` int(11) NOT NULL AUTO_INCREMENT,
  `idManagerFK` int(11) NOT NULL,
  `idSocietyFK` int(11) NOT NULL,
  PRIMARY KEY (`idManager2Society`),
  KEY `idManagerFKManager2Society_idx` (`idManagerFK`),
  KEY `idSocietyFKManager2Society_idx` (`idSocietyFK`),
  CONSTRAINT `idManagerFKManager2Society` FOREIGN KEY (`idManagerFK`) REFERENCES `Manager` (`idManager`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idSocietyFKManager2Society` FOREIGN KEY (`idSocietyFK`) REFERENCES `Society` (`idSociety`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Manager2Society`
--

LOCK TABLES `Manager2Society` WRITE;
/*!40000 ALTER TABLE `Manager2Society` DISABLE KEYS */;
/*!40000 ALTER TABLE `Manager2Society` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notification`
--

DROP TABLE IF EXISTS `Notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Notification` (
  `idNotification` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(256) NOT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idNotificationTypeFK` int(11) NOT NULL,
  `idManagerFK` int(11) NOT NULL,
  `idAttendanceFK` int(11) NOT NULL,
  PRIMARY KEY (`idNotification`),
  KEY `idManagerFKNotification_idx` (`idManagerFK`),
  KEY `idAttendanceFKNotification_idx` (`idAttendanceFK`),
  KEY `idNotificationTypeFKNotification_idx` (`idNotificationTypeFK`),
  CONSTRAINT `idAttendanceFKNotification` FOREIGN KEY (`idAttendanceFK`) REFERENCES `Attendance` (`idAttendance`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idManagerFKNotification` FOREIGN KEY (`idManagerFK`) REFERENCES `Manager` (`idManager`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idNotificationTypeFKNotification` FOREIGN KEY (`idNotificationTypeFK`) REFERENCES `NotificationType` (`idNotificationType`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notification`
--

LOCK TABLES `Notification` WRITE;
/*!40000 ALTER TABLE `Notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NotificationType`
--

DROP TABLE IF EXISTS `NotificationType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `NotificationType` (
  `idNotificationType` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`idNotificationType`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NotificationType`
--

LOCK TABLES `NotificationType` WRITE;
/*!40000 ALTER TABLE `NotificationType` DISABLE KEYS */;
INSERT INTO `NotificationType` VALUES (1,'CLIENT_INFECTED'),(2,'QRCODE_CHANGED');
/*!40000 ALTER TABLE `NotificationType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Society`
--

DROP TABLE IF EXISTS `Society`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Society` (
  `idSociety` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(45) NOT NULL,
  `city` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `vatnum` varchar(45) NOT NULL,
  `societyName` varchar(45) NOT NULL,
  PRIMARY KEY (`idSociety`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Society`
--

LOCK TABLES `Society` WRITE;
/*!40000 ALTER TABLE `Society` DISABLE KEYS */;
/*!40000 ALTER TABLE `Society` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-05 14:20:41
