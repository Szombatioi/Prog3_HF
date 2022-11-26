package backend;

@SuppressWarnings("serial")
/**
 * Hatástalanított bomba osztály. Bombának számít, viszont felfedéskor nem ér véget a játék.
 */
public class DifusedBomb extends Bomb {

	/**
	 * A létrehozása azonos a sima bombáéval.
	 * @param b, az őt tartalmazó tábla.
	 */
	public DifusedBomb(Board b) {
		super(b);
	}
	
	/**
	 * A felfedéskor megváltozik az ikonja fedett mezőről a neki megfelelőre.
	 */
	@Override
	protected void changeIcon() {
		icon = Images.difusedBomb;
	}
	
	/**
	 * Felfedés esetén ha még nincs felfedve, felfedi, és a bombák és zászlók számát csökkenti.
	 */
	@Override
	public void reveal() {
		if(!isRevealed) {
			revealEnd();
			master.setFlagsLeft(master.getFlagsLeft()-1);
			master.setBombsLeft(master.getBombsLeft()-1);
		}
	}
}
