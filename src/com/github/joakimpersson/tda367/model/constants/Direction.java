package com.github.joakimpersson.tda367.model.constants;

public enum Direction {
	NORTH 		(0, -1, "north", false),
	NORTHEAST 	(0.75F, -0.75F, "north east", true),
	EAST 		(1, 0, "east", false), 
	SOUTHEAST 	(0.75F, 0.75F, "south east", true), 
	SOUTH 		(0, 1, "south", false), 
	SOUTHWEST 	(-0.75F, 0.75F, "south west", true), 
	WEST 		(-1, 0, "west", false), 
	NORTHWEST 	(-0.75F, -0.75F, "north west", true), 
	NONE 		(0, 0, "none", false);
	
	private final float x, y;
	private final String s;
	private final boolean isDiagonal;
	
	Direction(float x, float y, String s, boolean isDiagonal) {
		this.x = x;
		this.y = y;
		this.s = s;
		this.isDiagonal = isDiagonal;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public String toString() {
		return s;
	}
	
	public boolean isDiagonal() {
		return isDiagonal;
	}
}
