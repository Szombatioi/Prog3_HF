package BackEnd;

import java.awt.Image;

public class ClusterBomb extends Bomb {

	public ClusterBomb(/*int x, int y, Image img, */Board b) {
//		super(x, y, img, b);
		super(b);
	}
	
	@Override
	public void reveal() {
		icon = Images.clusterBomb;
		execute();
	}
	

	private void execute() {
		master.addMoreBombs(1); //random szám legyen
	}
	

}
