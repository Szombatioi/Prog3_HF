package Game;

import java.io.Serializable;

public class Board implements Serializable{
	private Tile[][] tiles;
	
	public Board(Difficulty d) {
		int r = d.rows();
		int c = d.cols();
		
		//width = getWidth, ...
		//xoffset, yoffset
		//x,y
		
		tiles = new Tile[r][c];
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				tiles[i][j] = new Tile(0,0,null);
			}
		}
	}
	
	public void generateBombs() {
		
	}
	
	public int getBombsAround() {
		int sum = 0;
		return sum;
	}
	
	public void revealTile(int row, int col) {
		
	}
	
	//TODO utánanézni, megoldani
	public void draw() {
		
	}
}
