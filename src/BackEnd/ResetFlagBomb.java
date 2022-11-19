package BackEnd;

public class ResetFlagBomb extends Bomb{
	public ResetFlagBomb(Board b) {
		super(b);
	}
	
	public void reveal() {
		icon = Images.resetFlagBomb;
		execute();
	}
	
	private void execute() {
		
	}
}
