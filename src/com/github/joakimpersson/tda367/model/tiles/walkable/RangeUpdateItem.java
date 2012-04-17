package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * 
 * @author joakimpersson
 *
 */
public class RangeUpdateItem extends PowerupItem {	
	
	private String image;

	public RangeUpdateItem() {
		super();
		this.image = "rangeUpItem";
	}
	
	@Override
	public Attribute getAttr() {
		return Attribute.BombRange;
	}

	@Override
	public String getTileType() {
		return this.image;
	}

}
