package ama.ortasMgr;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame
{
	public MainFrame()
	{
		super.setTitle("Ortas Manager");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setSize(300, 200);
		

		// Creiamo la barra dei menu della finestra
		JMenuBar menubar;
		menubar = new JMenuBar();
	
		// Credo il menu "Ordini"
		JMenu menu_ordini;
		
		menu_ordini = new JMenu("Ordini");		
		menu_ordini.setMnemonic(KeyEvent.VK_O);

		menubar.add(menu_ordini);

		JMenuItem menuitem_ordini_nuovo;
		menuitem_ordini_nuovo = new JMenuItem("Nuovo", KeyEvent.VK_N);

		menu_ordini.add(menuitem_ordini_nuovo);

		// Creo il menu "Magazzino"
		JMenu menu_magazzino;

		// Creo il menu "Prodotti"
		JMenu menu_prodotti;
		
		// Creo il menu "Reports"
		JMenu menu_reports;


		// Aggiungo il JMenuBar appena creato al JFrame
		super.setJMenuBar(menubar);


		super.setVisible(true);
	}

	
}
