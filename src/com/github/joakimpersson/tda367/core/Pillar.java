package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.factory.PowerUpFactory;

public class Pillar extends OpaqueTile {

	private int toughness;

	public Pillar() {
		this.toughness = 2;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile fireAction() {
		if (getRandomNbr()) {
			return PowerUpFactory.getInstance().createObject();
		} else {
			return new Floor();
		}
	}

	private boolean getRandomNbr() {
		double a = Math.random();
		double probability = Parameters.INSTANCE.getPowerUpProbabilityPillar();

		return Double.compare(probability, a) >= 0;
	}

}
