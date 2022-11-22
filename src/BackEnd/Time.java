package BackEnd;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Egyszerű idő osztály, ami a mostani feladat megoldásához elegendő.
 */
public class Time implements Serializable{
	int min, sec;
	/**
	 * Tárolja az időt percekben és másodpercekben. 
	 */
	
	public Time() {
		min = sec = 0;
	}
	
	/**
	 * Megnöveli eggyel az idő értékét.
	 * Ha a másodperc elér a 60-at, akkor a percet növeli eggyel, azt meg lenullázza. 
	 */
	public void increase() {
		if(++sec >= 60) {
			sec = 0;
			min++;
		}
	}
	
	/**
	 * Függvény annak eldöntésére, hogy az idő kisebb-e, mint a megadott érték.
	 * @param num A vizsgált idő másodpercekben
	 * @return true, ha valóban kisebb.
	 */
	public boolean lt(int num) {
		return (min*60+sec)<num;
	}
	
	/**
	 * Get függvény a percek lekérésére.
	 * @return A percek értéke.
	 */
	public int getM() {
		return min;
	}
	
	/**
	 * Get függvény a másodpercek lekérésére.
	 * @return A másodpercek értéke.
	 */
	public int getS() {
		return sec;
	}

}
