package com.github.joakimpersson.tda367.core.tiles;


public class Wall implements Tile {

	private int toughness;

	public Wall() {
		//Is not meant to be destroyable
		this.toughness = 100;
	}
	
	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile onFire() {
		return this;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

}
