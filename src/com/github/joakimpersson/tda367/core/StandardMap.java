package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.core.tiles.Box;
import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Pillar;
import com.github.joakimpersson.tda367.core.tiles.Wall;


/**
 * @date 2012-03-26
 * @author Viktor Anderling
 * 
 * An implementation of a standard Bomberman map
 *
 */
public class StandardMap extends GameField {
	
	private	double boxProbability = 0.85;
	
	/* The initial matrix with a size of 15x13, where a 1 represents a Wall-tile,
	 * a 7 represents a Pillar-Tile and a 0 represents a Floor-Tile */
	private int[][] initMatrix =   {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
									{1, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 1},
									{1, 0, 7, 3, 7, 3, 7, 3, 7, 3, 7, 3, 7, 0, 1},
									{1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
									{1, 0, 7, 3, 7, 3, 7, 3, 7, 3, 7, 3, 7, 0, 1},
									{1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
									{1, 0, 7, 3, 7, 3, 7, 3, 7, 3, 7, 3, 7, 0, 1},
									{1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
									{1, 0, 7, 3, 7, 3, 7, 3, 7, 3, 7, 3, 7, 0, 1},
									{1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1},
									{1, 0, 7, 3, 7, 3, 7, 3, 7, 3, 7, 3, 7, 0, 1},
									{1, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 1},
									{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
								
	
	public StandardMap() {
		super(15, 13);
		resetField();
	}
	
	/**
	 * A method that is used to create the game-field, but also functions
	 * as way to reset it to it initial state.
	 */
	@Override
	public void resetField() {
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 15; j++) {
				switch(initMatrix[i][j]) {
					
					case 1: this.setTile(new Wall(), j, i);
							break;
					
					case 3:	if(Math.random() >= boxProbability) {
								setTile(new Box(), j, i);
							} else {
								setTile(new Floor(), j, i);
							}
							break;
					
					case 7:	setTile(new Pillar(), j, i);
							break;
					
					default:setTile(new Floor(), j, i);
							break;
				}
			}
		}
		
		
	/*// The enclosing walls are created here.
		for(int i = 0; i < 13; i++) {
			// Sets the vertical walls.
			if(i != 13) {
				setTile(new Wall(), 0, i);
				setTile(new Wall(), 14, i);
			}
			// Sets the horizontal walls.
			if(i != 0) {
				setTile(new Wall(), i, 0);
				setTile(new Wall(), i, 12);
			}
		}
		// Here are the pillars created.
		for(int i = 2; i <= 10; i = i+2) {
			for(int j = 2; j <= 12; j = j+2) {
				setTile(new Pillar(), i, j);
			}
		}
		/* Placing boxes with a set probability. Boxes are never placed in the 2x2 corners.
		for(int i = 1; i < 11; i++) {
			for(int j = 1; j <11; j++) {...
				
			}
		} */
	}
}
