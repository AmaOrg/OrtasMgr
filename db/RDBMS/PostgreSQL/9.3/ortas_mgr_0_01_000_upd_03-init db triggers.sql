CREATE FUNCTION db_upds.ortas_mgr_0_01_000_upd_03() RETURNS TEXT AS
$BODY_FUNC_UPD$
DECLARE
	v_upd_name TEXT := 'ortas_mgr_0_01_000_upd_03';
	v_upd_note TEXT := 'init db triggers';
BEGIN	

IF( db_upds.is_upd_executed(v_upd_name, v_upd_note, TRUE) )THEN
	RAISE NOTICE 'Aggiornamento db % gia eseguito', v_upd_name;
	RETURN 'ERR';
END IF;
	
-- ================ INIZIO AGGIORNAMENTO DB ========================

-- TODO: CREATE FUNCTION movimentazione_magazzino_tr__check_inout_moves() RETURNS TRIGGER


-- ================ FINE AGGIORNAMENTO DB ==========================
RETURN 'OK';
END;
$BODY_FUNC_UPD$
LANGUAGE plpgsql VOLATILE;

SELECT db_upds.ortas_mgr_0_01_000_upd_03();