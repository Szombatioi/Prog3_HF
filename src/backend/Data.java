package backend;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class Data extends AbstractTableModel{
	ArrayList<Record> list = new ArrayList<>(); //package láthatóság
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Record r = list.get(rowIndex);
		if(columnIndex == 0) return r.getName();
		return r.getTime();
	}
	
	public void addRecord(String playerName, Time time) {
		list.add(new Record(playerName, time));
		fireTableRowsInserted(getRowCount(), getColumnCount());
	}

}
