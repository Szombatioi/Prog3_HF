package frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

import backend.Images;
import backend.Time;

@SuppressWarnings("serial")
public class Timer extends Thread implements Serializable{
	private Time time;
	private boolean finished, running, started;
	private int w,h;
	private int delta;
	
	public Timer() {
		time = new Time();
		finished = false;
		running = false;
		w = 100;
		h=32;
		delta = 10;
	}
	
	public Timer(Time t) {
		this();
		time = t;
	}

	public int getHeight() {return h;}
	public boolean started() {return started;}
	public String toString() {
		return String.format("%02d", time.getM())+":"+String.format("%02d", time.getS());
	}
	
	public Time getTime() {
		return time;
	}
	
	@Override
	public void start() {
		super.start();
		started = true;
	}
	
	@Override
	public void run() {
		while(!finished) {
			if(running) tick();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setFinished(boolean b) {finished = b;}
	public void setRunning(boolean run) {running = run;}
	public boolean running() {return running;}
	
	public void tick() {
		if(time.lt(5999)) time.increase(); //99:60-nál megáll az óra
	}
	
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
