SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bedujbiis-------------------------------------
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `bedujbiis` ;
USE `bedujbiis` ;

-- -----------------------------------------------------
-- Table `bedujbiis`.`clientes`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `bedujbiis`.`clientes` ;

CREATE TABLE IF NOT EXISTS `bedujbiis`.`clientes` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(50) NOT NULL,
  `CorreoContacto` VARCHAR(50) NOT NULL,
  `numero_empleados` VARCHAR(10) NOT NULL,
  `direccion` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `idCliente_UNIQUE` (`idCliente` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `bedujbiis`.`etapas`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `bedujbiis`.`etapas` ;
CREATE TABLE IF NOT EXISTS `bedujbiis`.`etapas` (
  `idEtapa` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `orden` INT NOT NULL,
  PRIMARY KEY (`idEtapa`));

-- -----------------------------------------------------
-- Table `bedujbiis`.`productos`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `bedujbiis`.`productos` ;
CREATE TABLE IF NOT EXISTS `bedujbiis`.`productos`(
  `idproducto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `categoria` VARCHAR(50) NOT NULL,
  `precio` FLOAT NOT NULL,
  `numeroRegistro` VARCHAR(45) NOT NULL,
  `fechaCreacion` DATE NOT NULL,
  PRIMARY KEY (`idproducto`),
  UNIQUE INDEX `idproducto_UNIQUE` (`idproducto` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `bedujbiis`.`ventas`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `bedujbiis`.`ventas` ;
CREATE TABLE IF NOT EXISTS `bedujbiis`.`ventas` (
    `idventas` INT NOT NULL AUTO_INCREMENT,
    `monto` FLOAT NOT NULL,
    `fechacreacion` DATE NOT NULL,
    `ventascol` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idventas`),
  UNIQUE INDEX `idventas_UNIQUE` (`idventas` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `bedujbiis`.`visitas`
-- -----------------------------------------------------

 DROP TABLE IF EXISTS `bedujbiis`.`visitas` ;
 CREATE TABLE IF NOT EXISTS `bedujbiis`.`visitas` (
   `idvisitas` INT NOT NULL AUTO_INCREMENT,
   `fechaprogramada` DATE NOT NULL,
   `direccion` VARCHAR(60) NOT NULL,
   `proposito` VARCHAR(200) NOT NULL,
   `vendedor` VARCHAR(50) NOT NULL,
   PRIMARY KEY (`idvisitas`),
   UNIQUE INDEX `idvisitas_UNIQUE` (`idvisitas` ASC) VISIBLE);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


