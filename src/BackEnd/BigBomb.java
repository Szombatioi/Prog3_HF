package BackEnd;

import java.util.Random;

@SuppressWarnings("serial")
public class BigBomb extends Bomb {
	Random r = new Random();
	public BigBomb(Board b) {
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.bigBomb;
	}
	
	@Override
	public int getValue() {
		return 2; //végül csak 2 lett az értéke, 2 VAGY 3 esetén túl nehéz lenne
	}
}
