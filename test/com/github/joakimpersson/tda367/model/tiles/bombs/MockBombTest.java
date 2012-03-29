package com.github.joakimpersson.tda367.model.tiles.bombs;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Timer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MockBombTest {

	private Timer timer;
	private MockBomb bomb;
	private Player player;

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(0, 0);
		timer = new Timer();
		player = new Player("Kalle", pos);
		bomb = new MockBomb(player, timer);
	}

	@Test
	public void testGetToughness() {
		assertEquals(1, bomb.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = bomb.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertFalse(bomb.isWalkable());
	}

	@After
	public void tearDown() throws Exception {
		timer = null;
		player = null;
		bomb = null;
	}

}
