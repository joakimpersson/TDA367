package com.github.joakimpersson.tda367.model.constants;

public enum Direction {
	Up (0, -1, "Up"), 
	Down (0, 1, "Down"), 
	Left (-1, 0, "Left"), 
	Right (1, 0, "Right"), 
	None (0, 0, "None");
	
	private final int x, y;
	private final String s;
	
	Direction(int x, int y, String s) {
		this.x = x;
		this.y = y;
		this.s = s;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return s;
	}
}
