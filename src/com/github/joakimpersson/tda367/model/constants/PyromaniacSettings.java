package com.github.joakimpersson.tda367.model.constants;

/**
 * A class containing settings for the game logic class
 * 
 * @author joakimpersson
 * 
 */
public enum PyromaniacSettings {

	INSTANCE;

	/**
	 * Get how many matches a game is
	 * 
	 * @return
	 */
	public int getNumberOfMatches() {
		return 3;
	}

	/**
	 * Get how many rounds during one match
	 * 
	 * @return
	 */
	public int getNumberOfRounds() {
		return 3;
	}
	
	/**
	 * If bombs on fire will explode or not.
	 * 
	 * @return
	 */
	public boolean chainBombs() {
		return true;
	}
}
