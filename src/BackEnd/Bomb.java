package BackEnd;

import java.awt.Image;

public class Bomb extends Tile {
	
	public Bomb(int x, int y, Image img, Board b) {
		super(x, y, img, b);
		isBomb = true;
	}
	
	@Override
	public void reveal() {
		//...
		//super.reveal() nem jó, mert itt másra kell cserélni az ikont
		execute();
	}
	
	public void execute() {
		master.revealEveryTile();
	}

}
