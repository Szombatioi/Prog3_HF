package BackEnd;


import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Images {
	public static Font timerFont;
	public static Font MineSweeperFont;
	public static Image one;
	public static Image two;
	public static Image three;
	public static Image four;
	public static Image five;
	public static Image six;
	public static Image seven;
	public static Image eight;
	public static Image resetFlagBomb;
	public static Image redBomb;
	public static Image difusedBomb;
	public static Image resetBomb;
	public static Image bigBomb;
	public static Image clusterBomb;
	public static Image flag;
	public static Image flag2;
	public static Image tile;
	public static Image hTile;
	public static Image icon;
	public static Image error;
	
	public static void loadImages() throws Exception {
		try {		
			timerFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/seg.ttf")).deriveFont(30f);
			MineSweeperFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/mine-sweeper.ttf")).deriveFont(30f);
			icon = ImageIO.read(new File("resources/icon.png"));
			error = ImageIO.read(new File("resources/error.png"));
			one = ImageIO.read(new File("resources/1.png"));
			two = ImageIO.read(new File("resources/2.png"));
			three = ImageIO.read(new File("resources/3.png"));
			four = ImageIO.read(new File("resources/4.png"));
			five = ImageIO.read(new File("resources/5.png"));
			six = ImageIO.read(new File("resources/6.png"));
			seven = ImageIO.read(new File("resources/7.png"));
			eight = ImageIO.read(new File("resources/8.png"));
			resetFlagBomb = ImageIO.read(new File("resources/resetFlagBomb.png"));
			redBomb = ImageIO.read(new File("resources/bombRed.png"));
			bigBomb = ImageIO.read(new File("resources/bigBomb.png"));
			difusedBomb = ImageIO.read(new File("resources/difusedBomb.png"));
			resetBomb = ImageIO.read(new File("resources/resetBomb.png"));
			clusterBomb = ImageIO.read(new File("resources/clusterBomb.png"));
			flag = ImageIO.read(new File("resources/flag.png"));
			flag2 = ImageIO.read(new File("resources/flag2.png"));
			tile = ImageIO.read(new File("resources/tile.png"));
			hTile = ImageIO.read(new File("resources/hTile.png"));
		} catch(Exception e) {
			throw e;
		}
	}
	
}
