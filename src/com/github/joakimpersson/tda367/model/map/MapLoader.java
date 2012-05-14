package com.github.joakimpersson.tda367.model.map;

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
import com.github.joakimpersson.tda367.model.utils.FileScanner;
import com.github.joakimpersson.tda367.model.utils.Utils;

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

	public static MapLoader getInstance() {
		if (instance == null) {
			instance = new MapLoader();
		}
		return instance;
	}

	private void initMapsList() {
		File[] files = FileScanner.readFilesFromFolder(mapsFolderPath);
		for (File f : files) {
			Tile[][] map = createMapFromTxt(f);
			maps.add(map);
		}
	}

	private void initTypeCharArray() {
		chars.put('w', MapTileType.Wall);
		chars.put('f', MapTileType.Floor);
		chars.put('b', MapTileType.Box);
		chars.put('p', MapTileType.Pillar);
	}

	private Tile[][] createMapFromTxt(File file) {

		List<String> lines = FileScanner.readTextFromFile(file);
		lines = removeWhiteSpace(lines);
		validateTxt(lines);
		return converToTileMatrix(lines);
	}

	private List<String> removeWhiteSpace(List<String> lines) {
		List<String> tmpLines = new ArrayList<String>();
		for (String line : lines) {
			tmpLines.add(line.replaceAll(" ", ""));
		}
		return tmpLines;
	}

	private void validateTxt(List<String> lines)
			throws IllegalArgumentException {

		validateSize(lines);
		validateContent(lines);

	}

	private void validateContent(List<String> lines)
			throws IllegalArgumentException {
		int row = 1;
		for (String line : lines) {
			if (!correctSymbols(line.toCharArray())) {
				errorMsg("Non valid symbols at row: " + row);
			}
			row++;
		}
	}

	private void validateSize(List<String> lines)
			throws IllegalArgumentException {
		int height = this.HEIGHT;
		int width = this.WIDTH;
		if (lines.size() != height) {
			errorMsg("Map height is wrong", height, lines.size());
		}

		int line = 1;
		for (String s : lines) {
			if (s.length() != width) {
				errorMsg("Wrong map width at line: " + line, width, s.length());
			}
			line++;
		}

	}

	private Tile[][] converToTileMatrix(List<String> lines) {
		Tile[][] map = new Tile[this.HEIGHT][this.WIDTH];

		for (int i = 0; i < map.length; i++) {
			map[i] = convertToTileArray(lines.get(i));
		}

		return map;
	}

	private Tile[] convertToTileArray(String string) {
		Tile[] tiles = new Tile[string.length()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = converSymbolToTile(string.charAt(i));
		}
		return tiles;
	}

	private Tile converSymbolToTile(Character charAt) {
		MapTileType type = chars.get(charAt);
		return mapTileFactory.createObject(type);
	}

	private boolean correctSymbols(char[] symbols) {
		Set<Character> legalCharacters = chars.keySet();
		for (Character c : symbols) {
			if (!legalCharacters.contains(c)) {
				return false;
			}
		}
		return true;
	}

	private void errorMsg(String msg) throws IllegalArgumentException {
		throw new IllegalArgumentException(msg);
	}

	private <E> void errorMsg(String msg, E expected, E actual)
			throws IllegalArgumentException {
		throw new IllegalArgumentException(msg + " expected: " + expected
				+ " and got: " + actual);
	}

	public Tile[][] getMap(int index) {
		if (index > maps.size() || index < 0) {
			String msg = "There is no map with index: " + index;
			errorMsg(msg);
		}
		Tile[][] tileMap = maps.get(index);

		return Utils.copyGameMap(tileMap);
	}
}
