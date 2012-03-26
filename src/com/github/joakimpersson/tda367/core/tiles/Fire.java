package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Player;

public class Fire implements WalkableTile {

	private int toughness;

	public Fire() {
		this.toughness = 100;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile onFire(int damage) {
		// TODO not sure about what should happen here?
		// do nothing?
		return null;
	}

	@Override
	public void playerEnter(Player player) {
		player.playerHit();
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

}
