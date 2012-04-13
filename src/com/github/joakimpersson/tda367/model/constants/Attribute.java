package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Attribute {
	Speed(1000), Health(1000), BombRange(1000), BombStack(1000), BombPower(1000), BombType(
			1000);

	private int cost;

	Attribute(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
}
