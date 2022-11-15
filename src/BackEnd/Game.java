package BackEnd;

import FrontEnd.Timer;

public class Game{
	private Timer timer;
	private Board board;
	private boolean running;
	
	public Game(Difficulty d) {
		board = new Board(d);
		timer = new Timer();
	}
	
	public void setRun(boolean b) {
		running = b;
	}
	
	public void tick() {
		if(running) {
			timer.tick();
			board.tick();
		}
	}
	
//	public void paintComponent(Graphics g, int w, int h) {
//		super.paintComponent(g);
//		board.paintComponent(g, w/2 - board.getRows()*20/2, 0);
//		timer.paintComponent(g);
//	}
	
}
