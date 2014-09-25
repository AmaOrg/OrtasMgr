package ama.ortasMgr;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale; 
import javax.swing.*;

public class MagazzinoPanel extends EditorPanel
{
	// GUI VARS
	//
	
	private JLabel      lbl_data;
	private JLabel      lbl_codice;
	private JLabel      lbl_nome;
	private JLabel      lbl_qta;
	private JLabel      lbl_lotto;
	private JLabel      lbl_scadenza;
	private JLabel      lbl_note;
	//private JButton     btn_carica;
	//private JLabel      lbl_giac;
	private JLabel      lbl_giac_att;
	private JLabel      lbl_giac_nuova;
	private JTextArea   ta_note;
	private JTextField  tf_codice;
	private JTextField  tf_nome;
	private JTextField  tf_lotto;
	private JTextField  tf_giac_att;
	private JTextField  tf_giac_nuova;
	private JSpinner    sp_data;
	private JSpinner    sp_scad;
	private JSpinner    sp_qta;
	
	// ==========================================================================
	
	// METHODS
	//
	
	// private
	
	private void _createChildren() {
		// creo il contenuto da inserire nel pannello

		// Data 
		lbl_data = new JLabel("Data");
		
		// creo uno spinner con un DateModel
		Locale aLocale = Locale.ITALY;
		GregorianCalendar calendar = new GregorianCalendar(aLocale);
		Date _start = new Date();
		_start = calendar.getTime();
		
		SpinnerDateModel spdm;
		
		//argomenti: 
		//data corrente visualizzata
		//data minima
		//data massima utilizzabile
		//campo interessato per il cambiamento della data ->>
		//		ERA, YEAR, MONTH, WEEK_OF_YEAR, WEEK_OF_MONTH, 
		//		DAY_OF_MONTH, DAY_OF_YEAR, DAY_OF_WEEK, DAY_OF_WEEK_IN_MONTH, 
		//		AM_PM, HOUR, HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND.
		spdm = new SpinnerDateModel(_start, null, null, Calendar.DAY_OF_MONTH);
		sp_data = new JSpinner(spdm);
		
		// Codice prodotto  
		lbl_codice = new JLabel("Codice");
		tf_codice = new JTextField();
		
		// Nome Prodotto
		lbl_nome = new JLabel("Nome Prodotto");
		tf_nome = new JTextField();
		
		// Quantità 
		lbl_qta = new JLabel("Qta");
		
		//argomenti: 
		//valore iniziale visualizzato
		//valore minimo 
		//valore massimo 
		//valore di incremento/decremento della sequenza
		sp_qta = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		
		// Lotto
		lbl_lotto = new JLabel("Lotto");
		tf_lotto = new JTextField();
		
		// Scadenza
		lbl_scadenza = new JLabel("Scadenza");

		calendar = new GregorianCalendar(aLocale);
		_start = new Date();
		_start = calendar.getTime();
		
		SpinnerDateModel spdm_scad;
		spdm_scad = new SpinnerDateModel(_start, null, null, Calendar.MONTH);
		sp_scad = new JSpinner(spdm_scad);
		
		// Note
		lbl_note = new JLabel("Note");
		ta_note = new JTextArea();
		

		// Carica (btn)
		//btn_carica = new JButton("Carica");
		
		// Giacenza 
		//lbl_giac = new JLabel("Giacenza");
		
		// Giacenza Attuale 
		lbl_giac_att = new JLabel("Giacenza Attuale");
		tf_giac_att = new JTextField();
		
		// Giacenza Nuova
		lbl_giac_nuova = new JLabel("Giacenza Nuova");
		tf_giac_nuova = new JTextField();
	}
	
