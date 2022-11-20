package BackEnd;

import java.awt.Graphics;
import java.awt.Image;

public class Tile {
	protected boolean isFlagged;
	protected boolean isRevealed; //ahhoz hogy a flood fill megálljon
	protected Board master;
	
	protected int x,y;
	protected int bombsAround;
	
	protected static int w = 25;
	protected Image icon;
	
	public Tile(Board b) {
		icon = Images.hTile;
		master = b;
	}
	
	public int getValue() {	return 0; }
	public static int getW() {return w;}
	public int getBombsAround() {return bombsAround;}
	public boolean isFlagged() {return isFlagged;}
	
//	public void flag() {
//		if(isFlagged) {
//			master.rmFlag();
//			icon = Images.flag;
//		}
//		else {
//			master.addFlag();
//			icon = Images.hTile;
//		}
//	}
	
	public void setBombsAround(int b) {
		bombsAround = b;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void setRevealed(boolean b) {isRevealed = b;}
	public void reveal() {
		icon = Images.numbers[bombsAround];
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(icon, x, y, w, w, null);
	}
}
