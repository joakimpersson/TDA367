package com.github.joakimpersson.tda367.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.player.PlayerPoints;

public class Highscore {

	private File file;
	private Map<String, PlayerPoints> playerList;

	public Highscore() {
		playerList = new TreeMap<String, PlayerPoints>();
		file = new File("HighScore.txt");
	}

	public void update(List<Player> otherPlayers) {

		for (Player p : otherPlayers) {
			this.playerList.put(p.getName(), p.getPoints());
		}

		this.saveList();
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
		try {
			FileInputStream inFile = new FileInputStream(file);
			ObjectInputStream source = new ObjectInputStream(inFile);

			try {
				playerList = (Map<String, PlayerPoints>) source.readObject();
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

	public Map<String, PlayerPoints> getList() {
		this.loadList();
		return this.playerList;
	}

	public int getSize() {
		return this.playerList.size();
	}

	public void reset() {
		playerList.clear();
		saveList();
	}
}
