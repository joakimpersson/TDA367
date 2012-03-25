package com.github.joakimpersson.tda367.core;

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
		return 3;
	}
}
