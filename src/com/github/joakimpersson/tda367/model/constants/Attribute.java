package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Attribute {
	Speed(100), Health(100), BombRange(100), BombStack(100), BombPower(100), BombType(
			100);

	private int cost;

	Attribute(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
}
