package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import backend.Images;

@SuppressWarnings("serial")
public class feedBackWindow extends JFrame{
	
	JButton btn;
	
	public feedBackWindow(String text, boolean success) {
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
