package backend;

@SuppressWarnings("serial")
/**
 * Nagy bomba osztály. Felrobban, ha rányomnak, de a sima bombával ellentétben 2-nek számít.
 */
public class BigBomb extends Bomb {
	
	/**
	 * A létrehozása azonos a sima bombáéval.
	 * @param b, az őt tartalmazó tábla.
	 */
	public BigBomb(Board b) {
		super(b);
	}
	
	/**
	 * A felfedéskor megváltozik az ikonja fedett mezőről a neki megfelelőre.
	 */
	@Override
	protected void changeIcon() {
		icon = Images.bigBomb;
	}
	
	/**
	 * A getBombsAround függvénynél használatos.
	 * @return 2 bombának megfelelő az értéke.
	 */
	@Override
	public int getValue() {return 2;}
}
