package BackEnd;

import java.awt.Image;

public class ResetBomb extends Bomb{

	public ResetBomb(/*int x, int y, Image img, */Board b) {
//		super(x, y, img, b);
		super(b);
	}
	
	@Override
	public void reveal() {
		icon = Images.resetBomb;
			//thread.sleep()
		execute();
	}
	
	private void execute() {
//		master.restart(master.getRows(), master.getCols(), master.getBombs());
	}

}
