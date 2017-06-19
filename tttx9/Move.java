package tttx9;

/**
 * 
 * @author Senerato.
 * A representation for a move. Receives a subgame
 * (one of the nine games that consists of 9 singleFields)
 * and a singleField, a field in a subgame.
 */
public class Move {
	private int subGame = -1;
	private int singleField = -1;
	
	/**
	 * @param subGame one of the nine subgames present in a TTTx9 game
	 * that consists of 9 singleFields.
	 * @param singleField a singleField, a field in a subgame.
	 */
	public Move(int subGame, int singleField) {
		this.subGame = subGame;
		this.singleField = singleField;
	}
	
	/**
	 * Specifies a move in the TTTx9Game.
	 * @param singleField the field in a subTTT game.
	 */
	public Move(int singleField) {
		this.singleField = singleField;
	}
	
	public int getSubGame() {
		return subGame;
	}
	
	public int getSingleField() {
		return singleField;
	}

	public void setSubGame(int subGame) {
		this.subGame = subGame;
	}

	public Coord getCoord() {
		return new Coord(singleField);
	}
}
