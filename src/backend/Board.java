package backend;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import frontend.Game;
import frontend.VictoryPanel;

@SuppressWarnings("serial")
/**
 * A cellákat tartalmazó tábla osztálya.
 */
public class Board implements Serializable{
	/** A játék, ami tartalmazza őt.*/
	Game game;
	/**A rajta lévő cellák mátrixa*/
	private Tile[][] tiles;
	/** Értékek a fedett cellákról, az elhelyezhető zászlókról, a hátralévő bombákról és az össz bombaszámról.*/
	private int hiddenTiles, flagsLeft, bombsLeft, bombsNr;
	/** A tábla sorainak és oszlopainak száma*/
	private int rows, cols;
	/** A bombák listája. Generálásnál hasznos.*/
	private ArrayList<Bomb> bombs;
	/** A tábla eltolásának értékei, illetve az első felfedett cella oszlopa és sora*/
	private int xOffset, yOffset, startX, startY;
	/** A táblát (is) kezelő controller.*/
	private transient Controller controller;
	
	/**
	 * A tábla konstruktora. Beállítja a megfelelő értékeket és generál egy (még bombák nélküli) táblát.
	 * @param g Az őt tartalmazó játék.
	 * @param controller Az őt (is) irányító controller.
	 */
	public Board(Game g, Controller controller) {
		this.controller = controller;
		xOffset = yOffset = 0;
		restart();
		game = g;
	}
	
