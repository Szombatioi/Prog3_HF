package frontend;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Controller;
import backend.Difficulty;
import backend.Images;
import backend.Time;

@SuppressWarnings("serial")
public class VictoryPanel extends JPanel {
//	int mins, secs;
	private JLabel title;
	private JButton submit;
	private JTextField nameTextField;
	private boolean custom;
	private Time time;
	private Controller controller;
	
	
	//lehet a game-t kéne átadni?
	//a custom átadható controllerrel?
	public VictoryPanel(Controller controller, Time t, Difficulty diff) {
		this.controller = controller;
//		mins = t.getM();
//		secs = t.getS();
//		timeString = t.toString();
		time = t;
		custom = diff == Difficulty.CUSTOM;
		initComponents();
		setActionListeners();
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
		JLabel timeLabel = new JLabel("Your time: "+time);
		timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		centerTime.add(timeLabel);
		
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
	
	public void setActionListeners() {
		submit.addActionListener(a->{
			String text = nameTextField.getText(); 
			if(text.length() > 33 || text.length() <= 0) new feedBackWindow("Too many/few characters!", false);
			else {
				controller.addRecord(text, time);
//				new feedBackWindow("No one cares bruh. (Yet)", true);
			}
		});
	}
	
}
