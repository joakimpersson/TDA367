package com.github.joakimpersson.tda367.core;

import java.awt.Dimension;



/**
 * @date 2012-03-26
 * @author Viktor Anderling
 * 
 * An abstract class representing the game-field with a matrix of tiles.
 * 
 */
public abstract class GameField {
	private Tile[][] map;
	
	public GameField(int xSize, int ySize) {
		this.map = new Tile[xSize][ySize];
	}
	
	/**
	 * A method that is used to create the game-field, but also functions
	 * as way to reset it to it initial state.
	 */
	public abstract void resetField();
	

	/**
	 * A method for setting a specific position of the game-field to a specific tile.
	 * 
	 * @param tile	The tile that will be set.
	 * @param pos	The position where the tile will be set.
	 */
	public void setTile(Tile tile, Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		checkPos(x, y);
		map[x][y] = tile;
	}
	
	public void setTile(Tile tile, int x, int y) {
		this.setTile(tile, new Position(x, y));
	}
	
	/**
	 * 
	 * @param pos The position of the sought tile.
	 * @return The tile of that position.
	 */
	public Tile getTile(Position pos) {
		int x = pos.getX();
		int y = pos.getY();
		checkPos(x, y);
		return map[x][y];
	}
	
	/**
	 * @return The dimension of the game-field matrix.
	 */
	public Dimension getSize() {
		return new Dimension(map.length, map[0].length);
	}
	
	/**
	 * A method for checking if coordinates are valid.
	 * 
	 * @param x 
	 * @param y
	 * @throws IndexOutBoundsException if the position has negative parameters or is
	 * bigger than the size of the matrix.
	 */
	private void checkPos(int x, int y) {
		if(x < 0 || y < 0 || x > map.length || y < map[0].length) {
			throw new IndexOutOfBoundsException();
		}
	}
}
