package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Attribute {
	Speed("Speed", 100), Health("Health", 100), BombRange("Bomb range", 100), BombStack(
			"Bomb stack", 100), BombPower("Bomb power", 100), AreaBombs(
			"Area bombs", 100);

	private int cost;
	private String name;

	/**
	 * Create a new Attribute enum
	 * 
	 * @param name
	 *            The name of the attribute
	 * @param cost
	 *            The cost of the attribute
	 */
	Attribute(String name, int cost) {
		this.cost = cost;
		this.name = name;
	}

	/**
	 * Get the cost of the attribute
	 * 
	 * @return The attributes cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Get the name of the attribute
	 * 
	 * @return The name of the attribute
	 */
	public String getName() {
		return name;
	}
}
