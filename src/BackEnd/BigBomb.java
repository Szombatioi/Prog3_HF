package BackEnd;

import java.util.Random;

public class BigBomb extends Bomb {
	Random r = new Random();
	public BigBomb(Board b) {
		super(b);
	}
	
	@Override
	public void reveal() {
		icon = Images.bigBomb;
//		execute();
	}
	
	private void execute() {
		master.revealEveryTile();
	}
	
	@Override
	public int addValue() {
		return r.nextInt(2,5); //2 és 4 közötti értéket ad
	}
}
