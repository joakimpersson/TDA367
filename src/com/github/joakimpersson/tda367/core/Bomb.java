package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Tile;

/**
 * 
 * @author joakimpersson
 * 
 */
public abstract class Bomb implements Tile {

	private int toughness;
	private Player player;

	public Bomb(Player player) {
		this.player = player;
		this.toughness = 3;
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile onFire() {
		return new Floor();
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

	public abstract void explode();
}
