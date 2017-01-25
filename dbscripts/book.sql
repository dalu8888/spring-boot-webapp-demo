
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(20) DEFAULT NULL,
  `category` char(1) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `createdtime` timestamp DEFAULT NOW(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
