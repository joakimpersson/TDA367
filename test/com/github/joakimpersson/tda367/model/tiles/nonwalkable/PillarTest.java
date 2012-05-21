package com.github.joakimpersson.tda367.model.tiles.nonwalkable;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.tiles.Destroyable;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.WalkableTile;
import com.github.joakimpersson.tda367.model.tiles.walkable.PowerupItem;

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

	@Test
	public void testGetRandomNbr() {
		int sum = 0;
		int max = 20;
		for (int j = 0; j < max; j++) {
			int k = 0;
			for (int i = 0; i < 100; i++) {
				Destroyable tile = new Pillar();
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
		int delta = 2;
		assertEquals(probability, ratio, delta);
	}

	@Test
	public void testEquals() {
		Tile otherPillar = new Pillar();
		Tile otherWall = new Wall();

		// testing for null and self
		assertTrue(pillar.equals(pillar));
		assertFalse(pillar.equals(null));

		// should be true
		assertTrue(pillar.equals(otherPillar));

		// should be false since an box is not an wall
		assertFalse(pillar.equals(otherWall));
	}

	@Test
	public void testHashCode() {

		Tile otherPillar = new Pillar();
		Tile otherWall = new Wall();

		// should be true
		assertTrue(pillar.hashCode() == otherPillar.hashCode());

		// should be false since an box is not an wall
		assertFalse(pillar.hashCode() == otherWall.hashCode());
	}

	@Test
	public void testGetPointGiver() {
		PointGiver expected = PointGiver.Pillar;
		PointGiver actual = pillar.getPointGiver();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetImage() {
		String expected = "pillar";
		String actual = pillar.getTileType();
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		pillar = null;
	}
}
