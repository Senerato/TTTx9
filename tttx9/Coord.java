package tttx9;

public class Coord {

	private int x;
	private int y;
	
	/**
	 * A coordinate in a 2D space, represented as
	 * (x, y).
	 * @param x the x value of a coordinate
	 * @param y the y value of a coordinate
	 */
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * A coordinate in a 2D space, represented as
	 * (x, y). Give the position in a TTT game
	 * (an integer ranging from 0 to 8) to create
	 * a coord.
	 * @param pos the position in a subTTTGame.
	 */
	public Coord(int pos) {
		this.x = pos % 3;
		this.y = pos / 3;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
