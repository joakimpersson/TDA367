package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.bombs.AreaBomb;
import com.github.joakimpersson.tda367.core.bombs.Bomb;
import com.github.joakimpersson.tda367.core.bombs.NormalBomb;

/**
 * 
 * @author joakimpersson
 * 
 */
public class Player {

	private String name;
	private Position initalPosition, pos;
	private PlayerAttributes attr;
	private PlayerPoints points;
	private int lives, bombStack, health;

	public Player(String name, Position pos) {
		this.name = name;
		this.pos = pos;
		this.initalPosition = pos;
		this.attr = new PlayerAttributes();
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
		switch (attr.getAttrValue(Attribute.BombType)) {
		default:
			return new NormalBomb(this, pos,
					attr.getAttrValue(Attribute.BombRange),
					attr.getAttrValue(Attribute.BombPower));
		case 1:
			return new AreaBomb(this, pos,
					attr.getAttrValue(Attribute.BombRange),
					attr.getAttrValue(Attribute.BombPower));
		}
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

	public PlayerAttributes getAttr() {
		return this.attr;
	}

	@Override
	public String toString() {
		return "P[" + this.name + ", " + this.pos + ", " + this.health + " HP]";
	}

	public Position getTilePosition() {
		return pos;
	}

	public int getScore() {
		return points.getScore();
	}

	public int getCredits() {
		return points.getCredits();
	}

	public PlayerPoints getPlayerPoints() {
		return points;
	}
}
