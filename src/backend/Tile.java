package backend;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Cella ősosztály. A táblát ezen cellák alkotják, melyek lehetnek sima cellák, illetve bombák.
 */
public class Tile implements Serializable{
	/** Meg van-e jelölve zászlóval. Ilyenkor nem lehet felfedni. */
	protected boolean isFlagged;
	/** Fel van-e fedve már. Azért kell, hogy a felfedés flood-fill algoritmusa megálljon.*/
	protected boolean isRevealed; 
	/** Az őt tartalmazó tábla.*/
	protected Board master;
	/** A cella x és y koordinátája a képernyőn. */
	protected int x,y;
	/** A körülötte lévő bombák száma. */
	protected int bombsAround;
	/** A cellák szélessége (és egyben magassága)*/
	protected static int w = 25;
	/** A megjelenítendő ikonja. A játék menete során változik. */
	transient protected Image icon;
	
	/**
	 * Létrehozásakor beállítja, hogy nincs megjelölve, illetve felfedve. Alapból egy fedett mező az ikonja, beállítja az őt tartalmazó táblát.
	 * @param b : az őt tartalmazó tábla.
	 */
	public Tile(Board b) {
		isRevealed = false;
		isFlagged = false;
		icon = Images.hTile;
		master = b;
	}
	
	/**
	 * A getBombsAround függvénynél használatos.
	 * @return 0 bombának megfelelő az értéke.
	 */
	public int getValue() {	return 0; }
	/** Statikus get függvény a cella szélességének lekérdezésére. */
	public static int getW() {return w;}
	/** Get függvény a cella körüli bombák számának lekérdezésére. */
	public int getBombsAround() {return bombsAround;}
	/** Meg van-e jelölve a cella*/
	public boolean isFlagged() {return isFlagged;}
	/** Beállítja, hogy meg van-e jelölve a cella. A resetFlags-nél használatos */
	public void setFlagged(boolean b) {isFlagged = b;}
	/** Beállítja a cella körüli bombák számát. */
	public void setBombsAround(int b) {	bombsAround = b;}
	/**
	 * Beállítja, hogy fel van-e fedve a cella. Legfőképp a pálya újrakezdésénél van haszna.
	 * @param b
	 */
	public void setRevealed(boolean b) {isRevealed = b;}
	/**
	 * Beállítja a cella koordinátáit a képernyőn.
	 * @param x
	 * @param y
	 */
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Cella megjelölése. Ha még van elég elhelyezhető zászló, akkor megcseréli a státuszát, illetve ennek megfelelően átírja a tábla zászlóinak számát.
	 * Ha nincs elég zászló már, akkor ha meg van jelölve, leszedi róla a jelölést.
	 * Végezetül megváltoztatja az ikonját annak függvényében, hogy most meg van-e jelölve vagy sem.
	 */
	public void flag() {
		if(master.getFlagsLeft()>0) {
			isFlagged = !isFlagged;
			master.setFlagsLeft(master.getFlagsLeft() + (isFlagged ? -1 : 1));
		}
		else if(isFlagged) {
			isFlagged = false;
			master.setFlagsLeft(master.getFlagsLeft()+1);
		}
		icon = isFlagged ? Images.flag : Images.hTile;
	}
	
	/** Újratölti a cella ikonját. (Játék betöltésekor hasznos) Ha nincs felfedve, akkor ha meg van jelölve zászló, egyéb esetben sima cella lesz az ikonja.
	 * Ha fel van fedve, akkor a changeIcon() függvényt hívja meg.
	 */
	public void loadIcon() {
		if(!isRevealed) {
			icon = isFlagged ? Images.flag : Images.hTile;
		}
		else changeIcon();
	}
	
	/**
	 * Átállítja az ikont a megfelelő ikonjára.
	 */
	protected void changeIcon() {
		icon = getCorrespondingIcon();
	}
	
	/**
	 * A megfelelő ikont adja vissza a bombaszámtól függően. Ha több, mint 8 bomba van a cella körül, akkor egy "?" ikont állít be.
	 * @return a megfelelő ikon.
	 */
	public Image getCorrespondingIcon() {
		return bombsAround<=8 ? Images.numbers[bombsAround] : Images.unknown;
	}
	
	/**
	 * Cella felfedése. Elsősorban csökketi a tábla fedett mezőinek számát, aztán beállítja a megfelelő ikonját, valamint hogy fel van fedve már.
	 */
	public void reveal() {
		master.decHiddenTiles();
		icon = getCorrespondingIcon();
		isRevealed = true;
	}
	
	/**
	 * Végső felfedés. A játék végénél használatos, amikor a bombák miatt felrobban a pálya.
	 * Először csökkenti a fedett mezők számát, majd beállítja a megfelelő ikont. Ha meg volt jelölve egy sima cella, akkor egy áthúzott zászlót állít be ikonnak.
	 */
	public void revealEnd() {
		master.decHiddenTiles();
		icon = isFlagged ? Images.flag2 : getCorrespondingIcon();
		isRevealed = true;
	}
	
	/**
	 * Cella kirajzolása. Egy négyzetes képet rajzol a cella megfelelő koordinátáira.
	 * @param g : A grafikáért felelős paraméter. Az őt tároló Board adja át.
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(icon, x, y, w, w, null);
	}
}
