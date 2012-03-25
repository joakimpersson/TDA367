package com.github.joakimpersson.tda367.core;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author joakimpersson
 *
 */
public enum Bomberman {
	INSTANCE;
	
	private List<Player>player = new ArrayList<Player>();
	private GameField gameFiled;
	
	public static Bomberman getInstance() {
		return INSTANCE;
	}
	
	public void startGame() {
		
	}
	
	public void endGame() {
		
	}
	
	public void updateGame(Player player, PlayerAction action) {
		if(action == PlayerAction.PlaceBomb) {
			this.placeBomb(player);
		} else {
			this.move(player, action);
		}
	}
	
	private void upgradePlayer(Player player, Attribute attr) {
		
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
	
	private void updatePlayerScore(Player plaayer, List<Tile>tiles) {
		
	}
	
	private void handleFire() {
		
	}
	
}
