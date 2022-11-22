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
		icon = Images.difusedBomb;
		isRevealed = true;
		System.out.println("difused");
	}
	
	@Override
	public void revealEnd() {
		reveal();
	}
}
