package com.github.joakimpersson.tda367.core;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class HighscoreTest {

	@Test
	public void testUpdate() {
		Highscore hs = new Highscore();
		ArrayList<Player> players = new ArrayList<Player>();
		
		for(int i = 0; i < 6; i++) {
			Player tmpPlayer = new Player(("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}
	}

	@Test
	public void testSaveList() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
	}

}
