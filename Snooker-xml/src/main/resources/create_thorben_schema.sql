##CREATE database thorben;

CREATE TABLE `user` (
  `user_id` int NOT NULL,
  `user_firstname` varchar(225) NOT NULL,
  `user_lastname` varchar(225) NOT NULL,
  `user_password` varchar(225) DEFAULT NULL,
  `user_login` varchar(225) NOT NULL,
  `creation_date` bigint NOT NULL,
  PRIMARY KEY (`user_login`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `news` (
  `news_id` int NOT NULL,
  `news_title` varchar(225) NOT NULL,
  `news_teaser` text,
  `news_image` blob,
  `change_date` bigint DEFAULT NULL,
  `creation_date` bigint NOT NULL,
  `author_id` int DEFAULT NULL,
  `author_login` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`news_id`),
  KEY `FK_AUTHOR_NEWS` (`author_login`,`author_id`),
  CONSTRAINT `FK_AUTHOR_NEWS` FOREIGN KEY (`author_login`, `author_id`) REFERENCES `user` (`user_login`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `news_text` (
  `news_id` int NOT NULL AUTO_INCREMENT,
  `news_text` mediumtext,
  PRIMARY KEY (`news_id`),
  CONSTRAINT `news_text_ibfk_1` FOREIGN KEY (`news_id`) REFERENCES `news` (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `event` (
  `id` int NOT NULL,
  `title` mediumtext NOT NULL,
  `description` mediumtext NOT NULL,
  `date` bigint NOT NULL,
  `teaser` mediumtext NOT NULL,
  `creation_date` bigint NOT NULL,
  `change_date` bigint DEFAULT NULL,
  `author_login` varchar(225) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_AUTHOR_EVENT` (`author_login`,`author_id`),
  CONSTRAINT `FK_AUTHOR_EVENT` FOREIGN KEY (`author_login`, `author_id`) REFERENCES `user` (`user_login`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `event_text` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_text` mediumtext,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_event_text` FOREIGN KEY (`id`) REFERENCES `event` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `error_log` (
  `error_id` int NOT NULL AUTO_INCREMENT,
  `error_title` mediumtext NOT NULL,
  `error_description` mediumtext NOT NULL,
  `creation_date` bigint NOT NULL,
  PRIMARY KEY (`error_id`)
) ENGINE=InnoDB AUTO_INCREMENT=461 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `autoupdate_db` (
  `updatenumber` int unsigned NOT NULL,
  `specification` varchar(50) NOT NULL,
  PRIMARY KEY (`updatenumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ob3_title` (
	`id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(50) NOT NULL,
    `description` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
);

Create Table `ob3_title_definition`(
	`ob3_number` int NOT NULL,
    `title_id` int NOT NULL,
    PRIMARY KEY (`ob3_number`, `title_id`),
    CONSTRAINT `FK_ob3_title_definition` FOREIGN KEY (`title_id`) REFERENCES `ob3_title` (`id`)
);


INSERT INTO user SELECT * FROM snooker.user;
SELECT * FROM user;
INSERT IGNORE news SELECT * FROM snooker.news;
SELECT * FROM news;
INSERT IGNORE news_text SELECT * FROM snooker.news_text;
SELECT * FROM news_text;
INSERT INTO event SELECT * FROM snooker.termine;
SELECT * FROM event;
