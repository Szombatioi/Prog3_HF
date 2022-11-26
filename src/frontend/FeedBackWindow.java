package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Images;

@SuppressWarnings("serial")
/**
 * Visszajelzésért felelős ablak osztály. Üzenetet ad egy művelet sikerességéről.
 */
public class FeedBackWindow extends JDialog{
	
	/** A bezárás gombja.*/
	JButton btn;
	/**
	 * Az osztály konstruktora. Létrehozza a gombot és a hibaüzenet feliratát, illetve beállítja a grafikai részeket.
	 * @param text A hibaüzenet. Ez jelenik meg középen.
	 * @param success Jelzi, hogy a művelet sikerességét vagy hibáját jelzi-e az ablak.
	 */
	public FeedBackWindow(String text, boolean success) {
		setTitle(success ? "Success" : "Error");
		requestFocus();
		setLocationRelativeTo(null);
		setIconImage(success ? Images.success : Images.error);
		setMinimumSize(new Dimension(225, 100));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btn = new JButton("Exit");
		btn.addActionListener(al -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JPanel textPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		
		textPanel.add(new JLabel(text));
		btnPanel.add(btn);
		
		mainPanel.add(textPanel, BorderLayout.CENTER);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		
		add(mainPanel);
		pack();
		setVisible(true);
	}
}
