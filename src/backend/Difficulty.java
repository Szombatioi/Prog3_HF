package backend;

/**
 * A játék nehézségeit felsorakoztató enumeráció. Minden nehézségnek van egy adott sor- és oszlopszáma, illetve bombaszáma, ami legalább a 10%-a a tábla méretének.
 */
public enum Difficulty {
	/**
	 * 3 alapnehézség és egy egyedi nehézség van.
	 */
	EASY(8,8,10), NORMAL(16,16,40), HARD(16,30,99), CUSTOM(0,0,0);

	/**
	 * A nehézség sorai, oszlopai és bombái.
	 */
	private int rows, cols, bombs;
	
	/**
	 * A nehézség konstruktora.
	 * @param r a sorok száma
	 * @param c az oszlopok száma
	 * @param b a bombák száma
	 */
	private Difficulty(int r, int c, int b) {
		rows = r;
		cols = c;
		bombs = b;
	}
	/**
	 * Az egyéni nehézség beállítása.
	 * @param r a nehézség sorainak száma.
	 * @param c a nehézség oszlopainak száma.
	 * @param b a nehézség bombáinak száma.
	 */
	public void setCustom(int r, int c, int b) {
		CUSTOM.rows = r;
		CUSTOM.cols = c;
		CUSTOM.bombs = b;
	}
	/** Get függvény a sorok lekérdezésére.*/
	public int rows() {return rows;}
	/** Get függvény az oszlopok lekérdezésére.*/
	public int cols() {return cols;}
	/** Get függvény a bombák lekérdezésére.*/
	public int bombs() {return bombs;}
}