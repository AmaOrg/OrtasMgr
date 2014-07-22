
START TRANSACTION ISOLATION LEVEL SERIALIZABLE, READ WRITE;

CREATE SCHEMA OrtasMgr;

SET search_path TO ORTASMGR,"$user",public;

CREATE DOMAIN OrtasMgr.GiacenzaMinimaProdotto AS INTEGER CONSTRAINT GiacenzaMinimaProdotto_Unsigned_Chk CHECK (VALUE >= 0);

CREATE DOMAIN OrtasMgr.QuantitaProdottoOrdinato AS INTEGER CONSTRAINT QuantitaProdottoOrdinato_Unsigned_Chk CHECK (VALUE >= 0);

CREATE DOMAIN OrtasMgr.QuantitaSpedizioneProdottoOrdine AS INTEGER CONSTRAINT QuantitaSpedizioneProdottoOrdine_Unsigned_Chk CHECK (VALUE >= 0);

CREATE TABLE OrtasMgr.Cliente
(
	nomeCliente CHARACTER VARYING NOT NULL,
	codiceCliente CHARACTER VARYING NOT NULL,
	idCliente SERIAL NOT NULL,
	CONSTRAINT Cliente_PK PRIMARY KEY(nomeCliente),
	CONSTRAINT Cliente_UC1 UNIQUE(codiceCliente),
	CONSTRAINT Cliente_UC2 UNIQUE(idCliente)
);

CREATE TABLE OrtasMgr.Prodotto
(
	nomeProdotto CHARACTER VARYING NOT NULL,
	codiceProdotto CHARACTER VARYING NOT NULL,
	giacenzaMinimaProdotto OrtasMgr.GiacenzaMinimaProdotto NOT NULL,
	idProdotto SERIAL NOT NULL,
	CONSTRAINT Prodotto_UC1 UNIQUE(idProdotto),
	CONSTRAINT Prodotto_UC2 UNIQUE(codiceProdotto),
	CONSTRAINT Prodotto_PK PRIMARY KEY(nomeProdotto)
);

CREATE TABLE OrtasMgr.Spedizione
(
	letteraDiVetturaSpedizione CHARACTER VARYING NOT NULL,
	codiceSpedizione CHARACTER VARYING NOT NULL,
	dataSpedizione TIMESTAMP NOT NULL,
	idSpedizione SERIAL NOT NULL,
	note CHARACTER VARYING NOT NULL,
	CONSTRAINT Spedizione_UC1 UNIQUE(idSpedizione),
	CONSTRAINT Spedizione_UC2 UNIQUE(codiceSpedizione),
	CONSTRAINT Spedizione_PK PRIMARY KEY(letteraDiVetturaSpedizione)
);

CREATE TABLE OrtasMgr.Ordine
(
	idOrdine SERIAL NOT NULL,
	cliente CHARACTER VARYING NOT NULL,
	codiceOrdine CHARACTER VARYING NOT NULL,
	dataOrdine TIMESTAMP NOT NULL,
	tipoMovimentazione CHARACTER VARYING NOT NULL,
	CONSTRAINT Ordine_PK PRIMARY KEY(idOrdine),
	CONSTRAINT Ordine_UC UNIQUE(codiceOrdine)
);

CREATE TABLE OrtasMgr.StatoOrdine
(
	labelStatoOrdine CHARACTER VARYING NOT NULL,
	codiceStatoOrdine CHARACTER VARYING NOT NULL,
	descrizioneStatoOrdine CHARACTER VARYING NOT NULL,
	idStatoOrdine SERIAL NOT NULL,
	CONSTRAINT StatoOrdine_UC1 UNIQUE(idStatoOrdine),
	CONSTRAINT StatoOrdine_UC2 UNIQUE(codiceStatoOrdine),
	CONSTRAINT StatoOrdine_PK PRIMARY KEY(labelStatoOrdine)
);

