package Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnActionListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("exit")) {
			System.exit(0);
		}
	}
	
}
