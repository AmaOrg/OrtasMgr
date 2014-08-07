CREATE SCHEMA db_upds;

SET LOCAL search_path TO db_upds,"$user",public;

CREATE TABLE db_upds.upds
(
	id SERIAL NOT NULL,
	upd_name CHARACTER VARYING NOT NULL,
	data TIMESTAMP NOT NULL,
	note CHARACTER VARYING NOT NULL,
	CONSTRAINT db_upds_pk PRIMARY KEY(upd_name),
	CONSTRAINT db_upds_uc_id UNIQUE(id)
);

CREATE FUNCTION is_upd_executed(in_upd_name TEXT, in_upd_note TEXT, do_reg_exec BOOLEAN) RETURNS BOOLEAN AS
$BODY_FUNC$
DECLARE
	v_upd_executed BOOLEAN := FALSE;
BEGIN
	-- Questo aggiornamento è già stato eseguito in passato?

	SELECT INTO v_upd_executed
		COUNT(*) = 1 AS upd_executed
	FROM db_upds.upds
	WHERE upds.upd_name = in_upd_name;
	
	IF(v_upd_executed = FALSE)THEN
		IF(do_reg_exec = TRUE)THEN
			-- REGISTRO l'esecuzione dell'aggiornamento db
			INSERT INTO db_upds.upds
					(id     , upd_name  , data , note      ) 
			VALUES	(DEFAULT, in_upd_name, now(),in_upd_note);
		END IF;
		
		RETURN FALSE;
	END IF;
	
	RETURN TRUE;
END;
$BODY_FUNC$
LANGUAGE plpgsql VOLATILE;

SELECT is_upd_executed('ortas_mgr_0_01_000_upd_00', 'pg_store_00_01_00_upd_00', TRUE);
