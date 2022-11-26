package frontend;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import backend.Controller;

@SuppressWarnings("serial")
/**
 * A játék fejlécét reprezentáló saját készítésű JMenuBar.
 */
public class MyMenuBar extends JMenuBar{
	/** Az őt (is) irányító controller*/
	private Controller controller;
	/** A (csak) játék közben elérhető játékmenü. (Sokszor érvényteleníteni kell, ezért fontos külön változónak felvenni)*/
	JMenu gameMenu;
	/** A menü opciói. Mindegyiknek van actionListener-je, ezért kell őket külön tárolni.*/
	private JMenuItem menu, settings, rankList, exit, help, newGame, pause, save;
	
	/**
	 * A menü fejléc konstruktora. Beállítja a controllert, létrehozza az opcióit, majd beállítja azok actionListener-jeit.
	 * @param controller Az őt (is) irányító controller.
	 */
	public MyMenuBar(Controller controller) {
		this.controller = controller;
		initComponents();
		setActionListeners();
	}
	
	/**
	 * A grafikus elemek létrehozása.
	 */
	public void initComponents() {
		JMenu programMenu = new JMenu("Program");
		programMenu.setMnemonic(KeyEvent.VK_F1);
		menu = new JMenuItem("Menu");
		settings = new JMenuItem("Settings");
		rankList = new JMenuItem("Ranklist");
		exit = new JMenuItem("Exit");
		
		programMenu.add(menu);
		programMenu.add(settings);
		programMenu.add(rankList);
		programMenu.addSeparator();
		programMenu.add(exit);		
		
		gameMenu = new JMenu("Game");
		gameMenu.setMnemonic(KeyEvent.VK_F3);
		gameMenu.setEnabled(false);
		newGame = new JMenuItem("New Game");
		pause = new JMenuItem("Pause");
		save = new JMenuItem("Save");
		gameMenu.add(newGame);
		gameMenu.add(pause);
		gameMenu.add(save);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setMnemonic(KeyEvent.VK_F12);
		help = new JMenuItem("Help");
		aboutMenu.add(help);
		
		add(programMenu);
		add(gameMenu);
		add(aboutMenu);
	}
	
	/**
	 * A "Game" nevű opció engedélyezése.
	 * @param b Az engedélyezés logikai értéke.
	 */
	public void setGameBarEn(boolean b) {gameMenu.setEnabled(b);}
	/**
	 * A mentés gomb engedélyezése. Ha véget ért a játék vagy nyert a játékos, akkor nem lehet menteni már.
	 * @param b Az engedélyezés logikai értéke.
	 */
	public void setSaveBtnEn(boolean b) {save.setEnabled(b);}
	/**
	 * A mentés gomb szövegének beállítása
	 * @param running Annak a logikai értéke, hogy jelenleg fut-e a játék.
	 */
	public void setPauseBtnText(boolean running) {pause.setText(running ? "Pause" : "Continue");}
	
	/**
	 * Az actionListener-ek beállítása.
	 */
	public void setActionListeners() {
		menu.addActionListener(a -> {
			controller.setPanel(new MenuPanel(controller), false);
			controller.setGameMenuBarEn(false);
		});
		settings.addActionListener(a -> {
			controller.setPanel(new SettingsPanel(controller), false);
			controller.setGameMenuBarEn(false);
		});
		rankList.addActionListener(a->{
			controller.setPanel(new RecordPanel(controller), false);
			controller.setGameMenuBarEn(false);
		});
		exit.addActionListener(a -> controller.closeGame());
		
		/**
		 * A help opció egy weboldalt nyit meg, ahol a játék alapvető logikáját tanulhatják meg.
		 */
		help.addActionListener(a -> {
			Desktop desktop = java.awt.Desktop.getDesktop();
			try {
				URI oURL = new URI("https://www.wikihow.com/Play-Minesweeper");
				desktop.browse(oURL);
			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
			}
		});
		newGame.addActionListener(a->{
			Game g = new Game(controller);
			controller.setPanel(g, true);
			controller.setGame(g);
			pause.setText("Pause");
		});
		pause.addActionListener(a->{
			pause.setText(controller.pauseGame() ? "Pause" : "Continue");
		});
		save.addActionListener(a->controller.saveGame());
	}

}
