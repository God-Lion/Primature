DROP DATABASE IF EXISTS `Primature`;
CREATE DATABASE IF NOT EXISTS `Primature`;
USE `Primature`;

CREATE TABLE `Ministere` (
  `id`             INT                 PRIMARY KEY AUTO_INCREMENT,
  `code`           VARCHAR(6)   UNIQUE NULL,
  `nom`            VARCHAR(250) UNIQUE NULL,
  `sigle`          VARCHAR(50)  UNIQUE NULL,
  `adresse`        VARCHAR(255)        NULL,
  `telephone`      VARCHAR(15)         NULL,
  `ministreActuel` VARCHAR(250)        NULL,
  `noBRH`          VARCHAR(12)  UNIQUE NULL,
  `noBNC`          VARCHAR(12)  UNIQUE NULL,
  `soldeBRH`       FLOAT,
  `soldeBNC`       FLOAT
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `Fonds` (
  `id`              INT         PRIMARY KEY AUTO_INCREMENT,
  `Exercicefiscale` VARCHAR(10) NULL,
  `Ministere`       VARCHAR(20) NULL,
  `Montant`         FLOAT       NULL,
  `MontantBRH`      FLOAT       NULL,
  `MontantBNC`      FLOAT       NULL,
  `Signataires`     VARCHAR(100)NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
  
CREATE TABLE `PayrollEmployes` (
  `id`              INT                PRIMARY KEY AUTO_INCREMENT,
  `Exercicefiscale` VARCHAR(10)        NULL,
  `Ministere`       VARCHAR(20)        NULL,
  `NomEmploye`      VARCHAR(100)       NULL,
  `NumeroChequeBNC` VARCHAR(8)  UNIQUE NULL,
  `Montant`         FLOAT              NULL,
  `Mois`            VARCHAR(10)        NULL,
  `Code`            VARCHAR(10) UNIQUE NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `Projets` (
  `id`              INT                PRIMARY KEY AUTO_INCREMENT,
  `Code`            VARCHAR(10) UNIQUE NULL,
  `Exercicefiscale` VARCHAR(10)        NULL,
  `Ministere`       VARCHAR(20)        NULL,
  `noProjet`        INT                NULL,
  `TypeProjet`      VARCHAR(50)        NULL,
  `Description`     varchar(255)       NULL,
  `Firme`           VARCHAR(50)        NULL,
  `MaitreOuvrage`   VARCHAR(150)       NULL,
  `Cout`            FLOAT              NULL,
  `ZoneConcernee`   VARCHAR(250)       NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `LOGIN`(
  `ID_USER`     INT(4)              	PRIMARY KEY AUTO_INCREMENT,
  `USERNAME`    VARCHAR(100) UNIQUE 	NOT NULL,
  `PASSWORD`    VARCHAR(100)        	NOT NULL,
  `RIGHTS`      ENUM('simple', 'admin') DEFAULT 'simple',
  `RIGHTSAVE`   BOOLEAN             	DEFAULT FALSE,
  `RIGHTNODIFY` BOOLEAN             	DEFAULT FALSE,
  `RIGHTDELETE` BOOLEAN             	DEFAULT FALSE,
  `RIGHTSEARCH` BOOLEAN             	DEFAULT FALSE
)ENGINE = MyISAM  DEFAULT CHARSET = utf8;

INSERT INTO `login` (`ID_USER`, `USERNAME`, `PASSWORD`, `RIGHTS`, `RIGHTSAVE`, `RIGHTNODIFY`, `RIGHTDELETE`, `RIGHTSEARCH`) VALUES (NULL, 'LUX', 'LUX', 'admin', '1', '1', '1', '1');
INSERT INTO `LOGIN` (`ID_USER`, `USERNAME`, `PASSWORD`) VALUES (NULL, 'Bicoz', 	'zico');
INSERT INTO `LOGIN` (`ID_USER`, `USERNAME`, `PASSWORD`) VALUES (NULL, 'Jean-Sandro', 	'Thimo');
INSERT INTO `LOGIN` (`ID_USER`, `USERNAME`, `PASSWORD`) VALUES (NULL, 'Peters', 	'DOR');



/*
  Ministere des Affaires Etrangeres et des Cultes
    MAEC
  Ministere des Affaires Sociales
    MAS
  Ministere de l'Agriculture et des Ressources
    MAR
  Ministere du Commmerce et de l'Industries
    MCI
  Ministere du Communication et de la Culture
    MCC
  Ministere a la Condition Feminine
    MCF
  Ministere de de l'Economie et de la Finance
    MEF
  Ministere de L'environement
    ME
  Ministere d'education national et de la formation professionelle
    MENFP
  Ministere de la jeunesse, des sports et de l\'actcions civique
    MJSAC
  Ministere du Tourisme
    MT
  Ministere de l'Interieur et de la Collectivites Territoriales
    MICT
  Ministere de la Planifivation et de la Cooperation
    MPC
  Ministere de la Justice
    MJ
  Ministere de la Sante Publique et la Poupulation
    MSPP
  Ministere des Travaux Publiques du Transpoert et de la Construction
    MTPTC

*/