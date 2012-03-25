package com.github.joakimpersson.tda367.core;

/**
 * 
 * @author joakimpersson
 * 
 */
public abstract class OpaqueTile implements Tile {
	public OpaqueTile() {

	}
	
	@Override
	public boolean isWalkable() {
		return false;
	}
}
