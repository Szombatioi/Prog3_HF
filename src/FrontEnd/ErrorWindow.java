package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ErrorWindow extends JFrame{
	
	JButton btn;
	
	public ErrorWindow(String title, Image icon) {
		//jpanel
		//actionlistener
		setTitle(title);
		requestFocus();
		setLocationRelativeTo(null);
		setIconImage(icon);
//		setMinimumSize(new Dimension(300, 150));
		setResizable(false);
		setVisible(true);
		
		
		btn = new JButton("Exit");
		btn.setActionCommand("exit");
		BtnActionListener al = new BtnActionListener();
		btn.addActionListener(al);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(10, 10));
		
		panel.add(btn, BorderLayout.SOUTH);
		panel.add(new JLabel("Error! Couldn't load one or more images!"), BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		add(panel);
		pack();
		
	}
}
