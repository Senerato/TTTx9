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
	
	public int getSubGame() {
		return subGame;
	}
	
	public int getSingleField() {
		return singleField;
	}
}
