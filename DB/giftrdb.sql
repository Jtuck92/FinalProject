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
  `state_province` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NULL,
  `city` VARCHAR(100) NOT NULL,
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
  `role` INT NULL,
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
  `region` TINYINT NULL,
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
  `receiver_id` INT NULL,
  `rating` INT NULL,
  `name` VARCHAR(400) NULL,
  `image_url` TEXT NULL,
  `note` TEXT NULL,
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
  `card_number` VARCHAR(20) NULL,
  `amount` DOUBLE NULL,
  `exp` DATE NULL,
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
INSERT INTO `address` (`id`, `street`, `street2`, `country`, `zip`, `state_province`, `enabled`, `city`) VALUES (1, 'Street Name 1', '11', 'USA', '11111', 'CO', 1, 'Denver');
INSERT INTO `address` (`id`, `street`, `street2`, `country`, `zip`, `state_province`, `enabled`, `city`) VALUES (2, 'Street Name 2', '22', 'USA', '22222', 'CO', 1, 'Lakewood');
INSERT INTO `address` (`id`, `street`, `street2`, `country`, `zip`, `state_province`, `enabled`, `city`) VALUES (3, 'gifter', 'gifter', 'gifter', '111', 'CO', 1, 'gifter');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `first_name`, `last_name`, `created_date`, `last_update`, `address_id`, `birth_date`, `gender`, `role`) VALUES (1, '11', '11@giftr.com', '$2a$10$N8jBGxjk4M3fghOgXo2Us.wRZSRStbDtQXtBlqloiG70Kz3gUN1Ke', 1, '11', '11', NULL, NULL, 1, NULL, 'm', 2);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `first_name`, `last_name`, `created_date`, `last_update`, `address_id`, `birth_date`, `gender`, `role`) VALUES (2, '22', '22@giftr.com', '$2a$10$ADCtBZImdUBJMrkIfUIq0.bNfXVqi4Hvu7M0tXyODBc8ueeNZBif6', 1, '22', '22', NULL, NULL, 2, NULL, 'f', 2);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `first_name`, `last_name`, `created_date`, `last_update`, `address_id`, `birth_date`, `gender`, `role`) VALUES (3, '33', '33@giftr.com', '$2a$10$qas4NU73vdWMuEGvZ16WrOjRznTeeeA9lYoTcuc2t0gwooif4ghVO', 1, '33', '33', NULL, NULL, 2, NULL, 'm', 2);
INSERT INTO `user` (`id`, `username`, `email`, `password`, `enabled`, `first_name`, `last_name`, `created_date`, `last_update`, `address_id`, `birth_date`, `gender`, `role`) VALUES (4, 'giftr', 'giftr@giftr.com', '$2a$10$n7j.29fHydLG5FCttxC0E.NkrJtr8VonA5wf8nkBrXgrKLmWqzrgm', 1, 'giftr', 'giftr', NULL, NULL, 3, NULL, NULL, 1);

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
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (1, 'Secret Santa', 'Welcome to the 1st annual Giftrs Secret Santa! Our hope is Giftr users from around the world will come together during Secret Santa for a common theme—to spread goodwill and joy to one another through the spirit of giving and receiving happiness. It\'s a time where love and optimism is needed across the globe, and at Giftr, we rely on the kind hearts of our users to shine a little light during the holiday season.', '2020-12-01', '2020-12-22', 1, 1, 'https://i.pinimg.com/564x/19/76/2f/19762fd04384dc6efde8281d99790afc.jpg', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (2, 'New Years Resolutions', 'Ok people, we\'re almost to January. By now you should have a good idea of what those New Year\'s resolutions will be. If you\'re part of the small subsection who stick to their goals and want some reinforcement or if you are part of the large majority who actually go backwards on the progress scale and need some encouragement, our first ever New Year\'s Resolutions exchange is here for you! Join this exchange and help someone in the world conquer their commitment! Fitness goals! Cooking goals! Exploring goals! New hobby goals! Family goals! You name it, I\'ll bet there\'s a gift that could help you stay on course. So sign up now and help move the needle!', '2020-12-14', '2021-01-24', 1, 1, 'https://images.pexels.com/photos/450301/pexels-photo-450301.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (3, 'Star Trek', 'Join this exchange and boldly go where no Giftrs exchange has gone before! The fandom here is beyond belief thanks to five live-action TV shows, one lesser-known animated series, and 13 feature films! Whether you\'re here for James T. Kirk, Mr. Spock, Leonard McCoy, Nyota, Scotty or any of the others, this is out of this world exchange you\'ve been waiting for.', '2020-12-10', '2021-02-14', 1, 1, 'http://www.pngmart.com/files/10/Star-Trek-Badge-PNG-Clipart.png', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (4, 'Pokémon', 'If you love Pokémon, and you love trading mystery gifts, you\'re in luck! Arceus has answered your prayers; the Pokémon gift exchange is back! You\'ve been training for this for 20 years. You want to be the very best, like no one ever was. You want to extend your reach to the stars above. You\'ve followed the franchise from Kanto to Kalos. And now it\'s finally time to share that love with other trainers from around the globe!', '2020-11-14', '2021-01-10', 1, 1, 'https://www.denofgeek.com/wp-content/uploads/2019/05/pokemon_go_december_community_day.0.jpg?resize=768%2C432', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (5, 'Marvel', 'You\'re a huge Marvel fan! You love geeking out with other Marvel fans, and with the Marvel gift exchange, it\'s a snap! Whether you\'re into the movies, the comics, or the gatherings, this exchange is for you. Maybe you have some vintage X-Men comics that need a new home. Or maybe you hook your giftee up with the latest figurine! The possibilities are infinite and I can guarantee at least half of you will feel so good about the results.', '2020-10-01', '2020-11-25', 0, 1, 'https://i2.wp.com/www.comicsbeat.com/wp-content/uploads/2018/12/1header.jpg?w=1476&ssl=1', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (6, 'Ugly Christmas Sweater', 'Welcome to the 1st annual Giftr’s Ugly Sweater Exchange! Our hope is Giftr users from around the world will come together during this exchange for a common theme—to spread goodwill and joy to one another through the spirit of giving and receiving happiness.', '2020-11-10', '2020-12-10', 1, 1, 'https://i.etsystatic.com/13789014/r/il/a9d5ce/1330428965/il_570xN.1330428965_151o.jpg', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (7 , 'Harry Potter', '20 years ago, the doors of Hogwarts swung open to us muggles and we\'ve never been the same. Since then, you\'ve grown to know the three Unforgivable Curses better than the Seven Deadly Sins, you’re more likely to wear a S.P.E.W. campaign button than a PETA one, and you’d rather read The Daily Prophet than the New York Times.', '2020-09-15', '2020-10-15', 0, 1, 'https://www.soda.com/wp-content/uploads/2020/04/harry-potter-streaming-guide.jpg', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (8, 'Board Games', 'Has your board game game skyrocketed since we\'ve been sheltering in place? Have you learned a new game or a new version of a classic? Dreaming of Rummikub schemes or Catan strategies? Us too! \nWe challenge you to find your giftee a cool new game they’ve never heard of and maybe, just mayyyyybe you’ll be so intrigued that you buy one for yourself too!', '2020-05-11', '2020-05-29', 0, 1, 'https://www.marinmommies.com/sites/default/files/styles/full-width_column_827/public/stories/boardgames.jpg?itok=dJHsKx19', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (9, 'Disney 2019', 'Fans of Walt, Mickey and all magical characters in-between,Giftrs is calling all princesses, theme park lovers and pin traders to join us! A Disney Giftr exchange is upon us! Spread the magic amongst users who need a little bit of pixie dust in their lives!', '2019-09-30', '2019-10-18', 0, 1, 'https://g.foolcdn.com/image/?url=https%3A//g.foolcdn.com/editorial/images/568199/disney-magic-kingdom-castle-illustration.jpg&w=2000&op=resize', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (10, 'Camping 2019', 'Grab your sleeping bag and your headlamp because Giftr’s is going camping! This exchange is for those of you who would rather sleep under the stars than in your own bed and prefer a roaring campfire (landscape permitting) over a brand new oven. Camping is such a great way to connect with the earth, so whether it\'s your first time or 100th time camping, sign up and share the love. ', '2019-07-12', '2019-08-01', 0, 1, 'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/camping-quotes-1556677391.jpg?crop=1.00xw:0.855xh;0,0.0384xh&resize=980:*', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (11, 'Game Of Thrones 2019', 'Whether you\'re a fan of the books or the TV show, hoping to become a crow, maester, or sellsword, now\'s the time to gather together and geek out to your fullest over the amazing world George R. R. Martin has created. It\'s the Game of Thrones exchange, happening now! Sign up today and exchange awesome gifts around everyone\'s favorite series.', '2019-05-13', '2019-05-31', 0, 1, 'https://www.nydailynews.com/resizer/pvhXtnU6RC_FkASfOTFwBLVLuko=/800x533/top/arc-anglerfish-arc2-prod-tronc.s3.amazonaws.com/public/D3V5DH4ZRZDHLAZNQ66CNA5RFQ.JPG', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (12, 'Lego 2019', ' LEGO exchange for all your brick building needs! Ok, maybe not all your brick building needs, but maybe some of your desires? There\'s something so satisfying about the sound of shaking an unopened box—a true LEGO lover knows that sound from rooms away. Happy building!', '2019-07-15', '2019-07-26', 0, 1, 'https://i.guim.co.uk/img/media/f372225128789883b3e2daaf123805b3b8325952/0_389_4453_2671/master/4453.jpg?width=620&quality=85&auto=format&fit=max&s=d38c13bc6d5467f91327e799b58370e8', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (13, 'Fitness 2019', 'On your mark, get set, exchange! Fitness freaks we have the perfect exchange for you. If you\'re a Giftr who loves fitness and wants to share some tips or tricks this is the exchange for you!', '2019-05-01', '2019-05-15', 0, 1, 'https://stewarthunter.armymwr.com/application/files/6415/4143/4439/FSHAAF_Fitness_EWa.jpg', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (14, 'Hot Sauce', 'If you laugh at ghost peppers and chug Cholula the look no further - this is the exchange for you! Send your hometown\'s famous spicy sauces to your giftee and share in this brain-melting hotness! ', '2019-04-01', '2019-04-19', 0, 1, 'https://gardenandgun.com/wp-content/uploads/2020/03/8M7A9749-1100x733.jpg', 1, NULL, NULL, NULL);
INSERT INTO `event` (`id`, `name`, `description`, `start_date`, `end_date`, `enabled`, `budget_id`, `image_url`, `creator_id`, `created_date`, `last_update`, `region`) VALUES (15, 'Photography 2019', 'Calling all fans of photography and pictures!  Do you wait with your tripod at the crack of dawn for that perfect sunrise photo with your DSLR? Or do you stock up on discontinued Polaroid film? Are you all digital, or do you still use disposable cameras from the drug store? Whatever you fancy, sign up for this exchange and say cheeeeesssseeeeee! ', '2019-02-19', '2019-03-08', 0, 1, 'https://www.dpreview.com/files/p/articles/2970164337/Best-2010s-03_1.jpeg', 1, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `gift`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `gift` (`id`, `price`, `weight_kg`, `description`, `enabled`, `event_id`, `gifter_id`, `receiver_id`, `rating`, `name`, `image_url`, `note`) VALUES (1, 12.50, 12, 'New Gift', 1, 1, 1, 2, 5, 'name', 'image', 'THIS IS A NEW NOTE FOR YOU');
INSERT INTO `gift` (`id`, `price`, `weight_kg`, `description`, `enabled`, `event_id`, `gifter_id`, `receiver_id`, `rating`, `name`, `image_url`, `note`) VALUES (2, 12, 12, 'New Gift', 1, 2, 2, 1, NULL, NULL, NULL, 'THIS IS A NEW NOTE FOR YOU');
INSERT INTO `gift` (`id`, `price`, `weight_kg`, `description`, `enabled`, `event_id`, `gifter_id`, `receiver_id`, `rating`, `name`, `image_url`, `note`) VALUES (3, 12, 12, 'New Gift', 1, 3, 2, 1, NULL, NULL, NULL, 'THIS IS A NEW NOTE FOR YOU');
INSERT INTO `gift` (`id`, `price`, `weight_kg`, `description`, `enabled`, `event_id`, `gifter_id`, `receiver_id`, `rating`, `name`, `image_url`, `note`) VALUES (4, 12, 12, 'New Gift', 1, 1, 1, 2, NULL, NULL, NULL, NULL);
INSERT INTO `gift` (`id`, `price`, `weight_kg`, `description`, `enabled`, `event_id`, `gifter_id`, `receiver_id`, `rating`, `name`, `image_url`, `note`) VALUES (5, 12, 12, 'new', 1, 5, 3, 2, NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `event_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `event_type` (`id`, `name`, `enabled`, `description`, `image_url`) VALUES (1, 'New Type', 1, 'New Event Type', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `giftrdb`;
INSERT INTO `payment` (`id`, `card_number`, `amount`, `exp`, `created_date`, `last_update`, `address_id`, `user_id`, `enabled`) VALUES (1, '1111', 11.11, NULL, NULL, NULL, 1, 1, 1);
INSERT INTO `payment` (`id`, `card_number`, `amount`, `exp`, `created_date`, `last_update`, `address_id`, `user_id`, `enabled`) VALUES (2, '2222', 22.22, NULL, NULL, NULL, 2, 2, 1);

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

