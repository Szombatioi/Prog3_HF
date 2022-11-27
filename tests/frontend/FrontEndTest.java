package frontend;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import backend.Controller;
import backend.Difficulty;

public class FrontEndTest {
	Controller controller;
	
	@Before
	public void init() {
		controller = new Controller();
	}

	@Test
	public void WrongSubmitTest() {
		controller = new Controller();
		SettingsPanel s = new SettingsPanel(controller);
		s.cb.setSelectedItem(Difficulty.CUSTOM);
		Difficulty diff = Difficulty.CUSTOM;
		diff.setCustom(-1, 33, 3);
		
		assertFalse("Wrong values given.", s.submit());
	}
	
}
