package BackEnd;


@SuppressWarnings("serial")
public class Bomb extends Tile {
	private boolean first;
	public Bomb(Board b) {
		super(b);
		first = false;
	}
	
	@Override
	public void reveal() {
		if(!isFlagged) {
			changeIcon();
			isRevealed = true;
			execute();
		}
	}
	
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
