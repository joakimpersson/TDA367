package com.github.joakimpersson.tda367.model.constants;

public enum Direction {
	Up (0, -1, "up"), 
	Down (0, 1, "down"), 
	Left (-1, 0, "left"), 
	Right (1, 0, "right"), 
	None (0, 0, "none");
	
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
