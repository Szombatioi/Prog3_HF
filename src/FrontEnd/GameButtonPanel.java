package FrontEnd;

import javax.swing.JButton;
import javax.swing.JPanel;

//TODO Probaljuk meg a GamePanelbe tenni mind egy kulon JPanelbe ezt illetve egy masikba a tablat
public class GameButtonPanel extends JPanel {
	JButton newGameBtn, pauseBtn, saveBtn;
	public GameButtonPanel() {
		newGameBtn = new JButton("New"); //TODO setIcon
		pauseBtn = new JButton("Pause");
		saveBtn = new JButton("Save");
		//exit
		//main menu
		
		add(pauseBtn);
		add(newGameBtn);
		add(saveBtn);
	}
}
