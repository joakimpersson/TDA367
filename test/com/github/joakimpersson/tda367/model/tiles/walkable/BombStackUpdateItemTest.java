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
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class BombStackUpdateItemTest {

	private BombStackUpdateItem item;

	@Before
	public void setUp() throws Exception {
		item = new BombStackUpdateItem();
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
		Player player = new Player(1, "Kalle", new Position(0, 0));
		Tile tile = item.playerEnter(player);
		assertThat(tile, is(instanceOf(Floor.class)));

		int actual = player.getAttribute(Attribute.BombStack);
		int expected = Parameters.INSTANCE.getStartingBombs() + 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAttr() {
		Attribute attr = item.getAttr();
		assertEquals(Attribute.BombStack, attr);
	}

	@Test
	public void testEquals() {
		Tile otherItem = new BombStackUpdateItem();
		Tile otherAttrTile = new SpeedUpdateItem();

		// testing for null and self
		assertTrue(item.equals(item));
		assertFalse(item.equals(null));

		// should be true
		assertTrue(item.equals(otherItem));

		// should be false since an box is not an wall
		assertFalse(item.equals(otherAttrTile));
	}

	@Test
	public void testHashCode() {

		Tile otherItem = new BombStackUpdateItem();
		Tile otherAttrTile = new SpeedUpdateItem();

		// should be true
		assertTrue(item.hashCode() == otherItem.hashCode());

		// should be false since an box is not an wall
		assertFalse(item.hashCode() == otherAttrTile.hashCode());
	}

	@After
	public void tearDown() throws Exception {

		item = null;
	}

}
