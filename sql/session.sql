CREATE DATABASE IF NOT EXISTS social_login_buddy;

USE social_login_buddy;


DROP TABLE IF EXISTS `session`;

# todo verify string length
CREATE TABLE `session` (
  `buddy_session` VARCHAR(500) NOT NULL PRIMARY KEY,
  `client_session` VARCHAR(400) NOT NULL,
  `code` VARCHAR(400),
  `access_token` VARCHAR(400),
  `id_token` VARCHAR(400)
);





