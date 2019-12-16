package edu.csci312.unca;

public class Move {

	// White = TRUE
	// Black = FALSE
	
	private int pos;
	private boolean color;
	private boolean pass;
	private int value;
	
	public Move(boolean color, int pos) {
		this.color = color;
		this.pos = pos;
		pass = false;
	}
	
	public Move(boolean color) {
		this.color = color;
		pass = true;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public int getPos() {
		return pos;
	}
	
	public boolean getPass() {
		return pass;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj == this) {
			return false;
		}
		
		Move otherMove = (Move) obj;
		
		if (otherMove.color == this.color 
				&& otherMove.pos == this.pos) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		String color;
		
		if (this.color) {
			color = "W";
		} else {
			color = "B";
		}
		
		if (pass) {
			return color;
		} else {
			String column = intToString(pos % 10);
			int row = pos / 10;
			
			return color + " " + column + row;
		}
	}
	
	private String intToString(int num) {
		if (num == 1) {
			return "a ";
		} else if (num == 2) {
			return "b ";
		} else if (num == 3) {
			return "c ";
		} else if (num == 4) {
			return "d ";
		} else if (num == 5) {
			return "e ";
		} else if (num == 6) {
			return "f ";
		} else if (num == 7) {
			return "g ";
		} else if (num == 8) {
			return "h ";
		}
		return "";
	}
	
}
