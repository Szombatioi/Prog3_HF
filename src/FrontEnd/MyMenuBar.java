package FrontEnd;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import BackEnd.Controller;

@SuppressWarnings("serial")
public class MyMenuBar extends JMenuBar{
	
	private Controller controller;
	private JMenuItem menu, settings, exit, help;
	
	public MyMenuBar(Controller controller) {
		this.controller = controller;
		
		JMenu programMenu = new JMenu("Program");
		menu = new JMenuItem("Menu");
		settings = new JMenuItem("Settings");
		exit = new JMenuItem("Exit");
		
		programMenu.add(menu);
		programMenu.add(settings);
		programMenu.addSeparator();
		programMenu.add(exit);
		
		
		
		JMenu gameMenu = new JMenu("Game");
		gameMenu.setEnabled(false);
		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem pause = new JMenuItem("Pause");
		JMenuItem save = new JMenuItem("Save");
		gameMenu.add(newGame);
		gameMenu.add(pause);
		gameMenu.add(save);
		
		JMenu aboutMenu = new JMenu("About");
		help = new JMenuItem("Help");
		aboutMenu.add(help);
		
		add(programMenu);
		add(gameMenu);
		add(aboutMenu);
		
		setActionListeners();
	}
	
	public void initComponents() {
		
	}
	
	public void setActionListeners() {
		menu.addActionListener(a -> controller.setPanel(new MenuPanel(controller)));
		settings.addActionListener(a -> controller.setPanel(new SettingsPanel(controller)));
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
	}

}
