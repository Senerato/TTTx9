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
	 * Initiate ga game with two players.
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
	 * Play function. Runs the game, giving turns to both players
	 * while printing the game status.
	 */
	public void play() {
		while(gameResult == GameResult.UNFINISHED) {
			System.out.println(hasTurn + " performs turn");
			performTurn();
			checkGameStatus();
			hasTurn = players.get(hasTurn.getId() % 2);
		}
		//printGameSummary();
	}

	/**
	 * Checks if the game is still ongoing or if it has ended
	 * due to a player winning the game or because all possible
	 * moves are exhausted.
	 */
	private void checkGameStatus() {	
			// Check whether the game is finished:
		if (gs.checkForWinner(hasTurn) == GameResult.VICTORY) {
			this.gameResult = GameResult.VICTORY;
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
	 * Performs a turn.
	 */
	private void performTurn() {
		Move nextMove = nextPlayerMove();
		gs.submitMove(nextMove, hasTurn);
		view.updateUi(this, gs);
	}

	/**
	 * Gives another player a turn and returns the turn that player
	 * performs.
	 * @return the move the next player performs
	 */
	private Move nextPlayerMove() {
		Move nextMove;
		if (hasTurn.getId() == 1)
			nextMove = players.get(0).nextTurn(gs);
		else
			nextMove = players.get(1).nextTurn(gs);
		if (gs.getLastMove() != null)
			nextMove.setSubGame(gs.getLastMove().getSingleField());
		return nextMove;
	}

	public Player getPlayerTurn() {
		return this.hasTurn;
	}
	
}
