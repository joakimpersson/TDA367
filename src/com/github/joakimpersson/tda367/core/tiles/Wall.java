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
	public Tile onFire(int damage) {
		// TODO the question is if the all should be here?
		//or should we redefine the fireaction method
		//it has some flaws
		return this;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

}
