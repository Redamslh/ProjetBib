-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 30 mai 2020 à 19:06
-- Version du serveur :  5.7.26
-- Version de PHP :  7.3.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `biblio`
--

DELIMITER $$
--
-- Procédures
--
DROP PROCEDURE IF EXISTS `delay`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `delay` (`t_adh` INT)  begin 
	declare jourest int ;
	select min (Day(dateemp)-dureemax) into  jourest from emprunter where t_adh=NA;
	if jourest>0 then 
	select"tt";
    else 
     select(nn) ;
    end if ;
    end$$

DROP PROCEDURE IF EXISTS `enr`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `enr` (IN `v_NL` INT, IN `v_dureeMax` INT, IN `v_NA` INT)  begin 
declare v_titre varchar(30);
declare v_nom varchar(30);
declare v_prenom varchar(30);
declare ad_existe int ;
declare v_livreexiste int;
declare v_check boolean default true;
select count(*) into ad_existe from adherents where v_NA=NA; 
select titre into v_titre from oeuvres , livres where livres.NO=oeuvres.NO and v_NL=livres.NL;
select nom into v_nom from adherents where v_NA=NA;
select prenom into v_prenom from adherents where v_NA=NA;
select count(oeuvres.NO) into v_livreexiste from oeuvres,livres where livres.NO=oeuvres.NO and v_NL=livres.NL;
IF (select existe(v_titre)=0)THEN
    select 'ce livre n\'est pas disponible';
    set v_check=false;
END IF;
IF(v_livreexiste=0) THEN
    select 'ce livres n\'existe pas';
    set v_check=false;
END IF;
IF (select nbrempadh(v_nom,v_prenom)>=3) THEN
    set v_check=false;
    select 'l\'adherent emprunte deja 3 livres';
END IF;
IF (ad_existe=0) THEN 
    set v_check=false;
    select 'l\'adherent n\'existe pas';
END IF;
IF (v_check=true) THEN
    insert into emprunter values(v_NL,current_date,v_dureeMax,NULL,v_NA);
END IF;
end$$

DROP PROCEDURE IF EXISTS `ins`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ins` (`aNo` INT)  begin 
declare nd int;
select min(NL) into nd from livres where No=aNo;
select nd;
end$$

DROP PROCEDURE IF EXISTS `nbre`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `nbre` (`atitre` VARCHAR(40))  begin 
declare nbre_dispo int;
select count(*)-(select count(*)from emprunter,livres,oeuvres where livres.no=oeuvres.no and emprunter.NL=livres.NL and oeuvres.titre=atitre  and emprunter.dateRet IS NULL) into nbre_dispo from livres,oeuvres where livres.no=oeuvres.no and oeuvres.titre=atitre;
select nbre_dispo ; 
end$$

DROP PROCEDURE IF EXISTS `nbrexempl`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `nbrexempl` (IN `v_livre` VARCHAR(30))  begin
declare res int;
select count(*)-(select count(*) from emprunter,livres,oeuvres where livres.NO=oeuvres.NO and emprunter.NL=livres.NL and oeuvres.titre=v_livre and emprunter.dateRet IS NULL) into res from livres,oeuvres where livres.NO=oeuvres.NO and v_livre=oeuvres.titre;
select res ;
end$$

--
-- Fonctions
--
DROP FUNCTION IF EXISTS `adh`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `adh` (`a_NA` INT) RETURNS INT(11) begin
declare emp int ;
select count(*) into emp from emprunter,adherents where adherents.NA=emprunter.NA and a_NA=adherents.NA and emprunter.dateRet is NULL;
return emp;
end$$

DROP FUNCTION IF EXISTS `dd`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `dd` (`t_adh` INT) RETURNS INT(11) begin 
	declare jourest int ;
	select tel into jourest from adherents where t_adh=NA ; 
    return jourest ;
    end$$

DROP FUNCTION IF EXISTS `disp`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `disp` (`anl` INT) RETURNS INT(11) begin 
declare rest int;
declare nbre_dispo int;
 if (anl in (select nl from emprunter where dateret is NULL and  anl=nl)) then 
set rest=0; 
ELSE
set rest=1;
end if;
 RETURN rest;
end$$

DROP FUNCTION IF EXISTS `existe`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `existe` (`v_titre` VARCHAR(30)) RETURNS INT(11) begin 
declare res int;
IF (select nbrexempl(v_titre)=0) THEN 
     set res = 0;
ELSE 
     set res = 1;
END IF;
RETURN res ;
END$$

DROP FUNCTION IF EXISTS `nbre`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nbre` (`anl` INT) RETURNS INT(11) begin 
declare rest int;
declare nbre_dispo int; 
select count(*)-(select count(*)from emprunter,livres,oeuvres where livres.no=oeuvres.no and emprunter.NL=livres.NL and livres.NL=anl and emprunter.dateRet IS NULL) into nbre_dispo from livres,oeuvres where livres.no=oeuvres.no and livres.NL=anl;
 if (nbre_dispo>0) then 
