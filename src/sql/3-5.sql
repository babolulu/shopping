
create database spring;
CREATE USER 'spring'@'localhost' IDENTIFIED BY 'spring';
grant all on spring.* to 'spring'@'localhost' identified by 'spring';
FLUSH PRIVILEGES;

USE SPRING;

Drop table sale;
Drop table item;
Drop table sale_line;
Drop table user_account;

CREATE TABLE IF NOT EXISTS USER_ACCOUNT(
	USER_ID VARCHAR(20),
	USER_NAME VARCHAR(20),
	PASSWORD VARCHAR(20),
	POSTCODE VARCHAR(8),
	ADDRESS VARCHAR(50),
	EMAIL VARCHAR(50),
	JOB VARCHAR(30),
    BIRTHDAY DATE,
	PRIMARY KEY (USER_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS ITEM(
	ITEM_ID INTEGER(5) AUTO_INCREMENT,
	ITEM_NAME VARCHAR(20),
	PRICE INTEGER(6),
	DESCRIPTION VARCHAR(50),
	PICTURE_URL VARCHAR(20),
	PRIMARY KEY (ITEM_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS SALE(
	SALE_ID INTEGER(10),
	USER_ID VARCHAR(20),
	UPDATE_TIME TIMESTAMP,
	PRIMARY KEY (SALE_ID,USER_ID),
	FOREIGN KEY (USER_ID) REFERENCES USER_ACCOUNT (USER_ID) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS SALE_LINE(
	SALE_ID INTEGER(10),
	SALE_LINE_ID INTEGER(3),
	ITEM_ID INTEGER(5),
	QUANTITY INTEGER(1),
	UPDATE_TIME TIMESTAMP,
	PRIMARY KEY (SALE_ID,SALE_LINE_ID),
	FOREIGN KEY (SALE_ID) REFERENCES SALE (SALE_ID),
	FOREIGN KEY (ITEM_ID) REFERENCES ITEM (ITEM_ID)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

// board 관련 2개 테이블 추가 부분
CREATE TABLE `jmboard` (
  `idx` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `writer` varchar(50) NOT NULL,
  `subject` varchar(150) NOT NULL,
  `content` text NOT NULL,
  `hitcount` int(8) NOT NULL,
  `recommendcount` int(8) NOT NULL,
  `writedate` date NOT NULL,
  `writerid` varchar(50) NOT NULL,
  `filename` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `jmboard_comment` (
  `idx` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `writer` varchar(50) NOT NULL,
  `content` varchar(500) NOT NULL,
  `writedate` date NOT NULL,
  `linkedarticlenum` int(8) DEFAULT NULL,
  `writerid` varchar(50) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DELETE FROM ITEM;
LOAD DATA LOCAL INFILE 'c:/item.csv' 
INTO TABLE ITEM 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n';

DELETE FROM USER_ACCOUNT;
LOAD DATA LOCAL INFILE 'c:/user.csv' 
INTO TABLE USER_ACCOUNT 
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\r\n';
QUIT
