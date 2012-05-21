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

/**
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class RangeUpdateItemTest {

	private RangeUpdateItem rangeItem;

	@Before
	public void setUp() throws Exception {

		rangeItem = new RangeUpdateItem();
	}

	@Test
	public void testGetToughness() {
		int tougness = rangeItem.getToughness();
		assertEquals(0, tougness);
	}

	@Test
	public void testOnFire() {
		Tile tile = rangeItem.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertTrue(rangeItem.isWalkable());
	}

	@Test
	public void testGetTileType() {
		String expected = "rangeUpItem";
		String actual = rangeItem.getTileType();
		assertEquals(expected, actual);
	}

	@Test
	public void testPlayerEnter() {
		Player player = new Player(1, "Kalle", new Position(0, 0));
		Tile tile = rangeItem.playerEnter(player);
		assertThat(tile, is(instanceOf(Floor.class)));

		int actual = player.getAttribute(Attribute.BombRange);
		int expected = Parameters.INSTANCE.getInitBombRange() + 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAttr() {
		Attribute attr = rangeItem.getAttr();
		assertEquals(Attribute.BombRange, attr);
	}

	@Test
	public void testEquals() {
		Tile otherItem = new RangeUpdateItem();
		Tile otherAttrTile = new SpeedUpdateItem();

		// testing for null and self
		assertTrue(rangeItem.equals(rangeItem));
		assertFalse(rangeItem.equals(null));

		// should be true
		assertTrue(rangeItem.equals(otherItem));

		// should be false since an box is not an wall
		assertFalse(rangeItem.equals(otherAttrTile));
	}

	@Test
	public void testHashCode() {

		Tile otherItem = new RangeUpdateItem();
		Tile otherAttrTile = new SpeedUpdateItem();

		// should be true
		assertTrue(rangeItem.hashCode() == otherItem.hashCode());

		// should be false since an box is not an wall
		assertFalse(rangeItem.hashCode() == otherAttrTile.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		rangeItem = null;
	}

}
