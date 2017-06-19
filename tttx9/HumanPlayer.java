package tttx9;

import java.util.Scanner;

/**
 * 
 * @author Senerato.
 *
 */
public class HumanPlayer extends Player {

	Scanner scanner = new Scanner(System.in);

	public HumanPlayer(String name) {
		super(name);
	}

	@Override
	public Move nextTurn(GameState gs) {
		Move m;
		if (gs.getLastMove() != null) {
			System.out.println("Please give a location in TTT game number " + gs.getLastMove().getSingleField());
			System.out.println("Please give a number ranging from 0 to 8, where 0 is the most left number,"
					+ "\n1 is one place to the right and 8 is the most right lowest location.");
			int pos = scanner.nextInt();
			m = new Move(pos);
		}
		else {
			System.out.println("Please give a subTTTgame in which you want to start:");
			int subGame = scanner.nextInt();
			System.out.println("Please give a number ranging from 0 to 8, where 0 is the most left number,"
					+ "\n1 is one place to the right and 8 is the most right lowest location.");
			int pos = scanner.nextInt();
			m = new Move(subGame, pos);
		}
		return m;
	}

}
