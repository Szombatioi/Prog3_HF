package backend;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import frontend.Game;
import frontend.VictoryPanel;

@SuppressWarnings("serial")
public class Board implements Serializable{
	
	Game game;
	private Tile[][] tiles;
	private int hiddenTiles, flagsLeft, bombsLeft, bombsNr;
	private int rows, cols;
	private ArrayList<Bomb> bombs;
	private Difficulty diff;
	private int xOffset, yOffset, startX, startY;
	private Controller controller;
	
	public Board(Difficulty d, Game g, Controller controller) {
		diff = d;
		this.controller = controller;
		xOffset = yOffset = 0;
		restart();
		game = g;
	}
	public void setStartPos(int x, int y) {
		startX = x;
		startY = y;
	}
	public int getRows() {return rows;}
	public int getCols() {return cols;}
	public int getBombsNr() {return bombsNr;}
	public int getBombsLeft() {return bombsLeft;}
	public int getFlagsLeft() {return flagsLeft;}
	
	public void setFlagsLeft(int i) {flagsLeft = i;}
	public void setBombsLeft(int i) {bombsLeft = i;}
	
	public int getHiddenTiles() {return hiddenTiles;}
	
	public void resetGame() {
		game.setStarted(false);
		game.setRunning(true);
	}
	
	public void end() {
		game.setFinished(true);
	}
	public void restart() {
		int b = diff.bombs(), c = diff.cols(), r = diff.rows();
		bombs = new ArrayList<Bomb>();
		hiddenTiles = c*r;
		flagsLeft = bombsLeft = bombsNr = b;
		rows = r;
		cols = c;
		
		tiles = new Tile[cols][rows];
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				tiles[i][j] = new Tile(this);
			}
		}
	}
	
	public void generateBombs() {
		generateBombs(0);
	}
	
	static int testNum = 0;
	public void generateBombs(int startBombNr) {
		Random random = new Random();
		
		int c,r;
		for(int i = startBombNr; i < bombsNr; i++) { //elsőre ez 0-tól indul, addMoreBombsnál meg a régi bombaszámtól
			c = random.nextInt(cols);
			r= random.nextInt(rows);
			
			if(c!=startX && r!=startY && !bombs.contains(tiles[c][r]) && !tiles[c][r].isRevealed) {
				double chance = random.nextDouble();
//				double chance = .46;
				Bomb result = getCorrespondingBomb(chance);
				bombs.add(result);
				tiles[c][r] = result;
			}
			else i--;	
		}
	}
	
	public Bomb getCorrespondingBomb(double chance) {
		Bomb res = null;
		if(chance<=0.05) 
			res = new DifusedBomb(this);
		else if(0.05<chance && chance <= 0.15) 
			res = new ClusterBomb(this);
		else if(0.15<chance && chance <= 0.25)
			res  = new ResetFlagBomb(this);
		else if(0.25<chance && chance <= 0.45)
			res  = new BigBomb(this);
		else if(0.45<chance && chance <= 0.65)
			res  = new ResetBomb(this);
		else 
			res = new Bomb(this);
		return res;
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
	public void resetFlags() {
		flagsLeft = bombsLeft = bombsNr;
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				tiles[i][j].setFlagged(false);
				tiles[i][j].loadIcon();
			}
		}
	}
	public void revealTile(int row, int col) {
		if(row >= 0 && row < rows && col >= 0 && col < cols && tiles[col][row].getBombsAround()!=0 && !tiles[col][row].isFlagged && tiles[col][row].getBombsAround()!=1) tiles[col][row].reveal();
		else findZerosAround(row, col);
		checkEnd();
	}
	
	public void checkEnd() {
		if(bombsLeft==0 || hiddenTiles==bombsNr) {
			game.setFinished(true);
			controller.setPanel(new VictoryPanel(controller, game.getTime(), diff));
		}
	}
	
	public void decHiddenTiles() {hiddenTiles--;}
	
	public void flagTile(int col, int row) {
		if(row >= 0 && row < rows && col >= 0 && col < cols && !tiles[col][row].isRevealed) tiles[col][row].flag();
		checkEnd();
	}
	
	public void addMoreBombs(int db) {
		flagsLeft += db;
		int temp = bombsNr;
		bombsNr += db;
		bombsLeft += db;
		generateBombs(temp); 
		setBombsAroundNums();
		loadImages();
	}
	
	public void paintComponent(Graphics g, int startX, int startY) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				xOffset = startX - cols*Tile.getW()/2 + i*Tile.getW();
				yOffset = startY + j*Tile.getW();
				tiles[i][j].setCoords(xOffset, yOffset);
				controller.passOffset(startX - cols*Tile.getW()/2, startY);
				tiles[i][j].paintComponent(g);
			}
		}
		
		g.setFont(Images.timerFont);
		g.setColor(Color.gray);
		g.fillRect(0,0,startX/4,startY-5);
		g.setColor(Color.black);
		g.drawRect(0,0,startX/4,startY-5);
		g.setColor(Color.red);
		g.drawString(((Integer)flagsLeft).toString(), 0, startY-10);
	}
}
