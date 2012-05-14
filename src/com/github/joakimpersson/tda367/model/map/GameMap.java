package com.github.joakimpersson.tda367.model.map;

import java.awt.Dimension;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Tile;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameMap implements IGameMap {

	private Tile[][] map;
	private final int WIDTH;
	private final int HEIGHT;
	private MapLoader mapLoader;

	/**
	 * Create a new GameMap instance using the default map
	 */
	public GameMap() {

		mapLoader = MapLoader.getInstance();
		map = mapLoader.getMap(0);
		Dimension mapSize = Parameters.INSTANCE.getMapSize();
		WIDTH = mapSize.width;
		HEIGHT = mapSize.height;
	}

	@Override
	public Tile[][] getMap() {
		return map;
	}

	@Override
	public void setTile(Tile tile, Position pos) {
		isOutOfBounds(pos);
		map[pos.getY()][pos.getX()] = tile;

	}

	/**
	 * A private util method that check if a position exits within the current
	 * map
	 * 
	 * @param pos
	 *            The position to be checked
	 */
	private void isOutOfBounds(Position pos) {
		if (pos.getX() < 0 || pos.getX() >= WIDTH || pos.getY() < 0
				|| pos.getY() >= HEIGHT) {
			errorMsg("The supplied position doesnt exist on the map: "
					+ pos.toString());
		}
	}

	/**
	 * A private util method used when errors occurs in the class
	 * 
	 * @param msg
	 *            The message that describes the error
	 */
	private void errorMsg(String msg) {
		throw new IllegalArgumentException(msg);
	}

	@Override
	public Tile getTile(Position pos) {
		isOutOfBounds(pos);
		return map[pos.getY()][pos.getX()];
	}

	@Override
	public void reset() {
		map = mapLoader.getMap(0);

	}

	/**
	 * Returns a text based version of the map
	 */
	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				Tile tmpTile = map[i][j];
				strBuilder.append(tmpTile.toString());
				strBuilder.append("\t");
			}
			strBuilder.append("\n");
		}
		return strBuilder.toString();
	}
}
