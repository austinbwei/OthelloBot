package edu.csci312.unca;

import java.util.ArrayList;

public class Board {

	// White = TRUE
	// Black = FALSE
	
	private int[] tiles;
	private boolean color;
	private boolean gameOver;
	public static final int SELF = 1;
	public static final int OPPONENT = -1;
	public static final int BORDER = -2;
	public static final int EMPTY = 0;
	
	//Duplicate board
	public Board(Board board) {
		this.color = board.color;
		gameOver = board.getGameOver();
		tiles = new int[100];
		
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = board.getTile(i);
		}
	}
	
	// Default board constructor
	public Board(boolean color) {
		this.color = color;
		gameOver = false;
		tiles = new int[100];
		
		// Initialize as empty tiles
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = EMPTY;
		}
		
		// Border NORTH
		for (int i = 0; i < 10; i++) {
			tiles[i] = BORDER;
		}
		
		// Border SOUTH
		for (int i = 90; i < 100; i++) {
			tiles[i] = BORDER;
		}
		
		// Border WEST
		for (int i = 0; i <= 90; i+= 10) {
			tiles[i] = BORDER;
		}
		
		// Border EAST
		for (int i = 9; i < 100; i+= 10) {
			tiles[i] = BORDER;
		}
		
		// Initialize pieces
		if (color) {
			tiles[44] = SELF;
			tiles[45] = OPPONENT;
			tiles[54] = OPPONENT;
			tiles[55] = SELF;
		} else {
			tiles[44] = OPPONENT;
			tiles[45] = SELF;
			tiles[54] = SELF;
			tiles[55] = OPPONENT;
		}
	}
	
	public boolean getColor() {
		return color;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	/**
	 * Return piece at particular position
	 * @param pos in array
	 * @return number at position
	 */
	public int getTile(int pos) {
		return tiles[pos];
	}
	
	/**
	 * Get number of your pieces
	 * @param color of player
	 * @return number of pieces for self
	 */
	public int getMyPieces(boolean color) {
		int num = 0;
		if (this.color == color) {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == 1) {
					num++;
				}
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == -1) {
					num++;
				}
			}
		}
		return num;
	}
	
	/**
	 * Get number of opponent pieces
	 * @param color of self player
	 * @return number of pieces for opposing player
	 */
	public int getOppPieces(boolean color) {
		int num = 0;
		if (this.color == color) {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == -1) {
					num++;
				}
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == 1) {
					num++;
				}
			}
		}
		return num;
	}
	
	/**
	 * Determine if game is over
	 * @return if game is over
	 */
	public boolean isGameOver() {
		if (gameOver) {
			return true;
		}
		
		if (getMoves(true).get(0).getPass() && getMoves(false).get(0).getPass()) {
			gameOver = true;
			return true;
		} 
		
		gameOver = false;
		return false;
	}
	
	/**
	 * Setter for game over
	 * @param b boolean of game status
	 */
	public void setGameOver(boolean b) {
		gameOver = b;
	}
	
	/**
	 * Make list of possible moves a color can make
	 * @param color of team
	 * @return legal moves
	 */
	public ArrayList<Move> getMoves(boolean color)  {
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		for (int i = 0; i < tiles.length; i++) {
			if (willFlip(color, i)) {
				Move move = new Move(color, i);
				possibleMoves.add(move);
			}
		}
		
		// Pass
		if (possibleMoves.size() == 0) {
			possibleMoves.add(new Move(color));
		}
		
		return possibleMoves;
	}
	
	/**
	 * Apply move to the board
	 * @param m move to apply
	 */
	public void makeMove(Move m) {
		if (m.getPass()) {
			
		} else {
			if (willFlipNorth(m.getColor(), m.getPos())) {
				flipNorth(m.getColor(), m.getPos());
			} 
			if (willFlipSouth(m.getColor(), m.getPos())) {
				flipSouth(m.getColor(), m.getPos());
			} 
			if (willFlipEast(m.getColor(), m.getPos())) {
				flipEast(m.getColor(), m.getPos());
			} 
			if (willFlipWest(m.getColor(), m.getPos())) {
				flipWest(m.getColor(), m.getPos());
			} 
			if (willFlipNW(m.getColor(), m.getPos())) {
				flipNW(m.getColor(), m.getPos());
			} 
			if (willFlipNE(m.getColor(), m.getPos())) {
				flipNE(m.getColor(), m.getPos());
			} 
			if (willFlipSW(m.getColor(), m.getPos())) {
				flipSW(m.getColor(), m.getPos());
			} 
			if (willFlipSE(m.getColor(), m.getPos())) {
				flipSE(m.getColor(), m.getPos());
			}  
			
			if (m.getColor() == color) {
				tiles[m.getPos()] = 1;
			} else {
				tiles[m.getPos()] = -1;
			}
		}
	}
	
	/**
	 * Check if move will flip other pieces to be legal
	 * @param i position of proposed move
	 * @return if move will fip
	 */
	private boolean willFlip(boolean color, int i) {
		if (tiles[i] != 0) {
			return false;
		}

		if (willFlipNorth(color, i)) {
			return true;
		} else if (willFlipSouth(color, i)) {
			return true;
		} else if (willFlipEast(color, i)) {
			return true;
		} else if (willFlipWest(color, i)) {
			return true;
		}  else if (willFlipNW(color, i)) {
			return true;
		} else if (willFlipNE(color, i)) {
			return true;
		} else if (willFlipSW(color, i)) {
			return true;
		} else if (willFlipSE(color, i)) {
			return true;
		}  
		return false;
	}
	
	/**
	 * Check if move will flip north pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip north pieces
	 */
	private boolean willFlipNorth(boolean color, int i) {
		int buffer = 10;
		if (this.color == color) {
			if (tiles[i - 10] == -1) {
				while (tiles[i - buffer] == -1) {
					buffer += 10;
				}
				if (tiles[i - buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i - 10] == 1) {
				while (tiles[i - buffer] == 1) {
					buffer += 10;
				}
				if (tiles[i - buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip south pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip south pieces
	 */
	private boolean willFlipSouth(boolean color, int i) {
		int buffer = 10;
		if (this.color == color) {
			if (tiles[i + 10] == -1) {
				while (tiles[i + buffer] == -1) {
					buffer += 10;
				}
				if (tiles[i + buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i + 10] == 1) {
				while (tiles[i + buffer] == 1) {
					buffer += 10;
				}
				if (tiles[i + buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip east pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip east pieces
	 */
	private boolean willFlipEast(boolean color, int i) {
		int buffer = 1;
		if (this.color == color) {
			if (tiles[i + 1] == -1) {
				while (tiles[i + buffer] == -1) {
					buffer += 1;
				}
				if (tiles[i + buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i + 1] == 1) {
				while (tiles[i + buffer] == 1) {
					buffer += 1;
				}
				if (tiles[i + buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip west pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip west pieces
	 */
	private boolean willFlipWest(boolean color, int i) {
		int buffer = 1;
		if (this.color == color) {
			if (tiles[i - 1] == -1) {
				while (tiles[i - buffer] == -1) {
					buffer += 1;
				}
				if (tiles[i - buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i - 1] == 1) {
				while (tiles[i - buffer] == 1) {
					buffer += 1;
				}
				if (tiles[i - buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip nw pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip nw pieces
	 */
	private boolean willFlipNW(boolean color, int i) {
		int buffer = 11;
		if (this.color == color) {
			if (tiles[i - 11] == -1) {
				while (tiles[i - buffer] == -1) {
					buffer += 11;
				}
				if (tiles[i - buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i - 11] == 1) {
				while (tiles[i - buffer] == 1) {
					buffer += 11;
				}
				if (tiles[i - buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip ne pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip ne pieces
	 */
	private boolean willFlipNE(boolean color, int i) {
		int buffer = 9;
		if (this.color == color) {
			if (tiles[i - 9] == -1) {
				while (tiles[i - buffer] == -1) {
					buffer += 9;
				}
				if (tiles[i - buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i - 9] == 1) {
				while (tiles[i - buffer] == 1) {
					buffer += 9;
				}
				if (tiles[i - buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip sw pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip sw pieces
	 */
	private boolean willFlipSW(boolean color, int i) {
		int buffer = 9;
		if (this.color == color) {
			if (tiles[i + 9] == -1) {
				while (tiles[i + buffer] == -1) {
					buffer += 9;
				}
				if (tiles[i + buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i + 9] == 1) {
				while (tiles[i + buffer] == 1) {
					buffer += 9;
				}
				if (tiles[i + buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if move will flip se pieces
	 * @param color of player 
	 * @param i position of move
	 * @return if move will flip se pieces
	 */
	private boolean willFlipSE(boolean color, int i) {
		int buffer = 11;
		if (this.color == color) {
			if (tiles[i + 11] == -1) {
				while (tiles[i + buffer] == -1) {
					buffer += 11;
				}
				if (tiles[i + buffer] == 1) {
					return true;
				}
			}
		} else {
			if (tiles[i + 11] == 1) {
				while (tiles[i + buffer] == 1) {
					buffer += 11;
				}
				if (tiles[i + buffer] == -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Flip pieces north of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipNorth(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i - 10] != 1) {
				tiles[i - 10] = 1;
				flipNorth(color, i - 10);
			}
		} else {
			while (tiles[i - 10] != -1) {
				tiles[i - 10] = -1;
				flipNorth(color, i - 10);
			}
		}
	}
	
	/**
	 * Flip pieces south of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipSouth(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i + 10] != 1) {
				tiles[i + 10] = 1;
				flipSouth(color, i + 10);
			}
		} else {
			while (tiles[i + 10] != -1) {
				tiles[i + 10] = -1;
				flipSouth(color, i + 10);
			}
		}
	}
	
	/**
	 * Flip pieces east of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipEast(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i + 1] != 1) {
				tiles[i + 1] = 1;
				flipEast(color, i + 1);
			}
		} else {
			while (tiles[i + 1] != -1) {
				tiles[i + 1] = -1;
				flipEast(color, i + 1);
			}
		}
	}
	
	/**
	 * Flip pieces west of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipWest(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i - 1] != 1) {
				tiles[i - 1] = 1;
				flipWest(color, i - 1);
			}
		} else {
			while (tiles[i - 1] != -1) {
				tiles[i - 1] = -1;
				flipWest(color, i - 1);
			}
		}
	}
	
	/**
	 * Flip pieces NW of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipNW(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i - 11] != 1) {
				tiles[i - 11] = 1;
				flipNW(color, i - 11);
			}
		} else {
			while (tiles[i - 11] != -1) {
				tiles[i - 11] = -1;
				flipNW(color, i - 11);
			}
		}
	}
	
	/**
	 * Flip pieces NE of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipNE(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i - 9] != 1) {
				tiles[i - 9] = 1;
				flipNE(color, i - 9);
			}
		} else {
			while (tiles[i - 9] != -1) {
				tiles[i - 9] = -1;
				flipNE(color, i - 9);
			}
		}
	}
	
	/**
	 * Flip pieces SW of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipSW(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i + 9] != 1) {
				tiles[i + 9] = 1;
				flipSW(color, i + 9);
			}
		} else {
			while (tiles[i + 9] != -1) {
				tiles[i + 9] = -1;
				flipSW(color, i + 9);
			}
		}
	}
	
	/**
	 * Flip pieces SE of placement
	 * @param color of player making move
	 * @param i positon
	 */
	private void flipSE(boolean color, int i) {
		if (this.color == color) {
			while (tiles[i + 11] != 1) {
				tiles[i + 11] = 1;
				flipSE(color, i + 11);
			}
		} else {
			while (tiles[i + 11] != -1) {
				tiles[i + 11] = -1;
				flipSE(color, i + 11);
			}
		}
	}
	
	/**
	 * Get number of black pieces on board
	 * @return black pieces
	 */
	public int getBlackPieces() {
		int counter = 0;
		
		if (color) {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == -1) {
					counter++;
				}
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == 1) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	/**
	 * Get number of white pieces on board
	 * @return white pieces
	 */
	public int getWhitePieces() {
		int counter = 0;
		
		if (color) {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == 1) {
					counter++;
				}
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				if (tiles[i] == -1) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	/**
	 * Print the board
	 */
	public void printBoard() {
		int n = 1;
		System.out.print("C     a b c d e f g h");
		for (int i = 0; i < tiles.length; i++) {
			if (i % 10 == 0) {
				System.out.println("");
				if (i >= 10 && i <= 80) {
					System.out.print("C " + n + " ");
					n++;
				} else {
					System.out.print("C   ");
				}
				System.out.print(printTile(tiles[i]));
			} else {
				System.out.print(printTile(tiles[i]));
			}
		}
	}
	
	/**
	 * Format nums to represent an actual board
	 * @param i num
	 * @return appropriate string
	 */
	private String printTile(int i) {
		if (i == 0) {
			return "- ";
		} else if (i == -2) {
			return "* ";
		} else if (color) {
			if (i == 1) {
				return "W ";
			} else {
				return "B ";
			}	
		} else {
			if (i == 1) {
				return "B ";
			} else {
				return "W ";
			}
		}	
	}
	
}
