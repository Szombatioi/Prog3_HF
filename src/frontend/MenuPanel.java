package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Controller;
import backend.Images;

@SuppressWarnings("serial")
public class MenuPanel extends JPanel{
	
	JButton newGameBtn, loadBtn, settingsBtn, ranksBtn, exitBtn;
	JLabel title;
	Controller controller;
	
	public MenuPanel(Controller c) {
		controller = c;
		initComponents();
		setActionListeners();
	}
	
	public void initComponents() {
		Font f = new Font("Arial", Font.PLAIN, 24);
		newGameBtn = new JButton("New Game");
		loadBtn = new JButton("Load Game");
		settingsBtn = new JButton("Settings");
		ranksBtn = new JButton("Ranklist");
		exitBtn = new JButton("Exit");
		
		newGameBtn.setFont(f);
		loadBtn.setFont(f);
		settingsBtn.setFont(f);
		ranksBtn.setFont(f);
		exitBtn.setFont(f);
		
		title = new JLabel("MineSweeper 2.0");
		title.setFont(Images.MineSweeperFont);
		
		JPanel creditPanel = new JPanel();
		creditPanel.setLayout(new BorderLayout());
		creditPanel.add(new JLabel("Made by: Szombati Olivér"), BorderLayout.WEST);
		
		JPanel titlePanel = new JPanel();
		titlePanel.add(title);
		
		JPanel elements = new JPanel();
		elements.setBorder(new EmptyBorder(10,200,10,200));
		elements.setLayout(new GridLayout(5,1,0,15));
		elements.add(newGameBtn);
		elements.add(loadBtn);
		elements.add(settingsBtn);
		elements.add(ranksBtn);
		elements.add(exitBtn);
		
		setLayout(new BorderLayout());
		add(titlePanel, BorderLayout.NORTH);
		add(elements, BorderLayout.CENTER);
		add(creditPanel, BorderLayout.SOUTH);
	}
	
	public void setActionListeners() {
		newGameBtn.addActionListener(a -> {
			Game g = new Game(controller);
			controller.setGame(g);
			controller.setPanel(g, true);
			
		});
		loadBtn.addActionListener(a->{
			controller.loadGame();
			controller.setGameMenuBarEn(true);
		});
		settingsBtn.addActionListener(a -> {
			controller.setPanel(new SettingsPanel(controller), false);
			
		});
		ranksBtn.addActionListener(a->{
			controller.setPanel(new RecordPanel(controller), false);
		});
		exitBtn.addActionListener(a -> controller.closeGame());
	}
}
