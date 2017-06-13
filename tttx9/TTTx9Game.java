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
	
	private boolean gameFinished = false;
	private Player hasTurn;
	private GameState gs;
	
	/**
	 * Initiate ga game with two players.
	 * @param p1 player 1.
	 * @param p2 player 2.
	 */
	public TTTx9Game(View view, Player p1, Player p2) {
		this.view = view;
		this.players.add(p1);
		this.players.add(p2);
		this.gs = new GameState(this);
		this.hasTurn = players.get((int) (Math.random() * 2)); // Specifies the player that currently
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
		gs.submitMove(nextPlayerMove(), hasTurn);
		view.updateUi(this, gs, nextMove);
		hasTurn = players.get(hasTurn.getId() % 2);
	}

	/**
	 * Gives another player a turn
	 * @return 
	 */
	private Move nextPlayerMove() {
		Move nextMove;
		if (hasTurn.getId() == 2)
			nextMove = players.get(0).nextTurn(gs);
		else
			nextMove = players.get(1).nextTurn(gs);
		return nextMove;
	}

	public Player getPlayerTurn() {
		return this.hasTurn;
	}
	
}
