-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema atividade08
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `atividade08` ;

-- -----------------------------------------------------
-- Schema atividade08
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atividade08` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `atividade08` ;

-- -----------------------------------------------------
-- Table `atividade08`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atividade08`.`category` ;

CREATE TABLE IF NOT EXISTS `atividade08`.`category` (
  `category_id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `atividade08`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atividade08`.`user` ;

CREATE TABLE IF NOT EXISTS `atividade08`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(30) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password_hash` VARCHAR(60) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `profile_pic` VARCHAR(100) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `atividade08`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atividade08`.`course` ;

CREATE TABLE IF NOT EXISTS `atividade08`.`course` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `instructor_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `category_id` VARCHAR(50) NULL DEFAULT NULL,
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`course_id`),
  UNIQUE INDEX `course_id_UNIQUE` (`course_id` ASC) VISIBLE,
  INDEX `fk_course_user_idx` (`instructor_id` ASC) VISIBLE,
  INDEX `fk_course_category_idx` (`category_id` ASC) VISIBLE,
  CONSTRAINT `fk_course_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `atividade08`.`category` (`category_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_user`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `atividade08`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `atividade08`.`enrollment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atividade08`.`enrollment` ;

CREATE TABLE IF NOT EXISTS `atividade08`.`enrollment` (
  `course_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `enrolled_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`course_id`, `user_id`),
  INDEX `fk_course_has_user_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_course_has_user_course1_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_course_has_user_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `atividade08`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `atividade08`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `atividade08`.`lesson`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `atividade08`.`lesson` ;

CREATE TABLE IF NOT EXISTS `atividade08`.`lesson` (
  `lesson_id` INT NOT NULL AUTO_INCREMENT,
  `course_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `position` INT NULL DEFAULT NULL,
  `notes_file` VARCHAR(100) NULL,
  PRIMARY KEY (`lesson_id`),
  INDEX `fk_lesson_course1_idx` (`course_id` ASC) VISIBLE,
  CONSTRAINT `fk_lesson_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `atividade08`.`course` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
