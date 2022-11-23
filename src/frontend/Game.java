package frontend;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JPanel;

import backend.Board;
import backend.Controller;
//import backend.Difficulty;
import backend.Time;

@SuppressWarnings("serial")
public class Game extends JPanel implements Serializable{
	private Timer timer;
	private Board board;
	private boolean running;
	private boolean started;
	private boolean finished;
	private MyMouseListener mml;
	private Controller controller;
	int yOffset; //idő+flags rajzolása miatt
	
	public Game(Controller controller) {
		this.controller = controller;
		board = new Board(controller.getDiff(), this, controller);
		init();
		controller.setGameMenuBar(true);
	}
	
//	public Game(Board b) {
//		board = b;
//		init();
//	}
	
//	public Board getBoard() {
//		return board;
//	}
	public Time getTime() {
		return timer.getTime();
	}
	public void init() {
		timer = new Timer();
		yOffset = timer.getHeight();
		running = true;
//		running = false;
		started = false;
		finished = false;
		mml = new MyMouseListener(board, this);
		addMouseListener(mml);
		controller.setML(mml);
	}
	public MyMouseListener getListener() {return mml;}
	public boolean running() {return running;}
	public boolean finished() {return finished;}
	public boolean started() {return started;}
	public void setStarted(boolean b) {started = b;}
	
	public void setFinished(boolean b) {
		finished = b;
		timer.setRunning(!b);
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
	
	public void start() {
		running = true;
		started = true;
		board.generateBombs();
		board.setBombsAroundNums();
		if(!timer.running()) timer.start();
		timer.setRunning(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		if(running) board.paintComponent(g, getWidth()/2);
		board.paintComponent(g, getWidth()/2, yOffset+10);
		timer.paintComponent(g, getWidth());
		repaint();
	}
	
}
