package com.github.joakimpersson.tda367.core;

import java.util.ArrayList;
import java.util.List;

import com.github.joakimpersson.tda367.core.tiles.Tile;

/**
 * 
 * @author joakimpersson
 * 
 */
public class BombermanModel implements IBombermanModel {

	private List<Player> players;
	private GameField gameField;

	public BombermanModel() {
		this.players = new ArrayList<Player>();
	}

	public void startGame() {

	}

	public void endGame() {

	}

	public void updateGame(Player player, PlayerAction action) {
		if (action == PlayerAction.PlaceBomb) {
			this.placeBomb(player);
		} else {
			this.move(player, action);
		}
	}

	public void upgradePlayer(Player player, Attribute attr) {

	}

	private void matchEnd() {

	}

	private void turnEnd() {

	}

	private void move(Player player, PlayerAction action) {

	}

	private void placeBomb(Player player) {
		Bomb bomb = player.placeBomb();
		bomb.explode();
	}

	private void updatePlayerScore(Player plaayer, List<Tile> tiles) {

	}

	private void handleFire() {

	}

	public List<Player> getPlayers() {
		return players;
	}
}
