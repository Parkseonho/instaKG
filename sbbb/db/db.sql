# DB 생성
DROP DATABASE IF EXISTS sbb;
CREATE DATABASE sbb;
USE sbb;

# 회원 테이블 생성
CREATE TABLE USER (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reg_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    email VARCHAR(100) NOT NULL,
    PASSWORD VARCHAR(150) NOT NULL,
    NAME CHAR(50) NOT NULL
);



# 회원 데이터 생성
INSERT INTO USER
SET reg_date = NOW(),
update_date = NOW(),
email = 'use1@test.com',
PASSWORD = '1234',
NAME = 'user1';

INSERT INTO USER
SET reg_date = NOW(),
update_date = NOW(),
email = 'use2@test.com',
PASSWORD = '1234',
NAME = 'user2';

INSERT INTO USER
SET reg_date = NOW(),
update_date = NOW(),
email = 'use3@test.com',
PASSWORD = '1234',
NAME = 'user3';

# 게시물 테이블 생성
CREATE TABLE article (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reg_date DATETIME NOT NULL,
    update_date DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    BODY TEXT NOT NULL,
    user_id BIGINT UNSIGNED NOT NULL
);

# 게시물 데이터 생성
INSERT INTO article
SET reg_date = NOW(),
update_date = NOW(),
title = "title 1",
BODY = "body 1",
user_id = 1;

INSERT INTO article
SET reg_date = NOW(),
update_date = NOW(),
title = 'title 2',
BODY = 'body 2',
user_id = 2;

INSERT INTO article
SET reg_date = NOW(),
update_date = NOW(),
title = 'title 3',
BODY = 'body 3',
user_id = 3;

CREATE TABLE Question (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME DEFAULT NOW(),
    hit INT(11) UNSIGNED,
    reply_like VARCHAR(10) NOT NULL,
    on_off TINYINT DEFAULT 0,
    answer_id INT(11) UNSIGNED
);


CREATE TABLE Answer (
    id INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    content TEXT NOT NULL,
    create_date DATETIME NOT NULL,
    modify_date DATETIME DEFAULT NOW(),
    question_id INT(11) UNSIGNED NOT NULL,
    reply_like VARCHAR(10) NOT NULL
);

CREATE TABLE files(
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `filename` TEXT,
    question_id BIGINT UNSIGNED NOT NULL
);



SELECT * FROM Question;
SELECT * FROM Answer;
SELECT * FROM files;





