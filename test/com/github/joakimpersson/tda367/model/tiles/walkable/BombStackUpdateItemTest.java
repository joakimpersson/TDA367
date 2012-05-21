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
public class BombStackUpdateItemTest {

	private BombStackUpdateItem bombItem;

	@Before
	public void setUp() throws Exception {
		bombItem = new BombStackUpdateItem();
	}

	@Test
	public void testGetToughness() {
		int tougness = bombItem.getToughness();
		assertEquals(0, tougness);
	}

	@Test
	public void testOnFire() {
		Tile tile = bombItem.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertTrue(bombItem.isWalkable());
	}

	@Test
	public void testPlayerEnter() {
		Player player = new Player(1, "Kalle", new Position(0, 0));
		Tile tile = bombItem.playerEnter(player);
		assertThat(tile, is(instanceOf(Floor.class)));

		int actual = player.getAttribute(Attribute.BombStack);
		int expected = Parameters.INSTANCE.getStartingBombs() + 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAttr() {
		Attribute attr = bombItem.getAttr();
		assertEquals(Attribute.BombStack, attr);
	}

	@Test
	public void testGetTileType() {
		String expected = "bombUpItem";
		String actual = bombItem.getTileType();
		assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {
		Tile otherItem = new BombStackUpdateItem();
		Tile otherAttrTile = new SpeedUpdateItem();

		// testing for null and self
		assertTrue(bombItem.equals(bombItem));
		assertFalse(bombItem.equals(null));

		// should be true
		assertTrue(bombItem.equals(otherItem));

		// should be false since an box is not an wall
		assertFalse(bombItem.equals(otherAttrTile));
	}

	@Test
	public void testHashCode() {

		Tile otherItem = new BombStackUpdateItem();
		Tile otherAttrTile = new SpeedUpdateItem();

		// should be true
		assertTrue(bombItem.hashCode() == otherItem.hashCode());

		// should be false since an box is not an wall
		assertFalse(bombItem.hashCode() == otherAttrTile.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		bombItem = null;
	}

}
