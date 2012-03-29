package com.github.joakimpersson.tda367.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Highscore {

	private File file = new File("HighScore.txt");
	private TreeMap<String, Integer> playerList = new TreeMap<String, Integer>();

	public void update(ArrayList<Player> otherPlayers) {

		HashMap<String, Integer> tmpList = new HashMap<String, Integer>();

		for (int i = 0; i < otherPlayers.size(); i++) {
			tmpList.put(otherPlayers.get(i).getName(), otherPlayers.get(i)
					.getScore());
		}

		playerList.putAll(tmpList);

		this.saveList();
	}

	public void saveList() {
		Writer output = null;
		StringBuilder scoreText = null;
		// Creating a list were each row represent a players score and details
		for (int i = 0; i < this.playerList.size(); i++) {
			scoreText.append(this.playerList.pollLastEntry().getKey() + "_"
					+ this.playerList.pollLastEntry().getValue() + "\n");
		}
	
		try {
			output = new BufferedWriter(new FileWriter(file));
			// Clearing out the textfile
			output.write("");

			// Writing updated list
			output.write(scoreText.toString());
			output.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}

	public void loadList() {
		String tmpPlayerPointsLine;
		String tmpPlayerName = null;
		Integer tmpPlayerScore = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			System.out.println("File not found!");
		}
		try {
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
						tmpPlayerScore = Integer
								.parseInt(tmpPlayerPointsLine.substring(i));
						tmpPlayerPointsLine = tmpPlayerPointsLine.substring(0,
								i);
					}
				}
				
				this.playerList.put(tmpPlayerName, tmpPlayerScore);
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: " + e);
		} catch (IOException e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		} 

		
	}

	public Map<String, Integer> getList() {
		return this.playerList;
	}

	public int getSize() {
		return this.playerList.size();
	}

	public void clear() {
		this.playerList.clear();
	}

}
