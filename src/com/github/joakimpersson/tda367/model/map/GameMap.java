package com.github.joakimpersson.tda367.model.map;

import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.utils.Position;

public class GameMap implements IGameMap {

	private Tile[][] map;
	// TODO all perhaps refactor/move
	private static final int width = 12;
	private static final int height = 15;

	public GameMap() {

		MapLoader mapLoader = new MapLoader(width, height);
		map = mapLoader.readFile();
	}

	@Override
	public Tile[][] getMap() {
		return map;
	}

	@Override
	public void setTile(final Tile tile, final Position pos) {
		map[pos.getY()][pos.getX()] = tile;

	}

	@Override
	public Tile getTile(final Position pos) {
		return map[pos.getY()][pos.getX()];
	}

}
