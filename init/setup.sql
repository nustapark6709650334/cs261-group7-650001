/*
  FILE: setup.sql
  รันไฟล์นี้เป็นไฟล์แรกเพื่อสร้าง DB และ Table
*/

-- 1. สร้าง Database 'myDB1'
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'myDB1')
BEGIN
  CREATE DATABASE myDB1;
END;
GO

-- 2. สลับไปใช้ Database 'myDB1'
USE myDB1;
GO

-- 3. สร้างตาราง 'courses'
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='courses' and xtype='U')
BEGIN
    CREATE TABLE dbo.courses (
        course_code NVARCHAR(50) PRIMARY KEY,
        course_name NVARCHAR(255) NOT NULL,
        course_detail NVARCHAR(MAX),
        course_group NVARCHAR(255),
        credit INT NOT NULL
    );
END;
GO