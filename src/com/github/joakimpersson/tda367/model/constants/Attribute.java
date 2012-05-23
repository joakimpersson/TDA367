package com.github.joakimpersson.tda367.model.constants;

/**
 * A class with the constants for a players Attribute.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public enum Attribute {
	Speed("Speed", 400, 15), Health("Health", 600, 15), BombRange("Bomb range",
			200, 15), BombStack("Bomb stack", 300, 5), BombPower("Bomb power",
			1500, 3), AreaBombs("Area bombs", 1000, 3);

	private int cost;
	private String name;
	private int maxPurchasableAmount;

	/**
	 * Create a new Attribute enum
	 * 
	 * @param name
	 *            The name of the attribute
	 * @param cost
	 *            The cost of the attribute
	 * @param maxPurchasableAmount
	 *            The maximum purchasable amount
	 */
	Attribute(String name, int cost, int maxPurchasableAmount) {
		this.name = name;
		this.cost = cost;
		this.maxPurchasableAmount = maxPurchasableAmount;
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

	/**
	 * Get the max purchasable amount of the attribute
	 * 
	 * @return The max purchasable amount of the attribute
	 */
	public int getMaxPurchasableAmount() {
		return maxPurchasableAmount;
	}
}
