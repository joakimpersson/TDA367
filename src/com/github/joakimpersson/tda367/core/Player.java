package com.github.joakimpersson.tda367.core;

import java.util.Timer;

import static com.github.joakimpersson.tda367.core.Attribute.*;
import static com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType.*;

import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.bombs.AreaBomb;
import com.github.joakimpersson.tda367.core.bombs.Bomb;
import com.github.joakimpersson.tda367.core.bombs.NormalBomb;

public class Player {

	private String name;
	private Position initialPosition, pos;
	private PlayerAttributes attr;
	private PlayerPoints points;
	private int bombsPlaced, health;

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

	public void roundReset() {
		this.attr.resetAttr(Round);
		this.health = getAttribute(Health);
		this.pos = initialPosition;
		this.bombsPlaced = 0;
	}

	public void matchReset() {
		this.attr.resetAttr(Match);
		roundReset();
	}

	public void move(PlayerAction action) {
		// TODO send the change to BombermanModel
		switch (action) {
		case MoveDown:
			pos = new Position(pos.getX(), pos.getY() + 1);
			System.out.println("Down");
			break;
		case MoveUp:
			pos = new Position(pos.getX(), pos.getY() - 1);
			System.out.println("Up");
			break;
		case MoveLeft:
			pos = new Position(pos.getX() - 1, pos.getY());
			System.out.println("Left");
			break;
		case MoveRight:
			pos = new Position(pos.getX() + 1, pos.getY());
			System.out.println("Right");
			break;
		case PlaceBomb:
			if (tryPlaceBomb()) {
				// TODO implement in BombermanModel
				System.out.println("Bomb");
			}
			break;
		default:
			// The player stands still
			break;
		}
	}

	private boolean tryPlaceBomb() {
		if (getAttribute(BombStack) > this.bombsPlaced) {
			this.bombsPlaced++;
			return true;
		}
		return false;
	}

	public Bomb createBomb(Timer timer) {
		switch (getAttribute(BombType)) {
		case 1:
			return new AreaBomb(this, timer);
		default:
			return new NormalBomb(this, timer);
		}
	}

	public void decreaseBombsPlaced() {
		this.bombsPlaced--;
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

	public PlayerAttributes getAttr() {
		return this.attr;
	}

	public int getAttribute(Attribute a) {
		return this.attr.getAttrValue(a);
	}

	public void playerHit() {
		this.health--;
	}

	public boolean isAlive() {
		return this.health > 0;
	}

	@Override
	public String toString() {
		return "P[" + this.name + ", " + this.pos + ", " + this.health + " HP]";
	}

	public int getScore() {
		return points.getScore();
	}

	public int getCredits() {
		return points.getCredits();
	}

	public String getName() {
		return name;
	}
	
	public int getHealth() {
		return health;
	}

	public Position getTilePosition() {
		return pos;
	}

	public PlayerPoints getPlayerPoints() {
		return points;
	}
}
