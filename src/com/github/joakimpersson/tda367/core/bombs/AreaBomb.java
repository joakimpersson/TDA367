package com.github.joakimpersson.tda367.core.bombs;

import java.util.ArrayList;
import java.util.List;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;


public class AreaBomb extends Bomb {

	public AreaBomb(Player player, Position pos, int range, int power) {
		super(player, pos, range, power);
	}

	@Override
	public void explode() {
		int xPos = pos.getX();
		int yPos = pos.getY();
		List<Position> fireList = new ArrayList<Position>();
		
		for (int x = xPos-range; x < xPos+range; x++) {
			for (int y = yPos-range; y < yPos+range; y++) {
				Position pos = new Position(x, y);
				if (tryBreak(pos, power) > 0) {
					fireList.add(pos);
				}
			}
		}
		
		bm.handleFire(fireList);
	}
	
}
