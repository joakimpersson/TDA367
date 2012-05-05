package com.github.joakimpersson.tda367.model.tiles.bombs;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Wall;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 * @modified Viktor Anderling
 * 
 */
public class AreaBombTest {

	private Timer timer;
	private Bomb bomb;
	private Player player;
	private static Tile[][] map = {
			{ new Wall(), new Wall(), new Wall(), new Wall(), new Wall() },
			{ new Wall(), new Floor(), new Box(), new Floor(), new Wall() },
			{ new Wall(), new Floor(), new Pillar(), new Floor(), new Wall() },
			{ new Wall(), new Box(), new Floor(), new Box(), new Wall() },
			{ new Wall(), new Floor(), new Box(), new Floor(), new Wall() },
			{ new Wall(), new Floor(), new Floor(), new Floor(), new Wall() },
			{ new Wall(), new Wall(), new Wall(), new Wall(), new Wall() } };

	@BeforeClass
	public static void setUpMap() {
		// TODO jocke perhaps the map should be initialized here
	}

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(2, 3);
		timer = new Timer();
		player = new Player(1, "Kalle", pos);
		bomb = new AreaBomb(player, timer);
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

		List<Position> expectedPositions = new ArrayList<Position>();
		expectedPositions.add(new Position(1, 2));
		expectedPositions.add(new Position(3, 2));
		expectedPositions.add(new Position(1, 3));
		expectedPositions.add(new Position(2, 3));
		expectedPositions.add(new Position(3, 3));
		expectedPositions.add(new Position(2, 4));
		expectedPositions.add(new Position(1, 4));
		expectedPositions.add(new Position(3, 4));
		List<Position> actualPositions = new ArrayList<Position>(bomb.explode(
				map).keySet());

		// TODO Fix the test for the new returntype: Map<Position, Direction>.

		// can not use the lists equal method since it does not take into
		// consideration that the two lists might have the positions at
		// different index
		assertEquals(expectedPositions.size(), actualPositions.size());

		for (Position pos : expectedPositions) {
			assertTrue(actualPositions.contains(pos));
		}
	}

	@After
	public void tearDown() throws Exception {
		timer = null;
		player = null;
		bomb = null;
	}

	@AfterClass
	public static void destroyMap() {
		map = null;
	}

}
