package BackEnd;

import java.awt.Image;


public class Bomb extends Tile {
	
	public Bomb(int x, int y, Image img, Board b) {
		super(x, y, img, b);
	}
	
	@Override
	public void reveal() {
		icon = Images.redBomb;
		execute();
	}
	
	private void execute() {
		master.revealEveryTile();
	}

}
