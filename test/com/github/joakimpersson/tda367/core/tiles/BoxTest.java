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
import com.github.joakimpersson.tda367.core.powerupitems.PowerupItem;

/**
 * 
 * @author joakimpersson
 * 
 */
public class BoxTest {

	private Box box;

	@Before
	public void setUp() throws Exception {
		box = new Box();
	}

	@Test
	public void testGetToughness() {
		assertEquals(1, box.getToughness());
	}

	@Test
	public void testOnFire() {
		Tile tile = box.onFire();
		assertThat(tile, is(instanceOf(WalkableTile.class)));
	}

	@Test
	public void testIsWalkable() {
		assertFalse(box.isWalkable());
	}

	@After
	public void tearDown() throws Exception {
		box = null;
	}

	@Test
	public void testGetRandomNbr() {
		int sum = 0;
		int max = 20;
		for (int j = 0; j < max; j++) {
			int k = 0;
			for (int i = 0; i < 100; i++) {
				Tile tile = new Box();
				Tile t = tile.onFire();
				if (t instanceof PowerupItem) {
					k++;
				}
			}
			sum += k;
		}
		// TODO perhaps another way to go?
		int ratio = sum / max;
		int probability = (int) (Parameters.INSTANCE.getPowerUpProbabilityBox() * 100);
		int diff = 2;
		assertTrue((ratio >= (probability - diff))
				&& (ratio <= (probability + diff)));
	}

}
