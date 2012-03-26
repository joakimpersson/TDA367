package com.github.joakimpersson.tda367.core;

import org.junit.Test;

import com.github.joakimpersson.tda367.core.powerupitems.SpeedUpdateItem;
import com.github.joakimpersson.tda367.core.tiles.Pillar;
import com.github.joakimpersson.tda367.core.tiles.Tile;

public class PillarTest {

	/**
	 * I know that this is not a real test just playing! TODO implement a real test
	 * test
	 */
	@Test
	public void test() {
		int sum = 0;
		int max = 20;
		for (int j = 0; j < max; j++) {
			int k = 0;
			for (int i = 0; i < 1000; i++) {
				Tile tile = new Pillar();
				Tile t = tile.onFire(1);
				if (t instanceof SpeedUpdateItem) {
					k++;
				}
			}
			sum += k;
		}
		System.out.println(sum / max);
	}

}
