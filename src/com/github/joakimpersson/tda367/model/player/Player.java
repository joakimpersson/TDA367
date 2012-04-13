package com.github.joakimpersson.tda367.model.player;

import static com.github.joakimpersson.tda367.model.constants.Attribute.BombStack;
import static com.github.joakimpersson.tda367.model.constants.Attribute.Health;
import static com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType.Match;
import static com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType.Round;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.model.utils.FPosition;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @Modified Viktor Anderling, Joakim Persson
 * 
 */
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
		this.gamePos = new FPosition(initialPosition.getX() + 0.5F,
				initialPosition.getY() + 0.5F);
		this.bombsPlaced = 0;
	}

	public void matchReset() {
		this.attr.resetAttr(Match);
		roundReset();
	}

	public void move(Direction dir) {
		double stepSize = Parameters.INSTANCE.getPlayerStepSize();
		double newFX = gamePos.getX() + stepSize * dir.getX();
		double newFY = gamePos.getY() + stepSize * dir.getY();
		gamePos = new FPosition((float) newFX, (float) newFY);
		tilePos = new Position((int) newFX, (int) newFY);
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

	public List<Attribute> getPermantAttributes() {
		return this.attr.getAttributes();
	}

	public int getAttribute(Attribute a) {
		return this.attr.getAttrValue(a);
	}

	public void playerHit() {
		if (this.invulnerable == false) {
			this.health--;
			this.invulnerable = true;
			Timer invulnerableTimer = new Timer();
			invulnerableTimer.schedule(new HitTask(),
					Parameters.INSTANCE.getFireDuration());
		}
	}

	public boolean isAlive() {
		return this.health > 0;
	}

	@Override
	public String toString() {
		return "P[" + this.name + ", " + this.tilePos + ", " + this.health
				+ " HP]";
	}

	public int getScore() {
		return points.getScore();
	}

	public int getCredits() {
		return points.getCredits();
	}

	public void reduceCredits(int cost) {
		this.points.reduceCredits(cost);
	}

	public void updatePlayerPoints(List<PointGiver> pg) {
		this.points.update(pg);
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

	public FPosition getFPosition() {
		return gamePos;
	}

}
