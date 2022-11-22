package BackEnd;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

@SuppressWarnings("serial")
public class Tile implements Serializable{
	protected boolean isFlagged;
	protected boolean isRevealed; //ahhoz hogy a flood fill megálljon
	protected Board master;
	
	protected int x,y;
	protected int bombsAround;
	
	protected static int w = 25;
	transient protected Image icon;
	
	public Tile(Board b) {
		isRevealed = false;
		isFlagged = false;
		icon = Images.hTile;
		master = b;
	}
	
	public int getValue() {	return 0; }
	public static int getW() {return w;}
	public int getBombsAround() {return bombsAround;}
	public boolean isFlagged() {return isFlagged;}
	
	public void flag() {
		if(!isRevealed) {
			isFlagged = !isFlagged;
			master.modFlag(isFlagged ? -1 : 1);
			icon = isFlagged ? Images.flag : Images.hTile;
		}
	}
	
	public void setBombsAround(int b) {
		bombsAround = b;
	}
	
	public void setCoords(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void setRevealed(boolean b) {isRevealed = b;}
	public void reveal() {
		if(!isFlagged) {
			icon = Images.numbers[bombsAround];
			isRevealed = true;
		}
	}
	
	
	public void paintComponent(Graphics g) {
		g.drawImage(icon, x, y, w, w, null);
	}
}
