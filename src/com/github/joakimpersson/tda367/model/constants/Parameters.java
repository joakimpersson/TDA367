package com.github.joakimpersson.tda367.model.constants;

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

	/**
	 * This method returns the size of one step inside a tile, preferably
	 * smaller than 0.2. The step size affects how fast the game must be
	 * updated.
	 */
	public double getPlayerStepSize() {
		return 0.2;
	}

	public Dimension getMapSize() {
		return new Dimension(15, 13);
	}

	public float getInitSFXVolume() {
		return 1;
	}

	public float getInitBGMVolume() {
		return 1;
	}

	public int getHighscoreMaxSize() {
		return 20;
	}

}
