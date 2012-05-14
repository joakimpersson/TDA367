package com.github.joakimpersson.tda367.model.tiles.bombs;

import java.util.Map;
import java.util.Timer;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Tile;

/**
 * Definition of an area-bomb.
 * 
 * @author rekoil
 */
public class AreaBomb extends Bomb {

	private int areaRange;

	/**
	 * Creates an area-bomb, these have shorter range than regular bombs.
	 * 
	 * @param player
	 *            The owner of the bomb.
	 * @param timer
	 *            The timer which will detonate the bomb.
	 */
	public AreaBomb(Player player, Timer timer) {
		super(player, timer);
		
		if (range > 5) {
			this.areaRange = 3;
		} else if (range > 3) {
			this.areaRange = 2;
		} else {
			this.areaRange = 1;
		}
	}

	@Override
	public Map<Position, Direction> explode(Tile[][] map) {
		int xPos = pos.getX();
		int yPos = pos.getY();

		for (int x = xPos - areaRange; x <= xPos + areaRange; x++) {
			for (int y = yPos - areaRange; y <= yPos + areaRange; y++) {
				Position firePos = new Position(x, y);
				if (validPos(firePos) && tryBreak(map[y][x], power)) {
					fireList.put(firePos, null);
				}
			}
		}

		removeFromPlayer();
		return fireList;
	}

	@Override
	public String getTileType() {
		return "bomb-area";
	}

}
