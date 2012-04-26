package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.WalkableTile;

/**
 * 
 * @author joakimpersson
 * @modified Viktor Anderling
 * 
 */
public class Fire implements WalkableTile {
	private String image;
	private Direction direction;

	public Fire(Direction direction) {
		this.direction = direction;
		if (this.direction == null) {
			this.image = "fire-area";
		} else {
			this.image = "fire-column-" + direction;
		}
	}

	/**
	 * If a player enters a fire tile it should loose one hp. Since the tile's
	 * state is not changed it returns itself when the method is invoked
	 * 
	 * @param The
	 *            player who entered the tile
	 * @return Itself
	 */
	@Override
	public Tile playerEnter(Player player) {
		// TODO owner of the fire's bomb won't get points when a player enters
		// the fire.
		if (!player.isImmortal()) {
			player.playerHit();
		}
		return this;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public String toString() {
		return "Fire";
	}

	@Override
	public String getTileType() {
		return this.image;
	}
}
