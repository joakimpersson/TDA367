package com.github.joakimpersson.tda367.core.bombs;

import java.util.ArrayList;
import java.util.List;

import com.github.joakimpersson.tda367.core.Direction;
import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;


public class NormalBomb extends Bomb {
	private int power, range;

	public NormalBomb(Player player, Position pos, int range, int power) {
		super(player, pos, range, power);
	}
	
	@Override
	public void explode() {
		int x = pos.getX();
		int y = pos.getY();
		List<Position> fireList = new ArrayList<Position>();

		directedFire(fireList, x, y, Direction.Up, range, power);
		directedFire(fireList, x, y, Direction.Down, range, power);
		directedFire(fireList, x, y, Direction.Left, range, power);
		directedFire(fireList, x, y, Direction.Right, range, power);
		
		bm.handleFire(getPlayer(), fireList);
	}

	private void directedFire(List<Position> l, int x, int y, Direction dir, int r, int p) {
		for (int i = 1; i <= r; i++) {
			int firePower = p;
			Position pos = new Position(x+(dir.getX()*i),y+(dir.getY()*i));
			
			int toughness = tryBreak(pos, firePower);
			if (toughness >= 0) {
				l.add(pos);
				firePower = firePower-toughness;
			}
		}
	}
}
