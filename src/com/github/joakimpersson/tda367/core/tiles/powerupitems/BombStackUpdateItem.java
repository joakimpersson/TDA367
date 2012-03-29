package com.github.joakimpersson.tda367.core.tiles.powerupitems;

import com.github.joakimpersson.tda367.core.Attribute;

/**
 * 
 * @author joakimpersson
 *
 */
public class BombStackUpdateItem extends PowerupItem{

	public BombStackUpdateItem() {
		super();
	}
	
	@Override
	public Attribute getAttr() {
		return Attribute.BombStack;
	}

}
