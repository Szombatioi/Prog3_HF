package backend;

@SuppressWarnings("serial")
/**
 * Lerakott zászlókat eltűntető bomba. Ha felfedik, a már elhelyezett zászlókat leszedi a pályáról.
 */
public class ResetFlagBomb extends Bomb{
	/**
	 * A létrehozása azonos a sima bombáéval.
	 * @param b, az őt tartalmazó tábla.
	 */
	public ResetFlagBomb(Board b) {
		super(b);
	}
	
	/**
	 * A felfedéskor megváltozik az ikonja fedett mezőről a neki megfelelőre.
	 */
	@Override
	protected void changeIcon() {
		icon = Images.resetFlagBomb;
	}
	
	/**
	 * Végrehajtáskor meghívja a tábla zászló eltűntető függvényét.
	 */
	@Override
	protected void execute() {
		master.resetFlags();
	}
}
