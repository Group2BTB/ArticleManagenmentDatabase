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

Date: 2015-07-03 14:16:46
*/


-- ----------------------------
-- Sequence structure for id_tbl_user_seq
-- ----------------------------
DROP SEQUENCE "id_tbl_user_seq";
CREATE SEQUENCE "id_tbl_user_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 3
 CACHE 1;
SELECT setval('"public"."id_tbl_user_seq"', 3, true);

-- ----------------------------
-- Sequence structure for tbl_article_id_seq
-- ----------------------------
DROP SEQUENCE "tbl_article_id_seq";
CREATE SEQUENCE "tbl_article_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;
SELECT setval('"public"."tbl_article_id_seq"', 14, true);

-- ----------------------------
-- Table structure for tbl_article
-- ----------------------------
DROP TABLE IF EXISTS "tbl_article";
CREATE TABLE "tbl_article" (
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
BEGIN;
INSERT INTO "tbl_article" VALUES ('10', 'yyyyyy', '1', 'ddddddddd ddddddd', '2015-07-01 11:58:57.005', '2015-07-01 13:48:17.209', '0', '0');
INSERT INTO "tbl_article" VALUES ('11', 'dgedh', '1', 'tt', '2015-07-01 11:59:13.82', null, '1', '1');
INSERT INTO "tbl_article" VALUES ('12', 'ddddddd', '1', 'dddddd', '2015-07-01 13:10:43.612', null, '1', '1');
INSERT INTO "tbl_article" VALUES ('14', 'dsd', '1', 'sfsfs', '2015-07-01 13:49:13.7', null, '1', '1');
COMMIT;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS "tbl_user";
CREATE TABLE "tbl_user" (
"id" int4 DEFAULT nextval('id_tbl_user_seq'::regclass) NOT NULL,
"fullname" varchar(100) COLLATE "default",
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
BEGIN;
INSERT INTO "tbl_user" VALUES ('1', 'ddd', 'wffff', 'fff', 'ffdf', '2015-06-30 00:00:00', null, '1', null, 'admin');
INSERT INTO "tbl_user" VALUES ('2', 'dgdegh', 'qdgsg', 'wgf', 'kjjk', '2015-06-30 00:00:00', null, '1', null, 'admin');
INSERT INTO "tbl_user" VALUES ('3', 'wgew', 'ohkb', 'kjk', 'kjkbkj', '2015-06-30 00:00:00', null, '1', null, 'admin');
COMMIT;

-- ----------------------------
-- Function structure for count_search_all
-- ----------------------------
CREATE OR REPLACE FUNCTION "count_search_all"(_value varchar)
  RETURNS "pg_catalog"."int4" AS $BODY$
			BEGIN
						RETURN (SELECT 
								"count"(*)
						FROM
								tbl_article a
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*'));				
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Function structure for count_search_by_author
-- ----------------------------
CREATE OR REPLACE FUNCTION "count_search_by_author"(_value varchar)
  RETURNS "pg_catalog"."int4" AS $BODY$
			BEGIN
						RETURN (SELECT 
								"count"(*)
						FROM
								tbl_article a
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*'));				
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Function structure for count_search_by_title
-- ----------------------------
CREATE OR REPLACE FUNCTION "count_search_by_title"(_value varchar)
  RETURNS "pg_catalog"."int4" AS $BODY$
			BEGIN
						RETURN ( SELECT 
								"count"(*)
						FROM
								tbl_article a
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') );				
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Function structure for deletearticle(artid int4)
-- ----------------------------
CREATE OR REPLACE FUNCTION "deletearticle(artid int4)"(artid int4)
  RETURNS "pg_catalog"."void" AS $BODY$
		BEGIN
			DELETE FROM tbl_article WHERE id=artID;
		END;
	$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Function structure for insertarticle
-- ----------------------------
CREATE OR REPLACE FUNCTION "insertarticle"(_title varchar, _author int4, _content text, _active int4, _approved int4)
  RETURNS "pg_catalog"."void" AS $BODY$
			BEGIN
				INSERT INTO tbl_article(title,author_id,content,active,approved)
				VALUES(_title, _author, _content, _active, _approved);
			END;
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Function structure for search_all
-- ----------------------------
CREATE OR REPLACE FUNCTION "search_all"(IN _value varchar, IN _field varchar, IN _order_to varchar, IN _s int4, IN _e int4)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') OR
								u.fullname ~ CONCAT('.*', _value,'.*') OR
								a.content ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date DESC
						LIMIT _s OFFSET _e; 
				END IF;					
			END;	
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for searchbyauthor
-- ----------------------------
CREATE OR REPLACE FUNCTION "searchbyauthor"(IN _value varchar, IN _field varchar, IN _order_to varchar)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id ASC; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id DESC; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title ASC; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title DESC; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname ASC; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname DESC; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date ASC; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date DESC; 
				END IF;					
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for searchbyauthor
-- ----------------------------
CREATE OR REPLACE FUNCTION "searchbyauthor"(IN _value varchar, IN _field varchar, IN _order_to varchar, IN _s int4, IN _e int4)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								u.fullname ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date DESC
						LIMIT _s OFFSET _e; 
				END IF;					
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for searchbyid
-- ----------------------------
CREATE OR REPLACE FUNCTION "searchbyid"(IN _value varchar, IN _field varchar, IN _order_to varchar)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.id ASC; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.id DESC; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.title ASC; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.title DESC; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.id = _value
						ORDER BY u.fullname ASC; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.id = _value
						ORDER BY u.fullname DESC; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.id = _value
						ORDER BY a.create_date ASC; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.create_date DESC; 
				END IF;					
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for searchbyid
-- ----------------------------
CREATE OR REPLACE FUNCTION "searchbyid"(IN _value varchar, IN _field varchar, IN _order_to varchar, IN _s int4, IN _e int4)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.id ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.id DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.title ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.title DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.id = _value
						ORDER BY u.fullname ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.id = _value
						ORDER BY u.fullname DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.id = _value
						ORDER BY a.create_date ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.id = _value
						ORDER BY a.create_date DESC
						LIMIT _s OFFSET _e; 
				END IF;					
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for searchbytitle
-- ----------------------------
CREATE OR REPLACE FUNCTION "searchbytitle"(IN _value varchar, IN _field varchar, IN _order_to varchar)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id ASC; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY a.id DESC; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title ASC; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY a.title DESC; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname ASC; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY u.fullname DESC; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date ASC; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE 
								a.title ~ CONCAT('.*', _value,'.*')
						ORDER BY a.create_date DESC; 
				END IF;					
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for selectall
-- ----------------------------
CREATE OR REPLACE FUNCTION "selectall"(IN _field varchar, IN _order_to varchar)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
			BEGIN
					
					IF _field = 'id' AND _order_to = 'ASC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY a.id ASC; 
					ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY a.id DESC; 
					ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY a.title ASC; 
					ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY a.title DESC; 
					ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY u.fullname ASC; 
					ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY u.fullname DESC; 
					ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY a.create_date ASC; 
					ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
							RETURN QUERY
							SELECT 
									a.id,a.title,u.fullname,a.create_date
							FROM
									tbl_article a JOIN tbl_user u ON a.author_id = u.id
							ORDER BY a.create_date DESC; 
					END IF;					
			END;
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for selectall
-- ----------------------------
CREATE OR REPLACE FUNCTION "selectall"(IN _field varchar, IN _order_to varchar, IN _s int4, IN _e int4)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$

		BEGIN
				IF _field = 'id' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id						
						ORDER BY a.id ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.id DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.title ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.title DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY u.fullname ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY u.fullname DESC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.create_date ASC
						LIMIT _s OFFSET _e; 
				ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.create_date DESC
						LIMIT _s OFFSET _e; 
				END IF;					
			END;
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;

-- ----------------------------
-- Function structure for updatearticle
-- ----------------------------
CREATE OR REPLACE FUNCTION "updatearticle"(_id int4, _title varchar, _content text, _active int4, _approved int4)
  RETURNS "pg_catalog"."void" AS $BODY$
			BEGIN
					UPDATE tbl_article
					SET
							title = _title,
							content = _content,
							active = _active,
							approved = _approved,
							modified_date = NOW()
					WHERE id = _id;
			END;
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "tbl_article_id_seq" OWNED BY "tbl_article"."id";

-- ----------------------------
-- Primary Key structure for table tbl_article
-- ----------------------------
ALTER TABLE "tbl_article" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table tbl_user
-- ----------------------------
ALTER TABLE "tbl_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "tbl_article"
-- ----------------------------
ALTER TABLE "tbl_article" ADD FOREIGN KEY ("author_id") REFERENCES "tbl_user" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "tbl_user"
-- ----------------------------
ALTER TABLE "tbl_user" ADD FOREIGN KEY ("parent_id") REFERENCES "tbl_user" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
