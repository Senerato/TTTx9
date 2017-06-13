package tttx9;

/**
 * 
 * @author Senerato.
 *
 */
public class View {

	/**
	 * Function to update the UI. Gets a new gameState and a new
	 * Move to show.
	 * @param newGameState
	 * @param newMove
	 */
	public void updateUi(GameState newGameState, Move newMove) {
		newGameState.toString();
		if (newGameState.getGameResult() == GameResult.UNFINISHED) {
			System.out.println("It is now player " + newGameState.g);
		}
	}

}
