package backend;

import java.util.Random;

@SuppressWarnings("serial")
/**
 * Cluster bomba osztály. Ha van elég hely a táblán, akkor felfedéskor elhelyez még 1-3 új bombát. Egyéb esetben felrobban.
 */
public class ClusterBomb extends Bomb {

	/**
	 * A létrehozása azonos a sima bombáéval.
	 * @param b, az őt tartalmazó tábla.
	 */
	public ClusterBomb(Board b) {
		super(b);
	}
	
	/**
	 * A felfedéskor megváltozik az ikonja fedett mezőről a neki megfelelőre.
	 */
	@Override
	protected void changeIcon() {
		icon = Images.clusterBomb;
	}
	
	/**
	 * Felfedéskor először meghatároz egy random számot 1 és 3 között - ez lesz az új bombák száma.
	 * Ezt követően megvizsgálja, hogy van-e elég hely még a táblán, ha nincs, akkor felfedi az összes mezőt.
	 * Egyéb esetben a táblának meghívja az extra bomba elhelyzési függvényét, illetve csökkenti a saját értékét a zászlók és hátralévő bombák tekintetében.
	 */
	@Override
	protected void execute() {
		int extra = new Random().nextInt(3)+1; ///1 és 3 között ad hozzá új bombát
		if(master.getHiddenTiles() <= master.getBombsNr()+extra) {
			master.revealEveryTile();
		}
		else {
			master.addMoreBombs(extra);
			master.setFlagsLeft(master.getFlagsLeft()-1);
			master.setBombsLeft(master.getBombsLeft()-1);
			
		}
	}
	

}
