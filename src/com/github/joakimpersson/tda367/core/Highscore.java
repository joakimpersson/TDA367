package com.github.joakimpersson.tda367.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Highscore {

	private ArrayList<Player> players;
	private File file = new File("HighScore.txt");

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

	public void saveList() {
		// TODO Save list with player name, score, players killed, players hit
		// and date in a .txt file.
		Writer output = null;
		StringBuilder scoreText;
		String tmpPlayerScore = "";
		
		for(int i = 0; i < this.players.size(); i++) {
			tmpPlayerScore = this.players.get(i).getName() + "_" + this.players.get(i).getScore() + "_" + this.players.get(i).??.getKilledPlayers() + "_" + this.players.get(i).??.getHitPlayers() + "_" + this.players.get(i).??.getDestroyedBoxes() + "_" + this.players.get(i).??.getDestroyedPillars();
			scoreText.append((i+1) + "_" + tmpPlayerScore + "\n");
		}
		output = new BufferedWriter(new FileWriter(file));
		//Clearing out the textfile
		output.write("");
		
		//Writing updated list
		output.write(scoreText.toString());
		output.close();
	}

	public void loadList() {
		// TODO Load the .txt list into the player list.
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
