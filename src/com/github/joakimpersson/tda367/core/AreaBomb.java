package com.github.joakimpersson.tda367.core;

public class AreaBomb extends Bomb {
	int power, range;

	public AreaBomb(Player player, int range, int power, Position pos) {
		super(player, pos);
		this.range = range;
		this.power = power;
	}

	@Override
	public void explode() {
		int xPos = getPos().getX();
		int yPos = getPos().getY();
		
		for (int x = xPos-range; x < xPos+range; x++) {
			for (int y = yPos-range; y < yPos+range; y++) {
				tryBreak(x,y,power);
			}
		}

		for (int y = 0; y < range; y++) {
			for (int x = 0; x < range; x++) {
				tryBreak(xPos+x,yPos+y,power);
				tryBreak(xPos-x,yPos-y,power);
			}
		}
		
	}
	
}
