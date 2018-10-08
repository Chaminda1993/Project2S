-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: booklab
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cid` varchar(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telNo` int(11) DEFAULT NULL,
  `balance` decimal(20,2) DEFAULT NULL,
  `nic` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('c1','suresh','kopay',772212212,50.00,'951124512V'),('c2','sekar','kondavil',715348821,25.00,'784512365V'),('c3','senthil','urumpurai',762456895,0.00,'881254518V');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grn`
--

DROP TABLE IF EXISTS `grn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grn` (
  `grnId` varchar(45) NOT NULL,
  `grnNo` varchar(10) DEFAULT NULL,
  `pid` varchar(10) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`grnId`),
  KEY `grnno_idx` (`grnNo`),
  CONSTRAINT `grnno` FOREIGN KEY (`grnNo`) REFERENCES `grnlog` (`grnNo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grn`
--

LOCK TABLES `grn` WRITE;
/*!40000 ALTER TABLE `grn` DISABLE KEYS */;
INSERT INTO `grn` VALUES ('gr1','g1','p1',20,NULL);
/*!40000 ALTER TABLE `grn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grnlog`
--

DROP TABLE IF EXISTS `grnlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grnlog` (
  `grnNo` varchar(10) NOT NULL,
  `dateTime` datetime DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`grnNo`),
  KEY `userid_idx` (`user`),
  KEY `supplierid_idx` (`supplier`),
  CONSTRAINT `supplierid` FOREIGN KEY (`supplier`) REFERENCES `supplier` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userid` FOREIGN KEY (`user`) REFERENCES `users` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grnlog`
--

LOCK TABLES `grnlog` WRITE;
/*!40000 ALTER TABLE `grnlog` DISABLE KEYS */;
INSERT INTO `grnlog` VALUES ('g1','2017-11-29 00:00:00','u2','s2',3000.00),('g2','2017-11-26 00:00:00','u2','s2',25000.00),('g3','2017-11-28 00:00:00','u3','s1',4000.00),('g4','2017-11-27 10:10:00','u3','s1',10000.00);
/*!40000 ALTER TABLE `grnlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoiceid` varchar(45) NOT NULL,
  `invoiceNo` varchar(10) DEFAULT NULL,
  `pId` varchar(10) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`invoiceid`),
  KEY `proid_idx` (`pId`),
  KEY `inno_idx` (`invoiceNo`),
  CONSTRAINT `inno` FOREIGN KEY (`invoiceNo`) REFERENCES `invoicelog` (`invoiceNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `proid` FOREIGN KEY (`pId`) REFERENCES `product` (`pid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicelog`
--

DROP TABLE IF EXISTS `invoicelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoicelog` (
  `invoiceNo` varchar(10) NOT NULL,
  `dateTime` datetime DEFAULT NULL,
  `user` varchar(45) DEFAULT NULL,
  `customer` varchar(10) DEFAULT NULL,
  `totalAmount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`invoiceNo`),
  KEY `userin_idx` (`user`),
  KEY `custin_idx` (`customer`),
  KEY `custinid_idx` (`customer`),
  CONSTRAINT `cid` FOREIGN KEY (`customer`) REFERENCES `customer` (`cid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userin` FOREIGN KEY (`user`) REFERENCES `users` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoicelog`
--

LOCK TABLES `invoicelog` WRITE;
/*!40000 ALTER TABLE `invoicelog` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoicelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loginlog`
--

DROP TABLE IF EXISTS `loginlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loginlog` (
  `logid` varchar(45) NOT NULL,
  `uid` varchar(10) DEFAULT NULL,
  `loginTime` datetime DEFAULT NULL,
  `logoutTime` varchar(45) DEFAULT NULL,
  `activityList` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`logid`),
  KEY `userid_idx` (`uid`),
  CONSTRAINT `uid` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loginlog`
--

LOCK TABLES `loginlog` WRITE;
/*!40000 ALTER TABLE `loginlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `loginlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `pid` varchar(10) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `supplier` varchar(45) DEFAULT NULL,
  `price` decimal(20,2) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `stockMinLevel` int(11) DEFAULT NULL,
  `stockMaxLevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('p1','story book','A','book1','s1',120.00,50,50,250),('p2','exercise book','B','book2','s2',100.00,120,50,250),('p3','story book','C','book1','s2',125.00,30,50,250),('p5','ink pen','E','pen2','s4',40.00,70,100,300);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `returnproduct`
--

DROP TABLE IF EXISTS `returnproduct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `returnproduct` (
  `pid` varchar(10) NOT NULL,
  `returnDate` date DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `returnFrom` varchar(45) DEFAULT NULL,
  `invoiceNo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sup_idx` (`returnFrom`),
  KEY `inid_idx` (`invoiceNo`),
  KEY `inno_idx` (`invoiceNo`),
  CONSTRAINT `sup` FOREIGN KEY (`returnFrom`) REFERENCES `supplier` (`sid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `returnproduct`
--

LOCK TABLES `returnproduct` WRITE;
/*!40000 ALTER TABLE `returnproduct` DISABLE KEYS */;
INSERT INTO `returnproduct` VALUES ('p2','2017-04-21','damage','s2','i5'),('p4','2017-05-10','damage','s3','i3');
/*!40000 ALTER TABLE `returnproduct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `supplier` (
  `sid` varchar(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telNo` varchar(10) DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('s1','ravi','jaffna','771111111',1000,'ravi@gmail.com'),('s12','cfgg','','',0,''),('s2','ragul','kandy','712222222',250,'ragul@hotmail.com'),('s3','mahi','anuradhapura','212225544',0,'mahi@gmail.com'),('s4','sara','colombo','212216688',300,'sara@gmail.com'),('s5','dola','colombo','113333333',4000,'dola@gmail.com');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `uid` varchar(10) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telNo` int(11) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `nic` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('u1','vijay','nallur,jaffna',719999999,'*556BEF296211C2AF58F53DA3EDDD0A3371B6ECD5','admin','923456789V','c771904768@gmail.com'),('u2','kumar','kaithadai,jaffna',732342134,'*A9FD3BA43189B0F4E7E2E759AACCE67511CC034E','admin','892342312v','c771904768@gmail.com'),('u3','kannan','manipai,jaffna',761235215,'*40A7C3ED7EA876E5ED51B9716F44A7DDE64AD66A','reception','901184862V','c771904768@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-10 13:21:00
