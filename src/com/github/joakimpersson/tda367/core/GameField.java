package com.github.joakimpersson.tda367.core;

public abstract class GameField {
	private Tile[][] map;

	public abstract void createWorld();

	public abstract void resetField();

	public void setTile(Tile tile, Position pos) {

	}

}
