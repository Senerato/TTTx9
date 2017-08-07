package tttx9_ai;

import tttx9.GameResult;
import tttx9.GameState;
import tttx9.Move;
import tttx9.Player;
import tttx9.TTTx9Game;

/**
 * 
 * @author Senerato.
 * *Out of date* An AI that always claims the most left place on the highest 
 * possible row.
 */
public class IteratingAI implements Player {
	private String name;
	private int id;
	
	public IteratingAI(String name) {
		this.name = name;
	}

	@Override
	public Move nextTurn(GameState gs) {
		if (gs.getLastMove() == null)
			return new Move(0, 0);
		for (int j = 0; j < 9; j++)
			if (gs.getNextSubGame().isFreeField(new Move(gs.getNextSubGame().getId(), j)))
				return new Move(gs.getNextSubGame().getId(), j);
		return null;
	}
	
	@Override
	public Move nextFreeTurn(GameState gs) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (gs.isLegalMove(new Move(i, j)))
					return new Move(i, j);
		return null;
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
