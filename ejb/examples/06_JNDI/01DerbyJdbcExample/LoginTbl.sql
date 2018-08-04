--- Connect
CONNECT 'jdbc:derby:loginDb';

DROP TABLE USERS;


CREATE TABLE USERS (
  USER_ID INTEGER NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1),
  FULLNAME VARCHAR(255),
	EMAIL VARCHAR(255),
	PASSWORD VARCHAR(255),
  USERLEVEL INTEGER WITH DEFAULT 1,
  STARTDATE TIMESTAMP,
  LASTLOGDATE TIMESTAMP,
  PRIMARY KEY (USER_ID)
);

-- 
--  Initial "test" data
-- 

--- Create Users

INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 1', 'user1@example.com', 'password', {ts '2013-01-03 12:14:21.746123'}, {ts '2013-01-04 11:14:21.746123'});
INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 2', 'user2@example.com', 'password', {ts '2013-01-03 13:14:21.746123'}, {ts '2013-01-04 12:14:21.746123'});
INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 3', 'user3@example.com', 'password', {ts '2013-01-03 14:14:21.746123'}, {ts '2013-01-04 13:14:21.746123'});
INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 4', 'user4@example.com', 'password', {ts '2013-01-03 15:14:21.746123'}, {ts '2013-01-04 14:14:21.746123'});
INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 5', 'user5@example.com', 'password', {ts '2013-01-03 16:14:21.746123'}, {ts '2013-01-04 15:14:21.746123'});
INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 6', 'user6@example.com', 'password', {ts '2013-01-03 17:14:21.746123'}, {ts '2013-01-04 16:14:21.746123'});
INSERT INTO USERS (FULLNAME, EMAIL, PASSWORD, STARTDATE, LASTLOGDATE) VALUES ('User 7', 'user7@example.com', 'password', {ts '2013-01-03 18:14:21.746123'}, {ts '2013-01-04 17:14:21.746123'});

exit;


