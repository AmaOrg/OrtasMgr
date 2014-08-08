CREATE FUNCTION db_upds.ortas_mgr_0_01_000_upd_02() RETURNS TEXT AS
$BODY_FUNC_UPD$
DECLARE
	v_upd_name TEXT := 'ortas_mgr_0_01_000_upd_02';
	v_upd_note TEXT := 'pg_store_00_01_00_upd_01';
BEGIN	

IF( db_upds.is_upd_executed(v_upd_name, v_upd_note, TRUE) )THEN
	RAISE NOTICE 'Aggiornamento db % gia eseguito', v_upd_name;
	RETURN 'ERR';
END IF;
	
-- ================ INIZIO AGGIORNAMENTO DB ========================

CREATE SCHEMA utils;

CREATE OR REPLACE FUNCTION utils.array_to_csv(arr anyarray)
RETURNS text AS
$BODY$
DECLARE
	ret TEXT;
	n_intems INTEGER;
BEGIN
	ret := '';
	n_intems := array_upper(arr, 1);
	IF (n_intems > 0) THEN
		IF (arr[1] IS NULL) THEN ret := 'NULL'; ELSE ret := arr[1]; END IF;
		FOR i IN 2..n_intems LOOP 
			IF (arr[i] IS NULL) THEN 
				ret := ret || ',' || 'NULL';
			ELSE
				ret := ret || ',' || arr[i];
			END IF;
		END LOOP;
	END IF;
	RETURN ret;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION utils.get_format(format text, args character[])
RETURNS text AS
$BODY$
DECLARE
	ret TEXT;
	n_args INTEGER;
BEGIN
	ret := format;
	n_args := array_upper(args, 1);
	IF (n_args > 0) THEN
		FOR i IN 1..n_args LOOP
			IF (args[i] IS NULL) THEN
				ret := replace(ret, '{' || (i-1) || '}', 'NULL');
			ELSE
				ret := replace(ret, '{' || (i-1) || '}', args[i]);
			END IF;
		END LOOP;
	END IF;
	RETURN ret;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE SCHEMA err_hand;

CREATE OR REPLACE FUNCTION err_hand.get_error(error_key text, error_func xml, error_data xml, error_msg xml)
RETURNS text AS
$BODY$
DECLARE
	ret TEXT;
BEGIN
	ret := xmlelement(name error, xmlattributes(error_key as key), error_func, error_data, error_msg);
	RETURN ret;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_args(func_name text, arg_values character[])
RETURNS xml AS
$BODY$
DECLARE
	func_args XML;
	n_args INTEGER;
	f_name TEXT;
	f_vals TEXT;
BEGIN
	f_name := ' ? ';
	IF func_name IS NOT NULL THEN 
		f_name := func_name;
	END IF;
	f_vals := '( ' || utils.array_to_csv(arg_values) || ' )';
	func_args := xmlelement(name args, f_name, f_vals);
	RETURN func_args;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_data(err_data xml[])
RETURNS xml AS
$BODY$
DECLARE
	err XML;
	d XML;
	n_items INTEGER;
BEGIN
	n_items := array_upper(err_data, 1);
	IF (n_items > 0) THEN
		d := err_data[1];
		FOR i IN 2..n_items LOOP
			d = xmlconcat(d, err_data[i]);
		END LOOP;
	END IF;

	err := err_hand.get_error_data(d);
	RETURN err;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_data(err_data xml)
RETURNS xml AS
$BODY$
DECLARE
	err XML;
BEGIN
	err := xmlelement(name error_data, err_data);
	RETURN err;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_func(func_name text, arg_names character[], arg_values character[])
RETURNS xml AS
$BODY$
DECLARE
	func XML;
	func_firm XML;
	func_args XML;
BEGIN
	func_firm := err_hand.get_error_where(func_name, arg_names);
	func_args := err_hand.get_error_args(func_name, arg_values);
	func := xmlconcat(func_firm, func_args);
	RETURN func;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_where(func_name text, arg_names character[])
RETURNS xml AS
$BODY$
DECLARE
	func_firm XML;
	
	f_name TEXT;
	f_args TEXT;
BEGIN
	f_name := ' ? ';
	IF func_name IS NOT NULL THEN 
		f_name := func_name;
	END IF;
	
	f_args := '( ' || utils.array_to_csv(arg_names) || ' )';
	func_firm := xmlelement(name where, f_name, f_args);

	RETURN func_firm;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_msg(format text, args character[])
RETURNS xml AS
$BODY$
DECLARE
	ret XML;
BEGIN
	ret := utils.get_format(format, args);
	ret := xmlelement(name error_msg, ret);
	RETURN ret;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

CREATE OR REPLACE FUNCTION err_hand.get_error_msg(msg text)
RETURNS xml AS
$BODY$
DECLARE
	ret XML;
BEGIN
	ret := xmlelement(name error_msg, msg);
	RETURN ret;
END;
$BODY$
LANGUAGE plpgsql IMMUTABLE;

-- ================ FINE AGGIORNAMENTO DB ==========================
RETURN 'OK';
END;
$BODY_FUNC_UPD$
LANGUAGE plpgsql VOLATILE;

SELECT db_upds.ortas_mgr_0_01_000_upd_02();
