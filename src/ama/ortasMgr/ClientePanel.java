package ama.ortasMgr;

import java.awt.*;
import javax.swing.*;

public class ClientePanel extends EditorPanel
{
	// GUI VARS
	//
	
	private JLabel			lbl_codice_cliente;
	private JLabel			lbl_nome_cliente;
	private JLabel			lbl_note;
	private JScrollPane	scroll_pane_note;
	private JTextArea		ta_note;
	private JTextField	tf_codice_cliente; 
	private JTextField	tf_nome_cliente;

	// ==========================================================================
	
	// METHODS
	//
	
	// private
	
	private void _createChildren() {
		// creo il contenuto da inserire nel pannello
		//

		// Codice cliente
		lbl_codice_cliente = new JLabel("Codice Cliente");
		tf_codice_cliente = new JTextField();

		// Nome cliente
		lbl_nome_cliente = new JLabel("Nome Cliente");
		tf_nome_cliente = new JTextField();

		// Note
		lbl_note = new JLabel("Note");
		ta_note = new JTextArea();
	}

	
	
	private void _doLayout(JPanel p) {
		int CHAR_WIDTH = 11;
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

	//p1.setOpaque(true);
	//p1.setBackground(Color.yellow);
		
	//p2.setOpaque(true);
	//p2.setBackground(Color.pink);
		
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
		pg.addComponent(p1);
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		gl.setHorizontalGroup(sg);
		
		// aggiungo i gruppi verticali
		sg = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;
		
		pg = gl.createParallelGroup();
		pg.addComponent(p1);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup();
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		gl.setVerticalGroup(sg);
		
		// inserisco i componenti nel pannello p1
		gl = new GroupLayout(p1);
		p1.setLayout(gl);
		
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
		
		x.addGroup(x0);
		x.addGroup(x1);
		
		y.addGroup(y0);
		y.addGroup(y1);
		
		// aggiungo i componenti
		int ps = GroupLayout.PREFERRED_SIZE;
		
		x0.addComponent(lbl_codice_cliente);
		x0.addComponent(tf_codice_cliente,CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		x1.addComponent(lbl_nome_cliente);
		x1.addComponent(tf_nome_cliente,CHAR_WIDTH*33, CHAR_WIDTH*33, Short.MAX_VALUE);
		
		y0.addComponent(lbl_codice_cliente);
		y0.addComponent(lbl_nome_cliente);
		y1.addComponent(tf_codice_cliente, ps, ps, ps);
		y1.addComponent(tf_nome_cliente, ps, ps, ps);
		
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

		y0 = gl.createParallelGroup();
		y1 = gl.createParallelGroup();
		y.addGroup(y0);
		y.addGroup(y1);

		y0.addComponent(lbl_note);
		y1.addComponent(scroll_pane_note, ps, ps, ps);
	}

	// protected
	
	protected void initContentPanel(JPanel pnl_content) {
	// creo il pannello da inserire nella finestra
		JPanel p = pnl_content;

		this._createChildren();
		this._doLayout(p); 
	}
	
	// public

	public ClientePanel() {
		super();
	}
	
	public String getCodice() {
		String value = this.tf_codice_cliente.getText();
		return value;
	}
	public void setCodice(String value) {
		this.tf_codice_cliente.setText(value);
	}
	
	public String getNome() {
		String value = this.tf_nome_cliente.getText();
		return value;
	}
	public void setNome(String value) {
		this.tf_nome_cliente.setText(value);
	}
	
	public String getNote() {
		String value = this.ta_note.getText();
		return value;
	}
	public void setNote(String value) {
		this.ta_note.setText(value);
	}
}
