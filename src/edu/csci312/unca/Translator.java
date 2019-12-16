package edu.csci312.unca;

public class Translator {

	// White = TRUE
	// Black = FALSE
	
	private Game game;
	
	public Translator(Game game) {
		this.game = game;
	}
	
	/**
	 * Initialize program to input color
	 * @param s input string
	 * @return color of program
	 */
	public boolean initialize(String s) {
		if (s.charAt(2) == 'W') {
			System.out.println("C I'm set to White");
			return true;
		} else {
			System.out.println("C I'm set to Black");
			return false;
		}
	}
	
	/**
	 * Determine what input means
	 * @param s string input
	 */
	public void decypherInput(String s) {
		if (s.charAt(0) == 'B') {
			makingMove(false, s);
		} else if (s.charAt(0) == 'W') {
			makingMove(true, s);
		} else if (s.charAt(0) == 'C') {

		} else {
			game.endGame();
		}
	}
	
	/**
	 * Make move from string input
	 * @param color of player making move
	 * @param s string input
	 */
	private void makingMove(boolean color, String s) {
		if (s.length() == 1) {
			Move m = new Move(color);
			game.makeMove(m);
		} else {
			int column = columnToInt(s.charAt(2));
			int row = Character.getNumericValue(s.charAt(4)) * 10;
			int pos = column + row;
			Move m = new Move(color, pos);
			game.makeMove(m);
		}
	}
	
	private int columnToInt(char s) {
		if (s == 'a') {
			return 1;
		} else if (s == 'b') {
			return 2;
		} else if (s == 'c') {
			return 3;
		} else if (s == 'd') {
			return 4;
		} else if (s == 'e') {
			return 5;
		} else if (s == 'f') {
			return 6;
		} else if (s == 'g') {
			return 7;
		} else if (s == 'h') {
			return 8;
		}
		return 0;
	}
	
}
