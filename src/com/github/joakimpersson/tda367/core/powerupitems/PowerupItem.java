package com.github.joakimpersson.tda367.core.powerupitems;

import com.github.joakimpersson.tda367.core.Attribute;
import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Tile;
import com.github.joakimpersson.tda367.core.tiles.WalkableTile;

/**
 * 
 * @author joakimpersson
 * 
 */
public abstract class PowerupItem implements WalkableTile {

	private int toughness;

	public PowerupItem() {
		// should not slow down the fire
		this.toughness = 0;
	}

	@Override
	public Tile playerEnter(Player player) {
		player.upgradeAttr(this.getAttr(), UpgradeType.Round);
		return new Floor();
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
	public Tile onFire() {
		return new Floor();
	}

	@Override
	public boolean isWalkable() {
		return true;
	}

	/**
	 * 
	 * @return A powerup attribute that will last the entire round
	 */
	public abstract Attribute getAttr();
}
