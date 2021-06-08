package com.hope.model.bean;

import lombok.Data;

/**
 * @author aodeng
 */
@Data
public class GetID {

    private String name;
    private String id;
}
/*
author：aodeng
数据库配置：

1、mysql数据库创建表（该表为配置id生成规则）：
CREATE TABLE `pb_code_ident` (
  `PCI_Table` varchar(64) NOT NULL,
  `PCI_Type` varchar(64) DEFAULT NULL,
  `PCI_Length` int DEFAULT NULL,
  `PCI_Head` varchar(8) DEFAULT NULL,
  `PCI_Fill` varchar(64) DEFAULT NULL,
  `PCI_Date` datetime DEFAULT NULL,
  `PCI_Default` decimal(18,0) DEFAULT NULL,
  `PCI_Identity` decimal(16,0) DEFAULT NULL,
  PRIMARY KEY (`PCI_Table`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

2、创建存储过程
-- java 调用
        DROP PROCEDURE IF EXISTS `GetID2`;
        DELIMITER ;;
        CREATE PROCEDURE `GetID2`(IN TableName VARCHAR(100),OUT TableID VARCHAR(36))
        BEGIN
        DECLARE s_Ident VARCHAR(20);
        DECLARE s_Fill VARCHAR(1);
        DECLARE s_Type VARCHAR(3);
        DECLARE s_Date VARCHAR(16);
        DECLARE s_Head VARCHAR(10);
        DECLARE s_ID VARCHAR(20);
        DECLARE d_Date datetime;

        select PCI_Date into d_Date from PB_Code_Ident Where PCI_Table = TableName;
        if(REPLACE(DATE_FORMAT(d_Date,'%Y/%m/%d'),'-','/')=REPLACE(curdate(),'-','/')) THEN
        SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
        update PB_Code_Ident set PCI_Identity = PCI_Identity + 1 Where PCI_Table = TableName;
        else
        SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
        update PB_Code_Ident set PCI_Identity = PCI_Default,PCI_Date=REPLACE(curdate(),'-','/') Where PCI_Table = TableName;
        end if;
        select PCI_Identity,PCI_Head into s_ID,s_Head from PB_Code_Ident Where PCI_Table = TableName;
        set @TableID = concat(s_Head,REPLACE(curdate(),'-',''),s_ID);
        select @TableID INTO TableID;
        END
        ;;
        DELIMITER ;

-- .net 调用
        CREATE DEFINER=`root`@`%` PROCEDURE `GetID`(IN TableName VARCHAR(100))
        READS SQL DATA
        BEGIN
        DECLARE s_Ident VARCHAR(20);
        DECLARE s_Fill VARCHAR(1);
        DECLARE s_Type VARCHAR(3);
        DECLARE s_Date VARCHAR(16);
        DECLARE s_Head VARCHAR(10);
        DECLARE s_ID VARCHAR(20);
        DECLARE d_Date datetime;

        select PCI_Date into d_Date from PB_Code_Ident Where PCI_Table = TableName;
        if(REPLACE(DATE_FORMAT(d_Date,'%Y/%m/%d'),'-','/')=REPLACE(curdate(),'-','/')) THEN
        SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
        update PB_Code_Ident set PCI_Identity = PCI_Identity + 1 Where PCI_Table = TableName;
        else
        SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
        update PB_Code_Ident set PCI_Identity = PCI_Default,PCI_Date=REPLACE(curdate(),'-','/') Where PCI_Table = TableName;
        end if;
        select PCI_Identity,PCI_Head into s_ID,s_Head from PB_Code_Ident Where PCI_Table = TableName;
        set @TableID = concat(s_Head,REPLACE(curdate(),'-',''),s_ID);
        select @TableID;
        END

3、使用实现类方法 ：getId()即可获取id
*/