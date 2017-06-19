package tttx9;

import java.util.ArrayList;

/**
 * 
 * @author Senerato.
 *
 */
public class SubGame {
	int[] subGameState = new int[9]; // All fields have an owner, 0 for no owner, 1 for player 1, 2 for player 2.
	Player winner = null; //The winner of this subGame.

	public SubGame() {
		for (int owner: subGameState)
			owner = 0;
	}

	public int[] getSubGameStates() {
		return subGameState;
	}

	/**
	 * Get the owner of a specific field.
	 * @param field the field whereoff the owner should be given
	 * @return the owner of the specified field
	 */
	public int getOwner(int field) {
		return subGameState[field];
	}

	/**
	 * Get the owner of a specific field by providing a Coord.
	 * @param coord the Coord of the field whereoff the owner 
	 * should be given
	 * @return the owner of the specified field
	 */
	public int getOwner(Coord coord) {
		return subGameState[coord.getY() / 3 * 3 + coord.getX()];
	}

	/**
	 * Set the owner of a specified field.
	 * @param field the field of which the owner should be changed.
	 * @param newOwner the player that is going to own the specified
	 * field.
	 */
	public void setOwner(int field, Player newOwner) {
		subGameState[field] = newOwner.getId();
	}

	/**
	 * Checks whether there is a winner in the subGame, if that is the
	 * case, the function returns true, false otherwise.
	 * @return true if there is a winner, false otherwise.
	 */
	public void checkForWinner(Player player) {
		ArrayList<int[]> winningCombinations = new ArrayList<int[]>();
		for (int x = 0; x < 3; x++) // Vertical lines
			winningCombinations.add(new int[]{x + 0, x + 3, x + 6});
		for (int y = 0; y < 3; y++) // Horizontal lines
			winningCombinations.add(new int[]{y * 3 + 0, y * 3 + 1, y * 3 + 2});
		winningCombinations.add(new int[]{0, 4, 8}); // Diagonal line 1
		winningCombinations.add(new int[]{2, 4, 6}); // Diagonal line 2
		
		for (int[] comb : winningCombinations)
			if (checkCombination(comb, player))
				this.winner = player;
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
			if (subGameState[comb[i]] != player.getId())
				return false;
		return true;
	}

	public Player getWinner() {
		return winner;
	}
}
