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
 * @modified Viktor Anderling, Andreas Rolén
 * 
 */
public class AreaBombTest {

	private Timer timer;
	private Bomb areaBomb;
	private Player player;
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
		Position pos = new Position(2, 3);
		timer = new Timer();
		player = new Player(1, "Kalle", pos);
		areaBomb = new AreaBomb(player, timer);
	}

	@Test
	public void testGetToughness() {
		assertEquals(1, areaBomb.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = areaBomb.onFire();
		assertThat(tile, is(instanceOf(Floor.class)));
	}

	@Test
	public void testIsWalkable() {
		assertFalse(areaBomb.isWalkable());
	}

	@Test
	public void testGetTileType() {
		String expected = "bomb-area";
		String actual = areaBomb.getTileType();
		assertEquals(expected, actual);
	}

	public void testExplode() {

		Map<Position, Direction> expected = new HashMap<Position, Direction>();
		expected.put(new Position(1, 2), null);
		expected.put(new Position(3, 2), null);
		expected.put(new Position(1, 3), null);
		expected.put(new Position(2, 3), null);
		expected.put(new Position(3, 3), null);
		expected.put(new Position(2, 4), null);
		expected.put(new Position(1, 4), null);
		expected.put(new Position(3, 4), null);
		HashMap<Position, Direction> actual = new HashMap<Position, Direction>(areaBomb
				.explode(map));
				
		for (Position pos : actual.keySet()) {
			System.out.println(pos+" "+actual.get(pos));
		}

		// can not use the lists equal method since it does not take into
		// consideration that the two lists might have the positions at
		// different index
		assertEquals(expected.size(), actual.size());

		for (Position pos : expected.keySet()) {
			assertTrue(actual.keySet().contains(pos));
			assertTrue(actual.get(pos) == expected.get(pos));
		}
	}

	@After
	public void tearDown() throws Exception {
		timer = null;
		player = null;
		areaBomb = null;
	}

	@AfterClass
	public static void destroyMap() {
		map = null;
	}

}