set rest=1 ; 
ELSE set rest=0;
end if;
 RETURN rest;
end$$

DROP FUNCTION IF EXISTS `nbrempadh`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nbrempadh` (`v_nom` VARCHAR(30), `v_prenom` VARCHAR(30)) RETURNS INT(11) begin
declare res int ;
select count(NL) into res from emprunter,adherents where adherents.NA=emprunter.NA and v_nom=adherents.nom and v_prenom=adherents.prenom and emprunter.dateRet IS NULL;
RETURN res;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

DROP TABLE IF EXISTS `adherents`;
CREATE TABLE IF NOT EXISTS `adherents` (
  `login` varchar(30) NOT NULL,
  `NA` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) DEFAULT NULL,
  `adr` varchar(100) NOT NULL,
  `tel` char(10) DEFAULT NULL,
  `motdepasse` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  PRIMARY KEY (`NA`)
) ENGINE=InnoDB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`login`, `NA`, `nom`, `prenom`, `adr`, `tel`, `motdepasse`, `role`) VALUES
('55', 1, 'Lecoeur', 'Jeanette', '16 rue de la R?publique, 75010 Paris', '0145279274', '1235', 'adherent'),
('lolo', 2, 'Lecoeur', 'Philippe', '16 rue de la R?publique, 75010 Paris', '0145279274', '123', 'admin'),
('', 3, 'Dupont', 'Yvan', '73 rue Lamarck, 75018 Paris', '0163538294', '', ''),
('', 4, 'Mercier', 'Claude', '155 avenue Parmentier, 75011 Paris', '0136482736', '', ''),
('', 5, 'L?ger', 'Marc', '90 avenue du Maine, 75014 Paris', '0164832947', '', ''),
('', 6, 'Martin', 'Laure', '51 boulevard Diderot, 75012 Paris', '0174693277', '', ''),
('', 7, 'Crozier', 'Martine', '88 rue des Portes Blanches, 75018 Paris', '0146829384', '', ''),
('', 8, 'Lebon', 'Cl?ment', '196 boulevard de Sebastopol, 75001 Paris', '0132884739', '', ''),
('', 9, 'Dufour', 'Jacques', '32 rue des Alouettes, 75003 Paris', '0155382940', '', ''),
('', 10, 'Dufour', 'Antoine', '32 rue des Alouettes, 75003 Paris', '0155382940', '', ''),
('', 11, 'Dufour', 'St?phanie', '32 rue des Alouettes, 75003 Paris', '0155382940', '', ''),
('', 12, 'Raymond', 'Carole', '35 rue Oberkampf, 75011 Paris', '0153829402', '', ''),
('', 13, 'Durand', 'Albert', '4 rue Mandar, 75002 Paris', '0642374021', '', ''),
('', 14, 'Wilson', 'Paul', '12 rue Paul Vaillant Couturier, 92400 Montrouge', '0642327407', '', ''),
('', 15, 'Grecault', 'Philippe', '15 rue de la Roquette, 75012 Paris', '0132762983', '', ''),
('', 16, 'Carre', 'St?phane', '51 boulevard Diderot, 75012 Paris', '0174693277', '', ''),
('', 17, 'Johnson', 'Astrid', '3 rue L?on Blum, 75002 Paris', '0143762947', '', ''),
('', 18, 'Mirou', 'Caroline', '2 square Mirabeau, 75011 Paris', '0163827399', '', ''),
('', 19, 'Espelette', 'Jean-Jacques', '141 avenue de France, 75019 Paris', '0134887264', '', ''),
('', 20, 'Despentes', 'Anthony', '56 boulevard de la Villette, 75019 Paris', '0133889463', '', ''),
('', 21, 'Terlu', 'V?ronique', '45 rue des Batignolles, 75020 Paris', '0165998372', '', ''),
('', 22, 'Rihour', 'C?cile', '7 rue Montorgueil, 75002 Paris', '0166894754', '', ''),
('', 23, 'Franchet', 'Pierre', '160 rue Montmartre, 75009 Paris', '0633628549', '', ''),
('', 24, 'Trochet', 'Ernest', '34 rue de l\'Esperance, 75008 Paris', '0638295563', '', ''),
('', 25, 'Gainard', 'Simon', '55 rue Desnouettes, 75015 Paris', '0174928934', '', ''),
('', 26, 'Touche', 'Johanna', '14 rue du Bac, 75006 Paris', '0619384065', '', ''),
('', 27, 'Cornu', 'Sylvain', '22 rue Mouffetard, 75005 Paris', '0184927489', '', ''),
('', 28, 'Frederic', 'Cyril', '15 rue du Simplon, 75018 Paris', '0173625492', '', ''),
('', 29, 'Crestard', 'Cedric', '5 rue Doudeauville, 75018 Paris', '0629485700', '', ''),
('', 30, 'Le Bihan', 'Karine', '170 bis rue Ordener, 75018 Paris', '0638995221', '', ''),
('', 31, '', NULL, '', NULL, '', '');

-- --------------------------------------------------------

--
-- Structure de la table `avoir`
--

DROP TABLE IF EXISTS `avoir`;
CREATE TABLE IF NOT EXISTS `avoir` (
  `idMod` int(11) DEFAULT NULL,
  `idMatiere` int(11) DEFAULT NULL,
  KEY `AVOIR_FK` (`idMod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `contient`
