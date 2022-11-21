package FrontEnd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import BackEnd.Board;
import BackEnd.Tile;

public class MyMouseListener extends MouseAdapter{
	private Board board;
	private Game game;
	public MyMouseListener(Board b, Game g) {
		board = b;
		game = g;
	}

    @Override
    public void mouseReleased(MouseEvent e) {
        int r = e.getY()/Tile.getW();
        int c = e.getX()/Tile.getW();
        
//        System.out.println(r+"-"+c);
        if(SwingUtilities.isLeftMouseButton(e)) {
        	if(!game.started()) game.start(c,r);
            if(game.running()) board.revealTile(r,c);
        }
        else if(SwingUtilities.isRightMouseButton(e)) {
        	if(game.started() && game.running()) board.flagTile(c,r); 
        }
        
        
        game.repaint();
    }
}
