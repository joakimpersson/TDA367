package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Player;

public class Floor implements WalkableTile {

	private int toughness;

	// TODO rethink none of these methods are not needed

	public Floor() {
		this.toughness = 0;
	}

	@Override
	public int getToughness() {

		return toughness;
	}

	@Override
	public Tile onFire() {

		return null;
	}

	@Override
	public void playerEnter(Player player) {

	}

	@Override
	public boolean isWalkable() {
		return true;
	}

}