	/**Visszaadja a tábla vertikális eltolását.*/
	public int getYOffset() {return yOffset;}
	/**Visszaadja a tábla függőleges eltolását.*/
	public int getXOffset() {return xOffset;}
	/**Visszaadja a tábla sorainak számát.*/
	public int getRows() {return rows;}
	/**Visszaadja a tábla oszlopainak számát.*/
	public int getCols() {return cols;}
	/**Visszaadja a tábla bombáinak számát.*/
	public int getBombsNr() {return bombsNr;}
	/**Visszaadja a még hátralévő bombák számát.*/
	public int getBombsLeft() {return bombsLeft;}
	/**Visszaadja a még elhelyezhető zászlók számát.*/
	public int getFlagsLeft() {return flagsLeft;}
	/**Visszaadja a még fedett cellák számát.*/
	public int getHiddenTiles() {return hiddenTiles;}
	/**Beállítja az elsőként felfedett cella oszlopát és sorát*/
	public void setStartPos(int x, int y) {
		startX = x;
		startY = y;
	}
	/**Beállítja a még elhelyezhető zászlók számát*/
	public void setFlagsLeft(int i) {flagsLeft = i;}
	/**Beállítja a még hátralévő bombák számát*/
	public void setBombsLeft(int i) {bombsLeft = i;}
	/**Beállítja a táblát kezelő controllert*/
	public void setController(Controller c) {controller = c;}
	/**Eggyel csökkenti a még fedett cellák számát.*/
	public void decHiddenTiles() {hiddenTiles--;}
	/**Visszaállítja a játék kezdeti állapotát (nem generálja újra)*/
	public void resetGame() {
		game.setStarted(false);
		game.setRunning(true);
	}
	/**
	 * Újragenerálja a táblát. Beállítja a megfelelő értékeket majd csinál egy oszlop*sor nagyságú táblát csupa sima cellával. 
	 */
	public void restart() {
		int b = controller.getDiff().bombs(), c = controller.getDiff().cols(), r = controller.getDiff().rows();
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
	/**
	 * Bombák generálása. Annyiszor fut le, ahány bombát el kell helyezzen. Kiválaszt egy véletlenszerű sort és oszlopot, és ha az a
	 * cella nincs felfedve, nem bomba (még) és nem a kezdőkattintás helyén van, akkor egy véletlenszerű bombává teszi azt.
	 * @param startBombNr A kezdeti bombaszám. Játék kezdetekor 0, pálya újrakezdésénél a régi bombaszám értéke.
	 */
	public void generateBombs(int startBombNr) {
		Random random = new Random();
		
		int c,r;
		for(int i = startBombNr; i < bombsNr; i++) { //elsőre ez 0-tól indul, addMoreBombsnál meg a régi bombaszámtól
			c = random.nextInt(cols);
			r= random.nextInt(rows);
			
			if(c!=startX && r!=startY && !bombs.contains(tiles[c][r]) && !tiles[c][r].isRevealed) {
				double chance = random.nextDouble();
				Bomb result = getCorrespondingBomb(chance);
				bombs.add(result);
				tiles[c][r] = result;
			}
			else i--;	
		}
	}
	/**
	 * Egy véletlen szám alapján visszaad egy bombát. 
	 * Hatástalanított bombára 5%, ClusterBombra 10%, zászló eltűntető bombára 10%, nagy bombára 20%, pálya újrakezdő bombára 20% és sima bombára 35% az esély.
	 * @param chance A valószínűség.
	 * @return A megfelelő bomba.
	 */
	public Bomb getCorrespondingBomb(double chance) {
		if(chance<=0.05) 
			return new DifusedBomb(this);
		else if(0.05<chance && chance <= 0.15) 
			return new ClusterBomb(this);
		else if(0.15<chance && chance <= 0.25)
			return new ResetFlagBomb(this);
		else if(0.25<chance && chance <= 0.45)
			return new BigBomb(this);
		else if(0.45<chance && chance <= 0.65)
			return new ResetBomb(this);
		else 
			return new Bomb(this);
	}
	/**
	 * A cellák körüli bombaszámok beállítása.
	 * Végigmegy az összes cellán és lekérdezi a körülöttük lévő bombák számát (getBombsAround), ezt állítja be. 
	 */
	public void setBombsAroundNums() {
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				tiles[i][j].setBombsAround(getBombsAround(j, i));
			}
		}
	}
	/**
	 * Egy cella körüli bombák számát számolja meg. Végigmegy a cella körül és összeszámolja a bombákat.
	 * @param row A cella sorszáma
	 * @param col A cella oszlopszáma
	 * @return A megadott cella körüli bombák száma
	 */
	public int getBombsAround(int row, int col) {
		int sum = 0;
		for(int i = col-1; i <= col+1; i++) {
			for(int j = row-1; j <= row+1; j++) {
				if(j>=0 && j<rows &&  i>=0 && i<cols && (i!=col || j!=row)) sum += tiles[i][j].getValue();
			}
		}
		return sum;
	}
	
	/**
	 * Minden cellát felfed. A játék elvesztésekor használt.
	 */
	public void revealEveryTile() {
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				if(!tiles[i][j].isRevealed) {
					tiles[i][j].revealEnd();
				}
			}
		}
		game.setFinished(true);
		controller.setSaveBtnEn(false);
	}
	/**
	 * Flood-fill algoritmus cellák körüli üres vagy 1 értékű cellák felfedésére.
	 * @param row A cella sorszáma
	 * @param col A cella oszlopszáma
	 */
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
	
	/**
	 * A cellák ikonját tölti be. Játék betöltésekor illetve extra bombák hozzáadásakor használt.
	 */
	public void loadImages() {
		for(int i = 0; i < tiles.length; i++) 
			for(int j = 0; j < tiles[i].length; j++) 
				tiles[i][j].loadIcon();
	}
	/**
	 * Leszedi az összes elhelyezett zászlót a pályáról.
	 */
	public void resetFlags() {
		flagsLeft = bombsLeft = bombsNr;
		for(int i = 0; i < cols; i++) {
			for(int j = 0; j < rows; j++) {
				tiles[i][j].setFlagged(false);
				tiles[i][j].loadIcon();
			}
		}
	}
	/**
	 * Felfedi a megadott cellát. Ha bomba vagy 2-nél több értékű cella, akkor simán felfedi, egyéb esetben meghívja a findZerosAround függvényt.
	 * Bármely felfedés esetén ellenőrzi, hogy nyert-e a játékos.
	 * @param row A felfedésre szánt cella sorszáma.
	 * @param col A felfedésre szánt cella oszlopszáma.
	 */
	public void revealTile(int row, int col) {
		if(row >= 0 && row < rows && col >= 0 && col < cols && tiles[col][row].getBombsAround()!=0 && !tiles[col][row].isFlagged && tiles[col][row].getBombsAround()!=1) tiles[col][row].reveal();
		else findZerosAround(row, col);
		checkEnd();
	}	
	/**
	 * Megjelöl zászlóval egy mezőt. Itt is ellenőrzi, hogy nyert-e a játékos.
	 * @param col A megjelölésre szánt cella oszlopszáma.
	 * @param row A megjelölésre szánt cella sorszáma.
	 */
	public void flagTile(int col, int row) {
		if(row >= 0 && row < rows && col >= 0 && col < cols && !tiles[col][row].isRevealed) tiles[col][row].flag();
		checkEnd();
	}
	/**
	 * Ellenőrzi, hogy nyert-e a játékos. Ez akkor van, ha már nincs jelöletlen (fedett) bomba vagy ha már csak annyi fedett cella van, ahány bomba maradt.
	 */
	public void checkEnd() {
		if(bombsLeft==0 || hiddenTiles==bombsNr) {
			game.setFinished(true);
			controller.setPanel(new VictoryPanel(controller), false);
			controller.setSaveBtnEn(false);
		}
	}
	/**
	 * Extra bombák elhelyezése a pályán. Növeli az elhelyezhető zászlók és a hátralévő bombák számát. Az össz bombaszámot átállítja.
	 * Az ikonokat újratölti és a sima cellák körüli bombák számát újraszámolja.
	 * @param db Ennyi db új bombát kell elhelyezni.
	 */
	public void addMoreBombs(int db) {
		flagsLeft += db;
		int temp = bombsNr;
		bombsNr += db;
		bombsLeft += db;
		generateBombs(temp); 
		setBombsAroundNums();
		loadImages();
	}
	/**
	 * A tábla és celláinak kirajzolása.
	 * @param g A grafikáért felelős paraméter.
	 * @param startX A tábla kezdeti x koordinátája. A Game adja át.
	 * @param startY A tábla kezdeti y koordinátája. A Game adja át.
	 */
	public void paintComponent(Graphics g, int startX, int startY) {
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[i].length; j++) {
				xOffset = startX-cols*Tile.getW()/2;
				yOffset = startY;
				tiles[i][j].setCoords(xOffset + i*Tile.getW(), yOffset+ j*Tile.getW());
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
