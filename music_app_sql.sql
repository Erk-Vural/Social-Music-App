-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema music_app
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema music_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `music_app` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `music_app` ;

-- -----------------------------------------------------
-- Table `music_app`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`genre` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`artists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`artists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `country` VARCHAR(20) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`artist_song`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`artist_song` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `artist1_id` INT NOT NULL,
  `artist2_id` INT NULL DEFAULT NULL,
  `artist3_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `artist1_id` (`artist1_id` ASC) VISIBLE,
  INDEX `artist2_id` (`artist2_id` ASC) VISIBLE,
  INDEX `artist3_id` (`artist3_id` ASC) VISIBLE,
  CONSTRAINT `artist_song_ibfk_1`
    FOREIGN KEY (`artist1_id`)
    REFERENCES `music_app`.`artists` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `artist_song_ibfk_2`
    FOREIGN KEY (`artist2_id`)
    REFERENCES `music_app`.`artists` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `artist_song_ibfk_3`
    FOREIGN KEY (`artist3_id`)
    REFERENCES `music_app`.`artists` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`albums`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`albums` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre_id` INT NULL DEFAULT NULL,
  `artist_song_id` INT NULL DEFAULT NULL,
  `name` VARCHAR(30) NOT NULL,
  `release_date` INT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `genre_id` (`genre_id` ASC) VISIBLE,
  INDEX `artist_song_id` (`artist_song_id` ASC) VISIBLE,
  CONSTRAINT `albums_ibfk_1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `music_app`.`genre` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `albums_ibfk_2`
    FOREIGN KEY (`artist_song_id`)
    REFERENCES `music_app`.`artist_song` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`songs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`songs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `album_id` INT NULL DEFAULT NULL,
  `genre_id` INT NULL DEFAULT NULL,
  `artist_song_id` INT NULL DEFAULT NULL,
  `name` VARCHAR(20) NOT NULL,
  `release_date` INT NOT NULL,
  `lenght` FLOAT NOT NULL,
  `plays` INT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `album_id` (`album_id` ASC) VISIBLE,
  INDEX `genre_id` (`genre_id` ASC) VISIBLE,
  INDEX `artist_song_id` (`artist_song_id` ASC) VISIBLE,
  CONSTRAINT `songs_ibfk_1`
    FOREIGN KEY (`album_id`)
    REFERENCES `music_app`.`albums` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `songs_ibfk_2`
    FOREIGN KEY (`genre_id`)
    REFERENCES `music_app`.`genre` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `songs_ibfk_3`
    FOREIGN KEY (`artist_song_id`)
    REFERENCES `music_app`.`artist_song` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `is_premium` TINYINT(1) NOT NULL,
  `is_payed` TINYINT(1) NOT NULL,
  `country` VARCHAR(20) NOT NULL DEFAULT _utf8mb4'default country',
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`playlist_songs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`playlist_songs` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `playlist_id` INT NULL DEFAULT NULL,
  `song_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `playlist_id` (`playlist_id` ASC) VISIBLE,
  INDEX `song_id` (`song_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `playlist_songs_ibfk_1`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `music_app`.`playlist` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `playlist_songs_ibfk_2`
    FOREIGN KEY (`song_id`)
    REFERENCES `music_app`.`songs` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `playlist_songs_ibfk_3`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_app`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 32
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`user_album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`user_album` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `album_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `album_id` (`album_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_album_ibfk_1`
    FOREIGN KEY (`album_id`)
    REFERENCES `music_app`.`albums` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `user_album_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_app`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`user_artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`user_artist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `artist_song_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `artist_song_id` (`artist_song_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_artist_ibfk_1`
    FOREIGN KEY (`artist_song_id`)
    REFERENCES `music_app`.`artist_song` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `user_artist_ibfk_2`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_app`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`user_followers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`user_followers` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `follower_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  INDEX `follower_id` (`follower_id` ASC) VISIBLE,
  CONSTRAINT `user_followers_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_app`.`users` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `user_followers_ibfk_2`
    FOREIGN KEY (`follower_id`)
    REFERENCES `music_app`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `music_app`.`user_playlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `music_app`.`user_playlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `genre_id` INT NULL DEFAULT NULL,
  `playlist_id` INT NULL DEFAULT NULL,
  `user_id` INT NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `genre_id` (`genre_id` ASC) VISIBLE,
  INDEX `playlist_id` (`playlist_id` ASC) VISIBLE,
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `user_playlist_ibfk_1`
    FOREIGN KEY (`genre_id`)
    REFERENCES `music_app`.`genre` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `user_playlist_ibfk_2`
    FOREIGN KEY (`playlist_id`)
    REFERENCES `music_app`.`playlist` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `user_playlist_ibfk_3`
    FOREIGN KEY (`user_id`)
    REFERENCES `music_app`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
