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

	@Test
	public void testGetRandomNbr() {
		int sum = 0;
		int max = 20;
		for (int j = 0; j < max; j++) {
			int k = 0;
			for (int i = 0; i < 100; i++) {
				Destroyable tile = new Box();
				Tile t = tile.onFire();
				if (t instanceof PowerupItem) {
					k++;
				}
			}
			sum += k;
		}
		int ratio = sum / max;
		int probability = (int) (Parameters.INSTANCE.getPowerUpProbabilityBox() * 100);
		int delta = 3;
		assertEquals(probability, ratio, delta);
	}

	@Test
	public void testGetTileType() {
		String expected = "box";
		String actual = box.getTileType();
		assertTrue(actual.startsWith(expected));
	}

	@Test
	public void testGetPointGiver() {
		PointGiver expected = PointGiver.Box;
		PointGiver actual = box.getPointGiver();
		assertEquals(expected, actual);
	}

	@Test
	public void testEquals() {
		Tile otherBox = new Box();
		Tile otherWall = new Wall();

		// testing for null and self
		assertTrue(box.equals(box));
		assertFalse(box.equals(null));

		// should be true
		assertTrue(box.equals(otherBox));

		// should be false since an box is not an wall
		assertFalse(box.equals(otherWall));
	}

	@Test
	public void testHashCode() {

		Tile otherBox = new Box();
		Tile otherWall = new Wall();

		// should be true
		assertTrue(box.hashCode() == otherBox.hashCode());

		// should be false since an box is not an wall
		assertFalse(box.hashCode() == otherWall.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		box = null;
	}
}
