package com.github.joakimpersson.tda367.model.tiles.walkable;

import java.util.ArrayList;
import java.util.List;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.WalkableTile;

/**
 * An object representing a Fire Tile
 * 
 * @author joakimpersson
 * @modified Viktor Anderling, adderollen
 * 
 */
public class Fire implements WalkableTile {
	private final Direction direction;
	private String imageType;
	private final Player fireOwner;

	/**
	 * Creating a Fire tile.
	 * 
	 * @param fireOwner
	 *            The Player who created the Tile.
	 * @param direction
	 *            The Direction the fire will exploade at.
	 */
	public Fire(Player fireOwner, Direction direction) {
		this.fireOwner = fireOwner;
		this.direction = direction;
		setTileType();
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
		player.playerHit();
		distributePlayerPoints(player);
		return this;
	}

	/**
	 * Responsible for distributing the points the player that placed the fire
	 * deserves when another player walks into his fire
	 * 
	 * @param targetPlayer
	 *            The player that walked into the fire
	 */
	private void distributePlayerPoints(Player targetPlayer) {
		List<PointGiver> pointGiver = new ArrayList<PointGiver>();
		if (!targetPlayer.isImmortal() && targetPlayer.isAlive()) {
			targetPlayer.playerHit();
			if (!fireOwner.equals(targetPlayer)) {
				pointGiver.add(PointGiver.PlayerHit);
				if (!targetPlayer.isAlive()) {
					pointGiver.add(PointGiver.KillPlayer);
				}
				fireOwner.updatePlayerPoints(pointGiver);
			}
		}
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public String getTileType() {
		return imageType;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Fire other = (Fire) obj;
		return this.direction.equals(other.direction)
				&& this.fireOwner.equals(other.fireOwner);
	}

	@Override
	public int hashCode() {
		int sum = 0;

		sum += direction.hashCode() * 13;
		sum += fireOwner.hashCode() * 17;

		return sum;
	}

	/**
	 * Sets the tileType depending on what direction the fire got.
	 */
	private void setTileType() {
		if (direction == null) {
			imageType = "fire-area";
		} else if (direction == Direction.NONE) {
			imageType = "fire-mid";
		} else if (direction.isHorizontal()) {
			imageType = "fire-row";
		} else if (direction.isVertical()) {
			imageType = "fire-column";
		}
	}

}
