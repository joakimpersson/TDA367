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

import com.github.joakimpersson.tda367.core.Attribute;
import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.tiles.Box;
import com.github.joakimpersson.tda367.core.tiles.Fire;
import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Pillar;
import com.github.joakimpersson.tda367.core.tiles.Tile;
import com.github.joakimpersson.tda367.core.tiles.Wall;

public class NormalBombTest {

	private Timer timer;
	private NormalBomb bomb;
	private Player player;
	private static Tile[][] map = {
			{ new Wall(), new Wall(), new Wall(), new Wall(), new Wall() },
			{ new Wall(), new Floor(), new Box(), new Floor(), new Wall() },
			{ new Wall(), new Floor(), new Pillar(), new Floor(), new Wall() },
			{ new Wall(), new Box(), new Floor(), new Box(), new Wall() },
			{ new Wall(), new Floor(), new Floor(), new Floor(), new Wall() },
			{ new Wall(), new Floor(), new Box(), new Floor(), new Wall() },
			{ new Wall(), new Wall(), new Wall(), new Wall(), new Wall() } };

	@BeforeClass
	public static void setUpMap() {
		// TODO jocke perhaps the map should be initialized here
	}

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(2, 3);
		timer = new Timer();
		player = new Player("Kalle", pos);
		bomb = new NormalBomb(player, timer);
	}

	// TODO jocke about a test need with increased power or if it is coverd when testing tryBreak!
	@Test
	public void testExplode() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].toString() + "\t");
			}
			System.out.println("\n");
		}
		List<Position> expectedDestroyedPositions = new ArrayList<Position>();
		expectedDestroyedPositions.add(new Position(3, 3));
		expectedDestroyedPositions.add(new Position(3, 1));
		expectedDestroyedPositions.add(new Position(4, 2));
		expectedDestroyedPositions.add(new Position(5, 2));
		// spacing
		map[expectedDestroyedPositions.get(0).getX()][expectedDestroyedPositions
				.get(0).getY()] = new Fire();
		map[expectedDestroyedPositions.get(1).getX()][expectedDestroyedPositions
				.get(1).getY()] = new Fire();
		map[expectedDestroyedPositions.get(2).getX()][expectedDestroyedPositions
				.get(2).getY()] = new Fire();
		map[expectedDestroyedPositions.get(3).getX()][expectedDestroyedPositions
				.get(3).getY()] = new Fire();
		System.out.println();
		System.out.println();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j].toString() + "\t");
			}
			System.out.println("\n");
		}
		List<Position> actualDestroyedPositions = bomb.explode(map);
		assertEquals(expectedDestroyedPositions.size(),
				actualDestroyedPositions.size());
		for (int i = 0; i < expectedDestroyedPositions.size(); i++) {
			assertEquals(expectedDestroyedPositions.get(i),
					actualDestroyedPositions.get(i));
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
