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
		revealEnd();
		execute();
	}
	
	@Override
	public void revealEnd() {
		if(!isFlagged) {
			changeIcon();
			isRevealed = true;
		}
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
		System.out.println("bomb");
		master.revealEveryTile();
		master.end();
	}
	
	@Override
	public int getValue() {return 1;}

}
