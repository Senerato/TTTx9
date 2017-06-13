package tttx9;

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
	private TTTx9Game TTTGame = null;
	private int[][] state = new int[9][9];
	private GameResult gameResult = GameResult.UNFINISHED;
	private int winner = 0;

	/**
	 * Constructor for the GameState. Intializes a new Gamestate.
	 * @param ttTx9Game 
	 */
	public GameState(TTTx9Game TTTx9Game) {
		this.TTTGame = TTTx9Game;
		for (int[] subGame : state)
			for (int singleField : subGame)
				singleField = 0;
	}

	/**
	 * SubmitMove receives a move and an playerId, and alters
	 * the gameState accordingly.
	 * @param move a move that alters the gameState.
	 * @param playerId the player that performs the move.
	 */
	public void submitMove(Move move, int playerId) {
		int subGameMove = move.getSubGame();
		int singleFieldMove = move.getSingleField();
		if (subGameMove >= 0 && subGameMove < 9 && singleFieldMove >= 0 && singleFieldMove < 9) // Check whether the move is legal
			if (isFreeField(move)) // And whether or not the field is free
				executeMove(subGameMove, singleFieldMove, playerId);
			else
				throw new Error("Illegal move: field already in use");
		else
			throw new Error("Illegal move: field does not exists");
	}

	/**
	 * executeMove updates the gameState with the submitted move
	 * and checks if the game is finished.
	 * @param subGameMove
	 * @param singleFieldMove
	 * @param playerId
	 */
	private void executeMove(int subGameMove, int singleFieldMove, int playerId) {
		// Execute move:
		state[subGameMove][singleFieldMove] = playerId;
		// Check whether the game is finished:
		if (checkForWinner(subGameMove, playerId) == GameResult.VICTORY) {
			this.gameResult = GameResult.VICTORY;
			this.winner = playerId; // Is het handig als dit een int is?
		}
		if (allFieldsTaken())
			this.gameResult = GameResult.DRAW;
	}

	private GameResult checkForWinner(int pos, int playerId) {
		int x = pos % 3;
		int y = pos / 3;
		for (int i = 0; i < 3; i++) // Check the diagonal line
			if (state[i][i] == playerId)
				return GameResult.VICTORY;
		for (int i = 0; i < 3; i++) // Check the horizontal line
			if (state[x][i] == playerId)
				return GameResult.VICTORY;
		for (int i = 0; i < 3; i++) // Check the vertical line
			if (state[i][y] == playerId)
				return GameResult.VICTORY;
		return GameResult.UNFINISHED;
	}

	private boolean allFieldsTaken() {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (isFreeField(new Move(i, j)))
					return false;
		return true;
	}

	public boolean isFreeField(Move move) {
		return state[move.getSubGame()][move.getSingleField()] == 0;
	}
	
	public GameResult getGameResult() {
		return this.gameResult;
	}
	
	@Override
	public String toString() {
		return "Tostring van GameState moet nog gedaan worden";
	}

}
