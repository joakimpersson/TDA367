package com.github.joakimpersson.tda367.model.player;

import static com.github.joakimpersson.tda367.model.constants.Attribute.*;
import static com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType.*;

import java.util.Timer;
import java.util.TimerTask;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.model.utils.FPosition;
import com.github.joakimpersson.tda367.model.utils.Position;

public class Player {
	private class HitTask extends TimerTask {
		@Override
		public void run() {
			invulnerable = false;
		}
	}

	private String name;
	private Position initialPosition, tilePos;
	private FPosition gamePos;
	private PlayerAttributes attr;
	private PlayerPoints points;
	private int bombsPlaced, health;
	private boolean invulnerable = false;

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
		this.tilePos = initialPosition;
		this.gamePos = new FPosition(initialPosition.getX()+0.5F, initialPosition.getY()+0.5F);
		this.bombsPlaced = 0;
	}

	public void matchReset() {
		this.attr.resetAttr(Match);
		roundReset();
	}

	public void move(Direction dir) {
		gamePos = new FPosition(gamePos.getX() + dir.getX(), gamePos.getY() + dir.getY());
		tilePos = new Position((int) gamePos.getX(), (int) gamePos.getY());
	}

	public boolean canPlaceBomb() {
		if (getAttribute(BombStack) > this.bombsPlaced) {
			return true;
		}
		return false;
	}

	public void decreaseBombsPlaced() {
		this.bombsPlaced--;
	}

	public void increaseBombsPlaced() {
		this.bombsPlaced++;
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
		if (this.invulnerable == false) {
			this.health--;
			this.invulnerable = true;
			Timer invulnerableTimer = new Timer();
			invulnerableTimer.schedule(new HitTask(), Parameters.INSTANCE.getFireDuration());
		}
	}

	public boolean isAlive() {
		return this.health > 0;
	}

	@Override
	public String toString() {
		return "P[" + this.name + ", " + this.tilePos + ", " + this.health + " HP]";
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
		return tilePos;
	}

	public PlayerPoints getPlayerPoints() {
		return points;
	}
}
