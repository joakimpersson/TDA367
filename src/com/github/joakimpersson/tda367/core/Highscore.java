package com.github.joakimpersson.tda367.core;

import java.util.ArrayList;

public class Highscore {

	private ArrayList<Player> players;

	public void update(ArrayList<Player> otherPlayers) {

		if (this.players.isEmpty()) {
			this.players.add(otherPlayers.get(0));
		}

		for (int i = 0; i < otherPlayers.size(); i++) {
			for (int j = 0; j < this.players.size(); j++) {
				if (otherPlayers.get(i).getScore() > this.players.get(j)
						.getScore()) {
					this.players.add(j, otherPlayers.get(i));
				} else if (j == this.players.size() - 1) {
					this.players.add(j, otherPlayers.get(i));
				}
			}
		}
	}
	
	public ArrayList<Player> getList() {
		return (ArrayList<Player>) this.players.clone();
	}
	
	public Player getPlayer(int position) {
		return this.players.get(position);
	}
	
	public int getSize() {
		return this.players.size();
	}
	
	public void clear() {
		this.players.clear();
	}

}
