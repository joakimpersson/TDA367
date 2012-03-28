package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Parameters;
import com.github.joakimpersson.tda367.factory.PowerUpFactory;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Box implements Tile {

	private int toughness;

	public Box() {
		this.toughness = 1;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	/**
	 * When the box is destroyed either a floor or powerupitem is randomly
	 * generated and returned. The probability for the PowerUpItem can be found
	 * in the parameter class
	 * 
	 * @return Either a Floor or a PowerUpItem
	 */
	@Override
	public Tile onFire() {
		if (getRandomNbr()) {
			PowerUpFactory facctory = new PowerUpFactory();
			return facctory.createObject();
		} else {
			return new Floor();
		}
	}

	/**
	 * 
	 * Get's a box's probability from the parameters class. Based on its
	 * probability a boolean is generated randomly
	 * 
	 * @return Randomly chooses between true or false
	 */
	private boolean getRandomNbr() {
		double a = Math.random();
		double probability = Parameters.INSTANCE.getPowerUpProbabilityBox();

		return Double.compare(probability, a) >= 0;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Box";
	}
}
