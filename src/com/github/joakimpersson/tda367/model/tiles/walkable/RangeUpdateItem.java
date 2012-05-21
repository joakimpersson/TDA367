package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * An object representing a RangeUpdateItem Tile.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class RangeUpdateItem extends PowerupItem {

	private String imageType;
	private Attribute attribute;

	public RangeUpdateItem() {
		super();
		this.imageType = "rangeUpItem";
		this.attribute = Attribute.BombRange;
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

		RangeUpdateItem other = (RangeUpdateItem) obj;
		return this.attribute.equals(other.attribute);
	}

	@Override
	public int hashCode() {
		int sum = 0;

		sum += attribute.hashCode() * 13;

		return sum;
	}
}
