package Game;

public enum Difficulty {
	EASY(8,8,10), NORMAL(16,16,40), HARD(30,16,99), CUSTOM(0, 0, 0);
	public int rows, cols, bombs;
	
	private Difficulty(int rows, int cols, int bombs) {
		this.rows = rows;
		this.cols = cols;
		this.bombs = bombs;
	}
	
	public int rows() {return rows;}
	public int cols() {return cols;}
	public int bombs() {return bombs;}
	public void setValues(int r, int c, int b) {rows = r; cols = c; bombs = b;}
	
	
}
