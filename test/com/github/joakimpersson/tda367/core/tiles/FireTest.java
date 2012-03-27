package com.github.joakimpersson.tda367.core.tiles;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class FireTest {

	private Fire fire;
	private Player player;

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(0, 0);
		fire = new Fire();
	}

	@Test
	public void testGetToughness() {
		// TODO jocke either change or comment why
		assertEquals(100, fire.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = fire.onFire();
		assertThat(tile, is(instanceOf(Fire.class)));
	}

	@Test
	public void testIsWalkable() {
		assertTrue(fire.isWalkable());
	}

	@Test
	public void testPlayerEnter() {
		fail("Not implemented because player is not implemented");
		// TODO adrian implement player complete
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		fire = null;
	}
}
