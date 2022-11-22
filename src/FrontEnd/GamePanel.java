package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import BackEnd.Controller;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	Game game;
	Controller controller;
	JButton newGameBtn, pauseBtn, saveBtn, menuBtn, exitBtn;
	JPanel buttonsPanel;
	
	public GamePanel(Controller controller, Game g) {
		game = g;
		init(controller);
	}
	
	
	public GamePanel(Controller controller) {
		game = new Game(controller.getDiff());
		init(controller);
	}
	
	public void init(Controller controller) {
		this.controller = controller;
		initComponents();
		add(game, BorderLayout.CENTER);	
		add(buttonsPanel, BorderLayout.NORTH);
		setActionListeners();
	}
	
	public void initComponents() {
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
	}
	
	public void setActionListeners() {
		menuBtn.addActionListener(a -> {
			controller.setPanel(new MenuPanel(controller));
			controller.resetWindowSize();
			game.setRunning(false);
		});
		exitBtn.addActionListener(a->System.exit(0));
		newGameBtn.addActionListener(a->{
			game.restart();
			pauseBtn.setText("Pause");
		});
		pauseBtn.addActionListener(a->{
			if(game.started() && !game.finished()) {
				game.setRunning(!game.running());
				pauseBtn.setText(game.running() ? "Pause" : "Continue");
			}
		});
		saveBtn.addActionListener(a-> controller.save(game));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		buttonsPanel.repaint();
		game.paintComponent(g);
	}
}
