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

//import javax.swing.event.MenuListener;
//import javax.swing.event.MenuEvent;

public class MainFrame extends JFrame
{
	public MainFrame()
	{
		this.initGui();
	}

	private void initGui()
	{
		// Configuro il Frame
		this.initGui_ConfigFrame();

		// Creo la MenuBar
		this.initGui_MenuBar();
	}

	private void initGui_ConfigFrame()
	{
		super.setTitle("Ortas Manager");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(300, 200);
	}

	private void initGui_MenuBar()
	{
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

	private void initGui_MenuBar_Sistema(JMenuBar menubar)
	{
		// Creo il menu "Sistema"
		JMenu menu_sistema;

		menu_sistema = new JMenu("Sistema");
		menu_sistema.setMnemonic(KeyEvent.VK_S);
		//MenuBarListener mbl = new MenuBarListener();
		//menu_sistema.addMenuListener(mbl);

		menubar.add(menu_sistema);

		// Creo la voce "Esci" del menu "Sistema"
		JMenuItem menuitem_sistema_esci;
		menuitem_sistema_esci = new JMenuItem("Esci", KeyEvent.VK_E);

		menu_sistema.add(menuitem_sistema_esci);

		//Sistema_Esci_MenuItemListener se = new 	Sistema_Esci_MenuItemListener();
		//menuitem_sistema_esci.addItemListener(se);
	
		Sistema_Esci_ActionListener seal = new Sistema_Esci_ActionListener();
		menuitem_sistema_esci.addActionListener(seal);
	

		// Creo la voce "LogOut" del menu Sistema
		JMenuItem menuitem_sistema_logout;
		menuitem_sistema_logout = new JMenuItem("LogOut", KeyEvent.VK_L);

		menu_sistema.add(menuitem_sistema_logout);

	}
	
	private void initGui_MenuBar_MenuOrdini(JMenuBar menubar)
	{
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

	private void initGui_MenuBar_MenuMagazzino(JMenuBar menubar)
	{
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

	private void initGui_MenuBar_MenuProdotti(JMenuBar menubar)
	{
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

	}

	private void initGui_MenuBar_MenuReports(JMenuBar menubar)
	{
		// Creo il menu "Reports"
		JMenu menu_reports;

		menu_reports = new JMenu("Reports");
		menu_reports.setMnemonic(KeyEvent.VK_R);

		menubar.add(menu_reports);

	}
//set foldmethod=syntax
//set foldenable
//syn region foldBraces start=/{/ end=/}/ transparent fold
/*
	private class MenuBarListener implements MenuListener
	{
		public void menuSelected(MenuEvent e)
		{
			System.out.println("MS");
		}
		public void menuDeselected(MenuEvent e)
		{
			System.out.println("MD");
		}
		public void menuCanceled(MenuEvent e)
		{
			System.out.println("MC");
		}
	}
*/
/*

	private class Sistema_Esci_MenuItemListener implements ItemListener
	{
		public void itemStateChanged(ItemEvent ie)
		{
			if(ie.getStateChange() == ItemEvent.SELECTED)
			{
				System.out.println("SELECTED");
			}
			else
			{
				System.out.println("ciao");
			}
		}
	}
*/

	private class Sistema_Esci_ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			
			System.out.println("Action");		
		}
	}
}
