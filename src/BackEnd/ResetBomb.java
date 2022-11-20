package BackEnd;

public class ResetBomb extends Bomb{

	public ResetBomb(/*int x, int y, Image img, */Board b) {
//		super(x, y, img, b);
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.resetBomb;
	}
	
	@Override
	protected void execute() {
		//thread sleep
//		master.restart(master.getRows(), master.getCols(), master.getBombs());
	}

}
