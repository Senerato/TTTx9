package tttx9_ai;

import tttx9.GameResult;
import tttx9.GameState;
import tttx9.Move;
import tttx9.Player;
import tttx9.TTTx9Game;
import tttx9.Util;

public class RandomAI implements Player {
	private String name;
	private int id;

	public RandomAI(String name) {
		this.name = name;
	}

	@Override
	public Move nextTurn(GameState gs) {
		Move move = new Move(gs.getNextSubGame().getId(), Util.randomFieldOrSubGameNumber());
		while(!gs.isLegalMove(move))
			move.setSingleField(Util.randomFieldOrSubGameNumber());
		return move;
	}

	@Override
	public Move nextFreeTurn(GameState gs) {
		Move move = new Move(Util.randomFieldOrSubGameNumber(), Util.randomFieldOrSubGameNumber());
		while(!gs.isLegalMove(move)) {
			move.setSubGame(Util.randomFieldOrSubGameNumber());
			move.setSingleField(Util.randomFieldOrSubGameNumber());
		}
		return move;
	}

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
