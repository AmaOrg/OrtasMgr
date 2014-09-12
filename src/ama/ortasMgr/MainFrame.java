package ama.ortasMgr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout;

public class MainFrame extends JFrame
{
	public MainFrame() {
		this.initGui();
	}

	private void initGui() {
		// Configuro il Frame
		this.initGui_ConfigFrame();

		// Creo la MenuBar
		this.initGui_MenuBar();
	}

	private void initGui_ConfigFrame() {
		super.setTitle("Ortas Manager");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(500, 200);
	}

	private void initGui_MenuBar() {
		// Creiamo la barra dei menu della finestra
		JMenuBar menubar;
		menubar = new JMenuBar();
		
		// Creo il Menu "Sistema"
		this.initGui_MenuBar_Sistema(menubar);
		// Creo il Menu "Ordini"
		this.initGui_MenuBar_MenuOrdini(menubar);
		// Creo il Menu "Magazzino"
		this.initGui_MenuBar_MenuMagazzino(menubar);
		// Creo il Menu "Prodotti"
		this.initGui_MenuBar_MenuProdotti(menubar);
		// Creo il Menu "Reports"			
		this.initGui_MenuBar_MenuReports(menubar);

		// Aggiungo il JMenuBar appena creato al JFrame
		super.setJMenuBar(menubar);
	}

	private void initGui_MenuBar_Sistema(JMenuBar menubar) {
		// Creo il menu "Sistema"
		JMenu menu_sistema;

		menu_sistema = new JMenu("Sistema");
		menu_sistema.setMnemonic(KeyEvent.VK_S);

		menubar.add(menu_sistema);

		// Creo la voce "Esci" del menu "Sistema"
		JMenuItem menuitem_sistema_esci;
		menuitem_sistema_esci = new JMenuItem("Esci", KeyEvent.VK_E);

		menu_sistema.add(menuitem_sistema_esci);

		Sistema_Esci_ActionListener seal = new Sistema_Esci_ActionListener();
		seal.parent_frame = this;
		menuitem_sistema_esci.addActionListener(seal);

		// Creo la voce "LogOut" del menu Sistema
		JMenuItem menuitem_sistema_logout;
		menuitem_sistema_logout = new JMenuItem("LogOut", KeyEvent.VK_L);

		menu_sistema.add(menuitem_sistema_logout);
	}
	
	private void initGui_MenuBar_MenuOrdini(JMenuBar menubar) {
		// Credo il menu "Ordini"
		JMenu menu_ordini;
		
		menu_ordini = new JMenu("Ordini");		
		menu_ordini.setMnemonic(KeyEvent.VK_O);

		menubar.add(menu_ordini);
  
		// Creo la voce "Cerca" del menu "Ordini"
		JMenuItem menuitem_ordini_cerca;
		menuitem_ordini_cerca = new JMenuItem("Cerca", KeyEvent.VK_C);

		menu_ordini.add(menuitem_ordini_cerca); 
	
		// Creo la voce "Nuovo" del menu "Ordini"
		JMenuItem menuitem_ordini_nuovo;
		menuitem_ordini_nuovo = new JMenuItem("Nuovo", KeyEvent.VK_N);

		menu_ordini.add(menuitem_ordini_nuovo);
	}

	private void initGui_MenuBar_MenuMagazzino(JMenuBar menubar) {
		// Creo il menu "Magazzino"
		JMenu menu_magazzino;
		
		menu_magazzino = new JMenu("Magazzino");
		menu_magazzino.setMnemonic(KeyEvent.VK_M);

		menubar.add(menu_magazzino);
		
		// Creo la voce "Cerca" del menu "Magazzino"
		JMenuItem menuitem_magazzino_cerca;
		menuitem_magazzino_cerca = new JMenuItem("Cerca", KeyEvent.VK_C);

		menu_magazzino.add(menuitem_magazzino_cerca);

		// Creo la voce "Movimentazioni" del menu "Magazzino"
		JMenuItem menuitem_magazzino_movimentazioni;
		menuitem_magazzino_movimentazioni = new JMenuItem("Movimentazioni", KeyEvent.VK_M);

		menu_magazzino.add(menuitem_magazzino_movimentazioni);

		// Creo la voce "Carica" del menu "Magazzino	
		JMenuItem menuitem_magazzino_carica;
		menuitem_magazzino_carica = new JMenuItem("Carica", KeyEvent.VK_A);

		menu_magazzino.add(menuitem_magazzino_carica);
	}

