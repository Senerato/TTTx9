package tttx9;

import java.util.ArrayList;

/**
 * 
 * @author Senerato.
 * Het model: ontvangt een player en managed de zetten.
 */
public class TTTx9Game {
	private View view;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Player hasTurn;
	private GameState gs;
	private Player winner;
	private GameResult gameResult = GameResult.UNFINISHED;

	/**
	 * Initiate a game with two players.
	 * @param p1 player 1.
	 * @param p2 player 2.
	 */
	public TTTx9Game(View view, Player p1, Player p2) {
		this.view = view;
		p1.setId(1);
		p2.setId(2);
		this.players.add(p1);
		this.players.add(p2);
		this.gs = new GameState();
		this.hasTurn = players.get((int) (Math.random() * 2)); // Specifies the player that currently
	}

	/**
	 * Play function. Runs an AI game, giving turns to both players
	 * while printing the game status.
	 */
	public void play() {
		
		while (gameResult == GameResult.UNFINISHED) {
			System.out.println(hasTurn + " performs turn");
			Move nextMove = hasTurn.getMove(this, gs); // Get a move from the current player.
			submitPlayerMove(nextMove);
		}
	}

	/**
	 * Checks if the game is still ongoing or if it has ended
	 * due to a player winning the game or because all possible
	 * moves are exhausted.
	 */
	private void checkGameStatus() {
		// Check whether the game is finished:
		if (gs.checkForWinner(hasTurn) == GameResult.WON) {
			this.gameResult = GameResult.WON;
			this.winner = hasTurn;
			System.out.println(winner + " won the game!");
		}
		if (gs.allFieldsTaken())
			this.gameResult = GameResult.DRAW;
	}

	public GameResult getGameResult() {
		return this.gameResult;
	}

	/**
	 * A function that should be called by a Player to submit the
	 * move the Player wants to perform.
	 */
	public void submitPlayerMove(Move nextMove) {
		if (gs.getLastMove() != null) // Set the allowed subgame. (In the first move, everything is allowed).
			if (gs.getNextSubGame().getSubgameResult() == GameResult.UNFINISHED) // If the subgame is unfinished, the move has to be the SingleField move of the previous move.
				nextMove.setSubGame(gs.getLastMove().getSingleField());
		gs.submitMove(nextMove, hasTurn);
		checkGameStatus();
		view.updateUi(this, gs); // TODO: incorrect MVC pattern.
		hasTurn = players.get(hasTurn.getId() % 2);
	}

	public Player getPlayerTurn() {
		return this.hasTurn;
	}

}
