package BackEnd;

import java.awt.Image;

public class DifusedBomb extends Bomb {

	public DifusedBomb(/*int x, int y, Image img, */Board b) {
//		super(x, y, img, b);
		super(b);
	}
	
	@Override
	public void reveal() {
		icon = Images.difusedBomb;
	}
	
}
