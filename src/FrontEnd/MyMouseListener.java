package FrontEnd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        if(!game.started()) game.start(r,c);
        if(game.running()) board.revealTile(r,c);
        game.repaint();
    }
}
