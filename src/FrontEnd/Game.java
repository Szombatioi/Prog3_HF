package FrontEnd;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import BackEnd.Board;
import BackEnd.Difficulty;

@SuppressWarnings("serial")
public class Game extends JPanel /*implements Runnable*/{
	
	private Timer timer;
	private Board board;
	private boolean running;
//	private boolean finished;
	
	private int mouseX, mouseY;
	
	public Game(int r, int c, int b) {
		board = new Board(r,c,b);
		timer = new Timer();
		running = false;
//		finished = false;
		setBackground(Color.red);
		
		addMouseListener(new MouseListener(mouseX, mouseY, board));
		
	}
	
	public Game(Difficulty d) {
		this(d.rows(), d.cols(), d.bombs());
	}
	
	public void setRun(boolean b) {
		running = b;
	}
	
	public void tick() {
		if(running) {
			timer.tick();
			board.tick();
			
		}
		else {
			
		}
		
	}
	
	public void paintComponent(Graphics g, int yOffset) {
		super.paintComponent(g);
		board.paintComponent(g, getWidth()/2, yOffset);
		timer.paintComponent(g);
	}

//	@Override
//	public void run() {
//		while(!finished) {
//			tick();
//		}
//		
//	}
	
}
