package backend;
//import java.awt.Toolkitá;

import frontend.Window;
import frontend.FeedBackWindow;

/**
 * A main osztály. Innen indul a program.
 */
public class Main {
	/**
	 * Ha sikeresen beöltötte a képeket, akkor megjelenik a program ablaka. Ha nem, akkor hibaüzenetet kapunk.
	 */
	public static void main(String[] args) {
		boolean working = true;
		try {
			Images.loadImages();
		}catch(Exception e) {
			working = false;
			new FeedBackWindow("Error! Couldn't load one or more images!", false);
		}
		if(working) {
			Controller controller = new Controller();
			new Window("MineSweeper 2.0", controller, Images.icon);
		}
		
	}
}
