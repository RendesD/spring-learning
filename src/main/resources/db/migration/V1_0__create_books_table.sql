CREATE TABLE IF NOT EXISTS `books` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `title` varchar(50) NOT NULL UNIQUE,
    `author` varchar(50) NOT NULL
)

