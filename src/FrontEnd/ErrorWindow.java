package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ErrorWindow extends JFrame{
	
	JButton btn;
	
	public ErrorWindow(String title, String text, Image icon) {
		setTitle(title);
		requestFocus();
		setLocationRelativeTo(null);
		setIconImage(icon);
//		setMinimumSize(new Dimension(300, 150));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		btn = new JButton("Exit");
		btn.addActionListener(al -> {System.exit(0);});
		
		JPanel panel = new JPanel();
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(btn);
		
		panel.setLayout(new BorderLayout(10, 10));
		panel.add(btnPanel, BorderLayout.SOUTH);
		panel.add(new JLabel(text), BorderLayout.CENTER);
		panel.setBorder(new EmptyBorder(10,10,10,10));
		
		add(panel);
		pack();
		setVisible(true);
		
	}
}
