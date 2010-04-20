/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.0.41-community-nt : Database - lottery
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`lottery` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `ssq_lottery_collect_fetch` */

DROP TABLE IF EXISTS `ssq_lottery_collect_fetch`;

CREATE TABLE `ssq_lottery_collect_fetch` (
  `id` char(25) NOT NULL default '',
  `net` char(1) NOT NULL default '0' COMMENT '0：500万；1：大赢家',
  `code` mediumtext,
  `expect` char(5) default NULL,
  `isfail` int(1) default '0' COMMENT '0：成功；1：未失败（未上传或不开放）；',
  PRIMARY KEY  (`id`,`net`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_collect_result` */

DROP TABLE IF EXISTS `ssq_lottery_collect_result`;

CREATE TABLE `ssq_lottery_collect_result` (
  `first` char(2) NOT NULL default '',
  `second` char(2) NOT NULL,
  `third` char(2) NOT NULL,
  `fourth` char(2) NOT NULL,
  `firth` char(2) NOT NULL,
  `sixth` char(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_dan_result` */

DROP TABLE IF EXISTS `ssq_lottery_dan_result`;

CREATE TABLE `ssq_lottery_dan_result` (
  `dan` char(20) NOT NULL,
  `type` char(1) NOT NULL COMMENT '0:sina;1用户选择',
  UNIQUE KEY `dan` (`dan`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_filter_result` */

DROP TABLE IF EXISTS `ssq_lottery_filter_result`;

CREATE TABLE `ssq_lottery_filter_result` (
  `value` char(20) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `value` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=1467539 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `ssq_lottery_tj_code` */

DROP TABLE IF EXISTS `ssq_lottery_tj_code`;

CREATE TABLE `ssq_lottery_tj_code` (
  `redcode` char(25) NOT NULL,
  `bluecode` char(25) default NULL,
  PRIMARY KEY  (`redcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_tmp` */

DROP TABLE IF EXISTS `ssq_lottery_tmp`;

CREATE TABLE `ssq_lottery_tmp` (
  `value` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
