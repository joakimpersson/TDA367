package com.github.joakimpersson.tda367.model.tiles.bombs;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Destroyable;
import com.github.joakimpersson.tda367.model.tiles.Tile;

/**
 * This class defines a Bomb in the Pyromaniacs game.
 * 
 * @author rekoil
 * @modified Viktor Anderling, Joakim Persson, Andreas Rolén
 * 
 */
public abstract class Bomb implements Tile, Destroyable {

	protected final int toughness;
	protected final int power;
	protected final int range;
	protected final Timer timer;
	protected final Player player;
	protected final Position position;
	protected Map<Position, Direction> fireList = new HashMap<Position, Direction>();

	/**
	 * Creates an abstract Bomb.
	 * 
	 * @param player
	 *            The Player who the Bomb belongs to.
	 * @param timer
	 *            The timer which will detonate the bomb.
	 */
	public Bomb(Player player, Timer timer) {
		this.position = player.getTilePosition();
		this.player = player;
		this.toughness = 1;
		this.range = player.getAttribute(Attribute.BombRange);
		this.power = player.getAttribute(Attribute.BombPower);
		this.timer = timer;
	}

	/**
	 * Determines if a tile can be destroyed by this bomb or not.
	 * 
	 * @param tile
	 *            The tile which is to be tested.
	 * @param power
	 *            The current power of the flame.
	 * @return Whether the tile can be destroyed or not.
	 */
	protected boolean canBreak(Tile tile, int power) {
		if (tile instanceof Destroyable) {
			Destroyable destroyableTile = (Destroyable) tile;
			return power >= destroyableTile.getToughness();
		}
		return false;
	}

	/**
	 * Tests if a position is able to contain fire or not.
	 * 
	 * @param firePos
	 *            Position to test.
	 * @return Whether a Position is able to contain fire or not.
	 */
	protected boolean validPos(Position firePos) {
		Dimension dimension = Parameters.INSTANCE.getMapSize();
		return firePos.getX() >= 0 && firePos.getX() < dimension.width
				&& firePos.getY() >= 0 && firePos.getY() < dimension.height;
	}

	/**
	 * Returns the owner of the bomb.
	 * 
	 * @return The owner of the bomb.
	 */
	public Player getPlayer() {
		return player;
	}

	@Override
	public PointGiver getPointGiver() {
		return PointGiver.Bomb;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

	/**
	 * Generates a list of fires caused by the bomb.
	 * 
	 * @param map
	 *            The map which the fires will be placed on.
	 * @return A list of fires.
	 */
	public abstract Map<Position, Direction> explode(Tile[][] map);

	@Override
	public abstract Tile onFire();

}
