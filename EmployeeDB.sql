create database employee;

create table Employees(
   EmployeeID INT IDENTITY(1,1) PRIMARY KEY ,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Gender CHAR(1),
    Address VARCHAR(30),
    Email VARCHAR(30) UNIQUE,
    PhoneNo Int UNIQUE,
    DepartmentID VARCHAR(20), -- Foreign key referencing Departments table
    CONSTRAINT FK_Department FOREIGN KEY (DepartmentID) REFERENCES Departments(DepartmentID)ON UPDATE CASCADE
    ON DELETE CASCADE
    
);




CREATE TABLE Departments (
    DepartmentID VARCHAR(20) PRIMARY KEY,
    DepartmentName VARCHAR(30),
    ManagerID INT, -- Foreign key referencing Managers table
    CONSTRAINT FK_DepartmentManager FOREIGN KEY (ManagerID) REFERENCES Managers(ManagerID)ON UPDATE CASCADE
    ON DELETE CASCADE
);





CREATE TABLE Managers (
    ManagerID INT IDENTITY(100,1) PRIMARY KEY,
    ManagerName VARCHAR(30),
    Email varchar(30)
);




insert into Employees values
 ( 'Ramya', 'Menda', 'F', 'Srikakulam', 'ramyamenda369@gmail.com', 630334054, 'CSM22'),
 ('Satya','Challapalli','F','Ravulapalem','satya123@gmail.com',991237890,'CSM22'),
 ('vijay','guntagani','M','Guntur','vijay@gmail.com',901276238,'CSM22'),
 ('Harini','vavilapalli','F','Srikakulam','harini@gmail.com',989357634,'EDI11'),
 ('prasanthi','dadi','F','Anakapalli','prasanthi@gmail.com',990783456,'CSM22'),
 ('manikanta','pandem','M','Tuni','mani@gmail.com',630926780,'EDI11'),
 ('charitha','dusi','F','Srikakulam','cherry@gmail.com',893458901,'ROR33'),
 ('jay','chaudari','M','Srikakulam','jay@gmail.com',890124578,'DS44'),
 ('kalyan','Thamanampudi','M','Vizag','kalyan@gmail.com',791357271,'EDI11'),
 ('priya','gorella','F','Tuni','priya@gmail.com',889902779,'ROR33')


INSERT into Departments VALUES
('CSM22','CSM',100),
('EDI11','EDI',101),
('ROR33','ROR',102),
('DS44','DS',103)


INSERT into Managers VALUES
('Venu','venu@gmail.com'),
('Rajeev','rajeev@gmail.com'),
('Madhukar','madhukar@gmail.com'),
('Sasank','sasank@gmail.com')

SELECT *from Employees

select *from Managers

select *from Departments



