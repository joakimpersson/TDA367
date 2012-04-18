package com.github.joakimpersson.tda367.model.map;

import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.utils.Position;

public interface IGameMap {
	Tile[][] getMap();

	void setTile(Tile tile, Position pos);

	Tile getTile(Position pos);

	void reset();

}
