package tttx9;

public class IteratingAI implements Player {

	@Override
	public Move nextTurn(GameState gs) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (gs.isFreeField(new Move(i, j)))
					return new Move(i, j);
		return null;
	}

}
