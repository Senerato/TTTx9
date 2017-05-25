package tttx9;
/**
 * 
 * @author Senerato.
 * Player interface, een player is een object dat een gamestate ontvangt en een move teruggeeft.
 */
public interface Player {
	abstract Move nextTurn(GameState gs);
}
