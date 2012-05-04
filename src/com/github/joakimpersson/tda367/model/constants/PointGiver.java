package com.github.joakimpersson.tda367.model.constants;

public enum PointGiver {
	RoundWon(100),MatchWon(200),Pillar(30), Box(10), PlayerHit(25), KillPlayer(50), PowerUpItem(20), Bomb(
			20), Floor(0);

	private int score;

	PointGiver(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}

}
