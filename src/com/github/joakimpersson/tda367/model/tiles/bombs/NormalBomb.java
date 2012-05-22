package com.github.joakimpersson.tda367.model.tiles.bombs;

import static com.github.joakimpersson.tda367.model.constants.Direction.SOUTH;
import static com.github.joakimpersson.tda367.model.constants.Direction.WEST;
import static com.github.joakimpersson.tda367.model.constants.Direction.NONE;
import static com.github.joakimpersson.tda367.model.constants.Direction.EAST;
import static com.github.joakimpersson.tda367.model.constants.Direction.NORTH;

import java.util.Map;
import java.util.Timer;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Destroyable;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;

/**
 * Definition of a regular bomb.
 * 
 * @author rekoil
 * @modified Viktor Anderling
 * 
 */
public class NormalBomb extends Bomb {
	private Tile[][] map;
	protected boolean removedFromPlayer = false;

	/**
	 * Creates a regular bomb.
	 * 
	 * @param player
	 *            Defines the owner of the bomb.
	 * @param timer
	 *            The timer which will detonate the bomb.
	 */
	public NormalBomb(Player player, Timer timer) {
		super(player, timer);
		this.player.increaseBombsPlaced();
	}

	@Override
	public synchronized Map<Position, Direction> explode(Tile[][] m) {
		map = m;

		directedFire(NORTH);
		directedFire(SOUTH);
		directedFire(EAST);
		directedFire(WEST);

		// dont forget to add itself
		fireList.put(position, NONE);

		removeFromPlayer();
		return fireList;
	}

	/**
	 * Creates a column of fire.
	 * 
	 * @param dir
	 *            Direction of fire-column.
	 */
	private void directedFire(Direction dir) {
		int x = position.getX();
		int y = position.getY();

		int firePower = power;

		for (int i = 1; i <= range; i++) {
			if (firePower > 0) {
				Position firePos = new Position(x + (dir.getX() * i), y + (dir.getY() * i));
				if (validPos(firePos)) {
					Tile tile = map[firePos.getY()][firePos.getX()]; // inverted
																		// >.<
					if (canBreak(tile, firePower)) {
						Destroyable destroyableTile = (Destroyable) tile;
						firePower -= destroyableTile.getToughness();
						fireList.put(firePos, dir);
					} else {
						break; // fire stops directly
					}
				}
			}
		}
	}

	@Override
	public String getTileType() {
		return "bomb";
	}
	
	@Override
	public Tile onFire() {
		removeFromPlayer();
		this.timer.cancel();
		return new Floor();
	}

	protected synchronized void removeFromPlayer() {
		if (!removedFromPlayer) {
			this.player.decreaseBombsPlaced();
			removedFromPlayer = true;
		}
	}
}
