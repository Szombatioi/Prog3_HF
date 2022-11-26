package frontend;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backend.Controller;
import backend.Data;
import backend.Difficulty;
import backend.Images;

@SuppressWarnings("serial")
/**
 * A rekordok megtekintésére szolgáló JPanel. 3 nehézség között lehet váltani, mindegyiket egy táblázatban jeleníti meg.
 */
public class RecordPanel extends JPanel{
	/**A panel alján lévő törlés és menü gombok.*/
	JButton clearBtn, menuBtn;
	/**A rekordokat megjelenítő táblázat.*/
	JTable table;
	/**Gördítősáv a táblázat többi sorának megtekintéséhez.*/
	JScrollPane tablePane;
	/**Nehézség kiválasztására szolgáló legördülő lista.*/
	JComboBox<Difficulty> diffSelector;
	/**A panel felirata.*/
	JLabel title;
	/**A panelt (is) irányító controller.*/
	Controller controller;
	/**A jelenleg megjelenítendő adat a táblázatban. (Alapból a könnyű nehézség rekordjai)*/
	private Data data;
	/**
	 * A panel konstruktora. Beállítja a controllert, a megjelenítendő (alap) adatot, létrehozza a grafikus elemeket, majd beállítja azok actionListener-jeit.
	 * @param controller Az őt (is) irányító controller.
	 */
	public RecordPanel(Controller controller) {
		this.controller = controller;
		data = controller.getData(Difficulty.EASY);
		initComponents();
		setActionListeners();
	}
	/**
	 * A grafikus elemek létrehozása.
	 */
	public void initComponents() {
		setLayout(new BorderLayout());
		
		title = new JLabel("Ranklist");
		title.setFont(Images.MineSweeperFont);
		
		Difficulty arr[] = {Difficulty.EASY,Difficulty.NORMAL,Difficulty.HARD};
		diffSelector = new JComboBox<Difficulty>(arr);
		JPanel titlePanel = new JPanel();
		
		titlePanel.add(title);
		titlePanel.add(diffSelector);
		
		JPanel tablePanel = new JPanel();
		table = new JTable();
		table.setModel(data);
		tablePane = new JScrollPane(table);
		tablePanel.add(tablePane);
		
		JPanel bottomPanel = new JPanel();
		menuBtn = new JButton("Menu");
		clearBtn = new JButton("Clear");
		bottomPanel.add(clearBtn);
		bottomPanel.add(menuBtn);
		
		add(titlePanel, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Az actionListener-ek beállítása.
	 */
	public void setActionListeners() {
		menuBtn.addActionListener(a-> controller.setPanel(new MenuPanel(controller), false));
		clearBtn.addActionListener(a-> {
			controller.clearRecords();
			table.setModel(controller.getData(Difficulty.EASY));
			repaint();
		});
		diffSelector.addActionListener(a->{
			table.setModel(controller.getData((Difficulty)diffSelector.getSelectedItem()));
		});
	}
}
