package edu.csci312.unca;

import java.util.Scanner;

public class Game {

	// White = TRUE
	// Black = FALSE
	// Black goes first
	
	private Scanner scan;
	private String input;
	private Board myBoard;
	private boolean myColor;
	private Translator translator = new Translator(this);
	private AIPlayer ai;
	
	/**
	 * Start program
	 * Initialize program's color and setup game
	 */
	public void start() {
		System.out.println("C Initialize my color");
		scan = new Scanner(System.in);
		input = scan.nextLine();
		
		createBoard(translator.initialize(input));
		playGame();
	}

	/**
	 * Create board for AI
	 * @param color of AI
	 */
	public void createBoard(boolean color) {
		myColor = color;
		myBoard = new Board(color);
		ai = new AIPlayer(color, myBoard);

		// Tell referee program is ready
		if (color) {
			System.out.println("R W");
		} else {
			System.out.println("R B");
		}
		
		myBoard.printBoard();
	}

	/**
	 * Play Othello game
	 */
	private void playGame() {
		if (myColor) {
			while(!myBoard.isGameOver()) {
				oppMove();
				
				if (myBoard.isGameOver()) {
					break;
				}
				
				myMove();
			}
			
			if (myBoard.isGameOver()) {
				endGame();
			}
		} else {
			while(!myBoard.isGameOver()) {
				myMove();
				
				if (myBoard.isGameOver()) {
					break;
				}
				
				oppMove();
			}
			
			if (myBoard.isGameOver()) {
				endGame();
			}
		}
	}
	
	/**
	 * AI make a move
	 */
	private void myMove() {
		ai.makeMove();
		myBoard.printBoard();
	}
	
	/**
	 * Ask for opponent move
	 */
	private void oppMove() {
		System.out.println("\nC Input move");
		input = scan.nextLine();
		translator.decypherInput(input);
		myBoard.printBoard();
	}
	
	/**
	 * Make input move on board
	 * @param m move to make
	 */
	public void makeMove(Move m) {
		myBoard.makeMove(m);
	}
	
	/**
	 * Print pieces on board
	 */
	public void endGame() {
		myBoard.setGameOver(true);
		int whitePieces = myBoard.getWhitePieces();
		int blackPieces = myBoard.getBlackPieces();
		
		if (blackPieces < whitePieces) {
			System.out.println("\nC White Wins!");
		} else if(blackPieces > whitePieces) {
			System.out.println("\nC Black Wins!");
		} else {
			System.out.println("\nC Tie!");
		}
		
		System.out.println(blackPieces);
		
		scan.close();
	}
	
}
