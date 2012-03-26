package com.github.joakimpersson.tda367.core.tiles;

import com.github.joakimpersson.tda367.core.Parameters;
import com.github.joakimpersson.tda367.factory.PowerUpFactory;

public class Box implements Tile {

	private int toughness;

	public Box() {
		this.toughness = 1;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile onFire(int damage) {
		if (getRandomNbr()) {
			PowerUpFactory facctory = new PowerUpFactory();
			return facctory.createObject();
		} else {
			return new Floor();
		}
	}

	private boolean getRandomNbr() {
		double a = Math.random();
		double probability = Parameters.INSTANCE.getPowerUpProbabilityBox();

		return Double.compare(probability, a) >= 0;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

}
