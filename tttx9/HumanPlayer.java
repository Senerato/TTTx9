package tttx9;

import java.util.Scanner;

/**
 * 
 * @author Senerato.
 *
 */
public class HumanPlayer implements Player {
	private String name;
	private int id;
	
	Scanner scanner = new Scanner(System.in);

	public HumanPlayer(String name) {
		this.name = name;
	}

	@Override
	public Move nextTurn(GameState gs) {
		Move m;
			System.out.println("Please give a location in TTT game number " + gs.getLastMove().getSingleField());
			System.out.println("Please give a number ranging from 0 to 8, where 0 is the most left number,"
					+ "\n1 is one place to the right and 8 is the most right lowest location.");
			int pos = scanner.nextInt();
			m = new Move(pos);
		return m;
	}

	@Override
	public Move nextFreeTurn(GameState gs) {
		Move m;
		System.out.println("Please give a subTTTgame in which you want to start:");
		int subgame = scanner.nextInt();
		System.out.println("Please give a number ranging from 0 to 8, where 0 is the most left number,"
				+ "\n1 is one place to the right and 8 is the most right lowest location.");
		int pos = scanner.nextInt();
		m = new Move(subgame, pos);
		return m;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public Move getMove(TTTx9Game ttt, GameState gs) {
		Move nextMove;
		if (gs.getLastMove() == null || gs.getNextSubGame().getWinner() != null)
			nextMove = nextFreeTurn(gs);
		else
			nextMove = nextTurn(gs);
		return nextMove;
	}

}
