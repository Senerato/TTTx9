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
	 */
	public void updateUi(TTTx9Game newTTTx9Game, GameState newGameState) {
//		if (newTTTx9Game.getGameResult() == GameResult.UNFINISHED) {
//			System.out.println("It is now player " + newTTTx9Game.getPlayerTurn() + "'s turn.");
//		}
		System.out.println("\n" + newGameState.toString());
	}

}
