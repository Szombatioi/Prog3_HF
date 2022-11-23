package backend;


import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Images{
	public static Font timerFont;
	public static Font MineSweeperFont;
	public static Image numbers[];
	public static Image unknown;
	public static Image bomb;
	public static Image resetFlagBomb;
	public static Image redBomb;
	public static Image difusedBomb;
	public static Image resetBomb;
	public static Image bigBomb;
	public static Image clusterBomb;
	public static Image flag;
	public static Image flag2;
	public static Image hTile;
	public static Image icon;
	public static Image error;
	public static Image success;
	
	public static void loadImages() throws Exception {
		try {		
			timerFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/seg.ttf")).deriveFont(30f);
			MineSweeperFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/mine-sweeper.ttf")).deriveFont(30f);
			icon = ImageIO.read(new File("resources/icon.png"));
			error = ImageIO.read(new File("resources/error.png"));
			success = ImageIO.read(new File("resources/success.png"));
			numbers = new Image[9];
			for(int i = 0; i < 9; i++) {
				numbers[i] =  ImageIO.read(new File("resources/"+i+".png"));
			}
			unknown = ImageIO.read(new File("resources/unknown.png"));
			resetFlagBomb = ImageIO.read(new File("resources/resetFlagBomb.png"));
			redBomb = ImageIO.read(new File("resources/bombRed.png"));
			bigBomb = ImageIO.read(new File("resources/bigBomb.png"));
			difusedBomb = ImageIO.read(new File("resources/difusedBomb.png"));
			resetBomb = ImageIO.read(new File("resources/resetBomb.png"));
			clusterBomb = ImageIO.read(new File("resources/clusterBomb.png"));
			flag = ImageIO.read(new File("resources/flag.png"));
			flag2 = ImageIO.read(new File("resources/flag2.png"));
			hTile = ImageIO.read(new File("resources/hTile.png"));
			bomb = ImageIO.read(new File("resources/bomb.png"));
		} catch(Exception e) {
			throw e;
		}
	}
	
}
