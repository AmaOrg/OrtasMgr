/* ISTRUZIONI:
- sostituire le occorrenze di %NOME_TABELLA% con il nome della tabella alla 
  quale il trigger è collegato. Nel caso di trigger leggati a più tabelle,
	non indicare alcun nome di tabella (quindi il nome del trigger inizierà
	con tr_fn__)
  
- sostituire le occorrenze di %MINI_TR_DESCR% con poche parole (magari
  abbreviate) che descrivano quello che fa' il trigger (usare il carattere 
  di underscore invece degli spazii).
  
- sostituire le occorrenze di %FUNC_OUTPUT% con il tipo di dato restituito dalla funzione
  
- Compilare la sessione SUMMARY che si trova nella zona DECLARE della funzione

- sostituire le occorrenze di %FUNC_NAME% con il nome della funzione

- sostituire le occorrenze di 
*/

CREATE FUNCTION get_disp_prod(
	in_data_ora TIMESTAMP, in_prodotto TEXT, in_lotto TEXT, in_magazzino TEXT,
	OUT  TIMESTAMP, OUT prodotto TEXT, OUT lotto TEXT,
	OUT magazzino TEXT, OUT disponibilita INTEGER)
RETURNS SETOF RECORD
$BODY_FUNC_TR$
DECLARE	
	-- == DESCRIPTION FUNCTION PARAMETERS ========================================
	-- In questa sezione sono presenti le descrizioni dei parametri di ingresso
	-- della funzione.
	-- Per ogni parametro è spiegato il suo significato, i valori validi, il
	-- comportamento della funzione in corrispondenza dei valori non validi,
	-- la relazione con gli altri parametri di input della funzione.
	
	-- contiene il nome della funzione
	func_name TEXT = '%FUNC_NAME%';
	
	-- contiene i nomi ordinati dei parametri in ingresso della funzione
	func_prms TEXT[] := ARRAY ['func_arg0 TYPE', 'func_arg1 TYPE', 'func_arg2 TYPE'];
	
	-- == ERROR HANDLING VARS ===============
	-- In questa sezione sono dichiarate tutte le variabili necessarie alla 
	-- gestione degli errori.
	-- Se una funzione non gestisce gli errori, e consigliabile rimuovere questa
	-- sezione in modo da rendere il codice meno "pensante" da leggere.
	
	-- contiene la rappresentazione in stringa degli argomenti della funzione
	func_args TEXT[] := ARRAY [func_arg0::TEXT, func_arg1::TEXT, func_arg2::TEXT];
	
	-- Necessaria in caso di errore.
	-- Contiene un xml con la firma della funzione e gli argomenti che hanno
	-- generato l'errore.
	func XML := get_error_func(func_name, func_prms, func_args);
	
	-- Chiavi dei tipi di errore che la funzione puo generare.
	
	ASSERTION_FAILED TEXT := 'ASSERT_FAILED';
	/*
	ERROR_KEY1 TEXT := 'ERROR_KEY1';
	ERROR_KEY2 TEXT := 'ERROR_KEY2';
	ERROR_KEY3 TEXT := 'ERROR_KEY3';
	*/
	
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
	-- della funzione tutte variabili il cui nome possono andare in conflitto con 
	-- il nome di colonna di qualche tabella devono avere il prefisso v_
	
	-- >>>> MEANINGFULL VARS
	-- In questa sezione vanno le variabili il cui nome danno un significato al 
	-- valore che contengono.
	
	-- >>>> TEMP VARS
	-- In questa sezione vanno le variabili temporanee per le quali è più 
	-- importante il loro tipo del loro significato.
	
	rec RECORD;
BEGIN
	
END;
$BODY_FUNC_TR$
LANGUAGE plpgsql VOLATILE;

CREATE TRIGGER		%NOME_TABELLA%_tr_____%MINI_TR_DESCR%	AFTER [ [INSERT | UPDATE | DELETE]  OR UPDATE OR DELETE]
ON								%NOME_TABELLA%												FOR EACH STATEMENT
EXECUTE PROCEDURE	%NOME_TABELLA%_tr_fn__%MINI_TR_DESCR%(/*func_arg0 TYPE, func_arg1 TYPE, func_arg2 TYPE*/);

COMMENT ON FUNCTION func_template(p_p0 INTEGER, p_p1 TEXT, p_p2 BOOLEAN) IS $COMMENT$/*
<comment on="function" type="function" version="0.1">
	<function>
		<func_descr one_row="ONE ROW DESCRIPTION">MORE DETAILED DESCRIPTION</func_descr>
		<params>
			<param name="p_p0" direction="in">
				<param_descr one_row="ONE ROW DESCRIPTION">MORE DETAILED DESCRIPTION</param_descr>
				<required>YES | NO<required>
				<allowed_values>
					<allowed_value value="NULL">Non ammesso | Applica default | Significato personalizzato</allowed_value>
				</allowed_values>
				<param_exceptions>
					<param_exception key="ERROR_KEY1">When</param_exception>
				</param_exceptions>
			</param>
			<param name="p_p1" direction="in">param description</param>
			<param name="p_p2" direction="out">param description</param>
		</params>
		<returns>Come è fatto l'output di questa funzione? Quale sono i significati degli output possibili?</returns>
		<exceptions>
			<exception key="ASSERTION_FAILED">Quando un punto di controllo interno alla funzione non viene soddisfatto (evidenziando un diffetto/malfunzionamento)</exception>
		</exceptions>
		<usage>In quali situazioni potrebbe essere utile questa funzione?</usage>
		<example>Alcuni esempi pratici di utilizzo della funzione</example>
		<notes>Osservazioni aggiuntive non adatte ad alcuna sezione particolare</notes>
		<attention>
			Indicare eventuali rischi collegati all'utilizzo di questa funzione.
			Esempio di rischi sono:
			* perdite di dati
			* modifica dati accidentale
			* parametri di ingresso particolarmente ostici ai quali l'utilizzatore 
				della funzione dovrebbe stare attento
		</attention>
		<see_also>
			<see>
				Ci sono altre funzioni logicamente legate a questa funzione alle quali 
				converrebbe darci un occhio?
			<see>
		</see_also>
	</function>
</comment>
*/$COMMENT$