package com.github.joakimpersson.tda367.core;

public class Floor extends WalkableTile {

	private int toughness;

	// TODO rethink none of these methods are not needed

	public Floor() {
		this.toughness = 100;
	}

	@Override
	public int getToughness() {

		return toughness;
	}

	@Override
	public Tile fireAction() {

		return null;
	}

	@Override
	public void playerEnter(Player player) {

	}

}
