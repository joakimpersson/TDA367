package com.github.joakimpersson.tda367.core;

/**
 * 
 * @author joakimpersson
 *
 */
public enum Parameters {
	INSTANCE;

	public int getPlayerHealth() {
		return 3;
	}

	public int getPlayerLifes() {
		return 3;
	}
	
	public int getNbrOfStartingBombs() {
		return 1;
	}
	
	public long getBombDetonationTime() {
		return 1000;
	}
}
