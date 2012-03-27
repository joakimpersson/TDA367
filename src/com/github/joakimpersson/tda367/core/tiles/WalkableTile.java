package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Player;

public interface WalkableTile extends Tile {

	/**
	 * Determines what happen when a player enters the tile
	 * 
	 * @param player
	 *            The current player that enters the tile
	 * @return The tile that should replace the current after a player has
	 *         entered the tile. If an element is not picked up the object
	 *         returns itself
	 */
	public Tile playerEnter(Player player);
}
