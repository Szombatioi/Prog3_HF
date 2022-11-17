package FrontEnd;

import java.awt.Graphics;

public class Timer{
	private int time;
	
	public Timer() {
		time = 0;
	}

	public int getTime() { return time; }
	
	public void tick() {
		time++;
	}
	
	public void paintComponent(Graphics g) {
		
	}
}
