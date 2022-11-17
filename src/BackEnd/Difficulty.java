//COMMENT TEST
package BackEnd;

public enum Difficulty {
	EASY(8,8,10), NORMAL(16,16,40), HARD(16,30,99);
	public final int rows, cols, bombs;
	
	private Difficulty(int rows, int cols, int bombs) {
		this.rows = rows;
		this.cols = cols;
		this.bombs = bombs;
	}

	
	
}
