package com.github.joakimpersson.tda367.model.player;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.github.joakimpersson.tda367.model.constants.PointGiver;

/**
 * @author Andreas Rolen
 * @modified joakimpersson
 * 
 */
public class PlayerPoints {

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

	// TODO rolen Not sure if this is needed...
	public ArrayList<Integer> getPointList() {
		ArrayList<Integer> tmp = new ArrayList<Integer>();
		tmp.add(this.totalScore);
		tmp.add(this.pointGivers.get(PointGiver.KillPlayer));
		tmp.add(this.pointGivers.get(PointGiver.PlayerHit));
		tmp.add(this.pointGivers.get(PointGiver.Box));
		tmp.add(this.pointGivers.get(PointGiver.Pillar));
		return tmp;
	}

	public void resetScore() {
		this.totalScore = 0;
	}
}
