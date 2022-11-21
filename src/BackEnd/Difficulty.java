package BackEnd;

import java.io.Serializable;

public enum Difficulty {
		EASY(8,8,10), NORMAL(16,16,40), HARD(16,30,99), CUSTOM(0,0,0);
	
		private int rows, cols, bombs;
		
		private Difficulty(int r, int c, int b) {
			rows = r;
			cols = c;
			bombs = b;
		}
		public void set(int r, int c, int b) {
			CUSTOM.rows = r;
			CUSTOM.cols = c;
			CUSTOM.bombs = b;
		}
		public int rows() {return rows;}
		public int cols() {return cols;}
		public int bombs() {return bombs;}
}