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
		if (subGame >= 0 && subGame < 9) {
			if (singleField >= 0 && singleField < 9) {
				this.subGame = subGame;
				this.singleField = singleField;
			}
			else
				throw new IllegalArgumentException("Impossible move: the field does not exists in a TTT field");
		}
		else
			throw new IllegalArgumentException("Impossible move: the field does not exists in a TTT field");
	}

	/**
	 * Specifies a move in the TTTx9Game.
	 * @param singleField the field in a subTTT game.
	 */
	public Move(int singleField) {
		if (singleField >= 0 && singleField < 9)
			this.singleField = singleField;
		else
			throw new IllegalArgumentException("Impossible move: the field does not exists in a TTT field");
	}

	/**
	 * Get the subGame where this Move refers to.
	 * @return the subGame this move refers to.
	 */
	public int getSubGame() {
		return subGame;
	}

	/**
	 * Get the specific field in a TTT game where this move refers to.
	 * @return the field in the TTT game where this move refers to.
	 */
	public int getSingleField() {
		return singleField;
	}

	/**
	 * Set the subgame where this Move refers to. Throws an exeption if the field does not exists.
	 * @param subGame a field (which is larger or equal to 0 and smaller than 9).
	 */
	public void setSubGame(int subGame) {
		if (subGame >= 0 && subGame < 9)
			this.subGame = subGame;
		else
			throw new IllegalArgumentException("Impossible move: the field does not exists in a TTT field");
	}

	/**
	 * Get a Coord representation of the singleField in a TTT game where this move refers to.
	 * @return
	 */
	public Coord getSingleFieldCoord() {
		return new Coord(singleField);
	}

	@Override
	public String toString() {
		return "Subgame: " + subGame + " singleField: " + singleField + ".";
	}
}
