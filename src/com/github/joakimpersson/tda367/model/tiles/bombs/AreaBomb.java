package com.github.joakimpersson.tda367.model.tiles.bombs;

import java.util.Map;
import java.util.Timer;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;

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
		player.increaseAreaBombsPlaced();
		
		if (range > 5) {
			this.areaRange = 3;
		} else if (range > 3) {
			this.areaRange = 2;
		} else {
			this.areaRange = 1;
		}
	}

	@Override
	public synchronized Map<Position, Direction> explode(Tile[][] map) {
		int xPos = position.getX();
		int yPos = position.getY();

		for (int x = xPos - areaRange; x <= xPos + areaRange; x++) {
			for (int y = yPos - areaRange; y <= yPos + areaRange; y++) {
				Position firePos = new Position(x, y);
				if (validPos(firePos) && canBreak(map[y][x], power)) {
					fireList.put(firePos, null);
				}
			}
		}
		return fireList;
	}

	@Override
	public String getTileType() {
		return "bomb-area";
	}
	
	@Override
	public Tile onFire() {
		this.timer.cancel();
		return new Floor();
	}

}
