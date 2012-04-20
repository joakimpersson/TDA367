package com.github.joakimpersson.tda367.model.map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;

public class MapLoader {

	public enum MapTile {
		Wall, Floor, Box, Pillar;
	}

	private Character[] legalCharacters = { 'w', 'f', 'b', 'p' };
	private Map<Character, MapTile> chars;
	private int width;
	private int height;

	public MapLoader(int width, int height) {
		this.chars = new HashMap<Character, MapLoader.MapTile>();
		chars.put('w', MapTile.Wall);
		chars.put('f', MapTile.Floor);
		chars.put('b', MapTile.Box);
		chars.put('p', MapTile.Pillar);
		this.width = width;
		this.height = height;
	}

	public Tile[][] readFile() {
		File file = new File("./maps");
		File[] files = file.listFiles();
		List<String> lines = new ArrayList<String>();

		try {
			Scanner scanner = new Scanner(files[0]);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine().replaceAll(" ", "");
				if (!line.equals("")) {
					lines.add(line);
				}
			}

			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("No File, sir :/");
			e.printStackTrace();
		}
		validateTxt(lines);
		return converToTileMatrix(lines);
	}

	private Tile[][] converToTileMatrix(final List<String> lines) {
		Tile[][] map = new Tile[this.height][this.width];

		for (int i = 0; i < map.length; i++) {
			map[i] = convertToTileArray(lines.get(i));
		}

		return map;
	}

	private Tile[] convertToTileArray(final String string) {
		Tile[] tiles = new Tile[string.length()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = converSymbolToTile(string.charAt(i));
		}
		return tiles;
	}

	private Tile converSymbolToTile(final Character charAt) {
		MapTile type = chars.get(charAt);

		switch (type) {
		case Box:
			return new Box();
		case Floor:
			return new Floor();
		case Pillar:
			return new Pillar();
		case Wall:
			return new Wall();
		default:
			// should not happen
			// TODO should there be a floor here perhaps?
			return null;
		}

	}

	private void validateTxt(final List<String> lines)
			throws IllegalArgumentException {

		validateSize(lines);
		validateContent(lines);

	}

	private void validateContent(final List<String> lines)
			throws IllegalArgumentException {
		int row = 1;
		for (String line : lines) {
			if (!correctSymbols(line.toCharArray())) {
				errorMsg("Non valid symbols at row: " + row);
			}
			row++;
		}
	}

	private boolean correctSymbols(final char[] symbols) {
		// TODO not pretty
		List<Character> chars = Arrays.asList(legalCharacters);
		for (Character c : symbols) {
			if (!chars.contains(c)) {
				return false;
			}
		}
		return true;
	}

	private void validateSize(final List<String> lines)
			throws IllegalArgumentException {
		int height = this.height;
		int width = this.width;
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

	private void errorMsg(final String msg) throws IllegalArgumentException {
		throw new IllegalArgumentException(msg);
	}

	private <E> void errorMsg(final String msg, final E expected, final E actual)
			throws IllegalArgumentException {
		throw new IllegalArgumentException(msg + " expected: " + expected
				+ " and got: " + actual);
	}
}
