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
	
	public Move nextTurn(GameState gs) {
		return null;
	}
	
	public Move firstTurn(GameState gs) {
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
