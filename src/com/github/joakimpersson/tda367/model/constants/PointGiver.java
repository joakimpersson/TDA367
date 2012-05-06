package com.github.joakimpersson.tda367.model.constants;

/**
 * 
 * @author joakimpersson
 * 
 */
public enum PointGiver {
	RoundWon(100), MatchWon(200), GameWon(500), Pillar(30), Box(10), PlayerHit(
			25), KillPlayer(50), PowerUpItem(20), Bomb(20), Floor(0);

	private int score;

	/**
	 * Create a new pointgiver
	 * 
	 * @param score
	 *            How much the pointgiver is worth
	 */
	PointGiver(int score) {
		this.score = score;
	}

	/**
	 * Get the pointgivers value
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

}
