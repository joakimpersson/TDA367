package com.github.joakimpersson.tda367.core;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author joakimpersson
 *
 */
public class BombTest {

	private Bomb bomb;
	private Player player;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPlayer() {
		assertEquals(bomb.getPlayer(),player);
	}
	
	@After
	public void tearDown() throws Exception {
		player = null;
		bomb = null;
	}

}
