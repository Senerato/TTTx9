package tttx9;

import java.util.ArrayList;

/**
 * 
 * @author Senerato.
 * The state of the game. The state is represented in a double array,
 * where the inner arrays is a 9 long integer array representing a
 * single tic tac toe game. The values are encoded as follows:
 * 
 * 0: Empty field
 * 1: Field belonging to player 1.
 * 2: Field belonging to player 2.
 */
public class GameState {
	private SubGame[] subGames = new SubGame[9];
	private Move lastMove;

	public GameState() {
		for (int i = 0; i < 9; i++) {
			subGames[i] = new SubGame();
			subGames[i].setId(i);
		}
	}

	/**
	 * SubmitMove receives a move and an playerId, and alters
	 * the gameState accordingly.
	 * @param move a move that alters the gameState.
	 * @param playerId the player that performs the move.
	 */
	public void submitMove(Move move, Player player) {
		int subGameMove = move.getSubGame();
		int singleFieldMove = move.getSingleField();
		if (subGames[subGameMove].getWinner() == null) { // Throw an error if the subGame is already won.
			if (subGameMove >= 0 && subGameMove < 9 && singleFieldMove >= 0 && singleFieldMove < 9) {// Check whether the move is legal
				if (isFreeField(move)) // And whether or not the field is free
					setOwner(subGameMove, new Coord(singleFieldMove % 3, singleFieldMove / 3), player);
				else
					throw new Error("Illegal move: field already in use (subgame " + subGameMove + " location: " + singleFieldMove + ")");
			}
			else
				throw new Error("Illegal move: field does not exists");
		}
		else
			throw new Error("Illegal move: subGame already won");
		subGames[move.getSubGame()].checkForWinner(player);
		this.lastMove = move;
	}

	public boolean isLegalMove(Move move) {
		return isFreeField(move) && subGames[move.getSubGame()].getWinner() == null;
	}
	
	private boolean isFreeField(Move move) {
		return getOwner(move.getSubGame(), move.getSingleField()) == 0;
	}

	public SubGame[] getState() {
		return this.subGames;
	}

	/**
	 * A function that checks whether all fields in a GameState
	 * are taken.
	 * @return true if all fields in the game are taken, false
	 * otherwise.
	 */
	public boolean allFieldsTaken() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (isFreeField(new Move(i, j)))
					return false;
		return true;
	}

	public GameResult checkForWinner(Player player) {
		ArrayList<int[]> winningCombinations = new ArrayList<int[]>();
		for (int x = 0; x < 3; x++) // Vertical lines
			winningCombinations.add(new int[]{x + 0, x + 3, x + 6});
		for (int y = 0; y < 3; y++) // Horizontal lines
			winningCombinations.add(new int[]{y * 3 + 0, y * 3 + 1, y * 3 + 2});
		winningCombinations.add(new int[]{0, 4, 8}); // Diagonal line 1
		winningCombinations.add(new int[]{2, 4, 6}); // Diagonal line 2

		for (int[] comb : winningCombinations)
			if (checkCombination(comb, player))
				return GameResult.VICTORY;
		return GameResult.DRAW;
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
			if (subGames[comb[i]].getWinner() != player)
				return false;
		return true;
	}

	/**
	 * Returns the owner of a field, given a subTTTgame and the
	 * coordinates of the field in that subgame.
	 * @param subGame the subTTTgame in the TTTx9Game
	 * @param coord the coordinate of the field
	 * @return the owner of the field
	 */
	public int getOwner(int subGame, Coord coord) {
		return subGames[subGame].getOwner(coord.getY() * 3 + coord.getX());
	}

	public int getOwner(int subGame, int field) {
		return subGames[subGame].getOwner(field);
	}

	public void setOwner(int subGame, Coord coord, Player player) {
		subGames[subGame].setOwner(coord.getY() * 3 + coord.getX(), player);
	}

	public void setOWner(int subGame, int field, Player player) {
		subGames[subGame].setOwner(field, player);
	}

	public Move getLastMove() {
		return lastMove;
	}

	/**
	 * Get the subGame where the next move should be performed.
	 * @return an integer representation of the subGame where the next move should be performed.
	 */
	public SubGame getNextSubGame() {
		return subGames[lastMove.getSingleField()];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ------------------------- \n");
		for (int y = 0; y < 9; y++) {
			sb.append(" |");
			for (int ttt = y / 3 * 3; ttt < y / 3 * 3 + 3; ttt++) {
				for (int x = 0; x < 3; x++) {
					if (subGames[ttt].getWinner() == null) {
						if (!new Move(x, y).equals(lastMove))
							sb.append(" " + subGames[ttt].getOwner(y % 3 * 3 + x));
						else
							sb.append("-" + subGames[ttt].getOwner(y % 3 * 3 + x));
					}
					else
						sb.append(" " + subGames[ttt].getWinner().getId());
				}
				sb.append(" |");
			}
			if (y % 3 == 2)
				sb.append("\n -------------------------");
			sb.append("\n");
		}
		return sb.toString();
	}

}
