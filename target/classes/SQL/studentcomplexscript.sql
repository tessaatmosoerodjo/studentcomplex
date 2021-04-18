-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.11-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

-- Dumping database structure for studentencomplex
CREATE DATABASE IF NOT EXISTS `studentencomplex`;
USE `studentencomplex`;

-- Dumping structure for table studentencomplex.students
CREATE TABLE IF NOT EXISTS `students` (
    `student_id` int(11) NOT NULL AUTO_INCREMENT,
    `voornaam` varchar(50) NOT NULL,
    `achternaam` varchar(50) NOT NULL,
    `adres` varchar(50) NOT NULL,
    `telefoon` int(11) NOT NULL,
    `id_nummer` varchar(25) NOT NULL,
    `identificatie_type_id` int(11) NOT NULL,
    `status_id` int(11) NOT NULL,
    PRIMARY KEY (`student_id`),
    KEY `FK_student_identificatie_types` (`identificatie_type_id`),
    KEY `FK_student_status` (`status_id`),
    CONSTRAINT `FK_student_identificatie_types` FOREIGN KEY (`identificatie_type_id`) REFERENCES `identificatie_types` (`identificatie_type_id`),
    CONSTRAINT `FK_student_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


-- INSERT data for table studentencomplex.students
INSERT INTO `students` (`student_id`, `voornaam`, `achternaam`, `adres`, `telefoon`, `id_nummer`, `identificatie_type_id`, `status_id`) VALUES
(1, 'Tessa', 'Atmo', 'Tamanredjo', 7229239, 'FQ004422', 2, 1),
(2, 'Ruth', 'Boeroe', 'Tamansarie', 8945852, '526SE2015', 1, 2),
(3, 'Sandu', 'Coki', 'Meerzorg', 9651255, '965BI2016', 1, 1),
(4, 'Den', 'Doel', 'Pepperpot', 9685234, 'FP156548', 2, 2),
(5, 'Waki', 'Erfdeel', 'De hulp', 8885525, '993NE2021', 1, 2);


-- Dumping structure for table studentencomplex.identificatie_types
CREATE TABLE IF NOT EXISTS `identificatie_types` (
    `identificatie_type_id` int(11) NOT NULL AUTO_INCREMENT,
    `identificatie_type` varchar(50) NOT NULL,
    PRIMARY KEY (`identificatie_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- INSERT data for table studentencomplex.identificatie_types
INSERT INTO `identificatie_types` (`identificatie_type_id`, `identificatie_type`) VALUES
(1, 'Studentenkaart'),
(2, 'ID-Kaart');


-- Dumping structure for table studentencomplex.kamers
CREATE TABLE IF NOT EXISTS `kamers` (
    `kamer_id` int(11) NOT NULL AUTO_INCREMENT,
    `kamer` varchar(50) NOT NULL,
    `type_id` int(11) NOT NULL,
    `status_id` int(11) NOT NULL,
    PRIMARY KEY (`kamer_id`),
    KEY `FK_kamers_status` (`status_id`),
    KEY `FK_kamers_types` (`type_id`),
    CONSTRAINT `FK_kamers_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
    CONSTRAINT `FK_kamers_types` FOREIGN KEY (`type_id`) REFERENCES `types` (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


-- INSERT data for table studentencomplex.kamers
INSERT INTO `kamers` (`kamer_id`, `kamer`, `type_id`, `status_id`) VALUES
(21, 'SINGLEROOM01', 1, 2),
(22, 'SINGLEROOM02', 1, 2),
(23, 'SINGLEROOM03', 1, 2),
(24, 'DOUBLEROOM01', 2, 2),
(25, 'DOUBLEROOM02', 2, 2),
(26, 'DOUBLEROOM03', 2, 2);


-- Dumping structure for table studentencomplex.reserveringen
CREATE TABLE IF NOT EXISTS `reserveringen` (
     `reservering_id` int(11) NOT NULL AUTO_INCREMENT,
     `checkin_date` date NOT NULL,
     `checkout_date` date NOT NULL,
     `kamer_id` int(11) NOT NULL,
     `student_id` int(11) NOT NULL,
     PRIMARY KEY (`reservering_id`),
     KEY `FK_reserveringen_kamers` (`kamer_id`),
     KEY `FK_reserveringen_student` (`student_id`),
     CONSTRAINT `FK_reserveringen_student` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
     CONSTRAINT `FK_reserveringen_kamers` FOREIGN KEY (`kamer_id`) REFERENCES `kamers` (`kamer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


-- INSERT data for table studentencomplex.reserveringen
INSERT INTO `reserveringen` (`reservering_id`, `checkin_date`, `checkout_date`, `kamer_id`, `student_id`) VALUES
(8, '2019-06-25', '2020-06-25', 21, 4),
(9, '2019-11-01', '2020-11-01', 23, 2),
(10, '2020-02-14', '2021-02-14', 24, 5),
(11, '2020-05-31', '2021-05-31', 26, 1);

-- Dumping structure for table studentencomplex.status
CREATE TABLE IF NOT EXISTS `status` (
    `status_id` int(11) NOT NULL AUTO_INCREMENT,
    `status` varchar(50) NOT NULL,
    PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- INSERT data for table studentencomplex.status
INSERT INTO `status` (`status_id`, `status`) VALUES
(1, 'Checked-in'),
(2, 'Checked-out');


-- Dumping structure for table studentencomplex.types
CREATE TABLE IF NOT EXISTS `types` (
      `type_id` int(11) NOT NULL AUTO_INCREMENT,
      `type` varchar(50) NOT NULL,
      PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- INSERT data for table studentencomplex.types
INSERT INTO `types` (`type_id`, `type`) VALUES
(1, 'Single bed'),
(2, 'Double bed');


