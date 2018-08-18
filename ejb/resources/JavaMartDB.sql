-- Duke's JavaMart Data Model

-- Clear out any old tables
DROP TABLE ProductSales;
DROP TABLE Inventory;
DROP TABLE Product;
DROP TABLE Sales;

-- 
-- Product catalog for Duke's JavaMart
--
CREATE TABLE Product (
    -- Primary Key --
    Product_ID      VARCHAR(15),
    -- Data Fields --
    Prod_Name       VARCHAR(40),
    Price           DOUBLE,
    Prod_Desc       VARCHAR(200),
    PRIMARY KEY (Product_ID)
);

--
-- Inventory record of product
--
CREATE TABLE Inventory (
    -- Foreign Key --
    Product_ID      VARCHAR(15),
    -- Data Fields --
    Available       INTEGER,
    CONSTRAINT Inventory_fk FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID)
);

-- 
-- Sales of products for Duke's JavaMart
--
CREATE TABLE Sales (
    -- Primary Key --
    Sales_ID	INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY,
    -- Data Fields --
    Date_Sold 	DATE,
    Total_Sale	DOUBLE,
    PRIMARY KEY (Sales_ID)
);

--
-- Join table of products and sales id
--
CREATE TABLE ProductSales (
    -- Foreign Key --
    Sales_ID	INTEGER,
    Product_ID	VARCHAR(15),
    Quantity	INTEGER,
    CONSTRAINT Sales_ID_fk FOREIGN KEY (Sales_ID) REFERENCES Sales (Sales_ID),
    CONSTRAINT Product_ID_fk FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID)
);

--
-- Populate the tables with data
--

insert into Product values ('KEYRING0111', 'Duke Key Ring', 15.95, 'Java Oracle Duke Waving keyring with leather fob, multicolor, plastic');
insert into Product values ('JAVAONE2015-XS', 'JavaOne 2015 T-Shirt', 39.95, 'JavaOne 2015 Shirt (XS) designed by the Oracle Java design product team - cool!');
insert into Product values ('JAVAONE2015-S', 'JavaOne 2015 T-Shirt', 39.95, 'JavaOne 2015 Shirt (S) designed by the Oracle Java design product team - cool!');
insert into Product values ('JAVAONE2015-M', 'JavaOne 2015 T-Shirt', 39.95, 'JavaOne 2015 Shirt (M) designed by the Oracle Java design product team - cool!');
insert into Product values ('JAVAONE2015-L', 'JavaOne 2015 T-Shirt', 39.95, 'JavaOne 2015 Shirt (L) designed by the Oracle Java design product team - cool!');
insert into Product values ('JAVAONE2015-XL', 'JavaOne 2015 T-Shirt', 39.95, 'JavaOne 2015 Shirt (XL) designed by the Oracle Java design product team - cool!');
insert into Product values ('JAVAHAT', 'Java Logo Baseball Cap', 24.95, 'Java logo baseball cap - grey with red and black logo - one size fits all');
insert into Product values ('DUKEMUG', 'Duke Coffee Mug', 13.95, 'Duke coffee/tea mug - 4 inches tall, glass with etched Duke logo');

insert into Inventory values ('KEYRING0111', 100);
insert into Inventory values ('JAVAONE2015-XS', 13);
insert into Inventory values ('JAVAONE2015-S', 5);
insert into Inventory values ('JAVAONE2015-M', 12);
insert into Inventory values ('JAVAONE2015-L', 7);
insert into Inventory values ('JAVAONE2015-XL', 23);
insert into Inventory values ('JAVAHAT', 11);
insert into Inventory values ('DUKEMUG', 2);