package com.github.joakimpersson.tda367.core;

import java.util.Timer;

import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.bombs.AreaBomb;
import com.github.joakimpersson.tda367.core.bombs.Bomb;
import com.github.joakimpersson.tda367.core.bombs.NormalBomb;

public class Player {

	private String name;
	private Position initialPosition, pos;
	private PlayerAttributes attr;
	private PlayerPoints points;
	private int bombStack, health;

	public Player(String name, Position pos) {
		this.name = name;
		this.initialPosition = pos;
		initPlayer();
	}

	private void initPlayer() {
		this.attr = new PlayerAttributes();
		this.points = new PlayerPoints();
		roundReset();
	}

	public void move(PlayerAction action) {
		// TODO send the change to Bomberman
		switch (action) {
		case MoveDown:
			pos = new Position(pos.getX(), pos.getY()+1);
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

	public Bomb createBomb(Timer timer) {
		switch (attr.getAttrValue(Attribute.BombType)) {
		case 1:
			return new AreaBomb(this, timer);
		default:
			return new NormalBomb(this, timer);
		}
	}

	public void roundReset() {
		this.attr.resetAttr(UpgradeType.Round);
		this.health = getAttribute(Attribute.Health);
		this.pos = initialPosition;
	}

	public void matchReset() {
		this.attr.resetAttr(UpgradeType.Match);
		roundReset();
	}

	public void playerHit() {
		this.health--;
	}

	public boolean isAlive() {
		return this.health > 0;
	}

	public PlayerAttributes getAttr() {
		return this.attr;
	}
	
	public int getAttribute(Attribute a) {
		return getAttr().getAttrValue(a);
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

	public String getName() {
		return name;
	}
}
