package BackEnd;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;

import FrontEnd.Game;
import FrontEnd.MyMouseListener;
import FrontEnd.Window;
import FrontEnd.feedBackWindow;

public class Controller {
	private Window window;
	private Difficulty diff;
	private static MyMouseListener mml;
	
	public Controller() {
		diff = Difficulty.EASY; //alapból easy
	}
	
	public static void setML(MyMouseListener m) {
		mml = m;
	}
	public void setWindow(Window w) {window = w;}
	public Difficulty getDiff() {return diff;}
	public void setDiff(Difficulty d) {diff = d;}
	public void setPanel(JPanel panel) {
		if(diff.rows() > 21 || diff.cols() > 29) {
			int newW = (diff.cols()+1)*Tile.getW();
			int newH = (diff.rows()+4)*Tile.getW();
			setWindowSize(newW, newH);
		}
		window.setPanel(panel);
	}
	public void setWindowSize(int w, int h) {window.setMinimumSize(new Dimension(w, h));}
	public void resetWindowSize() {window.resetSize();}
	
	public static void passOffset(int x) {
		mml.setOffset(x);
	}
	
	//public void loadImages
	public void save(Game g) {
		try {
			FileOutputStream f = new FileOutputStream("save.mswp");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(g);
			out.close();
		} catch(IOException e) {
			new feedBackWindow("Couldn't save game!", false);
			e.printStackTrace();
			return;
		}
		new feedBackWindow("Game saved successfully!", true);
	}
	
	public Game load() {
		Game res = null;
		try {
			FileInputStream f = new FileInputStream("save.mswp");
			ObjectInputStream in = new ObjectInputStream(f);
			res = (Game)in.readObject();
			in.close();
			
		} catch(IOException | ClassNotFoundException c) {
			new feedBackWindow("Couldn't load game!", false);
		}
		return res;
	}
}
