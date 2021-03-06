package tttx9;

import java.util.ArrayList;

/**
 * 
 * @author Senerato.
 *
 */
public class Subgame {
	private int id; // The id of this subgame.
	private int[] subgameOwners = new int[9]; // All fields have an owner, 0 for no owner, 1 for player 1, 2 for player 2.
	private Player winner = null; //The winner of this subgame.
	private GameResult subgameResult = GameResult.UNFINISHED;

	public Subgame() {
		for (int owner: subgameOwners)
			owner = 0;
	}
	
	/**
	 * Retrieve the result of the subgame: whether the game is 
	 * unfinished, ended in a draw, or is won by either player.
	 * @return a GameResult representing the result of the subgame.
	 */
	public GameResult getSubgameResult() {
		return this.subgameResult;
	}

	/**
	 * Get a list of all the fields in this subgame with their owners.
	 * 0 represents neutral, 1 represents player 1, 2 represents player 2.
	 * @return an array with field 0 to 8 representing its owner.
	 */
	public int[] getSubGameOwners() {
		return subgameOwners;
	}

	/**
	 * Get the owner of a specific field.
	 * @param field the field whereoff the owner should be given
	 * @return the owner of the specified field
	 */
	public int getOwner(int field) {
		return subgameOwners[field];
	}

	/**
	 * Get the owner of a specific field by providing a Coord.
	 * @param coord the Coord of the field whereoff the owner 
	 * should be given
	 * @return the owner of the specified field
	 */
	public int getOwner(Coord coord) {
		return subgameOwners[coord.getY() / 3 * 3 + coord.getX()];
	}

	/**
	 * Set the owner of a specified field.
	 * @param field the field of which the owner should be changed.
	 * @param newOwner the player that is going to own the specified
	 * field.
	 */
	public void setOwner(int field, Player newOwner) {
		subgameOwners[field] = newOwner.getId();
		if (checkIsWon(newOwner)) {
			this.winner = newOwner;
			this.subgameResult = GameResult.WON;
		}
		if (checkEndedInDraw())
			this.subgameResult = GameResult.DRAW;
	}

	/**
	 * Checks whether or not the game has ended in a draw.
	 * @return true if the game ended in a draw, false otherwise.
	 */
	private boolean checkEndedInDraw() {
		for (int singleField : subgameOwners)
			if (singleField == 0)
				return false;
		return true;
	}

	/**
	 * Checks whether there is a winner in the subgame, if that is the
	 * case, the function returns true, false otherwise.
	 * @return true if there is a winner, false otherwise.
	 */
	private boolean checkIsWon(Player player) {
		ArrayList<int[]> winningCombinations = new ArrayList<int[]>();
		for (int x = 0; x < 3; x++) // Vertical lines
			winningCombinations.add(new int[]{x + 0, x + 3, x + 6});
		for (int y = 0; y < 3; y++) // Horizontal lines
			winningCombinations.add(new int[]{y * 3 + 0, y * 3 + 1, y * 3 + 2});
		winningCombinations.add(new int[]{0, 4, 8}); // Diagonal line 1
		winningCombinations.add(new int[]{2, 4, 6}); // Diagonal line 2
		
		for (int[] comb : winningCombinations)
			if (checkCombination(comb, player))
				return true;
		return false;
	}
	
	/**
	 * Checks whether a given array of 3 integers have the same player as owner.
	 * If this is the case, true is returned, otherwise, false is returned.
	 * @param comb the combination of numbers.
	 * @param player the player that is checked.
	 * @return true if the 3 integers in the given array have the same owner as
	 * the given player.
	 */
	private boolean checkCombination(int[] comb, Player player) {
		for (int i = 0; i < 3; i++)
			if (subgameOwners[comb[i]] != player.getId())
				return false;
		return true;
	}

	/**
	 * Get the winner of this specific subgame.
	 * @return the winner of this subgame.
	 */
	public Player getWinner() {
		return winner;
	}

	
	/**
	 * Check whether the move that is given is performed in a free field (a field
	 * that is not already claimed).
	 * @param move: A move that specifies a specific field in the subgame.
	 * @return true if the field is not yet claimed, false otherwise.
	 */
	public boolean isFreeField(Move move) {
		return this.getOwner(move.getSingleField()) == 0;
	}

	/**
	 * Set the id of the subgame.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get the id of this subgame.
	 * @return
	 */
	public int getId() {
		return id;
	}
}
