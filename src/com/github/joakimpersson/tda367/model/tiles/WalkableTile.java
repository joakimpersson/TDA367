package com.github.joakimpersson.tda367.model.tiles;

import com.github.joakimpersson.tda367.model.player.Player;

/**
 * An interface for the game tiles used on the gamefield and is supposed to be
 * walkable for players
 * 
 * @author joakimpersson
 * 
 */
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
