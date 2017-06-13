package tttx9;
/**
 * 
 * @author Senerato.
 * Het model: ontvangt een player en managed de zetten.
 */
public class TTTx9Game {
	private View view;
	private Player player1;
	private Player player2;
	
	private boolean gameFinished = false;
	private int currentTurn = (int) (Math.random() * 2 + 1); // Specifies the player that currently 
	//has the turn. 1 for player 1, 2 for player 2.
	private GameState gs;
	
	/**
	 * Initiate ga game with two players.
	 * @param p1 player 1.
	 * @param p2 player 2.
	 */
	public TTTx9Game(View view, Player p1, Player p2) {
		this.view = view;
		this.player1 = p1;
		this.player2 = p2;
		this.gs = new GameState(this);
	}
	
	/**
	 * Play function. Runs the game, giving turns to both players
	 * while printing the game status.
	 */
	public void play() {
		while(gs.getGameResult() == GameResult.UNFINISHED) {
			performTurn();
			checkGameStatus();
		}
		//printGameSummary();
	}

	/**
	 * Checks if the game is still ongoing or if it has ended
	 * due to a player winning the game or because all possible
	 * moves are exhausted.
	 */
	private void checkGameStatus() {
		
	}

	/**
	 * Performs a turn.
	 */
	private void performTurn() {
		Move nextMove = nextPlayerMove();
		gs.submitMove(nextPlayerMove(), currentTurn);
		view.updateUi(gs, nextMove);
		currentTurn = currentTurn % 2 + 1;
	}

	/**
	 * Gives another player a turn
	 * @return 
	 */
	private Move nextPlayerMove() {
		Move nextMove;
		if (currentTurn == 2)
			nextMove = player1.nextTurn(gs);
		else
			nextMove = player2.nextTurn(gs);
		return nextMove;
	}
	
}
