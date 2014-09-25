package ama.ortasMgr;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale; 

import javax.swing.*;

@SuppressWarnings("serial")
public class OrdinePanel extends EditorPanel
{
	// PRIVATE VARS
	
	private Object[][] _table_data;
	
	// GUI VARS
	//
	
	private JButton btn_cerca;
	private JButton btn_aggiungi;
	private JComboBox cb_tipo_ord; 
	private JLabel lbl_data_ordine;
	private JLabel lbl_cod_ordine;
	private JLabel lbl_tipo_ord;
	private JLabel lbl_stato;
	private JLabel lbl_cod_cliente;
	private JLabel lbl_nome_cliente;
	private JLabel lbl_note;
	private JLabel lbl_dettaglio_ordine;
	private JTable table;
	private JTextArea ta_note;
	private JTextField tf_cod_ordine;
	private JTextField tf_cod_cliente;
	private JTextField tf_nome_cliente;
	private JTextField tf_stato_att;
	private JSpinner sp_data_ordine; 
	
	private JTable table_custom;
	
	// ==========================================================================
	
	// METHODS
	//
	
	// private
	
	private void _createChildren() {
		
		// creo il contenuto da inserire nel pannello
		
		// Data Ordine
		lbl_data_ordine = new JLabel("Data Ordine");
		Locale locale = Locale.ITALY;
		GregorianCalendar calendar = new GregorianCalendar(locale);
		Date _start = new Date();
		_start = calendar.getTime();
		
		SpinnerDateModel spdm;
		spdm = new SpinnerDateModel(_start, null, null, Calendar.DAY_OF_MONTH);
		sp_data_ordine = new JSpinner(spdm);
		
		// Codice Ordine
		lbl_cod_ordine = new JLabel("Cod. Ordine");
		tf_cod_ordine = new JTextField();
		
		// Tipo ordine
		lbl_tipo_ord = new JLabel("Tipo Ordine");
		cb_tipo_ord = new JComboBox();
		
		// Stato 
		lbl_stato = new JLabel("Stato");
		tf_stato_att = new JTextField("Stato Attuale");
		tf_stato_att.setEditable(false);
		
		// Codice Cliente
		lbl_cod_cliente = new JLabel("Cod Cliente");
		tf_cod_cliente = new JTextField();
		
		// Nome Cliente
		lbl_nome_cliente = new JLabel("Nome Cliente");
		tf_nome_cliente = new JTextField();
		
		// Cerca
		btn_cerca = new JButton("Cerca");
		
		// Aggiungi
		btn_aggiungi = new JButton("Aggiungi");
		
		// Note
		lbl_note = new JLabel("Note");
		ta_note = new JTextArea();
		
		// Dettaglio ordine
		lbl_dettaglio_ordine = new JLabel("Dettaglio ordine");
	}
	
