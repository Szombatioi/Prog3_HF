package backend;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JPanel;

import frontend.Game;
import frontend.MyMenuBar;
import frontend.MyMouseListener;
import frontend.Window;
import frontend.FeedBackWindow;

public class Controller {
	private Window window;
	private Difficulty diff;
	private MyMouseListener mml;
	private MyMenuBar menuBar;
	private Game game;
	private HashMap<Difficulty, Data> datas;
	
	public Controller() {
		diff = Difficulty.EASY; //alapból easy
		datas = new HashMap<>();
		datas.put(Difficulty.EASY, new Data());
		datas.put(Difficulty.NORMAL, new Data());
		datas.put(Difficulty.HARD, new Data());
	}
	
	public void setGame(Game g) {game = g;}
	public Game getGame() {return game;}
	public void setGameMenuBarEn(boolean b) {menuBar.setGameBarEn(b);}
	public void setSaveBtnEn(boolean b) {menuBar.setSaveBtnEn(b);}
	public void setMenuBar(MyMenuBar m) {menuBar = m;}
	public void setWindow(Window w) {window = w;}
	public Difficulty getDiff() {return diff;}
	public void setDiff(Difficulty d) {diff = d;}
	
	public void setPanel(JPanel panel, boolean needsResize) {
		setSaveBtnEn(true); //egyszerűbb itt mindig engedélyezni, aztán máshol letiltani
		if(!needsResize) resetWindowSize();
		else if(diff.rows() > 21 || diff.cols() > 29) {
			int newW = (diff.cols()+1)*Tile.getW();
			int newH = (diff.rows()+5)*Tile.getW();
			setWindowSize(newW, newH);
		}
		window.setPanel(panel);
	}
	
	public void setWindowSize(int w, int h) {window.setMinimumSize(new Dimension(w, h));}
	public void resetWindowSize() {window.resetSize();}
	public void setML(MyMouseListener m) {mml = m;}
	public void passOffset(int x, int y) {mml.setOffset(x, y);}
	
	public void closeGame() {window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));}
	public void addRecord(String playerName, Time time) {datas.get(diff).addRecord(playerName, time);}
	
	public boolean pauseGame() {
		if(game.started() && !game.finished()) {
			game.setRunning(!game.running());
			return game.running();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public void loadRecords() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("records.rkb"));
			datas = (HashMap<Difficulty, Data>)in.readObject();
			in.close();
		} catch(IOException e) {
			new FeedBackWindow("Couldn't load saved records. (Possibly no saves)", false);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void saveRecords() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("records.rkb"));
			out.writeObject(datas);
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Data getData(Difficulty d) {
		Collections.sort(datas.get(d).list, (a,b)-> a.getTime().compareTo(b.getTime()));
		return datas.get(d);
	}
	
	public void clearRecords() {
		datas.get(Difficulty.EASY).list.clear();
		datas.get(Difficulty.NORMAL).list.clear();
		datas.get(Difficulty.HARD).list.clear();
	}
	
	public void saveGame() {
		game.setRunning(false);
		menuBar.setPauseBtnText(false);
		try {
			FileOutputStream f = new FileOutputStream("save.mswp");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(game);
			out.close();
		} catch(IOException e) {
			new FeedBackWindow("Couldn't save game!", false);
			e.printStackTrace();
			return;
		}
		new FeedBackWindow("Game saved successfully!", true);
	}
	
	public void loadGame() {
		
		Game g = null;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("save.mswp"));
			g = (Game)in.readObject();
			in.close();
		} catch(IOException | ClassNotFoundException e) {
			new FeedBackWindow("Couldn't load game", false);
			e.printStackTrace();
			return;
		}
		
		setML(g.getListener());
		setGame(g);
		g.setController(this);
		
		setPanel(g, true);
//		g.repaint();
		g.reload(g.getTime());
		menuBar.setPauseBtnText(g.running() && game.started());
//		System.out.println("Running: "+g.running());
//		System.out.println("Finished: "+g.finished());
//		System.out.println("Started: "+g.started());
		
//		setML(g.getMouseListener());
//		setGame(g);
		
//		g.setController(this);
		
		
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
