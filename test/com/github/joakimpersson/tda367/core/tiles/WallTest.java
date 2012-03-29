package com.github.joakimpersson.tda367.core.tiles;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author joakimpersson
 * 
 */
public class WallTest {

	private Wall wall;

	@Before
	public void setUp() throws Exception {
		wall = new Wall();
	}

	@Test
	public void testGetToughness() {
		// a wall should not be able to be destroyed
		assertEquals(100, wall.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = wall.onFire();
		assertThat(tile, is(instanceOf(Wall.class)));
	}

	@Test
	public void testIsWalkable() {
		assertFalse(wall.isWalkable());
	}

	@After
	public void tearDown() throws Exception {
		wall = null;
	}
}
