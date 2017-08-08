package tttx9;

import tttx9_ai.IteratingAI;
import tttx9_ai.RandomAI;

/**
 * 
 * @author Senerato
 *	Main klasse.
 */
public class Main {

	public static void main(String[] args) {
		Player player = new IteratingAI("Iterating AI1");
		Player player1 = new RandomAI("Random AI1");
		Player player2 = new IteratingAI("Iterating AI2");
		//Player player2 = new HumanPlayer("Human player");
		for (int i = 0; i < 10000; i++) {
			TTTx9Game ttt = new TTTx9Game(player1, player2);
			ttt.play2ai(true);
		}
	}
}
