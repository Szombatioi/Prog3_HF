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
public class MyMenuBar extends JMenuBar{
	
	private Controller controller;
	JMenu gameMenu;
	private JMenuItem menu, settings, rankList, exit, help, newGame, pause, save;
	
	public MyMenuBar(Controller controller) {
		this.controller = controller;
		initComponents();
		setActionListeners();
	}
	
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
	
	public void setGameBar(boolean b) {
		gameMenu.setEnabled(b);
	}
	
	public void setActionListeners() {
		menu.addActionListener(a -> {
			controller.setPanel(new MenuPanel(controller));
			controller.resetWindowSize();
			controller.setGameMenuBar(false);
		});
		settings.addActionListener(a -> {
			controller.setPanel(new SettingsPanel(controller));
			controller.resetWindowSize();
			controller.setGameMenuBar(false);
		});
		rankList.addActionListener(a->{
			
		});
		exit.addActionListener(a -> System.exit(0));
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
			controller.setPanel(g);
			controller.setGame(g);
			pause.setText("Pause");
		});
		pause.addActionListener(a->{
			pause.setText(controller.pauseGame() ? "Pause" : "Continue"); //pauseGame() azzal tér vissza, hogy MOST meg lesz-e állítva
		});
	}

}
