DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS ( 
	userId          varchar(12)		NOT NULL, 
	password		varchar(12)		NOT NULL,
	name			varchar(20)		NOT NULL,
	email			varchar(50),	
  	
	PRIMARY KEY               (userId)
);

INSERT INTO USERS VALUES('admin', 'password', '자바지기', 'admin@slipp.net');

DROP TABLE IF EXISTS QUESTIONS;

CREATE TABLE QUESTIONS (
    questionId      bigint      auto_increment,
    writer          varchar(30)     NOT NULL,
    title           varchar(30)     NOT NULL,
    contents        varchar(30)     NOT NULL,
    createDate      timestamp           NOT NULL,
    countOfAnswer   int,
    PRIMARY KEY      (questionId)
);

DROP TABLE IF EXISTS ANSWERS;

CREATE TABLE ANSWERS (
    answerId        bigint     auto_increment,
    writer          varchar(30)     NOT NULL,
    contents        varchar(30)     NOT NULL,
    createDate      timestamp       NOT NULL,
    questionId      bigint      NOT NULL,
    PRIMARY KEY (answerId)
);