--

DROP TABLE IF EXISTS `contient`;
CREATE TABLE IF NOT EXISTS `contient` (
  `idFillere` int(11) DEFAULT NULL,
  `idMod` int(11) DEFAULT NULL,
  KEY `CONTIENT_FK` (`idFillere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `idDep` int(11) NOT NULL,
  `nomDepartement` varchar(254) DEFAULT NULL,
  `responsable` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idDep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `emprunter`
--

DROP TABLE IF EXISTS `emprunter`;
CREATE TABLE IF NOT EXISTS `emprunter` (
  `NumEmp` int(11) NOT NULL,
  `NL` int(11) NOT NULL,
  `dateEmp` date NOT NULL,
  `dateRet` varchar(20) DEFAULT NULL,
  `NA` int(11) DEFAULT NULL,
  `dureeMax` int(11) NOT NULL,
  PRIMARY KEY (`NumEmp`),
  KEY `ff` (`NL`),
  KEY `kk` (`NA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `emprunter`
--

INSERT INTO `emprunter` (`NumEmp`, `NL`, `dateEmp`, `dateRet`, `NA`, `dureeMax`) VALUES
(1, 2, '2020-03-05', '2020-03-03', 1, 15),
(2, 2, '2020-03-05', '', 1, 15),
(3, 2, '2020-03-05', 'Pas encore retourné', 1, 15);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `idFillere` int(11) DEFAULT NULL,
  `cne` int(11) NOT NULL,
  `idAbsence` int(11) DEFAULT NULL,
  `nom` int(11) DEFAULT NULL,
  `prenom` varchar(254) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `telParr_ents` int(11) DEFAULT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`cne`),
  KEY `AVOIR_FK` (`idAbsence`),
  KEY `FK_ETUDIANT_APPARTIEN_FILIERE` (`idFillere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `filiere`
--

DROP TABLE IF EXISTS `filiere`;
CREATE TABLE IF NOT EXISTS `filiere` (
  `abreveation` varchar(254) DEFAULT NULL,
  `idFillere` int(11) NOT NULL,
  `idDep` int(11) DEFAULT NULL,
  `niveau` varchar(254) DEFAULT NULL,
  `nomFiliere` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idFillere`),
  KEY `APPARTIENT_FK` (`idDep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

DROP TABLE IF EXISTS `livres`;
CREATE TABLE IF NOT EXISTS `livres` (
  `NL` int(11) NOT NULL AUTO_INCREMENT,
  `editeur` varchar(50) DEFAULT NULL,
  `NO` int(11) NOT NULL,
  PRIMARY KEY (`NL`),
  KEY `NO` (`NO`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livres`
--

INSERT INTO `livres` (`NL`, `editeur`, `NO`) VALUES
(1, 'GF', 1),
(2, 'FOLIO', 2),
(3, 'HACHETTE', 3),
(4, 'GF', 4),
(5, 'FOLI', 1),
(6, 'FOLIO', 6),
(7, 'GF', 7),
(8, 'FOLIO', 8),
(9, 'HACHETTE', 9),
(10, 'GF', 10),
(11, 'HACHETTE', 11),
(12, 'FOLIO', 12),
(13, 'GF', 13),
(14, 'FOLIO', 14),
(15, 'HACHETTE', 15),
(16, 'HACHETTE', 16),
(17, 'GF', 1),
(18, 'FOLIO', 2),
(19, 'HACHETTE', 2),
(20, 'FOLIO', 4),
(21, 'GF', 5),
(22, 'HACHETTE', 4),
(23, 'HACHETTE', 7),
(24, 'FOLIO', 8),
(25, 'GF', 1),
(26, 'HACHETTE', 10),
(27, 'FOLIO', 11),
(28, 'FOLIO', 12),
(29, 'GF', 1),
(30, 'HACHETTE', 14),
(31, 'FOLI', 1);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `idMatiere` int(11) NOT NULL,
  `idAbsence` int(11) DEFAULT NULL,
  `libelle` int(11) DEFAULT NULL,
  `prof` int(11) DEFAULT NULL,
  `typeMatiere` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMatiere`),
  KEY `CONCERNE_FK` (`idAbsence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

DROP TABLE IF EXISTS `module`;
CREATE TABLE IF NOT EXISTS `module` (
  `idMod` int(11) NOT NULL,
  `libelle` varchar(254) DEFAULT NULL,
  PRIMARY KEY (`idMod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `oeuvres`
--

DROP TABLE IF EXISTS `oeuvres`;
CREATE TABLE IF NOT EXISTS `oeuvres` (
  `NO` int(11) NOT NULL AUTO_INCREMENT,
  `titre` varchar(150) DEFAULT NULL,
  `auteur` varchar(100) DEFAULT NULL,
  `nbrePage` int(11) NOT NULL,
  PRIMARY KEY (`NO`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `oeuvres`
--

INSERT INTO `oeuvres` (`NO`, `titre`, `auteur`, `nbrePage`) VALUES
(1, 'Narcisse et Goldmund', 'Hermann HESSE', 255),
(2, 'B?r?nice', 'Jean RACINE', 0),
(3, 'Prol?gom?nes ?  toute m?taphysique future', 'Emmanuel KANT', 0),
(4, 'Mon coeur mis ? nu', 'Charles BAUDELAIRE', 0),
(5, 'Voyage au bout de la nuit', 'Louis-Ferdinand CELINE', 0),
(6, 'Les poss?d?s', 'Fedor DOSTOIEVSKI', 0),
(7, 'Le Rouge et le Noir', 'STENDHAL', 0),
(8, 'Alcibiade', 'Jacqueline de ROMILLY', 0),
(9, 'Monsieur Teste', 'Paul VALERY', 0),
(10, 'Lettres de Gourgounel', 'Kenneth WHITE', 0),
(11, 'Lettres ? un jeune po?te', 'Rainer Maria RILKE', 0),
(12, 'Logique sans peine', 'Lewis CAROLL', 0),
(13, 'L\'?thique', 'Baruch SPINOZA', 0),
(14, 'Sur le r?ve', 'Sigmund FREUD', 0),
(15, 'Sens et d?notation', 'Gottlob FREGE', 0),
(16, 'Penser la logique', 'Gilbert HOTTOIS', 0),
(17, 'Au coeur des t?n?bres', 'Joseph CONRAD', 0),
(18, 'Jan Karski', 'Yannick HAENEL', 0),
(19, 'La chasse au Snark', 'Lewis CAROLL', 0),
(20, 'Sur le r?ve', 'Sigmund FREUD', 500),
(21, 'fsdfsd', 'fsdfsd', 400),
(23, 'GG', 'HJGJ6', 366);

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `rd`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `rd`;
CREATE TABLE IF NOT EXISTS `rd` (
`na` int(11)
,`dateret` varchar(20)
);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL,
  `login` varchar(30) NOT NULL,
  `mdp` varchar(30) NOT NULL,
  `role` varchar(30) NOT NULL,
  KEY `ggg` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `mdp`, `role`) VALUES
(1, 'reda', '1234', 'admin'),
(2, 'ziko', '1234', 'adherent');

-- --------------------------------------------------------

--
-- Structure de la vue `rd`
--
DROP TABLE IF EXISTS `rd`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rd`  AS  select `emprunter`.`NA` AS `na`,`emprunter`.`dateRet` AS `dateret` from `emprunter` ;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `emprunter`
--
ALTER TABLE `emprunter`
  ADD CONSTRAINT `ff` FOREIGN KEY (`NL`) REFERENCES `livres` (`NL`),
  ADD CONSTRAINT `kk` FOREIGN KEY (`NA`) REFERENCES `adherents` (`NA`);

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `FK_ETUDIANT_APPARTIEN_FILIERE` FOREIGN KEY (`idFillere`) REFERENCES `filiere` (`idFillere`);

--
-- Contraintes pour la table `livres`
--
ALTER TABLE `livres`
  ADD CONSTRAINT `gg` FOREIGN KEY (`NO`) REFERENCES `oeuvres` (`NO`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `ggg` FOREIGN KEY (`id`) REFERENCES `adherents` (`NA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
