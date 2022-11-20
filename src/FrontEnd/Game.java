package FrontEnd;

import java.awt.Graphics;
import javax.swing.JPanel;
import BackEnd.Board;
import BackEnd.Difficulty;

@SuppressWarnings("serial")
public class Game extends JPanel /*implements Runnable*/{
	private Timer timer;
	private Board board;
	private boolean running;
	private boolean started;
	
	public Game(Difficulty d) {
		board = new Board(d);
		timer = new Timer();
		running = false;
		started = false;
		addMouseListener(new MyMouseListener(board, this));
	}
	
	public void tick() {
		if(running) timer.tick();
	}
	public boolean running() {return running;}
	public void setRunning(boolean b) {running = b;}
	public boolean started() {return started;}
	
	public void restart() {
		running = false;
		started = false;
		board.restart();
	}
	
	public void start(int startX, int startY) {
		running = true;
		started = true;
		board.generateBombs(startX, startY);
		board.setBombsAroundNums();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		board.paintComponent(g, getWidth()/2);
		timer.paintComponent(g);
		repaint();
		tick();
	}
	
}
