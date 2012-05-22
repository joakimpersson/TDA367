package com.github.joakimpersson.tda367.model.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.joakimpersson.tda367.model.constants.MapTileType;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.factory.MapTileFactory;

/**
 * A subsystem used for converting txt files that are formatted in a certain way
 * into Tile[][]. It also performes checks to make sure that the .txt file is
 * formatterd correctly
 * 
 * @author joakimpersson
 * 
 */
public class MapLoader {

	private String mapsFolderPath = "./res/maps";
	private Map<Character, MapTileType> chars;
	private MapTileFactory mapTileFactory;
	private final int WIDTH;
	private final int HEIGHT;
	private List<Tile[][]> maps = null;
	private static MapLoader instance = null;

	private MapLoader() {
		this.chars = new HashMap<Character, MapTileType>();
		this.WIDTH = Parameters.INSTANCE.getMapSize().width;
		this.HEIGHT = Parameters.INSTANCE.getMapSize().height;
		this.mapTileFactory = new MapTileFactory();
		this.maps = new ArrayList<Tile[][]>();
		initTypeCharArray();
		initMapsList();
	}

	/**
	 * Gets the map object.
	 * 
	 * @return the map as a MapLoader object.
	 */
	public static MapLoader getInstance() {
		if (instance == null) {
			instance = new MapLoader();
		}
		return instance;
	}

	/**
	 * Initializes the map from a file.
	 */
	private void initMapsList() {
		File[] files = FileScanner.readFilesFromFolder(mapsFolderPath);
		for (File file : files) {
			Tile[][] map = createMapFromTxt(file);
			maps.add(map);
		}
	}

	/**
	 * Initializes the Array with the different tile types.
	 */
	private void initTypeCharArray() {
		chars.put('w', MapTileType.Wall);
		chars.put('f', MapTileType.Floor);
		chars.put('b', MapTileType.Box);
		chars.put('p', MapTileType.Pillar);
	}

	/**
	 * Creates a map from a file that contains information about how the map
	 * will look like.
	 * 
	 * @param file
	 *            Which file to create the map from.
	 * @return The map as a matrix of tiles.
	 */
	private Tile[][] createMapFromTxt(File file) {
		List<String> lines = FileScanner.readTextFromFile(file);
		lines = removeWhiteSpace(lines);
		validateTxt(lines);
		return converToTileMatrix(lines);
	}

	/**
	 * Removes all the whitespace in the the list of Strings.
	 * 
	 * @param lines
	 *            The List that get it's whitespace removed
	 * @return The same list without the whitespace.
	 */
	private List<String> removeWhiteSpace(List<String> lines) {
		List<String> tmpLines = new ArrayList<String>();
		for (String line : lines) {
			tmpLines.add(line.replaceAll(" ", ""));
		}
		return tmpLines;
	}

	/**
	 * Checks if a String list got valid content and size.
	 * 
	 * @param lines
	 *            The list that will be checked
	 * @throws IllegalArgumentException
	 */
	private void validateTxt(List<String> lines)
			throws IllegalArgumentException {

		validateSize(lines);
		validateContent(lines);

	}

	/**
	 * Checks if a String List got valid content.
	 * 
	 * @param lines
	 *            The list that will be checked.
	 * @throws IllegalArgumentException
	 */
	private void validateContent(List<String> lines)
			throws IllegalArgumentException {
		int row = 1;
		for (String line : lines) {
			if (!correctSymbols(line.toCharArray())) {
				Utils.errorMsg("Non valid symbols at row: " + row);
			}
			row++;
		}
	}

	/**
	 * Checks if a String List that represent the map got a valid size.
	 * 
	 * @param lines
	 *            The list that will be checked.
	 * @throws IllegalArgumentException
	 */
	private void validateSize(List<String> lines)
			throws IllegalArgumentException {
		int height = this.HEIGHT;
		int width = this.WIDTH;
		if (lines.size() != height) {
			Utils.errorMsg("Map height is wrong", height, lines.size());
		}

		int line = 1;
		for (String string : lines) {
			if (string.length() != width) {
				Utils.errorMsg("Wrong map width at line: " + line, width, string.length());
			}
			line++;
		}

	}

	/**
	 * Converts a String List into a matrix of tiles.
	 * 
	 * @param lines
	 *            The list that will be converted.
	 * @return A matrix of tiles.
	 */
	private Tile[][] converToTileMatrix(List<String> lines) {
		Tile[][] map = new Tile[this.HEIGHT][this.WIDTH];

		for (int i = 0; i < map.length; i++) {
			map[i] = convertToTileArray(lines.get(i));
		}

		return map;
	}

	/**
	 * Converts a String to an Array of tiles.
	 * 
	 * @param string
	 *            The String that will be converted.
	 * @return An Array of tiles.
	 */
	private Tile[] convertToTileArray(String string) {
		Tile[] tiles = new Tile[string.length()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = converSymbolToTile(string.charAt(i));
		}
		return tiles;
	}

	/**
	 * Converts a character to a Tile.
	 * 
	 * @param charAt
	 *            The character that will be converted.
	 * @return A Tile.
	 */
	private Tile converSymbolToTile(Character charAt) {
		MapTileType type = chars.get(charAt);
		return mapTileFactory.createObject(type);
	}

	/**
	 * Checks if an Character Array contains correct symbols.
	 * 
	 * @param symbols
	 *            The Array that will be checked
	 * @return A true if the Array is correct and false otherwise.
	 */
	private boolean correctSymbols(char[] symbols) {
		Set<Character> legalCharacters = chars.keySet();
		for (Character c : symbols) {
			if (!legalCharacters.contains(c)) {
				return false;
			}
		}
		return true;
	}



	/**
	 * Gets a List of the maps as matrix of tiles.
	 * 
	 * @return The maplist.
	 */
	public List<Tile[][]> getMapList() {
		List<Tile[][]> mapList = new ArrayList<Tile[][]>();

		for (Tile[][] tmpMap : maps) {
			Tile[][] tmp = Utils.copyGameMap(tmpMap);
			mapList.add(tmp);
		}

		return mapList;
	}

	/**
	 * Gets a Map.
	 * 
	 * @param index
	 *            Selects which map to get.
	 * @return The map with the selected index.
	 */
	public Tile[][] getMap(int index) {
		if (index > maps.size() || index < 0) {
			String msg = "There is no map with index: " + index;
			Utils.errorMsg(msg);
		}
		Tile[][] tileMap = maps.get(index);

		return Utils.copyGameMap(tileMap);
	}
}
