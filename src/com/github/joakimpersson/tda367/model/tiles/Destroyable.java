package com.github.joakimpersson.tda367.model.tiles;

import com.github.joakimpersson.tda367.model.constants.PointGiver;

/**
 * An interface for the game tiles used on the gamefield that are supposed to be
 * Destroyable by bombs
 * 
 * @author joakimpersson
 * 
 */
public interface Destroyable {
	/**
	 * Determines what happens when a tile is hitted by fire. Returns itself if
	 * it cannot be destroyed
	 * 
	 * @return The tile that will replace the current tile.
	 */
	public Tile onFire();

	/**
	 * Returns the toughness for a specific game tile
	 * 
	 * @return a tile's toughness
	 */
	public int getToughness();

	/**
	 * Returns the PointGiver Enum for the object containing the score the
	 * player get for destroying this tile
	 * 
	 * @return The tiles PointGiver Enum
	 */
	public PointGiver getPointGiver();
}
