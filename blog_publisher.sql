/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.0.51a-community-nt : Database - blog_publisher
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog_publisher` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `blog_publisher`;

/*Table structure for table `blog_contentlist` */

DROP TABLE IF EXISTS `blog_contentlist`;

CREATE TABLE `blog_contentlist` (
  `id` int(10) NOT NULL auto_increment,
  `username` char(50) NOT NULL,
  `password` char(50) NOT NULL,
  `type` int(1) default NULL,
  `title` char(200) NOT NULL,
  `blog_body` text NOT NULL,
  `tags` char(200) default NULL,
  `toblog` char(50) NOT NULL,
  `ispublished` int(1) NOT NULL default '0',
  `publishDate` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

/*Data for the table `blog_contentlist` */

LOCK TABLES `blog_contentlist` WRITE;

UNLOCK TABLES;

/*Table structure for table `blog_typemaping` */

DROP TABLE IF EXISTS `blog_typemaping`;

CREATE TABLE `blog_typemaping` (
  `toblog` char(50) NOT NULL,
  `typename` char(100) NOT NULL,
  `typeid` int(2) NOT NULL,
  PRIMARY KEY  (`toblog`,`typeid`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

/*Data for the table `blog_typemaping` */

LOCK TABLES `blog_typemaping` WRITE;

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
