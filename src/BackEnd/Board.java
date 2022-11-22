package BackEnd;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import FrontEnd.Game;

@SuppressWarnings("serial")
public class Board implements Serializable{
	
	Game game;
	private Tile[][] tiles;
	private int placableTiles, hiddenTiles, flagsLeft, bombsLeft, bombsNr;
	private int rows, cols;
	ArrayList<Tile> emptyTiles;
	Difficulty diff;
	int xOffset, yOffset;
//	ArrayList<Tile> flaggedTiles;
	
	public Board(Difficulty d, Game g) {
		diff = d;
		xOffset = yOffset = 0;
		restart();
		game = g;
	}
	public int getRows() {return rows;}
	public int getCols() {return cols;}
	public int getBombs() {return bombsNr;}
	
	public void resetGame() {game.setStarted(false);}
	
	public void end() {
		game.setFinished(true);
	}
	
	public void restart() {
		int b = diff.bombs(), c = diff.cols(), r = diff.rows();
		emptyTiles = new ArrayList<Tile>();
		
		placableTiles = b;
		hiddenTiles = c*r;
		flagsLeft = bombsLeft = bombsNr = b;
		rows = r;
		cols = c;
		
		tiles = new Tile[cols][rows];
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(this);
				emptyTiles.add(tiles[i][j]);
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
			
			if(c!=startX && r!=startY && emptyTiles.contains(tiles[c][r])) {
				//TODO ezen szépíteni kéne...
				Bomb result;

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
				emptyTiles.remove(tiles[c][r]);
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
				if(!tiles[i][j].isRevealed) {
					tiles[i][j].revealEnd();
				}
			}
		}
	}
	
	public void findZerosAround(int row, int col) {
		if(row<0 || row>=rows || col<0 || col>=cols) return;
		if(tiles[col][row].getValue()!=0) return;
		if(tiles[col][row].isFlagged || tiles[col][row].isRevealed) return;
		
		tiles[col][row].reveal();
		if(tiles[col][row].getBombsAround()<2) {
			findZerosAround(row-1, col);
			findZerosAround(row, col+1);
			findZerosAround(row+1, col);
			findZerosAround(row, col-1);
		}
		
	}
	
	public void loadImages() {
		for(int i = 0; i < tiles.length; i++) 
			for(int j = 0; j < tiles[i].length; j++) 
				tiles[i][j].loadIcon();
	}
	
	public void revealTile(int row, int col) {
		if(row >= 0 && row < rows && col >= 0 && col < cols && tiles[col][row].getBombsAround()!=0 && tiles[col][row].getBombsAround()!=1) tiles[col][row].reveal();
		else findZerosAround(row, col);
	}
	
	public void flagTile(int col, int row) {
		tiles[col][row].flag();
	}
	public void modFlag(int f) {
		flagsLeft+=f;
		bombsLeft-=f;
	}
	
	public void addMoreBombs(int db) {
		//if(hiddenTiles)
	}
	
	public void paintComponent(Graphics g, int startX) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				xOffset = startX - cols*Tile.getW()/2 + i*Tile.getW();
				yOffset = j*Tile.getW();
				tiles[i][j].setCoords(xOffset, yOffset);
				Controller.passOffset(startX - cols*Tile.getW()/2);
				tiles[i][j].paintComponent(g);
			}
		}
	}
}
