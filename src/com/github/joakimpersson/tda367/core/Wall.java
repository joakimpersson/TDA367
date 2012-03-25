package com.github.joakimpersson.tda367.core;

public class Wall extends OpaqueTile {

	private int toughness;

	public Wall() {
		//TODO Is not meant to be destroyable
		this.toughness = 100;
	}
	
	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile fireAction() {
		// TODO the question is if the all should be here?
		//or should we redefine the fireaction method
		//it has some flaws
		return new Wall();
	}

}
