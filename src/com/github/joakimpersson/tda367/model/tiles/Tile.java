package com.github.joakimpersson.tda367.model.tiles;

/**
 * 
 * An interface for the game tiles used on the gamefield
 * 
 * @author joakimpersson
 * 
 */

public interface Tile {

	/**
	 * 
	 * Returns true or false based on the tile is walkable by a player or not
	 * 
	 * @return true if the tile is walkable and false if not
	 */
	public boolean isWalkable();

	/**
	 * Sets the type of the image to the Tile.
	 * 
	 * @return A representring string for the image.
	 */
	public String getTileType();
}
