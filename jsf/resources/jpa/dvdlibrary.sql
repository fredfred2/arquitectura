
--
-- This table represents the DVD Items
--
CREATE TABLE Item (
-- PRIMARY KEY --
   id          INTEGER PRIMARY KEY,  
-- DATA FIELDS --
   
   title       VARCHAR(100) NOT NULL, 
   releaseyear        CHAR(4) NOT NULL,     
   genre       VARCHAR(20) NOT NULL  
);

-- Data template SQL script.
-- This file is used by Ant to create a user-specific data.sql file
-- that is then imported into the database

INSERT INTO Item VALUES(1,'The Godfather','1972','Drama');

INSERT INTO Item VALUES(2,'The Shawshank Redemption','1994','Drama');

INSERT INTO Item VALUES(3,'The Godfather: Part II','1974','Drama');

INSERT INTO Item VALUES(4,'The Lord of the Rings: The Return of the King','2003','Fantasy');

INSERT INTO Item VALUES(5,'The Lord of the Rings: The Two Towers','2002','Fantasy');

INSERT INTO Item VALUES(6,'Shindler''s List','1998','Drama');

INSERT INTO Item VALUES(7,'Shichinin no samurai','1954','Action');

INSERT INTO Item VALUES(8,'Casablanca','1942','Drama');

INSERT INTO Item VALUES(9,'The Lord of the Rings: The Fellowship of the Ring','2001','Fantasy');

INSERT INTO Item VALUES(10,'Star Wars','1977','Sci-Fi');

INSERT INTO Item VALUES(11,'Citizen Kane','1941','Mystery');

INSERT INTO Item VALUES(12,'One Flew Over the Cuckoo''s Nest','1975','Drama');

INSERT INTO Item VALUES(13,'Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb','1964','Comedy');

INSERT INTO Item VALUES(14,'Rear Window','1954','Thriller');

INSERT INTO Item VALUES(15,'Star Wars: Episode V - The Empire Strikes Back','1980','Sci-Fi');

INSERT INTO Item VALUES(16,'Raiders of the Lost Ark','1981','Action');

INSERT INTO Item VALUES(17,'The Usual Suspects','1995','Mystery');

INSERT INTO Item VALUES(18,'Pulp Fiction','1994','Crime');

INSERT INTO Item VALUES(19,'Memento','2000','Mystery');


