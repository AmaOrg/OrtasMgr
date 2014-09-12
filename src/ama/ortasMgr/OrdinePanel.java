package ama.ortasMgr;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale; 
import javax.swing.*;

public class OrdinePanel extends EditorPanel
{
	// GUI VARS
	//
	
	private JButton btn_cerca;
	private JButton btn_aggiungi;
	private JLabel lbl_data_ordine;
	private JLabel lbl_cod_ordine;
	private JLabel lbl_mov;
	private JLabel lbl_stato;
	private JLabel lbl_stato_att;
	private JLabel lbl_cod_cliente;
	private JLabel lbl_nome_cliente;
	private JLabel lbl_note;
	private JTextField tf_cod_ordine;
	private JTextField tf_cod_cliente;
	private JTextField tf_nome_cliente;
	private JTextField tf_note;
	private JSpinner sp_data_ordine; 
	

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
	spdm = new SpinnerDateModel(_start, null, null, calendar.DAY_OF_MONTH);
	sp_data_ordine = new JSpinner(spdm);
	
	// Codice Ordine
	lbl_cod_ordine = new JLabel("Cod. Ordine");
	tf_cod_ordine = new JTextField();
	
	// Tipo Movimentazione
	lbl_mov = new JLabel("Tipo Movimentazione");
	// TODO -->> COMBO BOX
	
	
	// Stato 
	lbl_stato = new JLabel("Stato");
	lbl_stato_att = new JLabel("Stato Attuale // Si chiamerà il metodo");
	
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
	tf_note = new JTextField();
	
	// Tabella 
	// TODO

	}
	
		private void _doLayout(JPanel p) {
		int CHAR_WIDTH = 11;
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();


		p1.setOpaque(true);
		p1.setBackground(Color.yellow);
		
		p2.setOpaque(true);
		p2.setBackground(Color.pink);
		
		p3.setOpaque(true);
		p3.setBackground(Color.cyan);
		
		p4.setOpaque(true);
		p4.setBackground(Color.red);
		
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
		pg.addComponent(p4);
		sg.addGroup(pg);
		
		gl.setHorizontalGroup(sg);
		
		// aggiungo i gruppi verticali
		sg = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p1);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);;
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p3);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup(v_align);
		pg.addComponent(p4);
		sg.addGroup(pg);
		
		gl.setVerticalGroup(sg);
		
		//°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°
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
		
		
		

	}
	
	
	
	
	// protected
	
	protected void initContentPanel(JPanel pnl_content) {
		JPanel p = pnl_content;
		
		p.setOpaque(true);
		p.setBackground(Color.gray);
		
		this._createChildren();
		this._doLayout(p);
	}
	
	// public
	
	public OrdinePanel() {
		super();
	}
}