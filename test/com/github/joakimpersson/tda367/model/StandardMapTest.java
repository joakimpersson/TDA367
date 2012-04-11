package com.github.joakimpersson.tda367.model;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.GameField;
import com.github.joakimpersson.tda367.model.StandardMap;
import com.github.joakimpersson.tda367.model.tiles.*;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 *
 */
public class StandardMapTest {
	private GameField map;

	@Before
	public void setUp() throws Exception {
		map = new StandardMap();

	}

	private void consoleMapView(Tile[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].toString() + "\t");
			}
			System.out.println();
			System.out.println("\n");
		}
	}

	@Test
	public void testSetTile() {
		Position pos = new Position(5, 6);
		Tile expected = new Box();
		map.setTile(expected, pos);
		Tile actual = map.getTile(pos);
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTile() {
		// only here for debugging
		consoleMapView(map.getMap());

		Tile wall = map.getTile(new Position(0, 0));
		Tile floor = map.getTile(new Position(1, 1));
		Tile pillar = map.getTile(new Position(2, 2));
		Tile tile = map.getTile(new Position(6, 9));

		assertThat(wall, is(instanceOf(Wall.class)));
		assertThat(floor, is(instanceOf(Floor.class)));
		assertThat(pillar, is(instanceOf(Pillar.class)));
		assertThat(tile, is(instanceOf(Tile.class)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetTileException() {
		Position pos = new Position(1, -11);
		map.getTile(pos);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setTileException() {
		Position pos = new Position(1, -11);
		map.setTile(new Floor(), pos);
	}

	@Test
	public void testGetMap() {
		fail("Don't know how to implement");
	}

	@Test
	public void testGetSize() {
		fail("Don't know how to implement");
	}

	@Test
	public void testResetField() {
		fail("Don't know how to implement");
	}

	@After
	public void tearDown() throws Exception {
		map = null;
	}
}
