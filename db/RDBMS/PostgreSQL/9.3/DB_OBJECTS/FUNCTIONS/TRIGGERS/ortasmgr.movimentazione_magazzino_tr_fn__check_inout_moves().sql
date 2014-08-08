CREATE OR REPLACE FUNCTION ortasmgr.movimentazione_magazzino_tr_fn__check_inout_moves() 
RETURNS TRIGGER 
--SET search_path TO ortasmgr, err_hand
AS
$BODY_FUNC_TR$
DECLARE
	-- == DESCRIPTION FUNCTION PARAMETERS ===============
	-- In questa sezione sono presenti le descrizioni dei parametri di ingresso 
	-- della funzione.
	-- Per ogni parametro e spiegato il suo significato, i valori validi, il 
	-- comportamento della funzione in corrispondenza dei valori non validi,
	-- la relazione con gli altri parametri di input della funzione.
	
	-- contiene il nome della funzione
	func_name TEXT = 'movimentazione_magazzino_tr_fn__check_inout_moves';
	
	-- contiene i nomi ordinati dei parametri in ingresso della funzione
	func_prms TEXT[] := ARRAY []::TEXT[];
	
	-- == ERROR HANDLING VARS ===============
	-- In questa sezione sono dichiarate tutte le variabili necessarie alla 
	-- gestione degli errori.
	-- Se una funzione non gestisce gli errori, e consigliabile rimuovere questa
	-- sezione in modo da rendere il codice meno "pensante" da leggere.
	
	-- contiene la rappresentazione in stringa degli argomenti della funzione
	func_args TEXT[] := ARRAY []::TEXT[];
	
	-- Necessaria in caso di errore.
	-- Contiene un xml con la firma della funzione e gli argomenti che hanno
	-- generato l'errore.
	func XML := err_hand.get_error_func(func_name, func_prms, func_args);
	
	-- Chiavi dei tipi di errore che la funzione puo generare.
	
	ASSERTION_FAILED TEXT := 'ASSERT_FAILED';
	QTA_MOV_MAG_SUP_QTA_LOTTO TEXT := 'QTA_MOV_MAG_SUP_QTA_LOTTO';
	SCA_MAG_SUP_CAR_MAG TEXT := 'SCA_MAG_SUP_CAR_MAG';
	DISP_LOTTO_NEGATIVA TEXT := 'DISP_LOTTO_NEGATIVA';
	QTA_LOTTO_INF_DISP_LOTTO TEXT := 'QTA_LOTTO_INF_DISP_LOTTO';
	
	-- variabili utili per la gestione di errori differiti.
	-- Gli errori differiti non vengono lanciati immediatamente al momento della 
	-- loro individuazione.
	-- Gli errori differiti, quando identificati, vengono segnalati impostanto a 
	-- TRUE la variabile <c>err</c>
	-- Negli errori differiti, dopo aver individuato l'errore, si prosegue con la 
	-- funzione nella ricerca di altri
	-- errori (di solito della stessa tipologia).
	-- Il messaggio degli errori differiti sono una sorta di sommario di tutti gli
	-- errori individuati.
	err BOOLEAN; -- Deve contenere TRUE se si verifica un errore, altrimenti FALSE.
	err_msg TEXT; 
	err_msg_txt TEXT;
	err_data TEXT; -- contiene i dati relativi alll'errore differito
	
	-- === DEGUG VARS ==============================
	-- In questa sezione devono essere dichiarate le variabili utilizzate solo 
	-- quando il debug e attivo.
	
	-- Abilita/disabilita i RAISE NOTICE di debug.
	-- Dovrebbe essere sempre FALSE nelle funzioni in ambiente di produzione.
	do_debug BOOLEAN := FALSE;
	
	-- === ARGOMENTI FACOLTATIVI =============
	-- in questa sezione vanno le variabili che sostituiscono (all'interno della 
	-- funzione) i parametri di ingresso facoltativi.
	-- la necessita di queste variabili deriva dal fatto che i parametri di
	-- ingresso non possono essere ri-assegnate all'interno della funzione.
	
	-- === VARS ==============================
	-- In questa sezione vanno tutte le variabili necessarie allo scopo primario 
	-- della funzione
	
	-- >>>> MEANINGFULL VARS
	-- In questa sezione vanno le variabili il cui nome danno un significato al 
	-- valore che contengono.
	-- NOTA:
	-- Le variabili il cui nome possono andare in conflitto con il nome di colonna
	-- di qualche tabella
	-- devono avere il prefisso v_
	
	qta_lotto ortasmgr.UINTEGER;
	sum_qta_car INTEGER;
	sum_qta_sca INTEGER;
	cod_lotto TEXT;
	disp_lotto ortasmgr.UINTEGER;
	v_key TEXT;
	v_func XML;
	v_data XML;
	v_msg XML;
	
	
	-- >>>> TEMP VARS
	-- In questa sezione vanno le variabili temporanee per le quali è più
	-- importante il loro tipo del loro significato.
	
	rec RECORD;
