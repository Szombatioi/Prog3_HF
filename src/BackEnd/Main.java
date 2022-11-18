package BackEnd;
//import java.awt.Toolkitá;

import FrontEnd.ErrorWindow;
import FrontEnd.Window;

public class Main {
	public static void main(String[] args) {
		boolean working = true;
		try {
			Images.loadImages();
		}catch(Exception e) {
			working = false;
			new ErrorWindow("Error! Couldn't load one or more images!", Images.error);
		}
		if(working) {
			Controller controller = new Controller();
			new Window("MineSweeper 2.0", controller, Images.icon);
		}
	}
}
