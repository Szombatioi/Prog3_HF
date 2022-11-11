package Game;
import java.awt.Toolkit;

import Frame.Window;
import Frame.ErrorWindow;

public class Main {
	public static void main(String[] args) {
		boolean working = true;
		
		Images images = new Images();
		try {
			images.loadImages();
		}catch(Exception e) {
			working = false;
			ErrorWindow err = new ErrorWindow("Error", Images.error);
		}
		Window window;
		if(working) window = new Window("MineSweeper 2.0", 600, 400, Toolkit.getDefaultToolkit().getImage("images/icon.png"));
		
	}
}
