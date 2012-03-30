package com.github.joakimpersson.tda367.core.tiles.bombs;

import java.util.List;
import java.util.Timer;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Tile;
import com.github.joakimpersson.tda367.core.tiles.bombs.Bomb;

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