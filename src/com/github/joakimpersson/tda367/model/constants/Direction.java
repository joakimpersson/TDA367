package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Direction {

	NORTH(0, -1),
	EAST(1, 0),
	SOUTH(0, 1),
	WEST(-1, 0),
	NONE(0, 0);

	private final int x;
	private final int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean isHorizontal() {
		return this == EAST || this == WEST;
	}
	
	public boolean isVertical() {
		return this == NORTH || this == SOUTH;
	}

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
