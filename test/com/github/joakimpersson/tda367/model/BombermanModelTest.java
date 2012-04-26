package com.github.joakimpersson.tda367.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.player.Player;

public class BombermanModelTest {

	private IBombermanModel model;

	@Before
	public void setUp() throws Exception {
		model = BombermanModel.getInstance();
	}

	@Test
	public void testIsRoundOver() {
		boolean test1;
		boolean test2;
		boolean test3;
		
		test1 = model.isRoundOver();
		
		List<Player> playerList = model.getPlayers();
		for(Player p : playerList) {
			p.playerHit();
			p.playerHit();
		}
		test2 = model.isRoundOver();
		
		playerList.get(0).playerHit();
		test3 = model.isRoundOver();

		assertTrue(!test1 && !test2 && test3);
	}
	
	@Test
	public void testStartGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpgradePlayer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPlayers() {
		fail("Not yet implemented");
	}
	
	@After
	public void tearDown() throws Exception {
		model = null;
	}
}
