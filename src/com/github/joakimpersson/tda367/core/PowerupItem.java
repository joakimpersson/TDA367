package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.core.PlayerAttribute.UpgradeType;

public abstract class PowerupItem extends WalkableTile {

	private int toughness;

	public PowerupItem() {
		this.toughness = 1;
	}

	@Override
	public void playerEnter(Player player) {
		player.upgradeAttr(this.getAttr(), UpgradeType.Round);
		// TODO somehow we must notify BM that it need to replace the current
		// tile with a floor tile
		// perhaps simply returning a tile?
		// solved
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	/**
	 * If a powerup item is within the fire range the powerup will be destroyed
	 * and replaced with an empty floor tile
	 * 
	 * @return A floor tile
	 */
	@Override
	public Tile fireAction() {
		return new Floor();
	}

	/**
	 * 
	 * @return A powerup attribute that will last the entire round
	 */
	public abstract Attribute getAttr();
}