BEGIN
	/*
	*	Nei commenti successivi, si consideri le variabili con i significati indicati
	*	----------------+---------------------------
	*	NomeVariabile		SignificatoVariabile
	*	----------------+---------------------------
	*	QtaLotto				Quantità di lotto prodotto
	*	QtaCar				Quantità di lotto caricato in uno qualsiasi dei magazzini
	*	QtaScar				Quantità di lotto scariato da uno qualsiasi dei magazzini
	*	QtaPrel				Quantità di lotto prelevato da uno qualsiasi dei magazzini
	*	QtaReso				Quantità di lotto restituito da un cliente e carcicato in uno qualsiasi dei magazzini
	*/

	FOR rec IN SELECT * FROM ortasmgr.lotto
	LOOP
		qta_lotto := rec.qta;																		
		cod_lotto := rec.cod;
		
		sum_qta_car := (SELECT SUM(mm.qta)
						FROM ortasmgr.movimentazione_magazzino AS mm INNER JOIN ortasmgr.tipo_movimentazione_magazzino AS tmm ON mm.tipo = tmm.label
						WHERE tmm.is_entrata = TRUE AND tmm.modifica_disp_lotto = FALSE);
		
		sum_qta_sca	:= (SELECT SUM(mm.qta)
						FROM ortasmgr.movimentazione_magazzino AS mm INNER JOIN ortasmgr.tipo_movimentazione_magazzino AS tmm ON mm.tipo = tmm.label
						WHERE tmm.is_entrata = FALSE AND tmm.modifica_disp_lotto = FALSE);
						
		-- Per ogni lotto, i giro magazzino movimentino quantità pari (o inferiore) a quella del lotto iniziale
		/*
		*	TEORIA
		*	// I giro magazzino devono movimentare per una quantità pari a qualla del lotto
		*	QtaLotto = SUM(QtaCar) + SUM(QtaScar)
		*
		*	IF(QtaLotto = SUM(QtaCar) + SUM(QtaScar))THEN
		*		TUTTO OK
		*	ELSE IF(QtaLotto > SUM(QtaCar) + SUM(QtaScar))THEN
		*		ALERT
		*	ELSE IF(QtaLotto < SUM(QtaCar) + SUM(QtaScar))THEN
		*		ERRORE
		*	END IF;
		*/
		-- IDEA PER IL FUTURO: Separare i lotti aperti dai lotti chiusi. 
		-- I lotti aperti avranno i controlli. I lotti chiusi saranno bloccati in ogni operazione.				
		
		IF(qta_lotto > sum_qta_car + sum_qta_sca)THEN
			-- INSERIRE ALERT DA VISUALIZZARE ALL'UTENTE
		ELSIF(qta_lotto < sum_qta_car + sum_qta_sca)THEN
			-- ERRORE
			-- Quali informazioni sono importanti da visualizzare all'utente programmatore?
			-- Dove si è verificato l'errore (in quale funzione? in quale riga?)
			-- Che tipo di errore si è verificato (Riferimento a null, divisione per zero, violazione di un constraint ..)
			-- Quali dati hanno generato l'errore (per esempio, in una funzione, i suoi parametri di ingresso. Il database in cui l'errore si è verificato.)
			-- Una descrizione informale del significato della violazione
			-- ESEMPI:
			-- movimentazione_magazzino_tr_fn__check_inout_moves()
			-- QTA_LOTTO_INFERIORE_A_MOV_MAG (oppure QTA_MOV_MAG_SUP_QTA_LOTTO)
			-- lotto_id = 765
			-- I giro magazzino devono movimentare per una quantità pari a qualla del lotto
			/*
			-- Fac-simile di un messaggio di errore:
			<error key="QTA_LOTTO_INFERIORE_A_MOV_MAG">
				<func>movimentazione_magazzino_tr_fn__check_inout_moves()</func>
				<func_args>movimentazione_magazzino_tr_fn__check_inout_moves()</func_args>
				<key>QTA_LOTTO_INFERIORE_A_MOV_MAG</key>
				<error_data>
					<lotto_id>765</lotto_id>
				</error_data>
				<error_msg>I giro magazzino devono movimentare per una quantità pari a quella del lotto</error_msg>
			</error>
			*/
			
			v_key  := QTA_MOV_MAG_SUP_QTA_LOTTO;
			v_func := func;
			v_data := err_hand.get_error_data( xmlelement(name lotto_id, cod_lotto) );
			v_msg  := err_hand.get_error_msg('I giro magazzino devono movimentare per una quantità pari a quella del lotto');
			RAISE EXCEPTION '%', err_hand.get_error(v_key, v_func, v_data, v_msg);
		END IF;
	
		-- Per ogni lotto, gli scarichi magazzino siano inferiore (o uguale) 
		-- alla quantità caricata
		/*
			TEORIA
			SUM(QtaCar) >= ABS(SUM(QtaScar))

			IF (SUM(QtaCar) == ABS(SUM(QtaScar))) THEN
				TUTTO OK
			ELSE IF (SUM(QtaCar) > ABS(SUM(QtaScar))) THEN
				ALERT
			ELSE IF (SUM(QtaCar) < ABS(SUM(QtaScar))) THEN
				ERRORE
			END IF;
		*/
		
		IF (sum_qta_car = ABS(sum_qta_sca))THEN
			-- OK
		ELSIF (sum_qta_car > ABS(sum_qta_sca))THEN
			-- ALERT
		ELSIF (sum_qta_car < ABS(sum_qta_sca))THEN
			-- ERRORE	
			v_key  := SCA_MAG_SUP_CAR_MAG;
			v_func := func;
			v_data := err_hand.get_error_data(xmlelement(name lotto_cod, cod_lotto));
			v_msg  := err_hand.get_error_msg('I carico magazzino di un lotto non possono essere inferiore agli scarichi magazzino');
			RAISE EXCEPTION '%', err_hand.get_error(v_key, v_func, v_data, v_msg);
		END IF;
		
		-- Per ogni lotto, la disponibilità lotto sia inferiore o uguale alla
		-- quantità di lotto iniziale
		/*
			QtaLotto >= QtaLotto + (SUM(QtaPrel) + SUM(QtaReso) + SUM(QtaScarto) + ...)
			QtaLotto >= DisLotto           dove DisLotto = QtaLotto + (SUM(QtaPrel) + ... )

			IF(QtaLotto >= DisLotto) THEN
				TUTTO OK
			ELSE IF(QtaLotto < DisLotto) THEN
				ERRORE
			END IF;

			IF(DisLotto >= 0) THEN
				TUTTO OK
			ELSE
				ERRORE
			END IF;
		*/
		
		disp_lotto := qta_lotto + (SELECT SUM(mm.qta)
						FROM ortasmgr.movimentazione_magazzino AS mm INNER JOIN ortasmgr.tipo_movimentazione_magazzino AS tmm ON mm.tipo = tmm.label
						WHERE tmm.modifica_disp_lotto = TRUE);
		
		IF(disp_lotto >= 0)THEN
			IF(qta_lotto >= disp_lotto) THEN
				-- TUTTO OK
			ELSIF(qta_lotto < disp_lotto)THEN
				--ERRORE
				v_key  := QTA_LOTTO_INF_DISP_LOTTO;
				v_func := func;
				v_data := err_hand.get_error_data(xmlelement(name lotto_cod, cod_lotto));
				v_msg  := err_hand.get_error_msg('La quantità di un lotto non può essere inferiore alla disponibilità del lotto');
				RAISE EXCEPTION '%', err_hand.get_error(v_key, v_func, v_data, v_msg);		
			END IF;
		ELSIF(disp_lotto < 0)THEN
			--ERRORE
			v_key  := DISP_LOTTO_NEGATIVA;
			v_func := func;
			v_data := err_hand.get_error_data(xmlelement(name lotto_cod, cod_lotto));
 			v_msg  := err_hand.get_error_msg('La disponibilità del lotto non può essere negativa');
			RAISE EXCEPTION '%', err_hand.get_error(v_key, v_func, v_data, v_msg);
		END IF;
	END LOOP;

	RETURN NULL;
