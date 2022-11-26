package backend;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
/**
 * Adat osztály rekordok eltárolására és táblában való megjelenítésére.
 */
public class Data extends AbstractTableModel{
	/**
	 * Egy adat (tároljon az bármilyen nehézséget) egy listában tárolja a rekordokat.
	 */
	ArrayList<Record> list = new ArrayList<>();
	
	/**
	 * @return Tábla sorainak számát adja vissza.
	 */
	@Override
	public int getRowCount() {
		return list.size();
	}

	/**
	 * @return Tábla oszlopainak számát adja vissza. (Név, idő)
	 */
	@Override
	public int getColumnCount() {
		return 2;
	}
	
	/**
	 * @return Tábla megfelelő oszlopának nevét adja vissza. Az első oszlop mindig a játékosok nevét, a második meg az idejüket tárolja.
	 */
	@Override
	public String getColumnName(int columnIndex){
		return columnIndex == 0 ? "Player" : "Time";
	}
	/**
	 * Érték megszerzése a tábla megfelelő sorából és oszlopából
	 * @param rowIndex : A sor sorszáma
	 * @param columnIndex : Az oszlop sorszáma
	 * @return A tábla megfelelő cellájának értékét adja vissza.
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Record r = list.get(rowIndex);
		if(columnIndex == 0) return r.getName();
		return r.getTime();
	}
	
	/**
	 * Rekord felvétele a táblába
	 * @param playerName a játékos neve
	 * @param time a játékos ideje
	 */
	public void addRecord(String playerName, Time time) {
		list.add(new Record(playerName, time));
		fireTableRowsInserted(getRowCount(), getColumnCount());
	}

}
