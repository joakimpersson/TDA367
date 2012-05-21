package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * A object rrepresenting a SpeedUpdateItem Tile.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class SpeedUpdateItem extends PowerupItem {

	private final String imageType;
	private final Attribute attribute;

	public SpeedUpdateItem() {
		super();
		this.imageType = "speedUpItem";
		this.attribute = Attribute.Speed;
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

		SpeedUpdateItem other = (SpeedUpdateItem) obj;
		return this.attribute.equals(other.attribute);
	}

	@Override
	public int hashCode() {
		int sum = 0;

		sum += attribute.hashCode() * 13;

		return sum;
	}
}
