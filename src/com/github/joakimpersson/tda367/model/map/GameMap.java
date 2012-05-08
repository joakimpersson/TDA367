package com.github.joakimpersson.tda367.model.map;

import java.awt.Dimension;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Fire;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.tiles.walkable.PowerupItem;
import com.github.joakimpersson.tda367.model.utils.Position;

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
	 * Returns a string representation of this map, where "_" represents a Floor,
	 * "#" a Box, "Z" a Wall, "^" a Fire, "¤" a Bomb and "!" a PowerupItem.
	 * An unrecognized tile is represented by an "?".
	 */
	@Override
	public String toString() {
		String s = "";
		String t = "";
		for(int i = 0; i < HEIGHT; i++) {
			for(int j = 0; j < WIDTH; j++) {
				if(map[i][j] instanceof Floor) {
					t = "_ ";
				} else if(map[i][j] instanceof Pillar) {
					t = "O ";
				} else if(map[i][j] instanceof Box) {
					t = "# ";
				} else if(map[i][j] instanceof Wall) {
					t = "Z ";
				} else if(map[i][j] instanceof Fire) {
					t = "^ ";
				} else if(map[i][j] instanceof Bomb) {
					t = "¤  ";
				} else if(map[i][j] instanceof PowerupItem) {
					t = "! ";
				} else {
					t = "? ";
				}
				s = s + t;
			}
			s = s + "\n";
		}
		return s;
	}
	
}
