/*
Group 44
	Tarun Jayadevan (IIT2020003)
	Jerald Kannath (IIT2020004)
	Rudranks Chandra Sarma (IIB2020003)
	Aniruddh Sharma (IIB2020502)
	Sahil Pote (IIT2020240)
*/
 
-- CREATE DATABASE store;
-- USE store;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;


-- LOGIN TABLE --

CREATE TABLE `login`(
	`Customer_Name` varchar(50) NOT NULL,
    `Customer_Password` varchar(50) NOT NULL
);
INSERT INTO `login`(`Customer_Name`,`Customer_Password`) VALUES
('admin','admin');
INSERT INTO `login`(`Customer_Name`,`Customer_Password`) VALUES
('John','john');
INSERT INTO `login`(`Customer_Name`,`Customer_Password`) VALUES
('Ash','ash');
INSERT INTO `login`(`Customer_Name`,`Customer_Password`) VALUES
('Eren','eren');
INSERT INTO `login`(`Customer_Name`,`Customer_Password`) VALUES
('Ted','ted');
INSERT INTO `login`(`Customer_Name`,`Customer_Password`) VALUES
('Pat','pat');


-- CUSTOMER TABLE --

CREATE TABLE `customertable` (
  `Id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `CustomerName` varchar(50) NOT NULL,
  `CustomerContact` varchar(50) NOT NULL UNIQUE,
  `CustomerAddress` varchar(150) NOT NULL
);
ALTER TABLE `customertable` AUTO_INCREMENT=6;

INSERT INTO `customertable` (`Id`, `CustomerName`, `CustomerContact`, `CustomerAddress`) VALUES
(1, 'John', '9999999999', 'abc');
INSERT INTO `customertable` (`Id`, `CustomerName`, `CustomerContact`, `CustomerAddress`) VALUES
(2, 'Eren', '9999999998', 'def');
INSERT INTO `customertable` (`Id`, `CustomerName`, `CustomerContact`, `CustomerAddress`) VALUES
(3, 'Ash', '9999999997', 'ghi');
INSERT INTO `customertable` (`Id`, `CustomerName`, `CustomerContact`, `CustomerAddress`) VALUES
(4, 'Ted', '9999999996', 'jkl');
INSERT INTO `customertable` (`Id`, `CustomerName`, `CustomerContact`, `CustomerAddress`) VALUES
(5, 'Pat', '9999999995', 'mno');

-- INVENTORY TABLE --

CREATE TABLE `inventorytable` (
  `Id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ProductName` varchar(50) NOT NULL,
  `BatchNumber` varchar(50) NOT NULL,
  `Quantity` varchar(50) NOT NULL,
  `PurchaseDate` date DEFAULT NULL,
  `PurchaseRate` double NOT NULL DEFAULT '0',
  `SellingRate` double NOT NULL DEFAULT '0',
  `ExpiryDate` date NOT NULL
);
ALTER TABLE `inventorytable` AUTO_INCREMENT=11;

INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(1, 'Jam', '123', '300', '2022-02-25', 25, 30, '2022-05-20');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(2, 'Soap', '789', '200', '2022-03-07', 15, 20, '2022-04-27');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(3, 'Pen', '450', '696', '2022-01-01', 5, 10, '2023-09-07');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(4, 'Oil', '321', '500', '2022-04-22', 35, 40, '2022-06-03');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(5, 'Notebook', '777', '450', '2021-03-09', 40, 50, '2023-02-03');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(6, 'Butter', '231', '200', '2022-03-01', 15, 20, '2022-05-17');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(7, 'Pepsi', '786', '250', '2022-02-21', 15, 20, '2022-06-11');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(8, 'Cake', '678', '270', '2022-01-23', 10, 15, '2022-05-19');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(9, 'Oreo', '432', '299', '2022-04-21', 7, 10, '2022-05-20');
INSERT INTO `inventorytable` (`Id`, `ProductName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(10, 'Battery', '420', '350', '2022-03-22', 5, 10, '2022-06-17');

-- PURCHASE TABLE --

CREATE TABLE `purchasetable` (
  `Id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `ProductName` varchar(50) NOT NULL,
  `SellerName` varchar(100) NOT NULL,
  `BatchNumber` varchar(50) NOT NULL,
  `Quantity` varchar(50) NOT NULL,
  `PurchaseDate` date DEFAULT NULL,
  `PurchaseRate` double NOT NULL DEFAULT '0',
  `SellingRate` double NOT NULL DEFAULT '0',
  `ExpiryDate` date NOT NULL
);
ALTER TABLE `purchasetable` AUTO_INCREMENT=11;

INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(1, 'Jam', 'Jim', '123', '300', '2022-02-25', 25, 30, '2022-05-20');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(2, 'Soap', 'Jim', '789', '200', '2022-03-07', 15, 20, '2022-04-27');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(3, 'Pen', 'Tony', '450', '696', '2022-01-01', 5, 10, '2023-09-07');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(4, 'Oil', 'Tony', '321', '500', '2022-04-22', 35, 40, '2022-06-03');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(5, 'Notebook', 'Tony', '777', '450', '2021-03-09', 40, 50, '2023-02-03');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(6, 'Butter', 'Leo', '231', '200', '2022-03-01', 15, 20, '2022-05-17');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(7, 'Pepsi', 'Leo', '786', '250', '2022-02-21', 15, 20, '2022-06-11');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(8, 'Cake', 'Sam', '678', '270', '2022-01-23', 10, 15, '2022-05-19');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(9, 'Oreo', 'Sam', '432', '299', '2022-04-21', 7, 10, '2022-05-20');
INSERT INTO `purchasetable` (`Id`, `ProductName`, `SellerName`, `BatchNumber`, `Quantity`, `PurchaseDate`, `PurchaseRate`, `SellingRate`, `ExpiryDate`) VALUES
(10, 'Battery', 'Mark', '420', '350', '2022-03-22', 5, 10, '2022-06-17');

-- SALE TABLE --

CREATE TABLE `saletable` (
  `Id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `Date` datetime DEFAULT NULL,
  `Customer` varchar(150) NOT NULL,
  `TotalAmount` double NOT NULL DEFAULT '0'
);
ALTER TABLE `saletable` AUTO_INCREMENT=4;

INSERT INTO `saletable` (`Id`, `Date`, `Customer`, `TotalAmount`) VALUES
(1, '2022-03-01 06:09:06', 'John', 30);
INSERT INTO `saletable` (`Id`, `Date`, `Customer`, `TotalAmount`) VALUES
(2, '2022-01-05 03:03:23', 'Ash', 50);
INSERT INTO `saletable` (`Id`, `Date`, `Customer`, `TotalAmount`) VALUES
(3, '2022-04-23 11:43:32', 'Pat', 20);

-- SALE ITEM TABLE --

CREATE TABLE `saleitemtable` (
  `InvoId` int(11) NOT NULL,
  `ProductName` varchar(50) NOT NULL,
  `Batch` varchar(50) NOT NULL,
  `Quantity` double NOT NULL DEFAULT '0',
  `Amount` double NOT NULL DEFAULT '0'
);
ALTER TABLE `saleitemtable`
  ADD KEY `InvoId` (`InvoId`),
  ADD KEY `par_ind` (`InvoId`);
ALTER TABLE `saleitemtable`
  ADD CONSTRAINT `fk_branchTable` FOREIGN KEY (`InvoId`) REFERENCES `saletable` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `saleitemtable` AUTO_INCREMENT=4;
  
INSERT INTO `saleitemtable` (`InvoId`, `ProductName`, `Batch`, `Quantity`, `Amount`) VALUES
(1, 'Jam', '123', 1, 30);
INSERT INTO `saleitemtable` (`InvoId`, `ProductName`, `Batch`, `Quantity`, `Amount`) VALUES
(2, 'Pen', '450', 5, 50);
INSERT INTO `saleitemtable` (`InvoId`, `ProductName`, `Batch`, `Quantity`, `Amount`) VALUES
(3, 'Oreo', '432', 2, 20);

-- SELLER TABLE --

CREATE TABLE `sellertable` (
  `Id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `SellerName` varchar(50) NOT NULL,
  `SellerContact` varchar(50) NOT NULL,
  `SellerAddress` varchar(150) NOT NULL
);
ALTER TABLE `sellertable` AUTO_INCREMENT=6;

INSERT INTO `sellertable` (`Id`, `SellerName`, `SellerContact`, `SellerAddress`) VALUES
(1, 'Jim', '9876543210', 'xyz xyz xyz');
INSERT INTO `sellertable` (`Id`, `SellerName`, `SellerContact`, `SellerAddress`) VALUES
(2, 'Tony', '8765432109', 'xyz xyz abc');
INSERT INTO `sellertable` (`Id`, `SellerName`, `SellerContact`, `SellerAddress`) VALUES
(3, 'Mark', '0987654321', 'xyz abc xyz');
INSERT INTO `sellertable` (`Id`, `SellerName`, `SellerContact`, `SellerAddress`) VALUES
(4, 'Leo', '7654321098', 'abc xyz xyz');
INSERT INTO `sellertable` (`Id`, `SellerName`, `SellerContact`, `SellerAddress`) VALUES
(5, 'Sam', '9879879879', 'abc abc abc');

COMMIT;