package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import BackEnd.Difficulty;
import BackEnd.Game;

public class GamePanel extends JPanel{
	Game game;
	JButton newGameBtn, pauseBtn, saveBtn; //+exit +main menu
	JPanel buttonsPanel;
	
	public GamePanel(Difficulty d) {
		game = new Game(d);
		setLayout(new BorderLayout());
		game.setBackground(Color.red);
		add(game, BorderLayout.CENTER);
		
		
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.blue);
		newGameBtn = new JButton("New"); //TODO setIcon talán
		pauseBtn = new JButton("Pause");
		saveBtn = new JButton("Save");
		buttonsPanel.add(newGameBtn);
		buttonsPanel.add(pauseBtn);
		buttonsPanel.add(saveBtn);
		
		add(buttonsPanel, BorderLayout.NORTH);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		buttonsPanel.repaint();
		game.paintComponent(g, getWidth(), getHeight());
	}
}
