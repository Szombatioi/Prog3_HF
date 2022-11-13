package Game;

import java.awt.*; //TODO csak a szükségeseket
import javax.swing.*; //TODO csak a szükségeseket
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel{
	JButton newGameBtn, loadBtn, settingsBtn, ranksBtn, exitBtn;
	
	public MenuPanel() {
		setLayout(new GridLayout(2,1));
		setBackground(Color.red);
		
		JLabel title = new JLabel("MineSweeper 2.0");
		
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(5,1));
		center.setBorder(new EmptyBorder(0,0,0,0));
		
		newGameBtn = new JButton("New Game");
		loadBtn = new JButton("Load Game");
		settingsBtn = new JButton("Settings");
		ranksBtn = new JButton("Ranks");
		exitBtn = new JButton("Exit");
		
		add(title);
		center.add(newGameBtn);
		center.add(loadBtn);
		center.add(settingsBtn);
		center.add(ranksBtn);
		center.add(exitBtn);
		add(center);
	}
	
	
	
}
