package com.github.joakimpersson.tda367.model;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BombermanModelTest {

	private IBombermanModel model;

	@Before
	public void setUp() throws Exception {
		model = BombermanModel.getInstance();
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
