package com.github.joakimpersson.tda367.core.bombs;

import java.util.List;
import java.util.Timer;

import com.github.joakimpersson.tda367.core.Direction;
import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Tile;

public class NormalBomb extends Bomb {
	Tile[][] map;

	public NormalBomb(Player p, Timer t) {
		super(p, t);
	}

	@Override
	public List<Position> explode(Tile[][] m) {
		map = m;

		directedFire(Direction.Up);
		directedFire(Direction.Down);
		directedFire(Direction.Left);
		directedFire(Direction.Right);

		// dont forget to add itself
		fireList.add(pos);

		return fireList;
	}

	private void directedFire(Direction dir) {
		int x = pos.getX();
		int y = pos.getY();

		int firePower = power;

		for (int i = 1; i <= range; i++) {

			Position firePos = new Position(x + (dir.getX() * i), y
					+ (dir.getY() * i));

			// int toughness = tryBreak(firePos, firePower, map[x][y]);

			// if (toughness >= 0) {
			// fireList.add(firePos);
			// firePower = firePower-toughness;
			// }
			Tile tile = map[firePos.getX()][firePos.getY()];

			boolean toughness = tryBreak(tile, firePower);
			if (toughness) {
				fireList.add(firePos);
				firePower -= tile.getToughness();
			} else {
				break;// fire stops directly
			}
		}
	}
}
