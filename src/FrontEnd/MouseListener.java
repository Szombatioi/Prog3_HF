package FrontEnd;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import BackEnd.Board;
import BackEnd.Tile;

public class MouseListener extends MouseAdapter{
	
	private int x,y;
	private Board board;
	public MouseListener(int x, int y, Board b) {
		this.x = x;
		this.y = y;
		board = b;
	}

    @Override
    public void mouseReleased(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        
        int r = x/Tile.getW();
        int c = y/Tile.getW();
        
        System.out.println(r+"-"+c);
    }
}
