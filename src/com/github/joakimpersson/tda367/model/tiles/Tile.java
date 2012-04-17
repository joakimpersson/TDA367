package com.github.joakimpersson.tda367.model.tiles;

/**
 * 
 * An interface for the game tiles used on the game field
 * 
 * @author joakimpersson
 * 
 */

public interface Tile {
	/**
	 * Returns the toughness for a specific game tile
	 * 
	 * @return a tile's toughness
	 */
	public int getToughness();

	/**
	 * 
	 * Returns true or false based on the tile is walkable by a player or not
	 * 
	 * @return true if the tile is walkable and false if not
	 */
	public boolean isWalkable();

	/**
	 * Determines what happens when a tile is hitted by fire. Returns itself if
	 * it cannot be destroyed
	 * 
	 * @return The tile that will replace the current tile.
	 */
	public Tile onFire();
	
	/**
	 * Sets the type of the image to the Tile.
	 * 
	 * @return A representring string for the image.
	 */
	public String getTileType();
}
