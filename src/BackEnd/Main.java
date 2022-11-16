package BackEnd;
//import java.awt.Toolkitá;

import FrontEnd.ErrorWindow;
import FrontEnd.Window;

public class Main {
	public static void main(String[] args) {
		boolean working = true;
		
		//Images images = new Images();
		try {
			Images.loadImages();
		}catch(Exception e) {
			working = false;
			ErrorWindow err = new ErrorWindow("Error", Images.error);
		}
		Window window = null;
		//if(working) window = new Window("MineSweeper 2.0", 600, 400, Toolkit.getDefaultToolkit().getImage("images/icon.png"));
		if(working) window = new Window("MineSweeper 2.0", 600, 400, Images.icon);
		
		
		
		
	}
}
