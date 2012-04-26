package com.github.joakimpersson.tda367.model.constants;

public enum Direction {
	NORTH 		(0, -1, "north"),
	NORTHEAST 	(0.75F, -0.75F, "north east"),
	EAST 		(1, 0, "east"), 
	SOUTHEAST 	(0.75F, 0.75F, "south east"), 
	SOUTH 		(0, 1, "south"), 
	SOUTHWEST 	(-0.75F, 0.75F, "south west"), 
	WEST 		(-1, 0, "west"), 
	NORTHWEST 	(-0.75F, -0.75F, "north west"), 
	NONE 		(0, 0, "none");
	
	private final float x, y;
	private final String s;
	
	Direction(float x, float y, String s) {
		this.x = x;
		this.y = y;
		this.s = s;
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
}
