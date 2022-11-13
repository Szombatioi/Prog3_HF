package Game;

import java.awt.Graphics;
import java.io.Serializable;

public class Board implements Serializable{
	private Tile[][] tiles;
	private int placableTiles;
	private int hiddenTiles;
	
	public Board(Difficulty d) {
		placableTiles = d.bombs();	//A java egyből átfordítja a .bombs-ot .bombs()-ra??
		hiddenTiles = d.cols()*d.rows();
		
		int r = d.rows();
		int c = d.cols();
		
		//width = getWidth, ...
		//xoffset, yoffset
		//x,y
		
		tiles = new Tile[r][c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				tiles[i][j] = new Tile(0,0,null,this);
				tiles[i][j].setBombsAround(getBombsAround(i,j));
			}
		}
	}
	
	public void generateBombs() {
		//bombák arányát kitalálni
		//ezen belül az egyes bombatípusok közti százalékos arány kiszámolása
	}
	
	public void revealEveryTile() {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j].reveal();
			}
		}
	}
	
	public int getBombsAround(int row, int col) {
		int sum = 0;
		
		for(int i = row-1; i < row+1; i++) {
			for(int j = col-1; j < col+1; j++) {
				if(i>0 && i < tiles.length && j > 0 && j < tiles[0].length) if(tiles[i][j].isBomb()) sum++;
			}
		}
		
		return sum;
	}
	
	//TODO magába ebbe a függvénybe kell rekurziós floodFill alg.
	public void revealTile(int row, int col) {
		if(!tiles[row][col].isFlagged()) tiles[row][col].reveal(); //mivel ha meg van jelölve, biztosítva van a felfedés elől
		
	}
	
	//TODO utánanézni, megoldani
	public void paintComponent(Graphics g) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				//calc x,y újra
				tiles[i][j].draw(g);
			}
		}
	}
}
