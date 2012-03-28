package com.github.joakimpersson.tda367.core.bombs;

import java.util.List;
import java.util.Timer;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Tile;


public class AreaBomb extends Bomb {

	public AreaBomb(Player p, Timer t) {
		super(p, t);
	}

	@Override
	public List<Position> explode(Tile[][] map) {
		int xPos = pos.getX();
		int yPos = pos.getY();
		
		for (int x = xPos-range; x < xPos+range; x++) {
			for (int y = yPos-range; y < yPos+range; y++) {
				Position firePos = new Position(x, y);
				if (tryBreak(firePos, power, map[x][y]) >= 0) {
					fireList.add(firePos);
				}
			}
		}
		
		return fireList;
	}
	
}
