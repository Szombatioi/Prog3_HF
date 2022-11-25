package backend;

import java.util.Random;

@SuppressWarnings("serial")
public class ClusterBomb extends Bomb {

	public ClusterBomb(Board b) {
		super(b);
	}
	
	@Override
	protected void changeIcon() {
		icon = Images.clusterBomb;
	}
	
	@Override
	protected void execute() {
		int extra = new Random().nextInt(3)+1; ///1 és 3 között ad hozzá új bombát
		if(master.getHiddenTiles() <= master.getBombsNr()+extra) {
			master.revealEveryTile();
			master.end();
			
		}
		else {
			master.addMoreBombs(extra);
			master.setFlagsLeft(master.getFlagsLeft()-1);
			master.setBombsLeft(master.getBombsLeft()-1);
			
		}
	}
	

}
