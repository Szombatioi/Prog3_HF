package Frame;

public class Timer extends Thread {
	private boolean running; //TODO nem azt jelenti, hogy véget ért. Lehet pl. pause
	private int time;
	
	public Timer() {
		running = false;
		time = 0;
	}
	
	public boolean isRunning() { return running; }
	
	public void tick() {
		time++;
	}
	
	public void draw() {
		//panelbe?
	}
	
	public void run() {
		
	}
}
