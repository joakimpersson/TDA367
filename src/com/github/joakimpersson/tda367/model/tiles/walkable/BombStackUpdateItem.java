package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * An object representing a BombStackUpdateItem Tile.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class BombStackUpdateItem extends PowerupItem {

	private final String imageType;
	private final Attribute attribute;

	/**
	 * Creating a BombStackUpdateItem Tile.
	 */
	public BombStackUpdateItem() {
		super();
		this.imageType = "bombUpItem";
		attribute = Attribute.BombStack;
	}

	@Override
	public Attribute getAttr() {
		return attribute;
	}

	@Override
	public String getTileType() {
		return imageType;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		BombStackUpdateItem other = (BombStackUpdateItem) obj;
		return this.attribute.equals(other.attribute);
	}

	@Override
	public int hashCode() {
		int sum = 0;

		sum += attribute.hashCode() * 13;

		return sum;
	}
}
