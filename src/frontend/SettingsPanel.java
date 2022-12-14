package frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import backend.Controller;
import backend.Difficulty;
import backend.Images;

@SuppressWarnings("serial")
/**
 * A beállítások panel. Itt lehet a játék nehézségét beállítani.
 */
public class SettingsPanel extends JPanel{
	/**Az őt (is) irányító controller. */
	private Controller controller;
	/**Legördülő lista a nehézség kiválasztására.*/
	JComboBox<Difficulty> cb;
	/**A sorok, oszlopok, bombák megadásáért felelős szövegdobozok előtti szövegek, illetve a fent megjelenő szöveg.*/
	private JLabel rowText, colText, bombText, titleText;
	/**A sorok oszlopok és bombák megadására használt szövegdobozok.*/
	JTextField rowTextField,colTextField,bombTextField;
	/**A menü, új játék és kilépés gombok.*/
	private JButton menuBtn, newGameBtn, exitBtn;
	/**A kiválasztott nehézség.*/
	private Difficulty chosen;
	/**
	 * A panel konstruktora. Beállítja a controllert, majd beállítja a grafikus elemeket és beállítja azok actionListener-jeit.
	 * @param controller Az őt (is) irányító controller.
	 */
	public SettingsPanel(Controller controller) {
		this.controller = controller;
		chosen = controller.getDiff();
		initComponents();
		setActionListeners();
		setBorder(BorderFactory.createEmptyBorder(0,0,100,0));
	}
	
	/**
	 * A beállítások elmentése. Ha egyéni nehézséget állítunk be és rosszak az értékei, akkor hibaüzenetet ad és nem hajtja végre a mentést.
	 * @return A mentés sikeressége.
	 */
	public boolean submit() {
		int rr=0, cc=0, bb=0;
		Difficulty ch = (Difficulty)cb.getSelectedItem();
		if(ch.equals(Difficulty.CUSTOM)) {
			try {
				rr = Integer.parseInt(rowTextField.getText());
				cc = Integer.parseInt(colTextField.getText());
				bb = Integer.parseInt(bombTextField.getText());
			}catch (Exception e) {
				new FeedBackWindow("Values should be integers!", false);
				return false;
			}
			//game = new Game();
			if(rr > 32 || cc > 32 || rr <= 0 || cc <= 0 || bb < (int)(cc*rr*10/100) || bb >= (int)(cc*rr*90/100)) {
				new FeedBackWindow("Wrong values given!", false);
				return false;
			}
		}
		Difficulty.CUSTOM.setCustom(rr, cc, bb);
		chosen = ch;
		controller.setDiff(chosen);
		return true;
	}
	
	/**
	 * A grafikus elemek létrehozása.
	 */
	public void initComponents() {
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		JPanel titlePanel = new JPanel();
		JPanel comboPanel = new JPanel();
		JPanel setPanel = new JPanel();
		JPanel botPanel = new JPanel();
		
		Difficulty arr[] = {Difficulty.EASY,Difficulty.NORMAL,Difficulty.HARD,Difficulty.CUSTOM};
		cb = new JComboBox<Difficulty>(arr);
		cb.setSelectedItem(chosen);
		comboPanel.add(cb);
		
		rowText = new JLabel("Rows:");
		colText = new JLabel("Cols:");
		bombText = new JLabel("Bombs");
		rowTextField = new JTextField(2);
		colTextField = new JTextField(2);
		bombTextField = new JTextField(2);
		
		rowTextField.setEnabled(false);
		rowTextField.setText(String.valueOf(Difficulty.CUSTOM.rows()));
		colTextField.setEnabled(false);
		colTextField.setText(String.valueOf(Difficulty.CUSTOM.cols()));
		bombTextField.setEnabled(false);
		bombTextField.setText(String.valueOf(Difficulty.CUSTOM.bombs()));
		
		setPanel.add(rowText);
		setPanel.add(rowTextField);
		setPanel.add(colText);
		setPanel.add(colTextField);
		setPanel.add(bombText);
		setPanel.add(bombTextField);
		
		menuBtn = new JButton("Menu");
		newGameBtn = new JButton("New");
		exitBtn = new JButton("Exit");
		
		botPanel.add(newGameBtn);
		botPanel.add(Box.createRigidArea(new Dimension(10,0)));
		botPanel.add(menuBtn);
		botPanel.add(Box.createRigidArea(new Dimension(10,0)));
		botPanel.add(exitBtn);
		
		titleText = new JLabel("Settings");
		titleText.setFont(Images.MineSweeperFont);
		titlePanel.add(titleText);
		titlePanel.setBorder(BorderFactory.createEmptyBorder(0,0,50,0));
		
		center.add(comboPanel, BorderLayout.NORTH);
		center.add(setPanel, BorderLayout.CENTER);
		add(titlePanel, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(botPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * ActionListener-ek beállítása.
	 */
	public void setActionListeners() {
		menuBtn.addActionListener(a -> {
			if(submit()) controller.setPanel(new MenuPanel(controller), false);
			controller.resetWindowSize();
		});
		
		newGameBtn.addActionListener(a -> {
			if(submit()) {
				Game g = new Game(controller);
				controller.setGame(g);
				controller.setPanel(controller.getGame(), true);
			}
			
		});
		exitBtn.addActionListener(a -> controller.closeGame());
		
		cb.addActionListener(a-> {
			chosen = (Difficulty)cb.getSelectedItem();
			boolean val;
			val = chosen.equals(Difficulty.CUSTOM);
			rowTextField.setEnabled(val);
			colTextField.setEnabled(val);
			bombTextField.setEnabled(val);
			
		});
	}
}
