package com.github.joakimpersson.tda367.core.bombs;

import java.util.Timer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Tile;

public class MockBombTest {
	
	private Tile[][] map;
	private Timer timer;
	private MockBomb bomb;
	private Player player;
	
	@BeforeClass
	public void setUpMap() {
		
	}

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(0, 0);
		timer = new Timer();
		player = new Player("Kalle", pos);
		bomb = new MockBomb(player, timer);
	}

	@After
	public void tearDown() throws Exception {
		timer = null;
		player = null;
		bomb = null;
	}
	
	@AfterClass
	public void destroyMap() {
		
	}
}
