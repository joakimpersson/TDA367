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

	private Fire fire;
	private Player fireOwner;

	@Before
	public void setUp() throws Exception {
		fireOwner = new Player(0, "Kalle", new Position(0, 0));
		fire = new Fire(fireOwner, Direction.NONE);
	}

	@Test
	public void testIsWalkable() {
		assertTrue(fire.isWalkable());
	}

	@Test
	public void testPlayerEnter() {
		Position pos = new Position(0, 0);
		Player player = new Player(1, "Kalle", pos);
		int expected = player.getHealth() - 1;
		fire.playerEnter(player);
		int actual = player.getHealth();
		assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {
		Player otherFireOwner = new Player(1, "Hobbe", new Position(0, 2));
		Fire otherFire = new Fire(otherFireOwner, Direction.NORTH);

		// testing with self refrence and null
		assertFalse(fire.equals(null));

		assertTrue(fire.equals(fire));

		assertFalse(fire.equals(otherFire));

		otherFire = new Fire(otherFireOwner, Direction.NONE);

		assertFalse(fire.equals(otherFire));

		otherFire = new Fire(fireOwner, Direction.EAST);

		assertFalse(fire.equals(otherFire));

		otherFire = new Fire(fireOwner, Direction.NONE);

		assertTrue(fire.equals(otherFire));
	}

	@Test
	public void testHashCode() {

		Player otherFireOwner = new Player(1, "Hobbe", new Position(0, 2));
		Fire otherFire = new Fire(otherFireOwner, Direction.NORTH);

		assertFalse(fire.hashCode() == otherFire.hashCode());

		otherFire = new Fire(otherFireOwner, Direction.NONE);

		assertFalse(fire.hashCode() == otherFire.hashCode());

		otherFire = new Fire(fireOwner, Direction.EAST);

		assertFalse(fire.hashCode() == otherFire.hashCode());

		otherFire = new Fire(fireOwner, Direction.NONE);

		assertTrue(fire.hashCode() == otherFire.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		fire = null;
		fireOwner = null;
	}
}
