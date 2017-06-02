-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.17-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2017-06-02 07:36:26
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for agileengine
DROP DATABASE IF EXISTS `agileengine`;
CREATE DATABASE IF NOT EXISTS `agileengine` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `agileengine`;


-- Dumping structure for table agileengine.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `money_amount` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table agileengine.account: ~2 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `name`, `money_amount`) VALUES
	(1, 'Jack', 1000),
	(2, 'Bob', 2000),
	(3, 'Yuri', 1000000);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- Dumping structure for table agileengine.money_transaction
DROP TABLE IF EXISTS `money_transaction`;
CREATE TABLE IF NOT EXISTS `money_transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(10) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `FK_money_transaction_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `FKnveoar71hw7yc7imsl3vcm1h` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Dumping data for table agileengine.money_transaction: ~0 rows (approximately)
/*!40000 ALTER TABLE `money_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `money_transaction` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
