package com.github.joakimpersson.tda367.model;

import java.io.Serializable;

import com.github.joakimpersson.tda367.model.player.PlayerPoints;

public class Score implements Serializable, Comparable<Score> {

	/**
	 * Auto generated serial
	 */
	private static final long serialVersionUID = -7241270636215740757L;
	private String playerName;
	private PlayerPoints playerPoints;

	public Score(String playerName, PlayerPoints pp) {
		this.playerName = playerName;
		this.playerPoints = pp;
	}

	public String getPlayerName() {
		return playerName;
	}

	public PlayerPoints getPlayerPoints() {
		return playerPoints;
	}

	@Override
	public int compareTo(Score other) {
		System.out.println(this.playerPoints.compareTo(other.playerPoints));
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
