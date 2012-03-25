package com.github.joakimpersson.tda367.core;

public abstract class WalkableTile implements Tile {

	public WalkableTile() {
		;//TODO not implemented
	}
	
	
	@Override
	public boolean isWalkable() {
		return true;
	}

	/**
	 * 
	 * Determines what happen when a player enters the tile
	 * 
	 * @param player
	 *            The current player that enters the tile
	 */
	public abstract void playerEnter(Player player);
}