END;
$BODY_FUNC_TR$
LANGUAGE plpgsql VOLATILE;

COMMENT ON FUNCTION ortasmgr.movimentazione_magazzino_tr_fn__check_inout_moves() IS
$COMMENT$/*
<comment on="function" type="trigger" version="0.1">
	<function>
		<func_descr one_row="Dato un lotto, questa funzione assicura la coerenza tra la quantità del lotto e le quantità coinvolte nelle movimentazioni magazzino.">
			Questo trigger assicura che:
			* Per ogni lotto, i giro magazzino movimentino quantità pari (o 
			  inferiore) a quella del lotto iniziale
			* Per ogni lotto, gli scarichi magazzino siano inferiore (o
			  uguale) alla quantità caricata
			* Per ogni lotto, la disponibilità lotto sia inferiore o uguale alla
			  quantità di lotto iniziale
		</func_descr>
		<exceptions>
			<exception key="ASSERTION_FAILED">
				Quando un punto di controllo interno alla funzione non viene soddisfatto (evidenziando un diffetto/malfunzionamento)
			</exception>
		</exceptions>
	</function>
</comment>
*/$COMMENT$;

DROP TRIGGER IF EXISTS     movimentazione_magazzino_tr_____check_inout_moves 
ON                ortasmgr.movimentazione_magazzino;
CREATE TRIGGER             movimentazione_magazzino_tr_____check_inout_moves AFTER INSERT OR UPDATE OR DELETE
ON                ortasmgr.movimentazione_magazzino                          FOR EACH STATEMENT
EXECUTE PROCEDURE ortasmgr.movimentazione_magazzino_tr_fn__check_inout_moves();
