package com.github.joakimpersson.tda367.model.utils;

/**
 * Immutable class describing float 2D-points.
 * 
 * @author joakimpersson
 * 
 */
public class FPosition {
	private final float x;
	private final float y;

	/**
	 * Creates an immutable instance of a 2D integer coordinate.
	 */
	public FPosition(final float x, final float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return The x value of the coordinate.
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * @return The x value of the coordinate.
	 */
	public float getY() {
		return this.y;
	}

	@Override
	public int hashCode() {
		return 23456789 * (int)this.x + 56789123 * (int)this.y;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		FPosition other = (FPosition) obj;
		return this.x == other.x && this.y == other.y;
	}

	@Override
	public String toString() {
		return "[ + x: " + this.getX() + ", y: " + this.getY() + " ]";
	}
}