	private void _doLayout(JPanel p) {
		// Posiziono il contenuto nel pannello
		//
		int CHAR_WIDTH = 11; 
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		/*
		p.setOpaque(true);
		p.setBackground(Color.gray);
		
		p1.setOpaque(true);
		p1.setBackground(Color.yellow);
		
		p2.setOpaque(true);
		p2.setBackground(Color.pink);
		
		p3.setOpaque(true);
		p3.setBackground(Color.cyan);
		*/
		GroupLayout gl = new GroupLayout(p);
		p.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		// aggiungo i gruppi orizzontali
		
		GroupLayout.SequentialGroup sg;
		GroupLayout.ParallelGroup pg;
		
		sg = gl.createSequentialGroup();
		GroupLayout.Alignment h_align = GroupLayout.Alignment.LEADING;
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(p1);
		pg.addComponent(p2);
		pg.addComponent(p3);
		sg.addGroup(pg);
		
		gl.setHorizontalGroup(sg);
		
		// aggiungo i gruppi verticali
		
		sg = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p1);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p3);
		sg.addGroup(pg);
		
		gl.setVerticalGroup(sg);
		
		// inserisco i componenti nel pannello p3
		gl = new GroupLayout(p3);
		p3.setLayout(gl);

		GroupLayout.SequentialGroup x;
		GroupLayout.SequentialGroup y;

		x = gl.createSequentialGroup();
		y = gl.createSequentialGroup();

		gl.setHorizontalGroup(x);
		gl.setVerticalGroup(y);

		GroupLayout.ParallelGroup x0 = gl.createParallelGroup();
		GroupLayout.ParallelGroup x1 = gl.createParallelGroup();

		GroupLayout.ParallelGroup y0 = gl.createParallelGroup();
		GroupLayout.ParallelGroup y1 = gl.createParallelGroup();
		//GroupLayout.ParallelGroup y2 = gl.createParallelGroup();

		x.addGroup(x0);
		x.addGroup(x1);
		y.addGroup(y0);
		y.addGroup(y1);
		//y.addGroup(y2);

		// aggiungo i componenti
		int ps = GroupLayout.PREFERRED_SIZE;
		
		//x0.addComponent(lbl_giac);
		x0.addComponent(lbl_giac_att);
		x0.addComponent(tf_giac_att,CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		
		x1.addComponent(lbl_giac_nuova);
		x1.addComponent(tf_giac_nuova,CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);

		//y0.addComponent(lbl_giac);
		y0.addComponent(lbl_giac_att);
		y0.addComponent(lbl_giac_nuova);
		y1.addComponent(tf_giac_att, ps, ps, ps);
		y1.addComponent(tf_giac_nuova, ps, ps, ps);
		
		// inserisco i componenti nel pannello p2
		
		JScrollPane scroll_pane_note;
		ta_note.setRows(3);
		scroll_pane_note = new JScrollPane(ta_note);
		
		gl = new GroupLayout(p2);
		p2.setLayout(gl);

		x = gl.createSequentialGroup();
		gl.setHorizontalGroup(x);

		y = gl.createSequentialGroup();
		gl.setVerticalGroup(y);

		x0 = gl.createParallelGroup();
		x.addGroup(x0);

		x0.addComponent(lbl_note);
		x0.addComponent(scroll_pane_note);
		//x0.addComponent(btn_carica,GroupLayout.Alignment.TRAILING,
		//								CHAR_WIDTH*6, CHAR_WIDTH*6, CHAR_WIDTH*8);

		y0 = gl.createParallelGroup();
		y1 = gl.createParallelGroup();
		//GroupLayout.ParallelGroup y2 = gl.createParallelGroup();
		//y2 = gl.createParallelGroup();
		y.addGroup(y0);
		y.addGroup(y1);
		//y.addGroup(y2);

		y0.addComponent(lbl_note);
		y1.addComponent(scroll_pane_note, ps, ps, ps);
		//y2.addComponent(btn_carica, ps, ps, ps);
		
		// inserisco i componenti nel pannello p1
		
		gl = new GroupLayout(p1);
		p1.setLayout(gl);

		x = gl.createSequentialGroup();
		gl.setHorizontalGroup(x);

		y = gl.createSequentialGroup();
		gl.setVerticalGroup(y);

		x0 = gl.createParallelGroup();
		x.addGroup(x0);
		
		x1 = gl.createParallelGroup();
		x.addGroup(x1);
		
		GroupLayout.ParallelGroup x2 = gl.createParallelGroup();
		x2 = gl.createParallelGroup();
		x.addGroup(x2);
		
		GroupLayout.ParallelGroup x3 = gl.createParallelGroup();
		x3 = gl.createParallelGroup();
		x.addGroup(x3);
		
		GroupLayout.ParallelGroup x4 = gl.createParallelGroup();
		x4 = gl.createParallelGroup();
		x.addGroup(x4);
		
		GroupLayout.ParallelGroup x5 = gl.createParallelGroup();
		x5 = gl.createParallelGroup();
		x.addGroup(x5);
		
		x0.addComponent(lbl_data);
		x0.addComponent(sp_data,CHAR_WIDTH*11, CHAR_WIDTH*11, CHAR_WIDTH*11);
		x1.addComponent(lbl_codice);
		x1.addComponent(tf_codice, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		x2.addComponent(lbl_nome);
		x2.addComponent(tf_nome, CHAR_WIDTH*33, CHAR_WIDTH*33, Short.MAX_VALUE);
		x3.addComponent(lbl_qta);
		x3.addComponent(sp_qta, CHAR_WIDTH*6, CHAR_WIDTH*6, CHAR_WIDTH*8);
		x4.addComponent(lbl_lotto);
		x4.addComponent(tf_lotto, CHAR_WIDTH*8, CHAR_WIDTH*8, CHAR_WIDTH*10);
		x5.addComponent(lbl_scadenza);
		x5.addComponent(sp_scad, CHAR_WIDTH*8, CHAR_WIDTH*8, CHAR_WIDTH*8);
		
		y0 = gl.createParallelGroup();
		y1 = gl.createParallelGroup();

		y.addGroup(y0);
		y.addGroup(y1);

		y0.addComponent(lbl_data);
		y0.addComponent(lbl_codice);
		y0.addComponent(lbl_nome);
		y0.addComponent(lbl_qta);
		y0.addComponent(lbl_lotto);
		y0.addComponent(lbl_scadenza);

		y1.addComponent(sp_data, ps, ps, ps);
		y1.addComponent(tf_codice, ps, ps, ps);
		y1.addComponent(tf_nome, ps, ps, ps);
		y1.addComponent(sp_qta, ps, ps, ps);
		y1.addComponent(tf_lotto, ps, ps, ps);
		y1.addComponent(sp_scad, ps, ps, ps);
		
	}
	
	// protected
	
	protected void initContentPanel(JPanel pnl_content) {
		// creo il pannello da inserire nella finestra
		JPanel p = pnl_content;

		this._createChildren();
		this._doLayout(p); 
	}
	
	// public 
	
	public MagazzinoPanel() {
		super();
	}
	
	public void setData(GregorianCalendar value) {
		this.sp_data.setValue(value);
		
		//Date d = value.getTime();
		//this.sp_data.setValue(d);
	}
	public GregorianCalendar getData() {
		GregorianCalendar value = (GregorianCalendar)this.sp_data.getValue();
		return value;
		
		// GregorianCalendar value = new GregorianCalendar();
		// Date d = (Date)this.sp_data.getValue();
		// value.setTime(d);
		// return value;
	}
	
	public void setCodice(String value) {
		this.tf_codice.setText(value);
	}
	public String getCodice() {
		return this.tf_codice.getText();
	}
	
	public String getNome() {
		String value = this.tf_nome.getText();
		return value;
	}
	public void setNome(String value) {
		this.tf_nome.setText(value);
	}

	public String getNote() {
		String value = this.ta_note.getText();
		return value;
	}
	public void setNote(String value) {
		this.ta_note.setText(value);
	}

	public String getLotto() {
		String value = this.tf_lotto.getText();
		return value;
	}
	public void setLotto(String value) {
		this.tf_lotto.setText(value);
	}
	
	public int getGiacAttuale() {
		String str = this.tf_giac_att.getText();
		int value = Integer.parseInt(str);
		return value;
	}
	public void setGiacAttuale(int value) {
		String str = Integer.toString(value);
		this.tf_giac_att.setText(str);
	}
	
	public int getGiacNuova() {
		String str = this.tf_giac_nuova.getText();
		int value = Integer.parseInt(str);
		return value;
	}
	public void setGiacNuova(int value) {
		String str = Integer.toString(value);
		this.tf_giac_nuova.setText(str);
	}
	
	public void setScadenza(GregorianCalendar value) {
		this.sp_scad.setValue(value);
		
		//Date d = value.getTime();
		//this.sp_scad.setValue(d);
	}
	public GregorianCalendar getScadenza() {
		GregorianCalendar value = (GregorianCalendar)this.sp_scad.getValue();
		return value;
		
		// GregorianCalendar value = new GregorianCalendar();
		// Date d = (Date)this.sp_scad.getValue();
		// value.setTime(d);
		// return value;
	}
	
	public int getQta() {
		int value = (int)this.sp_qta.getValue();
		return value;
	}
	public void setQta(int value) {
		this.sp_qta.setValue(value);
	}
}