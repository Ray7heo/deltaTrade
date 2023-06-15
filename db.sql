-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: delta_trade
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT '分类id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名',
  `sort` int NOT NULL DEFAULT '0' COMMENT '分类排序',
  `is_deleted` int DEFAULT '0' COMMENT '逻辑字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'手机',0,0),(2,'电脑',1,0),(3,'外设',2,0),(4,'图书',3,0),(999,'其他',999,0);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `id` bigint NOT NULL COMMENT '聊天记录id',
  `commodity_id` bigint NOT NULL,
  `from_id` bigint NOT NULL COMMENT '发送者id',
  `to_id` bigint NOT NULL COMMENT '接收者id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_deleted` int DEFAULT NULL COMMENT '逻辑字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1668185880134881282,1668184861774647297,1666667977610878978,1668184464016216066,'hello','2023-06-12 17:18:02',0),(1668186472945225730,1668184861774647297,1668184464016216066,1666667977610878978,'hi','2023-06-12 17:20:23',0),(1668186714524553217,1668184861774647297,1668184464016216066,1666667977610878978,'hello','2023-06-12 17:21:21',0),(1668186741242269698,1668184861774647297,1668184464016216066,1666667977610878978,'hi','2023-06-12 17:21:27',0),(1668187923876614146,1666670507505999873,1668180194453819394,1666667977610878978,'123','2023-06-12 17:26:09',0),(1668188426077409281,1666670507505999873,1666667977610878978,1668180194453819394,'你好','2023-06-12 17:28:09',0),(1668188748720050177,1668184861774647297,1668180194453819394,1668184464016216066,'我的世界','2023-06-12 17:29:26',0),(1668814664882130945,1666670507505999873,1666667977610878978,1668180194453819394,'hi','2023-06-14 10:56:36',0),(1668815406816759810,1666670507505999873,1666667977610878978,1668180194453819394,'hello','2023-06-14 10:59:33',0),(1668815671557033986,1668184861774647297,1666667977610878978,1668184464016216066,'1','2023-06-14 11:00:36',0),(1668816918481670146,1666670507505999873,1666667977610878978,1668180194453819394,'1','2023-06-14 11:05:33',0),(1668826578899111938,1668189996890394625,1666667977610878978,1668180194453819394,'你好','2023-06-14 11:43:56',0);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commodity` (
  `id` bigint NOT NULL COMMENT '商品id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名字',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品描述',
  `price` double DEFAULT NULL COMMENT '商品价格',
  `images` json DEFAULT NULL COMMENT '图片列表',
  `seller_id` bigint NOT NULL COMMENT '卖家id',
  `category_id` bigint NOT NULL COMMENT '分类id',
  `is_deleted` int NOT NULL COMMENT '逻辑字段',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_sold` int NOT NULL DEFAULT '0' COMMENT '是否售出',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` VALUES (1666668977063190529,'戴尔G15 5525','R5 6600h+RTX3050\n全新无磕碰',4000,'[\"/63573882-a9df-4780-84d7-7328261e90af.jpg\", \"/766b6fc7-570b-4499-af2d-4de77e6b812b.webp\", \"/7c99703c-4f2a-448c-ae28-bb5d1bc682b9.png\"]',1666667977610878978,2,0,'2023-06-08 12:50:24','2023-06-08 12:51:35',0),(1666670507505999873,'三星Galaxy S23','8+512\n99新无磕碰,箱说全',3800,'[\"/ed0e6036-1717-480f-b42b-4b00fb716e43.webp\", \"/4d5f628d-3dae-4692-baa7-b65ab7b6a26a.webp\", \"/de5c63c0-b83c-4276-8610-263fbce2b93c.webp\"]',1666667977610878978,1,0,'2023-06-08 12:56:29','2023-06-12 17:08:17',0),(1668184861774647297,'123','123',123,'[\"/72f84cfc-3234-4640-98e6-e77d6494746e.jpg\"]',1668184464016216066,1,0,'2023-06-12 17:13:59','2023-06-12 17:13:59',0),(1668189996890394625,'女生自用99新 工作站','想出给有缘人😘😘',52000,'[\"/48e7feb5-471a-4149-ace1-8092343c0a6a.jpeg\"]',1668180194453819394,2,0,'2023-06-12 17:34:23','2023-06-12 17:35:01',0);
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `id` bigint NOT NULL COMMENT '收藏id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `commodity_id` bigint DEFAULT NULL COMMENT '商品id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1666645394203074561,1665317966960640002,1666284062341570561,'2023-06-08 11:16:41','2023-06-08 11:16:41'),(1668179352900276225,1666677132157509634,1666670507505999873,'2023-06-12 16:52:06','2023-06-12 16:52:06'),(1668179426120241153,1668062654469758977,1666668977063190529,'2023-06-12 16:52:23','2023-06-12 16:52:23'),(1668819716371845121,1666667977610878978,1666668977063190529,'2023-06-14 11:16:40','2023-06-14 11:16:40'),(1668825276420919298,1666667977610878978,1668189996890394625,'2023-06-14 11:38:46','2023-06-14 11:38:46');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` bigint NOT NULL COMMENT '订单id',
  `commodity_id` bigint NOT NULL COMMENT '商品id',
  `seller_id` bigint NOT NULL COMMENT '卖家id',
  `buyer_id` bigint NOT NULL COMMENT '买家id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '逻辑字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '用户id',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户头像',
  `phone_number` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户电话号码',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户地址',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '逻辑字段',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '微信用户唯一标识',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `emil` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1666666325365481474,NULL,NULL,'天真无邪','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTInJ6LZx4So2cVbhMghPfCZx4RZgiaur0RQGTqlaUMMsnu97vKf837zhmWz7Up62o0TIibPvR2ZqjQw/132',NULL,NULL,0,'oBbVI6w5HHZGzawhhuc5BkDObp88'),(1666667977610878978,NULL,NULL,'🥟','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIHIK9icweVtoOJpm0KhlgUYRncJbibsPsgfQSSoq1mwXic5ZwVpj65AUHf1axoc2xzLWoicZLw2uyx1Q/132',NULL,NULL,0,'oBbVI64DldBU77xaFk617dllCWTc'),(1668180194453819394,NULL,NULL,'众','https://thirdwx.qlogo.cn/mmopen/vi_32/YwTnxC9ibErn8HBibtGaDObAT2DPcOEvic3KtqLCibJSSncNFic20QiawiaGIdWiaK3tNQQIVggvmc1r9vtQH5bgLMKSSg/132',NULL,NULL,0,'oBbVI61iqSzUHmUu02oo1ET-eeL8'),(1668184464016216066,NULL,NULL,'骰子','https://thirdwx.qlogo.cn/mmopen/vi_32/QrHu2tNJdcsMq8tlwXVkia52NQSvbvpHllI8veJRYT55rIaBOpV8GyTd7EG7tgg5J4vXmAP39jGpGyqUo1aibH5A/132',NULL,NULL,0,'oBbVI66i-uNJkQ9wShhjJju_PZ7E');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-15  8:37:08
