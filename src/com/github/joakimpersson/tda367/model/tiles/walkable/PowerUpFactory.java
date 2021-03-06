package com.github.joakimpersson.tda367.model.tiles.walkable;


/**
 * A factory for creating a PowerUpItem. It chooses randomly one of the three
 * available items
 * 
 * @author joakimpersson
 * 
 */
public class PowerUpFactory {

	/**
	 * The factory method chooses randomly between one of the three available
	 * items. SpeedUpdateItem, RangeUpdateItem, BombstackUpdateItem
	 * 
	 * @return A PowerUpItem tile that can be placed on the game field
	 */
	public PowerupItem createObject() {

		double rand = Math.random();
		double underLevel = 1 / 3.0;
		double middleLevel = 2 / 3.0;

		if (Double.compare(underLevel, rand) >= 0) {
			return new SpeedUpdateItem();
		} else if (Double.compare(rand, underLevel) >= 0
				&& Double.compare(middleLevel, rand) >= 0) {
			return new BombStackUpdateItem();
		} else {
			return new RangeUpdateItem();
		}
	}
}
