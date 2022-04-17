USE [master]
GO
/****** Object:  Database [SendOrders]    Script Date: 2022/4/17 星期日 23:03:04 ******/
CREATE DATABASE [SendOrders]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SendOrders', FILENAME = N'D:\SQL Sever 2019 根目录\MSSQL15.MSSQLSERVER\MSSQL\DATA\SendOrders.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SendOrders_log', FILENAME = N'D:\SQL Sever 2019 根目录\MSSQL15.MSSQLSERVER\MSSQL\DATA\SendOrders_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [SendOrders] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SendOrders].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SendOrders] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SendOrders] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SendOrders] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SendOrders] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SendOrders] SET ARITHABORT OFF 
GO
ALTER DATABASE [SendOrders] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SendOrders] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SendOrders] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SendOrders] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SendOrders] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SendOrders] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SendOrders] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SendOrders] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SendOrders] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SendOrders] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SendOrders] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SendOrders] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SendOrders] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SendOrders] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SendOrders] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SendOrders] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SendOrders] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SendOrders] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SendOrders] SET  MULTI_USER 
GO
ALTER DATABASE [SendOrders] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SendOrders] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SendOrders] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SendOrders] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SendOrders] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SendOrders] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [SendOrders] SET QUERY_STORE = OFF
GO
USE [SendOrders]
GO
/****** Object:  Table [dbo].[Assign]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Assign](
	[StaffNumber] [nvarchar](50) NULL,
	[OddNumber] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Evaluate]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Evaluate](
	[StaffNumber] [nvarchar](50) NOT NULL,
	[OddNumber] [int] NULL,
	[num1] [int] NULL,
	[num2] [int] NULL,
	[num3] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Manager]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Manager](
	[ID] [nchar](10) NULL,
	[Password] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Odd]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Odd](
	[OddNumber] [int] IDENTITY(1,1) NOT NULL,
	[Town] [nvarchar](50) NULL,
	[UseUnit] [nvarchar](50) NULL,
	[MaintenanceUnit] [nvarchar](50) NULL,
	[EquipmentType] [nvarchar](50) NULL,
	[Quantity] [nvarchar](50) NULL,
	[CheckoutType] [nvarchar](50) NULL,
	[UID] [nvarchar](50) NULL,
	[state] [int] NULL,
 CONSTRAINT [PK__Odd__2E7C0F4322A8CA23] PRIMARY KEY CLUSTERED 
(
	[OddNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderRate]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderRate](
	[OddNumber] [int] NULL,
	[StaffNumber] [nvarchar](50) NULL,
	[Rate] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderS]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderS](
	[StaffNumber] [nvarchar](50) NULL,
	[OddNumber] [int] NULL,
	[Time] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[StaffNumber] [nvarchar](50) NULL,
	[Password] [nchar](10) NULL,
	[State] [int] NULL,
	[Forklift] [float] NULL,
	[Elevator] [float] NULL,
	[Crane] [float] NULL,
	[baiyun] [float] NULL,
	[tianhe] [float] NULL,
	[yuexiu] [float] NULL,
	[panyu] [float] NULL,
	[haizhu] [float] NULL,
	[huangpu] [float] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Userr]    Script Date: 2022/4/17 星期日 23:03:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Userr](
	[UID] [nvarchar](50) NULL,
	[Password] [nchar](10) NULL
) ON [PRIMARY]
GO
INSERT [dbo].[Assign] ([StaffNumber], [OddNumber]) VALUES (N'落花', 10)
INSERT [dbo].[Assign] ([StaffNumber], [OddNumber]) VALUES (N'人独立', 11)
GO
INSERT [dbo].[Evaluate] ([StaffNumber], [OddNumber], [num1], [num2], [num3]) VALUES (N'落花', 1, 5, 5, 5)
INSERT [dbo].[Evaluate] ([StaffNumber], [OddNumber], [num1], [num2], [num3]) VALUES (N'落花', 4, 5, 5, 5)
INSERT [dbo].[Evaluate] ([StaffNumber], [OddNumber], [num1], [num2], [num3]) VALUES (N'人独立', 9, 5, 5, 5)
GO
INSERT [dbo].[Manager] ([ID], [Password]) VALUES (N'1         ', N'1         ')
GO
SET IDENTITY_INSERT [dbo].[Odd] ON 

INSERT [dbo].[Odd] ([OddNumber], [Town], [UseUnit], [MaintenanceUnit], [EquipmentType], [Quantity], [CheckoutType], [UID], [state]) VALUES (1, N'tianhe', N'萌萌公司', N'不萌单位', N'Elevator', N'2', N'Entrust', N'微雨', 2)
INSERT [dbo].[Odd] ([OddNumber], [Town], [UseUnit], [MaintenanceUnit], [EquipmentType], [Quantity], [CheckoutType], [UID], [state]) VALUES (4, N'baiyun', N'去', N'去', N'Elevator', N'1', N'Regular', N'微雨', 2)
INSERT [dbo].[Odd] ([OddNumber], [Town], [UseUnit], [MaintenanceUnit], [EquipmentType], [Quantity], [CheckoutType], [UID], [state]) VALUES (9, N'tianhe', N's', N'q', N'Elevator', N'1', N'Regular', N'微雨', 2)
INSERT [dbo].[Odd] ([OddNumber], [Town], [UseUnit], [MaintenanceUnit], [EquipmentType], [Quantity], [CheckoutType], [UID], [state]) VALUES (10, N'baiyun', N'ad', N'多少', N'Elevator', N'1', N'Regular', N'微雨', 3)
INSERT [dbo].[Odd] ([OddNumber], [Town], [UseUnit], [MaintenanceUnit], [EquipmentType], [Quantity], [CheckoutType], [UID], [state]) VALUES (11, N'tianhe', N'使得', N'使得', N'Elevator', N'2', N'Regular', N'微雨', 3)
SET IDENTITY_INSERT [dbo].[Odd] OFF
GO
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (1, N'落花', 0.5)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (1, N'人独立', 0.5)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (3, N'落花', 0.64999999999999991)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (3, N'人独立', 0.5)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (6, N'落花', 0.82500000000000007)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (6, N'人独立', 0.5)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (7, N'落花', 0.82500000000000007)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (7, N'人独立', 0.5)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (8, N'落花', 0.82500000000000007)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (8, N'人独立', 0.5)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (10, N'落花', 0.82500000000000007)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (10, N'人独立', 0.64999999999999991)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (11, N'落花', 0.82500000000000007)
INSERT [dbo].[OrderRate] ([OddNumber], [StaffNumber], [Rate]) VALUES (11, N'人独立', 0.75)
GO
INSERT [dbo].[OrderS] ([StaffNumber], [OddNumber], [Time]) VALUES (N'落花', 1, 2)
INSERT [dbo].[OrderS] ([StaffNumber], [OddNumber], [Time]) VALUES (N'落花', 4, 2)
INSERT [dbo].[OrderS] ([StaffNumber], [OddNumber], [Time]) VALUES (N'人独立', 9, 2)
GO
INSERT [dbo].[Staff] ([StaffNumber], [Password], [State], [Forklift], [Elevator], [Crane], [baiyun], [tianhe], [yuexiu], [panyu], [haizhu], [huangpu]) VALUES (N'落花', N'123456    ', 1, 0.5, 0.8, 0.5, 0.9, 0.75, 0.5, 0.5, 0.5, 0.5)
INSERT [dbo].[Staff] ([StaffNumber], [Password], [State], [Forklift], [Elevator], [Crane], [baiyun], [tianhe], [yuexiu], [panyu], [haizhu], [huangpu]) VALUES (N'人独立', N'aaa       ', 1, 0.5, 0.75, 0.5, 0.5, 0.75, 0.5, 0.5, 0.5, 0.5)
GO
INSERT [dbo].[Userr] ([UID], [Password]) VALUES (N'微雨', N'123456    ')
INSERT [dbo].[Userr] ([UID], [Password]) VALUES (N'燕双飞', N'aaa       ')
GO
USE [master]
GO
ALTER DATABASE [SendOrders] SET  READ_WRITE 
GO
