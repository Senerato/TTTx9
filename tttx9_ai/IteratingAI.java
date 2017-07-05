package tttx9_ai;

import tttx9.GameState;
import tttx9.Move;
import tttx9.Player;

/**
 * 
 * @author Senerato.
 * *Out of date* An AI that always claims the most left place on the highest 
 * possible row.
 */
public class IteratingAI extends Player {

	public IteratingAI(String name) {
		super(name);
	}

	@Override
	public Move nextTurn(GameState gs) {
		System.out.println("zoeken naar nieuwe move");
		if (gs.getLastMove() == null)
			return new Move(0, 0);
		for (int j = 0; j < 9; j++)
			if (gs.getNextSubGame().isFreeField(new Move(gs.getNextSubGame().getId(), j)))
				return new Move(gs.getNextSubGame().getId(), j);
		return null;
	}
	
	@Override
	public Move nextFreeTurn(GameState gs) {
		System.out.println("zoeken naar een nieuwe vrije move");
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				if (gs.getState()[i].isFreeField(new Move(i, j))){
					System.out.println("het word move: " + new Move(i, j));
					return new Move(i, j);}}}
		return null;
	}

}
