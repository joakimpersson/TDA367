package com.github.joakimpersson.tda367.core;

public class Box extends OpaqueTile {

	private int toughness;

	public Box() {
		this.toughness = 1;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile fireAction() {
		if (getRandomNbr()) {
			return new SpeedUpdateItem();
		} else {
			return new Floor();
		}
	}

	private boolean getRandomNbr() {
		double a = Math.random();
		double probability = Parameters.INSTANCE.getPowerUpProbabilityBox();

		return Double.compare(probability, a) >= 0;
	}
}
