package frontend;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.SwingUtilities;

import backend.Board;
import backend.Tile;

@SuppressWarnings("serial")
public class MyMouseListener extends MouseAdapter implements Serializable{
	private Board board;
	private Game game;
	private int xOffset, yOffset;
	public MyMouseListener(Board b, Game g) {
		board = b;
		game = g;
	}

	public void setBoard(Board b) {
		board = b;
	}
	
	public void setOffset(int x, int y) {
		xOffset = x;
		yOffset = y;
	}
	

    @Override
    public void mouseReleased(MouseEvent e) {
        int r = (e.getY()-yOffset)/Tile.getW();
        int c = (e.getX()-xOffset)/Tile.getW();
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
