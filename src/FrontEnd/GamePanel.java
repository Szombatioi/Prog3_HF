package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Savepoint;

import javax.swing.JButton;
import javax.swing.JPanel;

import BackEnd.Difficulty;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = -8011518685804605139L;
	
	Game game;
	JButton newGameBtn, pauseBtn, saveBtn, menuBtn, exitBtn;
	JPanel buttonsPanel;
	
	public GamePanel() {
		
		setLayout(new BorderLayout());
		
		buttonsPanel = new JPanel();
		
		pauseBtn = new JButton("Pause");
		newGameBtn = new JButton("New Game");
		saveBtn = new JButton("Save");
		
		buttonsPanel.add(pauseBtn);
		buttonsPanel.add(newGameBtn);
		buttonsPanel.add(saveBtn);
		
		game = new Game(Difficulty.EASY); //Controller.getGameDifficulty
//		game = new Game(32,32,10);
		
		add(buttonsPanel, BorderLayout.NORTH);
		add(game, BorderLayout.CENTER);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buttonsPanel.repaint();
		game.paintComponent(g, buttonsPanel.getHeight());
	}
}
