package backend;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JPanel;

import frontend.Game;
import frontend.MyMenuBar;
import frontend.Window;
import frontend.FeedBackWindow;

/**
 * Controller osztály, ami összeköttetést teremt a megjelenítés és a logika között. A legtöbb osztály vele van kapcsolatban, ő álltja az értékeiket.
 */
public class Controller {
	/** A program ablaka */
	private Window window;
	/** A játék nehézsége*/
	private Difficulty diff;
	/** A program fejléce*/
	private MyMenuBar menuBar;
	/** Az aktív játék*/
	private Game game;
	/** A 3 nehézség rekordjai*/
	private HashMap<Difficulty, Data> datas;
	
	/**
	 * A controller konstruktora.
	 * Alapból könnyű nehézséget állítunk be rá, ezt a későbbiekben lehet módosítani.
	 * Ezt követően a rekordok HashMapjét állítja be alapértékre. (Ha esetleg nem sikerülne betölteni)
	 */
	public Controller() {
		diff = Difficulty.EASY; //alapból easy
		datas = new HashMap<>();
		datas.put(Difficulty.EASY, new Data());
		datas.put(Difficulty.NORMAL, new Data());
		datas.put(Difficulty.HARD, new Data());
	}
	
	/** Beállítja a controller által kezelt játékot*/
	public void setGame(Game g) {game = g;}
	/** Visszaadja a controller által kezelt játékot. (VictoryPanelnél használt.)*/
	public Game getGame() {return game;}
	/** Beállítja a controller által kezelt fejléc játék mezőjének engedélyezettségét*/
	public void setGameMenuBarEn(boolean b) {menuBar.setGameBarEn(b);}
	/** Beállítja a mentés gomb engedélyezettségét */
	public void setSaveBtnEn(boolean b) {menuBar.setSaveBtnEn(b);}
	/** Beállítja a controller által kezelt fejlécet*/
	public void setMenuBar(MyMenuBar m) {menuBar = m;}
	/** Beállítja a controller által kezelt ablakot*/
	public void setWindow(Window w) {window = w;}
	/** Visszaadja a kontroller által kezelt nehézséget. (A SettingsPanel és a VictoryPanelnél használt főként)*/
	public Difficulty getDiff() {return diff;}
	/** Beállítja a controller által kezelt nehézséget*/
	public void setDiff(Difficulty d) {diff = d;}
	/**
	 * Beállítja az ablak Paneljét.
	 * @param panel A beállítandó JPanel
	 * @param needsResize Azt jelöli, hogy szükséges lehet-e az ablak átméretezése. (Leginkább ha játékot kezdünk)
	 */
	public void setPanel(JPanel panel, boolean needsResize) {
		/**
		 * Egyszerűbb itt mindig engedélyezni, aztán máshol letiltani
		 */
		setSaveBtnEn(true); 
		if(!needsResize) resetWindowSize();
		else if(diff.rows() > 15 || diff.cols() > 29) {
			int newW = (diff.cols()+1)*Tile.getW();
			int newH = (diff.rows()+5)*Tile.getW();
			setWindowSize(newW, newH);
		}
		window.setPanel(panel);
	}
	
	/**
	 * Beállítja az ablak méretét.
	 * @param w Az ablak szélessége.
	 * @param h Az ablak magassága.
	 */
	public void setWindowSize(int w, int h) {window.setMinimumSize(new Dimension(w, h));}
	/** Visszaállítja az ablak méretét az alapértelmezett értékére */
	public void resetWindowSize() {window.resetSize();}
	/** Bezárja a játékot. A sima System.exit(0) nem jó mentésekhez*/
	public void closeGame() {window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));}
	/**
	 * Rekord felvétele. A megfelelő nehézséghez adja hozzá.
	 * @param playerName A játékos neve.
	 * @param time A játékos ideje.
	 */
	public void addRecord(String playerName, Time time) {datas.get(diff).addRecord(playerName, time);}
	/**
	 * Megállítja a játékot
	 * @return A játék most meg van-e állítva.
	 */
	public boolean pauseGame() {
		if(game.started() && !game.finished()) {
			game.setRunning(!game.running());
			return game.running();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Rekordok betöltése a program indításakor. Ha nem sikerül, hibaüzenetet ad.
	 */
	public void loadRecords() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("records.rkb"));
			datas = (HashMap<Difficulty, Data>)in.readObject();
			in.close();
		} catch(IOException e) {
			new FeedBackWindow("Couldn't load saved records. (Possibly no saves)", false);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Rekorok elmentése a program bezárásakor.
	 */
	public void saveRecords() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("records.rkb"));
			out.writeObject(datas);
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A megfelelő nehézség adatának lekérdezése.
	 * @param d A kért nehézség.
	 * @return A nehézség adata.
	 */
	public Data getData(Difficulty d) {
		Collections.sort(datas.get(d).list, (a,b)-> a.getTime().compareTo(b.getTime()));
		return datas.get(d);
	}
	
	/**Törli a rekordokat az adatbázisából.*/
	public void clearRecords() {
		datas.get(Difficulty.EASY).list.clear();
		datas.get(Difficulty.NORMAL).list.clear();
		datas.get(Difficulty.HARD).list.clear();
	}
	
	/**
	 * Elmenti a játékállást. Először megállítja a játék futását, aztán megpróbálja elmenteni. A sikertől függően visszaad egy feedback üzenetet.
	 */
	public void saveGame() {
		game.setRunning(false);
		menuBar.setPauseBtnText(false);
		try {
			FileOutputStream f = new FileOutputStream("save.mswp");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(game);
			out.close();
		} catch(IOException e) {
			new FeedBackWindow("Couldn't save game!", false);
			e.printStackTrace();
			return;
		}
		new FeedBackWindow("Game saved successfully!", true);
	}
	
	/**
	 * Betölti az elmentett játékot.
	 * Ha nincs korábbi mentés vagy sérült a fájl, akkor hibaüzenetet dob. 
	 * Egyébként visszatölti a játékot a megállított állapotába.
	 */
	public void loadGame() {
		Game g = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.mswp"));
			g = (Game)in.readObject();
			in.close();
		} catch(IOException | ClassNotFoundException e) {
			new FeedBackWindow("Couldn't load game", false);
			return;
		}
		setGame(g);
		g.getBoard().setController(this);
		setPanel(g, true);
		g.reload(g.getTime());
		menuBar.setPauseBtnText(g.running() && game.started());
	}
}
