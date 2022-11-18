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
	public void setDiff(Difficulty d) {diff = d; System.out.println(diff+"-"+diff.rows()+"-"+diff.cols()+"-"+diff.bombs());}
	public void setPanel(JPanel panel) {window.setPanel(panel);}
	public void setWindowSize(int w, int h) {window.setMinimumSize(new Dimension(w, h));}
	public void resetWindowSize() {window.resetSize();}
}
