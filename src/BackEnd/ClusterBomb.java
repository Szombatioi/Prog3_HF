package BackEnd;

import java.awt.Image;

public class ClusterBomb extends Bomb {

	public ClusterBomb(int x, int y, Image img, Board b) {
		super(x, y, img, b);
	}
	
	@Override
	public void reveal() {
//		icon = Images.bomb;
		execute();
	}
	

	private void execute() {
		master.addMoreBombs(1); //random szám legyen
	}
	

}
