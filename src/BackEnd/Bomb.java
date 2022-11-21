package BackEnd;


public class Bomb extends Tile {
	private boolean first;
	public Bomb(/*int x, int y, Image img, */Board b) {
		super(/*x, y, img,*/ b);
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
	}
	
	@Override
	public int getValue() {return 1;}

}
