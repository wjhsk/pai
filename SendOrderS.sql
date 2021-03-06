USE [SendOrders]
GO
/****** Object:  Table [dbo].[Assign]    Script Date: 2022/3/19 星期六 19:51:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Assign](
	[StaffNumber] [nvarchar](50) NULL,
	[OddNumber] [int] NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Manager]    Script Date: 2022/3/19 星期六 19:51:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Manager](
	[ID] [nchar](10) NULL,
	[Password] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Odd]    Script Date: 2022/3/19 星期六 19:51:06 ******/
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
/****** Object:  Table [dbo].[OrderRate]    Script Date: 2022/3/19 星期六 19:51:06 ******/
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
/****** Object:  Table [dbo].[OrderS]    Script Date: 2022/3/19 星期六 19:51:06 ******/
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
/****** Object:  Table [dbo].[Staff]    Script Date: 2022/3/19 星期六 19:51:06 ******/
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
/****** Object:  Table [dbo].[Userr]    Script Date: 2022/3/19 星期六 19:51:06 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Userr](
	[UID] [nvarchar](50) NULL,
	[Password] [nchar](10) NULL
) ON [PRIMARY]
GO
