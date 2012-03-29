package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * 
 * @author joakimpersson
 *
 */
public class RangeUpdateItem extends PowerupItem {	

	public RangeUpdateItem() {
		super();
	}
	
	@Override
	public Attribute getAttr() {
		return Attribute.BombRange;
	}

}
