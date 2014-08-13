CREATE FUNCTION db_upds.ortas_mgr_0_01_000_upd_01() RETURNS TEXT AS
$BODY_FUNC_UPD$
DECLARE
	v_upd_name TEXT := 'ortas_mgr_0_01_000_upd_01';
	v_upd_note TEXT := 'init db tables';
	
	-- upd_id INTEGER;
	-- upd_name TEXT;
	-- upd_data TIMESTAMP;
	-- upd_note TEXT;
BEGIN
	/*
	upd_executed := (
		SELECT COUNT(*) = 1 AS upd_executed
		FROM ortasmgr_upds.db_upds AS upd
		WHERE upd.upd_name = 'upd_0_001_000_n00'
		);
	
	SELECT COUNT(*) = 1 AS upd_executed
	INTO upd_executed
	FROM ortasmgr_upds.db_upds AS upd
	WHERE upd.upd_name = 'upd_0_001_000_n00';
	
	SELECT INTO upd_id, upd_name, upd_data, upd_note
		*
	FROM ortasmgr_upds.db_upds AS upd
	WHERE upd.upd_name = 'upd_0_001_000_n00';
	*/
	
-- SE (aggiornamento_db_gia_eseguito) ALLORA
--		Comunico che l'aggiornamento è già stato eseguito
--		RETURN;
-- FINE SE;

	IF( db_upds.is_upd_executed(v_upd_name, v_upd_note, TRUE) )THEN
		RAISE NOTICE 'Aggiornamento db % gia eseguito', v_upd_name;
		RETURN 'ERR';
	END IF;
	
-- Procedo con l'esecuzione dell'aggiornamento db

CREATE SCHEMA OrtasMgr;

SET LOCAL search_path TO ORTASMGR,"$user",public;

CREATE DOMAIN OrtasMgr.UINTEGER AS INTEGER CONSTRAINT Unsigned_Integer_Chk CHECK (VALUE >= 0);

CREATE TABLE OrtasMgr.Cliente
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,
	CONSTRAINT Cliente_pk PRIMARY KEY(nome),
	CONSTRAINT Cliente_uc_cod UNIQUE(cod),
	CONSTRAINT Cliente_uc_id UNIQUE(id)
);

CREATE TABLE OrtasMgr.Prodotto
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,
	giac_min OrtasMgr.UINTEGER NOT NULL,
	is_prod_int BOOLEAN NOT NULL,
	CONSTRAINT Prodotto_pk PRIMARY KEY(nome),
	CONSTRAINT Prodotto_uc_id UNIQUE(id),
	CONSTRAINT Prodotto_uc_cod UNIQUE(cod)
);

CREATE TABLE OrtasMgr.Spedizione
(
	id SERIAL NOT NULL,
	ldv VARCHAR,
	data TIMESTAMP NOT NULL,
	note VARCHAR NOT NULL,
	CONSTRAINT Spedizione_pk PRIMARY KEY(id),
	CONSTRAINT Spedizione_uc_ldv UNIQUE(ldv)
);

CREATE TABLE OrtasMgr.Ordine
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	data TIMESTAMP NOT NULL,
	cliente VARCHAR NOT NULL,
	tipo VARCHAR NOT NULL,
	stato VARCHAR NOT NULL,
	CONSTRAINT Ordine_pk PRIMARY KEY(cod),
	CONSTRAINT Ordine_uc_id UNIQUE(id)
);

CREATE TABLE OrtasMgr.stato_ordine
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	label VARCHAR NOT NULL,
	descr VARCHAR NOT NULL,
	CONSTRAINT stato_ordine_pk PRIMARY KEY(label),
	CONSTRAINT stato_ordine_uc_id UNIQUE(id),
	CONSTRAINT stato_ordine_uc_cod UNIQUE(cod)
	
);

CREATE TABLE OrtasMgr.ordine_prodotto
(
	id SERIAL NOT NULL,
	ordine VARCHAR NOT NULL,
	prodotto VARCHAR NOT NULL,
	qta OrtasMgr.UINTEGER NOT NULL,
	stato VARCHAR NOT NULL,
	CONSTRAINT ordine_prodotto_pk PRIMARY KEY(ordine, prodotto),
	CONSTRAINT ordine_prodotto_uc_id UNIQUE(id)
);

