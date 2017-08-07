package tttx9;

/**
 * 
 * @author Senerato.
 * A representation for a move. Receives a subgame
 * (one of the nine games that consists of 9 singleFields)
 * and a singleField, a field in a subgame.
 */
public class Move {
	private int subgame = -1;
	private int singleField = -1;

	/**
	 * @param subgame one of the nine subgames present in a TTTx9 game
	 * that consists of 9 singleFields.
	 * @param singleField a singleField, a field in a subgame.
	 */
	public Move(int subgame, int singleField) {
		if (subgame >= 0 && subgame < 9) {
			if (singleField >= 0 && singleField < 9) {
				this.subgame = subgame;
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
	 * Get the subgame where this Move refers to.
	 * @return the subgame this move refers to.
	 */
	public int getSubGame() {
		return subgame;
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
	 * @param subgame a field (which is larger or equal to 0 and smaller than 9).
	 */
	public void setSubGame(int subgame) {
		if (subgame >= 0 && subgame < 9)
			this.subgame = subgame;
		else
			throw new IllegalArgumentException("Impossible move: the field does not exists in a TTT field");
	}
	
	public void setSingleField(int singleField) {
		if (singleField >= 0 && singleField < 9)
			this.singleField = singleField;
		else
			throw new IllegalArgumentException("Impossible move: the field does not exists in a TTT subgame");
	}

	/**
	 * Get a Coord representation of the singleField in a TTT game where this move refers to.
	 * @return
	 */
	public Coord getSingleFieldCoord() {
		return new Coord(singleField);
	}
	
	@Override
	public boolean equals(Object z) {
		if (this.getClass() != z.getClass())
			return false;
		else {
			Move zMove = (Move) z;
			return subgame == zMove.getSubGame() && singleField == zMove.getSingleField();
		}
	}

	@Override
	public String toString() {
		return "Subgame: " + subgame + " singleField: " + singleField + ".";
	}
}
