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
/**
 * A játék megnyerése során megjelenő nézet. Lehetőség nyílik elmenteni az időt, ha nem egyéni nehézségen játszottunk.
 */
public class VictoryPanel extends JPanel {
	/**A panel címe.*/
	private JLabel title;
	/**A rekord leadásának gombja. Ha nem megfelelő hosszú a beírt név (min 1, max 33 hosszú), akkor hibaüzenetet dob.*/
	private JButton submit;
	/**A név beírására szolgáló szövegdoboz.*/
	JTextField nameTextField;
	/**Azt tárolja, hogy egyéni nehézségen játszottunk-e.*/
	private boolean custom;
	/**A játékos ideje.*/
	private Time time;
	/**Az őt (is) irányító controller.*/
	private Controller controller;
	/**
	 * A panel konstruktora. Beállítja a controllert, lekéri a játék idejét, beállítja az egyéni nehézséget ellenőrző változó értékét.
	 * Ezt követően létrehozza a grafikus elemeket és beállítja azok actionListener-jeit.
	 * @param controller Az őt (is) irányító controller.
	 */
	public VictoryPanel(Controller controller) {
		this.controller = controller;
		time = controller.getGame().getTime();
		custom = controller.getDiff() == Difficulty.CUSTOM;
		initComponents();
		setActionListeners();
	}
	
	/**
	 * A grafikus elemek létrehozása.
	 */
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
	
	/**
	 * Az actionListener-ek beállítása.
	 */
	public void setActionListeners() {
		submit.addActionListener(a->{
			String text = nameTextField.getText(); 
			if(text.length() > 33 || text.length() <= 0) new FeedBackWindow("Too many/few characters!", false);
			else {
				controller.addRecord(text, time);
				submit.setEnabled(false);
			}
		});
	}
	
}
