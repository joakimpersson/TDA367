package com.github.joakimpersson.tda367.model.tiles.walkable;

import java.util.Random;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.WalkableTile;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Floor implements WalkableTile {

	private int toughness;
	private String image;
	private int floorNumber;


	public Floor() {
		// should be ignored by the fire and skipped
		this.toughness = 0;
		Random rand = new Random();
		this.floorNumber = rand.nextInt(5) + 1;
		this.image = "floor" + this.floorNumber;
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
	public String getImage() {
		return this.image;
	}
}
