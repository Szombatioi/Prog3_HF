package backend;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Egyszerű idő osztály, ami a mostani feladat megoldásához elegendő.
 */
public class Time implements Serializable{
	/**
	 * Tárolja az időt percekben és másodpercekben. 
	 */
	int min, sec;
	
	/**
	 * Az idő konstruktora, beállítja a percet és a másodpercet 0-ra.
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
	public boolean lt(int num) {return (min*60+sec)<num;}
	
	/**
	 * Get függvény a percek lekérésére.
	 * @return A percek értéke.
	 */
	public int getM() {return min;}
	
	/**
	 * Get függvény a másodpercek lekérésére.
	 * @return A másodpercek értéke.
	 */
	public int getS() {return sec;}
	
	/**
	 * Kiíratáshoz használható toString() metódus.
	 */
	public String toString() {
		return String.format("%02d", min)+":"+String.format("%02d", sec);
	}

	/**
	 * Sorbarendezéshez használható összehasonlítás
	 * @param time egy másik idő
	 * @return negatív érték, ha ez az idő kisebb a paraméterben megadottnál.
	 */
	public int compareTo(Time time) {
		int secs = min*60+sec;
		int secs2 = time.getM()*60 + time.getS();
		return secs-secs2;
	}

}
