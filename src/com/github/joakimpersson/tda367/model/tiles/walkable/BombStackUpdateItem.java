package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

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
