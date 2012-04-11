package com.github.joakimpersson.tda367.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Test;

import com.github.joakimpersson.tda367.model.Highscore;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.utils.Position;

public class HighscoreTest {

	@Test
	public void testUpdate() {
		Highscore hs = new Highscore();
		ArrayList<Player> players = new ArrayList<Player>();		
		for(int i = 0; i < 5; i++) {
			Player tmpPlayer = new Player(("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}
		
		hs.update(players);
		
		assertTrue(hs.getList().containsKey(players.get(0).getName()));
		
	}

	@Test
	public void testLoadList() {
		Highscore hs = new Highscore();
		ArrayList<Player> players = new ArrayList<Player>();
				
		for(int i = 0; i < 5; i++) {
			Player tmpPlayer = new Player(("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}
				
		hs.update(players);
		
		Map<String, Integer> tmpList = hs.getList();
		
		hs.clear();
		hs.loadList();
			
		assertEquals(tmpList, hs.getList());
	}

	@Test
	public void testGetSize() {
		Highscore hs = new Highscore();
		ArrayList<Player> players = new ArrayList<Player>();
		int size = 5;
				
		for(int i = 0; i < size; i++) {
			Player tmpPlayer = new Player(("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}
		
		hs.update(players);
		
		assertEquals(size, hs.getSize());
	}

}
