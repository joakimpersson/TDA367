package com.github.joakimpersson.tda367.model.player;

import java.io.Serializable;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.github.joakimpersson.tda367.model.constants.PointGiver;

/**
 * @author Andreas Rolen
 * @modified joakimpersson
 * 
 */
public class PlayerPoints implements Serializable, Comparable<PlayerPoints> {

	/**
	 * Auto generated serial UID
	 */
	private static final long serialVersionUID = -4730508024940842687L;

	private int totalScore;
	private int credits;
	private Map<PointGiver, Integer> pointGivers;

	public PlayerPoints() {
		this.totalScore = 0;
		this.credits = 0;
		this.pointGivers = new EnumMap<PointGiver, Integer>(PointGiver.class);
		initPointGiverMaps();
	}

	private void initPointGiverMaps() {
		int startValue = 0;
		for (PointGiver pg : PointGiver.values()) {
			this.pointGivers.put(pg, startValue);
		}
	}

	public void update(List<PointGiver> list) {

		for (PointGiver p : list) {
			this.totalScore += p.getScore();
			this.credits += p.getScore();
			int prevValue = pointGivers.get(p);
			pointGivers.put(p, prevValue + 1);
		}
	}

	/**
	 * Reducing the players credits.
	 * 
	 * @param cost
	 *            The cost for the item that is bought.
	 */
	public void useCredits(int cost) {
		this.credits = this.credits - cost;
	}

	/**
	 * Returns the players current score.
	 * 
	 * @return Integer
	 */
	public int getScore() {
		return this.totalScore;
	}

	/**
	 * Returns the players credit amount.
	 * 
	 * @return Integer
	 */
	public int getCredits() {
		return this.credits;
	}

	/**
	 * Returns the amount of certain destroyed PointGiver type by this player.
	 * 
	 * @param type
	 *            type of PointGiver tile
	 * @return The number of destroyed tile type in PointGiver
	 */
	public int getDestroyedPointGiver(PointGiver type) {
		return this.pointGivers.get(type);
	}

	/**
	 * Resets the players score and credits to zero
	 */
	public void reset() {
		this.totalScore = 0;
		this.credits = 0;
		pointGivers.clear();
		initPointGiverMaps();
	}

	@Override
	public int compareTo(PlayerPoints pp) {
		return this.totalScore - pp.getScore();
	}

}
