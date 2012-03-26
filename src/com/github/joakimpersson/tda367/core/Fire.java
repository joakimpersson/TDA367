package com.github.joakimpersson.tda367.core;

public class Fire extends WalkableTile {

	private int toughness;

	public Fire() {
		this.toughness = 100;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile fireAction() {
		// TODO not sure about what should happen here?
		// do nothing?
		return null;
	}

	@Override
	public void playerEnter(Player player) {
		player.playerHit();
	}

}
