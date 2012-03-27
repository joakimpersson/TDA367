package com.github.joakimpersson.tda367.core.tiles;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;

public class FloorTest {

	private Floor floor;
	private Player player;

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(1, 1);
		player = new Player("Kalle", pos);
		floor = new Floor();
	}

	@Test
	public void testGetToughness() {
		assertEquals(0, floor.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = floor.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testPlayerEnter() {
		Tile tile = floor.playerEnter(player);
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertTrue(floor.isWalkable());
	}

	@After
	public void tearDown() throws Exception {
		floor = null;
		player = null;
	}

}
