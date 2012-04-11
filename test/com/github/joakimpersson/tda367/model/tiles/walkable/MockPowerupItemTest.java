package com.github.joakimpersson.tda367.model.tiles.walkable;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.utils.Position;

public class MockPowerupItemTest {

	private Player player;
	private MockPowerupItem item;

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(1, 1);
		player = new Player("Kalle", pos);
		item = new MockPowerupItem();
	}

	@Test
	public void testGetAttr() {
		Attribute attr = item.getAttr();
		assertEquals(Attribute.BombRange, attr);
	}

	@Test
	public void testGetToughness() {
		int tougness = item.getToughness();
		assertEquals(0, tougness);
	}

	@Test
	public void testOnFire() {
		Tile tile = item.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertTrue(item.isWalkable());
	}

	@Test
	public void testPlayerEnter() {
		Tile tile = item.playerEnter(player);
		assertThat(tile, is(instanceOf(Floor.class)));

		PlayerAttributes attr = player.getAttr();
		int actual = attr.getAttrValue(Attribute.BombRange);
		int expected = Parameters.INSTANCE.getInitBombRange() + 1;
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		player = null;
		item = null;
	}

}
