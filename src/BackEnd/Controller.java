package BackEnd;

import java.awt.Dimension;

import javax.swing.JPanel;

import FrontEnd.Window;

public class Controller {
	Window window;
	Difficulty diff;
	
	public Controller() {
		diff = Difficulty.EASY; //alapból easy
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
}
