package com.github.joakimpersson.tda367.core.bombs;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Timer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Box;
import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Pillar;
import com.github.joakimpersson.tda367.core.tiles.Tile;

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

	@Test
	public void testTryBreak() {
		// TODO jocke should this be tested and if so how?
		// TODO jocke remember adrian why it shouldn't be package private
		Position pos = new Position(-1, -1);
		Tile pillar = new Pillar();
		Tile box = new Box();
		int power = 1;
		
		

	}

	@Test(expected = IllegalArgumentException.class)
	public void testTryBreakException() {
		Position pos = new Position(-1, -1);
		Tile tile = new Floor();
		int power = 1;
		bomb.tryBreak(pos, power, tile);
	}

	@After
	public void tearDown() throws Exception {
		timer = null;
		player = null;
		bomb = null;
	}

}
