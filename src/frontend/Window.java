package frontend;

import java.awt.Dimension;
import java.awt.Image;
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
		this.controller = controller;
		this.controller.setWindow(this);
		
		setTitle(title);
		setMinimumSize(new Dimension(defWidth, defHeight));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		requestFocus();
		setResizable(false);
		
		MyMenuBar menuBar = new MyMenuBar(controller);
		setJMenuBar(menuBar);
		controller.setMenuBar(menuBar);
		add(new MenuPanel(controller));
//		add(new VictoryPanel(3, 22,false));

		pack();
		setVisible(true);
	}
	
	public void resetSize() {
		setMinimumSize(new Dimension(defWidth, defHeight));
		setSize(new Dimension(defWidth, defHeight));
	}
	
	public Window(String title, Controller controller, Image img) {
		this(title, controller);
		setIconImage(img);
	}
	
	public Dimension getDefSize() {
		return new Dimension(defWidth, defHeight);
	}
	
	public void setPanel(JPanel panel) {
		setContentPane(panel);
		invalidate();
		validate();
	}
}
