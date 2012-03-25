package com.github.joakimpersson.tda367.core;



/**
 * 
 * @author joakimpersson
 *
 */
public abstract class Bomb extends OpaqueTile{
	
	private int toughness;
	private Player player;
	
	public Bomb(Player player) {
		this.player = player;
		this.toughness = 3;//TODO what should happen here
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile fireAction() {
		//TODO what should happen here
		return null;
	}
	
	/**
	 * Replaces the getBombRange function and is now 
	 * only public TODO need a system for this shit!
	 */
	public abstract void explode();
}
