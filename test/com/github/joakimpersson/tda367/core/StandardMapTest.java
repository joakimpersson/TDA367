package com.github.joakimpersson.tda367.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.tiles.*;

public class StandardMapTest {
	private GameField map;
	
	@Before
	public void setUp() throws Exception {
		map = new StandardMap();
	}
	
	@Test
	public void testResetField() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTileTilePosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTileTileIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTile() {
		Tile wall = map.getTile(0, 0);
		Tile floor = map.getTile(1, 1);
		Tile pillar = map.getTile(2, 2);
		assertTrue(wall instanceof Wall && floor instanceof Floor && pillar instanceof Pillar);
	}

	@Test
	public void testGetMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}
	
	@After
	public void tearDown() throws Exception {
		map = null;
	}
}
