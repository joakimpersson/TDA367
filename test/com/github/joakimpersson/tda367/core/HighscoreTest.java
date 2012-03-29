package com.github.joakimpersson.tda367.core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HighscoreTest {

	@Test
	public void testUpdate() {
		Highscore hs = new Highscore();
		ArrayList<Player> players = new ArrayList<Player>();
		
		System.out.println("asd");
		
		for(int i = 0; i < 5; i++) {
			Player tmpPlayer = new Player(("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}
		
		System.out.println("asdf");
		
		hs.update(players);
		
		System.out.println("asdf");
		
		assertTrue(hs.getList().containsKey(players.get(0).getName()));
		
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
