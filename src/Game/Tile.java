package Game;

import java.awt.Graphics;
import java.awt.Image;

public class Tile {
	protected boolean isBomb; //TODO vagy ez az isBomb boolean vagy public boolean reveal()
	protected boolean isFlagged;
//	protected boolean isRevealed;
	protected Board master;
	
	protected int x,y,w,h;
	protected int bombsAround;
	
	protected Image icon;
	
	public Tile(int x, int y, Image img, Board b) {
		this.x = x;
		this.y = y;
		w = 20;
		h = 20;
		icon = img;
		master = b;
		isBomb = false;
	}
	
	public boolean isBomb() {
		return isBomb;
	}
	
	public boolean isFlagged() {
		return isFlagged;
	}
	
	public void setBombsAround(int b) {
		bombsAround = b;
	}
	
	public void reveal() {
		//TODO switch icon
	}
	
	//TODO utánanézni, megcsinálni
	public void draw(Graphics g) {
		g.drawRect(x, y, w, h); //TODO draw Image
	}
	
}
