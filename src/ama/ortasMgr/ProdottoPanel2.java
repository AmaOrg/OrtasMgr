package ama.ortasMgr;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ProdottoPanel2 extends EditorPanel
{
	// GUI VARS
	//
	
	private JCheckBox  ckb_prod_int;
	private JLabel     lbl_codice;
	private JLabel     lbl_nome;
	private JLabel     lbl_giac;
	private JLabel     lbl_prod_interna;
	private JSpinner   sp_giac;
	private JTextField tf_codice;
	private JTextField tf_nome;
	
	// ==========================================================================
	
	// METHODS
	//
	
	// private
	
	private void _createChildren() {
		// creo il contenuto da inserire nel pannello
		//

		// Codice prodotto
		lbl_codice = new JLabel("Codice");
		tf_codice = new JTextField();

		// Nome prodotto
		lbl_nome = new JLabel("Nome");
		tf_nome = new JTextField();

		// Giacenza minima prodotto
		lbl_giac = new JLabel("Giac. min.");
		sp_giac = new JSpinner(new SpinnerNumberModel(0, 0, null, 1));
		
		boolean is_prod_interna = false;
		String lbl_prod_interna = "Prodotto internamente"; 
		
		ckb_prod_int = new JCheckBox(lbl_prod_interna, is_prod_interna);
	}

	private void _doLayout(JPanel p) {
		// Posiziono il contenuto nel pannello
		//

		// Tutorial: How to Use GroupLayout
		// http://docs.oracle.com/javase/tutorial/uiswing/layout/group.html
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		/*
		p.setOpaque(true);
		p.setBackground(Color.gray);
		
		p1.setOpaque(true);
		p1.setBackground(Color.yellow);
		
		p2.setOpaque(true);
		p2.setBackground(Color.pink);
		*/
		
		GroupLayout gl = new GroupLayout(p);
		gl.setAutoCreateGaps(true); // gap automatico tra componenti
		gl.setAutoCreateContainerGaps(true); // gap automatico con container
		p.setLayout(gl);
		
		GroupLayout gl1 = new GroupLayout(p1);
		p1.setLayout(gl1);

		GroupLayout gl2 = new GroupLayout(p2);
		p2.setLayout(gl2);

		this._doLayout_HGroup(gl, gl1, gl2, p1, p2);
		this._doLayout_VGroup(gl, gl1, gl2, p1, p2);
	}

	private void _doLayout_HGroup(
					GroupLayout gl, GroupLayout gl1, GroupLayout gl2, JPanel p1, JPanel p2) {
		// aggiungo i gruppi orizzontali
		//
		int CHAR_WIDTH = 11; 
		int ps = GroupLayout.PREFERRED_SIZE;
		
		GroupLayout.SequentialGroup sg;
		GroupLayout.ParallelGroup pg;
		
		// pannello p
		// 
		
		sg = gl.createSequentialGroup();
		
		GroupLayout.Alignment h_align = GroupLayout.Alignment.LEADING;
		
		pg = gl.createParallelGroup(h_align);
		pg.addComponent(p1);
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		gl.setHorizontalGroup(sg);
		
		// pannello p1
		//
		
		GroupLayout.SequentialGroup sg_h;
		sg_h = gl1.createSequentialGroup();

		pg = gl1.createParallelGroup(h_align);
		pg.addComponent(lbl_codice);
		pg.addComponent(tf_codice, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		sg_h.addGroup(pg);

		pg = gl1.createParallelGroup(h_align);
		pg.addComponent(lbl_nome);
		pg.addComponent(tf_nome, CHAR_WIDTH*33, CHAR_WIDTH*33, CHAR_WIDTH*50);
		sg_h.addGroup(pg);

		pg = gl1.createParallelGroup(h_align);
		pg.addComponent(lbl_giac);
		pg.addComponent(sp_giac, CHAR_WIDTH*6, CHAR_WIDTH*6, CHAR_WIDTH*8);
		sg_h.addGroup(pg);

		gl1.setHorizontalGroup(sg_h);
		
		// pannello p2
		//
		
		sg_h = gl2.createSequentialGroup();

		pg = gl2.createParallelGroup();
		pg.addComponent(ckb_prod_int);
		sg_h.addGroup(pg);

		gl2.setHorizontalGroup(sg_h);
	}

	private void _doLayout_VGroup(
					GroupLayout gl, GroupLayout gl1, GroupLayout gl2, JPanel p1, JPanel p2) {
		// aggiungo i gruppi verticali
		//
		int ps = GroupLayout.PREFERRED_SIZE;
		
		GroupLayout.SequentialGroup sg;
		GroupLayout.ParallelGroup pg;
		
		// pannello p
		//
		
		sg = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;
		
		pg = gl.createParallelGroup();
		pg.addComponent(p1);
		sg.addGroup(pg);
		
		pg = gl.createParallelGroup();
		pg.addComponent(p2);
		sg.addGroup(pg);
		
		gl.setVerticalGroup(sg);
		
		// pannello p1
		//
		
		GroupLayout.SequentialGroup sg_v = gl1.createSequentialGroup();

		pg = gl1.createParallelGroup();
		pg.addComponent(lbl_codice);
		pg.addComponent(lbl_nome);
		pg.addComponent(lbl_giac);
		sg_v.addGroup(pg);

		pg = gl1.createParallelGroup();
		pg.addComponent(tf_codice, ps, ps, ps);
		pg.addComponent(tf_nome, ps, ps, ps);
		pg.addComponent(sp_giac, ps, ps, ps);
		sg_v.addGroup(pg);

		gl1.setVerticalGroup(sg_v);

		// pannello p2
		// 
		
		sg_v = gl2.createSequentialGroup();

		pg = gl2.createParallelGroup();
		pg.addComponent(ckb_prod_int, ps, ps, ps);
		sg_v.addGroup(pg);

		gl2.setVerticalGroup(sg_v);
	}

	protected void initContentPanel(JPanel pnl_content) {
		
		// creo il pannello da inserire nella finestra
		JPanel p = pnl_content;

		this._createChildren();
		this._doLayout(p);
		/*
		Btn_Salva_ActionListener btns = new Btn_Salva_ActionListener();
		super.btn_salva.addActionListener(btns);  
		*/
	}

	private class Btn_Salva_ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			System.out.println("Ciao");
		}
	}

	// public

	public ProdottoPanel2() {
		super();
	}

	public String getCodice() {
		String value = this.tf_codice.getText();
		return value;
	}
	public void setCodice(String value) {
		this.tf_codice.setText(value);
	}
	
	public int getGiacMin() {
		int value = (int)this.sp_giac.getValue();
		return value;
	}
	public void setGiacMin(int value) {
		this.sp_giac.setValue(value);
	}
	
	public String getNome() {
		String value = this.tf_nome.getText();
		return value;
	}
	public void setNome(String value) {
		this.tf_nome.setText(value);
	}
	
	public boolean getIsProdInt() {
		boolean value = this.ckb_prod_int.isSelected();
		return value;
	}
	public void setIsProdInt(boolean value) {
		this.ckb_prod_int.setSelected(value);
	}
}
