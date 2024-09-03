SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- version 1.0
 
DROP DATABASE IF EXISTS pco;
CREATE DATABASE pco;
USE pco;

SELECT 'Create the tables';


-- ***************************************
-- Table Tops
-- ***************************************
DROP TABLE IF EXISTS tops ;

CREATE TABLE tops (
	SKU VARCHAR(50) NOT NULL,
	sleeves VARCHAR(15),
	thermal BOOLEAN,
	aero BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Bottoms
-- ***************************************
DROP TABLE IF EXISTS bottoms ;

CREATE TABLE bottoms (
	SKU VARCHAR(50) NOT NULL,
	chamois BOOLEAN,
	thermal BOOLEAN,
	aero BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table SkinSuit
-- ***************************************
DROP TABLE IF EXISTS skinsuit ;

CREATE TABLE skinsuit (
	SKU VARCHAR(50) NOT NULL,
	sleeves VARCHAR(15),
	thermal BOOLEAN,
	aero BOOLEAN,
	timetrial BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Components
-- ***************************************
DROP TABLE IF EXISTS components ;

CREATE TABLE components (
	SKU VARCHAR(50) NOT NULL,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table BaseLayer
-- ***************************************
DROP TABLE IF EXISTS baselayer ;

CREATE TABLE baselayer (
	SKU VARCHAR(50) NOT NULL,
	sleeves VARCHAR(25),
	thermal BOOLEAN,
	merino BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Warmer
-- ***************************************
DROP TABLE IF EXISTS warmer ;

CREATE TABLE warmer (
	SKU VARCHAR(50) NOT NULL,
	waterproof BOOLEAN,
	bodypart VARCHAR(15),
	thermal BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Footwear
-- ***************************************
DROP TABLE IF EXISTS footwear ;

CREATE TABLE footwear (
	SKU VARCHAR(50) NOT NULL,
	discipline varchar(15),
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Gloves
-- ***************************************
DROP TABLE IF EXISTS gloves ;

CREATE TABLE gloves (
	SKU VARCHAR(50) NOT NULL,
	waterproof BOOLEAN,
	neoprene BOOLEAN,
	thermal BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Headwear
-- ***************************************
DROP TABLE IF EXISTS headwear ;

CREATE TABLE headwear (
	SKU VARCHAR(50) NOT NULL,
	waterproof BOOLEAN,
	visor BOOLEAN,
	thermal BOOLEAN,
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table HealthNutrition
-- ***************************************
DROP TABLE IF EXISTS healthnutrition ;

CREATE TABLE healthnutrition (
	SKU VARCHAR(50) NOT NULL,
	expirationDate DATETIME,
	flavour VARCHAR(15),
	PRIMARY KEY (SKU),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table Product
-- ***************************************
DROP TABLE IF EXISTS product ;

CREATE TABLE product (
	SKU VARCHAR(50) NOT NULL,
    title VARCHAR(100),
	brandCode bigint NOT NULL,
	teamCode bigint,
    boxNumber VARCHAR(15),
    coCode bigint,
	price DECIMAL(6,2),
	activeProduct BOOLEAN,
	colour VARCHAR(15),
    size VARCHAR(25),
    quantity INTEGER,
    prodCondition VARCHAR(20),
    vendorCode VARCHAR(20),
	PRIMARY KEY (SKU)
);

-- ***************************************
-- Table Brand
-- ***************************************
DROP TABLE IF EXISTS brand ;

CREATE TABLE brand (
	brandCode bigint NOT NULL auto_increment,
    brandName VARCHAR(50),
	nationality VARCHAR(20),
	PRIMARY KEY (brandCode)
);

ALTER TABLE product
	ADD CONSTRAINT fk_brandCode
    FOREIGN KEY (brandCode)
    REFERENCES brand (brandCode);

-- ***************************************
-- Table Team
-- ***************************************
DROP TABLE IF EXISTS team ;

CREATE TABLE team (
	teamCode bigint NOT NULL auto_increment,
    teamName VARCHAR(50),
	nationality VARCHAR(20),
	PRIMARY KEY (teamCode)
);

ALTER TABLE product
	ADD CONSTRAINT fk_teamCode
    FOREIGN KEY (teamCode)
    REFERENCES team (teamCode);
	
-- ***************************************
-- Table Box
-- ***************************************
DROP TABLE IF EXISTS box ;

CREATE TABLE box (
	boxNumber VARCHAR(15) NOT NULL,
	quantityActive INTEGER,
    quantityDraft INTEGER,
    quantityTotal INTEGER,
	allActive BOOLEAN,
	PRIMARY KEY (boxNumber)
);

ALTER TABLE product
	ADD CONSTRAINT fk_boxNumber
    FOREIGN KEY (boxNumber)
    REFERENCES box (boxNumber);

-- ***************************************
-- Table ProductLocationChange
-- ***************************************
DROP TABLE IF EXISTS productlocationchanges ;

CREATE TABLE productlocationchanges (
	boxMovementCode bigint NOT NULL auto_increment,
	boxNumber VARCHAR(15) NOT NULL,
	SKU VARCHAR(15) NOT NULL,
	oldBoxNumber VARCHAR(15),
	dateOfMovement DATETIME,
	PRIMARY KEY (boxMovementCode),
	FOREIGN KEY (boxNumber) REFERENCES box (boxNumber),
	FOREIGN KEY (SKU) REFERENCES product (SKU)
);

-- ***************************************
-- Table ClientOrder
-- ***************************************
DROP TABLE IF EXISTS clientorder ;

CREATE TABLE clientorder (
	coCode bigint NOT NULL auto_increment,
	dateOfOrder DATETIME,
	transCode bigint,
	userCode bigint,
	shipped BOOLEAN,
    totalOrderValue DECIMAL(6,2),
	PRIMARY KEY (coCode)
);

ALTER TABLE product
	ADD CONSTRAINT fk_clientOrder
    FOREIGN KEY (coCode)
    REFERENCES clientorder (coCode);

-- ***************************************
-- Table TransportCompany
-- ***************************************
DROP TABLE IF EXISTS transportcompany ;

CREATE TABLE transportcompany (
	transCode bigint NOT NULL,
	PRIMARY KEY (transCode)
);

ALTER TABLE clientorder
	ADD CONSTRAINT fk_transCode
    FOREIGN KEY (transCode)
    REFERENCES transportcompany (transCode);
	
-- ***************************************
-- Table Country
-- ***************************************
DROP TABLE IF EXISTS country ;

CREATE TABLE country (
	countryCode bigint NOT NULL auto_increment,
    countryName VARCHAR(50),
	PRIMARY KEY (countryCode)
);

-- ***************************************
-- Table AppUser
-- ***************************************
DROP TABLE IF EXISTS appuser ;

CREATE TABLE appuser (
	userCode bigint NOT NULL auto_increment,
	appUsername VARCHAR(50),
	appAddress VARCHAR(100),
    emailAddress VARCHAR(100),
	countryCode bigint,
	PRIMARY KEY (userCode),
	FOREIGN KEY (countryCode) REFERENCES country (countryCode)
);

ALTER TABLE clientorder
	ADD CONSTRAINT fk_userCode
    FOREIGN KEY (userCode)
    REFERENCES appuser (userCode);

-- ***************************************
-- Table Vendor
-- ***************************************
DROP TABLE IF EXISTS vendor ;

CREATE TABLE vendor (
	userCode bigint NOT NULL,
    vendorCode VARCHAR(50),
	shipments INTEGER,
	totalPayedTo DECIMAL(10,2),
	totalValueAchieved DECIMAL(10,2),
	PRIMARY KEY (userCode),
	FOREIGN KEY (userCode) REFERENCES appuser (userCode)
);

-- ***************************************
-- Table Employee
-- ***************************************
DROP TABLE IF EXISTS employee ;

CREATE TABLE employee (
	userCode bigint NOT NULL,
	PRIMARY KEY (userCode),
	FOREIGN KEY (userCode) REFERENCES appuser (userCode)
);

-- ***************************************
-- Table client
-- ***************************************
DROP TABLE IF EXISTS client ;

CREATE TABLE client (
	userCode bigint NOT NULL,
	repeatedTransactions INTEGER,
	totalValueAchieved DECIMAL(10,2),
    coCode bigint,
	PRIMARY KEY (userCode),
	FOREIGN KEY (userCode) REFERENCES appuser (userCode),
    FOREIGN KEY (coCode) REFERENCES clientorder (coCode)
);

-- ***************************************
-- Table manytomany TeamVendorMapping
-- ***************************************
DROP TABLE IF EXISTS teamvendormapping ;

CREATE TABLE teamvendormapping (
	userCode bigint NOT NULL,
	teamCode bigint NOT NULL,
	PRIMARY KEY (userCode, teamCode),
	FOREIGN KEY (userCode) REFERENCES appuser (userCode),
	FOREIGN KEY (teamCode) REFERENCES team (teamCode)
    );
    
