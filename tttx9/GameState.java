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
	private Subgame[] subgames = new Subgame[9];
	private Move lastMove;

	public GameState() {
		for (int i = 0; i < 9; i++) {
			subgames[i] = new Subgame();
			subgames[i].setId(i);
		}
	}

	/**
	 * SubmitMove receives a move and an playerId, and alters
	 * the gameState accordingly.
	 * @param move a move that alters the gameState.
	 * @param playerId the player that performs the move.
	 */
	public void submitMove(Move move, Player player) {
		int subgameMove = move.getSubGame();
		int singleFieldMove = move.getSingleField();
		if (subgames[subgameMove].getSubgameResult() == GameResult.UNFINISHED) { // Throw an error if the subgame is already won.
			if (subgameMove >= 0 && subgameMove < 9 && singleFieldMove >= 0 && singleFieldMove < 9) {// Check whether the move is legal
				if (isFreeField(move)) // And whether or not the field is free
					setOwner(subgameMove, new Coord(singleFieldMove % 3, singleFieldMove / 3), player);
				else
					throw new Error("Illegal move: field already in use (subgame " + subgameMove + " location: " + singleFieldMove + ")");
			}
			else
				throw new Error("Illegal move: field does not exists");
		}
		else
			throw new Error("Illegal move: subgame already won");
		this.lastMove = move;
	}

	/**
	 * Check whether it is possible to claim the specified field in the specified subgame.
	 * @param move: The move that is questioned to be legal
	 * @return: true if the move is possible, false otherwise.
	 */
	public boolean isLegalMove(Move move) {
		return isFreeField(move) && subgames[move.getSubGame()].getSubgameResult() == GameResult.UNFINISHED;
	}
	
	private boolean isFreeField(Move move) {
		return getOwner(move.getSubGame(), move.getSingleField()) == 0;
	}

	/**
	 * Get all subgames of the game. Subgames hold information
	 * about their fields and winners.
	 * @return an array of the 9 subgames in the game.
	 */
	public Subgame[] getSubgames() {
		return this.subgames;
	}

	/**
	 * A function that checks whether all subgames have ended.
	 * @return true if all subgames have ended, false
	 * otherwise.
	 */
	public boolean allSubGamesEnded() {
		for (Subgame subgame : subgames)
			if (subgame.getSubgameResult() == GameResult.UNFINISHED)
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
				return GameResult.WON;
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
			if (subgames[comb[i]].getWinner() != player)
				return false;
		return true;
	}

	/**
	 * Returns the owner of a field, given a subTTTgame and the
	 * coordinates of the field in that subgame.
	 * @param subgame the subTTTgame in the TTTx9Game
	 * @param coord the coordinate of the field
	 * @return the owner of the field
	 */
	public int getOwner(int subgame, Coord coord) {
		return subgames[subgame].getOwner(coord.getY() * 3 + coord.getX());
	}

	public int getOwner(int subgame, int field) {
		return subgames[subgame].getOwner(field);
	}

	public void setOwner(int subgame, Coord coord, Player player) {
		subgames[subgame].setOwner(coord.getY() * 3 + coord.getX(), player);
	}

	public void setOwner(int subgame, int field, Player player) {
		subgames[subgame].setOwner(field, player);
	}

	/**
	 * Get the last move that is performed. This the move the other player
	 * performed last.
	 * @return: the previous move performed in the game.
	 */
	public Move getLastMove() {
		return lastMove;
	}

	/**
	 * Get the subgame where the next move should be performed.
	 * @return an integer representation of the subgame where the next move should be performed.
	 */
	public Subgame getNextSubGame() {
		return subgames[lastMove.getSingleField()];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ------------------------- \n");
		for (int y = 0; y < 9; y++) {
			sb.append(" |");
			for (int ttt = y / 3 * 3; ttt < y / 3 * 3 + 3; ttt++) {
				for (int x = 0; x < 3; x++) {
					if (subgames[ttt].getWinner() == null) {
						if (!new Move(ttt, y % 3 * 3 + x).equals(lastMove))
							sb.append(" " + subgames[ttt].getOwner(y % 3 * 3 + x));
						else
							sb.append(">" + subgames[ttt].getOwner(y % 3 * 3 + x));
					}
					else
						sb.append(" " + subgames[ttt].getWinner().getId());
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
