USE [FILE_SHARING_DB]

GO

DROP TABLE IF EXISTS [FILE_SHARING_DB].[dbo].[USERS];
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [FILE_SHARING_DB].[dbo].[USERS]
(
    [ID]                   [int] IDENTITY (1,1) NOT NULL,
    [USERNAME]			   [varchar](20)        NOT NULL,
    [PASSWORD]             [varchar](20)        NOT NULL,
    [GENDER]               [varchar](10)        NOT NULL,
    [FULL_NAME]            [varchar](100)       NOT NULL,
    [EMAIL]                [varchar](255)       NOT NULL,
    [DOB]                  [varchar](10)        NOT NULL,
    [MOBILE_NO]            [varchar](20)        NOT NULL,
    [STATUS]               [int] DEFAULT (0)    NOT NULL,
    [CREATED_AT]           [datetime]           NOT NULL,
    [MODIFIED_AT]          [datetime]           NOT NULL,
    CONSTRAINT [PK_USERS] PRIMARY KEY CLUSTERED
        (
         [ID] ASC
        ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
)
GO

ALTER TABLE [FILE_SHARING_DB].[dbo].[USERS]
    ADD DEFAULT (getdate()) FOR [CREATED_AT]
GO

ALTER TABLE [FILE_SHARING_DB].[dbo].[USERS]
    ADD DEFAULT (getdate()) FOR [MODIFIED_AT]
GO

ALTER TABLE [FILE_SHARING_DB].[dbo].[USERS]
	ADD CONSTRAINT UC_USERS UNIQUE ([USERNAME],[EMAIL],[MOBILE_NO]);
GO

CREATE NONCLUSTERED INDEX [NonClusteredIndex-USERS.USERNAME] ON [FILE_SHARING_DB].[dbo].[USERS]
    (
     [USERNAME] ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO

CREATE NONCLUSTERED INDEX [NonClusteredIndex-USERS.EMAIL] ON [FILE_SHARING_DB].[dbo].[USERS]
    (
     [EMAIL] ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO


INSERT INTO [dbo].[USERS]
           ([USERNAME]
           ,[PASSWORD]
           ,[GENDER]
           ,[FULL_NAME]
           ,[EMAIL]
           ,[DOB]
           ,[MOBILE_NO])
     VALUES
           ('triuhn','123456'
           ,'MALE'
           ,'Hoang Ngoc Triu'
           ,'triuhoang.hn@gmail.com'
           ,'1997-08-11'
           ,'0123123111')
GO
INSERT INTO [dbo].[USERS]
           ([USERNAME]
           ,[PASSWORD]
           ,[GENDER]
           ,[FULL_NAME]
           ,[EMAIL]
           ,[DOB]
           ,[MOBILE_NO])
     VALUES
           ('thelight','123456'
           ,'MALE'
           ,'The Light'
           ,'thelight@gmail.com'
           ,'1997-08-11'
           ,'0123129987')
GO


