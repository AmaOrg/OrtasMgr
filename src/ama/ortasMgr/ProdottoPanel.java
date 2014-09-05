package ama.ortasMgr;

import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class ProdottoPanel extends JPanel
{
	// GUI VARS
	//
	
	private JLabel     lbl_codice;
	private JTextField  tf_codice;
	private JLabel     lbl_nome;
	private JTextField  tf_nome;
	private JLabel     lbl_giac;
	private JSpinner    sp_giac;
	private JButton    btn_esci;
	private JButton    btn_modifica;
	private JButton    btn_annulla;
	private JButton    btn_salva;

	JPanel pnl_tmp;

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

		// Pulsante Esci
		btn_esci = new JButton("Esci");

		// Pulsante Modifica
		btn_modifica = new JButton("Modifica");
		
		// Pulsante Annulla
		btn_annulla = new JButton("Annulla");
		
		// Pulsante Salva
		btn_salva = new JButton("Salva");
	}

	private void _doLayout(JPanel p) {
		// Posiziono il contenuto nel pannello
		//

		// Tutorial: How to Use GroupLayout
		// http://docs.oracle.com/javase/tutorial/uiswing/layout/group.html

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
		GroupLayout.Alignment h_align = GroupLayout.Alignment.LEADING;

		GroupLayout.ParallelGroup pg;
		int CHAR_WIDTH = 11; 

		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_codice);
		pg.addComponent(tf_codice, CHAR_WIDTH*10, CHAR_WIDTH*10, CHAR_WIDTH*15);
		pg.addComponent(btn_esci);
		sg_h.addGroup(pg);

		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_nome);
		pg.addComponent(tf_nome, CHAR_WIDTH*33, CHAR_WIDTH*33, CHAR_WIDTH*50);
		pnl_tmp = new JPanel();
		FlowLayout fl = new FlowLayout(FlowLayout.TRAILING);
		fl.setVgap(0);
		pnl_tmp.setLayout(fl);
		pnl_tmp.add(btn_modifica);
		pnl_tmp.add(btn_annulla);
		// pg.addComponent(btn_modifica, GroupLayout.Alignment.CENTER);
		// pg.addComponent(btn_annulla, GroupLayout.Alignment.TRAILING);
		pg.addComponent(pnl_tmp, GroupLayout.Alignment.TRAILING);
		sg_h.addGroup(pg);

		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_giac);
		pg.addComponent(sp_giac, CHAR_WIDTH*6, CHAR_WIDTH*6, CHAR_WIDTH*8);
		pg.addComponent(btn_salva, GroupLayout.Alignment.TRAILING);
		sg_h.addGroup(pg);

		gl.setHorizontalGroup(sg_h);
	}

	private void _doLayout_VGroup(GroupLayout gl) {
		// aggiungo i gruppi verticali
		//

		GroupLayout.SequentialGroup sg_v = gl.createSequentialGroup();
		GroupLayout.Alignment v_align = GroupLayout.Alignment.BASELINE;

		GroupLayout.ParallelGroup pg;

		pg = gl.createParallelGroup(v_align);
		pg.addComponent(lbl_codice);
		pg.addComponent(lbl_nome);
		pg.addComponent(lbl_giac);
		sg_v.addGroup(pg);

		pg = gl.createParallelGroup(v_align);
		pg.addComponent(tf_codice);
		pg.addComponent(tf_nome);
		pg.addComponent(sp_giac);
		sg_v.addGroup(pg);

		pg = gl.createParallelGroup(v_align);
		pg.addComponent(btn_esci);
		pg.addComponent(pnl_tmp);
		//pg.addComponent(btn_modifica);
		//pg.addComponent(btn_annulla);
		pg.addComponent(btn_salva);
		sg_v.addGroup(pg);

		gl.setVerticalGroup(sg_v);
	}

	private void _initPanel() {
		// creo il pannello da inserire nella finestra
		JPanel p = this;

		this._createChildren();
		this._doLayout(p);
	}

	// public

	public ProdottoPanel() {
		this._initPanel();
	}
}
