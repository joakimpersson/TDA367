package com.github.joakimpersson.tda367.core;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Timer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.bombs.AreaBomb;
import com.github.joakimpersson.tda367.core.bombs.Bomb;
import com.github.joakimpersson.tda367.core.bombs.NormalBomb;

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
		player = new Player("Hobbe", pos);
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateBomb() {
		Bomb bomb = null;
		Timer timer = new Timer();

		// creating normal bomb
		bomb = player.createBomb(timer);
		assertThat(bomb, is(instanceOf(NormalBomb.class)));

		// creating area bomb
		player.upgradeAttr(Attribute.BombType, UpgradeType.Match);
		bomb = player.createBomb(timer);
		assertThat(bomb, is(instanceOf(AreaBomb.class)));
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

	@After
	public void tearDown() throws Exception {
		player = null;
	}

}
