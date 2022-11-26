package backend;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Egy adatrekordot reprezentáló osztály. Tartalmazza a játékos nevét és idejét.
 */
public class Record implements Serializable {
	/** A játékos neve.*/
	private String playerName;
	/** A játékos ideje.*/
	private Time time;
	
	/**
	 * A rekord konstruktora. Beállítja a nevet és az időt.
	 * @param name a játékos neve
	 * @param t a játékos ideje
	 */
	public Record(String name, Time t) {
		playerName = name;
		time = t;	
	}
	
	/**
	 * Get függvény a játékos nevének lekérdezésére.
	 * @return a játékos neve
	 */
	public String getName() {return playerName;}
	/**
	 * Get függvény a játékos idejének lekérdezésére.
	 * @return a játékos ideje
	 */
	public Time getTime() {return time;}
}
