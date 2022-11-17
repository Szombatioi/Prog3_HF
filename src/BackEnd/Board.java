package BackEnd;
import java.awt.Graphics;
import java.io.Serializable;

public class Board implements Serializable{
	private static final long serialVersionUID = -6327125171557330052L;
	
	private Tile[][] tiles;
	private int placableTiles;
	private int hiddenTiles;
	private int rows, cols;
	private int flagsLeft;
	private int bombs;
	private int bombsLeft;
	
	public Board(int r, int c, int b) {
		restart(r,c,b);
	}
	
	public int getRows() {return rows;}
	public int getCols() {return cols;}
	public int getBombs() {return bombs;}
	
	public void restart(int r, int c, int b) {
		placableTiles = b;
		hiddenTiles = c*r;
		flagsLeft = bombsLeft = bombs = b;
		
		rows = r;
		cols = c;
		
		tiles = new Tile[cols][rows];
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(0,0,Images.hTile,this);
				tiles[i][j].setBombsAround(getBombsAround(i,j));
			}
		}
	}
	
	public void generateBombs() {
		//bombák arányát kitalálni
		//ezen belül az egyes bombatípusok közti százalékos arány kiszámolása
	}
	
	public void revealEveryTile() {
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				tiles[i][j].reveal();
			}
		}
	}
	
	public int getBombsAround(int row, int col) {
		int sum = 0;
		
		for(int i = row-1; i < row+1; i++) {
			for(int j = col-1; j < col+1; j++) {
				if(i>0 && i < cols && j > 0 && j < rows) sum+=tiles[i][j].addValue();
			}
		}
		
		return sum;
	}
	
	//TODO magába ebbe a függvénybe kell rekurziós floodFill alg.
	public void revealTile(int row, int col) {
		if(row<0 || row > rows || col < 0 || col > cols) return;
		if(tiles[col][row].getBombsAround() > 1 || tiles[col][row].addValue()!=0) return;
		
		if(!tiles[col][row].isFlagged()) tiles[row][col].reveal(); //mivel ha meg van jelölve, biztosítva van a felfedés elől
		revealTile(row-1, col);
		revealTile(row, col+1);
		revealTile(row+1, col);
		revealTile(row, col-1);
		
	}
	
	public void addFlag() {
		flagsLeft++;
	}
	
	public void rmFlag() {
		flagsLeft--;
	}
	
	public void tick() {
		
	}
	
	public void addMoreBombs(int db) {
		
	}
	
	public void paintComponent(Graphics g, int startX, int startY) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				int xOffset = startX - cols*Tile.getW()/2 + i*20;
				int yOffset = startY + j*20;
				tiles[i][j].setCoords(xOffset, yOffset);
				tiles[i][j].paintComponent(g);
			}
		}
	}
}
