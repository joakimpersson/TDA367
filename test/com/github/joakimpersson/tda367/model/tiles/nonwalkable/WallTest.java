package com.github.joakimpersson.tda367.model.tiles.nonwalkable;

import static org.junit.Assert.assertFalse;

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
	public void testIsWalkable() {
		assertFalse(wall.isWalkable());
	}

	@After
	public void tearDown() throws Exception {
		wall = null;
	}
}
