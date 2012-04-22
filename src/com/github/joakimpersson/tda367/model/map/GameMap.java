package com.github.joakimpersson.tda367.model.map;

import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.utils.Position;

public class GameMap implements IGameMap {

	private Tile[][] map;
	// TODO all perhaps refactor/move
	private static final int width = 15;
	private static final int height = 13;
	private MapLoader mapLoader;
	
	public GameMap() {
		
		mapLoader = MapLoader.getInstance();
		map = mapLoader.getMap(0);
	}

	@Override
	public Tile[][] getMap() {
		return map;
	}

	// TODO jocke perhaps refactor
	@Override
	public void setTile(final Tile tile, final Position pos) {
		isOutOfBounds(pos, "Index out of bounds");
		map[pos.getY()][pos.getX()] = tile;

	}

	private void isOutOfBounds(Position pos, String msg) {
		if (pos.getX() < 0 || pos.getX() >= width || pos.getY() < 0
				|| pos.getY() >= height) {
			errorMsg(msg);
		}
	}

	private void errorMsg(final String msg) throws IllegalArgumentException {
		throw new IllegalArgumentException(msg);
	}

	@Override
	public Tile getTile(final Position pos) {
		isOutOfBounds(pos, "Index out of bounds");
		return map[pos.getY()][pos.getX()];
	}

	@Override
	public void reset() {
		map = mapLoader.readFile();
		
	}
	
}
