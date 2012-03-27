package com.github.joakimpersson.tda367.core;

public class NormalBomb extends Bomb {
	int power, range;
	Bomberman bm = Bomberman.getInstance();

	public NormalBomb(Player player, int range, int power, Position pos) {
		super(player, pos);
		this.range = range;
		this.power = power;
	}
	
	@Override
	public void explode() {
		int xPos = getPos().getX();
		int yPos = getPos().getY();
		
		for (int i = 1; i <= range; i++) {
			int firePower = power;
			if (tryBreak(xPos,yPos+i,firePower)) {
				firePower--;
			}
		}
		
		for (int i = 1; i <= range; i++) {
			int firePower = power;
			if (tryBreak(xPos,yPos-i,firePower)) {
				firePower--;
			}
		}
		
		for (int i = 1; i <= range; i++) {
			int firePower = power;
			if (tryBreak(xPos+i,yPos,firePower)) {
				firePower--;
			}
		}
		
		for (int i = 1; i <= range; i++) {
			int firePower = power;
			if (tryBreak(xPos-i,yPos,firePower)) {
				firePower--;
			}
		}
	}

	private boolean tryBreak(int xPos, int i, int firePower) {
		if (firePower<)
			return false;
	}
}
