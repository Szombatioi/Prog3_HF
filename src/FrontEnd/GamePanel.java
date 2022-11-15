package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import BackEnd.Difficulty;
import BackEnd.Game;

public class GamePanel extends JPanel{
	Game game;
	public GamePanel(Difficulty d) {
		game = new Game(d);
		setLayout(new BorderLayout());
		setBackground(Color.red);
//		add(game, BorderLayout.SOUTH);
//		add(new GameButtonPanel(), BorderLayout.NORTH);
//		add(game);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		game.paintComponent(g, getWidth(), getHeight());
	}
}
