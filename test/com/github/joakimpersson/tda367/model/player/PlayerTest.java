package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PlayerTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(0, 0);
		player = new Player(1,"Hobbe", pos);
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetRound() {
		fail("Not yet implemented");
	}

	@Test
	public void testMatchReset() {
		fail("Not yet implemented");
	}

	@Test
	public void testPlayerHit() {
		int expected = player.getHealth() - 1;
		player.playerHit();
		int actual = player.getHealth();
		assertEquals(expected, actual);
	}

	@Test
	public void testKillPlayer() {
		int lifes = player.getHealth();
		for (int i = 0; i < lifes; i++) {
			player.playerHit();
		}
		assertTrue(player.isAlive());
	}

	@Test
	public void testGetScore() {
		fail("Not implementd");
	}

	@Test
	public void testGetCredits() {
		fail("Not implementd");
	}

	@Test
	public void testGetName() {
		String expected = "Hobbe";
		String actual = player.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetHealth() {
		int expected = Parameters.INSTANCE.getInitHealth();
		int actual = player.getHealth();
		assertEquals(expected, actual);
	}

	@Test
	public void getTilePosition() {
		Position expected = new Position(0, 0);
		Position actual = player.getTilePosition();
		assertEquals(expected, actual);
	}

	@Test
	public void getPlayerPoints() {
		fail("Not implemented");
	}
	
	@After
	public void tearDown() throws Exception {
		player = null;
	}

}
