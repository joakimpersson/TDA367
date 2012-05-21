package com.github.joakimpersson.tda367.model.tiles.walkable;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.SpeedUpdateItem;

/**
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class SpeedUpdateItemTest {

	private SpeedUpdateItem speedItem;

	@Before
	public void setUp() throws Exception {
		speedItem = new SpeedUpdateItem();
	}

	@Test
	public void testGetToughness() {
		int tougness = speedItem.getToughness();
		assertEquals(0, tougness);
	}

	@Test
	public void testOnFire() {
		Tile tile = speedItem.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertTrue(speedItem.isWalkable());
	}

	@Test
	public void testPlayerEnter() {
		Player player = new Player(1, "Kalle", new Position(0, 0));
		Tile tile = speedItem.playerEnter(player);
		assertThat(tile, is(instanceOf(Floor.class)));

		int actual = player.getAttribute(Attribute.Speed);
		int expected = Parameters.INSTANCE.getInitSpeed() + 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAttr() {
		Attribute attr = speedItem.getAttr();
		assertEquals(Attribute.Speed, attr);
	}

	@Test
	public void testGetTileType() {
		String expected = "speedUpItem";
		String actual = speedItem.getTileType();
		assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {
		Tile otherItem = new SpeedUpdateItem();
		Tile otherAttrTile = new BombStackUpdateItem();

		// testing for null and self
		assertTrue(speedItem.equals(speedItem));
		assertFalse(speedItem.equals(null));

		// should be true
		assertTrue(speedItem.equals(otherItem));

		// should be false since an box is not an wall
		assertFalse(speedItem.equals(otherAttrTile));
	}

	@Test
	public void testHashCode() {

		Tile otherItem = new SpeedUpdateItem();
		Tile otherAttrTile = new BombStackUpdateItem();

		// should be true
		assertTrue(speedItem.hashCode() == otherItem.hashCode());

		// should be false since an box is not an wall
		assertFalse(speedItem.hashCode() == otherAttrTile.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		speedItem = null;
	}
}
