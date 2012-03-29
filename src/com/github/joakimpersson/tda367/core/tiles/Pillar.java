package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Parameters;
import com.github.joakimpersson.tda367.core.tiles.powerupitems.PowerUpFactory;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Pillar implements Tile {

	private int toughness;

	public Pillar() {
		this.toughness = 2;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	/**
	 * When the pillar is destroyed either a floor or powerupitem is randomly
	 * generated and returned. The probability for the PowerUpItem can be found
	 * in the parameter class
	 * 
	 * @return Either a Floor or a PowerUpItem
	 */
	@Override
	public Tile onFire() {
		if (getRandomNbr()) {
			PowerUpFactory factory = new PowerUpFactory();
			return factory.createObject();
		} else {
			return new Floor();
		}
	}

	/**
	 * 
	 * Gets a pillar's probability from the parameters class. Based on its
	 * probability a boolean is generated randomly
	 * 
	 * @return Randomly chooses between true or false
	 */
	private boolean getRandomNbr() {
		double a = Math.random();
		double probability = Parameters.INSTANCE.getPowerUpProbabilityPillar();

		return Double.compare(probability, a) >= 0;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Pillar";
	}
	
}
