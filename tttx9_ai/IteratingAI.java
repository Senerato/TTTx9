package tttx9_ai;

import tttx9.GameState;
import tttx9.Move;
import tttx9.Player;

/**
 * 
 * @author Senerato.
 * An AI that always claims the most left place on the highest 
 * possible row.
 */
public class IteratingAI extends Player {

	public IteratingAI(String name) {
		super(name);
	}

	@Override
	public Move nextTurn(GameState gs) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (gs.isFreeField(new Move(i, j)))
					return new Move(i, j);
		return null;
	}

}
