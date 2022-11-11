package Game;

import java.awt.Image;

public class Bomb extends Tile {
	
	public Bomb(int x, int y, Image img) {
		super(x, y, img);
		
	}
	
	@Override
	public void reveal() {
		//...
		execute();
	}
	
	public void execute() {
		
	}

}
