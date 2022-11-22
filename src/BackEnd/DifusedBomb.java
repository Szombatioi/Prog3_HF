package BackEnd;

@SuppressWarnings("serial")
public class DifusedBomb extends Bomb {

	public DifusedBomb(Board b) {
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.difusedBomb;
	}
	
	@Override
	public void reveal() {
		if(!isRevealed) {
			icon = Images.difusedBomb;
			isRevealed = true;
		}
	}
	
	@Override
	public void revealEnd() {
		reveal();
	}
}
