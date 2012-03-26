package com.github.joakimpersson.tda367.core.powerupitems;

import com.github.joakimpersson.tda367.core.Attribute;

public class RangeUpdateItem extends PowerupItem {	

	public RangeUpdateItem() {
		super();
	}
	
	@Override
	public Attribute getAttr() {
		return Attribute.BombRange;
	}

}
