package Game;

import Frame.Timer;

public class Game{
	private Timer timer;
	private Board board;
	
	public Game(Difficulty d) {
		board = new Board(d);
		timer = new Timer();
	}
	
}
