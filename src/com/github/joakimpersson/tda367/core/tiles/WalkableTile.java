package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Player;

public interface WalkableTile extends Tile {

	/**
	 * 
	 * Determines what happen when a player enters the tile
	 * 
	 * @param player
	 *            The current player that enters the tile
	 */
	public void playerEnter(Player player);
}
