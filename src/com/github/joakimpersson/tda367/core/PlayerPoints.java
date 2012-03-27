package com.github.joakimpersson.tda367.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.joakimpersson.tda367.core.tiles.Tile;

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
	private HashMap<String, Integer> destroyedTiles;
	private int killedPlayers;
	
	public PlayerPoints() {
		this.totalScore = 0;
		this.credits = 0;
		this.killedPlayers = 0;
		this.destroyedTiles = new HashMap<String, Integer>();
		this.destroyedTiles.put("Pillar", 0);
		this.destroyedTiles.put("Box", 0);
	}
	
	public void update(List<PointGiver>list) {
		
		for(int i = 0; i < list.size(); i++) {
			this.totalScore += list.get(i).getScore();
			this.credits += list.get(i).getScore();
			
			if(list.get(i).getScore() == 30) {
				this.killedPlayers = this.killedPlayers + 1;
			} else if(list.get(i).getScore() == 20)	{
				this.destroyedTiles.put("Pillar", this.destroyedTiles.get("Pillar") + 1);
			} else if(list.get(i).getScore() == 5) {
				this.destroyedTiles.put("Box", this.destroyedTiles.get("Box") + 1);
			}	
		}
	}
	
	public void reduceCredits(int cost) {
		this.credits = this.credits - cost;
	}
	
	public int getScore() {
		return this.totalScore;
	}
	
	public int getCredits() {
		return this.credits;
	}
}
