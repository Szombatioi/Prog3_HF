package backend;

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
			icon = isFlagged ? Images.flag : Images.difusedBomb;
			isRevealed = true;
			master.decHiddenTiles();
			master.setBombsLeft(master.getBombsLeft()-1);
		}
	}
	
	@Override
	public void revealEnd() {
		reveal();
	}
}
