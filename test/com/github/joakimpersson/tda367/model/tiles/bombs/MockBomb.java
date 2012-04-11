package com.github.joakimpersson.tda367.model.tiles.bombs;

import java.util.List;
import java.util.Timer;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 *
 */
public class MockBomb extends Bomb {

	public MockBomb(Player p, Timer t) {
		super(p, t);
	}

	@Override
	public List<Position> explode(Tile[][] map) {
		throw new UnsupportedOperationException();
	}

}
