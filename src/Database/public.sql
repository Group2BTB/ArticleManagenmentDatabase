/*
Navicat PGSQL Data Transfer

Source Server         : Sereyvong
Source Server Version : 90303
Source Host           : localhost:5432
Source Database       : ArticleManagement
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90303
File Encoding         : 65001

Date: 2015-07-01 10:22:42
*/


-- ----------------------------
-- Sequence structure for id_tbl_user_seq
-- ----------------------------
DROP SEQUENCE "public"."id_tbl_user_seq";
CREATE SEQUENCE "public"."id_tbl_user_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."id_tbl_user_seq"', 3, true);

-- ----------------------------
-- Sequence structure for tbl_article_id_seq
-- ----------------------------
DROP SEQUENCE "public"."tbl_article_id_seq";
CREATE SEQUENCE "public"."tbl_article_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 6
 CACHE 1;
SELECT setval('"public"."tbl_article_id_seq"', 6, true);

-- ----------------------------
-- Table structure for tbl_article
-- ----------------------------
DROP TABLE IF EXISTS "public"."tbl_article";
CREATE TABLE "public"."tbl_article" (
"id" int4 DEFAULT nextval('tbl_article_id_seq'::regclass) NOT NULL,
"title" varchar(255) COLLATE "default" NOT NULL,
"author_id" int2 NOT NULL,
"content" text COLLATE "default",
"create_date" timestamp(6) DEFAULT now() NOT NULL,
"modified_date" timestamp(6),
"active" int2 DEFAULT 1,
"approved" int2 DEFAULT 1
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbl_article
-- ----------------------------

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."tbl_user";
CREATE TABLE "public"."tbl_user" (
"id" int4 DEFAULT nextval('id_tbl_user_seq'::regclass) NOT NULL,
"full name" varchar(100) COLLATE "default",
"email" varchar(100) COLLATE "default",
"username" varchar(100) COLLATE "default" NOT NULL,
"passwd" varchar(100) COLLATE "default" NOT NULL,
"create_date" timestamp(6) DEFAULT now() NOT NULL,
"modified_date" timestamp(6),
"active" int2 DEFAULT 1,
"parent_id" int4,
"type" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO "public"."tbl_user" VALUES ('1', 'ddd', 'wffff', 'fff', 'ffdf', '2015-06-30 00:00:00', null, '1', null, 'admin');
INSERT INTO "public"."tbl_user" VALUES ('2', 'dgdegh', 'qdgsg', 'wgf', 'kjjk', '2015-06-30 00:00:00', null, '1', null, 'admin');
INSERT INTO "public"."tbl_user" VALUES ('3', 'wgew', 'ohkb', 'kjk', 'kjkbkj', '2015-06-30 00:00:00', null, '1', null, 'admin');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."tbl_article_id_seq" OWNED BY "tbl_article"."id";

-- ----------------------------
-- Primary Key structure for table tbl_article
-- ----------------------------
ALTER TABLE "public"."tbl_article" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tbl_user
-- ----------------------------
ALTER TABLE "public"."tbl_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."tbl_article"
-- ----------------------------
ALTER TABLE "public"."tbl_article" ADD FOREIGN KEY ("author_id") REFERENCES "public"."tbl_user" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "public"."tbl_user"
-- ----------------------------
ALTER TABLE "public"."tbl_user" ADD FOREIGN KEY ("parent_id") REFERENCES "public"."tbl_user" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
