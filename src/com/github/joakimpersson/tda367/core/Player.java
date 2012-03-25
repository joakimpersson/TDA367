package com.github.joakimpersson.tda367.core;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Player {

	private String name;
	private Position initalPosition;
	private Position pos;
	private PlayerAttribute attr;
	private PlayerPoints points;
	private int lives;
	private int bombStack;
	private int health;
	
	public Player(String name, Position pos) {
		
	}

	public void move(PlayerAction action) {
		switch (action) {
		case MoveDown:
			System.out.println("down");
			break;
		case MoveUp:
			System.out.println("Up");
			break;
		case MoveLeft:
			System.out.println("Left");
			break;
		case MoveRight:
			System.out.println("roght");
			break;
		default:
			// The player stands still
			break;
		}
	}

	public Bomb placeBomb() {
		return null;
	}

	public void roundReset() {

	}

	public void matchReset() {

	}

	public void playerHit() {

	}

	public boolean isAlive() {
		return this.health > 0;
	}
	
	
	@Override
	public String toString() {
		return "P[" + this.name + "," + this.pos + "," + this.health + "]";
	}
}