CREATE TABLE OrtasMgr.stato_ordine_prodotto
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	label VARCHAR NOT NULL,
	descr VARCHAR NOT NULL,
	CONSTRAINT stato_ordine_prodotto_pk PRIMARY KEY(label),
	CONSTRAINT stato_ordine_prodotto_uc_id UNIQUE(id),
	CONSTRAINT stato_ordine_prodotto_uc_cod UNIQUE(cod)
);

CREATE TABLE OrtasMgr.tipo_ordine
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	label VARCHAR NOT NULL,
	descr VARCHAR NOT NULL,
	CONSTRAINT tipo_ordine_pk PRIMARY KEY(label),
	CONSTRAINT tipo_ordine_uc_id UNIQUE(id),
	CONSTRAINT tipo_ordine_uc_cod UNIQUE(cod)
);

CREATE TABLE OrtasMgr.Magazzino
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	nome VARCHAR NOT NULL,
	CONSTRAINT Magazzino_pk PRIMARY KEY(nome),
	CONSTRAINT Magazzino_uc_id UNIQUE(id),
	CONSTRAINT Magazzino_uc_cod UNIQUE(cod)
);

CREATE TABLE OrtasMgr.tipo_movimentazione_magazzino
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	label VARCHAR NOT NULL,
	is_entrata BOOLEAN NOT NULL,
	modifica_disp_lotto BOOLEAN NOT NULL,
	is_carico_iniz BOOLEAN NOT NULL,
	descr VARCHAR NOT NULL,
	CONSTRAINT tipo_movimentazione_magazzino_pk PRIMARY KEY(label),
	CONSTRAINT tipo_movimentazione_magazzino_uc_id UNIQUE(id),
	CONSTRAINT tipo_movimentazione_magazzino_uc_cod UNIQUE(cod)
);

CREATE TABLE OrtasMgr.Lotto
(
	id SERIAL NOT NULL,
	cod VARCHAR NOT NULL,
	prodotto VARCHAR NOT NULL,
	data_scad TIMESTAMP NOT NULL,
	qta OrtasMgr.UINTEGER NOT NULL,
	CONSTRAINT Lotto_pk PRIMARY KEY(cod),
	CONSTRAINT Lotto_uc_id UNIQUE(id)
);

CREATE TABLE OrtasMgr.movimentazione_magazzino
(
	id SERIAL NOT NULL,
	data TIMESTAMP NOT NULL,
	lotto VARCHAR NOT NULL,
	magazzino VARCHAR NOT NULL,
	tipo VARCHAR NOT NULL,
	qta OrtasMgr.UINTEGER NOT NULL,
	note VARCHAR NOT NULL,
	CONSTRAINT movimentazione_magazzino_pk PRIMARY KEY(lotto, magazzino, data),
	CONSTRAINT movimentazione_magazzino_uc_id UNIQUE(id)
);

CREATE TABLE OrtasMgr.prelievo_magazzino
(
	id SERIAL NOT NULL,
	lotto VARCHAR NOT NULL,
	magazzino VARCHAR NOT NULL,
	data TIMESTAMP NOT NULL,
	ordine VARCHAR NOT NULL,
	prodotto VARCHAR NOT NULL,
	spedizione INTEGER NOT NULL,
	CONSTRAINT prelievo_magazzino_pk PRIMARY KEY(lotto, magazzino, data),
	CONSTRAINT prelievo_magazzino_uc_id UNIQUE(id)
);

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_fk_cliente
FOREIGN KEY (cliente) REFERENCES OrtasMgr.Cliente (nome)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_fk_tipo
FOREIGN KEY (tipo) REFERENCES OrtasMgr.tipo_ordine (label) 
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_fk_stato
FOREIGN KEY (stato) REFERENCES OrtasMgr.stato_ordine (label) 
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.ordine_prodotto ADD CONSTRAINT ordine_prodotto_fk_ordine
FOREIGN KEY (ordine) REFERENCES OrtasMgr.Ordine (cod)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.ordine_prodotto ADD CONSTRAINT ordine_prodotto_fk_prodotto
FOREIGN KEY (prodotto) REFERENCES OrtasMgr.Prodotto (nome)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.ordine_prodotto ADD CONSTRAINT ordine_prodotto_fk_stato
FOREIGN KEY (stato) REFERENCES OrtasMgr.stato_ordine_prodotto (label)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.Lotto ADD CONSTRAINT Lotto_fk_prodotto
FOREIGN KEY (prodotto) REFERENCES OrtasMgr.Prodotto (nome)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.movimentazione_magazzino ADD CONSTRAINT movimentazione_magazzino_fk_lotto
FOREIGN KEY (lotto) REFERENCES OrtasMgr.Lotto (cod)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.movimentazione_magazzino ADD CONSTRAINT movimentazione_magazzino_fk_magazzino
FOREIGN KEY (magazzino) REFERENCES OrtasMgr.Magazzino (nome)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.movimentazione_magazzino ADD CONSTRAINT movimentazione_magazzino_fk_tipo
FOREIGN KEY (tipo) REFERENCES OrtasMgr.tipo_movimentazione_magazzino (label)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.prelievo_magazzino ADD CONSTRAINT prelievo_magazzino_fk_ordine_prodotto
FOREIGN KEY (ordine, prodotto) REFERENCES OrtasMgr.ordine_prodotto (ordine, prodotto)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.prelievo_magazzino ADD CONSTRAINT prelievo_magazzino_fk_spedizione
FOREIGN KEY (spedizione) REFERENCES OrtasMgr.Spedizione (id)
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE OrtasMgr.prelievo_magazzino ADD CONSTRAINT prelievo_magazzino_fk_lotto_magazzino_data
FOREIGN KEY (lotto, magazzino, data) REFERENCES OrtasMgr.movimentazione_magazzino (lotto, magazzino, data)
ON DELETE RESTRICT ON UPDATE CASCADE;

