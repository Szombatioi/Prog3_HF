package Game;

import java.awt.Image;

public class Tile {
//	boolean isBomb;
	protected boolean isFlagged;
	protected boolean isRevealed;
	
	protected int x,y,w,h;
	protected int bombsAround;
	
	protected Image icon;
	
	public Tile(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		w = 20;
		h = 20;
		icon = img;
	}
	
	public void reveal() {
		
	}
	
	//TODO utánanézni, megcsinálni
	public void draw() {
		
	}
	
}
