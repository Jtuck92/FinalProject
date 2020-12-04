-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema giftrdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `giftrdb` ;

-- -----------------------------------------------------
-- Schema giftrdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `giftrdb` DEFAULT CHARACTER SET utf8 ;
USE `giftrdb` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `country` VARCHAR(45) NOT NULL,
  `zip` VARCHAR(45) NULL,
  `state_province` VARCHAR(2) NOT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(1000) NOT NULL,
  `password` VARCHAR(400) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `address_id` INT NOT NULL,
  `birth_date` DATE NULL,
  `gender` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_address_idx` (`address_id` ASC),
  CONSTRAINT `fk_user_address`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `private_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `private_event` ;

CREATE TABLE IF NOT EXISTS `private_event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `last_update` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_date` DATETIME NULL,
  `manager_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  `image_url` VARCHAR(5000) NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC),
  INDEX `fk_group_user1_idx` (`manager_id` ASC),
  CONSTRAINT `fk_group_user1`
    FOREIGN KEY (`manager_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `private_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `private_post` ;

CREATE TABLE IF NOT EXISTS `private_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `image_url` TEXT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  `rating` VARCHAR(45) NULL,
  `subject` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_group1_idx` (`group_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `private_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `private_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `private_comment` ;

CREATE TABLE IF NOT EXISTS `private_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `user_id` INT NOT NULL,
  `post_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comments_user1_idx` (`user_id` ASC),
  INDEX `fk_comments_post1_idx` (`post_id` ASC),
  CONSTRAINT `fk_comments_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comments_post1`
    FOREIGN KEY (`post_id`)
    REFERENCES `private_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `budget` ;

