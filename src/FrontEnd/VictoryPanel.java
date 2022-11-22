package FrontEnd;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BackEnd.Images;

@SuppressWarnings("serial")
public class VictoryPanel extends JPanel {
	int mins, secs;
	JLabel title;
	JButton submit;
	JTextField nameTextField;
	boolean custom;
	
	//lehet a game-t kéne átadni?
	//a custom átadható controllerrel?
	public VictoryPanel(int m, int s, boolean custom) {
		mins = m;
		secs = s;
		this.custom = custom;
		initComponents();
		submit.addActionListener(a->{
			String text = nameTextField.getText(); 
			if(text.length() > 33 || text.length() <= 0) new feedBackWindow("Too many/few characters!", false);
//			else controller.addRecord();
		});
	}
	
	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		title = new JLabel("You won!");
		title.setFont(Images.MineSweeperFont);
		titlePanel.add(title);
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		JPanel centerTime = new JPanel();
		JLabel time = new JLabel("Your time: "+mins+":"+secs);
		time.setFont(new Font("Arial", Font.PLAIN, 18));
		centerTime.add(time);
		
		JPanel centerSubmit = new JPanel();
		submit = new JButton("Submit");
		nameTextField = new JTextField(24);
		nameTextField.setFont(new Font("Arial", Font.PLAIN, 18));
		centerSubmit.add(nameTextField);
		centerSubmit.add(submit);
		nameTextField.setEnabled(!custom);
		submit.setEnabled(!custom);
		
		center.add(centerTime, BorderLayout.NORTH);
		center.add(centerSubmit, BorderLayout.CENTER);
		center.setBorder(new EmptyBorder(100, 0,0,0));
		
		add(titlePanel, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
	}
	
}
