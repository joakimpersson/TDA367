package com.github.joakimpersson.tda367.core;

/**
 * 
 * @author joakimpersson
 *
 */
public interface Tile {
	public int getToughness();
	public boolean isWalkable();
	public Tile fireAction();
}
