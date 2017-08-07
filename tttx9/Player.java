package tttx9;
/**
 * 
 * @author Senerato.
 * Player interface, een player is een object dat een gamestate ontvangt en een move teruggeeft.
 */
public interface Player {
	
	/**
	 * A player returns a new move given a GameState in which
	 * the player does not have a free choice about which subTTTgame
	 * will be used. You can specify a this by creating a new Move: new Move(0, yourFieldChoice);.
	 * @param gs the current gamestate
	 * @return a move with only a specific field in a subTTTgame (so without
	 * a subTTTgame).
	 */
	public Move nextTurn(GameState gs);
	
	/**
	 * A player returns a new move given a GameState in which the
	 * player also has to choose a subTTTgame. You can specify a this by creating a new Move: new Move(yourSubgameChoice, yourFieldChoice);.
	 * @param gs the current gamestate
	 * @return a move with both a subTTTgame and a field in that subTTTgame
	 * specified.
	 */
	public Move nextFreeTurn(GameState gs);
	
	public Move getMove(TTTx9Game ttt, GameState gs);
	
	public void setId(int id);
	
	public int getId();
	
	public String toString();
}
