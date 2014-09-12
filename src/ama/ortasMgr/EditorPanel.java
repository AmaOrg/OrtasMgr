package ama.ortasMgr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public abstract class EditorPanel extends JPanel
{
	// GUI VARS
	//

	private   JPanel     pnl_content;
	private   JPanel     pnl_buttons;

	protected JButton    btn_esci;
	protected JButton    btn_modifica;
	protected JButton    btn_annulla;
	protected JButton    btn_salva;

	//===========================================================================

	// protected

	protected EditorPanel() {
		BorderLayout bl = new BorderLayout();
		this.setLayout(bl);

		this.pnl_buttons = new JPanel();

		this.btn_esci = new JButton("Esci");
		this.btn_modifica = new JButton("Modifica");
		this.btn_annulla = new JButton("Annulla");
		this.btn_salva = new JButton("Salva");

		JPanel p = new JPanel();

		String east = SpringLayout.EAST;
		String west = SpringLayout.WEST;
		String north = SpringLayout.NORTH;
		String south = SpringLayout.SOUTH;

		SpringLayout sp = new SpringLayout();
		p.setLayout(sp);

		sp.putConstraint(east , this.btn_salva   ,  0, east , p          );
		sp.putConstraint(south, this.btn_salva   ,  0, south, p          );
		sp.putConstraint(east , this.btn_annulla , -5, west , this.btn_salva  );
		sp.putConstraint(south, this.btn_annulla ,  0, south, this.btn_salva  );
		sp.putConstraint(east , this.btn_modifica, -5, west , this.btn_annulla);
		sp.putConstraint(south, this.btn_modifica,  0, south, this.btn_annulla);

		p.add(this.btn_modifica);
		p.add(this.btn_annulla);
		p.add(this.btn_salva);

		GroupLayout gl = new GroupLayout(this.pnl_buttons);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		this.pnl_buttons.setLayout(gl);

		GroupLayout.SequentialGroup sg;
		GroupLayout.ParallelGroup pg;

		sg = gl.createSequentialGroup(); // Aggiungo gruppo orizzontale
		pg = gl.createParallelGroup(GroupLayout.Alignment.LEADING); // Aggiungo 1°rettangolo
		pg.addComponent(this.btn_esci);
		sg.addGroup(pg);
		pg = gl.createParallelGroup(GroupLayout.Alignment.TRAILING);
		pg.addComponent(p);
		sg.addGroup(pg);
		gl.setHorizontalGroup(sg);

		sg = gl.createSequentialGroup();
		pg = gl.createParallelGroup(GroupLayout.Alignment.LEADING);
		pg.addComponent(this.btn_esci);
		pg.addComponent(p);
		sg.addGroup(pg);
		gl.setVerticalGroup(sg);

		this.pnl_content = new JPanel();

		this.add(this.pnl_content, BorderLayout.CENTER);
		this.add(this.pnl_buttons, BorderLayout.PAGE_END);
		
		this.initContentPanel(this.pnl_content);
	}

	protected abstract void initContentPanel(JPanel pnl_content);
}
