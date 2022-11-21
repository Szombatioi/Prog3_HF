package FrontEnd;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import BackEnd.Board;
import BackEnd.Difficulty;

@SuppressWarnings("serial")
public class Game extends JPanel /*implements Runnable*/{
	private Timer timer;
	private Board board;
	private boolean running;
	private boolean started;
	private boolean finished;
	
//	JLabel time, bomb;
	
	public Game(Difficulty d) {
		board = new Board(d);
		timer = new Timer();
		running = true;
		started = false;
		addMouseListener(new MyMouseListener(board, this));
	}
	
	public boolean running() {return running;}
	public void setRunning(boolean b) {
		running = b;
		timer.setRunning(b);
	}
	public boolean started() {return started;}
	
	public void restart() {
		running = true;
		started = false;
		board.restart();
		
		timer.setFinished(true);
		try {
			timer.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		timer = new Timer();
	}
	
	public void end() {
		
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
