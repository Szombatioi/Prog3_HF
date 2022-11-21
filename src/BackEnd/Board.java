package BackEnd;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Board implements Serializable{
	private static final long serialVersionUID = -6327125171557330052L;
	
	private Tile[][] tiles;
//	private int placableTiles, hiddenTiles, flagsLeft, bombsLeft;
	private int bombsNr;
	private int rows, cols;
	ArrayList<Bomb> bombs;
	Difficulty diff;
//	ArrayList<Tile> flaggedTiles;
	
	public Board(Difficulty d) {
		diff = d;
		restart();
	}
	//éáéá
	public int getRows() {return rows;}
	public int getCols() {return cols;}
	public int getBombs() {return bombsNr;}
	
	public void restart() {
		int b = diff.bombs(), c = diff.cols(), r = diff.rows();
		bombs = new ArrayList<Bomb>();
//		placableTiles = b;
//		hiddenTiles = c*r;
//		flagsLeft = bombsLeft = 
		bombsNr = b;
		rows = r;
		cols = c;
		
		tiles = new Tile[cols][rows];
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(this);
			}
		}
	}
	
	public void generateBombs(int startX, int startY) {
		Random random = new Random();
		int c,r;
		for(int i = 0; i < bombsNr; i++) {
//			c = random.nextInt(0, cols);
//			r= random.nextInt(0, rows);
			c = random.nextInt(cols);
			r= random.nextInt(rows);
			
			if(c!=startX && r!=startY && !bombs.contains(tiles[c][r])) {
				//TODO ezen szépíteni kéne...
				Bomb result;
//				double chance = random.nextDouble(0,1);
				double chance = random.nextDouble();
				if(chance<=0.05) 
					result = new DifusedBomb(this);
				else if(0.05<chance && chance <= 0.15) 
					result = new ClusterBomb(this);
				else if(0.15<chance && chance <= 0.25)
					result = new ResetFlagBomb(this);
				else if(0.25<chance && chance <= 0.45)
					result = new BigBomb(this);
				else if(0.45<chance && chance <= 0.65)
					result = new ResetBomb(this);
				else 
					result = new Bomb(this);
				bombs.add(result);
				tiles[c][r] = result;
			}
			else i--;
		}
	}
	
	public void setBombsAroundNums() {
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				tiles[i][j].setBombsAround(getBombsAround(j, i));
			}
		}
	}
	
	public int getBombsAround(int row, int col) {
		int sum = 0;
		for(int i = col-1; i <= col+1; i++) {
			for(int j = row-1; j <= row+1; j++) {
				if(j>=0 && j<rows &&  i>=0 && i<cols && (i!=col || j!=row)) sum += tiles[i][j].getValue();
			}
		}
		return sum;
	}
	
	public void revealEveryTile() {
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				tiles[i][j].reveal();
			}
		}
	}
	
	public void findZerosAround(int row, int col) {
		if(row<0 || row>=rows || col<0 || col>=cols) return;
		if(tiles[col][row].getValue()!=0) return;
		if(tiles[col][row].isFlagged || tiles[col][row].isRevealed) return;
		
		tiles[col][row].reveal();
		tiles[col][row].setRevealed(true);
		
		if(tiles[col][row].getBombsAround()<2) {
			findZerosAround(row-1, col);
			findZerosAround(row, col+1);
			findZerosAround(row+1, col);
			findZerosAround(row, col-1);
		}
		
	}
	
	public void revealTile(int row, int col) {
		if(row >= 0 && row < rows && col >= 0 && col < cols && tiles[col][row].getBombsAround()!=0 && tiles[col][row].getBombsAround()!=1) tiles[col][row].reveal();
		else findZerosAround(row, col);
		
//		
//		
//		tiles[col][row].reveal();
//		
//		revealTile(row-1, col);
//		revealTile(row, col+1);
//		revealTile(row+1, col);
//		revealTile(row, col-1);
		
//		if(row >= 0 && row < rows && col >= 0 && col < cols) tiles[col][row].reveal();
//		if(row >= 0 && row < rows && col >= 0 && col < cols) revealEveryTile();
	}
	
//	public void addFlag() {flagsLeft++;}
//	public void rmFlag() {flagsLeft--;}
	public void addMoreBombs(int db) {
		//if(hiddenTiles)
	}
	
	public void paintComponent(Graphics g, int startX) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				int xOffset = /*startX - cols*Tile.getW()/2 + */i*Tile.getW();
				int yOffset = /*startY + */j*Tile.getW();
				tiles[i][j].setCoords(xOffset, yOffset);
				tiles[i][j].paintComponent(g);
			}
		}
	}
}
