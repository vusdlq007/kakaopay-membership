-- MySQL Script generated by MySQL Workbench
-- Sat Jun 25 22:57:45 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema MEMBERSHIP
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema MEMBERSHIP
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MEMBERSHIP` DEFAULT CHARACTER SET utf8 ;
USE `MEMBERSHIP` ;

-- -----------------------------------------------------
-- Table `MEMBERSHIP`.`T_MEMBER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEMBERSHIP`.`T_MEMBER` (
  `id` INT(9) NOT NULL,
  `name` VARCHAR(45) NULL,
  `barcode` VARCHAR(10) NOT NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`, `barcode`),
  UNIQUE INDEX `barcode_UNIQUE` (`barcode` ASC) ) DEFAULT CHARACTER SET UTF8
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEMBERSHIP`.`T_STORE_CATEGORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEMBERSHIP`.`T_STORE_CATEGORY` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEMBERSHIP`.`T_STORE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEMBERSHIP`.`T_STORE` (
  `id` VARCHAR(10) NOT NULL,
  `name` VARCHAR(45) NULL,
  `category_id` INT NOT NULL,
  `created_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_T_STORE_T_STORE_CATEGORY_idx` (`category_id` ASC) ,
  CONSTRAINT `fk_T_STORE_T_STORE_CATEGORY`
    FOREIGN KEY (`category_id`)
    REFERENCES `MEMBERSHIP`.`T_STORE_CATEGORY` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEMBERSHIP`.`T_POINT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEMBERSHIP`.`T_POINT` (
  `id` INT NOT NULL,
  `point` INT NULL,
  `store_category` INT NOT NULL,
  `created_at` DATETIME NULL,
  `barcode` VARCHAR(10) NOT NULL,
  `store_id` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_T_POINT_T_STORE_CATEGORY1_idx` (`store_category` ASC) ,
  INDEX `fk_T_POINT_T_MEMBER1_idx` (`barcode` ASC) ,
  INDEX `fk_T_POINT_T_STORE1_idx` (`store_id` ASC) ,
  CONSTRAINT `fk_T_POINT_T_STORE_CATEGORY1`
    FOREIGN KEY (`store_category`)
    REFERENCES `MEMBERSHIP`.`T_STORE_CATEGORY` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_POINT_T_MEMBER1`
    FOREIGN KEY (`barcode`)
    REFERENCES `MEMBERSHIP`.`T_MEMBER` (`barcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_POINT_T_STORE1`
    FOREIGN KEY (`store_id`)
    REFERENCES `MEMBERSHIP`.`T_STORE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEMBERSHIP`.`T_ACT_LOG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MEMBERSHIP`.`T_ACT_LOG` (
  `id` INT NOT NULL,
  `member_barcode` VARCHAR(10) NOT NULL,
  `approved_at` DATETIME NULL,
  `category` INT NOT NULL,
  `ac_point` INT NULL,
  `remain_point` INT NULL,
  `ac_type` VARCHAR(10) NULL,
  `created_at` DATETIME NULL,
  `store_name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_T_ACT_LOG_T_MEMBER1_idx` (`member_barcode` ASC) ,
  UNIQUE INDEX `member_barcode_UNIQUE` (`member_barcode` ASC) ,
  INDEX `fk_T_ACT_LOG_T_STORE_CATEGORY1_idx` (`category` ASC) ,
  CONSTRAINT `fk_T_ACT_LOG_T_MEMBER1`
    FOREIGN KEY (`member_barcode`)
    REFERENCES `MEMBERSHIP`.`T_MEMBER` (`barcode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_ACT_LOG_T_STORE_CATEGORY1`
    FOREIGN KEY (`category`)
    REFERENCES `MEMBERSHIP`.`T_STORE_CATEGORY` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
