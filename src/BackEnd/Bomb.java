package BackEnd;


public class Bomb extends Tile {
	private boolean first;
	public Bomb(/*int x, int y, Image img, */Board b) {
		super(/*x, y, img,*/ b);
		first = false;
	}
	
	@Override
	public void reveal() {
		isRevealed = true;
		icon = first ? Images.redBomb : Images.bomb;
//		execute();
	}
	
	@Override
	public void setBombsAround(int b) {
		bombsAround = -1;
	}
	
	private void execute() {
		master.revealEveryTile();
	}
	
	@Override
	public int addValue() {
		return 1;
	}

}