CREATE TABLE IF NOT EXISTS `budget` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `low_price` DOUBLE NULL,
  `high_price` DOUBLE NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event` ;

CREATE TABLE IF NOT EXISTS `event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(400) NOT NULL,
  `description` TEXT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `enabled` TINYINT NULL,
  `budget_id` INT NOT NULL,
  `image_url` VARCHAR(5000) NULL,
  `creator_id` INT NOT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_category1_idx` (`budget_id` ASC),
  INDEX `fk_event_user1_idx` (`creator_id` ASC),
  CONSTRAINT `fk_event_category1`
    FOREIGN KEY (`budget_id`)
    REFERENCES `budget` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_user1`
    FOREIGN KEY (`creator_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gift`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gift` ;

CREATE TABLE IF NOT EXISTS `gift` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `price` DOUBLE NULL,
  `weight_kg` DOUBLE NULL,
  `description` TEXT NULL,
  `enabled` TINYINT NOT NULL,
  `event_id` INT NOT NULL,
  `gifter_id` INT NOT NULL,
  `receiver_id` INT NOT NULL,
  `rating` INT NULL,
  `name` VARCHAR(400) NULL,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_gift_event1_idx` (`event_id` ASC),
  INDEX `fk_gift_user1_idx` (`gifter_id` ASC),
  INDEX `fk_gift_user2_idx` (`receiver_id` ASC),
  CONSTRAINT `fk_gift_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gift_user1`
    FOREIGN KEY (`gifter_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gift_user2`
    FOREIGN KEY (`receiver_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_type` ;

CREATE TABLE IF NOT EXISTS `event_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `enabled` TINYINT NULL,
  `description` TEXT NULL,
  `image_url` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `payment` ;

CREATE TABLE IF NOT EXISTS `payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `card_number` INT NULL,
  `amount` DOUBLE NULL,
  `exp` INT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `address_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_payment_address1_idx` (`address_id` ASC),
  INDEX `fk_payment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_payment_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_group` ;

CREATE TABLE IF NOT EXISTS `user_has_group` (
  `user_id` INT NOT NULL,
  `group_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `group_id`),
  INDEX `fk_user_has_group_group1_idx` (`group_id` ASC),
  INDEX `fk_user_has_group_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_group_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_group_group1`
    FOREIGN KEY (`group_id`)
    REFERENCES `private_event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_has_event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_has_event` ;

CREATE TABLE IF NOT EXISTS `user_has_event` (
  `user_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `event_id`),
  INDEX `fk_user_has_event_event1_idx` (`event_id` ASC),
  INDEX `fk_user_has_event_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_event_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_event_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_has_event_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_has_event_type` ;

CREATE TABLE IF NOT EXISTS `event_has_event_type` (
  `event_id` INT NOT NULL,
  `event_type_id` INT NOT NULL,
  PRIMARY KEY (`event_id`, `event_type_id`),
  INDEX `fk_event_has_event_type_event_type1_idx` (`event_type_id` ASC),
  INDEX `fk_event_has_event_type_event1_idx` (`event_id` ASC),
  CONSTRAINT `fk_event_has_event_type_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_has_event_type_event_type1`
    FOREIGN KEY (`event_type_id`)
    REFERENCES `event_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_post` ;

CREATE TABLE IF NOT EXISTS `event_post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` TEXT NOT NULL,
  `image_url` TEXT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enabled` TINYINT NULL,
  `rating` VARCHAR(45) NULL,
  `event_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `subject` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_post_event1_idx` (`event_id` ASC),
  INDEX `fk_event_post_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_event_post_event1`
    FOREIGN KEY (`event_id`)
    REFERENCES `event` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_event_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `event_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `event_comment` ;

CREATE TABLE IF NOT EXISTS `event_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `created_date` DATETIME NULL,
  `last_update` DATETIME NULL,
  `enabled` TINYINT NULL,
  `event_post_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_group_comment_copy1_event_post1_idx` (`event_post_id` ASC),
  INDEX `fk_group_comment_copy1_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_group_comment_copy1_event_post1`
    FOREIGN KEY (`event_post_id`)
    REFERENCES `event_post` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_comment_copy1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS giftr@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'giftr'@'localhost' IDENTIFIED BY 'giftr';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'giftr'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `address` (`id`, `street`, `street2`, `country`, `zip`, `state_province`, `enabled`) VALUES (1, 'Street Name 1', '11', 'USA', '11111', 'CO', 1);
INSERT INTO `address` (`id`, `street`, `street2`, `country`, `zip`, `state_province`, `enabled`) VALUES (2, 'Street Name 2', '22', 'USA', '22222', 'CO', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `first_name`, `last_name`, `created_date`, `last_update`, `address_id`, `birth_date`, `gender`) VALUES (1, '11', '11@giftr.com', '11', 1, '11', '11', NULL, NULL, 1, NULL, 'm');
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `first_name`, `last_name`, `created_date`, `last_update`, `address_id`, `birth_date`, `gender`) VALUES (2, '22', '22@giftr.com', '22', 1, '22', '22', NULL, NULL, 2, NULL, 'f');

COMMIT;


-- -----------------------------------------------------
-- Data for table `private_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `private_event` (`id`, `name`, `last_update`, `created_date`, `manager_id`, `enabled`, `image_url`, `description`) VALUES (1, 'Private Event Name', NULL, NULL, 1, 1, NULL, 'Private Event Description');

COMMIT;


-- -----------------------------------------------------
-- Data for table `private_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `private_post` (`id`, `description`, `image_url`, `created_date`, `last_update`, `user_id`, `group_id`, `enabled`, `rating`, `subject`) VALUES (1, 'Private Giftr Description', NULL, NULL, NULL, 1, 1, 1, '5', 'New Private Subject');

COMMIT;


-- -----------------------------------------------------
-- Data for table `private_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `private_comment` (`id`, `comment`, `created_date`, `last_update`, `user_id`, `post_id`, `enabled`) VALUES (1, 'Private Giftr Comment', NULL, NULL, 2, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `budget`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `budget` (`id`, `low_price`, `high_price`, `enabled`) VALUES (1, 5.00, 10.00, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`) VALUES (1, 'Giftr Event Name', 'Giftr Event Description', NULL, NULL, 1, 1, NULL, 1, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gift`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `gift` (`id`, `price`, `weight_kg`, `description`, `enabled`, `event_id`, `gifter_id`, `receiver_id`, `rating`, `name`, `image_url`) VALUES (1, 12.50, 12, 'New Gift', 1, 1, 1, 2, 5, 'name', 'image');

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `event_type` (`id`, `name`, `enabled`, `description`, `image_url`) VALUES (1, 'New Type ', 1, 'New Event Type', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `payment` (`id`, `card_number`, `amount`, `exp`, `created_date`, `last_update`, `address_id`, `user_id`, `enabled`) VALUES (1, 1111, 11.11, 111, NULL, NULL, 1, 1, 1);
INSERT INTO `payment` (`id`, `card_number`, `amount`, `exp`, `created_date`, `last_update`, `address_id`, `user_id`, `enabled`) VALUES (2, 2222, 22.22, 222, NULL, NULL, 2, 2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `user_has_group` (`user_id`, `group_id`) VALUES (1, 1);
INSERT INTO `user_has_group` (`user_id`, `group_id`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_has_event`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `user_has_event` (`user_id`, `event_id`) VALUES (1, 1);
INSERT INTO `user_has_event` (`user_id`, `event_id`) VALUES (2, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_has_event_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `event_has_event_type` (`event_id`, `event_type_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_post`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `event_post` (`id`, `description`, `image_url`, `created_date`, `last_update`, `enabled`, `rating`, `event_id`, `user_id`, `subject`) VALUES (1, 'New Event Description', NULL, NULL, NULL, 1, '5', 1, 1, 'New Event Subject');

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `event_comment` (`id`, `comment`, `created_date`, `last_update`, `enabled`, `event_post_id`, `user_id`) VALUES (1, 'THIS WAS SUPER FUN', NULL, NULL, 1, 1, 2);

COMMIT;

