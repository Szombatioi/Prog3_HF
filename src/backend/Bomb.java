package backend;


@SuppressWarnings("serial")
/**
 * Bomba ősosztály. Sima mezőből származik, viszont ezt ha felfedik, akkor felrobban a pálya és a játéknak vége.
 */
public class Bomb extends Tile {
	/** Azt jelöli, hogy ez okozta-e a pálya felrobbanását. (Azaz hogy erre kattintottunk-e) */
	private boolean first;
	
	/**
	 * A létrehozása azonos a sima mezőével.
	 * @param b, az őt tartalmazó tábla.
	 */
	public Bomb(Board b) {
		super(b);
		first = false;
	}
	
	/**
	 * Sima felfedéskor beállítja, hogy ő az első bomba, ami a robbanást okozta (ezáltal piros hátterű lesz) 
	 * és ha még nincs felfedve (ez a végtelen ciklus elkerüléséhez kell) akkor megjeleníti (revealEnd) és aktiválja (execute).
	 */
	@Override
	public void reveal() {
		first = true;
		if(!isRevealed) {
			revealEnd();
			execute();
		}
	}
	
	/**
	 * Végső felfedés. Ez a felfedés általában a játék végét jelzi.
	 * Ha nincs megjelölve zászlóval, akkor megváltoztatja az ikonját, beállítja, hogy már fel van fedve és csökkenti a tábla
	 * fedett mezőinek számát.
	 */
	@Override
	public void revealEnd() {
		if(!isFlagged) {
			changeIcon();
			isRevealed = true;
			master.decHiddenTiles();
		}
	}
	
	/**
	 * Mező megjelölése. A hagyományos megjelöléssel ellentétben ha bombát jelölünk meg, akkor az a hátralévő bombák számát is csökkenti. 
	 */
	@Override
	public void flag() {
		super.flag();
		int flagMod = isFlagged? -1 : 1;
		master.setBombsLeft(master.getBombsLeft()+flagMod);
	}
	
	/**
	 * A felfedéskor megváltozik az ikonja fedett mezőről a neki megfelelőre.
	 */
	@Override
	protected void changeIcon() {
		icon = first ? Images.redBomb : Images.bomb;
	}
	
	/**
	 * A bombáknál a setBombsAround függvény minden esetben -1-et állít be, mivel bombáknál nem számít, hogy hány bomba van körülöttük.
	 */
	@Override
	public void setBombsAround(int b) {
		bombsAround = -1;
	}
	
	/**
	 * Bomba aktiválása. Alapvetően felfedi az összes cellát és ezáltal véget ér a játék.
	 */
	protected void execute() {
		master.revealEveryTile();
	}
	
	/**
	 * A getBombsAround függvénynél használatos.
	 * @return 1 bombának megfelelő az értéke.
	 */
	@Override
	public int getValue() {return 1;}

}
