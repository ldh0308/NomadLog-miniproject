

-- 대륙 TABLE SQL
CREATE TABLE `continents` (
                              `continent_Id` INT(11) NOT NULL AUTO_INCREMENT,
                              `continent_name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
                              PRIMARY KEY (`continent_Id`) USING BTREE
)
    COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=4

-- 국가 TABLE SQL
CREATE TABLE `countries` (
                             `country_id` INT(11) NOT NULL AUTO_INCREMENT,
                             `continent_id` INT(11) NULL DEFAULT NULL,
                             `country_name` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
                             PRIMARY KEY (`country_id`) USING BTREE,
                             INDEX `continent_Id` (`continent_id`) USING BTREE,
                             CONSTRAINT `continent_Id` FOREIGN KEY (`continent_id`) REFERENCES `continents` (`continent_Id`) ON UPDATE CASCADE ON DELETE CASCADE
)
    COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=3
;
-- 해시태그 SQL
CREATE TABLE `hashtag` (
                           `hashtag_id` INT(11) NOT NULL AUTO_INCREMENT,
                           `bbs_id` INT(11) NULL DEFAULT NULL,
                           `content` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb3_bin',
                           PRIMARY KEY (`hashtag_id`) USING BTREE,
                           INDEX `bbs_id` (`bbs_id`) USING BTREE,
                           CONSTRAINT `bbs_id` FOREIGN KEY (`bbs_id`) REFERENCES `bbs` (`bbs_id`) ON UPDATE CASCADE ON DELETE CASCADE
)
    COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=12
;

-- 게시판 SQL
CREATE TABLE `bbs` (
                       `bbs_id` INT(11) NOT NULL AUTO_INCREMENT,
                       `title` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_general_ci',
                       `contents` VARCHAR(100) NOT NULL COLLATE 'utf8mb4_general_ci',
                       `created_at` TIMESTAMP NULL DEFAULT current_timestamp(),
                       `modify_at` TIMESTAMP NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
                       `view_cnt` INT(11) NULL DEFAULT '0',
                       `like_cnt` INT(11) NULL DEFAULT '0',
                       `tag_id` INT(11) NULL DEFAULT '0',
                       `is_deleted` TINYINT(1) NULL DEFAULT '0',
                       `member_code` INT(11) NULL DEFAULT '0',
                       `country_id` INT(11) NULL DEFAULT '1',
                       PRIMARY KEY (`bbs_id`) USING BTREE,
                       INDEX `country_id` (`country_id`) USING BTREE,
                       CONSTRAINT `country_id` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`) ON UPDATE CASCADE ON DELETE CASCADE
)
    COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=51
;
