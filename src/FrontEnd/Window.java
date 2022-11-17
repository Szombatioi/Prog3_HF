package FrontEnd;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;

public class Window extends JFrame{
	
	MenuPanel menu;
	
	public Window(String title, int w, int h) {
		setTitle(title);
		setMinimumSize(new Dimension(w,h));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		requestFocus();
		setResizable(false);
		
		//TODO menubar csinalasa
		menu = new MenuPanel();
//		add(menu);
		add(new GamePanel());

		
		pack();
		setVisible(true);
	}
	
	public Window(String title, int w, int h, Image img) {
		this(title, w, h);
		setIconImage(img);
	}
}
