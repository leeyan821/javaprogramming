# javaprogramming
//영화 예매 시스템
table

CREATE TABLE `admin` (
  `id` varchar(15) NOT NULL,
  `pwd` varchar(15) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `movie` (
  `movieNum` int unsigned NOT NULL AUTO_INCREMENT,
  `movieName` varchar(40) NOT NULL,
  `purchase` int DEFAULT '0',
  PRIMARY KEY (`movieNum`)
);

CREATE TABLE `movielist` (
  `movieListId` int unsigned NOT NULL AUTO_INCREMENT,
  `movieNum` int unsigned NOT NULL,
  `theater` varchar(45) NOT NULL,
  `date` char(10) NOT NULL,
  `time` time NOT NULL,
  `room` int unsigned NOT NULL,
  PRIMARY KEY (`movieListId`),
  KEY `movieNum_idx` (`movieNum`),
  CONSTRAINT `movieNum` FOREIGN KEY (`movieNum`) REFERENCES `movie` (`movieNum`) ON UPDATE CASCADE
);

CREATE TABLE `user` (
  `id` varchar(15) NOT NULL,
  `pwd` varchar(15) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `booking` (
  `bookingNum` int NOT NULL AUTO_INCREMENT,
  `userId` varchar(15) NOT NULL,
  `movieListId` int unsigned NOT NULL,
  bookingDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`bookingNum`),
  foreign key(movieListId) references movieList(movieListId),
  foreign key(userId) references user(id)
);

CREATE TABLE `bookingSeat` (
  `num` int NOT NULL AUTO_INCREMENT,
  `bookingNum` int NOT NULL,
  `seat` varchar(3) NOT NULL,
  foreign key(bookingNum) references booking(bookingNum) ON DELETE CASCADE,
  PRIMARY KEY (`num`)
);
