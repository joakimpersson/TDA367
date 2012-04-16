package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * 
 * @author joakimpersson
 *
 */
public class BombStackUpdateItem extends PowerupItem{

	private String image;

	public BombStackUpdateItem() {
		super();
		this.image = "bombUpItem";
	}
	
	@Override
	public Attribute getAttr() {
		return Attribute.BombStack;
	}

	@Override
	public String getImage() {
		return this.image;
	}

}
