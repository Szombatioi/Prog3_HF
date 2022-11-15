package FrontEnd;

import java.awt.Graphics;

public class Timer{
//	private boolean running; //TODO nem azt jelenti, hogy véget ért. Lehet pl. pause
	private int time;
	
	public Timer() {
//		running = false;
		time = 0;
	}
	
//	public boolean isRunning() { return running; }
//	public boolean getRunning() {return running; }
	public int getTime() { return time; }
	
	public void tick() {
//		if(running) time++;
		time++;
	}
	
	public void paintComponent(Graphics g) {
		//panelbe?
	}
	
//	public void setRunning(boolean b) {
//		running = b;
//	}
}
