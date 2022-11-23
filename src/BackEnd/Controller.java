package BackEnd;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

import FrontEnd.Game;
//import FrontEnd.GamePanel;
import FrontEnd.MenuPanel;
import FrontEnd.MyMenuBar;
import FrontEnd.MyMouseListener;
import FrontEnd.SettingsPanel;
import FrontEnd.Window;
import FrontEnd.feedBackWindow;

public class Controller {
	private Window window;
	private Difficulty diff;
	private MyMouseListener mml;
	private MyMenuBar menuBar;
	private Game game;
	//LinkedList<> records;
	//addRecord(type, name, value)
	
	public Controller() {
		diff = Difficulty.EASY; //alapból easy
	}
	
	public void setMenuBar(MyMenuBar m) {menuBar = m;}
	public void setGame(Game g) {game = g;}
	
	public void setGameMenuBar(boolean b) {
		menuBar.setGameBar(b);
	}
	
	public void setWindow(Window w) {window = w;}
	public Difficulty getDiff() {return diff;}
	public void setDiff(Difficulty d) {diff = d;}
	public void setPanel(JPanel panel) {
		if(diff.rows() > 21 || diff.cols() > 29) {
			int newW = (diff.cols()+1)*Tile.getW();
			int newH = (diff.rows()+5)*Tile.getW();
			setWindowSize(newW, newH);
		}
		window.setPanel(panel);
	}
	public void setWindowSize(int w, int h) {window.setMinimumSize(new Dimension(w, h));}
	public void resetWindowSize() {window.resetSize();}
	
	public void setML(MyMouseListener m) {mml = m;}
	public void setMMLBoard(Board b) {mml.setBoard(b);}
	public void passOffset(int x, int y) {mml.setOffset(x, y);}
	public boolean pauseGame() {
		if(game.started() && !game.finished()) {
			game.setRunning(!game.running());
			return game.running();
		}
		return true;
	}
	
	public void save(Game g) {
//		try {
//			FileOutputStream f = new FileOutputStream("save.mswp");
//			ObjectOutputStream out = new ObjectOutputStream(f);
//			out.writeObject(g);
//			out.close();
//		} catch(IOException e) {
//			new feedBackWindow("Couldn't save game!", false);
//			e.printStackTrace();
//			return;
//		}
//		new feedBackWindow("Game saved successfully!", true);
	}
	
	public Game load() {
		return null;
//		Game res = null;
//		try {
//			FileInputStream f = new FileInputStream("save.mswp");
//			ObjectInputStream in = new ObjectInputStream(f);
//			res = (Game)in.readObject();
//			in.close();
//			
//		} catch(IOException | ClassNotFoundException c) {
//			new feedBackWindow("Couldn't load game!", false);
//		}
//		
//		res.getBoard().loadImages();
//		res.setRunning(false);
//		return res;
	}
}
