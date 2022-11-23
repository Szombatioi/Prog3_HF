package backend;


@SuppressWarnings("serial")
public class Bomb extends Tile {
	private boolean first;
	public Bomb(Board b) {
		super(b);
		first = false;
	}
	
	@Override
	public void reveal() {
		if(!isRevealed) {
			revealEnd();
			execute();
		}
	}
	
	@Override
	public void revealEnd() {
		if(!isFlagged) {
			changeIcon();
			isRevealed = true;
			master.decHiddenTiles();
		}
	}
	
	@Override
	public void flag() {
		super.flag();
//		master.setBombsLeft(master.getBombsLeft() + (isFlagged ? -1 : 1));
		int flagMod = isFlagged? -1 : 1;
		master.setBombsLeft(master.getBombsLeft()+flagMod);
	}
	
	@Override
	protected void changeIcon() {
		icon = first ? Images.redBomb : Images.bomb;
	}
	
	@Override
	public void setBombsAround(int b) {
		bombsAround = -1;
	}
	
	protected void execute() {
		master.revealEveryTile();
		master.end();
	}
	
	@Override
	public int getValue() {return 1;}

}
