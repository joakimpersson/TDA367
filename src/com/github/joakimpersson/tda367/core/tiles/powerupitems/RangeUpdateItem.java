package com.github.joakimpersson.tda367.core.tiles.powerupitems;

import com.github.joakimpersson.tda367.core.Attribute;

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
