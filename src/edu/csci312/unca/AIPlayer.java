package edu.csci312.unca;

import java.util.ArrayList;

public class AIPlayer {

	private boolean myColor;
	private Board myBoard;
	private Board newBoard;
	
	public AIPlayer(boolean color, Board board) {
		myColor = color;
		myBoard = board;
	}
	
	public void makeMove() {
		ArrayList<Move> moves = myBoard.getMoves(myColor);
		
		if (moves.size() == 1) {
			if (myColor) {
				Move move = new Move(myColor);
				myBoard.makeMove(move);
			} 
		}
		
		Move myMove = alphaBeta(myBoard, 0, myColor, Double.MIN_VALUE, Double.MAX_VALUE, 10);
		myBoard.makeMove(myMove);
		System.out.println("\n" + myMove.toString());
	}
	
	private Move alphaBeta(Board board, int ply, boolean player, double alpha, double beta, int depth) {
		if (ply >= depth) {
            Move returnMove = new Move(myColor);
            returnMove.setValue(evaluate(newBoard, player));
            return returnMove;
		} else {
            ArrayList<Move> moves = board.getMoves(player);
            if (moves.isEmpty()) moves.add(new Move(player));
            Move bestMove = moves.get(0);
            for (Move move : moves) {
                newBoard = new Board(board);
                newBoard.makeMove(move);
                Move tempMove = alphaBeta(newBoard, ply + 1, !player, -beta, -alpha, depth);
                move.setValue(-tempMove.getValue());
                if (move.getValue() > alpha){
                    bestMove = move;
                    alpha = move.getValue();
                    if (alpha > beta)
                        return bestMove;
                }
            }
            return bestMove;
		}
	}
	
	private int evaluate(Board board, boolean player) {
		ArrayList<Move> moves = myBoard.getMoves(player);
		
		int pieceCount = board.getMyPieces(player);
		int moveOptions = moves.size() * 7;
		
		return pieceCount
				+ moveOptions
				+ cornerCount(board, player)
				- cornerRisk(board, player);
	}
	
	private int cornerCount(Board board, boolean player) {
		int cornerValue = 0;
		
		// Top left
		if (board.getTile(11) == 1) {
			cornerValue += 30;
		}
		
		// Top right
		if (board.getTile(18) == 1) {
			cornerValue += 30;
		}
		
		// Bottom left
		if (board.getTile(81) == 1) {
			cornerValue += 30;
		}
		
		// Bottom right
		if (board.getTile(88) == 1) {
			cornerValue += 30;
		}
		
		return cornerValue;
	}
	
	private int cornerRisk(Board board, boolean player) {
		int riskValue = 0;
		
		// Top left
		if (board.getTile(11) == 0 && (board.getTile(12) == 1 || board.getTile(21) == 1 || board.getTile(22) == 1)) {
			riskValue += 20;
		}
		
		// Top right
		if (board.getTile(18) == 0 && (board.getTile(17) == 1 || board.getTile(27) == 1 || board.getTile(28) == 1) ) {
			riskValue += 20;
		}
		
		// Bottom left
		if (board.getTile(81) == 0 && (board.getTile(71) == 1 || board.getTile(72) == 1 || board.getTile(82) == 1)) {
			riskValue += 20;
		}
		
		// Bottom right
		if (board.getTile(88) == 0 && (board.getTile(77) == 1 || board.getTile(78) == 1 || board.getTile(87) == 1)) {
			riskValue += 20;
		}
		
		return riskValue;
	}
	
}
