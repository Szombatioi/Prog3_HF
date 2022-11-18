package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ErrorWindow extends JFrame{
	
	JButton btn;
	
	public ErrorWindow(String text, Image icon) {
		setTitle("Error");
		requestFocus();
		setLocationRelativeTo(null);
		setIconImage(icon);
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
