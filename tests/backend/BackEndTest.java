package backend;

import static org.junit.Assert.*;
import frontend.Game;
import frontend.MyMenuBar;
import frontend.Window;

import org.junit.Before;
import org.junit.Test;

public class BackEndTest {
	Controller controller;
	Difficulty diff;
	Game g;
	MyMenuBar mmb;
	
	@Before
	public void init() throws Exception {
		controller = new Controller();
		mmb = new MyMenuBar(controller);
		controller.setMenuBar(mmb);
		diff = Difficulty.CUSTOM;
		diff.setCustom(3, 3, 3); //3 bomba egy 3x3-as táblán
		controller.setDiff(diff);
		Images.loadImages();
		g = new Game(controller); //ez egy új boardot csinál
//		controller.setGame(g);
	}
	
	@Test
	public void generateBombsTest() {
		g.getBoard().generateBombs(0);
		g.getBoard().setBombsAroundNums();
		assertNotEquals("Bombs around middle not 0", 0, g.getBoard().tiles[1][1].getBombsAround()); //azt várjuk, hogy ne 0 bomba legyen a középső körül, azaz be legyen állítva a bombsAround.
	}
	
	@Test
	public void flaggedCannotBeRevealedTest() {
		g.getBoard().flagTile(1, 1);
		g.getBoard().revealTile(1, 1);
		assertEquals("Flagged tiles cannot be revealed",false, g.getBoard().tiles[1][1].isRevealed); //azt várjuk, hogy ne fedje fel a megjelölt cellát
	}
	
	@Test
	public void revealEveryTileAndGameOverTest(){
		g.getBoard().revealEveryTile();
		for(int i = 0; i < g.getBoard().getCols(); i++) {
			for(int j = 0; j < g.getBoard().getRows(); j++) {
				assertEquals("Tile ["+i+","+j+"] revealed?", true, g.getBoard().tiles[i][j].isRevealed); //mindegyik legyen felfedve
			}
		}
		assertEquals("Game is finished", true, g.finished());
		assertEquals("Game is not running", false, g.running());
	}
	
	@Test
	public void addMoreBombsTest() {
		g.getBoard().setStartPos(-1, -1); //ez azért kell, hogy ne akadjon ki a rendszer
		g.getBoard().generateBombs(0); //ez generál 3 bombát, 6 mező üres
		assertEquals("3 bombs on board", 3, g.getBoard().getBombsNr());
		g.getBoard().addMoreBombs(3); //hozzáadunk még 1 bombát
		assertEquals("Now 6 bombs on board", 6, g.getBoard().getBombsNr());
	}
	
	@Test
	public void ResetFlagBombTest() {
		g.getBoard().tiles[1][1] = new ResetFlagBomb(g.getBoard());
		g.getBoard().setBombsAroundNums();
		
		g.getBoard().flagTile(0, 0); //megjelölünk 3 mezőt
		g.getBoard().flagTile(1, 0);
		g.getBoard().flagTile(0, 1);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2 && !(i==1 && j==1); j++) {
				assertEquals("These tiles are flagged", true, g.getBoard().tiles[i][j].isFlagged());
			}
		}
		g.getBoard().revealTile(1, 1); //aztán felrobbantjuk a zászló eltüntető bombát
		
		assertEquals("Flags are removed", diff.bombs(), g.getBoard().getFlagsLeft()); //annyi zászló maradt, ahány bomba.
	}
	

	@Test
	public void checkEndTest() {
		diff.setCustom(3, 3, 0);
		g = new Game(controller);
		try {
			g.getBoard().revealTile(0, 0); //bombák nélkül egyből nyer
		} catch (NullPointerException e) {
			//nem csinálunk semmit, ablakot várna, de ide nem kell...
		}
		assertEquals("We won, game is finished", true, g.finished());
	}
	@Test
	public void resizeBigMapTest() {
		Difficulty diff = Difficulty.CUSTOM;
		diff.setCustom(32, 32, 150);
		
		Window w = new Window(null, controller, null);
		w.setVisible(false);
		int width = w.getSize().width;
		int height = w.getSize().height;
		
		controller.setPanel(g, true);
		
		assertNotEquals("Resized window width", width, w.getSize().width);
		assertNotEquals("Resized window height", height, w.getSize().height);
	}
	
	@Test
	public void resetBombTest() {
		g.getBoard().tiles[1][1] = new ResetBomb(g.getBoard()); //beállítjuk a középsőt egy resetbomb-ra
		g.getBoard().setBombsAroundNums();
		
		g.getBoard().revealTile(0, 0); //felfedünk néhány mezőt...
		g.getBoard().revealTile(0, 1);
		g.getBoard().revealTile(1, 2);
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2 && !(i==1 && j==1); j++) {
				assertEquals("These tiles are revealed", true, g.getBoard().tiles[i][j].isRevealed);
			}
		}
		
		g.getBoard().revealTile(1,1); //... majd felrobbantjuk a bombát.
		
		for(int i = 0; i < g.getBoard().getCols(); i++) {
			for(int j = 0; j < g.getBoard().getRows(); j++) {
				assertEquals("Tiles are hidden again", false, g.getBoard().tiles[i][j].isRevealed);
			}
		}
	}
	
	@Test
	public void clusterBombWhenMapIsFull() {
		diff.setCustom(3, 3, 8); //8 bomba egy 3x3-as táblán
		controller.setDiff(diff);
		g = new Game(controller);
		g.getBoard().setStartPos(-1, -1); //hogy ne akadjon ki a generálás
		g.getBoard().generateBombs(0);
		g.getBoard().tiles[1][1] = new ClusterBomb(g.getBoard()); //beállítjuk a középsőt egy resetbomb-ra
		g.getBoard().setBombsAroundNums();
		
		g.getBoard().revealTile(1, 1);
		assertTrue("No extra bombs, game over", g.finished());
		
		for(int i = 0; i < g.getBoard().getCols(); i++) {
			for(int j = 0; j < g.getBoard().getRows(); j++) {
				assertEquals("Tiles are revealed due to revealEveryTile", true, g.getBoard().tiles[i][j].isRevealed);
			}
		}
		
		
	}
	
}
