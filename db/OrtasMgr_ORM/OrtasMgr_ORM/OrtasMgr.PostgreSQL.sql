
START TRANSACTION ISOLATION LEVEL SERIALIZABLE, READ WRITE;

CREATE SCHEMA OrtasMgr;

SET search_path TO ORTASMGR,"$user",public;

CREATE DOMAIN OrtasMgr.GiacenzaMinimaProdotto AS INTEGER CONSTRAINT GiacenzaMinimaProdotto_Unsigned_Chk CHECK (VALUE >= 0);

CREATE DOMAIN OrtasMgr.QuantitaProdottoOrdinato AS INTEGER CONSTRAINT QuantitaProdottoOrdinato_Unsigned_Chk CHECK (VALUE >= 0);

CREATE DOMAIN OrtasMgr.QuantitaLotto AS INTEGER CONSTRAINT QuantitaLotto_Unsigned_Chk CHECK (VALUE >= 0);

CREATE TABLE OrtasMgr.Cliente
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	nome CHARACTER VARYING NOT NULL,
	CONSTRAINT Cliente_PK PRIMARY KEY(nome),
	CONSTRAINT Cliente_UC1 UNIQUE(cod),
	CONSTRAINT Cliente_UC2 UNIQUE(id)
);

CREATE TABLE OrtasMgr.Prodotto
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	nome CHARACTER VARYING NOT NULL,
	giac_min OrtasMgr.GiacenzaMinimaProdotto NOT NULL,
	is_prod_int BOOLEAN NOT NULL,
	CONSTRAINT Prodotto_UC1 UNIQUE(id),
	CONSTRAINT Prodotto_UC2 UNIQUE(cod),
	CONSTRAINT Prodotto_PK PRIMARY KEY(nome)
);

CREATE TABLE OrtasMgr.Spedizione
(
	id SERIAL NOT NULL,
	data TIMESTAMP NOT NULL,
	note CHARACTER VARYING NOT NULL,
	ldv CHARACTER VARYING,
	CONSTRAINT Spedizione_PK PRIMARY KEY(id),
	CONSTRAINT Spedizione_UC UNIQUE(ldv)
);

CREATE TABLE OrtasMgr.Ordine
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	data TIMESTAMP NOT NULL,
	cliente CHARACTER VARYING NOT NULL,
	tipo CHARACTER VARYING NOT NULL,
	stato CHARACTER VARYING NOT NULL,
	CONSTRAINT Ordine_UC UNIQUE(id),
	CONSTRAINT Ordine_PK PRIMARY KEY(cod)
);

CREATE TABLE OrtasMgr.StatoOrdine
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	label CHARACTER VARYING NOT NULL,
	descr CHARACTER VARYING NOT NULL,
	CONSTRAINT StatoOrdine_UC1 UNIQUE(id),
	CONSTRAINT StatoOrdine_UC2 UNIQUE(cod),
	CONSTRAINT StatoOrdine_PK PRIMARY KEY(label)
);

CREATE TABLE OrtasMgr.OrdineProdotto
(
	id SERIAL NOT NULL,
	ordine CHARACTER VARYING NOT NULL,
	prodotto CHARACTER VARYING NOT NULL,
	qta OrtasMgr.QuantitaProdottoOrdinato NOT NULL,
	stato CHARACTER VARYING NOT NULL,
	CONSTRAINT OrdineProdotto_PK PRIMARY KEY(ordine, prodotto),
	CONSTRAINT OrdineProdotto_UC UNIQUE(id)
);

CREATE TABLE OrtasMgr.StatoOrdineProdotto
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	label CHARACTER VARYING NOT NULL,
	descr CHARACTER VARYING NOT NULL,
	CONSTRAINT StatoOrdineProdotto_UC1 UNIQUE(id),
	CONSTRAINT StatoOrdineProdotto_UC2 UNIQUE(cod),
	CONSTRAINT StatoOrdineProdotto_PK PRIMARY KEY(label)
);

CREATE TABLE OrtasMgr.TipoOrdine
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	label CHARACTER VARYING NOT NULL,
	descr CHARACTER VARYING NOT NULL,
	CONSTRAINT TipoOrdine_UC1 UNIQUE(id),
	CONSTRAINT TipoOrdine_UC2 UNIQUE(cod),
	CONSTRAINT TipoOrdine_PK PRIMARY KEY(label)
);

CREATE TABLE OrtasMgr.Magazzino
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	nome CHARACTER VARYING NOT NULL,
	CONSTRAINT Magazzino_PK PRIMARY KEY(nome),
	CONSTRAINT Magazzino_UC1 UNIQUE(id),
	CONSTRAINT Magazzino_UC2 UNIQUE(cod)
);

