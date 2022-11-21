package FrontEnd;

import java.awt.Color;
import java.awt.Graphics;

import BackEnd.Images;

public class Timer extends Thread{
	private int time;
	private int x,y;
	private boolean finished, running;
	
	public Timer() {
		time = 0;
		finished = false;
		running = false;
	}

	public String toString() {
		int min = time/60;
		int sec = time%60;
		return String.format("%02d", min)+":"+String.format("%02d", sec);
	}
	
	
	public void run() {
		while(!finished) {
			if(running) { tick(); System.out.println(toString());}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setFinished(boolean b) {
		finished = b;
	}
	
	public void setRunning(boolean run) {
		running = run;
	}
	
	public void tick() {
		time++;
	}
	
	public void paintComponent(Graphics g, int width) {
		g.setFont(Images.timerFont);
		g.setColor(Color.red);
		g.drawString(toString(), width-101,32);
		
	}
}
