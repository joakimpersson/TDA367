package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * 
 * @author joakimpersson
 *
 */
public class SpeedUpdateItem extends PowerupItem{
	
	private String image;
	
	public SpeedUpdateItem() {
		super();
		this.image = "speedUpItem";
	}

	@Override
	public Attribute getAttr() {
		return Attribute.Speed;
	}

	@Override
	public String getTileType() {
		return this.image;
	}
	
}
