package tttx9;
/**
 * 
 * @author Senerato.
 * Player interface, een player is een object dat een gamestate ontvangt en een move teruggeeft.
 */
public class Player {
	String name;
	int id;
	
	public Player(String name) {
		this.name = name;
	}
	
	/**
	 * A player returns a new move given a GameState in which
	 * the player does not have a free choice about which subTTTgame
	 * will be used.
	 * @param gs the current gamestate
	 * @return a move with only a specific field in a subTTTgame (so without
	 * a subTTTgame).
	 */
	public Move nextTurn(GameState gs) {
		return null;
	}
	
	/**
	 * A player returns a new move given a GameState in which the
	 * player also has to choose a subTTTgame.
	 * @param gs the current gamestate
	 * @return a move with both a subTTTgame and a field in that subTTTgame
	 * specified.
	 */
	public Move nextFreeTurn(GameState gs) {
		return null;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
