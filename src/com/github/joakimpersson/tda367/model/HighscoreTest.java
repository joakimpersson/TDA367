package com.github.joakimpersson.tda367.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.player.PlayerPoints;
import com.github.joakimpersson.tda367.model.utils.Position;

public class HighscoreTest {
	
	private Highscore hs;

	@Before
	public void setUp() throws Exception {
		hs = new Highscore();
	}

	@Test
	public void testUpdate() {
		ArrayList<Player> players = new ArrayList<Player>();		
		for(int i = 0; i < 5; i++) {
			Player tmpPlayer = new Player(1,("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}

		hs.update(players);

		assertTrue(hs.getList().containsKey(players.get(0).getName()));
	}

	@Test
	public void testGetList() {
		ArrayList<Player> players = new ArrayList<Player>();

		for(int i = 0; i < 2; i++) {
			Player tmpPlayer = new Player(1,("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}

		Map<String, PlayerPoints>playerList = new TreeMap<String, PlayerPoints>();
		
		PlayerPoints pp1 = new PlayerPoints();
		ArrayList<PointGiver> list1 = new ArrayList<PointGiver>();
		pp1.update(list1);
		String playerName1 = "Player0";
		
		PlayerPoints pp2 = new PlayerPoints();
		ArrayList<PointGiver> list2 = new ArrayList<PointGiver>();
		pp1.update(list2);
		String playerName2 = "Player1";
		
		playerList.put(playerName1, pp1);
		playerList.put(playerName2, pp2);
		
		hs.update(players);

		assertEquals(playerList, hs.getList());
	}

	@Test
	public void testGetSize() {
		ArrayList<Player> players = new ArrayList<Player>();
		int size = 5;

		for(int i = 0; i < size; i++) {
			Player tmpPlayer = new Player(1,("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}

		hs.update(players);

		assertEquals(size, hs.getSize());
	}

	@Test
	public void testReset() {
		ArrayList<Player> players = new ArrayList<Player>();
		int size = 5;

		for(int i = 0; i < size; i++) {
			Player tmpPlayer = new Player(1,("Player" + i), new Position(i, i));
			players.add(tmpPlayer);
		}

		hs.update(players);
		
		assertTrue(hs.getSize() > 0);
		
		hs.reset();

		assertTrue(hs.getSize() == 0);
	}
	
	@After
	public void tearDown() throws Exception {
		hs = null;
	}
}
