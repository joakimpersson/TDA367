package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

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
