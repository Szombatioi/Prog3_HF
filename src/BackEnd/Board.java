package BackEnd;
import java.io.Serializable;

public class Board implements Serializable{
	private Tile[][] tiles;
	private int placableTiles;
	private int hiddenTiles;
	private int rows, cols;
	
	public Board(Difficulty d) {
		placableTiles = d.bombs();	//A java egyből átfordítja a .bombs-ot .bombs()-ra??
		hiddenTiles = d.cols()*d.rows();
		
		rows = d.rows();
		cols = d.cols();
		
		//width = getWidth, ...
		//xoffset, yoffset
		//x,y
		
		tiles = new Tile[rows][cols];
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				tiles[i][j] = new Tile(i*20,j*20,Images.hTile,this);
				tiles[i][j].setBombsAround(getBombsAround(i,j));
			}
		}
	}
	
	public int getRows() {return rows;}
	public int getCols() {return cols;}
	
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
	
	public void tick() {
		
	}
	
	//TODO utánanézni, megoldani
//	public void paintComponent(Graphics g, int startX, int startY) {
//		for(int i = 0; i < tiles.length; i++) {
//			for(int j = 0; j < tiles[i].length; j++) {
//				//calc x,y újra
//				//TODO befejezni
//				int xOffset = 0 + i*20;
//				int yOffset = 0 + j*20;
//				tiles[i][j].setCoords(xOffset, yOffset);
//				tiles[i][j].paintComponent(g);
//			}
//		}
//	}
}
