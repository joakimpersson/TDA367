package com.github.joakimpersson.tda367.model.constants;

public enum PointGiver {
	Pillar(20), Box(10), PlayerHit(15), KillPlayer(30), PowerUpItem(10), Bomb(
			10), Floor(0);

	private int score;

	PointGiver(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

}
