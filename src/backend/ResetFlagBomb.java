package backend;

@SuppressWarnings("serial")
public class ResetFlagBomb extends Bomb{
	public ResetFlagBomb(Board b) {
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.resetFlagBomb;
	}
	
	@Override
	protected void execute() {
		master.resetFlags();
	}
}