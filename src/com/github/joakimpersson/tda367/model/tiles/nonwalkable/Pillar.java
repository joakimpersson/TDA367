package com.github.joakimpersson.tda367.model.tiles.nonwalkable;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.tiles.Destroyable;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.tiles.walkable.PowerUpFactory;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Pillar implements Tile, Destroyable {

	private final int toughness;
	private final String imageString;

	public Pillar() {
		this.toughness = 2;
		this.imageString = "pillar";
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
		if (isContainingPowerUp()) {
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
	private boolean isContainingPowerUp() {
		double randomNumber = Math.random();
		double probability = Parameters.INSTANCE.getPowerUpProbabilityPillar();

		return Double.compare(probability, randomNumber) >= 0;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

	@Override
	public String getTileType() {
		return imageString;
	}

	@Override
	public PointGiver getPointGiver() {
		return PointGiver.Pillar;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) {
			return true;
		}
		return obj != null && getClass() == obj.getClass();

	}

	@Override
	public int hashCode() {
		int sum = 0;
		sum += imageString.hashCode() * 7;
		sum += toughness * 13;
		return sum;
	}
}