CREATE TABLE OrtasMgr.OrdineProdotto
(
	ordine INTEGER NOT NULL,
	prodotto CHARACTER VARYING NOT NULL,
	quantitaProdottoOrdinato OrtasMgr.QuantitaProdottoOrdinato NOT NULL,
	statoOrdineProdotto CHARACTER VARYING NOT NULL,
	CONSTRAINT OrdineProdotto_PK PRIMARY KEY(ordine, prodotto)
);

CREATE TABLE OrtasMgr.SpedizioneProdottoOrdine
(
	ordine INTEGER NOT NULL,
	prodotto CHARACTER VARYING NOT NULL,
	spedizione CHARACTER VARYING NOT NULL,
	quantitaSpedizioneProdottoOrdine OrtasMgr.QuantitaSpedizioneProdottoOrdine NOT NULL,
	CONSTRAINT SpedizioneProdottoOrdine_PK PRIMARY KEY(spedizione, prodotto, ordine)
);

CREATE TABLE OrtasMgr.StatoOrdineProdotto
(
	labelStatoOrdineProdotto CHARACTER VARYING NOT NULL,
	codiceStatoOrdineProdotto CHARACTER VARYING NOT NULL,
	idStatoOrdineProdotto SERIAL NOT NULL,
	descrizioneStatoOrdineProdotto CHARACTER VARYING,
	CONSTRAINT StatoOrdineProdotto_UC1 UNIQUE(idStatoOrdineProdotto),
	CONSTRAINT StatoOrdineProdotto_UC2 UNIQUE(codiceStatoOrdineProdotto),
	CONSTRAINT StatoOrdineProdotto_PK PRIMARY KEY(labelStatoOrdineProdotto)
);

CREATE TABLE OrtasMgr.TipoMovimentazione
(
	labelTipoMovimentazione CHARACTER VARYING NOT NULL,
	codiceTipoMovimentazione CHARACTER VARYING NOT NULL,
	idTipoMovimentazione SERIAL NOT NULL,
	descrizioneTipoMovimentazione CHARACTER VARYING,
	CONSTRAINT TipoMovimentazione_UC1 UNIQUE(idTipoMovimentazione),
	CONSTRAINT TipoMovimentazione_UC2 UNIQUE(codiceTipoMovimentazione),
	CONSTRAINT TipoMovimentazione_PK PRIMARY KEY(labelTipoMovimentazione)
);

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_FK1 FOREIGN KEY (cliente) REFERENCES OrtasMgr.Cliente (nomeCliente) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.Ordine ADD CONSTRAINT Ordine_FK2 FOREIGN KEY (tipoMovimentazione) REFERENCES OrtasMgr.TipoMovimentazione (labelTipoMovimentazione) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.OrdineProdotto ADD CONSTRAINT OrdineProdotto_FK1 FOREIGN KEY (ordine) REFERENCES OrtasMgr.Ordine (idOrdine) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.OrdineProdotto ADD CONSTRAINT OrdineProdotto_FK2 FOREIGN KEY (prodotto) REFERENCES OrtasMgr.Prodotto (nomeProdotto) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.OrdineProdotto ADD CONSTRAINT OrdineProdotto_FK3 FOREIGN KEY (statoOrdineProdotto) REFERENCES OrtasMgr.StatoOrdineProdotto (labelStatoOrdineProdotto) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.SpedizioneProdottoOrdine ADD CONSTRAINT SpedizioneProdottoOrdine_FK1 FOREIGN KEY (spedizione) REFERENCES OrtasMgr.Spedizione (letteraDiVetturaSpedizione) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.SpedizioneProdottoOrdine ADD CONSTRAINT SpedizioneProdottoOrdine_FK2 FOREIGN KEY (prodotto) REFERENCES OrtasMgr.Prodotto (nomeProdotto) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE OrtasMgr.SpedizioneProdottoOrdine ADD CONSTRAINT SpedizioneProdottoOrdine_FK3 FOREIGN KEY (ordine) REFERENCES OrtasMgr.Ordine (idOrdine) ON DELETE RESTRICT ON UPDATE RESTRICT;

COMMIT WORK;
