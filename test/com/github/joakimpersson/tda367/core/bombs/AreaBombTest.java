package com.github.joakimpersson.tda367.core.bombs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Box;
import com.github.joakimpersson.tda367.core.tiles.Fire;
import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Pillar;
import com.github.joakimpersson.tda367.core.tiles.Tile;
import com.github.joakimpersson.tda367.core.tiles.Wall;

/**
 * 
 * @author joakimpersson
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
		Position pos = new Position(3, 2);
		timer = new Timer();
		player = new Player("Kalle", pos);
		bomb = new AreaBomb(player, timer);
	}

	// TODO jocke about a test need with increased power or if it is covered
	// when testing tryBreak!
	@Test
	public void testExplode() {
		consoleMapView(map);

		List<Position> expectedPositions = new ArrayList<Position>();
		expectedPositions.add(new Position(2, 1));
		expectedPositions.add(new Position(2, 3));
		expectedPositions.add(new Position(3, 1));
		expectedPositions.add(new Position(3, 2));
		expectedPositions.add(new Position(3, 3));
		expectedPositions.add(new Position(4, 1));
		expectedPositions.add(new Position(4, 2));
		expectedPositions.add(new Position(4, 3));
		List<Position> actualPositions = bomb.explode(map);
		
		assertTrue(expectedPositions.equals(actualPositions));
		
		//just for debugging
		for (Position pos : expectedPositions) {
			map[pos.getX()][pos.getY()] = new Fire();
		}

		System.out.println();
		System.out.println();
		consoleMapView(map);

	}

	private void consoleMapView(Tile[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].toString() + "\t");
			}
			System.out.println("\n");
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