-- //////////////////////////////////
-- // IMMETTO I DATI DI DEFAULT

INSERT INTO ortasmgr.stato_ordine
       (cod , label       , descr)
VALUES ('IC', 'In corso'  , 'Un ordine è ''In corso'' quando almeno uno prodotto ha stato ''In attesa'' oppure ''In produzione''.'                               ),
       ('DS', 'Da spedire', 'Un ordine è ''Da spedire'' quando almeno un prodotto ha stato ''Da spedire'' ed i restanti hanno stato ''Spedito'' o ''Eliminato''.'),
       ('CH', 'Chiuso'    , 'Un ordine è ''Chiuso'' quando almeno un prodotto ha stato ''Spedito'' ed i restanti prodotti hanno stato ''Eliminato'''             ),
       ('EL', 'Eliminato' , 'Un ordine è ''Eliminato quanto tutti i suoi prodotti hanno stato ''Eliminato'''                                                     );
 	
INSERT INTO ortasmgr.stato_ordine_prodotto
       (cod , label, descr)
VALUES ('IA', 'In attesa', 'Un ordine prodotto è ''In attesa'' quando tale prodotto non è prodotto internamente e la richiesta del prodotto non può essere soddisfatta dalle disponibilità magazzino.'),
       ('IP', 'In produzione', 'Un ordine prodotto è ''In produzione'' quando tale prodotto è prodotto internamente e la richiesta del prodotto non può essere soddisfatta dalle disponibilità magazzino.'),
       ('DS', 'Da spedire', 'Un ordine prodotto è ''Da spedire'' se la richiesta del prodotto può essere soddisfatta dalle disponibilità di magazzino'),
       ('SP', 'Spedito', 'Un ordine prodotto è spedito quando una spezione (o più) spedizione un prelievo magazzino per l''ordine prodotto in quantità pari alla quantità ordinata'),
       ('EL', 'Eliminato', 'Un ordine prodotto è eliminato quando tale ordine viene eliminato dall''utente');

INSERT INTO ortasmgr.tipo_ordine
       (cod , label              , descr)
VALUES ('OR', 'Ordine'           , ''   ),
       ('OE', 'Ordine e-commerce', ''   );
 
INSERT INTO OrtasMgr.tipo_movimentazione_magazzino
       (cod , label            , is_entrata, modifica_disp_lotto, descr, is_carico_iniz)
VALUES ('CA', 'Carico'         , TRUE      , FALSE              , ''   , FALSE         ),
       ('SP', 'Spedizione'     , FALSE     , TRUE               , ''   , FALSE         ),
       ('ST', 'Scarto'         , FALSE     , TRUE               , ''   , FALSE         ),
       ('SC', 'Scarico'        , FALSE     , FALSE              , ''   , FALSE         ),
       ('RS', 'Reso'           , TRUE      , TRUE               , ''   , FALSE         ),
			 ('CI', 'Carico iniziale', TRUE      , TRUE               , ''   , TRUE          );
			 ; 

RETURN 'OK';
END;
$BODY_FUNC_UPD$
LANGUAGE plpgsql VOLATILE;

SELECT db_upds.ortas_mgr_0_01_000_upd_01();