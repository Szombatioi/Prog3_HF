package frontend;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Controller;

@SuppressWarnings("serial")
/**
 * A program ablaka. Erre helyezzük el a különböző nézetek paneljeit.
 */
public class Window extends JFrame{
	/**Az őt (is) irányító controller.*/
	Controller controller;
	/**Az ablak eredeti méretei.*/
	int defWidth, defHeight;
	
	/**
	 * Az ablak konstruktora. Az alapértelmezett ablakméretet beállítja 600x500-asra, beállítja az ablak szükséges adatait, beállítja a controllert, 
	 * betölti a mentett rekordokat, létrehozza a grafikus elemeket, majd megjeleníti az ablakot.
	 * @param title Az ablak címe.
	 * @param controller Az őt (is) irányító controller.
	 */
	public Window(String title, Controller controller, Image img) {
		defWidth = 600;
		defHeight = 500;
		
		setTitle(title);
		setMinimumSize(new Dimension(defWidth, defHeight));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		requestFocus();
		setResizable(false);
		setIconImage(img);
		
		this.controller = controller;
		this.controller.setWindow(this);
		
		controller.loadRecords();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.saveRecords();
			}
		});
		
		initComponents();
		pack();
		setVisible(true);
	}
	
	/**A grafikus elemek létrehozása.*/
	public void initComponents() {
		MyMenuBar menuBar = new MyMenuBar(controller);
		setJMenuBar(menuBar);
		controller.setMenuBar(menuBar);
		add(new MenuPanel(controller));
	}
	/**Visszaállítja az ablak eredeti méretét.*/
	public void resetSize() {
		setMinimumSize(new Dimension(defWidth, defHeight));
		setSize(new Dimension(defWidth, defHeight));
	}
	/**
	 * Beállítja az aktív panelt.
	 * @param panel A beállítandó panel.
	 */
	public void setPanel(JPanel panel) {
		setContentPane(panel);
		invalidate();
		validate();
	}
}
