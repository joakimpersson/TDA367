package com.github.joakimpersson.tda367.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PlayerTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player("Joakim");
	}

	@Test
	public void testPlayerHit() {
		player.playerHit();
		int health = Parameters.INSTANCE.getPlayerHealth();
		assertEquals(player.getHealth(), health - 1);
	}

	@Test
	public void killPlayer() {

		int health = Parameters.INSTANCE.getPlayerHealth();
		for (int i = 0; i < health; i++) {
			player.playerHit();
		}
		assertEquals(player.getHealth(), 0);
	}

	@Test
	public void testCanPlaceBomb() {
		// TODO perhaps a better test
		long time = Parameters.INSTANCE.getBombDetonationTime();
		player.placeBomb();

		try {
			Thread.sleep(time / 2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertFalse(player.canPlaceBomb());

		try {
			Thread.sleep((long) (time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertTrue(player.canPlaceBomb());
	}

	@Test
	public void testPlaceBomb() {
		// TODO perhaps implement a better test!
		long time = Parameters.INSTANCE.getBombDetonationTime();
		int nbrOfBombs = Parameters.INSTANCE.getNbrOfStartingBombs();

		player.placeBomb();

		try {
			Thread.sleep((long) (1.1 * time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(player.getNbrOfAvailableBombs(), nbrOfBombs);

		player.placeBomb();

		try {
			Thread.sleep((long) (1.1 * time));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(player.getNbrOfAvailableBombs(), nbrOfBombs);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlaceBombException() {
		player.placeBomb();
		player.placeBomb();
	}

	@After
	public void tearDown() throws Exception {
		player = null;
	}

}
