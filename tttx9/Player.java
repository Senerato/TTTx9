package tttx9;
/**
 * 
 * @author Senerato.
 * Player interface, een player is een object dat een gamestate ontvangt en een move teruggeeft.
 */
public class Player {
	String name;
	int id;
	
	public Player(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public Move nextTurn(GameState gs) {
		return null;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
