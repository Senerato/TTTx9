package tttx9_ai;

import tttx9.GameState;
import tttx9.Move;
import tttx9.Player;
import tttx9.Util;

public class RandomAI implements Player {
	String name;
	int id;

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
			System.out.println(move);
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

}
