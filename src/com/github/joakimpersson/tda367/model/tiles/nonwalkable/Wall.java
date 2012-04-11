package com.github.joakimpersson.tda367.model.tiles.nonwalkable;

import com.github.joakimpersson.tda367.model.tiles.Tile;


/**
 * 
 * @author joakimpersson
 * 
 */
public class Wall implements Tile {

	private int toughness;

	public Wall() {
		// Is not meant to be destroyable
		this.toughness = 100;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	/**
	 * Since a wall is not destroyable it will return itself
	 * @return Itself
	 */
	@Override
	public Tile onFire() {
		return this;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Wall";
	}

}