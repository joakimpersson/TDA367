package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Player;

public class Fire implements WalkableTile {

	private int toughness;

	public Fire() {
		// different players fire should not be able to cross each other
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
	public Tile playerEnter(Player player) {
		player.playerHit();
		return this;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

}
