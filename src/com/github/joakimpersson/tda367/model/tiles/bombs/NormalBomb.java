package com.github.joakimpersson.tda367.model.tiles.bombs;

import static com.github.joakimpersson.tda367.model.constants.Direction.Down;
import static com.github.joakimpersson.tda367.model.constants.Direction.Left;
import static com.github.joakimpersson.tda367.model.constants.Direction.None;
import static com.github.joakimpersson.tda367.model.constants.Direction.Right;
import static com.github.joakimpersson.tda367.model.constants.Direction.Up;

import java.util.Map;
import java.util.Timer;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * Definition of a regular bomb.
 * 
 * @author rekoil
 * @modified Viktor Anderling
 * 
 */
public class NormalBomb extends Bomb {
	private Tile[][] map;
	private String image;

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
		this.image = "bomb";
	}

	@Override
	public Map<Position, Direction> explode(Tile[][] m) {
		map = m;

		directedFire(Up);
		directedFire(Down);
		directedFire(Left);
		directedFire(Right);

		// dont forget to add itself
		fireList.put(pos, None);

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
		int x = pos.getX();
		int y = pos.getY();

		int firePower = power;

		for (int i = 1; i <= range; i++) { // TODO should the range be regarded
											// as including the position of the
											// bomb? i.e. range of 2 means
											// player can hit only directly
											// adjacent tiles
			if (firePower > 0) {
				Position firePos = new Position(x + (dir.getX() * i), y + (dir.getY() * i));

				if (validPos(firePos)) {
					Tile tile = map[firePos.getY()][firePos.getX()]; // inverted
																		// >.<
					if (tryBreak(tile, firePower)) {
						firePower -= tile.getToughness();
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
		return this.image;
	}
}
