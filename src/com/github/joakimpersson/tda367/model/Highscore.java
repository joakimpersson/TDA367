package com.github.joakimpersson.tda367.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.Player;

public class Highscore {

	private File file;
	private List<Score> playerList;
	private final int maxSize;

	public Highscore() {
		playerList = new ArrayList<Score>();
		file = new File("highScore.data");
		maxSize = Parameters.INSTANCE.getHighscoreMaxSize();
		loadList();
	}

	public void update(List<Player> otherPlayers) {
		for (Player p : otherPlayers) {
			Score score = new Score(p.getName(), p.getPoints());
			playerList.add(score);
		}

		// Reverse the order, since it sorts ascending
		Collections.sort(playerList);
		Collections.reverse(playerList);
		this.trimeHighScoreList();
		this.saveList();
	}

	private void trimeHighScoreList() {
		int index = playerList.size() - 1;
		while (playerList.size() > maxSize) {
			playerList.remove(index);
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
					playerList = (List<Score>) source.readObject();
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

	public List<Score> getList() {
		this.loadList();
		return playerList;
	}

	public int getSize() {
		return this.playerList.size();
	}

	public void reset() {
		playerList.clear();
		saveList();
	}
}
