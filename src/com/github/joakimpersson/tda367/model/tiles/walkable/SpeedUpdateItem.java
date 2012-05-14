package com.github.joakimpersson.tda367.model.tiles.walkable;

import com.github.joakimpersson.tda367.model.constants.Attribute;

/**
 * 
 * @author joakimpersson
 * 
 */
public class SpeedUpdateItem extends PowerupItem {

	private String image;
	private Attribute attribute;

	public SpeedUpdateItem() {
		super();
		this.image = "speedUpItem";
		this.attribute = Attribute.Speed;
	}

	@Override
	public Attribute getAttr() {
		return attribute;
	}

	@Override
	public String getTileType() {
		return this.image;
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
