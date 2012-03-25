package com.github.joakimpersson.tda367.core;

import org.junit.Test;

public class BoxTest {

	/**
	 * I know that this is not a real test just playing! TODO implement a real test
	 */
	@Test
	public void test() {
		int sum = 0;
		int max = 20;
		for (int j = 0; j < max; j++) {
			int k = 0;
			for (int i = 0; i < 60; i++) {
				Tile tile = new Box();
				Tile t = tile.fireAction();
				if (t instanceof SpeedUpdateItem) {
					k++;
				}
			}
			sum += k;
		}
		System.out.println(sum / max);
	}

}
