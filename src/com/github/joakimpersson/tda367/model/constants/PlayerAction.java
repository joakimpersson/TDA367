package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum PlayerAction {
	
	MOVE_NORTH(new Direction[] {Direction.NORTH}),
	MOVE_SOUTH(new Direction[] {Direction.SOUTH}),
	MOVE_WEST(new Direction[] {Direction.WEST}),
	MOVE_EAST(new Direction[] {Direction.EAST}),
	ACTION(new Direction[] {Direction.NONE}),
	MOVE_NORTHEAST(new Direction[] {Direction.NORTH, Direction.EAST}),
	MOVE_SOUTHEAST(new Direction[] {Direction.SOUTH, Direction.EAST}),
	MOVE_SOUTHWEST(new Direction[] {Direction.SOUTH, Direction.WEST}),
	MOVE_NORTHWEST(new Direction[] {Direction.NORTH, Direction.WEST});
	
	private final Direction[] direction;
	
	PlayerAction(Direction[] direction) {
		this.direction = direction;
	}
	
	public Direction[] getDirections() {
		return direction;
	}
}
