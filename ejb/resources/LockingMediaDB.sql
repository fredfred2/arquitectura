-- Clear out the old tables
DROP TABLE MediaInfo;
DROP TABLE MediaContent;

--
-- This table stores the meta data for Media Items.
--
CREATE TABLE MediaInfo (
-- PRIMARY KEY --
   id           VARCHAR(50) PRIMARY KEY,
-- DATA FIELDS --
   title        VARCHAR (50),
   mediaDate    DATE,
   tags         VARCHAR (100),
   version      INT DEFAULT 0
);

--
-- This table stores the contents of an actual Media item
--
CREATE TABLE MediaContent (
-- PRIMARY KEY --
   id          VARCHAR(50) PRIMARY KEY,  
-- DATA FIELDS --
   media       BLOB(1G)  
);