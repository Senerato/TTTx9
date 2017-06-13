package tttx9;

/**
 * 
 * @author Senerato.
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(String name, int id) {
		super(name, id);
	}

	@Override
	public Move nextTurn(GameState gs) {
		
		Move m = new Move(0, 0);
		return null;
	}

}
