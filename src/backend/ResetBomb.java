package backend;

@SuppressWarnings("serial")
/**
 * Pályát újrakezdő bomba. Ha felfedik, újragenerálja a pályát, viszont az időzítő halad tovább.
 */
public class ResetBomb extends Bomb{

	/**
	 * A létrehozása azonos a sima bombáéval.
	 * @param b, az őt tartalmazó tábla.
	 */
	public ResetBomb(Board b) {
		super(b);
	}
	
	/**
	 * A felfedéskor megváltozik az ikonja fedett mezőről a neki megfelelőre.
	 */
	@Override
	protected void changeIcon() {
		icon = Images.resetBomb;
	}
	
	/**
	 * Aktiválásakor újraindítja a táblát és a játékot.
	 */
	@Override
	protected void execute() {
		master.restart();
		master.resetGame();
	}

}
