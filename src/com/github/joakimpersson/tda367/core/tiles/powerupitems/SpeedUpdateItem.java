package com.github.joakimpersson.tda367.core.tiles.powerupitems;

import com.github.joakimpersson.tda367.core.Attribute;

/**
 * 
 * @author joakimpersson
 *
 */
public class SpeedUpdateItem extends PowerupItem{
	
	public SpeedUpdateItem() {
		super();
	}

	@Override
	public Attribute getAttr() {
		return Attribute.Speed;
	}
	
}
