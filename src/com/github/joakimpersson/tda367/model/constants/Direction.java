package com.github.joakimpersson.tda367.model.constants;

/**
 * An enum representing and handling the directions
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public enum Direction {

	NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0), NONE(0, 0);

	private final int X;
	private final int Y;

	/**
	 * Creating a direction from given value, a negative y gives a north
	 * direction etc.
	 * 
	 * @param x
	 *            The y-value deciding if the direction is west or east
	 * @param y
	 *            The x-value deciding if the direction is north or south
	 */
	Direction(int x, int y) {
		this.X = x;
		this.Y = y;
	}

	/**
	 * Get the x-value or in words the horizontal direction value
	 * 
	 * @return The x-value
	 */
	public int getX() {
		return X;
	}

	/**
	 * Get the y-value or in words the vertical direction value
	 * 
	 * @return The y-value
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Checks if the direction is horizontal
	 * 
	 * @return If the direction is horizontal
	 */
	public boolean isHorizontal() {
		return this.equals(EAST) || this.equals(WEST);
	}

	/**
	 * Checks if the direction is vertical
	 * 
	 * @return If the direction is vertical
	 */
	public boolean isVertical() {
		return this.equals(NORTH) || this.equals(SOUTH);
	}

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
}
