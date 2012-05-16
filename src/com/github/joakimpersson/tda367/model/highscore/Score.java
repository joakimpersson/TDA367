package com.github.joakimpersson.tda367.model.highscore;

import java.io.Serializable;

import com.github.joakimpersson.tda367.model.player.PlayerPoints;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Score implements Serializable, Comparable<Score> {

	/**
	 * Auto generated serial
	 */
	private static final long serialVersionUID = -7241270636215740757L;
	private String playerName;
	private PlayerPoints playerPoints;

	/**
	 * Create a new score object using a player and his playerpoint object
	 * 
	 * @param playerName The name of the player
	 * @param pp The players playerpoint object
	 */
	public Score(String playerName, PlayerPoints pp) {
		this.playerName = playerName;
		this.playerPoints = pp;
	}

	/**
	 * Get the name of the player
	 * @return The player's name
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Get the players playerpoint object
	 * @return The player's playerpoint object
	 */
	public PlayerPoints getPlayerPoints() {
		return playerPoints;
	}

	@Override
	public int compareTo(Score other) {
		return this.playerPoints.compareTo(other.playerPoints);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Score other = (Score) obj;
		return this.playerName.equals(other.playerName)
				&& this.playerPoints.equals(other.playerPoints);
	}

	@Override
	public int hashCode() {
		int sum = 0;
		sum += playerName.hashCode() * 5;
		sum += playerPoints.hashCode() * 7;

		return sum;
	}
}
