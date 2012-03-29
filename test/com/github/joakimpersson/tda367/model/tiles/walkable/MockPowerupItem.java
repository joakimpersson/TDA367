package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.tiles.walkable.PowerupItem;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MockPowerupItem extends PowerupItem {
	//TODO remove!
	@Override
	public Attribute getAttr() {
		return Attribute.BombRange;
	}

}
