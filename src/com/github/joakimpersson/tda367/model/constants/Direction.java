package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Direction {

	NORTH(0, -1, "north", false),
	EAST(1, 0, "east", false),
	SOUTH(0, 1, "south", false),
	WEST(-1, 0, "west", false),
	NONE(0, 0, "none", false);

	private final int x;
	private final int y;
	private final String s;

	Direction(int x, int y, String s, boolean isDiagonal) {
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

	@Override
	public String toString() {
		return s;
	}
}
