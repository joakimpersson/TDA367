package com.github.joakimpersson.tda367.core;

/**
 * 
 * @author joakimpersson
 *
 */
public class Bomb {

	private Position pos;
	private Player player;
	
	public Bomb(Player player, Position pos) {
		this.player = player;
		this.pos = pos;
	}

	public void explode() {
		//TODO add implementation
		//Depending on the players attr
	}
	
	public Player getPlayer() {
		return player;
	}
}
