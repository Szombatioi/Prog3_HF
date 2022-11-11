package Game;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
//import java.awt.Toolkit;

public class Images {
	private int db; //TODO delete
	
	public static Image one;
	public static Image two;
	public static Image three;
	public static Image four;
	public static Image five;
	public static Image six;
	public static Image seven;
	public static Image eight;
	public static Image bomb;
	public static Image redBomb;
	public static Image flag;
	public static Image flag2;
	public static Image tile;
	public static Image hTile;
	
	public static Image images[];
	
	public static Image icon;
	public static Image error;
	
	public static Image test; //TODO delete
	
	public void loadImages() throws Exception {
		
		try {
			icon = ImageIO.read(new File("images/icon.png"));
			error = ImageIO.read(new File("images/error.png"));
			one = ImageIO.read(new File("images/1.png"));
			two = ImageIO.read(new File("images/2.png"));
			three = ImageIO.read(new File("images/3.png"));
			four = ImageIO.read(new File("images/4.png"));
			five = ImageIO.read(new File("images/5.png"));
			six = ImageIO.read(new File("images/6.png"));
			seven = ImageIO.read(new File("images/7.png"));
			eight = ImageIO.read(new File("images/8.png"));
			bomb = ImageIO.read(new File("images/bomb.png"));
			redBomb = ImageIO.read(new File("images/bombRed.png"));
			flag = ImageIO.read(new File("images/flag.png"));
			flag2 = ImageIO.read(new File("images/flag2.png"));
			tile = ImageIO.read(new File("images/tile.png"));
			hTile = ImageIO.read(new File("images/hTile.png"));
//			test = ImageIO.read(new File("images/nope.png"));
		} catch(Exception e) {
			throw e;
		}
	}
	
}
