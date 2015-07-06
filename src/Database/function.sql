CREATE or REPLACE FUNCTION select_approved(status INTEGER)
	RETURNS TABLE(ID INTEGER, TITLE VARCHAR, AUTHOR VARCHAR, DATES TIMESTAMP) AS $BODY$
			BEGIN				
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id	
						WHERE a.approved = status
						ORDER BY a.id DESC;								
			END;				
		$BODY$
  LANGUAGE 'plpgsql';

-------------------select_all----------------------------------------------------------------------
CREATE or REPLACE FUNCTION select_all(__field VARCHAR, __order_to VARCHAR, _s INTEGER, _e INTEGER)
	RETURNS TABLE(ID INTEGER, TITLE VARCHAR, AUTHOR VARCHAR, DATES TIMESTAMP) AS $BODY$
			BEGIN
				IF __field = 'id' AND __order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id						
						ORDER BY a.id ASC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'id' AND __order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.id DESC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'title' AND __order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.title ASC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'title' AND __order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.title DESC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'author' AND __order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY u.fullname ASC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'author' AND __order_to = 'DESC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY u.fullname DESC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'creDate' AND __order_to = 'ASC' THEN 
						RETURN QUERY
						SELECT 
								a.id,a.title,u.fullname,a.create_date
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						ORDER BY a.create_date ASC
						LIMIT _s OFFSET _e; 
				ELSIF __field = 'creDate' AND __order_to = 'DESC' THEN 
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
  LANGUAGE 'plpgsql';  
  ---------------------------------------------------------------------------------------------------
 -------------------search_all----------------------------------------------------------------------
CREATE or REPLACE FUNCTION search_all(_value VARCHAR, _field VARCHAR, _order_to VARCHAR, s INTEGER, e INTEGER)
	RETURNS TABLE(ID INTEGER, TITLE VARCHAR, AUTHOR VARCHAR, DATES TIMESTAMP) AS $BODY$ 
  
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
      LIMIT s OFFSET e; 
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
      LIMIT s OFFSET e; 
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
      LIMIT s OFFSET e; 
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
      LIMIT s OFFSET e; 
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
      LIMIT s OFFSET e; 
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
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
       a.title ~ CONCAT('.*', _value,'.*') OR
        u.fullname ~ CONCAT('.*', _value,'.*') OR
        a.content ~ CONCAT('.*', _value,'.*')
      ORDER BY a.create_date ASC
      LIMIT s OFFSET e; 
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
      LIMIT s OFFSET e; 
    END IF;     
   END;
 $BODY$
  LANGUAGE 'plpgsql';  
  --------------------------------------------------------------------------------------------------- 
  
  -------------------search_by_title----------------------------------------------------------------------
CREATE or REPLACE FUNCTION search_by_title(_value VARCHAR, _field VARCHAR, _order_to VARCHAR, s INTEGER, e INTEGER)
	RETURNS TABLE(ID INTEGER, TITLE VARCHAR, AUTHOR VARCHAR, DATES TIMESTAMP) AS $BODY$ 
  
  BEGIN
    IF _field = 'id' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY a.id ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY a.id DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY a.title ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY a.title DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY u.fullname ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY u.fullname DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY a.create_date ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.title ~ CONCAT('.*', _value,'.*')
      ORDER BY a.create_date DESC
      LIMIT s OFFSET e; 
    END IF;     
   END;
 $BODY$
  LANGUAGE 'plpgsql';  
  --------------------------------------------------------------------------------------------------- 
  
   -------------------search_by_author----------------------------------------------------------------------
CREATE or REPLACE FUNCTION search_by_author(_value VARCHAR, _field VARCHAR, _order_to VARCHAR, s INTEGER, e INTEGER)
	RETURNS TABLE(ID INTEGER, TITLE VARCHAR, AUTHOR VARCHAR, DATES TIMESTAMP) AS $BODY$ 
  
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
      LIMIT s OFFSET e; 
    ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
       u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY a.id DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
       u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY a.title ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
       u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY a.title DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
       u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY u.fullname ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
        u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY u.fullname DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
       u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY a.create_date ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        u.fullname ~ CONCAT('.*', _value,'.*')
      ORDER BY a.create_date DESC
      LIMIT s OFFSET e; 
    END IF;     
   END;
 $BODY$
  LANGUAGE 'plpgsql';  
  --------------------------------------------------------------------------------------------------- 
  
  
  
     -------------------search_by_id----------------------------------------------------------------------
CREATE or REPLACE FUNCTION search_by_id(_value INTEGER, _field VARCHAR, _order_to VARCHAR, s INTEGER, e INTEGER)
	RETURNS TABLE(ID INTEGER, TITLE VARCHAR, AUTHOR VARCHAR, DATES TIMESTAMP) AS $BODY$ 
  
  BEGIN
    IF _field = 'id' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.id=_value
      ORDER BY a.id ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'id' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.id=_value
      ORDER BY a.id DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'title' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.id=_value
      ORDER BY a.title ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'title' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
        a.id=_value
      ORDER BY a.title DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'author' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
        a.id=_value
      ORDER BY u.fullname ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'author' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
         a.id=_value
      ORDER BY u.fullname DESC
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'ASC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE 
        a.id=_value
      ORDER BY a.create_date ASC
      LIMIT s OFFSET e; 
    ELSIF _field = 'creDate' AND _order_to = 'DESC' THEN 
      RETURN QUERY
      SELECT 
        a.id,a.title,u.fullname,a.create_date
      FROM
        tbl_article a JOIN tbl_user u ON a.author_id = u.id
      WHERE
         a.id=_value
      ORDER BY a.create_date DESC
      LIMIT s OFFSET e; 
    END IF;     
   END;
 $BODY$
  LANGUAGE 'plpgsql';  
  --------------------------------------------------------------------------------------------------- 
  -- ----------------------------
-- Function structure for count_search_all
-- ----------------------------
CREATE OR REPLACE FUNCTION "count_search_all"(_value varchar)
  RETURNS "pg_catalog"."int4" AS $BODY$
			BEGIN
						RETURN (SELECT 
								"count"(*)
						FROM
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
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
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
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
								tbl_article a JOIN tbl_user u ON a.author_id = u.id
						WHERE
								a.title ~ CONCAT('.*', _value,'.*') );				
			END;				
		$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

-- ----------------------------
-- Function structure for count_select_all
-- ----------------------------
CREATE OR REPLACE FUNCTION "count_select_all"()
  RETURNS "pg_catalog"."int4" AS $BODY$
			BEGIN
						RETURN (SELECT 
								"count"(*)
						FROM
								tbl_article a);							
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
  
  