CREATE DATABASE BANK;
CREATE TABLE ACCOUNTS(
ACCOUNT_NUMBER BIGINT PRIMARY KEY NOT NULL UNIQUE,
FULL_NAME VARCHAR(200),
EMAIL VARCHAR(200),
BALANCE DOUBLE,
PIN CHAR(4)
);
CREATE TABLE USER
(
FULL_NAME VARCHAR(200),
EMAIL VARCHAR(200),
PASSWORD VARCHAR(45)
);
CREATE TABLE TRANSCATION
(
DEBIT_ACCOUNT BIGINT,
CREDIT_ACCOUNT BIGINT,
AMOUNT DOUBLE);
