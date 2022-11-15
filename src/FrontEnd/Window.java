package FrontEnd;
import javax.swing.*; //TODO csak a szükségeseket!

import BackEnd.Difficulty;
import BackEnd.Game;
import BackEnd.MenuPanel;

import java.awt.*; //TODO csak a szükségeseket!

//TODO sz�ll� tenni, while(running)
public class Window extends JFrame{
	private JPanel Menu;
	
	
	
	public Window(String title, int w, int h) {
		setTitle(title);
		setSize(new Dimension(w,h));
		setMinimumSize(new Dimension(w,h));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		requestFocus();
		setVisible(true);
		
//		setLayout(new BorderLayout());
//		add(new MenuPanel());
//		add(new JLabel("MineSweeper 2.0"), BorderLayout.PAGE_START);
		
		//TODO menu csinalasa
		add(new GamePanel(Difficulty.EASY));
//		add(new GameButtonPanel(), BorderLayout.NORTH);
		repaint();
		pack();
	}
	
	public Window(String title, int w, int h, Image img) {
		this(title, w, h);
//		if(img!=null) setIconImage(img);
		setIconImage(img);
	}
}
