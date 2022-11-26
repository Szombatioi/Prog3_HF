package frontend;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.SwingUtilities;

import backend.Board;
import backend.Tile;

@SuppressWarnings("serial")
/**
 * Egérkezelő osztály. Ez felel a mezők felfedésének, megjelölésének detektálásáért.
 */
public class MyMouseListener extends MouseAdapter implements Serializable{
	/**	A játék táblája. Hasznosabb eltárolni mint sokszor lekérdezgetni a játéktól.*/
	private Board board;
	/** Az éppen futó játék. */
	private Game game;
	/**Az egérkezelő konstruktora. Beállítja a játékot és a táblát.*/
	public MyMouseListener(Game g) {
		game = g;
		setBoard(game.getBoard());
	}

	/**
	 * Beállítja a táblát, amit figyel.
	 * @param b A beállítandó tábla.
	 */
	public void setBoard(Board b) {board = b;}
	/**
	 * Az egér felengedése. Ha felengedi a játékos az egér egy gombját, akkor kiszámolja a koordinátákra eső sor- és oszlopszámot,
	 * majd bal kattintás esetén felfedi a cellát (és esetleg elindítja a játék futását), jobb kattintás esetén megjelöli a cellát (ha fut a játék).
	 */
    @Override
    public void mouseReleased(MouseEvent e) {
        int r = (e.getY()-board.getYOffset())/Tile.getW();
        int c = (e.getX()-board.getXOffset())/Tile.getW();
        if(SwingUtilities.isLeftMouseButton(e)) {
        	if(!game.started()) {
        		board.setStartPos(c,r);
        		game.start();
        	}
            if(game.running()) board.revealTile(r,c);
        }
        else if(SwingUtilities.isRightMouseButton(e)) {
        	if(game.started() && game.running() && !game.finished()) board.flagTile(c,r); 
        }
        game.repaint();
    }
}
