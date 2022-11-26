package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import backend.Images;
import backend.Time;

@SuppressWarnings("serial")
/**
 * Idő számlálására alkalmas osztály. 00:00-tól elkezdve számolja a játékos idejét. Külön szálként működik.
 */
public class Timer extends Thread implements Serializable{
	/**A játék ideje.*/
	private Time time;
	/**Állapot arról, hogy befejeződött-e a számláló feladata, fut-e vagy elkezdődött-e már a számláló*/
	private boolean finished, running, started;
	/**A kirajzoláshoz kell, hogy milyen széles és magas területen rajzoljuk ki az időt.*/
	private int w,h;
	/**Kis körítés a rajzok köré.*/
	private int delta;
	/**
	 * Az időszámláló osztály konstruktora. Létrehozza az időt, illetve beállítja a kezdeti értékeket.
	 */
	public Timer() {
		time = new Time();
		finished = false;
		running = false;
		w = 100;
		h=32;
		delta = 10;
	}
	
	/**
	 * Egy másik konstruktor, ami játék betöltésekor használt.
	 * @param t A már meglévő játék ideje.
	 */
	public Timer(Time t) {
		this();
		time = t;
	}
	/**
	 * Visszaadja az időzítő kirajzolásának magasságát. (A még elhelyezhető zászlók kirajzolásához kell.)
	 * @return A kirajzolás magassága.
	 */
	public int getHeight() {return h;}
	/**
	 * @return Elkezdődött-e már a számláló.
	 */
	public boolean started() {return started;}
	/**Kirajzoláshoz használt toString() metódus. 2db 0-ra kerekíti az értékeket.*/
	public String toString() {
		return String.format("%02d", time.getM())+":"+String.format("%02d", time.getS());
	}
	/**
	 * @return Visszaadja a mért időt.
	 */
	public Time getTime() {
		return time;
	}
	
	/**
	 * A szál elindítása. A started mező értékét igazra állítja.
	 */
	@Override
	public void start() {
		super.start();
		started = true;
	}
	
	/**
	 * A szál futása. Amíg nem fejeződött be, addig másodpercenként számol egyet.
	 */
	@Override
	public void run() {
		while(!finished) {
			if(running && time.lt(5999)) time.increase();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**Beállítja a finished mező értékét
	 * @param b A beállítandó érték.
	 */
	public void setFinished(boolean b) {finished = b;}
	/**
	 * Beállítja a running mező értékét.
	 * @param b A beállítandó érték.
	 */
	public void setRunning(boolean b) {running = b;}
	/**Visszaadja, hogy fut-e az időzítő.*/
	public boolean running() {return running;}
	/**
	 * Kirajzolja az időzítőt az ablak szélességének megfelelően bal oldalra.
	 * @param g A grafikáért felelős paraméter.
	 * @param width Az ablak szélessége.
	 */
	public void paintComponent(Graphics g, int width) {
		g.setFont(Images.timerFont);
		
		g.setColor(Color.gray);
		g.fillRect(width-w-delta, 0, width-delta-5, h+5);
		g.setColor(Color.black);
		g.drawRect(width-w-delta, 0, width-delta-5, h+5);
		
		g.setColor(Color.red);
		g.drawString(time.toString(), width-w-delta, h);
		
	}
}
