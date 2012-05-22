package com.github.joakimpersson.tda367.model.tiles.bombs;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;

/**
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class NormalBombTest {

	private Bomb bomb;
	private static Tile[][] map = {
			{ new Wall(), new Wall(), new Wall(), new Wall(), new Wall() },
			{ new Wall(), new Floor(), new Box(), new Floor(), new Wall() },
			{ new Wall(), new Floor(), new Pillar(), new Floor(), new Wall() },
			{ new Wall(), new Box(), new Floor(), new Box(), new Wall() },
			{ new Wall(), new Floor(), new Box(), new Floor(), new Wall() },
			{ new Wall(), new Floor(), new Floor(), new Floor(), new Wall() },
			{ new Wall(), new Wall(), new Wall(), new Wall(), new Wall() } };

	@Before
	public void setUp() throws Exception {
		Position position = new Position(2, 3);
		Timer timer = new Timer();
		Player player = new Player(1, "Kalle", position);
		bomb = new NormalBomb(player, timer);
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
	public void testExplode() {
		Map<Position, Direction> expected = new HashMap<Position, Direction>();
		expected.put(new Position(3, 3), Direction.EAST);
		expected.put(new Position(1, 3), Direction.WEST);
		expected.put(new Position(2, 4), Direction.SOUTH);
		expected.put(new Position(2, 3), Direction.NONE);
		Map<Position, Direction> actualPositions = bomb.explode(map);

		assertEquals(expected.size(), actualPositions.size());
		for (Position pos : expected.keySet()) {
			assertTrue(actualPositions.containsKey(pos));
			assertTrue(actualPositions.get(pos).equals(expected.get(pos)));
		}
	}

	@Test
	public void testGetTileType() {
		String expected = "bomb";
		String actual = bomb.getTileType();
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		bomb = null;
	}

	@AfterClass
	public static void destroyMap() {
		map = null;
	}
}
