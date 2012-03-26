package com.github.joakimpersson.tda367.core.tiles;

/**
 * 
 * @author joakimpersson
 * 
 */
public interface Tile {
	// TODO add documentation and add checks to see if the current thoughness is
	// enough or something like that
	public int getToughness();

	public boolean isWalkable();

	public Tile onFire(int damage);
}
