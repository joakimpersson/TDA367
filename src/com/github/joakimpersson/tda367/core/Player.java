package com.github.joakimpersson.tda367.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * 
 * @author joakimpersson
 *
 */
public class Player {
	
	private String name;
	private Position pos;
	private int health;
	private int availableBombs;

	private ActionListener actionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			availableBombs++;

		}
	};
	
	private Timer timer = null;

	public Player(String name) {
		this.timer = new Timer((int) Parameters.INSTANCE.getBombDetonationTime(),
				actionListener);
		this.timer.setRepeats(false);
		
		this.name = name;
		this.pos = new Position(0, 0);
		this.resetPlayerHealth();

		// TODO attributeobject
		this.availableBombs = Parameters.INSTANCE.getNbrOfStartingBombs();
	}

	public Player(String name, Position pos) {
		this.name = name;
		this.pos = pos;

	}

	public void playerHit() {

		this.health--;

		if (health == 0) {
			this.killPlayer();
		}

	}

	private void killPlayer() {
		// TODO add implementation
	}

	public void resetPlayerHealth() {
		this.health = Parameters.INSTANCE.getPlayerHealth();
	}

	public boolean canPlaceBomb() {
		return availableBombs > 0;
	}

	/**
	 * @return a new bomb at the players current position
	 * @throws IllegalArgumentException
	 *             if canPlaceBomb() is false
	 */
	public Bomb placeBomb() throws IllegalStateException {
		// TODO perhaps a better implementation
		if (!canPlaceBomb()) {
			throw new IllegalStateException("canPlaceBomb must be true");
		}
		availableBombs--;
		timer.start();
		return new Bomb(this, pos);

	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return pos;
	}

	public int getHealth() {
		return health;
	}

	// TODO Only for testing
	public int getNbrOfAvailableBombs() {
		return availableBombs;
	}

	@Override
	public String toString() {
		return "P[" + this.name + "," + this.pos + "," + this.health + "]";
	}
}