	private void _doLayout(JPanel p) {
		int CHAR_WIDTH = 11;
		
		JPanel p0 = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
/*
		p0.setOpaque(true);
		p0.setBackground(Color.orange);
		
		p1.setOpaque(true);
		p1.setBackground(Color.yellow);
		
		p2.setOpaque(true);
		p2.setBackground(Color.pink);
		
		p3.setOpaque(true);
		p3.setBackground(Color.cyan);
		
		p4.setOpaque(true);
		p4.setBackground(Color.green);
*/		
		GroupLayout gl = new GroupLayout(p);
		p.setLayout(gl);
		
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		// aggiungo i gruppi orizzontali
		GroupLayout.SequentialGroup sg;
		GroupLayout.ParallelGroup pg;
		
		sg = gl.createSequentialGroup();
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		GroupLayout.Alignment h_align = GroupLayout.Alignment.LEADING;
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(p0);
		pg.addComponent(p1);
		pg.addComponent(p2);
		pg.addComponent(p3);
		pg.addComponent(p4);
		sg.addGroup(pg);
		
		gl.setHorizontalGroup(sg);
		
		// aggiungo i gruppi verticali
		sg = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p0);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p1);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p3);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p4);
		sg.addGroup(pg);
		
		gl.setVerticalGroup(sg);
		
		// inserisco i componenti nel pannello p2
		gl = new GroupLayout(p2);
		p2.setLayout(gl);
		
		GroupLayout.SequentialGroup x;
		GroupLayout.SequentialGroup y;
		
		x = gl.createSequentialGroup();
		y = gl.createSequentialGroup();
		
		gl.setHorizontalGroup(x);
		gl.setVerticalGroup(y);
		
		GroupLayout.ParallelGroup x0 = gl.createParallelGroup();
		GroupLayout.ParallelGroup x1 = gl.createParallelGroup();
		GroupLayout.ParallelGroup x2 = gl.createParallelGroup();
		GroupLayout.ParallelGroup x3 = gl.createParallelGroup();
		
		GroupLayout.ParallelGroup y0 = gl.createParallelGroup();
		GroupLayout.ParallelGroup y1 = gl.createParallelGroup(GroupLayout.Alignment.BASELINE);
		
		x.addGroup(x0);
		x.addGroup(x1);
		x.addGroup(x2);
		x.addGroup(x3);
		
		y.addGroup(y0);
		y.addGroup(y1);
		
		// aggiungo i componenti
		int ps = GroupLayout.PREFERRED_SIZE;
		
		x0.addComponent(lbl_cod_cliente);
		x0.addComponent(tf_cod_cliente,CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		x1.addComponent(lbl_nome_cliente);
		x1.addComponent(tf_nome_cliente,CHAR_WIDTH*33, CHAR_WIDTH*33, Short.MAX_VALUE);
		x2.addComponent(btn_cerca);
		x3.addComponent(btn_aggiungi);
		
		y0.addComponent(lbl_cod_cliente);
		y0.addComponent(lbl_nome_cliente);
		y1.addComponent(tf_cod_cliente, ps, ps, ps);
		y1.addComponent(tf_nome_cliente, ps, ps, ps);
		y1.addComponent(btn_cerca);
		y1.addComponent(btn_aggiungi);
	
		// inserisco i componenti nel pannello p3
		
		JScrollPane scroll_pane_note;
		ta_note.setRows(3);
		scroll_pane_note = new JScrollPane(ta_note);
		
		gl = new GroupLayout(p3);
		p3.setLayout(gl);

		x = gl.createSequentialGroup();
		gl.setHorizontalGroup(x);

		y = gl.createSequentialGroup();
		gl.setVerticalGroup(y);

		x0 = gl.createParallelGroup();
		x.addGroup(x0);

		x0.addComponent(lbl_note);
		x0.addComponent(scroll_pane_note);

		y0 = gl.createParallelGroup();
		y1 = gl.createParallelGroup();
		y.addGroup(y0);
		y.addGroup(y1);

		y0.addComponent(lbl_note);
		y1.addComponent(scroll_pane_note, ps, ps, ps);
		
		// inserisco i componenti nel pannello p1
		gl = new GroupLayout(p1);
		p1.setLayout(gl);
		
		x = gl.createSequentialGroup();
		y = gl.createSequentialGroup();
		
		gl.setHorizontalGroup(x);
		gl.setVerticalGroup(y);
		
		x0 = gl.createParallelGroup();
		x1 = gl.createParallelGroup();
		x2 = gl.createParallelGroup();
		
		y0 = gl.createParallelGroup();
		y1 = gl.createParallelGroup(GroupLayout.Alignment.BASELINE);
		
		x.addGroup(x0);
		x.addGroup(x1);
		x.addGroup(x2);
		
		y.addGroup(y0);
		y.addGroup(y1);
		
		// aggiungo i componenti
		
		x0.addComponent(lbl_data_ordine);
		x0.addComponent(sp_data_ordine,CHAR_WIDTH*11, CHAR_WIDTH*11, CHAR_WIDTH*11);
		x1.addComponent(lbl_cod_ordine);
		x1.addComponent(tf_cod_ordine, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		x2.addComponent(lbl_stato);
		x2.addComponent(tf_stato_att,CHAR_WIDTH*33, CHAR_WIDTH*33, Short.MAX_VALUE);

		y0.addComponent(lbl_data_ordine);
		y0.addComponent(lbl_cod_ordine);
		y0.addComponent(lbl_stato);

		y1.addComponent(sp_data_ordine, ps, ps, ps);
		y1.addComponent(tf_cod_ordine, ps, ps, ps);
		y1.addComponent(tf_stato_att, ps, ps, ps);
		
		// inserisco i componenti nel pannello p4
		
		table_custom = new JTable();
		
		//Creo array di intestazioni
		String columns_headers[] = 
				{
					"Cod. Prodotto", "Nome Prodotto", "Qta'", "Lotto", "Scadenza", "Spedizioni"
				};

		//Creo matrice di dati
		Object[][] data = { 
												{"1", "Blu" , "3", "", "01/01/2014", ""},
												{"2", "Red" , "000", "", "", ""},
												{"2", "Red" , "2", "", "", ""},
												{"2", "Red" , "2", "", "", ""},
												{"3", "Pink", "1", "", "", ""}
											};

		//Setto dati e intestazioni della tabella
		this.table_custom.setModel(
			new javax.swing.table.DefaultTableModel(data, columns_headers) {
				@Override
				public boolean isCellEditable(int rowIndex, int mColIndex) {
					return false;
				}
			}
		);
		
		//Imposto allinamento delle celle al centro
		javax.swing.table.DefaultTableCellRenderer renderer;
		renderer = new javax.swing.table.DefaultTableCellRenderer();
		
		renderer.setHorizontalAlignment(
			javax.swing.table.DefaultTableCellRenderer.CENTER);

		this.table_custom.setDefaultRenderer(
			this.table_custom.getColumnClass(0), renderer);
			
		this.table_custom.updateUI();
		
		table_custom.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int i_cod_prod = 0;
		int i_nome_prod = 1;
		int i_qta = 2;
		int i_lotto = 3;
		int i_scadenza = 4;
		int i_spedizione = 5;
		
		table_custom.getColumnModel().getColumn(i_cod_prod).setPreferredWidth(CHAR_WIDTH*11);
		table_custom.getColumnModel().getColumn(i_nome_prod).setPreferredWidth(CHAR_WIDTH*33); 
		table_custom.getColumnModel().getColumn(i_qta).setPreferredWidth(CHAR_WIDTH*3);
		table_custom.getColumnModel().getColumn(i_lotto).setPreferredWidth(CHAR_WIDTH*10);
		table_custom.getColumnModel().getColumn(i_scadenza).setPreferredWidth(CHAR_WIDTH*8);
		table_custom.getColumnModel().getColumn(i_spedizione).setPreferredWidth(CHAR_WIDTH*20);
		
		table_custom.setShowVerticalLines(false);
		table_custom.setShowGrid(true);
		table_custom.setShowHorizontalLines(true);
		
		// permette il sorter sulle colonne
		table_custom.setAutoCreateRowSorter(true);
		
		JScrollPane scrollpane = new JScrollPane(table_custom);
		
		gl = new GroupLayout(p4);
		p4.setLayout(gl);
		
		x = gl.createSequentialGroup();
		gl.setHorizontalGroup(x);

		y = gl.createSequentialGroup();
		gl.setVerticalGroup(y);

		x0 = gl.createParallelGroup(GroupLayout.Alignment.CENTER);
		x.addGroup(x0);

		x0.addComponent(lbl_dettaglio_ordine);
		x0.addComponent(scrollpane);

		y0 = gl.createParallelGroup(GroupLayout.Alignment.CENTER);
		y.addGroup(y0);
		
		y1 = gl.createParallelGroup();
		y.addGroup(y1);
		
		y0.addComponent(lbl_dettaglio_ordine);
		y1.addComponent(scrollpane, 18*3, 18*4, 18*10);
		
		// inserisco i componenti nel pannello p0
		gl = new GroupLayout(p0);
		p0.setLayout(gl);
		
		x = gl.createSequentialGroup();
		y = gl.createSequentialGroup();
		
		gl.setHorizontalGroup(x);
		gl.setVerticalGroup(y);
		
		x0 = gl.createParallelGroup();
		
		y0 = gl.createParallelGroup();
		y1 = gl.createParallelGroup(GroupLayout.Alignment.BASELINE);
		
		x.addGroup(x0);
		
		y.addGroup(y0);
		y.addGroup(y1);
		
		// aggiungo i componenti
		
		x0.addComponent(lbl_tipo_ord);
		x0.addComponent(cb_tipo_ord, CHAR_WIDTH*21, CHAR_WIDTH*21, CHAR_WIDTH*26);
		
		y0.addComponent(lbl_tipo_ord);
		y1.addComponent(cb_tipo_ord); 
	}
	
	// protected
	
	protected void initContentPanel(JPanel pnl_content) {
		JPanel p = pnl_content;
		
		// p.setOpaque(true);
		// p.setBackground(Color.gray);
		
		this._createChildren();
		this._doLayout(p);
	}
	
	// public
	
	public OrdinePanel() {
		super();
	}
	
	public String getNote() {
		String value = this.ta_note.getText();
		return value;
	}
	public void setNote(String value) {
		this.ta_note.setText(value);
	}
	
	public String getCodiceOrdine() {
		String value = this.tf_cod_ordine.getText();
		return value;
	}
	public void setCodiceOrdine(String value) {
		this.tf_cod_ordine.setText(value);
	}
	
	public String getCodiceCliente() {
		String value = this.tf_cod_cliente.getText();
		return value;
	}
	public void setCodiceCliente(String value) {
		this.tf_cod_cliente.setText(value);
	}
	
	public String getNomeCliente() {
		String value = this.tf_nome_cliente.getText();
		return value;
	}
	public void setNomeCliente(String value) {
		this.tf_nome_cliente.setText(value);
	}
	
	public String getStatoAttuale() {
		String value = this.tf_stato_att.getText();
		return value;
	}
	public void getStatoAttuale(String value) {
			this.tf_stato_att.setText(value);
	}
	
	public void setDataOrdine(GregorianCalendar value) {
		this.sp_data_ordine.setValue(value);
		
		//Date d = value.getTime();
		//this.sp_data_ordine.setValue(d);
	}
	public GregorianCalendar getDataOrdine() {
		GregorianCalendar value =(GregorianCalendar)this.sp_data_ordine.getValue();
		return value;
		
		// GregorianCalendar value = new GregorianCalendar();
		// Date d = (Date)this.sp_data_ordine.getValue();
		// value.setTime(d);
		// return value;
	}
	
	public String getTipoOrdine() {
	// public Object getSelectedItem() 
	// ritorna l’elemento attualmente selezionato
		String value = (String)this.cb_tipo_ord.getSelectedItem(); 
		return value;
	}
	public void setTipoOrdine(String value) {
	/*
	public void setSelectedItem(Object anObject) 
		consente di impostare come item selezionato 
		l’oggetto anObject secondo le seguenti regole: 
		-> se anObject è presente nella lista, sarà automaticamente selezionato; 
		-> se anObject non è presente nella lista, il metodo non avrà effetto; 
		-> se anObject è non è presente nella lista, ma il combo box è editabile, 
			 lo stesso sarà visualizzato nella relativa casella di testo 
			(in quest’ultimo caso, se abbiamo per esempio un combo box editabile 
			 con gli item Rosso, Giallo, Blu e poi invochiamo tale metodo come: 
			setSelectedItem("Verde"),
			nella casella di testo relativa verrà posto il valore Verde).
	*/
		this.cb_tipo_ord.setSelectedItem(value);
	}
	
	public OrdineProdottoEntity[] getOrdiniProdotto() {
		OrdineProdottoEntity[] value;
		int n_rows = this.table.getRowCount();
		
		value = new OrdineProdottoEntity[n_rows];
		
		for(int r = 0; r < n_rows; r++)
		{
			Object[] row = this._table_data[r];
			
			OrdineProdottoEntity op = new OrdineProdottoEntity();
			
			ProdottoEntity p = new ProdottoEntity();
			p.setCodice((String)row[0]);
			p.setNome((String)row[1]);
			
			op.setProdotto(p);
			op.setQta((int)row[2]);
			
			value[r] = op;
		}
		
		return value;
	}
	public void setOrdiniProdotto(OrdineProdottoEntity[] value) {
		int n_ordini_prod = value.length;
		int columns_count = table.getColumnCount();
		
		this._table_data = new Object[n_ordini_prod][columns_count];
		
		for(int r = 0; r < n_ordini_prod; r++)
		{
			OrdineProdottoEntity op = value[r];
			ProdottoEntity p = op.getProdotto();
			
			this._table_data[r][0] = p.getCodice();
			this._table_data[r][1] = p.getNome();
			this._table_data[r][2] = op.getQta();
		}
	}

	public PrelievoMagEntity[] getPrelieviMagazzino() {
		return null;
	}
	public void setPrelieviMagazzino(PrelievoMagEntity[] value) {
		
	}
	
	public SpedizioneEntity[] getSpedizioni() {
		return null;
	}
	public void setSpedizioni(SpedizioneEntity[] value) {
	
	}
}