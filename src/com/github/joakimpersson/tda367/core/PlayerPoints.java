package com.github.joakimpersson.tda367.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author joakimpersson
 *
 */
public class PlayerPoints {
	public enum PointGiver {
		Pillar(20), Box(5), PlayerHit(15), KillPlayer(30);

		private int score;

		PointGiver(int score) {
			this.score = score;
		}

		public int getScore() {
			return this.score;
		}

	}
	
	private int totalScore;
	private int credits;
	private List<Tile>destroyedTiles;
	private int killedPlayers;
	
	public PlayerPoints() {
		this.totalScore = 0;
		this.credits = 0;
		this.killedPlayers = 0;
		this.destroyedTiles = new ArrayList<Tile>();
	}
	
	public void update(List<PointGiver>list) {
		
	}
	
	public void reduceCredits(int cost) {
		
	}
	
	public int getScore() {
		return this.totalScore;
	}
	
	public int getCredits() {
		return this.credits;
	}
	
	public void reset() {
		
	}
}
