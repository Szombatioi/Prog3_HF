package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import BackEnd.Controller;

public class GamePanel extends JPanel{
	private static final long serialVersionUID = -8011518685804605139L;
	
	Game game;
	Controller controller;
	JButton newGameBtn, pauseBtn, saveBtn, menuBtn, exitBtn;
	JPanel buttonsPanel;
	
	public GamePanel(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		
		buttonsPanel = new JPanel();
		
		pauseBtn = new JButton("Pause");
		newGameBtn = new JButton("New Game");
		saveBtn = new JButton("Save");
		menuBtn = new JButton("Menu");
		exitBtn = new JButton("Exit");
		
		buttonsPanel.add(pauseBtn);
		buttonsPanel.add(newGameBtn);
		buttonsPanel.add(saveBtn);
		buttonsPanel.add(menuBtn);
		buttonsPanel.add(exitBtn);
		
		game = new Game(controller.getDiff()); //Controller.getGameDifficulty
		
		add(buttonsPanel, BorderLayout.NORTH);
		add(game, BorderLayout.CENTER);	
		setActionListeners();
	}
	
	public void setActionListeners() {
		menuBtn.addActionListener(a -> {
			controller.setPanel(new MenuPanel(controller));
			controller.resetWindowSize();
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buttonsPanel.repaint();
		game.paintComponent(g, buttonsPanel.getHeight());
	}
}
