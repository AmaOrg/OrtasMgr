package ama.ortasMgr;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale; 
import javax.swing.*;

public class MagazzinoCarica extends EditorPanel
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
	private JButton     btn_carica;
	private JLabel      lbl_giac;
	private JLabel      lbl_giac_att;
	private JLabel      lbl_giac_nuova;
	private JTextField  tf_codice;
	private JTextField  tf_nome;
	private JTextField  tf_lotto;
	private JTextField  tf_note;
	private JTextField  tf_giac_att;
	private JTextField  tf_giac_nuova;
	private JSpinner    sp_data;
	private JSpinner    sp_scad;
	private JSpinner    sp_qta;
	private SpinnerDateModel spdm;
	private SpinnerDateModel spdm_scad;
	
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
		
		spdm_scad = new SpinnerDateModel(_start, null, null, Calendar.MONTH);
		sp_scad = new JSpinner(spdm_scad);
		
		// Note
		lbl_note = new JLabel("Note");
		tf_note = new JTextField();
		
		// Carica (btn)
		btn_carica = new JButton("Carica");
		
		// Giacenza 
		lbl_giac = new JLabel("Giacenza");
		
		// Giacenza Attuale 
		lbl_giac_att = new JLabel("Attuale");
		tf_giac_att = new JTextField();
		
		// Giacenza Nuova
		lbl_giac_nuova = new JLabel("Nuova");
		tf_giac_nuova = new JTextField();
	}
	
		private void _doLayout(JPanel p) {
		// Posiziono il contenuto nel pannello
		//
		GroupLayout gl = new GroupLayout(p);
		gl.setAutoCreateGaps(true); // gap automatico tra componenti
		gl.setAutoCreateContainerGaps(true); // gap automatico con container
		p.setLayout(gl);

		this._doLayout_HGroup(gl);
		this._doLayout_VGroup(gl);
	}

	private void _doLayout_HGroup(GroupLayout gl) {
		// aggiungo i gruppi orizzontali
		//
		GroupLayout.SequentialGroup sg_h = gl.createSequentialGroup();
		GroupLayout.Alignment h_align   = GroupLayout.Alignment.LEADING;
		GroupLayout.Alignment h_align_t = GroupLayout.Alignment.TRAILING;

		GroupLayout.ParallelGroup pg;
		int CHAR_WIDTH = 11; 

		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_data);
		pg.addComponent(sp_data, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_codice);
		pg.addComponent(tf_codice, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_nome);
		pg.addComponent(tf_nome, CHAR_WIDTH*33, CHAR_WIDTH*33, CHAR_WIDTH*50);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_qta);
		pg.addComponent(sp_qta, CHAR_WIDTH*6, CHAR_WIDTH*6, CHAR_WIDTH*8);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_lotto);
		pg.addComponent(tf_lotto, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_scadenza);
		pg.addComponent(sp_scad, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_note);
		pg.addComponent(tf_note, CHAR_WIDTH*43, CHAR_WIDTH*43, CHAR_WIDTH*60);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align_t);
		pg.addComponent(btn_carica);
		pg.addComponent(btn_carica, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align_t);
		pg.addComponent(lbl_giac);
		pg.addComponent(lbl_giac);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_giac_att);
		pg.addComponent(tf_giac_att, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_giac_nuova);
		pg.addComponent(tf_giac_nuova, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);
		
		gl.setHorizontalGroup(sg_h);
	}
	
	
	private void _doLayout_VGroup(GroupLayout gl) {
	// aggiungo i gruppi verticali
	
		GroupLayout.SequentialGroup sg_v = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;

		GroupLayout.ParallelGroup pg;

		pg = gl.createParallelGroup(v_align);
		pg.addComponent(lbl_data);
		pg.addComponent(lbl_codice);
		pg.addComponent(lbl_nome);
		pg.addComponent(lbl_qta);
		pg.addComponent(lbl_lotto);
		pg.addComponent(lbl_scadenza);
		pg.addComponent(lbl_note);
		pg.addComponent(btn_carica);   // -->>?????
		pg.addComponent(lbl_giac);
		pg.addComponent(lbl_giac_att);
		pg.addComponent(lbl_giac_nuova);

		sg_v.addGroup(pg);

		pg = gl.createParallelGroup(v_align);
		pg.addComponent(sp_data);
		pg.addComponent(tf_codice);
		pg.addComponent(tf_nome);
		pg.addComponent(sp_qta);
		pg.addComponent(tf_lotto);
		pg.addComponent(sp_scad);
		pg.addComponent(tf_note);
		pg.addComponent(btn_carica);      // -->>?????
		pg.addComponent(lbl_giac);
		pg.addComponent(tf_giac_att);
		pg.addComponent(tf_giac_nuova);
		
		sg_v.addGroup(pg);
		
		gl.setVerticalGroup(sg_v);
	}
	
	// protected
	
		protected void initContentPanel(JPanel pnl_content) {
		// creo il pannello da inserire nella finestra
		JPanel p = pnl_content;

		this._createChildren();
		this._doLayout(p);

		// TODO -->> questo sarà il Btn_carica
		//Btn_Salva_ActionListener btns = new Btn_Salva_ActionListener();
		//super.btn_salva.addActionListener(btns);  
	}
	
	// public 
	
	public MagazzinoCarica() {
		super();
	}
}