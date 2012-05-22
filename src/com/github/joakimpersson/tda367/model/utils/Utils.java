package com.github.joakimpersson.tda367.model.utils;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.tiles.Tile;

/**
 * A utils class for the model
 * 
 * @author joakimpersson
 * 
 */
public class Utils {
	private Utils() {
	}

	/**
	 * Get a copy of a matrix representing the game map used in the game
	 * 
	 * @param originalMap
	 *            The tile matrix map
	 * @return A copy of the map
	 */
	public static Tile[][] copyGameMap(Tile[][] originalMap) {
		int height = Parameters.INSTANCE.getMapSize().height;
		int width = Parameters.INSTANCE.getMapSize().width;
		Tile[][] copyOfMap = new Tile[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				copyOfMap[i][j] = originalMap[i][j];
			}
		}
		return copyOfMap;
	}

	/**
	 * Get if two map tile matrixs are equal. The methods consider size and if
	 * they have the tiles at the same positions in the matrix:s are equal
	 * 
	 * @param mapOne
	 *            The first tile matrix map
	 * @param mapTwo
	 *            The second tile matrix map
	 * @return If they are equal or not
	 */
	public static boolean mapMatrixEquals(Tile[][] mapOne, Tile[][] mapTwo) {

		if (mapOne.length != mapTwo.length) {
			return false;
		}

		for (int i = 0; i < mapOne.length; i++) {
			if (mapOne[i].length != mapTwo[i].length) {
				return false;
			}
		}

		for (int i = 0; i < mapOne.length; i++) {
			for (int j = 0; j < mapOne[i].length; j++) {
				Tile tmpTile = mapOne[i][j];
				Tile otherTile = mapTwo[i][j];
				if (!(tmpTile.equals(otherTile))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * A util method for displaying error messages
	 * 
	 * @param msg
	 *            The error message describing the problem
	 * @throws IllegalArgumentException
	 *             with the corresponding error message
	 */
	public static void errorMsg(String msg) throws IllegalArgumentException {
		throw new IllegalArgumentException(msg);
	}

	/**
	 * A util method for displaying error messages, which also shows a what the
	 * method expected and actually got
	 * 
	 * @param msg
	 *            The error message describing the problem
	 * @param expected
	 *            The expected value
	 * @param actual
	 *            The actual value
	 * @throws IllegalArgumentException
	 *             with the corresponding error message
	 */
	public static <E> void errorMsg(String msg, E expected, E actual)
			throws IllegalArgumentException {
		throw new IllegalArgumentException(msg + " expected: " + expected
				+ " and got: " + actual);
	}
}
