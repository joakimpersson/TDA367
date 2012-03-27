package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Player;

public class Floor implements WalkableTile {

	private int toughness;

	public Floor() {
		// should be ignored by the fire and skipped
		this.toughness = 0;
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
		return this;
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

}
