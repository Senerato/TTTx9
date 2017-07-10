package tttx9;

public class Util {

	/**
	 * Function that returns a random field or subgame number, in other words,
	 * a number ranging from 0 to 8.
	 * @return a random field or subgame number (an integer ranging from 0 to 8).
	 */
	public static int randomFieldOrSubGameNumber() {
		return (int) (Math.random() * 9);
	}
	
}
