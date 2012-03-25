package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.core.PlayerAttribute.UpgradeType;

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
		this.name = name;
		this.pos = pos;
		this.initalPosition = pos;
		this.attr = new PlayerAttribute();
		this.points = new PlayerPoints();
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

	/**
	 * Upgrade either an round or match attribute with one level
	 * 
	 * @param attr
	 *            The attribute to be upgraded
	 * @param type
	 *            The type of the upgrade
	 */
	public void upgradeAttr(Attribute attr, UpgradeType type) {
		this.attr.upgradeAttr(attr, type);
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
