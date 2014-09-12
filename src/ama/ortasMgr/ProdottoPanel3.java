package ama.ortasMgr;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

public class ProdottoPanel3 extends EditorPanel
{
	// GUI VARS
	//
	
	private JLabel     lbl_codice;
	private JTextField  tf_codice;
	private JLabel     lbl_nome;
	private JTextField  tf_nome;
	private JLabel     lbl_giac;
	private JSpinner    sp_giac;

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
		sg_h.addGroup(pg);

		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_nome);
		pg.addComponent(tf_nome, CHAR_WIDTH*33, CHAR_WIDTH*33, CHAR_WIDTH*50);
		sg_h.addGroup(pg);

		pg = gl.createParallelGroup(h_align);
		pg.addComponent(lbl_giac);
		pg.addComponent(sp_giac, CHAR_WIDTH*6, CHAR_WIDTH*6, CHAR_WIDTH*8);
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

		gl.setVerticalGroup(sg_v);
	}

	protected void initContentPanel(JPanel pnl_content) {
		// creo il pannello da inserire nella finestra
		JPanel p = pnl_content;

		this._createChildren();
		this._doLayout(p);

		BtnSalva_ActionListener al = new BtnSalva_ActionListener();
		al.tf_codice = this.tf_codice;
		al.tf_nome = this.tf_nome;
		al.sp_giac = this.sp_giac;
		super.btn_salva.addActionListener(al);
	}

	private class BtnSalva_ActionListener implements ActionListener
	{
		JTextField tf_codice;
		JTextField tf_nome;
		JSpinner sp_giac;

		public void actionPerformed(ActionEvent ae) {
			// Carico e registro nella JVM il driver JDBC per postgres
			try { Class.forName("org.postgresql.Driver"); }
			catch(ClassNotFoundException ex)
			{
				System.out.println(
					"Where is your PostgreSQL JDBC Driver? " +
					"Include in your library path!"); 
				ex.printStackTrace();
				return;
			}

			Connection cn = null;
			String url = "jdbc:postgresql://::1:5432/db_ortas_mgr_6";
			String user = "postgres";
			String psw = "tWcGmOKsH6POSTGRES";

			// Provo ad aprire una connessione con il database
			try { cn = DriverManager.getConnection(url, user, psw); }
			catch (SQLException e)
			{
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				return;
			}

			if (cn == null) // Se null allora connessione fallita
			{
				System.out.println("Failed to make connection!");
				return;
			}

			System.out.println("You made it, take control your database now!");

			// TODO: Inserire qui il codice per il salvataggio dei dati nel db.
			
			// Devo salvare nel db i dati inseriti dall'utente

			String codice = this.tf_codice.getText();
			String nome = this.tf_nome.getText();
			Object obj = this.sp_giac.getValue();
			String giac = obj.toString();

			System.out.println(
				"codice: " + codice + "\n" +
				"nome: " + nome + "\n" +
				"giac: " + giac + "\n");

			String q =
				"INSERT INTO ortasmgr.prodotto\n" + 
				"      (   cod      ,    nome   ,   giac_min, is_prod_int)\n" +
				"VALUES('"+codice+"', '"+nome+"', "+giac+"  , TRUE       )\n" +
				"RETURNING *";

			Statement st = null;
			ResultSet rs = null;

			try
			{
				st = cn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
				rs = st.executeQuery(q);
			}
			catch(SQLException e)
			{
				System.out.println("Error while executing query:\n" + q);
				e.printStackTrace();
				return;
			}

			int n_rows = 0;
			try { n_rows = rs.last() ? rs.getRow() : 0; }
			catch(SQLException e)
			{
				System.out.println("Error while retrieving total rows number");
				e.printStackTrace();
				return;
			}

			if(n_rows == 1)
			{
				System.out.println("Riga inserita con successo!");

				try
				{
					rs.beforeFirst();
					rs.next();
				}
				catch(SQLException e)
				{
					System.out.println("Error while moving to first row");
					e.printStackTrace();
					return;
				}

				int id = 0;
				
				try { id = rs.getInt("id"); }
				catch(SQLException e)
				{
					System.out.println("Error while retrieving field value: 'id'");
					e.printStackTrace();
					return;
				}

				System.out.println("New row id: " + id);
			}

			// Chiudo la connessione con il database
			try
			{
				rs.close();
				st.close();
				cn.close();
			}
			catch(SQLException e)
			{
				System.out.println("Can't close connection! Check output console");
				e.printStackTrace();
				return;
			}
		}
	}

	// public

	public ProdottoPanel3() {
		super();
	}
}
