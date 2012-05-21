package com.github.joakimpersson.tda367.model.tiles.walkable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class FireTest {

	private Fire fireTile;
	private Player fireOwner;

	@Before
	public void setUp() throws Exception {
		fireOwner = new Player(0, "Kalle", new Position(0, 0));
		fireTile = new Fire(fireOwner, Direction.NONE);
	}

	@Test
	public void testIsWalkable() {
		assertTrue(fireTile.isWalkable());
	}

	@Test
	public void testPlayerEnter() {
		Position position = new Position(0, 0);
		Player player = new Player(1, "Kalle", position);
		int expected = player.getHealth() - 1;
		fireTile.playerEnter(player);
		int actual = player.getHealth();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTileType() {
		String expected = "fire";
		String actual = fireTile.getTileType();
		assertTrue(actual.startsWith(expected));
	}

	@Test
	public void testEquals() {
		Player otherFireOwner = new Player(1, "Hobbe", new Position(0, 2));
		Fire otherFire = new Fire(otherFireOwner, Direction.NORTH);

		// testing with self refrence and null
		assertFalse(fireTile.equals(null));

		assertTrue(fireTile.equals(fireTile));

		assertFalse(fireTile.equals(otherFire));

		otherFire = new Fire(otherFireOwner, Direction.NONE);

		assertFalse(fireTile.equals(otherFire));

		otherFire = new Fire(fireOwner, Direction.EAST);

		assertFalse(fireTile.equals(otherFire));

		otherFire = new Fire(fireOwner, Direction.NONE);

		assertTrue(fireTile.equals(otherFire));
	}

	@Test
	public void testHashCode() {

		Player otherFireOwner = new Player(1, "Hobbe", new Position(0, 2));
		Fire otherFire = new Fire(otherFireOwner, Direction.NORTH);

		assertFalse(fireTile.hashCode() == otherFire.hashCode());

		otherFire = new Fire(otherFireOwner, Direction.NONE);

		assertFalse(fireTile.hashCode() == otherFire.hashCode());

		otherFire = new Fire(fireOwner, Direction.EAST);

		assertFalse(fireTile.hashCode() == otherFire.hashCode());

		otherFire = new Fire(fireOwner, Direction.NONE);

		assertTrue(fireTile.hashCode() == otherFire.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		fireTile = null;
		fireOwner = null;
	}
}
