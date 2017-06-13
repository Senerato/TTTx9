package tttx9;

import tttx9_ai.IteratingAI;

/**
 * 
 * @author Senerato
 *	Main klasse.
 */
public class Main {

	public static void main(String[] args) {
		View view = new View();
		Player player = new IteratingAI();
		Player player2 = new IteratingAI();
		TTTx9Game ttt = new TTTx9Game(view, player, player2);
		Controller controller = new Controller(ttt, view);
	}

}
