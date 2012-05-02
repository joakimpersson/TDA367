package com.github.joakimpersson.tda367.model.utils;

/**
 * Immutable class describing integer 2D-points.
 * 
 * @author joakimpersson
 * 
 */
public class Position {
	private final int x;
	private final int y;

	/**
	 * Creates an immutable instance of a 2D integer coordinate.
	 */
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return The x value of the coordinate.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return The x value of the coordinate.
	 */
	public int getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		return 13 * this.x + 7 * this.y;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Position other = (Position) obj;
		return this.x == other.x && this.y == other.y;
	}

	@Override
	public String toString() {
		return "[ + x: " + this.getX() + ", y: " + this.getY() + " ]";
	}
}
