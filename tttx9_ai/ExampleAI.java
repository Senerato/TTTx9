package tttx9_ai;

import tttx9.GameResult;
import tttx9.GameState;
import tttx9.Move;
import tttx9.Player;
import tttx9.TTTx9Game;

public class ExampleAI implements Player {
	private String name;
	private int id;
	
	public ExampleAI(String name) {
		this.name = name;
	}

	@Override
	public Move nextTurn(GameState gs) {
		/**
		 * TODO: Put your code here. This function is called
		 * when the game asks for a field in a predetermined
		 * subgame, so you only have to specify a field in the
		 * specified subgame.
		 * 
		 * Hint: you can get the fixed subgame by calling
		 * gs.getNextSubGame().
		 * 
		 * Hint 2: you do not have to specify the subgame since it
		 * is fixed. new Move(0, yourField); will suffice.
		 */
		return new Move(0, 0);
	}
	
	@Override
	public Move nextFreeTurn(GameState gs) {
		/**
		 * TODO: Put your code here. This function is called
		 * when the game asks for a field and a subgame, so you 
		 * have to specify both the subgame and a field in that
		 * subgame.
		 */
		return new Move(0, 0);
	}
	
	/*
	 * Hint: do you not want these two functions and just return a
	 * move? Then edit the getMove() function at the bottom of this
	 * java file instead.
	 */
	
	
	/////////////////////////////////////////
	////////// Standard AI code: ////////////
	/// This should not have to be changed //
	/////////////////////////////////////////
	
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public Move getMove(TTTx9Game ttt, GameState gs) {
		Move nextMove;
		if (gs.getLastMove() == null || gs.getNextSubGame().getSubgameResult() != GameResult.UNFINISHED)
			nextMove = nextFreeTurn(gs);
		else
			nextMove = nextTurn(gs);
		return nextMove;
	}
}
