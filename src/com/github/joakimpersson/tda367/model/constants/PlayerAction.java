package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum PlayerAction {
	
	MOVE_NORTH(new Direction[] {Direction.NORTH}, false),
	MOVE_SOUTH(new Direction[] {Direction.SOUTH}, false),
	MOVE_WEST(new Direction[] {Direction.WEST}, false),
	MOVE_EAST(new Direction[] {Direction.EAST}, false),
	PRIMARY_ACTION(new Direction[] {Direction.NONE}, false),
	SECONDARY_ACTION(new Direction[] {Direction.NONE}, false),
	MOVE_NORTHEAST(new Direction[] {Direction.NORTH, Direction.EAST}, true),
	MOVE_SOUTHEAST(new Direction[] {Direction.SOUTH, Direction.EAST}, true),
	MOVE_SOUTHWEST(new Direction[] {Direction.SOUTH, Direction.WEST}, true),
	MOVE_NORTHWEST(new Direction[] {Direction.NORTH, Direction.WEST}, true),
	DO_NOTHING(new Direction[] {Direction.NONE}, false);
	
	private final Direction[] direction;
	private final boolean isDiagonal;
	
	PlayerAction(Direction[] direction, boolean isDiagonal) {
		this.direction = direction;
		this.isDiagonal = isDiagonal;
	}
	
	/**
	 * Gets the directions that the action contains.
	 * 
	 * @return The directions that the action contains.
	 */
	public Direction[] getDirections() {
		return direction;
	}
	
	/**
	 * Tells if the action is diagonal or not.
	 * If action doesn't contain any directions it will return false.
	 * 
	 * @return True if contains directions and is diagonal, false otherwise.
	 */
	public boolean isDiagonal() {
		return isDiagonal;
	}
}
