package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import BackEnd.Images;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = -7952030833962914692L;
	
	JButton newGameBtn, loadBtn, settingsBtn, ranksBtn, exitBtn;
	JLabel title;
	
	public MenuPanel() {
		Font f = new Font("Arial", Font.PLAIN, 24);
		newGameBtn = new JButton("New Game");
		loadBtn = new JButton("Load Game");
		settingsBtn = new JButton("Settings");
		ranksBtn = new JButton("Ranklist");
		exitBtn = new JButton("Exit");
		exitBtn.addActionListener(a -> System.exit(0));
		
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
}
