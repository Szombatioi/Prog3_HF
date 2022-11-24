package frontend;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import backend.Controller;

@SuppressWarnings("serial")
public class Window extends JFrame{
	Controller controller;
	int defWidth, defHeight;
	
	public Window(String title, Controller controller) {
		defWidth = 600;
		defHeight = 500;
		
		setTitle(title);
		setMinimumSize(new Dimension(defWidth, defHeight));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		requestFocus();
		setResizable(false);
		
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
	
	//TODO belepakolni
	public void initComponents() {
		MyMenuBar menuBar = new MyMenuBar(controller);
		setJMenuBar(menuBar);
		controller.setMenuBar(menuBar);
		add(new MenuPanel(controller));
	}
	
	public void resetSize() {
		setMinimumSize(new Dimension(defWidth, defHeight));
		setSize(new Dimension(defWidth, defHeight));
	}
	
	public Window(String title, Controller controller, Image img) {
		this(title, controller);
		setIconImage(img);
	}
	
//	public Dimension getDefSize() {
//		return new Dimension(defWidth, defHeight);
//	}
	
	public void setPanel(JPanel panel) {
		setContentPane(panel);
		invalidate();
		validate();
	}
}
