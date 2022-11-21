package FrontEnd;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JPanel;

import BackEnd.Board;
import BackEnd.Difficulty;

@SuppressWarnings("serial")
public class Game extends JPanel implements Serializable{
	private Timer timer;
	private Board board;
	private boolean running;
	private boolean started;
	private boolean finished;
	
	public Game(Board b) {
		board = b;
		init();
	}
	
	public Game(Difficulty d) {
		board = new Board(d, this);
		init();
	}
	
	public void init() {
		timer = new Timer();
		running = true;
		started = false;
		finished = false;
		addMouseListener(new MyMouseListener(board, this));
	}
	
	public boolean running() {return running;}
	public boolean finished() {return finished;}
	public boolean started() {return started;}
	
	public void setFinished(boolean b) {
		finished = b;
		timer.setRunning(b);
	}
	public void setRunning(boolean b) {
		running = b;
		timer.setRunning(b);
	}
	
	public void restart() {
		running = true;
		started = false;
		finished = false;
		board.restart();
		
		timer.setFinished(true);
		try {
			timer.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		timer = new Timer();
	}
	
	public void start(int startX, int startY) {
		running = true;
		started = true;
		board.generateBombs(startX, startY);
		board.setBombsAroundNums();
		timer.start();
		timer.setRunning(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(running) board.paintComponent(g, getWidth()/2);
		timer.paintComponent(g, getWidth());
		repaint();
	}
	
}
