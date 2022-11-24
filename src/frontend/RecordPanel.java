package frontend;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backend.Controller;
import backend.Data;
import backend.Images;

@SuppressWarnings("serial")
public class RecordPanel extends JPanel{
	JButton clearBtn, menuBtn;
	JTable table;
	JScrollPane tablePane;
	JLabel title;
	Controller controller;
	
	private Data data;
	
	public RecordPanel(Controller controller) {
		this.controller = controller;
		data = controller.getData();
		initComponents();
		setActionListeners();
	}
	
//	public void setData(Data d) {data = d;}
//	public Data getData() {return data;}
	
	public void initComponents() {
		setLayout(new BorderLayout());
		
		title = new JLabel("Ranklist");
		title.setFont(Images.MineSweeperFont);
		JPanel titlePanel = new JPanel();
		titlePanel.add(title);
		
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
	
	public void setActionListeners() {
		menuBtn.addActionListener(a-> controller.setPanel(new MenuPanel(controller)));
		clearBtn.addActionListener(a-> controller.clearRecords());
	}
}
