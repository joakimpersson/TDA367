package com.github.joakimpersson.tda367.model.tiles.walkable;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.utils.Position;

public class FloorTest {

	private Floor floor;

	@Before
	public void setUp() throws Exception {
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
		Position pos = new Position(1, 1);
		Player player = new Player(1,"Kalle", pos);
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
	}

}
