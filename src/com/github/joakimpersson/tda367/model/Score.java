package com.github.joakimpersson.tda367.model;

import java.io.Serializable;

import com.github.joakimpersson.tda367.model.player.PlayerPoints;

public class Score implements Serializable, Comparable<Score> {

	/**
	 * Auto generated serial
	 */
	private static final long serialVersionUID = -7241270636215740757L;
	private String playerName;
	private PlayerPoints pp;

	public Score(String playerName, PlayerPoints pp) {
		this.playerName = playerName;
		this.pp = pp;
	}

	public String getPlayerName() {
		return playerName;
	}

	public PlayerPoints getPlayerPoints() {
		return pp;
	}

	@Override
	public int compareTo(Score other) {
		return pp.compareTo(other.pp);
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
				&& this.pp.equals(other.pp);
	}

	@Override
	public int hashCode() {
		int sum = 0;
		sum += playerName.hashCode() * 5;
		sum += pp.hashCode() * 7;

		return sum;
	}
}
