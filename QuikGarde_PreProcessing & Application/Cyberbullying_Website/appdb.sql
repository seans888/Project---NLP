
grant all on user.* to 'root'@'127.0.0.1' identified by 'root';

CREATE TABLE statement( 
pid int(60) NOT NULL AUTO_INCREMENT, 
addentry VARCHAR(200) NOT NULL,
author VARCHAR(60) NOT NULL, 
title VARCHAR(200) NOT NULL, 
PRIMARY KEY(pid));


SELECT * FROM statement;


CREATE TABLE users( 
uid int(60) NOT NULL AUTO_INCREMENT, 
username VARCHAR(50) NOT NULL,
password VARCHAR(50) NOT NULL,
fullname VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
gender VARCHAR(50) NOT NULL,
bday VARCHAR(50) NOT NULL,
PRIMARY KEY(uid));

SELECT * FROM users;

