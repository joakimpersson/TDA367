package com.github.joakimpersson.tda367.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.TreeSet;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.Player;

public class Highscore {

	private File file;
	private TreeSet<Score> playerList;
	private final int maxSize;

	public Highscore() {
		playerList = new TreeSet<Score>();
		file = new File("HighScore.txt");
		maxSize = Parameters.INSTANCE.getHighscoreMaxSize();
	}

	public void update(List<Player> otherPlayers) {
		for (Player p : otherPlayers) {
			Score score = new Score(p.getName(), p.getPoints());
			playerList.add(score);
		}

		this.trimeHighScoreList();

		this.saveList();
	}

	private void trimeHighScoreList() {
		while (playerList.size() > maxSize) {
			playerList.pollLast();
		}

	}

	private void saveList() {
		try {
			FileOutputStream outFile = new FileOutputStream(this.file);
			ObjectOutputStream dest = new ObjectOutputStream(outFile);

			try {
				dest.writeObject(playerList);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					dest.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO jocke reconsider this
	@SuppressWarnings("unchecked")
	private void loadList() {
		if (file.exists()) {
			try {
				FileInputStream inFile = new FileInputStream(file);
				ObjectInputStream source = new ObjectInputStream(inFile);

				try {
					playerList = (TreeSet<Score>) source.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						source.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Score[] getList() {
		Score[] highscore = null;
		this.loadList();
		highscore = (Score[]) playerList.toArray(new Score[playerList.size()]);
		return highscore;
	}

	public int getSize() {
		return this.playerList.size();
	}

	public void reset() {
		playerList.clear();
		saveList();
	}
}
