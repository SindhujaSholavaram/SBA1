-- This file contains the db script for
-- Corona Kit Ordering Portal Database

DROP DATABASE ckitDB;

CREATE DATABASE ckitDB;

USE ckitDB;

CREATE TABLE Products (
	id int primary key,
	productName varchar(10) not null,
	cost decimal not null,
	productDescription varchar(20) not null
);

INSERT INTO Products VALUES
(1,"N-95 Mask", 100, "Face Mask"),
(2,"Sanitizer",120,"Dettol Hand Santizer");

CREATE TABLE KitDetails (
	id int not null,
	coronaKitId int primary key,
	productId int not null,
	quantity int not null,
	amount int not null
);