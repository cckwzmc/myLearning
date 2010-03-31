/*
SQLyog 企业版 - MySQL GUI v7.14 
MySQL - 5.0.51a-community-nt : Database - lottery
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`lottery` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lottery`;

/*Table structure for table `ft_lottery_all_result` */

DROP TABLE IF EXISTS `ft_lottery_all_result`;

CREATE TABLE `ft_lottery_all_result` (
  `value` char(30) default NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=3763987 DEFAULT CHARSET=utf8;

/*Table structure for table `ft_lottery_filter_result` */

DROP TABLE IF EXISTS `ft_lottery_filter_result`;

CREATE TABLE `ft_lottery_filter_result` (
  `value` char(30) default NULL,
  UNIQUE KEY `value` (`value`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `lottery_gen_log` */

DROP TABLE IF EXISTS `lottery_gen_log`;

CREATE TABLE `lottery_gen_log` (
  `type` char(1) NOT NULL COMMENT '1:双色球，2：足彩',
  `lottery_qh` char(10) NOT NULL COMMENT '期号',
  `is_gen` char(1) default '0' COMMENT '是否生成过滤结果号码',
  PRIMARY KEY  (`type`,`lottery_qh`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `lottery_role` */

DROP TABLE IF EXISTS `lottery_role`;

CREATE TABLE `lottery_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `screen_name` varchar(12) NOT NULL COMMENT '显示名称',
  `description` varchar(50) default NULL COMMENT '描述',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `lottery_user` */

DROP TABLE IF EXISTS `lottery_user`;

CREATE TABLE `lottery_user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `nickname` varchar(100) default NULL,
  `fullname` varchar(100) default NULL,
  `email` varchar(50) NOT NULL,
  `datecreated` int(11) default NULL,
  `isenabled` int(1) NOT NULL default '0' COMMENT '0不可用；1：可用',
  `activation_code` varchar(15) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `username_uqiue` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `lottery_user_role` */

DROP TABLE IF EXISTS `lottery_user_role`;

CREATE TABLE `lottery_user_role` (
  `id` int(11) NOT NULL,
  `user_id` int(11) default NULL,
  `role_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `user_role_uqiue` (`user_id`,`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_all_result` */

DROP TABLE IF EXISTS `ssq_lottery_all_result`;

CREATE TABLE `ssq_lottery_all_result` (
  `value` char(20) NOT NULL,
  `id` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `value` (`value`)
) ENGINE=MyISAM AUTO_INCREMENT=733770 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `ssq_lottery_collect_result` */

DROP TABLE IF EXISTS `ssq_lottery_collect_result`;

CREATE TABLE `ssq_lottery_collect_result` (
  `value` char(20) NOT NULL,
  UNIQUE KEY `value` (`value`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_filter_result` */

DROP TABLE IF EXISTS `ssq_lottery_filter_result`;

CREATE TABLE `ssq_lottery_filter_result` (
  `value` char(20) NOT NULL,
  UNIQUE KEY `value` (`value`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `ssq_lottery_his_media_stat` */

DROP TABLE IF EXISTS `ssq_lottery_his_media_stat`;

CREATE TABLE `ssq_lottery_his_media_stat` (
  `expect` char(6) NOT NULL COMMENT '期数',
  `type` char(1) NOT NULL default '0' COMMENT '0：红球；1：蓝球',
  `code` char(2) NOT NULL COMMENT '号码',
  `num` int(2) default '0' COMMENT '推荐次数',
  `fen` int(2) default '0' COMMENT '在推荐中百分比',
  `istrue` tinyint(1) default '0' COMMENT '是否猜中,0没中，1：猜中',
  PRIMARY KEY  (`expect`,`type`,`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='媒体历史推荐统计';

/*Table structure for table `ssq_lottery_his_open_code` */

DROP TABLE IF EXISTS `ssq_lottery_his_open_code`;

CREATE TABLE `ssq_lottery_his_open_code` (
  `expect` char(5) NOT NULL COMMENT '期号',
  `redcode` char(18) NOT NULL COMMENT '红球',
  `bluecode` char(2) default NULL COMMENT '蓝球',
  `redcodesum` int(3) default NULL COMMENT '红球和值',
  PRIMARY KEY  (`expect`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
