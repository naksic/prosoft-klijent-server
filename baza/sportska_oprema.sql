/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.28-MariaDB : Database - 0_sportska_oprema
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`0_sportska_oprema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `0_sportska_oprema`;

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `idKupca` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `brojLoyaltyKartice` varchar(20) DEFAULT NULL,
  `kontakt` varchar(30) NOT NULL,
  `mesto` bigint(20) NOT NULL,
  `datumRodjenja` date NOT NULL,
  PRIMARY KEY (`idKupca`),
  UNIQUE KEY `uq_kupac_brojLoyaltyKartice` (`brojLoyaltyKartice`),
  KEY `mesto` (`mesto`),
  CONSTRAINT `kupac_ibfk_1` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kupac` */

insert  into `kupac`(`idKupca`,`ime`,`prezime`,`brojLoyaltyKartice`,`kontakt`,`mesto`,`datumRodjenja`) values 
(1,'Milica','Mikic','124125315','062/215125',2,'2002-08-25'),
(2,'Marko','Marinković','654754744','065/9157124',1,'1983-08-25'),
(3,'Ana','Aleksovic','1152311','064/9135113',3,'1994-08-25'),
(7,'Dragutin','Dimitrijevic','12515','069/8512819',1,'1996-08-25'),
(9,'Vojislav','Tankosic','1111','066/124125',3,'1976-08-25'),
(12,'Borisav','Lakic','190571512','066/1251855',2,'1980-03-25'),
(13,'Dragoljub','Mihajlovic','12412512','065/9158152',1,'1955-08-07'),
(14,'Misko','Lakic','512412','066/971522',5,'2004-05-25'),
(15,'Sava','Savovic','55446677','064/9889838',7,'2003-01-28'),
(17,'Mihajlo','Pupin','1351351','065/151222',4,'1954-10-09');

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `idMesto` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `postanskiBroj` bigint(20) NOT NULL CHECK (`postanskiBroj` > 0),
  PRIMARY KEY (`idMesto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`idMesto`,`naziv`,`postanskiBroj`) values 
(1,'Beograd',11000),
(2,'Smederevo',11300),
(3,'Niš',18000),
(4,'Novi Sad',21000),
(5,'Čačak',32000),
(6,'Kragujevac',34000),
(7,'Požarevac',12000),
(8,'Užice',31000),
(9,'Zaječar',19000),
(10,'Kraljevo',36000);

/*Table structure for table `oprema` */

DROP TABLE IF EXISTS `oprema`;

CREATE TABLE `oprema` (
  `idOpreme` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `napomena` varchar(300) DEFAULT NULL,
  `trenutnaCena` double NOT NULL CHECK (`trenutnaCena` > 0),
  PRIMARY KEY (`idOpreme`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `oprema` */

insert  into `oprema`(`idOpreme`,`naziv`,`napomena`,`trenutnaCena`) values 
(1,'Nike Tech Fleece','',15499),
(2,'Columbia Omni-Heat','Ne prati u masini.',12399),
(3,'Adidas Adizero Boston','Ciscenje suvom krpom.',24999),
(4,'NewBalance 9060',NULL,22999),
(5,'Colmar Hoodie',NULL,32699),
(6,'Nike Air Max 90',NULL,17999),
(7,'Adidas Ultraboost Light',NULL,21499),
(8,'Puma Suede Classic',NULL,9499),
(9,'Reebok Nano X3',NULL,15999),
(10,'Under Armour Project Rock',NULL,14299),
(11,'Asics Gel-Kayano 30',NULL,22999),
(12,'Jordan Stay Loyal 2',NULL,13999),
(13,'Converse Chuck Taylor All Star','Obuća za suvo vreme.',8999),
(14,'New Balance 574',NULL,11999),
(15,'Vans Old Skool',NULL,8499);

/*Table structure for table `prodavac` */

DROP TABLE IF EXISTS `prodavac`;

CREATE TABLE `prodavac` (
  `idProdavac` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL CHECK (`email` like '%@%'),
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `datumRodjenja` date NOT NULL,
  `telefon` varchar(15) NOT NULL,
  `korisnickoIme` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL CHECK (`sifra` <> `korisnickoIme`),
  PRIMARY KEY (`idProdavac`),
  UNIQUE KEY `uk_prodavac_korisnickoime` (`korisnickoIme`),
  CONSTRAINT `chk_sifra_razlicita_od_korisnickog_imena` CHECK (`sifra` <> `korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `prodavac` */

insert  into `prodavac`(`idProdavac`,`email`,`ime`,`prezime`,`datumRodjenja`,`telefon`,`korisnickoIme`,`sifra`) values 
(1,'petar@gmail.com','Petar','Peric','2000-03-02','062555333','petar.peric@sv.com','pp0203'),
(2,'milan@gmail.com','Milan','Milanovic','1995-07-15','069113554','milan.milanovic@sv.com','mm1507'),
(3,'nikola@gmail.com','Nikola','Nikolic','2002-05-30','065444666','nikola.nikolic@sv.com','nn3005'),
(4,'mila@gmail.com','Mila','Milic','1998-11-05','063654345','mila.milic@sv.com','mm0511'),
(5,'nina@gmail.com','Nina','Ninovic','2000-12-20','066766899','nina.ninovic@sv.com','nn2012');

/*Table structure for table `prss` */

DROP TABLE IF EXISTS `prss`;

CREATE TABLE `prss` (
  `prodavac` bigint(20) NOT NULL,
  `strucnaSprema` bigint(20) NOT NULL,
  `datumSticanja` date NOT NULL,
  `brojDiplome` varchar(15) NOT NULL,
  PRIMARY KEY (`prodavac`,`strucnaSprema`),
  KEY `strucnaSprema` (`strucnaSprema`),
  CONSTRAINT `prss_ibfk_1` FOREIGN KEY (`prodavac`) REFERENCES `prodavac` (`idProdavac`),
  CONSTRAINT `prss_ibfk_2` FOREIGN KEY (`strucnaSprema`) REFERENCES `strsprema` (`idStrucnaSprema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `prss` */

insert  into `prss`(`prodavac`,`strucnaSprema`,`datumSticanja`,`brojDiplome`) values 
(2,2,'2020-02-07','225883'),
(3,3,'2023-05-09','1121'),
(5,4,'2022-11-18','77455');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `idRacun` bigint(20) NOT NULL AUTO_INCREMENT,
  `datumIzdavanja` date NOT NULL,
  `nacinPlacanja` varchar(10) NOT NULL,
  `napomena` varchar(300) DEFAULT NULL,
  `popust` double NOT NULL CHECK (`popust` >= 0 and `popust` <= 0.3),
  `ukupanIznos` double NOT NULL CHECK (`ukupanIznos` > 0),
  `prodavac` bigint(20) NOT NULL,
  `kupac` bigint(20) NOT NULL,
  PRIMARY KEY (`idRacun`),
  KEY `prodavac` (`prodavac`),
  KEY `kupac` (`kupac`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`prodavac`) REFERENCES `prodavac` (`idProdavac`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`kupac`) REFERENCES `kupac` (`idKupca`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racun` */

insert  into `racun`(`idRacun`,`datumIzdavanja`,`nacinPlacanja`,`napomena`,`popust`,`ukupanIznos`,`prodavac`,`kupac`) values 
(1,'2026-01-15','KES',NULL,0,15300,2,2),
(2,'2026-03-20','KARTICA','Mora biti lepo zapakovano.',0.2,48396.8,3,1),
(3,'2026-08-23','KES','',0,43796,2,2),
(4,'2025-05-25','KES','',0.2,22878.4,3,1),
(5,'2026-04-21','KARTICA','',0.05,70012.15,4,2),
(6,'2021-03-27','KARTICA','',0.05,50061.2,4,7),
(7,'2014-04-21','KES','',0,49998,4,7),
(8,'2020-04-05','KES','',0,113396,4,9),
(9,'2024-09-08','KARTICA','Mora biti lepo zapakovano.',0.1,22499.1,2,13),
(10,'2018-10-10','KARTICA','',0.05,23749.05,1,7),
(11,'2025-05-03','KARTICA','Neka napomena.',0.05,51581.2,2,3),
(12,'2026-08-24','KARTICA','',0.2,25198.4,1,14),
(13,'2026-08-27','KES','',0,40997,4,12),
(14,'2026-08-29','KARTICA','',0.1,29248.2,3,17),
(15,'2026-09-02','KARTICA','',0.05,73146.2,4,12),
(16,'2026-09-03','KES','',0.2,47997.6,5,14);

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `racun` bigint(20) NOT NULL,
  `rb` bigint(20) NOT NULL AUTO_INCREMENT,
  `kolicina` bigint(20) NOT NULL CHECK (`kolicina` > 0),
  `cena` double NOT NULL CHECK (`cena` > 0),
  `iznos` double NOT NULL CHECK (`iznos` > 0),
  `oprema` bigint(20) NOT NULL,
  PRIMARY KEY (`racun`,`rb`),
  KEY `oprema` (`oprema`),
  KEY `rb` (`rb`),
  CONSTRAINT `stavkaracuna_ibfk_1` FOREIGN KEY (`racun`) REFERENCES `racun` (`idRacun`),
  CONSTRAINT `stavkaracuna_ibfk_2` FOREIGN KEY (`oprema`) REFERENCES `oprema` (`idOpreme`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`racun`,`rb`,`kolicina`,`cena`,`iznos`,`oprema`) values 
(1,1,1,10000,10000,2),
(1,2,3,4000,12000,5),
(2,1,3,15499,46497,1),
(2,2,1,13999,13999,12),
(3,1,2,12399,24798,2),
(3,2,2,9499,18998,8),
(4,3,2,14299,28598,10),
(5,1,1,22999,22999,4),
(5,2,1,32699,32699,5),
(5,3,1,17999,17999,6),
(6,1,1,15499,15499,1),
(6,2,3,12399,37197,2),
(7,1,2,24999,49998,3),
(8,1,1,24999,24999,3),
(8,2,2,32699,65398,5),
(8,3,1,22999,22999,4),
(9,1,1,24999,24999,3),
(10,1,1,24999,24999,3),
(11,2,1,15499,15499,1),
(11,3,2,12399,24798,2),
(11,4,1,13999,13999,12),
(12,1,1,8499,8499,15),
(12,2,1,22999,22999,11),
(13,1,1,8999,8999,13),
(13,2,2,15999,31998,9),
(14,1,1,9499,9499,8),
(14,2,1,22999,22999,11),
(15,1,2,15499,30998,1),
(15,2,2,22999,45998,4),
(16,1,1,22999,22999,11),
(16,2,1,21499,21499,7),
(16,3,1,15499,15499,1);

/*Table structure for table `strsprema` */

DROP TABLE IF EXISTS `strsprema`;

CREATE TABLE `strsprema` (
  `idStrucnaSprema` bigint(20) NOT NULL AUTO_INCREMENT,
  `zvanje` varchar(50) NOT NULL,
  `stepen` bigint(20) NOT NULL CHECK (`stepen` > 0),
  PRIMARY KEY (`idStrucnaSprema`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `strsprema` */

insert  into `strsprema`(`idStrucnaSprema`,`zvanje`,`stepen`) values 
(1,'SSS',3),
(2,'SSS',4),
(3,'diplomirani ekonomista',7),
(4,'VKV',5),
(5,'magistar farmacije',7),
(6,'doktor nauka',8),
(8,'master',7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
