package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Attribute {
	Speed(100, "Speed"), 
	Health(100, "Health"), 
	BombRange(100, "Bomb range"), 
	BombStack(100, "Bomb stack"), 
	BombPower(100, "Bomb power"), 
	BombType(100, "Bomb type");

	private int cost;
	private String name;

	Attribute(int cost, String name) {
		this.cost = cost;
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
}
