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
}
