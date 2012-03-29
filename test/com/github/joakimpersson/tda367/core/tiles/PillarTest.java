package com.github.joakimpersson.tda367.core.tiles;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.Parameters;
import com.github.joakimpersson.tda367.core.tiles.powerupitems.PowerupItem;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PillarTest {

	private Pillar pillar;

	@Before
	public void setUp() throws Exception {

		pillar = new Pillar();

	}

	@Test
	public void testGetToughness() {
		assertEquals(2, pillar.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = pillar.onFire();
		assertThat(tile, is(instanceOf(WalkableTile.class)));
	}

	@Test
	public void testIsWalkable() {
		assertFalse(pillar.isWalkable());
	}

	@After
	public void tearDown() throws Exception {
		pillar = null;
	}

	@Test
	public void test() {
		int sum = 0;
		int max = 20;
		for (int j = 0; j < max; j++) {
			int k = 0;
			for (int i = 0; i < 100; i++) {
				Tile tile = new Pillar();
				Tile t = tile.onFire();
				if (t instanceof PowerupItem) {
					k++;
				}
			}
			sum += k;
		}
		
		int ratio = sum / max;
		int probability = (int) (Parameters.INSTANCE
				.getPowerUpProbabilityPillar() * 100);
		int diff = 2;
		assertTrue((ratio >= (probability - diff))
				&& (ratio <= (probability + diff)));
	}

}
