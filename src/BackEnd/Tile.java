package BackEnd;

import java.awt.Graphics;
import java.awt.Image;

public class Tile {
	//TODO isBomb törlése
//	protected boolean isBomb; //TODO vagy ez az isBomb boolean vagy public boolean reveal()
	protected boolean isFlagged;
	protected boolean isRevealed; //ahhoz hogy a flood fill megálljon
	protected Board master;
	
	protected int x,y;
	protected int bombsAround;
	
	protected static int w = 25;
	protected Image icon;
	
	public Tile(/*int x, int y, Image img, */Board b) {
//		this.x = x;
//		this.y = y;
		icon = Images.hTile;
		master = b;
//		isBomb = false;
	}
	
	public int addValue() {	return 0; }
	public static int getW() {return w;}
	public int getBombsAround() {return bombsAround;}
	public boolean isFlagged() {return isFlagged;}
	
	public void flag() {
		if(isFlagged) {
			master.rmFlag();
			icon = Images.flag;
		}
		else {
			master.addFlag();
			icon = Images.hTile;
		}
	}
	
	public void setBombsAround(int b) {
		bombsAround = b;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void reveal() {
		isRevealed = true;
		icon = Images.numbers[bombsAround];
	}
	
	//TODO utánanézni, megcsinálni
	public void paintComponent(Graphics g) {
		g.drawImage(icon, x, y, w, w, null);
	}
}
