package com.github.joakimpersson.tda367.core;

import java.awt.Dimension;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum Parameters {
	INSTANCE;

	public int getPlayerLives() {
		return 3;
	}

	public long getBombDetonationTime() {
		return 1000;
	}

	// For the player attr object perhaps extract to another class
	public int getStartingBombs() {
		return 1;
	}

	public int getInitHealth() {
		return 3;
	}

	public int getInitSpeed() {
		return 1;
	}

	public int getInitBombRange() {
		return 2;
	}

	public int getInitBombPower() {
		return 1;
	}

	public int getInitBombType() {
		return 0;
	}

	/*
	 * For the game field and when someone destroys a block&pillar
	 * I actually have no idea if this works like I want to ;)
	 */
	public double getPowerUpProbabilityBox() {
		return 0.4;
	}

	public double getPowerUpProbabilityPillar() {
		return 0.15;
	}
	
	public int getFireDuration() {
		return 1500;
	}
	
	public Dimension getDimensions() {
		// TODO implement this in GameField
		return new Dimension(15,13);
	}
}
