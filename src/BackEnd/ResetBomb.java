package BackEnd;

import java.awt.Image;

public class ResetBomb extends Bomb{

	public ResetBomb(int x, int y, Image img, Board b) {
		super(x, y, img, b);
	}
	
	@Override
	public void reveal() {
		icon = Images.resetBomb;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		execute();
	}
	
	private void execute() {
//		master.restart(master.getRows(), master.getCols(), master.getBombs());
	}

}
