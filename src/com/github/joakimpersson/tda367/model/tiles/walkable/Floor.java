package com.github.joakimpersson.tda367.model.tiles.walkable;

import java.util.Random;

import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Destroyable;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.WalkableTile;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Floor implements WalkableTile, Destroyable {

	private int toughness;
	private String image;
	private int floorNumber;

	public Floor() {
		// should be ignored by the fire and skipped
		this.toughness = 0;
		Random rand = new Random();
		this.floorNumber = rand.nextInt(5) + 1;
		this.image = "floor";
	}

	@Override
	public int getToughness() {

		return toughness;
	}

	/**
	 * A floor is the simplest tile and is only meant to be walkable for the
	 * player without any other actions. Therefore it returns itself (this) when
	 * the method is called, since nothing has changed to the tile.
	 * 
	 * @return Itself
	 */
	@Override
	public Tile onFire() {

		return this;
	}

	/**
	 * A floor is the simplest tile and is only meant to be walkable for the
	 * player without any other actions. Therefore it returns itself (this) when
	 * the method is called, since nothing has changed to the tile
	 * 
	 * @return Itself
	 */
	@Override
	public Tile playerEnter(Player player) {
		return this;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	@Override
	public String toString() {
		return "Floor";
	}

	@Override
	public String getTileType() {
		return this.image + this.floorNumber;
	}

	@Override
	public PointGiver getPointGiver() {
		return PointGiver.Floor;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		return obj != null && getClass() == obj.getClass();

	}

	@Override
	public int hashCode() {
		int sum = 0;
		sum += image.hashCode() * 7;
		sum += toughness * 13;
		return sum;
	}
}