	private void initGui_MenuBar_MenuProdotti(JMenuBar menubar) {
		// Creo il menu "Prodotti"
		JMenu menu_prodotti;

		menu_prodotti = new JMenu("Prodotti");
		menu_prodotti.setMnemonic(KeyEvent.VK_P);

		menubar.add(menu_prodotti);

		// Creo la voce "Cerca" del menu "Prodotti"
		JMenuItem menuitem_prodotti_cerca;
		menuitem_prodotti_cerca = new JMenuItem("Cerca", KeyEvent.VK_C);

		menu_prodotti.add(menuitem_prodotti_cerca);

		// Creo la voce "Nuovo" del menu "Prodotti"
		JMenuItem menuitem_prodotti_nuovo;
		menuitem_prodotti_nuovo = new JMenuItem("Nuovo", KeyEvent.VK_N);

		menu_prodotti.add(menuitem_prodotti_nuovo);

		Prodotti_Nuovo_ActionListener pnal = new Prodotti_Nuovo_ActionListener();
		menuitem_prodotti_nuovo.addActionListener(pnal);
	}

	private void initGui_MenuBar_MenuReports(JMenuBar menubar) {
		// Creo il menu "Reports"
		JMenu menu_reports;

		menu_reports = new JMenu("Reports");
		menu_reports.setMnemonic(KeyEvent.VK_R);

		menubar.add(menu_reports);
	}
	
	private class Sistema_Esci_ActionListener implements ActionListener
	{
		public JFrame parent_frame;

		public void chiediConfermaUscita() {
			//crea una finestra di dialogo composta dal titolo title,
			//un pulsante di chiusura,
			//un messaggio message, dei pulsanti dati da optionType, 
			//il tipo di messaggio dato da messageType;
			//public static int showConfirmDialog(	Component parentComponent,
			//																			Object message,
			//																			String title,
			//																			int optionType,
			//																			int messageType)
			int n = JOptionPane.showConfirmDialog(this.parent_frame,
																					 "Vuoi uscire dal programma?",
																					 "Avviso",
																					 JOptionPane.YES_NO_OPTION, 
																					 JOptionPane.WARNING_MESSAGE);
			if(n == JOptionPane.YES_OPTION) 
			{
				this.avviaChiusuraProgramma();
			}
			else if(n == JOptionPane.NO_OPTION) 
			{
				return;
			}
		}

		public void avviaChiusuraProgramma() {
			// Provo ad uscire dal programma con exit.
			// Se exit fallisce 2 volte di seguito, forzo l'uscita con halt.
			// Se anche halt fallisce, comunico all'utente l'impossibilità di uscire dal programma.

			for(int c=0; c<2 ; c++ )
			{
				try
				{
					System.exit(0);
					// Simulazione fallimento metodo exit
					// SecurityException my_se = new SecurityException("failed exit number " + c);
					// throw my_se; 
				}
				catch(SecurityException se)
				{
					try
					{
						if(c == 1)
						{
							Runtime.getRuntime().halt(0);
							// Simulazione fallimento metodo halt
							// SecurityException my_se = new SecurityException("faild exit number " + c);
							// throw my_se; 
						}
					}
					catch(SecurityException seh)
					{
						/*
						crea una finestra di dialogo composta dal titolo title,
						un pulsante di chiusura, il messaggio message, un pulsante OK,
						il tipo del messaggio con messageType;
						public static void showMessageDialog(Component parentComponent,
																		 Object message,
																		 String title, 
																		 int messageType)*/
						JOptionPane.showMessageDialog(this.parent_frame,
																"Non è stato possibile completare la chiusura del programma", 
																"ERRORE", 
																JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}

		public void actionPerformed(ActionEvent ae) {
			this.chiediConfermaUscita();
		}
	}

	private class Prodotti_Nuovo_ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae) {
			// creo la finestra tramite la quale inserire il nuovo prodotto
			JFrame f = new JFrame();
			f.setTitle("Anagrafica Prodotto (Nuovo)");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setSize(600, 200);
			f.setLocationRelativeTo(null); // finestra nel centro schermo

			// creo il pannello da inserire nella finestra
			// ProdottoPanel p = new ProdottoPanel();
			ProdottoPanel3 p = new ProdottoPanel3();

			f.add(p, BorderLayout.CENTER);
			f.setVisible(true);
		}
	}
}