CREATE TABLE OrtasMgr.TipoMovimentazioneMagazzino
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	label CHARACTER VARYING NOT NULL,
	is_entrata BOOLEAN NOT NULL,
	descr CHARACTER VARYING NOT NULL,
	modifica_disp_lotto BOOLEAN NOT NULL,
	CaricoIniziale BOOLEAN NOT NULL,
	CONSTRAINT TipoMovimentazioneMagazzino_UC1 UNIQUE(id),
	CONSTRAINT TipoMovimentazioneMagazzino_UC2 UNIQUE(cod),
	CONSTRAINT TipoMovimentazioneMagazzino_PK PRIMARY KEY(label)
);

CREATE TABLE OrtasMgr.Lotto
(
	id SERIAL NOT NULL,
	cod CHARACTER VARYING NOT NULL,
	prodotto CHARACTER VARYING NOT NULL,
	data_scad TIMESTAMP NOT NULL,
	qta OrtasMgr.QuantitaLotto NOT NULL,
	CONSTRAINT Lotto_UC UNIQUE(id),
	CONSTRAINT Lotto_PK PRIMARY KEY(cod)
);

CREATE TABLE OrtasMgr.MovimentazioneMagazzino
(
	id SERIAL NOT NULL,
	data TIMESTAMP NOT NULL,
	lotto CHARACTER VARYING NOT NULL,
	magazzino CHARACTER VARYING NOT NULL,
	tipo CHARACTER VARYING NOT NULL,
	qta INTEGER NOT NULL,
	note CHARACTER VARYING NOT NULL,
	CONSTRAINT MovimentazioneMagazzino_PK PRIMARY KEY(lotto, magazzino, data),
	CONSTRAINT MovimentazioneMagazzino_UC UNIQUE(id)
);

CREATE TABLE OrtasMgr.PrelievoMagazzino
(
	id SERIAL NOT NULL,
	lotto CHARACTER VARYING NOT NULL,
	magazzino CHARACTER VARYING NOT NULL,
	data TIMESTAMP NOT NULL,
	ordine CHARACTER VARYING NOT NULL,
	prodotto CHARACTER VARYING NOT NULL,
	spedizione INTEGER NOT NULL,
	CONSTRAINT PrelievoMagazzino_UC UNIQUE(id),
	CONSTRAINT PrelievoMagazzino_PK PRIMARY KEY(lotto, magazzino, data)
);

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_FK1 FOREIGN KEY (cliente) REFERENCES OrtasMgr.Cliente (nome) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_FK2 FOREIGN KEY (tipo) REFERENCES OrtasMgr.TipoOrdine (label) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_FK3 FOREIGN KEY (stato) REFERENCES OrtasMgr.StatoOrdine (label) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.OrdineProdotto ADD CONSTRAINT OrdineProdotto_FK1 FOREIGN KEY (ordine) REFERENCES OrtasMgr.Ordine (cod) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.OrdineProdotto ADD CONSTRAINT OrdineProdotto_FK2 FOREIGN KEY (prodotto) REFERENCES OrtasMgr.Prodotto (nome) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.OrdineProdotto ADD CONSTRAINT OrdineProdotto_FK3 FOREIGN KEY (stato) REFERENCES OrtasMgr.StatoOrdineProdotto (label) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.Lotto ADD CONSTRAINT Lotto_FK FOREIGN KEY (prodotto) REFERENCES OrtasMgr.Prodotto (nome) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.MovimentazioneMagazzino ADD CONSTRAINT MovimentazioneMagazzino_FK1 FOREIGN KEY (lotto) REFERENCES OrtasMgr.Lotto (cod) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.MovimentazioneMagazzino ADD CONSTRAINT MovimentazioneMagazzino_FK2 FOREIGN KEY (magazzino) REFERENCES OrtasMgr.Magazzino (nome) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.MovimentazioneMagazzino ADD CONSTRAINT MovimentazioneMagazzino_FK3 FOREIGN KEY (tipo) REFERENCES OrtasMgr.TipoMovimentazioneMagazzino (label) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.PrelievoMagazzino ADD CONSTRAINT PrelievoMagazzino_FK1 FOREIGN KEY (ordine, prodotto) REFERENCES OrtasMgr.OrdineProdotto (ordine, prodotto) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.PrelievoMagazzino ADD CONSTRAINT PrelievoMagazzino_FK2 FOREIGN KEY (spedizione) REFERENCES OrtasMgr.Spedizione (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.PrelievoMagazzino ADD CONSTRAINT PrelievoMagazzino_FK3 FOREIGN KEY (lotto, magazzino, data) REFERENCES OrtasMgr.MovimentazioneMagazzino (lotto, magazzino, data) ON DELETE RESTRICT ON UPDATE RESTRICT;

COMMIT WORK;
