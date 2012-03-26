package com.github.joakimpersson.tda367.core;

/**
 * 
 * @author joakimpersson
 * 
 */
public abstract class Bomb extends OpaqueTile {

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
	public Tile fireAction() {
		return null;
	}

	public abstract void explode();
}
