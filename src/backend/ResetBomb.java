package backend;

@SuppressWarnings("serial")
public class ResetBomb extends Bomb{

	public ResetBomb(/*int x, int y, Image img, */Board b) {
//		super(x, y, img, b);
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.resetBomb; //TODO ez lehet nem kell
	}
	
	@Override
	protected void execute() {
		//thread sleep
		master.restart();
		master.resetGame();
	}

}
