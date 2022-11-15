package BackEnd;

import java.awt.Graphics;
import java.awt.Image;

public class Tile {
	//TODO isBomb t�rl�se
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
	
	//TODO m�s megold�s?
	public boolean isBomb() {
		return isBomb;
	}
	
	public boolean isFlagged() {
		return isFlagged;
	}
	
	public void setBombsAround(int b) {
		bombsAround = b;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void reveal() {
		//TODO switch icon
		icon = Images.tile;
	}
	
	//TODO utánanézni, megcsinálni
	public void paintComponent(Graphics g) {
		g.drawImage(icon, x, y, w, h, null);
	}
	
}
