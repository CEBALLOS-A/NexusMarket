IF DB_ID('NexusMarket') IS NULL
    CREATE DATABASE NexusMarket;
GO
USE NexusMarket;
GO

IF OBJECT_ID('dbo.NM_Users', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.NM_Users (
        id INT NOT NULL PRIMARY KEY,
        full_name NVARCHAR(160) NOT NULL,
        email NVARCHAR(254) NOT NULL UNIQUE,
        role NVARCHAR(40) NOT NULL,
        status NVARCHAR(40) NOT NULL,
        main_address NVARCHAR(500) NULL
    );
END;
GO

IF OBJECT_ID('dbo.NM_Products', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.NM_Products (
        id INT NOT NULL PRIMARY KEY,
        name NVARCHAR(160) NOT NULL,
        product_type NVARCHAR(80) NOT NULL,
        status NVARCHAR(40) NOT NULL
    );
END;
GO

-- Safe migration for databases created with an earlier version of this script.
IF COL_LENGTH('dbo.NM_Users', 'main_address') IS NULL
    ALTER TABLE dbo.NM_Users ADD main_address NVARCHAR(500) NULL;
GO
