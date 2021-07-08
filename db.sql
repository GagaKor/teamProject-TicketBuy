#공연테이블
CREATE TABLE perform(
	performId INT(10)UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	categoryCode TINYINT(1) UNSIGNED NOT NULL COMMENT '0=연극/1=뮤지컬/2=콘서트',
	performName CHAR(50) NOT NULL,
	performDetail LONGTEXT,
	performImg VARCHAR(255),
	detailImg VARCHAR(255),
	runTime char(20)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
#출연진테이블
CREATE TABLE actor(
	actorId INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	performId INT(10)UNSIGNED NOT NULL,
	name CHAR(20) NOT NULL,
	part CHAR(20),
	actorCode TINYINT(1) UNSIGNED NOT NULL COMMENT '0=감독/1=주연/2=조연/3=가수',
	FOREIGN KEY (performId) REFERENCES perform(performId) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#공연장테이블
CREATE TABLE place(
	placeId INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	placeName CHAR(50) NOT NULL,
	placeaddr CHAR(100) NOT NULL,
	phonNum CHAR(20) NOT NULL,
	totalSeatNum INT(10) UNSIGNED NOT NULL,
	placeRow INT(3) UNSIGNED NOT NULL,
	placeCol INT(3) UNSIGNED NOT NULL
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#일정 테이블
CREATE TABLE sch (
    schId INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    schDate DATETIME NOT NULL,
    performId INT(10) UNSIGNED NOT NULL,
    placeId INT(10) UNSIGNED NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    FOREIGN KEY (performId) REFERENCES perform(performId) on delete cascade,
    FOREIGN KEY (placeId) REFERENCES place(placeId) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#좌석 테이블
CREATE TABLE seat(
	seatId	 INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	seatName CHAR(10) NOT NULL,
	placeId INT(10) UNSIGNED NOT NULL,
	schId INT(10) UNSIGNED NOT NULL,
	issue TINYINT(1) UNSIGNED NOT NULL DEFAULT 0  COMMENT '0=예매가능/1=예매불가능',
	FOREIGN KEY (placeId) REFERENCES place(placeId) on delete cascade,
	FOREIGN KEY (schId) REFERENCES sch(schId) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;
#티켓
CREATE TABLE ticket(
	ticketId INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	schId INT(10) UNSIGNED NOT NULL,
	placeId INT(10) UNSIGNED NOT NULL,
	seatId INT(10) UNSIGNED NOT NULL,
	ticketPrice INT(10) UNSIGNED NOT NULL,
	FOREIGN KEY (schId) REFERENCES sch(schId) on delete cascade,
	FOREIGN KEY (placeId) REFERENCES place(placeId) on delete cascade,
	FOREIGN KEY (seatId) REFERENCES seat(seatId) on delete cascade
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#티켓 예매 테이블
CREATE TABLE reservation (
	reservationNo INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	idx INT(10) not null,
	ticketId INT(10) UNSIGNED NOT NULL,
	FOREIGN KEY (ticketId) REFERENCES ticket(ticketId) on delete cascade
) AUTO_INCREMENT= 1000000, ENGINE=INNODB DEFAULT CHARSET=utf8;

#회원 테이블
create table `member`(
   idx INT(10) not null primary key auto_increment,
   userid VARCHAR(15) not null unique,
   userpw VARCHAR(20) not null,
   phone VARCHAR(12) not null,
   memberCode TINYINT(1) not null DEFAULT 1 COMMENT '0=관리자/1=일반회원'
   ) ENGINE=INNODB DEFAULT CHARSET=utf8;
