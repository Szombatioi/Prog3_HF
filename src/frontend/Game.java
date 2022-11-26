package frontend;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.JPanel;

import backend.Board;
import backend.Controller;
import backend.Images;
//import backend.Difficulty;
import backend.Time;

@SuppressWarnings("serial")
/**
 * Az aktuálisan futó játék osztálya. Méri a játékos idejét, illetve tárolja a játéktáblát.
 */
public class Game extends JPanel implements Serializable{
	/**A játékos ideje.*/
	private Timer timer;
	/**A játéktábla.*/
	private Board board;
	/**Logikai változók arról, hogy fut-e, elindult-e vagy befejeződött-e a játék.*/
	private boolean running, started, finished;
	/**A játék kirajzolásához szükséges függőleges eltolás. (Mivel az időmérő alá akarjuk kirajzolni a táblát)*/
	int yOffset;
	/**
	 * A játék konstruktora. Létrehozza a táblát az azt irányító controllerrel, beállítja a kezdeti értékeket és aktiválja a fejléc Game opcióját.
	 * @param controller A táblát (is) irányító controller.
	 */
	public Game(Controller controller) {
		board = new Board(this, controller);
		init();
		controller.setGameMenuBarEn(true);
	}
	/**Visszaadja a játékos idejét.*/
	public Time getTime() {	return timer.getTime();	}
	/**A kezdeti értékek beállítása. alapból a játék nem fut, nem fejeződött be és nem is kezdődött el.*/
	public void init() {
		timer = new Timer();
		yOffset = timer.getHeight();
		running = false;
		started = false;
		finished = false;
		addMouseListener(new MyMouseListener(this));
	}
	/**A játék betöltésekor újratölti a képeket, beállítja az időzítőt a mentett játék idejére és elindítja az időmérő szált.*/
	public void reload(Time t) {
		board.loadImages();
		timer = new Timer(t);
		timer.start();
	}
	/**@return Visszaadja, hogy fut-e a játék.*/
	public boolean running() {return running;}
	/**@return Visszaadja, hogy befejeződött-e a játék.*/
	public boolean finished() {return finished;}
	/**@return Visszaadja, hogy elkezdődött-e a játék.*/
	public boolean started() {return started;}
	/**
	 * Beállítja, hogy elkezdődött-e a játék.
	 * @param b A beállítandó érték.
	 */
	public void setStarted(boolean b) {started = b;}
	/**@return visszaadja a játéktáblát*/
	public Board getBoard() {return board;}
	/**Beállítja, hogy befejeződött-e a játék
	 * @param A beállítandó érték.
	 */
	public void setFinished(boolean b) {
		finished = b;
		timer.setRunning(!b);
	}
	/**Beállítja, hogy fut-e a játék
	 * @param A beállítandó érték.
	 */
	public void setRunning(boolean b) {
		running = b;
		timer.setRunning(b);
	}
	/**Újrakezdi a játékot. Új játék indításakor használt.*/
	public void restart() {
		running = true;
		started = false;
		finished = false;
		board.restart();
		
		timer.setFinished(true);
		try {
			timer.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		timer = new Timer();
	}
	/** Elindítja a játékot és a stoppert az első kattintás után. Ekkor generálódnak a bombák és számítódnak ki a cellák körüli bombák számai.*/
	public void start() {
		running = true;
		started = true;
		finished = false;
		board.generateBombs(0);
		board.setBombsAroundNums();
		if(!timer.running()) {
			if(!timer.started()) timer.start();
			timer.setRunning(true);
		}
	}
	/**A grafikus elemek kirajzolása*/
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(Images.MineSweeperFont);
		if(running || !started) { 
			board.paintComponent(g, getWidth()/2, yOffset+10);
			timer.paintComponent(g, getWidth());
		}
		else g.drawString("Game Paused", getWidth()/4-25, getHeight()/2);
		repaint();
	}
	
}
