package com.github.joakimpersson.tda367.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Highscore {

	private ArrayList<Player> players;
	private File file = new File("HighScore.txt");
	private HashMap<String, ArrayList<Integer>> playerList;

	public void update(ArrayList<Player> otherPlayers) {

		if (this.players.isEmpty()) {
			this.players.add(otherPlayers.get(0));
		}

		// Sorting all players
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

		// Adding to the list
		for (int i = 0; i < this.players.size(); i++) {
			this.playerList.put(this.players.get(i).getName(), this.players
					.get(i).getPlayerPoints().getPointList());
		}
		
		this.saveList();
	}

	public void saveList() {
		Writer output = null;
		StringBuilder scoreText;
		String tmpPlayerScore = "";
		// Creating a list were each row represent a players score and details
		for (int i = 0; i < this.players.size(); i++) {
			// TODO Better to use the getPointList() method?
			tmpPlayerScore = this.players.get(i).getName()
					+ "_"
					+ this.players.get(i).getScore()
					+ "_"
					+ this.players.get(i).getPlayerPoints().getKilledPlayers()
					+ "_"
					+ this.players.get(i).getPlayerPoints().getHitPlayers()
					+ "_"
					+ this.players.get(i).getPlayerPoints().getDestroyedBoxes()
					+ "_"
					+ this.players.get(i).getPlayerPoints()
							.getDestroyedPillars();
			scoreText.append(tmpPlayerScore + "\n");
		}
		output = new BufferedWriter(new FileWriter(file));
		// Clearing out the textfile
		output.write("");

		// Writing updated list
		output.write(scoreText.toString());
		output.close();
	}

	public void loadList() {
		String tmpPlayerPointsLine;
		String tmpPlayerName = null;
		ArrayList<Integer> tmpPlayerPointsList = new ArrayList<Integer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while ((tmpPlayerPointsLine = br.readLine()) != null) {
				char[] tmpChar = tmpPlayerPointsLine.toCharArray();
				// Saving playername
				for (int i = 0; i < tmpChar.length; i++) {
					if (!Character.isLetter(tmpChar[i])) {
						tmpPlayerName = tmpPlayerPointsLine.substring(0, i);
						tmpPlayerPointsLine = tmpPlayerPointsLine.substring(i);
					}
				}
				// Saving the score of the player
				for (int i = (tmpChar.length - 1); i > 0; i--) {
					if (!Character.isDigit(tmpChar[i])) {
						tmpPlayerPointsList.add(Integer
								.parseInt(tmpPlayerPointsLine.substring(i)));
						tmpPlayerPointsLine = tmpPlayerPointsLine.substring(0,
								i);
					}
				}
			}

		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		this.playerList.put(tmpPlayerName, tmpPlayerPointsList);
	}

	public HashMap<String, ArrayList<Integer>> getList() {
		return this.playerList;
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
