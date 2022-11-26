package backend;


import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Images osztály, ami a játék képeit, betűtípusait tartalmazza.
 */
public class Images{
	/** Az időzítő betűtípusa*/
	public static Font timerFont;
	/** A játék betűtípusa*/
	public static Font MineSweeperFont;
	/** A cellák körül lévő bombák számainak ikonjai*/
	public static Image numbers[];
	/** A cellák körül lévő (8-nál több) bombák ikonja*/
	public static Image unknown;
	/** A sima bomba ikonja*/
	public static Image bomb;
	/** A zászó eltűntető bomba ikonja*/
	public static Image resetFlagBomb;
	/** A piros hátterű bomba ikonja. */
	public static Image redBomb;
	/** A hatástalanított bomba ikonja*/
	public static Image difusedBomb;
	/** A pályát újrakezdő bomba ikonja*/
	public static Image resetBomb;
	/** A nagy bomba ikonja*/
	public static Image bigBomb;
	/** A cluster bomba ikonja*/
	public static Image clusterBomb;
	/** A sima zászló ikonja*/
	public static Image flag;
	/** Az áthúzott zászló ikonja*/
	public static Image flag2;
	/** A fedett cella ikonja*/
	public static Image hTile;
	/** A játék ablakának ikonja*/
	public static Image icon;
	/** A hibaüzenetek ikonja*/
	public static Image error;
	/** A sikeres műveletek ikonja*/
	public static Image success;
	
	/**
	 * Betölti a játék elején a megfelelő ikonokat.
	 * @throws Exception ha nem sikerült legalább egy ikont betölteni, hibaüzenetet kapunk.
	 */
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
