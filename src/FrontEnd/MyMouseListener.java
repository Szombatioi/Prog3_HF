package FrontEnd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import BackEnd.Board;
import BackEnd.Tile;

public class MyMouseListener extends MouseAdapter{
	private static Board board;
	private Game game;
	private int xOffset;
//	public MyMouseListener(Board b, Game g) {
//		board = b;
//		game = g;
//		System.out.println(game.getUIClassID());
//	}
	public MyMouseListener() {
		
	}
	
	public void setGame(Game g) {
		game = g;
	}
	public static void setBoard(Board b) {
		board = b;
	}
	
	public void setOffset(int x) {
		xOffset = x;
	}
	

    @Override
    public void mouseReleased(MouseEvent e) {
        int r = e.getY()/Tile.getW();
        
        int c = (e.getX()-xOffset)/Tile.getW();
//        System.out.println(r+"-"+c);
        if(SwingUtilities.isLeftMouseButton(e)) {
        	if(!game.started()) game.start(c,r);
            if(game.running()) board.revealTile(r,c);
        }
        else if(SwingUtilities.isRightMouseButton(e)) {
        	if(game.started() && game.running() && !game.finished()) board.flagTile(c,r); 
        }
        game.repaint();
    }
}
